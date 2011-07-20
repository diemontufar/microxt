package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ENTITY_RELATIONSHIP_FIELD database table.
 * 
 */
@Embeddable
public class EntityRelationshipFieldPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RELATIONSHIP_ID")
	private long relationshipId;

	@Column(name="RELATIONSHIP_ORDER")
	private long relationshipOrder;

    public EntityRelationshipFieldPK() {
    }
	public long getRelationshipId() {
		return this.relationshipId;
	}
	public void setRelationshipId(long relationshipId) {
		this.relationshipId = relationshipId;
	}
	public long getRelationshipOrder() {
		return this.relationshipOrder;
	}
	public void setRelationshipOrder(long relationshipOrder) {
		this.relationshipOrder = relationshipOrder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntityRelationshipFieldPK)) {
			return false;
		}
		EntityRelationshipFieldPK castOther = (EntityRelationshipFieldPK)other;
		return 
			(this.relationshipId == castOther.relationshipId)
			&& (this.relationshipOrder == castOther.relationshipOrder);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.relationshipId ^ (this.relationshipId >>> 32)));
		hash = hash * prime + ((int) (this.relationshipOrder ^ (this.relationshipOrder >>> 32)));
		
		return hash;
    }
}