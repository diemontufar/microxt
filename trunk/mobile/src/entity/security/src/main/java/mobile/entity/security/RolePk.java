package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the ROLE database table.
 */
@Embeddable
public class RolePk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Profile Id
     */
    @Column(name = "PROFILE_ID", nullable = false)
    private String profileId;

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
     * Day Id
     */
    @Column(name = "DAY_ID", nullable = false)
    private String dayId;

    public RolePk() {}

    public RolePk(String profileId, String subsystemId, String moduleId, String processId, String dayId) {
        this.profileId = profileId;
        this.subsystemId = subsystemId;
        this.moduleId = moduleId;
        this.processId = processId;
        this.dayId = dayId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public String getDayId() {
        return this.dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getProfileId() + ", " + this.getSubsystemId() + ", "
                + this.getModuleId() + ", " + this.getProcessId() + ", "
                + this.getDayId() + "]";
    }
}
