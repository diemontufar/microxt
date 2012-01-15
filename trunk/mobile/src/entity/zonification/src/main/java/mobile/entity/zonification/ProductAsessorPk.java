package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the PRODUCT_ASESSOR database table.
 */
@Embeddable
public class ProductAsessorPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
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

	public ProductAsessorPk() {
	}

	public ProductAsessorPk(String asessorId, String productId) {
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
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getAsessorId() + ", " + this.getProductId() + "]";
	}
}
