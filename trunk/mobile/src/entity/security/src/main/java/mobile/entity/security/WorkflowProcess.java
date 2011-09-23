package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the WORKFLOW_PROCESS database table.
 * Values of workflow processes
 */
@Entity
@Table(name = "WORKFLOW_PROCESS")
public class WorkflowProcess extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private WorkflowProcessPk pk;

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

    public WorkflowProcess() {}

    public WorkflowProcess(WorkflowProcessPk pk) {
        this.pk = pk;
    }

    public WorkflowProcess(WorkflowProcessPk pk, String subsystemId, String moduleId, String processId) {
        this.pk = pk;
        this.subsystemId = subsystemId;
        this.moduleId = moduleId;
        this.processId = processId;
    }

    public WorkflowProcessPk getPk() {
        return this.pk;
    }

    public void setPk(WorkflowProcessPk pk) {
        this.pk = pk;
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

    @Override
    public void setPk(Object pk) {
        this.pk = (WorkflowProcessPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        WorkflowProcess copy = (WorkflowProcess) super.clone();

        copy.setPk((WorkflowProcessPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "WORKFLOW_PROCESS:[" + this.getPk().toString() + ", "
                + this.getSubsystemId() + ", " + this.getModuleId() + ", "
                + this.getProcessId() + "]";
    }
}
