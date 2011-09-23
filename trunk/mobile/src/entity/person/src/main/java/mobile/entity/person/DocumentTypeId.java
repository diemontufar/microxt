package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the DOCUMENT_TYPE_ID database table.
 */
@Entity
@Table(name = "DOCUMENT_TYPE_ID")
public class DocumentTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Document type Id
     */
    @Id
    @Column(name = "DOCUMENT_TYPE_ID", nullable = false)
    private String documentTypeId;

    public DocumentTypeId() {}

    public DocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeId() {
        return this.documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Override
    public Object getPk() {
        return this.documentTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.documentTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "DOCUMENT_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
