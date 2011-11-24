package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the OFFICE database table.
 */
@Embeddable
public class OfficePk extends AbstractCompanyKey implements MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Branch Id
	 */
	@Column(name = "BRANCH_ID", nullable = false)
	private String branchId;

	/**
	 * Office Id
	 */
	@Column(name = "OFFICE_ID", nullable = false)
	private String officeId;

	public OfficePk() {
	}

	public OfficePk(String branchId, String officeId) {
		this.branchId = branchId;
		this.officeId = officeId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getOfficeId() {
		return this.officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getBranchId() + ", "
				+ this.getOfficeId() + "]";
	}
}
