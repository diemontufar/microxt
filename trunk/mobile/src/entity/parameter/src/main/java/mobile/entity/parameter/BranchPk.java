package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the BRANCH database table.
 */
@Embeddable
public class BranchPk extends AbstractCompanyKey implements MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Branch Id
	 */
	@Column(name = "BRANCH_ID", nullable = false)
	private String branchId;

	public BranchPk() {
	}

	public BranchPk(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getBranchId() + "]";
	}
}
