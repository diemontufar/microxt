package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
 * The persistent class for the LANGUAGE database table. Values of languages
 */
@Entity
@Table(name = "LANGUAGE")
public class Language extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Language Id
	 */
	@Id
	@Column(name = "LANGUAGE_ID", nullable = false)
	private String languageId;

	/**
	 * Name of language
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public Language() {
	}

	public Language(String languageId) {
		this.languageId = languageId;
	}

	public Language(String languageId, String name) {
		this.languageId = languageId;
		this.name = name;
	}

	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getPk() {
		return this.languageId;
	}

	@Override
	public void setPk(Object pk) {
		this.languageId = (String) pk;
	}

	@Override
	public String toString() {
		return "LANGUAGE:[" + this.getPk().toString() + ", " + this.getName() + "]";
	}
}
