package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ENTITY_RELATIONSHIP database table.
 * 
 */
@Embeddable
public class EntityRelationshipPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RELATIONSHIP_ID", unique=true, nullable=false, precision=10)
	private long relationshipId;

	@Column(name="COMPANY_ID", unique=true, nullable=false, length=4)
	private String companyId;

	@Column(name="NAME", unique=true, nullable=false, length=30)
	private String name;

    public EntityRelationshipPK() {
    }
	public long getRelationshipId() {
		return this.relationshipId;
	}
	public void setRelationshipId(long relationshipId) {
		this.relationshipId = relationshipId;
	}
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntityRelationshipPK)) {
			return false;
		}
		EntityRelationshipPK castOther = (EntityRelationshipPK)other;
		return 
			(this.relationshipId == castOther.relationshipId)
			&& this.companyId.equals(castOther.companyId)
			&& this.name.equals(castOther.name);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.relationshipId ^ (this.relationshipId >>> 32)));
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.name.hashCode();
		
		return hash;
    }
}