package mobile.entity.schema;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCompanyLanguageKey extends AbstractEntityKey
		implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	@Column(name = "COMPANY_ID", unique = true, nullable = false, length = 4)
	private String companyId;

	@Column(name = "LANGUAGE_ID", unique = true, nullable = false, length = 2)
	private String languageId;

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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		AbstractCompanyLanguageKey castOther = (AbstractCompanyLanguageKey) other;
		return this.getLanguageId().equals(castOther.getLanguageId())
				&& this.getCompanyId() == castOther.getCompanyId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.getCompanyId().hashCode();
		hash = hash * prime + this.getLanguageId().hashCode();
		hash = hash * prime + super.hashCode();
		return hash;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}