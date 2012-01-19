package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the PRODUCT_ASESSOR_ID database table.
 */
@Embeddable
public class ProductAsessorIdPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Assessor
	 */
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	/**
	 * Product id
	 */
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	public ProductAsessorIdPk() {
	}

	public ProductAsessorIdPk(String asessorId, String productId) {
		this.asessorId = asessorId;
		this.productId = productId;
	}

	public String getAsessorId() {
		return this.asessorId;
	}

	public void setAsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "[" + this.getAsessorId() + ", " + this.getProductId() + "]";
	}
}
