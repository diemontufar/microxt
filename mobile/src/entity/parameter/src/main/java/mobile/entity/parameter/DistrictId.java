package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the DISTRICT_ID database table.
*/
@Entity
@Table(name="DISTRICT_ID")
public class DistrictId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

@EmbeddedId
private DistrictIdPk pk;

public DistrictId() {
}
public DistrictId(DistrictIdPk pk) {
this.pk = pk;
}
public DistrictIdPk getPk() {
return this.pk;
}
public void setPk(DistrictIdPk pk) {
this.pk = pk;
}

@Override
public void setPk(Object pk) {
this.pk=(DistrictIdPk) pk;
}

@Override
public Object clone() throws CloneNotSupportedException {
DistrictId copy = (DistrictId) super.clone();
copy.setPk((DistrictIdPk) this.pk.clone());
return copy;
}

@Override
public String toString() {
return "DISTRICT_ID:[" +
this.getPk().toString() + "]";
}
}
