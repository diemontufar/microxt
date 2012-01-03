package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the CURRENCY_ID database table.
*/
@Entity
@Table(name="CURRENCY_ID")
public class CurrencyId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Currency
*/
@Id
@Column(name="CURRENCY_ID", nullable=false)
private String currencyId;

public CurrencyId() {
}
public CurrencyId(String currencyId) {
this.currencyId = currencyId;
}
public String getCurrencyId() {
return this.currencyId;
}
public void setCurrencyId(String currencyId) {
this.currencyId = currencyId;
}

@Override
public Object getPk() {
return this.currencyId;
}

@Override
public void setPk(Object pk) {
this.currencyId=(String) pk;
}

@Override
public String toString() {
return "CURRENCY_ID:[" +
this.getPk().toString() + "]";
}
}
