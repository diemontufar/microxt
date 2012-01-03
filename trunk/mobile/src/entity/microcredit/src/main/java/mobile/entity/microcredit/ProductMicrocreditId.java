package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
* The persistent class for the PRODUCT_MICROCREDIT_ID database table.
*/
@Entity
@Table(name="PRODUCT_MICROCREDIT_ID")
public class ProductMicrocreditId extends AbstractEntityId implements GeneralEntityId{
private static final long serialVersionUID = 1L;

/**
* Product id
*/
@Id
@Column(name="PRODUCT_ID", nullable=false)
private String productId;

public ProductMicrocreditId() {
}
public ProductMicrocreditId(String productId) {
this.productId = productId;
}
public String getProductId() {
return this.productId;
}
public void setProductId(String productId) {
this.productId = productId;
}

@Override
public Object getPk() {
return this.productId;
}

@Override
public void setPk(Object pk) {
this.productId=(String) pk;
}

@Override
public String toString() {
return "PRODUCT_MICROCREDIT_ID:[" +
this.getPk().toString() + "]";
}
}
