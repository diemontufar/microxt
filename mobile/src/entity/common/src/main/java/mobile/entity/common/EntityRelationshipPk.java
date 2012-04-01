package mobile.entity.common;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
* The primary key class for the ENTITY_RELATIONSHIP database table.
*/
@Embeddable
public class EntityRelationshipPk extends AbstractCompanyKey implements MulticompanyKey{
private static final long serialVersionUID = 1L;

/**
* Relationship order
*/
@Column(name="RELATIONSHIP_ID", nullable=false)
private String relationshipId;

/**
* Field order
*/
@Column(name="RELATIONSHIP_ORDER", nullable=false)
private Integer relationshipOrder;

public EntityRelationshipPk() {
}
public EntityRelationshipPk(String relationshipId,Integer relationshipOrder) {
this.relationshipId = relationshipId;
this.relationshipOrder = relationshipOrder;
}
public String getRelationshipId() {
return this.relationshipId;
}
public void setRelationshipId(String relationshipId) {
this.relationshipId = relationshipId;
}
public Integer getRelationshipOrder() {
return this.relationshipOrder;
}
public void setRelationshipOrder(Integer relationshipOrder) {
this.relationshipOrder = relationshipOrder;
}

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getRelationshipId() + ", " +
this.getRelationshipOrder() + "]";
}
}
