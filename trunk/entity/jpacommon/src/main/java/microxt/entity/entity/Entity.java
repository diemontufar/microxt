package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

import microxt.entity.common.AbstractEntity;
import microxt.entity.common.GeneralEntity;


/**
 * The persistent class for the ENTITY database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY")
public class Entity extends AbstractEntity implements Serializable, GeneralEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ENTITY_ID", unique=true, nullable=false, precision=10)
	private Long entityId;

	@Column(name="CACHE_MEMORY", nullable=false, length=1)
	private String cacheMemory;

	@Column(name="COMPANY_ID", nullable=false, length=4)
	private String companyId;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="ENUMERATED_TYPES", nullable=false, length=1)
	private String enumeratedTypes;

	@Column(name="HISTORICAL_DATA", nullable=false, length=1)
	private String historicalData;

	@Column(name="MULTI_COMPANY", nullable=false, length=1)
	private String multiCompany;

	@Column(name="MULTI_LANGUAGE", nullable=false, length=1)
	private String multiLanguage;

	@Column(name="NAME", nullable=false, length=30)
	private String name;

	@Column(name="PACKAGE_NAME", nullable=false, length=30)
	private String packageName;

    public Entity() {
    }

	public Long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getCacheMemory() {
		return this.cacheMemory;
	}

	public void setCacheMemory(String cacheMemory) {
		this.cacheMemory = cacheMemory;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnumeratedTypes() {
		return this.enumeratedTypes;
	}

	public void setEnumeratedTypes(String enumeratedTypes) {
		this.enumeratedTypes = enumeratedTypes;
	}

	public String getHistoricalData() {
		return this.historicalData;
	}

	public void setHistoricalData(String historicalData) {
		this.historicalData = historicalData;
	}

	public String getMultiCompany() {
		return this.multiCompany;
	}

	public void setMultiCompany(String multiCompany) {
		this.multiCompany = multiCompany;
	}

	public String getMultiLanguage() {
		return this.multiLanguage;
	}

	public void setMultiLanguage(String multiLanguage) {
		this.multiLanguage = multiLanguage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Object getPk() {
		return getEntityId();
	}

	public void setPk(Object pk) {
		setEntityId((Long) pk);
	}

	@Override
	public String toString() {
		return "[" + 
		this.getPk().toString() + ", " +
		this.getName() + ", " +
		this.getDescription() + ", " +
		this.getPackageName() + ", " +
		"MCompany:"+this.getMultiCompany() + ", " +
		"MLanguage:"+this.getMultiLanguage() + ", " +
		"Historical"+this.getHistoricalData()
		+ "]";
	}

}