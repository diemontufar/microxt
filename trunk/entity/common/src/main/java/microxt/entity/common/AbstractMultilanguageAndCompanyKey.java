package microxt.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractMultilanguageAndCompanyKey extends AbstractMulticompanyKey
		implements MultilanguageKey{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "LANGUAGE_ID", unique = true, nullable = false, length = 2)
	private String languageId;

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
		AbstractMultilanguageKey castOther = (AbstractMultilanguageKey) other;
		return this.languageId.equals(castOther.getLanguageId()) && super.equals(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.languageId.hashCode(); 
		hash = hash * prime + super.hashCode();
		return hash;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
