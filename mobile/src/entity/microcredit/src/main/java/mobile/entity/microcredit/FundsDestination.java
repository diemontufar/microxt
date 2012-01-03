package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
* The persistent class for the FUNDS_DESTINATION database table.
* Destination of funds
*/
@Entity
@Table(name="FUNDS_DESTINATION")
public class FundsDestination extends AbstractEntity implements Multicompany, Multilanguage{
private static final long serialVersionUID = 1L;

@EmbeddedId
private FundsDestinationPk pk;

/**
* Description
*/
@Column(name="DESCRIPTION", nullable=false)
private String description;

public FundsDestination() {
}
public FundsDestination(FundsDestinationPk pk) {
this.pk = pk;
}
public FundsDestination(FundsDestinationPk pk,String description) {
this.pk = pk;
this.description = description;
}
public FundsDestinationPk getPk() {
return this.pk;
}
public void setPk(FundsDestinationPk pk) {
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
this.pk=(FundsDestinationPk) pk;
}

@Override
public Object clone() throws CloneNotSupportedException {
FundsDestination copy = (FundsDestination) super.clone();
copy.setPk((FundsDestinationPk) this.pk.clone());
return copy;
}

@Override
public String toString() {
return "FUNDS_DESTINATION:[" +
this.getPk().toString() + ", " +
this.getDescription() + "]";
}
}
