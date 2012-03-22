package mobile.entity.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import mobile.common.message.Item;
import mobile.entity.common.EntityField;
import mobile.entity.common.EntityTable;
import mobile.entity.common.EntityTablePk;
import mobile.entity.common.Sequential;
import mobile.entity.common.SequentialPk;
import mobile.entity.schema.GeneralEntity;
import mobile.entity.schema.GeneralEntityId;
import mobile.entity.schema.GeneralEntityKey;
import mobile.entity.schema.Historical;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.OptimisticLocking;
import mobile.entity.schema.SequentialKey;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.CoreConverter;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class JpManager {

	public static JpManager INSTANCE = null;

	private EntityManager em;

	private final static String ENTITY_PACKAGE = "mobile.entity";

	private final static String ALL_COMPANY = "ALL";

	private final static Logger log = Log.getInstance();

	private JpManager() {
		// Initialize the entity manager manually
		// em = JPManagerFactory.getEntityManager();
	}

	public static void createEntityManager() {
		getInstance().em = JPManagerFactory.createEntityManager();
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JpManager();
		}
	}

	public static JpManager getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	public static void beginTransaction() {
		log.info("Begin transaction");
		getEntityManager().getTransaction().begin();
	}

	public static void commitTransaction() {
		log.info("Commit transaction");
		getEntityManager().getTransaction().commit();
	}

	public static void rollbackTransaction() {
		if ((getEntityManager().getTransaction() != null) && (getEntityManager().getTransaction().isActive())) {
			log.info("Rollback transaction");
			getEntityManager().getTransaction().rollback();
		}
	}

	public static EntityManager getEntityManager() {
		return getInstance().em;
	}

	public static void close() {
		if (getEntityManager() != null) {
			log.info("Close entity manager");
			getEntityManager().close();
		}
	}
	
	private static void setAutomaticSequential(GeneralEntity entity) throws Exception {
		if(entity.getPk() instanceof SequentialKey){
			SequentialKey key = (SequentialKey) entity.getPk();
			if(key.getId() == null ){
				// Get sequential name
				String QL_FIELD_WITH_SEQ = "select f from EntityField f where f.pk.tableId=:tableId "
						+ "and f.pk.companyId=:companyId " + "and f.primaryKey=1 and f.sequentialId is not null";
				TypedQuery<EntityField> query = getEntityManager().createQuery(QL_FIELD_WITH_SEQ, EntityField.class);
				query.setParameter("companyId", ALL_COMPANY);
				query.setParameter("tableId",toSqlName(entity.getClass().getSimpleName()));
				query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
	
				// Find sequential name
				EntityField fieldSeq = null;
				try {
					fieldSeq = query.getSingleResult();
				} catch (Exception e) {
					throw new Exception("SEQUENCIAL NO DEFINIDO PARA LA ENTIDAD: " + entity.getClass().getSimpleName());
				}

				if(fieldSeq != null){
					String seqName = fieldSeq.getSequentialId();
					
					// Get sequential number
					SequentialPk seqPk = new SequentialPk(seqName);
					Sequential seq = find(Sequential.class, seqPk);
					if(seq == null){
						throw new Exception("SEQUENCIAL NO DEFINIDO: " + seqName);
					}
					
					// Set sequential number and increment it
					key.setId(seq.getSequentialValue());
					seq.setSequentialValue(seq.getSequentialValue()+1);
					getEntityManager().merge(seq);
				}
			}
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
		// Automatic id generation
		setAutomaticSequential(entity);
		
		// Search Id
		if (hasId(entity)) {
			GeneralEntityId entityId = (GeneralEntityId) find(getEntityIdClass(entity), getEntityIdPk(entity));
			// If not exists, create
			if (entityId == null) {
				getEntityManager().persist(getEntityId(entity));
				JpManager.getEntityManager().flush();
			}
		}

		// Complete general fields
		completeAbstractFields(entity);

		// Persist
		getEntityManager().persist(entity);
		// this.em.detach(entity);
	}

	public static void persistOrUpdate(GeneralEntity entity) throws Exception {
		if (find(entity.getClass(), entity.getPk()) != null) {
			update(entity);
		} else {
			persist(entity);
		}

	}

	private static void completeAbstractFields(GeneralEntity entity) throws Exception {
		if (entity.getPk() instanceof GeneralEntityKey) {
			GeneralEntityKey key = (GeneralEntityKey) entity.getPk();
			completeAbstractFieldsForPk(key);
		}
		completeCreated(entity);
	}

	private static void completeAbstractFieldsForPk(GeneralEntityKey key) throws Exception {
		if (key instanceof MulticompanyKey) {
			MulticompanyKey multicompanyKey = (MulticompanyKey) key;
			if (multicompanyKey.getCompanyId() == null) {
				log.info("Complete companyId: " + key.getClass().getName());
				multicompanyKey.setCompanyId(LocalParameter.get(ParameterEnum.COMPANY, String.class));
			}
		}
		if (key instanceof MultilanguageKey) {
			MultilanguageKey multilanguageKey = (MultilanguageKey) key;
			if (multilanguageKey.getLanguageId() == null) {
				log.info("Complete languageId: " + key.getClass().getName());
				multilanguageKey.setLanguageId(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
			}
		}
		if (key instanceof HistoricalKey) {
			HistoricalKey historicalKey = (HistoricalKey) key;
			if (historicalKey.getExpired() == null) {
				log.info("Complete expired: " + key.getClass().getName());
				historicalKey.setExpired(Timer.getExpiredTime());
			}
		}
	}

	private static void completeCreated(Object obj) throws Exception {
		if (obj instanceof Historical) {
			Historical historical = (Historical) obj;
			if (historical.getCreated() == null) {
				log.info("Complete created: " + historical.getClass().getName());
				historical.setCreated(Timer.getCurrentTime());
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
		if (entity instanceof Historical) {
			expire((Historical) entity);
		} else {
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
		if (pk instanceof GeneralEntityKey) {
			completeAbstractFieldsForPk((GeneralEntityKey) pk);
		}

		// Apply locking, if the entity implements OptimistickLocking interface
		if (implementsInterface(type, OptimisticLocking.class)) {
			entity = getEntityManager().find(type, pk, LockModeType.OPTIMISTIC);
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
	public static <T> T findActiveEntity(GeneralEntityId generalId) throws Exception {
		Object obj;
		// Aplicar locking, si la entidad posee version
		if (implementsInterface(getEntityClass(generalId), OptimisticLocking.class)) {
			obj = getEntityManager().find(getEntityClass(generalId), (Object) getEntityPk(generalId),
					LockModeType.OPTIMISTIC);
		} else {
			obj = getEntityManager().find(getEntityClass(generalId), (Object) getEntityPk(generalId));
		}
		// Detach of the actual persistent context
		if (obj != null) {
			getEntityManager().detach(obj);
		}

		return (T) obj;
	}

	private static Class<?> getEntityClass(GeneralEntityId entityId) throws ClassNotFoundException {
		String strClass = entityId.getClass().getName();
		return Class.forName(strClass.substring(0, strClass.length() - 2));
	}

	private static Class<?> getEntityPkClass(GeneralEntityId entityId) throws ClassNotFoundException {
		String strClass = entityId.getClass().getName();
		String strEntityPkClass = strClass.substring(0, strClass.length() - 2) + "Pk";
		return Class.forName(strEntityPkClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static GeneralEntityKey getEntityPk(GeneralEntityId generalId) throws Exception {
		Object generalIdPk = generalId.getPk();
		Object entityPk = null;

		// Verify if the pk implements GeneralEntityKey
		if (JpManager.implementsInterface(generalIdPk.getClass(), GeneralEntityKey.class)) {
			// Check the fields
			Class idPkClass = generalIdPk.getClass();
			Field[] fields = idPkClass.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				// Instantiate pk
				Class pkClass = JpManager.getEntityPkClass(generalId);
				entityPk = pkClass.newInstance();
				// Set the fields
				for (Field field : fields) {
					if (field.getName().compareTo("serialVersionUID") == 0) {
						continue;
					}
					// Set the data
					String fieldName = field.getName();
					Method getterMethod = idPkClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1));
					Object val = getterMethod.invoke(generalIdPk);
					Method setterMethod = pkClass.getMethod(
							"set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
					setterMethod.invoke(entityPk, val);
				}
				// Set the default active values
				completeAbstractFieldsForPk((GeneralEntityKey) entityPk);
			}
		} else {
			// Obtain the name of the field
			Class entityIdClass = generalId.getClass();
			Field[] fields = entityIdClass.getDeclaredFields();
			String fieldName = "";
			for (Field field : fields) {
				if (field.getName().compareTo("serialVersionUID") == 0) {
					continue;
				}
				// Set the data
				fieldName = field.getName();
			}

			// Instantiate pk
			Class pkClass = JpManager.getEntityPkClass(generalId);
			entityPk = pkClass.newInstance();
			// Set the data
			Method setterMethod = pkClass.getMethod(
					"set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), generalIdPk.getClass());
			setterMethod.invoke(entityPk, generalIdPk);
			// Set the default active values
			completeAbstractFieldsForPk((GeneralEntityKey) entityPk);
		}

		return (GeneralEntityKey) entityPk;
	}

	private static Class<?> getEntityIdClass(GeneralEntity entity) throws Exception {
		try {
			String strClass = entity.getClass().getName() + "Id";
			return Class.forName(strClass);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private static Class<?> getEntityIdPkClass(GeneralEntity entity) throws Exception {
		Class entityIdClass = getEntityIdClass(entity);
		Class entityIdPkClass = null;
		if (entityIdClass != null) {
			Field[] fields = entityIdClass.getDeclaredFields();
			Field pkField = null;
			for (Field field : fields) {
				if (field.getName().compareTo("serialVersionUID") == 0) {
					continue;
				}
				pkField = field;
				break;
			}

			entityIdPkClass = pkField.getType();
		}
		return entityIdPkClass;

	}

	private static boolean hasId(GeneralEntity entity) throws Exception {
		EntityTable entityTable = getEntityTable(entity.getClass().getSimpleName());
		return entityTable.getHasTableId();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object getEntityIdPk(GeneralEntity entity) throws Exception {
		Object entityIdPk = null;

		Class entityIdPkClass = getEntityIdPkClass(entity);

		// Verify if the pk implements GeneralEntityKey
		if (JpManager.implementsInterface(entityIdPkClass, GeneralEntityKey.class)) {
			// Check the fields
			Field[] fields = entityIdPkClass.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				// Instantiate pk
				entityIdPk = entityIdPkClass.newInstance();
				// Set the fields
				for (Field field : fields) {
					if (field.getName().compareTo("serialVersionUID") == 0) {
						continue;
					}
					// Set the data
					String fieldName = field.getName();
					Method getterMethod = entity.getPk().getClass()
							.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
					Object val = getterMethod.invoke(entity.getPk());
					Method setterMethod = entityIdPkClass.getMethod("set" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1), field.getType());
					setterMethod.invoke(entityIdPk, val);
				}
			}
		} else {
			// Obtain the name of the field
			Class entityPkClass = entity.getPk().getClass();
			Field[] fields = entityPkClass.getDeclaredFields();
			Field uniqueField = null;
			for (Field field : fields) {
				if (field.getName().compareTo("serialVersionUID") == 0) {
					continue;
				}
				uniqueField = field;
				break;
			}

			// Instantiate pk
			//entityIdPk = entityIdPkClass.newInstance();
			
			// Set the data
			Method getterMethod = entityPkClass.getMethod("get" + uniqueField.getName().substring(0, 1).toUpperCase()
					+ uniqueField.getName().substring(1));
			Object val = getterMethod.invoke(entity.getPk());
			entityIdPk = val;
		}

		return entityIdPk;
	}

	private static GeneralEntityId getEntityId(GeneralEntity entity) throws Exception {
		Object pk = getEntityIdPk(entity);
		GeneralEntityId entityId = (GeneralEntityId) getEntityIdClass(entity).newInstance();
		entityId.setPk(pk);
		return entityId;
	}

	public static EntityTable getEntityTable(String entityName) throws Exception {
		EntityTable entity = null;

		EntityTablePk entityPk = new EntityTablePk(toTableName(entityName));
		entityPk.setCompanyId(ALL_COMPANY);
		entity = find(EntityTable.class, entityPk);
		if (entity == null) {
			throw new Exception("ENTITY_TABLE NOT FOUND: " + toTableName(entityName));
		}
		return entity;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static GeneralEntity parseEntity(String entityName, Item item, boolean isNew) throws Exception {
		// Get entity class
		EntityTablePk entityPk = new EntityTablePk(toTableName(entityName));
		entityPk.setCompanyId(ALL_COMPANY);
		EntityTable entityTable = JpManager.find(EntityTable.class, entityPk);
		if (entityTable == null) {
			throw new Exception("ENTITY_TABLE NOT FOUND: " + toTableName(entityName));
		}
		String packageName = entityTable.getPackageName();

		Class entityClass = Class.forName(ENTITY_PACKAGE + "." + packageName + "." + entityName);

		// Instantiate object
		Object entity = entityClass.newInstance();

		// Set embedded pk
		Boolean hasEmbeddePk = false;
		Object pk = null;
		for (Field classField : entityClass.getDeclaredFields()) {
			if (classField.getName().compareTo("pk") == 0) {
				// Parse embedded pk
				// Separate pk fields
				List<mobile.common.message.Field> pkFields = new ArrayList<mobile.common.message.Field>();
				for (mobile.common.message.Field itemField : item.getFieldList()) {
					if (itemField.getName().startsWith("pk_")) {
						pkFields.add(itemField);
					}
				}
				hasEmbeddePk = true;
				pk = parsePk(classField.getType(), pkFields);
				Method setterMethod = entityClass.getMethod("setPk", classField.getType());
				setterMethod.invoke(entity, pk);
				completeAbstractFieldsForPk((GeneralEntityKey) pk);
				break;
			}
		}

		// Set pk field (no embedded pk)
		if (!hasEmbeddePk) {
			for (Field classField : entityClass.getDeclaredFields()) {
				if (classField.getName().compareTo("pk") == 0
						|| classField.getName().compareTo("serialVersionUID") == 0) {
					continue;
				}

				// Set pk
				if (item.getField("pk_" + classField.getName()) != null) {
					Method setterMethod = entityClass.getMethod("set"
							+ classField.getName().substring(0, 1).toUpperCase() + classField.getName().substring(1),
							classField.getType());
					pk = CoreConverter.convertObject(item.getField("pk_" + classField.getName()).getValue(),
							classField.getType());
					setterMethod.invoke(entity, pk);
					break;
				}
			}
		}

		// Find the entity
		if (!isNew) {
			GeneralEntity findedEntity = (GeneralEntity) find(entity.getClass(), pk);
			if (findedEntity != null) {
				entity = findedEntity;
			}
		}

		// Set fields
		for (Field classField : entityClass.getDeclaredFields()) {
			if (classField.getName().compareTo("pk") == 0 || classField.getName().compareTo("serialVersionUID") == 0) {
				continue;
			}

			if (item.getField(classField.getName()) != null) {
				Method setterMethod = entityClass.getMethod("set" + classField.getName().substring(0, 1).toUpperCase()
						+ classField.getName().substring(1), classField.getType());
				Object val = CoreConverter.convertObject(item.getField(classField.getName()).getValue(),
						classField.getType());
				// System.out.println("Seteando...");
				// System.out.println(classField.getName());
				// System.out.println(item.getField(classField.getName()).getValue());
				// System.out.println(val);
				setterMethod.invoke(entity, val);
			}
		}

		return (GeneralEntity) entity;
	}

	private static GeneralEntityKey parsePk(Class<?> entityKeyClass, List<mobile.common.message.Field> pkFields)
			throws Exception {
		// Instantiate object
		Object pk = entityKeyClass.newInstance();

		// Set fields
		for (Field field : entityKeyClass.getDeclaredFields()) {
			if (field.getName().compareTo("serialVersionUID") == 0) {
				continue;
			}

			mobile.common.message.Field itemField = getField(field.getName(), pkFields);
			if (itemField != null) {
				Method setterMethod = entityKeyClass.getMethod("set" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1), field.getType());
				Object val = CoreConverter.convertObject(itemField.getValue(), field.getType());
				setterMethod.invoke(pk, val);
			}
		}

		return (GeneralEntityKey) pk;
	}

	private static mobile.common.message.Field getField(String name, List<mobile.common.message.Field> lField) {
		for (mobile.common.message.Field field : lField) {
			if ((field.getName().compareTo("pk_" + name)) == 0) {
				return field;
			}
		}
		return null;
	}

	public static String toTableName(String entityName) {
		String tableName = "";

		for (int i = 0; i < entityName.length(); i++) {
			String letter = entityName.substring(i, i + 1);
			if (letter.matches("[A-Z]") && i > 0) {
				letter = "_" + letter;
			}
			tableName = tableName + letter;
		}

		return tableName;
	}

	public static String toSqlName(String name) {
		String sqlName = "";

		for (int i = 0; i < name.length(); i++) {
			String letter = name.substring(i, i + 1);
			if (letter.matches("[A-Z]") && i > 0) {
				letter = "_" + letter;
			}
			sqlName = sqlName + letter;
		}

		return sqlName.toUpperCase();
	}

	/**
	 * Actualiza (merge) una entidad que fue aislada (detach) previamente del
	 * contexto actual.
	 * 
	 * @param entity
	 *            Entidad
	 */
	private static void updateEntity(GeneralEntity entity) throws Exception {
		completeAbstractFields(entity);
		getEntityManager().merge(entity);
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
		HistoricalKey pk = (HistoricalKey) ((HistoricalKey) expired.getPk()).clone();
		pk.setExpired(Timer.getCurrentTime());
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
		// Crear la entidad caducada
		Historical expired = find(pEntity.getClass(), pEntity.getPk());
		HistoricalKey pk = (HistoricalKey) ((HistoricalKey) expired.getPk()).clone();
		pk.setExpired(Timer.getCurrentTime());
		expired.setPk(pk);
		persist(expired);
		// Actualiza la entidad nueva
		Historical nueva = (Historical) pEntity;
		nueva.setCreated(Timer.getCurrentTime());
		updateEntity(pEntity);
	}

	public static void update(GeneralEntity entity) throws Exception {
		if (entity instanceof Historical) {
			manageHistory((Historical) entity);
		} else {
			updateEntity(entity);
		}
	}

	public static void detachList(List<? extends GeneralEntity> lEntities) {
		for (GeneralEntity generalEntity : lEntities) {
			getEntityManager().detach(generalEntity);
		}
	}

}