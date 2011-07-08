package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY_RELATIONSHIP database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY_RELATIONSHIP")
public class EntityRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityRelationshipPK id;

	@Column(name="RELATIONSHIP_TYPE", length=30)
	private String relationshipType;

	//uni-directional many-to-one association to EntityField
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ENTITY_FROM", referencedColumnName="ENTITY_ID", nullable=false),
		@JoinColumn(name="FIELD_FROM", referencedColumnName="FIELD_ID", nullable=false)
		})
	private EntityField entityField1;

	//uni-directional many-to-one association to EntityField
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ENTITY_TO", referencedColumnName="ENTITY_ID", nullable=false),
		@JoinColumn(name="FIELD_TO", referencedColumnName="FIELD_ID", nullable=false)
		})
	private EntityField entityField2;

    public EntityRelationship() {
    }

	public EntityRelationshipPK getId() {
		return this.id;
	}

	public void setId(EntityRelationshipPK id) {
		this.id = id;
	}
	
	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public EntityField getEntityField1() {
		return this.entityField1;
	}

	public void setEntityField1(EntityField entityField1) {
		this.entityField1 = entityField1;
	}
	
	public EntityField getEntityField2() {
		return this.entityField2;
	}

	public void setEntityField2(EntityField entityField2) {
		this.entityField2 = entityField2;
	}
	
}