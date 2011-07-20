package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTITY_RELATIONSHIP_FIELD database table.
 * 
 */
@javax.persistence.Entity
@Table(name="ENTITY_RELATIONSHIP_FIELD")
public class EntityRelationshipField implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityRelationshipFieldPK pk;

	@Column(name="ENTITY_FROM")
	private Integer entityFrom;

	@Column(name="ENTITY_TO")
	private Integer entityTo;

	@Column(name="FIELD_FROM")
	private Integer fieldFrom;

	@Column(name="FIELD_TO")
	private Integer fieldTo;

    public EntityRelationshipField() {
    }

	public EntityRelationshipFieldPK getPk() {
		return this.pk;
	}

	public void setPk(EntityRelationshipFieldPK pk) {
		this.pk = pk;
	}
	
	public Integer getEntityFrom() {
		return this.entityFrom;
	}

	public void setEntityFrom(Integer entityFrom) {
		this.entityFrom = entityFrom;
	}

	public Integer getEntityTo() {
		return this.entityTo;
	}

	public void setEntityTo(Integer entityTo) {
		this.entityTo = entityTo;
	}

	public Integer getFieldFrom() {
		return this.fieldFrom;
	}

	public void setFieldFrom(Integer fieldFrom) {
		this.fieldFrom = fieldFrom;
	}

	public Integer getFieldTo() {
		return this.fieldTo;
	}

	public void setFieldTo(Integer fieldTo) {
		this.fieldTo = fieldTo;
	}

}