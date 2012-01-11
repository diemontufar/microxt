package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the SOLICITUDE_STATUS database table. Solicitude
 * status
 */
@Entity
@Table(name = "SOLICITUDE_STATUS")
public class SolicitudeStatus extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudeStatusPk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public SolicitudeStatus() {
	}

	public SolicitudeStatus(SolicitudeStatusPk pk) {
		this.pk = pk;
	}

	public SolicitudeStatus(SolicitudeStatusPk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public SolicitudeStatusPk getPk() {
		return this.pk;
	}

	public void setPk(SolicitudeStatusPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (SolicitudeStatusPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		SolicitudeStatus copy = (SolicitudeStatus) super.clone();
		copy.setPk((SolicitudeStatusPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "SOLICITUDE_STATUS:[" + this.getPk().toString() + ", "
				+ this.getDescription() + "]";
	}
}
