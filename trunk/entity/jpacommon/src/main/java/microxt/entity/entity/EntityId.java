package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY_ID database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY_ID")
public class EntityId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ENTITY_ID", unique=true, nullable=false, length=30)
	private String entityId;

    public EntityId() {
    }

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}