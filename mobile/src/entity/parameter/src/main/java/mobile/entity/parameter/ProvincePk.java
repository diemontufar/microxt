package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
* The primary key class for the PROVINCE database table.
*/
@Embeddable
public class ProvincePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey{
private static final long serialVersionUID = 1L;

/**
* Country Id
*/
@Column(name="COUNTRY_ID", nullable=false)
private String countryId;

/**
* Province Id
*/
@Column(name="PROVINCE_ID", nullable=false)
private String provinceId;

public ProvincePk() {
}
public ProvincePk(String countryId,String provinceId) {
this.countryId = countryId;
this.provinceId = provinceId;
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

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getLanguageId() + ", " +
this.getCountryId() + ", " +
this.getProvinceId() + "]";
}
}
