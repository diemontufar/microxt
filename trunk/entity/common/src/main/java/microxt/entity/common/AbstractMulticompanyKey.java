package microxt.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractMulticompanyKey extends AbstractKey implements MulticompanyKey{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COMPANY_ID", unique = true, nullable = false, length=4)
	private String companyId;
	
	public String getCompanyId(){
		return this.companyId;
	}
	
	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		AbstractMulticompanyKey castOther = (AbstractMulticompanyKey) other;
		return this.companyId.equals(castOther.getCompanyId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		//hash = hash * prime + super.hashCode();
		return hash;
	}


}
