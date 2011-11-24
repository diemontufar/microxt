package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCompanyLanguageHistoricalKey extends
		AbstractEntityKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey {
	private static final long serialVersionUID = 1L;

	@Column(name = "COMPANY_ID", unique = true, nullable = false, length = 4)
	private String companyId;

	@Column(name = "LANGUAGE_ID", unique = true, nullable = false, length = 2)
	private String languageId;

	@Column(name = "EXPIRED", unique = true, nullable = false)
	private Timestamp expired;

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public Timestamp getExpired() {
		return this.expired;
	}

	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		AbstractCompanyLanguageHistoricalKey castOther = (AbstractCompanyLanguageHistoricalKey) other;
		return this.getCompanyId() == castOther.getCompanyId()
				&& this.getLanguageId() == castOther.getLanguageId()
				&& this.getExpired().equals(castOther.getExpired());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.getCompanyId().hashCode();
		hash = hash * prime + this.getLanguageId().hashCode();
		hash = hash * prime + this.getExpired().hashCode();
		hash = hash * prime + super.hashCode();
		return hash;
	}
}
