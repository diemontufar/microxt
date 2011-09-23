package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the PERSON_ADDRESS database table.
 */
@Embeddable
public class PersonAddressPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Person Id
     */
    @Column(name = "PERSON_ID", nullable = false)
    private Long personId;

    /**
     * Sequence of person address
     */
    @Column(name = "ADDRESS_SEQUENCE", nullable = false)
    private Integer addressSequence;

    public PersonAddressPk() {}

    public PersonAddressPk(Long personId, Integer addressSequence) {
        this.personId = personId;
        this.addressSequence = addressSequence;
    }

    public Long getPersonId() {
        return this.personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getAddressSequence() {
        return this.addressSequence;
    }

    public void setAddressSequence(Integer addressSequence) {
        this.addressSequence = addressSequence;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getPersonId() + ", " + this.getAddressSequence() + "]";
    }
}
