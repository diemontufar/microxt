package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the WORKFLOW_MONITOR database table.
 */
@Embeddable
public class WorkflowMonitorPk extends AbstractCompanyHistoricalKey implements
		MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Workflow Id
	 */
	@Column(name = "JOB_NUMBER", nullable = false)
	private Long jobNumber;

	public WorkflowMonitorPk() {
	}

	public WorkflowMonitorPk(Long jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Long getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(Long jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getJobNumber() + "]";
	}
}
