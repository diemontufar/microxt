package mobile.entity.microcredit;

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
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Product id
	 */
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	public ProductAsessorPk() {
	}

	public ProductAsessorPk(String userId, String productId) {
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
				+ this.getUserId() + ", " + this.getProductId() + "]";
	}
}
