package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the OFFICE_ID database table.
 */
@Embeddable
public class OfficeIdPk extends AbstractEntityKey implements GeneralEntityKey {
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

	public OfficeIdPk() {
	}

	public OfficeIdPk(String branchId, String officeId) {
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
		return "[" + this.getBranchId() + ", " + this.getOfficeId() + "]";
	}
}
