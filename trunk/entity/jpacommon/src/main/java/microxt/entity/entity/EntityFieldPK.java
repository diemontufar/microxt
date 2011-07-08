package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

import microxt.entity.common.AbstractKey;
import microxt.entity.common.GeneralKey;

/**
 * The primary key class for the ENTITY_FIELD database table.
 * 
 */
@Embeddable
public class EntityFieldPK extends AbstractKey implements Serializable, GeneralKey {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ENTITY_ID", unique=true, nullable=false, precision=10)
	private long entityId;

	@Column(name="FIELD_ID", unique=true, nullable=false, precision=10)
	private long fieldId;

    public EntityFieldPK() {
    }
	public long getEntityId() {
		return this.entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public long getFieldId() {
		return this.fieldId;
	}
	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntityFieldPK)) {
			return false;
		}
		EntityFieldPK castOther = (EntityFieldPK)other;
		return 
			(this.entityId == castOther.entityId)
			&& (this.fieldId == castOther.fieldId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.entityId ^ (this.entityId >>> 32)));
		hash = hash * prime + ((int) (this.fieldId ^ (this.fieldId >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "[" + 
		this.getEntityId() + ", " +
		this.getFieldId() + "]";
	}
}