package mobile.entity.schema;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLanguageKey extends AbstractEntityKey implements Cloneable, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	@Column(name = "LANGUAGE_ID", unique = true, nullable = false, length = 2)
	String languageId;

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
		/*
		 * if (!(other instanceof UserstatusidPK)) { return false; }
		 */
		AbstractLanguageKey castOther = (AbstractLanguageKey) other;
		return this.languageId.equals(castOther.getLanguageId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.languageId.hashCode();
		return hash;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
