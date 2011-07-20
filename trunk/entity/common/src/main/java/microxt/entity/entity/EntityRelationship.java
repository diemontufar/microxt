package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY_RELATIONSHIP database table.
 * 
 */
//@javax.persistence.Entity
//@Table(name="ENTITY_RELATIONSHIP")
public class EntityRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityRelationshipPK pk;

	@Column(name="RELATIONSHIP_TYPE", length=30)
	private String relationshipType;

	//uni-directional many-to-one association to EntityField
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ENTITY_FROM", referencedColumnName="ENTITY_ID", nullable=false),
		@JoinColumn(name="FIELD_FROM", referencedColumnName="FIELD_ID", nullable=false)
		})
	private EntityField entityFrom;

	//uni-directional many-to-one association to EntityField
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ENTITY_TO", referencedColumnName="ENTITY_ID", nullable=false),
		@JoinColumn(name="FIELD_TO", referencedColumnName="FIELD_ID", nullable=false)
		})
	private EntityField entityTo;

    public EntityRelationship() {
    }

	public EntityRelationshipPK getPk() {
		return this.pk;
	}

	public void setId(EntityRelationshipPK pk) {
		this.pk = pk;
	}
	
	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public EntityField getEntityFrom() {
		return this.entityFrom;
	}

	public void setEntityFrom(EntityField entityFrom) {
		this.entityFrom = entityFrom;
	}
	
	public EntityField getEntityTo() {
		return this.entityTo;
	}

	public void setEntityTo(EntityField entityTo) {
		this.entityTo = entityTo;
	}
	
}