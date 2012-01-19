package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the PERSON_DOCUMENT database table. Values of person
 * documents
 */
@Entity
@Table(name = "PERSON_DOCUMENT")
public class PersonDocument extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonDocumentPk pk;

	/**
	 * Document type Id
	 */
	@Column(name = "DOCUMENT_TYPE_ID", nullable = false)
	private String documentTypeId;

	/**
	 * Datafile Id
	 */
	@Column(name = "DATAFILE_ID", nullable = false)
	private Long datafileId;

	public PersonDocument() {
	}

	public PersonDocument(PersonDocumentPk pk) {
		this.pk = pk;
	}

	public PersonDocument(PersonDocumentPk pk, String documentTypeId, Long datafileId) {
		this.pk = pk;
		this.documentTypeId = documentTypeId;
		this.datafileId = datafileId;
	}

	public PersonDocumentPk getPk() {
		return this.pk;
	}

	public void setPk(PersonDocumentPk pk) {
		this.pk = pk;
	}

	public String getDocumentTypeId() {
		return this.documentTypeId;
	}

	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public Long getDatafileId() {
		return this.datafileId;
	}

	public void setDatafileId(Long datafileId) {
		this.datafileId = datafileId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PersonDocumentPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PersonDocument copy = (PersonDocument) super.clone();
		copy.setPk((PersonDocumentPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PERSON_DOCUMENT:[" + this.getPk().toString() + ", " + this.getCreated() + ", "
				+ this.getDocumentTypeId() + ", " + this.getDatafileId() + ", " + this.getVersion() + "]";
	}
}
