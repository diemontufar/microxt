package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the RESPONSE_ID database table.
*/
@Entity
@Table(name="RESPONSE_ID")
public class ResponseId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Response Id
*/
@Id
@Column(name="RESPONSE_ID", nullable=false)
private String responseId;

public ResponseId() {
}
public ResponseId(String responseId) {
this.responseId = responseId;
}
public String getResponseId() {
return this.responseId;
}
public void setResponseId(String responseId) {
this.responseId = responseId;
}

@Override
public Object getPk() {
return this.responseId;
}

@Override
public void setPk(Object pk) {
this.responseId=(String) pk;
}

@Override
public String toString() {
return "RESPONSE_ID:[" +
this.getPk().toString() + "]";
}
}
