package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

import microxt.entity.common.AbstractEntity;
import microxt.entity.common.GeneralEntity;

import java.math.BigDecimal;


/**
 * The persistent class for the ENTITY_FIELD database table.
 * 
 */
//@javax.persistence.Entity
//@Table(name="ENTITY_FIELD")
public class EntityField extends AbstractEntity implements Serializable, GeneralEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityFieldPK pk;

	@Column(name="CALCULATION", length=100)
	private String calculation;

	@Column(name="COMPANY_ID", nullable=false, length=4)
	private String companyId;

	@Column(name="DATA_SCALE", nullable=false, precision=10)
	private BigDecimal dataScale;

	@Column(name="DATA_SIZE", nullable=false, precision=10)
	private BigDecimal dataSize;

	@Column(name="DATA_TYPE_ID", nullable=false, length=30)
	private String dataTypeId;

	@Column(name="DEFAULT_VALUE", length=30)
	private String defaultValue;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="FIELD_ORDER", nullable=false, precision=10)
	private BigDecimal fieldOrder;

	@Column(name="HIDDEN", nullable=false, length=1)
	private String hidden;

	@Column(name="LABEL", length=100)
	private String label;

	@Column(name="MASK", length=100)
	private String mask;

	@Column(name="MAXIMUM_VALUE", length=30)
	private String maximumValue;

	@Column(name="MINIMUM_VALUE", length=30)
	private String minimumValue;

	@Column(name="NAME", nullable=false, length=30)
	private String name;

	@Column(name="NULLABLE", nullable=false, length=1)
	private String nullable;

	@Column(name="PRIMARY_KEY", nullable=false, length=1)
	private String primaryKey;

	@Column(name="READONLY", nullable=false, length=1)
	private String readonly;

	@Column(name="SECURITY_LEVEL", nullable=false, precision=10)
	private BigDecimal securityLevel;

	@Column(name="SEQUENCE_NAME", length=30)
	private String sequenceName;

	@Column(name="TOOLTIP", length=100)
	private String tooltip;

	@Column(name="UNIQUE_KEY", nullable=false, length=1)
	private String uniqueKey;

	@Column(name="VALIDATION", length=100)
	private String validation;

	//uni-directional many-to-one association to Entity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ENTITY_ID", nullable=false, insertable=false, updatable=false)
	private Entity entity;

    public EntityField() {
    }

	public EntityFieldPK getPk() {
		return this.pk;
	}

	public void setPk(EntityFieldPK pk) {
		this.pk = pk;
	}
	
	public String getCalculation() {
		return this.calculation;
	}

	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getDataScale() {
		return this.dataScale;
	}

	public void setDataScale(BigDecimal dataScale) {
		this.dataScale = dataScale;
	}

	public BigDecimal getDataSize() {
		return this.dataSize;
	}

	public void setDataSize(BigDecimal dataSize) {
		this.dataSize = dataSize;
	}

	public String getDataTypeId() {
		return this.dataTypeId;
	}

	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFieldOrder() {
		return this.fieldOrder;
	}

	public void setFieldOrder(BigDecimal fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public String getHidden() {
		return this.hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMask() {
		return this.mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getMaximumValue() {
		return this.maximumValue;
	}

	public void setMaximumValue(String maximumValue) {
		this.maximumValue = maximumValue;
	}

	public String getMinimumValue() {
		return this.minimumValue;
	}

	public void setMinimumValue(String minimumValue) {
		this.minimumValue = minimumValue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNullable() {
		return this.nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getReadonly() {
		return this.readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public BigDecimal getSecurityLevel() {
		return this.securityLevel;
	}

	public void setSecurityLevel(BigDecimal securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getSequenceName() {
		return this.sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getTooltip() {
		return this.tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getValidation() {
		return this.validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void setPk(Object pk) {
		setPk((EntityFieldPK)pk);
	}

	@Override
	public String toString() {
		return "[" + 
		this.getPk().toString() + ", " +
		this.getName() + ", " +
		this.getDataTypeId() + ", " +
		this.getDataSize() + ", " +
		this.getDataScale() 
		+ "]";
	}
	
}