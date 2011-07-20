package microxt.entity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import microxt.entity.common.AbstractHistoricalLocking;
import microxt.entity.common.GeneralEntity;
import microxt.entity.common.Historical;
import microxt.entity.common.HistoricalKey;
import microxt.entity.common.Multicompany;
import microxt.entity.common.Multilanguage;
import microxt.entity.common.OptimisticLocking;


/**
 * The persistent class for the PARAMETER database table.
 * 
 */
//@Entity
//@Table(name="PARAMETER")
public class Parameter extends AbstractHistoricalLocking implements Serializable, Cloneable,
		GeneralEntity, Multicompany, Multilanguage, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParameterPk pk;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="VALUE", length=100)
	private String value;

	//uni-directional many-to-one association to DataType
    @ManyToOne
	@JoinColumn(name="DATA_TYPE_ID")
	private DataType dataType;

	//uni-directional many-to-one association to ParameterId
    @ManyToOne
	@JoinColumn(name="PARAMETER_ID", nullable=false, insertable=false, updatable=false)
	private ParameterId parameterIdBean;

    public Parameter() {
    }

    public Parameter(String parameterId) {
    	this.pk = new ParameterPk(parameterId);
    }
    
	public ParameterPk getPk() {
		return this.pk;
	}

	public void setPk(ParameterPk id) {
		this.pk = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DataType getDataType() {
		return this.dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	
	public ParameterId getParameterIdBean() {
		return this.parameterIdBean;
	}

	public void setParameterIdBean(ParameterId parameterIdBean) {
		this.parameterIdBean = parameterIdBean;
	}
	
	public void setPk(Object pk) {
		this.setPk((ParameterPk)pk);
	}

	public void setPk(HistoricalKey pk) {
		this.setPk((ParameterPk)pk);
	}

	/*
	@Override
	public void setPk(MultilanguageKey pk) {
		this.setPk((ParameterPk)pk);
	}

	@Override
	public void setPk(MulticompanyKey pk) {
		this.setPk((ParameterPk)pk);
	}*/

	@Override
	public String toString() {
		return "[" + 
			this.getPk().toString() + ", " +
			this.getValue() + ", " +
			this.getDescription() + "]";
	}

	public Long getVersion(){
		return this.getVersion();
	}
	
}