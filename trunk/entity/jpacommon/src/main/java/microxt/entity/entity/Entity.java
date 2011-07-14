package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY")
public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityPK pk;

	@Column(name="CACHE_MEMORY", nullable=false, length=1)
	private String cacheMemory;

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

	@Column(name="OPTIMISTIC_LOCKING", nullable=false, length=1)
	private String optimisticLocking;

	@Column(name="PACKAGE_NAME", nullable=false, length=30)
	private String packageName;

	//uni-directional many-to-one association to EntityId
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ENTITY_ID", nullable=false, insertable=false, updatable=false)
	private EntityId entityIdBean;

	//uni-directional many-to-one association to Company
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COMPANY_ID", nullable=false, insertable=false, updatable=false)
	private Company company;

    public Entity() {
    }

	public EntityPK getPk() {
		return this.pk;
	}

	public void setPk(EntityPK pk) {
		this.pk = pk;
	}
	
	public String getCacheMemory() {
		return this.cacheMemory;
	}

	public void setCacheMemory(String cacheMemory) {
		this.cacheMemory = cacheMemory;
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

	public String getOptimisticLocking() {
		return this.optimisticLocking;
	}

	public void setOptimisticLocking(String optimisticLocking) {
		this.optimisticLocking = optimisticLocking;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public EntityId getEntityIdBean() {
		return this.entityIdBean;
	}

	public void setEntityIdBean(EntityId entityIdBean) {
		this.entityIdBean = entityIdBean;
	}
	
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}