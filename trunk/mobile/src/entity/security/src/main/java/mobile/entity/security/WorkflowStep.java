package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the WORKFLOW_STEP database table.
 * Values of workflow steps
 */
@Entity
@Table(name = "WORKFLOW_STEP")
public class WorkflowStep extends AbstractEntity implements Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private WorkflowStepPk pk;

    /**
     * Name of workflow step
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public WorkflowStep() {}

    public WorkflowStep(WorkflowStepPk pk) {
        this.pk = pk;
    }

    public WorkflowStep(WorkflowStepPk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public WorkflowStepPk getPk() {
        return this.pk;
    }

    public void setPk(WorkflowStepPk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (WorkflowStepPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        WorkflowStep copy = (WorkflowStep) super.clone();

        copy.setPk((WorkflowStepPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "WORKFLOW_STEP:[" + this.getPk().toString() + ", "
                + this.getName() + "]";
    }
}
