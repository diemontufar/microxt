package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ENTITY_FIELD_ID database table.
 * 
 */
@Embeddable
public class EntityFieldIdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ENTITY_ID", unique=true, nullable=false, length=30)
	private String entityId;

	@Column(name="FIELD_ID", unique=true, nullable=false, length=30)
	private String fieldId;

    public EntityFieldIdPK() {
    }
	public String getEntityId() {
		return this.entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getFieldId() {
		return this.fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntityFieldIdPK)) {
			return false;
		}
		EntityFieldIdPK castOther = (EntityFieldIdPK)other;
		return 
			this.entityId.equals(castOther.entityId)
			&& this.fieldId.equals(castOther.fieldId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.entityId.hashCode();
		hash = hash * prime + this.fieldId.hashCode();
		
		return hash;
    }
}