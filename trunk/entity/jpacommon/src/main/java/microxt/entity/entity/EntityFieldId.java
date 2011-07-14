package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY_FIELD_ID database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY_FIELD_ID")
public class EntityFieldId implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityFieldIdPK id;

	//uni-directional many-to-one association to EntityId
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ENTITY_ID", nullable=false, insertable=false, updatable=false)
	private EntityId entityIdBean;

    public EntityFieldId() {
    }

	public EntityFieldIdPK getId() {
		return this.id;
	}

	public void setId(EntityFieldIdPK id) {
		this.id = id;
	}
	
	public EntityId getEntityIdBean() {
		return this.entityIdBean;
	}

	public void setEntityIdBean(EntityId entityIdBean) {
		this.entityIdBean = entityIdBean;
	}
	
}