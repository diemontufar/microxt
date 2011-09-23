package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;


/**
 * The primary key class for the WORKFLOW_PROCESS_ID database table.
 */
@Embeddable
public class WorkflowProcessIdPk extends AbstractEntityKey implements GeneralEntityKey {
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

    public WorkflowProcessIdPk() {}

    public WorkflowProcessIdPk(Long workflowId, Integer workflowSequence) {
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
        return "[" + this.getWorkflowId() + ", " + this.getWorkflowSequence()
                + "]";
    }
}
