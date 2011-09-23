package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the WORKFLOW_DECISION_ID database table.
 */
@Entity
@Table(name = "WORKFLOW_DECISION_ID")
public class WorkflowDecisionId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private WorkflowDecisionIdPk pk;

    public WorkflowDecisionId() {}

    public WorkflowDecisionId(WorkflowDecisionIdPk pk) {
        this.pk = pk;
    }

    public WorkflowDecisionIdPk getPk() {
        return this.pk;
    }

    public void setPk(WorkflowDecisionIdPk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (WorkflowDecisionIdPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        WorkflowDecisionId copy = (WorkflowDecisionId) super.clone();

        copy.setPk((WorkflowDecisionIdPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "WORKFLOW_DECISION_ID:[" + this.getPk().toString() + "]";
    }
}
