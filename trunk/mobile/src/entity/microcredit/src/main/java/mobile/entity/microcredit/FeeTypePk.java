package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
* The primary key class for the FEE_TYPE database table.
*/
@Embeddable
public class FeeTypePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey{
private static final long serialVersionUID = 1L;

/**
* Fee type
*/
@Column(name="FEE_TYPE_ID", nullable=false)
private String feeTypeId;

public FeeTypePk() {
}
public FeeTypePk(String feeTypeId) {
this.feeTypeId = feeTypeId;
}
public String getFeeTypeId() {
return this.feeTypeId;
}
public void setFeeTypeId(String feeTypeId) {
this.feeTypeId = feeTypeId;
}

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getLanguageId() + ", " +
this.getFeeTypeId() + "]";
}
}
