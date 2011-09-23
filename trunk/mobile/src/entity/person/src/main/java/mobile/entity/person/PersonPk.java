package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the PERSON database table.
 */
@Embeddable
public class PersonPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Person Id
     */
    @Column(name = "PERSON_ID", nullable = false)
    private Long personId;

    public PersonPk() {}

    public PersonPk(Long personId) {
        this.personId = personId;
    }

    public Long getPersonId() {
        return this.personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getPersonId() + "]";
    }
}
