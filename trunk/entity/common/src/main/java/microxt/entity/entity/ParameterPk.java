package microxt.entity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import microxt.entity.common.AbstractHistoricalAndLanguageAndCompanyKey;
import microxt.entity.common.HistoricalKey;
import microxt.entity.common.MulticompanyKey;
import microxt.entity.common.MultilanguageKey;

/**
 * The primary key class for the PARAMETER database table.
 * 
 */
@Embeddable
public class ParameterPk extends AbstractHistoricalAndLanguageAndCompanyKey implements Serializable, 
		Cloneable, HistoricalKey, MulticompanyKey, MultilanguageKey{
	private static final long serialVersionUID = 1L;

	@Column(name="PARAMETER_ID", unique=true, nullable=false, length=30)
	private String parameterId;

	public ParameterPk() {
    }
	
	public ParameterPk(String parameterId) {
		this.parameterId = parameterId;
	}
	
	public String getParameterId() {
		return this.parameterId;
	}
	
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParameterPk)) {
			return false;
		}
		ParameterPk castOther = (ParameterPk)other;
		return 
			this.parameterId.equals(castOther.parameterId)
			&& super.equals(castOther);
    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.parameterId.hashCode();
		hash = hash * prime + super.hashCode();
		return hash;
    }
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public String toString() {
		return "[" +
			this.getCompanyId() + ", " +
			this.getLanguageId() + ", " +
			this.getExpired() + ", " +
			this.getParameterId() + "]";
	}
}