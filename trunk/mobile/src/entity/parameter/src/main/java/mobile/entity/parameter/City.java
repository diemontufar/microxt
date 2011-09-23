package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the CITY database table.
 * Values of cities
 */
@Entity
@Table(name = "CITY")
public class City extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CityPk pk;

    /**
     * Name of city
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public City() {}

    public City(CityPk pk) {
        this.pk = pk;
    }

    public City(CityPk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public CityPk getPk() {
        return this.pk;
    }

    public void setPk(CityPk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (CityPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        City copy = (City) super.clone();

        copy.setPk((CityPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "CITY:[" + this.getPk().toString() + ", " + this.getName() + "]";
    }
}
