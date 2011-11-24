package mobile.entity.security;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Historical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the WORKFLOW_MONITOR database table. Values of
 * workflow monitor
 */
@Entity
@Table(name = "WORKFLOW_MONITOR")
public class WorkflowMonitor extends AbstractHistoricalLocking implements
		Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkflowMonitorPk pk;

	/**
	 * Name of workflow step
	 */
	@Column(name = "JOB_DATE", nullable = false)
	private Date jobDate;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Workflow Id
	 */
	@Column(name = "WORKFLOW_ID", nullable = false)
	private Long workflowId;

	/**
	 * Workflow sequence
	 */
	@Column(name = "WORKFLOW_SEQUENCE", nullable = false)
	private Integer workflowSequence;

	/**
	 * Workflow status Id
	 */
	@Column(name = "WORKFLOW_STATUS_ID", nullable = false)
	private String workflowStatusId;

	/**
	 * Subsystem Id
	 */
	@Column(name = "SUBSYSTEM_ID", nullable = false)
	private String subsystemId;

	/**
	 * Module Id
	 */
	@Column(name = "MODULE_ID", nullable = false)
	private String moduleId;

	/**
	 * Process Id
	 */
	@Column(name = "PROCESS_ID", nullable = false)
	private String processId;

	/**
	 * Authorizer Id
	 */
	@Column(name = "AUTHORIZER_ID", nullable = false)
	private String authorizerId;

	public WorkflowMonitor() {
	}

	public WorkflowMonitor(WorkflowMonitorPk pk) {
		this.pk = pk;
	}

	public WorkflowMonitor(WorkflowMonitorPk pk, Date jobDate, String userId,
			Long workflowId, Integer workflowSequence, String workflowStatusId,
			String subsystemId, String moduleId, String processId,
			String authorizerId) {
		this.pk = pk;
		this.jobDate = jobDate;
		this.userId = userId;
		this.workflowId = workflowId;
		this.workflowSequence = workflowSequence;
		this.workflowStatusId = workflowStatusId;
		this.subsystemId = subsystemId;
		this.moduleId = moduleId;
		this.processId = processId;
		this.authorizerId = authorizerId;
	}

	public WorkflowMonitorPk getPk() {
		return this.pk;
	}

	public void setPk(WorkflowMonitorPk pk) {
		this.pk = pk;
	}

	public Date getJobDate() {
		return this.jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public Integer getWorkflowSequence() {
		return this.workflowSequence;
	}

	public void setWorkflowSequence(Integer workflowSequence) {
		this.workflowSequence = workflowSequence;
	}

	public String getWorkflowStatusId() {
		return this.workflowStatusId;
	}

	public void setWorkflowStatusId(String workflowStatusId) {
		this.workflowStatusId = workflowStatusId;
	}

	public String getSubsystemId() {
		return this.subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getAuthorizerId() {
		return this.authorizerId;
	}

	public void setAuthorizerId(String authorizerId) {
		this.authorizerId = authorizerId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (WorkflowMonitorPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		WorkflowMonitor copy = (WorkflowMonitor) super.clone();

		copy.setPk((WorkflowMonitorPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "WORKFLOW_MONITOR:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getJobDate() + ", "
				+ this.getUserId() + ", " + this.getWorkflowId() + ", "
				+ this.getWorkflowSequence() + ", "
				+ this.getWorkflowStatusId() + ", " + this.getSubsystemId()
				+ ", " + this.getModuleId() + ", " + this.getProcessId() + ", "
				+ this.getAuthorizerId() + ", " + this.getVersion() + "]";
	}
}
