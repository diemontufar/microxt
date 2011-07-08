package microxt.entity.util;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import microxt.entity.common.GeneralEntityId;
import microxt.entity.common.Historical;
import microxt.entity.common.HistoricalKey;
import microxt.entity.common.Multicompany;
import microxt.entity.common.Multilanguage;
import microxt.entity.common.OptimisticLocking;
import microxt.entity.session.LocalParameter;
import microxt.entity.session.ParameterEnum;

public class JpaManager {
	public static JpaManager INSTANCE = null;
	private EntityManager em;

	private JpaManager(){
		// Initialize the entity manager manually  
		//em = PersistenceFactory.getEntityManager();
	}
	
	public static void createEntityManager(){
		getInstance().em = PersistenceFactory.createEntityManager();
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance(){
		if (INSTANCE == null) {
			INSTANCE = new JpaManager();
		}
	}

	public static JpaManager getInstance(){
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	public static void beginTransaction(){
		getEntityManager().getTransaction().begin();
	}
	
	public static void commitTransaction(){
		if (getEntityManager().getTransaction() != null) {
			getEntityManager().getTransaction().commit();
		} else {
			new Exception("NULL TRANSACTION");
		}
	}
	
	public static void rollbackTransaction(){
		if (getEntityManager().getTransaction() != null) {
			getEntityManager().getTransaction().rollback();
		} else {
			new Exception("NULL TRANSACTION");
		}
	}

	public static EntityManager getEntityManager(){
		return getInstance().em;
	}

	public static void close(){
		if (getEntityManager() != null) {
			getEntityManager().close();
		}
	}

	/**
	 * Hace persistente una entidad
	 * 
	 * @param entity
	 *            Entidad a incluir en el contexto actual
	 * @throws Exception
	 */
	private static void persist(Object entity) throws Exception {
		
		if (entity != null) {
			setCreated(entity);
			getEntityManager().persist(entity);
			// this.em.detach(entity);
		} else {
			throw new Exception("ERROR");
		}
	}
	
	private static void completeGeneralFields(Object obj) throws Exception{
		if(obj instanceof Historical){
			System.out.println("Is historic");
			Historical historical = (Historical) obj;
			if(historical.getCreated()==null)
				historical.setCreated(PersistenceTime.getCurrentTime());
			if(historical.getPk().getExpired()==null)
				historical.getPk().setExpired(PersistenceTime.getExpiredTime());
		}
		//FIXME quitar esta parte, es solo por prueba
		if(obj instanceof OptimisticLocking){
			System.out.println("Has version");
			//Nothing to do
		}
		if(obj instanceof Multilanguage){
			System.out.println("Is multi-language");
			Multilanguage multilanguage = (Multilanguage) obj;
			if(multilanguage.getPk().getLanguageId()==null)
				multilanguage.getPk().setLanguageId(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		}
		if(obj instanceof Multicompany){
			System.out.println("Is multi-company");
			Multicompany multicompany = (Multicompany) obj;
			if (multicompany.getPk().getCompanyId() == null)
				multicompany.getPk().setCompanyId(LocalParameter.get(ParameterEnum.COMPANY,String.class));
		}
	}
	
	private static void setCreated(Object obj) throws Exception{
		if(obj instanceof Historical){
			Historical historical = (Historical) obj;
			historical.setCreated(PersistenceTime.getCurrentTime());
		}
	}
	
	/*
	public static void create(Dto dto) throws Exception {
		//Create the id entity, if necesary
		GeneralEntityId id = (GeneralEntityId)convertToEntityId(dto); 
		
		if(dto.getEntityIdCode()!=null && find(id.getClass(), id.getPk())==null){
			persist(id);
		}
		//Create de entity
		persist(convertToEntity(dto));
	}
	*/
	
	/*
	private static Object convertToEntity(Dto dto) throws Exception{
		// Build the name of entity searching in ENTITY table by ENTITY_ID
		//EntityPk pk = new EntityPk();
		//pk.setEntityId(dto.GetEntityName());
		//Entity entity = find(Entity.class, pk);
		//String className;
		//if(entity != null){
		//className = entity.getPackageName()+"."+entity.getName();
		//}
		
		GeneralEntity entity = (GeneralEntity) Class.forName(getEntityClassName(dto.getEntityCode())).newInstance();
		entity.load(dto);
		//FIXME Se debe completar los campos antes de hacer persist o merge?
		completeGeneralFields(entity);

		return entity;
	}*/

	/*
	private static Object convertToEntityId(Dto dto) throws Exception{
		//GeneralEntityId entityId = (GeneralEntityId) dto.getEntityIdClass().newInstance();
		GeneralEntityId entityId = (GeneralEntityId) Class.forName(getEntityClassName(dto.getEntityIdCode())).newInstance();
		entityId.load(dto);
		completeGeneralFields(entityId);

		return entityId;
	}*/
	
	/*
	private static String getEntityClassName(int code) throws Exception{
		Entity ent = find(Entity.class, code);
		if(ent==null){
			throw new Exception("ENTITY NOT FOUND");
		}else{
			return ent.getPackageName()+"."+ent.getName();
		}
	}*/
	
	/**
	 * Elimina una entidad
	 * 
	 * @param entity
	 *            Entidad a incluir en el contexto actual
	 * @throws Exception
	 */
	private static void deleteEntity(Object entity) throws Exception {
		if (entity != null) {
			entity = getEntityManager().merge(entity);
			getEntityManager().remove(entity);
		} else {
			throw new Exception("ERROR");
		}
		
	}
	
	public static void delete(Object entity) throws Exception {
		if(entity instanceof Historical){
			expire((Historical)entity);
		}else{
			deleteEntity(entity);
		}
		
	}
	
	/*
	public static void delete(Dto dto) throws Exception {
		delete(convertToEntity(dto));
	}*/

	@SuppressWarnings("rawtypes")
	public static boolean implementsInterface(Class pClass, Class pInterface) {
		for (Class c : pClass.getInterfaces()) {
			if (c.equals(pInterface)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene una referencia a una entidad dada la clase de la entidad y su Pk.
	 * Por defecto se utiliza optimistic locking
	 * 
	 * @param type
	 *            Clase de la Entidad
	 * @param pk
	 *            Clave primaria
	 * @return Una referencia a la Entidad encontrada o null si no existe
	 * @throws Exception 
	 */
	private static <T> T find(Class<T> type, Object pk) throws Exception {
		T entity;
		
		// Aplicar locking, si la entidad posee version
		if (implementsInterface(type, OptimisticLocking.class)) {
			entity = getEntityManager().find(type, pk,
					LockModeType.OPTIMISTIC);
		} else {
			entity = getEntityManager().find(type, pk);
		}

		// Separar del contexto 
		if (entity != null) {
			getEntityManager().detach(entity);
		}
		
		return entity;
	}

	/**
	 * Obtiene una referencia a una entidad activa dada su entidad ID. Por
	 * defecto se utiliza optimistic locking
	 * 
	 * @param entityId
	 *            Entidad Id
	 * @return Una referencia a la Entidad encontrada o null si no existe
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findActiveEntity(GeneralEntityId entityId)
			throws Exception {
		Object obj;
		// Aplicar locking, si la entidad posee version
		if (implementsInterface(entityId.getEntityDataClass(),
				OptimisticLocking.class)) {
			obj = getEntityManager().find(
					entityId.getEntityDataClass(), entityId.getEntityDataPk(),
					LockModeType.OPTIMISTIC);
		} else {
			obj = getEntityManager().find(
					entityId.getEntityDataClass(), entityId.getEntityDataPk());
		}
		// Detach of the actual persistent context
		if (obj != null) {
			getEntityManager().detach(obj);
		}
		return (T) obj;
	}
	
	/*
	public static Object findActiveDto(Dto id) throws Exception {
		//Convert dto to entity
		GeneralEntityId entityId = (GeneralEntityId) convertToEntityId(id);
		
		GeneralEntity entity = (GeneralEntity) findActiveEntity(entityId);
		
		return entity.toDto();
	}*/

	/**
	 * Obtiene una referencia a una entidad activa dada su entidad ID. Se puede
	 * establecer el tipo de bloqueo
	 * 
	 * @param entityId
	 *            Entidad Id
	 * @param lockModeType
	 *            Tipo de bloqueo
	 * @return Una referencia a la Entidad encontrada o null si no existe
	 * @throws Exception
	 */
	/*
	@SuppressWarnings("unchecked")
	private static <T> T findActiveEntity(GeneralEntityId entityId,
			LockModeType lockModeType) throws Exception {
		Object obj = getEntityManager().find(entityId.getEntityDataClass(),entityId.getEntityDataPk(), lockModeType);
		if (obj != null){
			getEntityManager().detach(obj);
		}
		return (T) obj;
	}*/

	/**
	 * Actualiza (merge) una entidad que fue aislada (detach) previamente del
	 * contexto actual.
	 * 
	 * @param obj
	 *            Entidad
	 */
	private static void updateEntity(Object obj) throws Exception {
		if (obj != null) {
			getEntityManager().merge(obj);
		} else {
			throw new Exception("NULL OBJECT");
		}
	}

	/**
	 * Caduca una entidad que maneja datos históricos
	 * 
	 * @param entity
	 *            Entidad a caducar
	 */
	private static void expire(Historical entity) throws Exception {
		// Busca la entidad a caducar
		Historical deleted = find(entity.getClass(), entity.getPk());
		Historical expired = (Historical) deleted.clone();
		// Elimina el registro activo
		deleteEntity(deleted);
		// Inserta el registro caducado
		HistoricalKey pk = (HistoricalKey) expired.getPk().clone();
		pk.setExpired(PersistenceTime.getCurrentTime());
		expired.setPk(pk);
		getEntityManager().persist(expired);
	}

	/**
	 * Actualiza una entidad con historicos, primero caduca el registro activo,
	 * y luego actualiza el nuevo registro
	 * 
	 * @param pEntity
	 *            Entidad a actualizar
	 */
	private static void manageHistory(Historical pEntity) throws Exception {
		/*
		// Caduca la entidad vieja (con los datos antiguos)
		Helper.expireEntity(pEntity);
		// Para que no se produzca el error de clave duplicada
		Helper.getEntityManager().flush(); 
		// Crea la entidad nueva (con los datos nuevos)
		// pEntity.setVersion(pEntity.getVersion()+1L); //No se permite
		Helper.getEntityManager().persist(pEntity);
		*/
		//Crear la entidad caducada 
		Historical expired = find(pEntity.getClass(), pEntity.getPk());
		HistoricalKey pk = (HistoricalKey) expired.getPk().clone();
		pk.setExpired(PersistenceTime.getCurrentTime());
		expired.setPk(pk);
		persist(expired);
		//Actualiza la entidad nueva
		Historical nueva = (Historical) pEntity;
		nueva.setCreated(PersistenceTime.getCurrentTime());
		updateEntity(pEntity);
	}
	
	public static void update(Object pObj) throws Exception{
		if(pObj instanceof Historical){
			manageHistory((Historical)pObj);
		}else{
			updateEntity(pObj);
		}
	}
	
	/*
	public static void updateDto(Dto dto) throws Exception{
		update(convertToEntity(dto));
	}*/
	
	
	/**
	 * There exists a special version of find() that can be used in one
	 * particular situation. That situation is when a relationship is being
	 * created between two entities in a one-to-one or many-to-one relationship
	 * in which the target entity already exists and its primary key is well
	 * known. Because we are only creating a relationship, it might not be
	 * necessary to fully load the target entity to create the foreign key
	 * reference to it. Only its primary key is required. The getReference()
	 * operation can be used for this purpose.
	 * 
	 * @param pType
	 *            Clase de la entidad a buscar
	 * @param pPk
	 *            Clave de la entidad a buscar
	 * @return La referencia encontrada
	 * @throws Exception 
	 */
	private static <T> T getReference(Class<T> pType, Object pPk) throws Exception {
		return (T) getReference(pType, pPk);
	}

	/**
	 * Create an instance of Query for executing a named query (in the Java
	 * Persistence query language or in native SQL).
	 * 
	 * @param name
	 *            the name of a query defined in metadata
	 * @return the new query instance
	 * @throws Exception 
	 * @see EntityManager#createNamedQuery(java.lang.String)
	 */
	public static Query createNamedQuery(String pName) throws Exception {
		return getEntityManager().createNamedQuery(pName);
	}

	/**
	 * Create an instance of TypedQuery for executing a Java Persistence query
	 * language named query. The select list of the query must contain only a
	 * single item, which must be assignable to the type specified by the
	 * resultClass argument.
	 * 
	 * @param name
	 *            the name of a query defined in metadata
	 * @param resultClass
	 *            the type of the query result
	 * @throws Exception 
	 * @returns the new query instance
	 * @see EntityManager#createNamedQuery(java.lang.String, java.lang.Class)
	 */
	public static <T> TypedQuery<T> createNamedQuery(String pName,
			Class<T> pResultClass) throws Exception {
		return createNamedQuery(pName, pResultClass);
	}
	
}