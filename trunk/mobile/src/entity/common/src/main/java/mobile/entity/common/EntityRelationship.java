package mobile.entity.common;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the ENTITY_RELATIONSHIP database table.
 * Values of entity relationships
 */
@Entity
@Table(name = "ENTITY_RELATIONSHIP")
public class EntityRelationship extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EntityRelationshipPk pk;

    /**
     * Table from
     */
    @Column(name = "TABLE_FROM", nullable = false)
    private String tableFrom;

    /**
     * Field from
     */
    @Column(name = "FIELD_FROM", nullable = false)
    private String fieldFrom;

    /**
     * Table to
     */
    @Column(name = "TABLE_TO", nullable = false)
    private String tableTo;

    /**
     * Filed to
     */
    @Column(name = "FIELD_TO", nullable = false)
    private String fieldTo;

    public EntityRelationship() {}

    public EntityRelationship(EntityRelationshipPk pk) {
        this.pk = pk;
    }

    public EntityRelationship(EntityRelationshipPk pk, String tableFrom, String fieldFrom, String tableTo, String fieldTo) {
        this.pk = pk;
        this.tableFrom = tableFrom;
        this.fieldFrom = fieldFrom;
        this.tableTo = tableTo;
        this.fieldTo = fieldTo;
    }

    public EntityRelationshipPk getPk() {
        return this.pk;
    }

    public void setPk(EntityRelationshipPk pk) {
        this.pk = pk;
    }

    public String getTableFrom() {
        return this.tableFrom;
    }

    public void setTableFrom(String tableFrom) {
        this.tableFrom = tableFrom;
    }

    public String getFieldFrom() {
        return this.fieldFrom;
    }

    public void setFieldFrom(String fieldFrom) {
        this.fieldFrom = fieldFrom;
    }

    public String getTableTo() {
        return this.tableTo;
    }

    public void setTableTo(String tableTo) {
        this.tableTo = tableTo;
    }

    public String getFieldTo() {
        return this.fieldTo;
    }

    public void setFieldTo(String fieldTo) {
        this.fieldTo = fieldTo;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (EntityRelationshipPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        EntityRelationship copy = (EntityRelationship) super.clone();

        copy.setPk((EntityRelationshipPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "ENTITY_RELATIONSHIP:[" + this.getPk().toString() + ", "
                + this.getTableFrom() + ", " + this.getFieldFrom() + ", "
                + this.getTableTo() + ", " + this.getFieldTo() + "]";
    }
}
