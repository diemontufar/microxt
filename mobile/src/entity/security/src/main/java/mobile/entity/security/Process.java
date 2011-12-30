package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the PROCESS database table. Values of processes
 */
@Entity
@Table(name = "PROCESS")
public class Process extends AbstractHistorical implements Multicompany,
		Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProcessPk pk;

	/**
	 * Name of process
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * Enable
	 */
	@Column(name = "ENABLE", nullable = false)
	private Boolean enable;

	/**
	 * Show in app menu
	 */
	@Column(name = "MENU", nullable = false)
	private Boolean menu;

	/**
	 * URL
	 */
	@Column(name = "URL", nullable = false)
	private String url;

	/**
	 * Datafile Id
	 */
	@Column(name = "DATAFILE_ID", nullable = true)
	private Long datafileId;

	public Process() {
	}

	public Process(ProcessPk pk) {
		this.pk = pk;
	}

	public Process(ProcessPk pk, String name, Boolean enable, Boolean menu,
			String url) {
		this.pk = pk;
		this.name = name;
		this.enable = enable;
		this.menu = menu;
		this.url = url;
	}

	public ProcessPk getPk() {
		return this.pk;
	}

	public void setPk(ProcessPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getMenu() {
		return this.menu;
	}

	public void setMenu(Boolean menu) {
		this.menu = menu;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDatafileId() {
		return this.datafileId;
	}

	public void setDatafileId(Long datafileId) {
		this.datafileId = datafileId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProcessPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Process copy = (Process) super.clone();
		copy.setPk((ProcessPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PROCESS:[" + this.getPk().toString() + ", " + this.getCreated()
				+ ", " + this.getName() + ", " + this.getEnable() + ", "
				+ this.getMenu() + ", " + this.getUrl() + ", "
				+ this.getDatafileId() + "]";
	}
}
