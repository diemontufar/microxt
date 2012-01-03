package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
* The persistent class for the FEE_TYPE database table.
* Fee type
*/
@Entity
@Table(name="FEE_TYPE")
public class FeeType extends AbstractEntity implements Multicompany, Multilanguage{
private static final long serialVersionUID = 1L;

@EmbeddedId
private FeeTypePk pk;

/**
* Description
*/
@Column(name="DESCRIPTION", nullable=false)
private String description;

public FeeType() {
}
public FeeType(FeeTypePk pk) {
this.pk = pk;
}
public FeeType(FeeTypePk pk,String description) {
this.pk = pk;
this.description = description;
}
public FeeTypePk getPk() {
return this.pk;
}
public void setPk(FeeTypePk pk) {
this.pk = pk;
}
public String getDescription() {
return this.description;
}
public void setDescription(String description) {
this.description = description;
}

@Override
public void setPk(Object pk) {
this.pk=(FeeTypePk) pk;
}

@Override
public Object clone() throws CloneNotSupportedException {
FeeType copy = (FeeType) super.clone();
copy.setPk((FeeTypePk) this.pk.clone());
return copy;
}

@Override
public String toString() {
return "FEE_TYPE:[" +
this.getPk().toString() + ", " +
this.getDescription() + "]";
}
}
