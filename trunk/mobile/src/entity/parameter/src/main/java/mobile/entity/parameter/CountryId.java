package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the COUNTRY_ID database table.
*/
@Entity
@Table(name="COUNTRY_ID")
public class CountryId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Country Id
*/
@Id
@Column(name="COUNTRY_ID", nullable=false)
private String countryId;

public CountryId() {
}
public CountryId(String countryId) {
this.countryId = countryId;
}
public String getCountryId() {
return this.countryId;
}
public void setCountryId(String countryId) {
this.countryId = countryId;
}

@Override
public Object getPk() {
return this.countryId;
}

@Override
public void setPk(Object pk) {
this.countryId=(String) pk;
}

@Override
public String toString() {
return "COUNTRY_ID:[" +
this.getPk().toString() + "]";
}
}
