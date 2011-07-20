package microxt.entity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import microxt.entity.common.AbstractEntityId;
import microxt.entity.common.GeneralEntityId;
import microxt.entity.session.LocalParameter;
import microxt.entity.session.ParameterEnum;
import microxt.entity.util.PersistenceTime;


/**
 * The persistent class for the PARAMETER_ID database table.
 * 
 */
//@Entity
//@Table(name="PARAMETER_ID")
public class ParameterId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAMETER_ID", unique=true, nullable=false, length=30)
	private String parameterId;

    public ParameterId() {
    }
    
    public ParameterId(String parameterId) {
    	this.parameterId = parameterId;
    }

	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public Object getPk() {
		return this.getParameterId();
	}

	public void setPk(Object pk) {
		this.setParameterId((String)pk);
	}

	@Override
	public Class<?> getEntityDataClass() {
		return Parameter.class;
	}

	@Override
	public Object getEntityDataPk() throws Exception {
		ParameterPk pk = new ParameterPk();
		pk.setCompanyId(LocalParameter.get(ParameterEnum.COMPANY, String.class));
		pk.setLanguageId(LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		pk.setExpired(PersistenceTime.getExpiredTime());
		pk.setParameterId(this.getParameterId());
		return pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public String toString() {
		return "[" + this.getParameterId() + "]";
	}

}