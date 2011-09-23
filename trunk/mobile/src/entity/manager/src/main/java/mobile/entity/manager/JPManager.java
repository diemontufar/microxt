package mobile.entity.manager;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;
import mobile.entity.manager.util.PersistenceTime;
import mobile.entity.schema.GeneralEntity;
import mobile.entity.schema.GeneralEntityId;
import mobile.entity.schema.GeneralEntityKey;
import mobile.entity.schema.Historical;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.OptimisticLocking;

public class JPManager {
	public static JPManager INSTANCE = null;
	private EntityManager em;

	private JPManager(){
		// Initialize the entity manager manually  
		// em = JPManagerFactory.getEntityManager();
	}
	
	public static void createEntityManager(){
		getInstance().em = JPManagerFactory.createEntityManager();
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance(){
		if (INSTANCE == null) {
			INSTANCE = new JPManager();
		}
	}

	public static JPManager getInstance(){
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
	 * @see javax.persistence.EntityManager.persist
	 * 
	 * @param entity
	 *            entity to persist
	 * @throws Exception
	 */
	public static void persist(GeneralEntity entity) throws Exception {
		if (entity != null) {
			// Search Id
			if(hasId(entity)){
				GeneralEntityId entityId = (GeneralEntityId) find(getEntityIdClass(entity),getEntityIdPk(entity));
				// If not exists, create
				if(entityId==null){
					getEntityManager().persist(getEntityId(entity));
					JPManager.getEntityManager().flush();
				}
			}
			
			// Complete general fields
			completeAbstractFields(entity);
			completeCreated(entity);
			
			//Persist
			getEntityManager().persist(entity);
			//this.em.detach(entity);
		} else {
			throw new Exception("ERROR");
		}
	}
	
	private static void completeAbstractFields(GeneralEntity entity) throws Exception{
		if(entity.getPk() instanceof GeneralEntity){
			GeneralEntityKey key = (GeneralEntityKey) entity.getPk();
			completeAbstractFieldsForPk(key);
			completeCreated(entity);
		}
	}
	
	private static void completeAbstractFieldsForPk(GeneralEntityKey key) throws Exception{
		if(key instanceof MulticompanyKey){
			MulticompanyKey multicompanyKey = (MulticompanyKey) key;
			if (multicompanyKey.getCompanyId() == null){
				System.out.println("Complete companyId");
				multicompanyKey.setCompanyId(LocalParameter.get(ParameterEnum.COMPANY,String.class));
			}
		}
		if(key instanceof MultilanguageKey){
			MultilanguageKey multilanguageKey = (MultilanguageKey) key;
			if(multilanguageKey.getLanguageId()==null){
				System.out.println("Complete languageId");
				multilanguageKey.setLanguageId(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
			}
		}
		if(key instanceof HistoricalKey){
			HistoricalKey historicalKey = (HistoricalKey) key;
			if(historicalKey.getExpired()==null){
				System.out.println("Complete expired");
				historicalKey.setExpired(PersistenceTime.getExpiredTime());
			}
		}
	}
	
	private static void completeCreated(Object obj) throws Exception{
		if(obj instanceof Historical){
			Historical historical = (Historical) obj;
			if(historical.getCreated()==null){
				System.out.println("Complete created");
				historical.setCreated(PersistenceTime.getCurrentTime());
			}
		}
	}

	/**
	 * Removes a detached entity
	 * 
	 * @param entity
	 *            entity for removing
	 * @throws Exception
	 */
	private static void deleteEntity(GeneralEntity entity) throws Exception {
		// Merge
		entity = getEntityManager().merge(entity);
		
		// Delete
		getEntityManager().remove(entity);
		
		// TODO: Consider remove entityId
		
	}
	
	public static void delete(GeneralEntity entity) throws Exception {
		if(entity instanceof Historical){
			expire((Historical)entity);
		}else{
			deleteEntity(entity);
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
	 * Obtiene una referencia a una entidad dada la clase de la entidad y su Pk.
	 * Se utiliza optimistic locking si la entidad tiene el campo version
	 * 
	 * @param type
	 *            Clase de la Entidad
	 * @param pk
	 *            Clave primaria
	 * @return Una referencia a la Entidad encontrada o null si no existe
	 * @throws Exception 
	 */
	public static <T> T find(Class<T> type, Object pk) throws Exception {
		return find(type, pk, true);
	}

	public static <T> T find(Class<T> type, Object pk, boolean detachEntity) throws Exception {
		T entity;
		
		// Complete general fields
		if(pk instanceof GeneralEntityKey){
			completeAbstractFieldsForPk((GeneralEntityKey)pk);
		}
		
		// Aply locking, if the entity implements OptimistickLocking interface
		if (implementsInterface(type, OptimisticLocking.class)) {
			entity = getEntityManager().find(type, pk,
					LockModeType.OPTIMISTIC);
		} else {
			entity = getEntityManager().find(type, pk);
		}

		// Detach from context
		if (detachEntity && entity != null) {
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
	public static <T> T findActiveEntity(GeneralEntityId generalId)
			throws Exception {
		Object obj;
		// Aplicar locking, si la entidad posee version
		if (implementsInterface(getEntityClass(generalId),
				OptimisticLocking.class)) {
			obj = getEntityManager().find(
					getEntityClass(generalId), (Object) getEntityPk(generalId),
					LockModeType.OPTIMISTIC);
		} else {
			obj = getEntityManager().find(
					getEntityClass(generalId), (Object) getEntityPk(generalId));
		}
		// Detach of the actual persistent context
		if (obj != null) {
			getEntityManager().detach(obj);
		}
		
		return (T) obj;
	}
	
	private static Class<?> getEntityClass(GeneralEntityId entityId) throws ClassNotFoundException {
		String strClass = entityId.getClass().getName();
		//System.out.println(strClass.substring(0,strClass.length()-2));
        return Class.forName(strClass.substring(0,strClass.length()-2));
    }
	
	private static Class<?> getEntityPkClass(GeneralEntityId entityId) throws ClassNotFoundException {
		String strClass = entityId.getClass().getName();
		String strEntityPkClass = strClass.substring(0,strClass.length()-2)+"Pk";
		//System.out.println(strEntityPkClass);
        return Class.forName(strEntityPkClass);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static GeneralEntityKey getEntityPk(GeneralEntityId generalId)
			throws Exception {
		Object generalIdPk = generalId.getPk();
		Object entityPk = null;
		
		// Verify if the pk implements GeneralEntityKey
		if(JPManager.implementsInterface(generalIdPk.getClass(),GeneralEntityKey.class)){
			// Check the fields
			Class idPkClass = generalIdPk.getClass();
			Field[] fields = idPkClass.getDeclaredFields();
			if( fields!=null && fields.length>0){
				// Instantiate pk
				Class pkClass = JPManager.getEntityPkClass(generalId);
				entityPk = pkClass.newInstance();
				// Set the fields
				for (Field field : fields) {
					if(field.getName().compareTo("serialVersionUID")==0){
						continue;
					}
					// Set the data
					String fieldName = field.getName();
					Method getterMethod = idPkClass.getMethod("get"+fieldName.substring(0,1).toUpperCase() 
							+ fieldName.substring(1));
					Object val = getterMethod.invoke(generalIdPk);
					Method setterMethod = 
							pkClass.getMethod("set"+fieldName.substring(0,1).toUpperCase() 
									+ fieldName.substring(1),field.getType());
					setterMethod.invoke(entityPk, val);		
				}
				// Set the default active values
				completeAbstractFieldsForPk((GeneralEntityKey)entityPk);
			}
		}
		else{
			//Obtain the name of the field
			Class entityIdClass = generalId.getClass();
			Field[] fields = entityIdClass.getDeclaredFields();
			String fieldName = "";
			for (Field field : fields) {
				if(field.getName().compareTo("serialVersionUID")==0){
					continue;
				}
				// Set the data
				fieldName = field.getName();		
			}
			
			// Instantiate pk
			Class pkClass = JPManager.getEntityPkClass(generalId);
			entityPk = pkClass.newInstance();
			// Set the data
			Method setterMethod = pkClass.getMethod("set"+fieldName.substring(0,1).toUpperCase() 
					+ fieldName.substring(1),generalIdPk.getClass());
			setterMethod.invoke(entityPk, generalIdPk);		
			// Set the default active values
			completeAbstractFieldsForPk((GeneralEntityKey)entityPk);
		}
		
		return (GeneralEntityKey) entityPk;
	}

	private static Class<?> getEntityIdClass(GeneralEntity entity) throws Exception {
		try {
			String strClass = entity.getClass().getName()+"Id";
			return Class.forName(strClass);	
		} catch (ClassNotFoundException e) {
			return null;
		}
    }
	
	@SuppressWarnings("rawtypes")
	private static Class<?> getEntityIdPkClass(GeneralEntity entity) throws Exception {
		Class entityIdClass = getEntityIdClass(entity);
		Class entityIdPkClass = null;
		if(entityIdClass!=null){
			Field[] fields = entityIdClass.getDeclaredFields();
			Field pkField = null;
			for (Field field : fields) {
				if(field.getName().compareTo("serialVersionUID")==0){
					continue;
				}
				pkField = field;
				break;
			}
			
			entityIdPkClass = pkField.getType();
		}
		return entityIdPkClass;
		
    }
	
	private static boolean hasId(GeneralEntity entity) throws Exception{
		return getEntityIdClass(entity)!=null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object getEntityIdPk(GeneralEntity entity)
			throws Exception {
		Object entityIdPk = null;
		
		Class entityIdPkClass = getEntityIdPkClass(entity);

		// Verify if the pk implements GeneralEntityKey
		if(JPManager.implementsInterface(entityIdPkClass,GeneralEntityKey.class)){
			// Check the fields
			Field[] fields = entityIdPkClass.getDeclaredFields();
			if( fields!=null && fields.length>0){
				// Instantiate pk
				entityIdPk = entityIdPkClass.newInstance();
				// Set the fields
				for (Field field : fields) {
					if(field.getName().compareTo("serialVersionUID")==0){
						continue;
					}
					// Set the data
					String fieldName = field.getName();
					Method getterMethod = entity.getPk().getClass().getMethod("get"+fieldName.substring(0,1).toUpperCase() 
							+ fieldName.substring(1));
					Object val = getterMethod.invoke(entity.getPk());
					Method setterMethod = 
							entityIdPkClass.getMethod("set"+fieldName.substring(0,1).toUpperCase() 
									+ fieldName.substring(1),field.getType());
					setterMethod.invoke(entityIdPk, val);		
				}
			}
		}else{
			//Obtain the name of the field
			Class entityPkClass = entity.getPk().getClass();
			Field[] fields = entityPkClass.getDeclaredFields();
			Field uniqueField = null;
			for (Field field : fields) {
				if(field.getName().compareTo("serialVersionUID")==0){
					continue;
				}
				uniqueField = field;
				break;
			}
			
			// Instantiate pk
			entityIdPk = entityIdPkClass.newInstance();
			// Set the data
			Method getterMethod = entityPkClass.getMethod("get"+uniqueField.getName().substring(0,1).toUpperCase() 
					+ uniqueField.getName().substring(1));
			Object val = getterMethod.invoke(entity.getPk());
			entityIdPk = val;		
		}
		
		return entityIdPk;
	}
	
	private static GeneralEntityId getEntityId(GeneralEntity entity) throws Exception{
		Object pk = getEntityIdPk(entity);
		GeneralEntityId entityId = (GeneralEntityId) getEntityIdClass(entity).newInstance();
		entityId.setPk(pk);
		return entityId;
	}
	
	/**
	 * Actualiza (merge) una entidad que fue aislada (detach) previamente del
	 * contexto actual.
	 * 
	 * @param obj
	 *            Entidad
	 */
	private static void updateEntity(Object obj) throws Exception {
		getEntityManager().merge(obj);
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
		HistoricalKey pk = (HistoricalKey) ((HistoricalKey)expired.getPk()).clone();
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
		//Crear la entidad caducada 
		Historical expired = find(pEntity.getClass(), pEntity.getPk());
		HistoricalKey pk = (HistoricalKey) ((HistoricalKey)expired.getPk()).clone();
		pk.setExpired(PersistenceTime.getCurrentTime());
		expired.setPk(pk);
		persist(expired);
		//Actualiza la entidad nueva
		Historical nueva = (Historical) pEntity;
		nueva.setCreated(PersistenceTime.getCurrentTime());
		updateEntity(pEntity);
	}
	
	public static void update(GeneralEntity entity) throws Exception{
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