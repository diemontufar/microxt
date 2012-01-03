package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the SOLICITUDE_STATUS_ID database table.
*/
@Entity
@Table(name="SOLICITUDE_STATUS_ID")
public class SolicitudeStatusId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Status
*/
@Id
@Column(name="SOLICITUDE_STATUS_ID", nullable=false)
private String solicitudeStatusId;

public SolicitudeStatusId() {
}
public SolicitudeStatusId(String solicitudeStatusId) {
this.solicitudeStatusId = solicitudeStatusId;
}
public String getSolicitudeStatusId() {
return this.solicitudeStatusId;
}
public void setSolicitudeStatusId(String solicitudeStatusId) {
this.solicitudeStatusId = solicitudeStatusId;
}

@Override
public Object getPk() {
return this.solicitudeStatusId;
}

@Override
public void setPk(Object pk) {
this.solicitudeStatusId=(String) pk;
}

@Override
public String toString() {
return "SOLICITUDE_STATUS_ID:[" +
this.getPk().toString() + "]";
}
}
