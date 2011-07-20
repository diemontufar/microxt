package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SEQUENTIAL database table.
 * 
 */
@Embeddable
public class SequentialPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_ID", unique=true, nullable=false, length=4)
	private String companyId;

	@Column(name="SEQUENTIAL_ID", unique=true, nullable=false, length=30)
	private String sequentialId;

    public SequentialPK() {
    }
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSequentialId() {
		return this.sequentialId;
	}
	public void setSequentialId(String sequentialId) {
		this.sequentialId = sequentialId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SequentialPK)) {
			return false;
		}
		SequentialPK castOther = (SequentialPK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.sequentialId.equals(castOther.sequentialId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.sequentialId.hashCode();
		
		return hash;
    }
}