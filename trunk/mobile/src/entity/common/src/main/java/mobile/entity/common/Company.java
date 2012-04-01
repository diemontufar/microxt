package mobile.entity.common;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
* The persistent class for the COMPANY database table.
* Values of companies
*/
@Entity
@Table(name="COMPANY")
public class Company extends AbstractEntity implements GeneralEntity{
private static final long serialVersionUID = 1L;

/**
* Company Id
*/
@Id
@Column(name="COMPANY_ID", nullable=false)
private String companyId;

/**
* Name of company
*/
@Column(name="NAME", nullable=false)
private String name;

/**
* Document Id
*/
@Column(name="DATAFILE_ID", nullable=true)
private Long datafileId;

/**
* Upgrade number of company
*/
@Column(name="UPGRADE_NUMBER", nullable=true)
private BigDecimal upgradeNumber;

/**
* License date of company
*/
@Column(name="LICENSE_DATE", nullable=true)
private Date licenseDate;

/**
* Enable
*/
@Column(name="ENABLE", nullable=false)
private Boolean enable;

public Company() {
}
public Company(String companyId) {
this.companyId = companyId;
}
public Company(String companyId,String name,Boolean enable) {
this.companyId = companyId;
this.name = name;
this.enable = enable;
}
public String getCompanyId() {
return this.companyId;
}
public void setCompanyId(String companyId) {
this.companyId = companyId;
}
public String getName() {
return this.name;
}
public void setName(String name) {
this.name = name;
}
public Long getDatafileId() {
return this.datafileId;
}
public void setDatafileId(Long datafileId) {
this.datafileId = datafileId;
}
public BigDecimal getUpgradeNumber() {
return this.upgradeNumber;
}
public void setUpgradeNumber(BigDecimal upgradeNumber) {
this.upgradeNumber = upgradeNumber;
}
public Date getLicenseDate() {
return this.licenseDate;
}
public void setLicenseDate(Date licenseDate) {
this.licenseDate = licenseDate;
}
public Boolean getEnable() {
return this.enable;
}
public void setEnable(Boolean enable) {
this.enable = enable;
}

@Override
public Object getPk() {
return this.companyId;
}

@Override
public void setPk(Object pk) {
this.companyId=(String) pk;
}

@Override
public String toString() {
return "COMPANY:[" +
this.getPk().toString() + ", " +
this.getName() + ", " +
this.getDatafileId() + ", " +
this.getUpgradeNumber() + ", " +
this.getLicenseDate() + ", " +
this.getEnable() + "]";
}
}
