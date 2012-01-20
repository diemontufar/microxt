package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PRODUCT_MICROCREDIT database table.
 */
@Embeddable
public class ProductMicrocreditPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey,
		MultilanguageKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Product id
	 */
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	public ProductMicrocreditPk() {
	}

	public ProductMicrocreditPk(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getProductId() + "]";
	}
}
