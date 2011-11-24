package mobile.entity.parameter;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Historical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the DATAFILE database table. Values of datafiles
 */
@Entity
@Table(name = "DATAFILE")
public class Datafile extends AbstractHistoricalLocking implements
		Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatafilePk pk;

	/**
	 * Datafile type Id
	 */
	@Column(name = "DATAFILE_TYPE_ID", nullable = false)
	private String datafileTypeId;

	/**
	 * Binary path
	 */
	@Column(name = "BINARY_PATH", nullable = false)
	private String binaryPath;

	/**
	 * Binary bytes
	 */
	@Column(name = "BINARY_BYTES", nullable = true)
	private Long binaryBytes;

	/**
	 * Binary object
	 */
	@Column(name = "BINARY_OBJECT", nullable = true)
	private Blob binaryObject;

	/**
	 * Character data
	 */
	@Column(name = "CHARACTER_DATA", nullable = true)
	private Clob characterData;

	public Datafile() {
	}

	public Datafile(DatafilePk pk) {
		this.pk = pk;
	}

	public Datafile(DatafilePk pk, String datafileTypeId, String binaryPath) {
		this.pk = pk;
		this.datafileTypeId = datafileTypeId;
		this.binaryPath = binaryPath;
	}

	public DatafilePk getPk() {
		return this.pk;
	}

	public void setPk(DatafilePk pk) {
		this.pk = pk;
	}

	public String getDatafileTypeId() {
		return this.datafileTypeId;
	}

	public void setDatafileTypeId(String datafileTypeId) {
		this.datafileTypeId = datafileTypeId;
	}

	public String getBinaryPath() {
		return this.binaryPath;
	}

	public void setBinaryPath(String binaryPath) {
		this.binaryPath = binaryPath;
	}

	public Long getBinaryBytes() {
		return this.binaryBytes;
	}

	public void setBinaryBytes(Long binaryBytes) {
		this.binaryBytes = binaryBytes;
	}

	public Blob getBinaryObject() {
		return this.binaryObject;
	}

	public void setBinaryObject(Blob binaryObject) {
		this.binaryObject = binaryObject;
	}

	public Clob getCharacterData() {
		return this.characterData;
	}

	public void setCharacterData(Clob characterData) {
		this.characterData = characterData;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (DatafilePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Datafile copy = (Datafile) super.clone();

		copy.setPk((DatafilePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "DATAFILE:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getDatafileTypeId() + ", "
				+ this.getBinaryPath() + ", " + this.getBinaryBytes() + ", "
				+ this.getBinaryObject() + ", " + this.getCharacterData()
				+ ", " + this.getVersion() + "]";
	}
}
