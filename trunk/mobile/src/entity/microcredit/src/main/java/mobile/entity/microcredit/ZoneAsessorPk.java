package mobile.entity.microcredit;

import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the ZONE_ASESSOR database table.
 */
@Embeddable
public class ZoneAsessorPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Geographic zone id
	 */
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private Integer geographicZoneId;

	public ZoneAsessorPk() {
	}

	public ZoneAsessorPk(String userId, Integer geographicZoneId) {
		this.userId = userId;
		this.geographicZoneId = geographicZoneId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(Integer geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public String toString() {
		return "[" + this.getUserId() + ", " + this.getGeographicZoneId() + "]";
	}

    /**
     * The persistent class for the PRODUCT_MICROCREDIT database table. Products of
     * microcredit
     */
    @Entity
    @Table(name = "PRODUCT_MICROCREDIT")
    public class ProductMicrocredit extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
        private static final long serialVersionUID = 1L;

        @EmbeddedId
        private mobile.entity.microcredit.ProductMicrocreditPk pk;

        /**
         * Description of product
         */
        @Column(name = "DESCRIPTION", nullable = false)
        private String description;

        /**
         * Currency id
         */
        @Column(name = "CURRENCY_ID", nullable = false)
        private String currencyId;

        /**
         * Minimun amount
         */
        @Column(name = "MIN_AMOUNT", nullable = false)
        private BigDecimal minAmount;

        /**
         * Maximun amount
         */
        @Column(name = "MAX_AMOUNT", nullable = false)
        private BigDecimal maxAmount;

        /**
         * Minimun period
         */
        @Column(name = "MIN_PERIOD", nullable = false)
        private Long minPeriod;

        /**
         * Maximun period
         */
        @Column(name = "MAX_PERIOD", nullable = false)
        private Long maxPeriod;

        /**
         * Rate
         */
        @Column(name = "RATE", nullable = false)
        private BigDecimal rate;

        public ProductMicrocredit() {
        }

        public ProductMicrocredit(mobile.entity.microcredit.ProductMicrocreditPk pk) {
            this.pk = pk;
        }

        public ProductMicrocredit(mobile.entity.microcredit.ProductMicrocreditPk pk, String description,
                                  String currencyId, BigDecimal minAmount, BigDecimal maxAmount, Long minPeriod,
                                  Long maxPeriod, BigDecimal rate) {
            this.pk = pk;
            this.description = description;
            this.currencyId = currencyId;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
            this.minPeriod = minPeriod;
            this.maxPeriod = maxPeriod;
            this.rate = rate;
        }

        public mobile.entity.microcredit.ProductMicrocreditPk getPk() {
            return this.pk;
        }

        public void setPk(mobile.entity.microcredit.ProductMicrocreditPk pk) {
            this.pk = pk;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCurrencyId() {
            return this.currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public BigDecimal getMinAmount() {
            return this.minAmount;
        }

        public void setMinAmount(BigDecimal minAmount) {
            this.minAmount = minAmount;
        }

        public BigDecimal getMaxAmount() {
            return this.maxAmount;
        }

        public void setMaxAmount(BigDecimal maxAmount) {
            this.maxAmount = maxAmount;
        }

        public Long getMinPeriod() {
            return this.minPeriod;
        }

        public void setMinPeriod(Long minPeriod) {
            this.minPeriod = minPeriod;
        }

        public Long getMaxPeriod() {
            return this.maxPeriod;
        }

        public void setMaxPeriod(Long maxPeriod) {
            this.maxPeriod = maxPeriod;
        }

        public BigDecimal getRate() {
            return this.rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        @Override
        public void setPk(Object pk) {
            this.pk = (mobile.entity.microcredit.ProductMicrocreditPk)pk;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            mobile.entity.microcredit.ProductMicrocredit copy =
                (mobile.entity.microcredit.ProductMicrocredit)super.clone();
            copy.setPk((mobile.entity.microcredit.ProductMicrocreditPk)this.pk.clone());
            return copy;
        }

        @Override
        public String toString() {
            return "PRODUCT_MICROCREDIT:[" + this.getPk().toString() + ", " + this.getCreated() + ", " +
                this.getDescription() + ", " + this.getCurrencyId() + ", " + this.getMinAmount() + ", " +
                this.getMaxAmount() + ", " + this.getMinPeriod() + ", " + this.getMaxPeriod() + ", " + this.getRate() +
                "]";
        }
    }

    /**
     * The primary key class for the PRODUCT_MICROCREDIT database table.
     */
    @Embeddable
    public class ProductMicrocreditPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey,
                                                                                              MultilanguageKey,
                                                                                              HistoricalKey {
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
            return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", " +
                this.getProductId() + "]";
        }
    }
}
