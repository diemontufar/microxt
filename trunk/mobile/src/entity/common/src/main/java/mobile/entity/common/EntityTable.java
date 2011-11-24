package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;

/**
 * The persistent class for the ENTITY_TABLE database table. Values of entity
 * tables
 */
@Entity
@Table(name = "ENTITY_TABLE")
public class EntityTable extends AbstractEntity implements Multicompany {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityTablePk pk;

	/**
	 * Order of fields
	 */
	@Column(name = "HAS_TABLE_ID", nullable = false)
	private Boolean hasTableId;

	/**
	 * Package name of entity
	 */
	@Column(name = "PACKAGE_NAME", nullable = false)
	private String packageName;

	/**
	 * Multi company of entity
	 */
	@Column(name = "MULTI_COMPANY", nullable = false)
	private Boolean multiCompany;

	/**
	 * Multi language of entity
	 */
	@Column(name = "MULTI_LANGUAGE", nullable = false)
	private Boolean multiLanguage;

	/**
	 * Historical data of entity
	 */
	@Column(name = "HISTORICAL_DATA", nullable = false)
	private Boolean historicalData;

	/**
	 * Optimistic locking of entity
	 */
	@Column(name = "OPTIMISTIC_LOCKING", nullable = false)
	private Boolean optimisticLocking;

	/**
	 * Enumerated type of entity
	 */
	@Column(name = "ENUMERATED_TYPES", nullable = false)
	private Boolean enumeratedTypes;

	/**
	 * Cache memory of entity
	 */
	@Column(name = "CACHE_MEMORY", nullable = false)
	private Boolean cacheMemory;

	/**
	 * Description of entity
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public EntityTable() {
	}

	public EntityTable(EntityTablePk pk) {
		this.pk = pk;
	}

	public EntityTable(EntityTablePk pk, Boolean hasTableId,
			String packageName, Boolean multiCompany, Boolean multiLanguage,
			Boolean historicalData, Boolean optimisticLocking,
			Boolean enumeratedTypes, Boolean cacheMemory, String description) {
		this.pk = pk;
		this.hasTableId = hasTableId;
		this.packageName = packageName;
		this.multiCompany = multiCompany;
		this.multiLanguage = multiLanguage;
		this.historicalData = historicalData;
		this.optimisticLocking = optimisticLocking;
		this.enumeratedTypes = enumeratedTypes;
		this.cacheMemory = cacheMemory;
		this.description = description;
	}

	public EntityTablePk getPk() {
		return this.pk;
	}

	public void setPk(EntityTablePk pk) {
		this.pk = pk;
	}

	public Boolean getHasTableId() {
		return this.hasTableId;
	}

	public void setHasTableId(Boolean hasTableId) {
		this.hasTableId = hasTableId;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Boolean getMultiCompany() {
		return this.multiCompany;
	}

	public void setMultiCompany(Boolean multiCompany) {
		this.multiCompany = multiCompany;
	}

	public Boolean getMultiLanguage() {
		return this.multiLanguage;
	}

	public void setMultiLanguage(Boolean multiLanguage) {
		this.multiLanguage = multiLanguage;
	}

	public Boolean getHistoricalData() {
		return this.historicalData;
	}

	public void setHistoricalData(Boolean historicalData) {
		this.historicalData = historicalData;
	}

	public Boolean getOptimisticLocking() {
		return this.optimisticLocking;
	}

	public void setOptimisticLocking(Boolean optimisticLocking) {
		this.optimisticLocking = optimisticLocking;
	}

	public Boolean getEnumeratedTypes() {
		return this.enumeratedTypes;
	}

	public void setEnumeratedTypes(Boolean enumeratedTypes) {
		this.enumeratedTypes = enumeratedTypes;
	}

	public Boolean getCacheMemory() {
		return this.cacheMemory;
	}

	public void setCacheMemory(Boolean cacheMemory) {
		this.cacheMemory = cacheMemory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (EntityTablePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		EntityTable copy = (EntityTable) super.clone();

		copy.setPk((EntityTablePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ENTITY_TABLE:[" + this.getPk().toString() + ", "
				+ this.getHasTableId() + ", " + this.getPackageName() + ", "
				+ this.getMultiCompany() + ", " + this.getMultiLanguage()
				+ ", " + this.getHistoricalData() + ", "
				+ this.getOptimisticLocking() + ", "
				+ this.getEnumeratedTypes() + ", " + this.getCacheMemory()
				+ ", " + this.getDescription() + "]";
	}
}
