package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the OFFICE database table.
 * Values of offices
 */
@Entity
@Table(name = "OFFICE")
public class Office extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OfficePk pk;

    /**
     * Name of office
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Country Id
     */
    @Column(name = "COUNTRY_ID", nullable = true)
    private String countryId;

    /**
     * Province Id
     */
    @Column(name = "PROVINCE_ID", nullable = true)
    private String provinceId;

    /**
     * City Id
     */
    @Column(name = "CITY_ID", nullable = true)
    private String cityId;

    public Office() {}

    public Office(OfficePk pk) {
        this.pk = pk;
    }

    public Office(OfficePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public OfficePk getPk() {
        return this.pk;
    }

    public void setPk(OfficePk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (OfficePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Office copy = (Office) super.clone();

        copy.setPk((OfficePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "OFFICE:[" + this.getPk().toString() + ", " + this.getName()
                + ", " + this.getCountryId() + ", " + this.getProvinceId()
                + ", " + this.getCityId() + "]";
    }
}
