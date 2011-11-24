package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the DEPARTMENT database table.
 */
@Embeddable
public class DepartmentPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
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

	/**
	 * Department Id
	 */
	@Column(name = "DEPARTMENT_ID", nullable = false)
	private String departmentId;

	public DepartmentPk() {
	}

	public DepartmentPk(String branchId, String officeId, String departmentId) {
		this.branchId = branchId;
		this.officeId = officeId;
		this.departmentId = departmentId;
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

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getBranchId() + ", " + this.getOfficeId() + ", "
				+ this.getDepartmentId() + "]";
	}
}
