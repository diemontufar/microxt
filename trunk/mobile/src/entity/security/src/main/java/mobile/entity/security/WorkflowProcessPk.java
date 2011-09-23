package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the WORKFLOW_PROCESS database table.
 */
@Embeddable
public class WorkflowProcessPk extends AbstractCompanyKey implements MulticompanyKey {
    private static final long serialVersionUID = 1L;

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

    public WorkflowProcessPk() {}

    public WorkflowProcessPk(Long workflowId, Integer workflowSequence) {
        this.workflowId = workflowId;
        this.workflowSequence = workflowSequence;
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

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getWorkflowId() + ", "
                + this.getWorkflowSequence() + "]";
    }
}
