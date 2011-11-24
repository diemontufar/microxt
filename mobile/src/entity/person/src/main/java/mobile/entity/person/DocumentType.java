package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the DOCUMENT_TYPE database table. Values of document
 * types
 */
@Entity
@Table(name = "DOCUMENT_TYPE")
public class DocumentType extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DocumentTypePk pk;

	/**
	 * Name of document type
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public DocumentType() {
	}

	public DocumentType(DocumentTypePk pk) {
		this.pk = pk;
	}

	public DocumentType(DocumentTypePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public DocumentTypePk getPk() {
		return this.pk;
	}

	public void setPk(DocumentTypePk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (DocumentTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DocumentType copy = (DocumentType) super.clone();

		copy.setPk((DocumentTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "DOCUMENT_TYPE:[" + this.getPk().toString() + ", "
				+ this.getName() + "]";
	}
}
