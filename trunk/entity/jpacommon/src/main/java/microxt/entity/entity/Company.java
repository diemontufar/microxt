package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@javax.persistence.Entity
@Table(name="COMPANY")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPANY_ID", unique=true, nullable=false, length=4)
	private String companyId;

	@Column(name="DATAFILE_ID", precision=10)
	private BigDecimal datafileId;

	@Column(name="ENABLE", length=1)
	private String enable;

    @Temporal( TemporalType.DATE)
	@Column(name="LICENSE_DATE")
	private Date licenseDate;

	@Column(name="NAME", nullable=false, length=40)
	private String name;

	@Column(name="UPGRADE_NUMERIC", precision=10, scale=2)
	private BigDecimal upgradeNumeric;

    public Company() {
    }

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getDatafileId() {
		return this.datafileId;
	}

	public void setDatafileId(BigDecimal datafileId) {
		this.datafileId = datafileId;
	}

	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Date getLicenseDate() {
		return this.licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUpgradeNumeric() {
		return this.upgradeNumeric;
	}

	public void setUpgradeNumeric(BigDecimal upgradeNumeric) {
		this.upgradeNumeric = upgradeNumeric;
	}

}