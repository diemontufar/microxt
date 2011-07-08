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
	private static JpaManager INSTANCE = null;
	private EntityManager em;

	private JpaManager(){
		em = PersistenceFactory.getEntityManager();
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

	public static EntityManager getEntityManager(){
		return getInstance().em;
	}

	public static void close(){
		if (getEntityManager() != null) {
			getEntityManager().close();
		}
	}

	/**
	 * Make an instance managed and persistent.
	 * 
	 * @param entity
	 *            entity instance
	 * @throws Exception
	 */
	public static void create(Object entity) throws Exception {
		completeGeneralFields(entity);
		
		if (entity != null) {
			getEntityManager().persist(entity);
			// this.em.detach(entity);
		} else {
			throw new Exception("ERROR");
		}
	}
	
	/**
	 * Complete the general fields: Company, Language, Expired,
	 * Created. 
	 */
	public static void completeGeneralFields(Object entity) throws Exception{
		if(entity instanceof Historical){
			System.out.println("Es historico");
			Historical historical = (Historical) entity;
			historical.setCreated(PersistenceTime.getCurrentTime());
			historical.getPk().setExpired(PersistenceTime.getExpiredTime());
		}
		if(entity instanceof OptimisticLocking){
			System.out.println("Tiene version");
			//Nothing to do
		}
		if(entity instanceof Multilanguage){
			System.out.println("Es multilenguage");
			Multilanguage multilanguage = (Multilanguage) entity;
			multilanguage.getPk().setLanguageId(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		}
		if(entity instanceof Multicompany){
			System.out.println("Es multicompania");
			Multicompany multicompany = (Multicompany) entity;
			multicompany.getPk().setCompanyId(LocalParameter.get(ParameterEnum.COMPANY, String.class));
		}
	}
	
	/**
	 * Expire an historical entity 
	 * 
	 * @param entity
	 *            entity instance
	 */
	private static void expire(Historical entity) throws Exception {
		// Busca la entidad a caducar
		Historical deleted = find(entity.getClass(), entity.getPk());
		Historical expired = (Historical) deleted.clone();
		// Elimina el registro activo
		remove(deleted);
		// Inserta el registro caducado
		HistoricalKey pk = (HistoricalKey) expired.getPk().clone();
		pk.setExpired(PersistenceTime.getCurrentTime());
		expired.setPk(pk);
		getEntityManager().persist(expired);
	}

	/**
	 * Remove the entity instance 
	 * 
	 * @param entity
	 *            entity instance
	 */
	private static void remove(Object entity) throws Exception {
		if (entity != null) {
			entity = getEntityManager().merge(entity);
			getEntityManager().remove(entity);
		} else {
			throw new Exception("ERROR");
		}
	}
	
	/**
	 * Remove or expire the entity
	 * 
	 * @param entity
	 *            entity instance
	 * @throws Exception
	 */
	public static void delete(Object entity) throws Exception{
		if(entity instanceof Historical){
			expire((Historical)entity);
		}else{
			remove(entity);
		}
	}

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
	 * Find by primary key. Search for an entity of the specified class and 
	 * primary key. Lock automatically to OptimisticLocking if supported
	 * 
	 * @param type
	 * 		entity class
	 * @param pk
	 *      primary key
	 * @return 
	 * 		the found entity instance or null if the entity does not exist 
	 * @throws Exception 
	 */
	public static <T> T find(Class<T> type, Object pk) throws Exception {
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
	 * Find by ID Entity and lock. Search for an active entity using the id.
	 * Lock automatically to OptimisticLocking if supported.
	 * Detach the given entity from the persistence context
	 * 
	 * @param entityId
	 * 		id entity instance
	 * @return 
	 * 		the found active entity instance or null if the entity does not exist
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findActiveEntity(GeneralEntityId entityId)
			throws Exception {
		Object obj;
		// Lock if supported
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
	
	/**
	 * Find by ID Entity. Search for an active entity using the id.
	 * Set the lock
	 * Detach the given entity from the persistence context
	 * 
	 * @param entityId
	 * 		id entity instance
	 * @param lockType
	 * 		lock mode
	 * @return 
	 * 		the found active entity instance or null if the entity does not exist
	 * @throws Exception
	 */	@SuppressWarnings("unchecked")
	public static <T> T findActiveEntity(GeneralEntityId entityId,
			LockModeType lockType) throws Exception {
		Object obj = getEntityManager().find(entityId.getEntityDataClass(),entityId.getEntityDataPk(), lockType);
		if (obj != null){
			getEntityManager().detach(obj);
		}
		return (T) obj;
	}

	/**
	 * Merge the state of the given entity into the current persistence context.
	 * 
	 * @param entity
	 * 		entity instance
	 */
	private static void updateEntity(Object entity) throws Exception {
		if (entity != null) {
			getEntityManager().merge(entity);
		} else {
			throw new Exception("NULL OBJECT");
		}
	}

	/**
	 * Expire an entity whith old values and update 
	 * the active entity whit the new ones.
	 * 
	 * @param entity
	 * 		entity instance
	 */
	private static void manageHistory(Historical entity) throws Exception {
		//Crear la entidad caducada 
		Historical expired = find(entity.getClass(), entity.getPk());
		HistoricalKey pk = (HistoricalKey) expired.getPk().clone();
		pk.setExpired(PersistenceTime.getCurrentTime());
		expired.setPk(pk);
		getEntityManager().persist(expired);
		//Actualiza la entidad nueva
		updateEntity(entity);
	}
	
	/**
	 * Update or archive (expire+update) the entity.
	 * 
	 * @param entity
	 * 		entity instance
	 */
	public static void update(Object entity) throws Exception{
		if(entity instanceof Historical){
			manageHistory((Historical)entity);
		}else{
			updateEntity(entity);
		}
	}
	
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
	 * Get an instance, whose state may be lazily fetched.
	 * 
	 * @param pType
	 *            Clase de la entidad a buscar
	 * @param pk
	 *            Clave de la entidad a buscar
	 * @return La referencia encontrada
	 * @throws Exception 
	 */
	public static <T> T getReference(Class<T> entityClass, Object pk) throws Exception {
		return (T) getEntityManager().getReference(entityClass, pk);
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
	public static Query createNamedQuery(String name) throws Exception {
		return getEntityManager().createNamedQuery(name);
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
	public static <T> TypedQuery<T> createNamedQuery(String name,
			Class<T> resultClass) throws Exception {
		return getEntityManager().createNamedQuery(name, resultClass);
	}

	
}
