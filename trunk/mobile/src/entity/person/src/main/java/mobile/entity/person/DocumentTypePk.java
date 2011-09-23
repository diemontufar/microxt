package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;


/**
 * The primary key class for the DOCUMENT_TYPE database table.
 */
@Embeddable
public class DocumentTypePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
    private static final long serialVersionUID = 1L;

    /**
     * Document type Id
     */
    @Column(name = "DOCUMENT_TYPE_ID", nullable = false)
    private String documentTypeId;

    public DocumentTypePk() {}

    public DocumentTypePk(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeId() {
        return this.documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
                + this.getDocumentTypeId() + "]";
    }
}
