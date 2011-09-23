package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractLanguageKey;
import mobile.entity.schema.MultilanguageKey;


/**
 * The primary key class for the WORKFLOW_STEP database table.
 */
@Embeddable
public class WorkflowStepPk extends AbstractLanguageKey implements MultilanguageKey {
    private static final long serialVersionUID = 1L;

    /**
     * Workflow step Id
     */
    @Column(name = "WORKFLOW_STEP_ID", nullable = false)
    private String workflowStepId;

    public WorkflowStepPk() {}

    public WorkflowStepPk(String workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public String getWorkflowStepId() {
        return this.workflowStepId;
    }

    public void setWorkflowStepId(String workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    @Override
    public String toString() {
        return "[" + this.getLanguageId() + ", " + this.getWorkflowStepId()
                + "]";
    }
}
