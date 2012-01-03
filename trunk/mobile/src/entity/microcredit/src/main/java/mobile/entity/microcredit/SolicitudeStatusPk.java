package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
* The primary key class for the SOLICITUDE_STATUS database table.
*/
@Embeddable
public class SolicitudeStatusPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey{
private static final long serialVersionUID = 1L;

/**
* Status
*/
@Column(name="SOLICITUDE_STATUS_ID", nullable=false)
private String solicitudeStatusId;

public SolicitudeStatusPk() {
}
public SolicitudeStatusPk(String solicitudeStatusId) {
this.solicitudeStatusId = solicitudeStatusId;
}
public String getSolicitudeStatusId() {
return this.solicitudeStatusId;
}
public void setSolicitudeStatusId(String solicitudeStatusId) {
this.solicitudeStatusId = solicitudeStatusId;
}

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getLanguageId() + ", " +
this.getSolicitudeStatusId() + "]";
}
}
