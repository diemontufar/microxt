package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PRODUCT_ASESSOR_ID database table.
 */
@Entity
@Table(name = "PRODUCT_ASESSOR_ID")
public class ProductAsessorId extends AbstractEntityId implements
		GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductAsessorIdPk pk;

	public ProductAsessorId() {
	}

	public ProductAsessorId(ProductAsessorIdPk pk) {
		this.pk = pk;
	}

	public ProductAsessorIdPk getPk() {
		return this.pk;
	}

	public void setPk(ProductAsessorIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProductAsessorIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProductAsessorId copy = (ProductAsessorId) super.clone();
		copy.setPk((ProductAsessorIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PRODUCT_ASESSOR_ID:[" + this.getPk().toString() + "]";
	}
}
