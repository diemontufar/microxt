package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;


/**
 * The primary key class for the WORKFLOW_DECISION_ID database table.
 */
@Embeddable
public class WorkflowDecisionIdPk extends AbstractEntityKey implements GeneralEntityKey {
    private static final long serialVersionUID = 1L;

    /**
     * Decision Id
     */
    @Column(name = "DECISION_ID", nullable = false)
    private String decisionId;

    /**
     * Decision sequence
     */
    @Column(name = "DECISION_SEQUENCE", nullable = false)
    private Integer decisionSequence;

    public WorkflowDecisionIdPk() {}

    public WorkflowDecisionIdPk(String decisionId, Integer decisionSequence) {
        this.decisionId = decisionId;
        this.decisionSequence = decisionSequence;
    }

    public String getDecisionId() {
        return this.decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public Integer getDecisionSequence() {
        return this.decisionSequence;
    }

    public void setDecisionSequence(Integer decisionSequence) {
        this.decisionSequence = decisionSequence;
    }

    @Override
    public String toString() {
        return "[" + this.getDecisionId() + ", " + this.getDecisionSequence()
                + "]";
    }
}
