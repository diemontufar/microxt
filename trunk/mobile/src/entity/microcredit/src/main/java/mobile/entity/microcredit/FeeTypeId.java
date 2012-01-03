package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the FEE_TYPE_ID database table.
*/
@Entity
@Table(name="FEE_TYPE_ID")
public class FeeTypeId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Fee type
*/
@Id
@Column(name="FEE_TYPE_ID", nullable=false)
private String feeTypeId;

public FeeTypeId() {
}
public FeeTypeId(String feeTypeId) {
this.feeTypeId = feeTypeId;
}
public String getFeeTypeId() {
return this.feeTypeId;
}
public void setFeeTypeId(String feeTypeId) {
this.feeTypeId = feeTypeId;
}

@Override
public Object getPk() {
return this.feeTypeId;
}

@Override
public void setPk(Object pk) {
this.feeTypeId=(String) pk;
}

@Override
public String toString() {
return "FEE_TYPE_ID:[" +
this.getPk().toString() + "]";
}
}
