package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;


/**
 * The primary key class for the COUNTRY database table.
 */
@Embeddable
public class CountryPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
    private static final long serialVersionUID = 1L;

    /**
     * Country Id
     */
    @Column(name = "COUNTRY_ID", nullable = false)
    private String countryId;

    public CountryPk() {}

    public CountryPk(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
                + this.getCountryId() + "]";
    }
}
