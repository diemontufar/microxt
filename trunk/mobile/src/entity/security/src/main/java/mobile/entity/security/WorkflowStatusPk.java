package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractLanguageKey;
import mobile.entity.schema.MultilanguageKey;


/**
 * The primary key class for the WORKFLOW_STATUS database table.
 */
@Embeddable
public class WorkflowStatusPk extends AbstractLanguageKey implements MultilanguageKey {
    private static final long serialVersionUID = 1L;

    /**
     * Workflow status Id
     */
    @Column(name = "WORKFLOW_STATUS_ID", nullable = false)
    private String workflowStatusId;

    public WorkflowStatusPk() {}

    public WorkflowStatusPk(String workflowStatusId) {
        this.workflowStatusId = workflowStatusId;
    }

    public String getWorkflowStatusId() {
        return this.workflowStatusId;
    }

    public void setWorkflowStatusId(String workflowStatusId) {
        this.workflowStatusId = workflowStatusId;
    }

    @Override
    public String toString() {
        return "[" + this.getLanguageId() + ", " + this.getWorkflowStatusId()
                + "]";
    }
}
