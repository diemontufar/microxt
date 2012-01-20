package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PARTNER_ID database table.
 */
@Entity
@Table(name = "PARTNER_ID")
public class PartnerId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartnerIdPk pk;

	public PartnerId() {
	}

	public PartnerId(PartnerIdPk pk) {
		this.pk = pk;
	}

	public PartnerIdPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PartnerIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PartnerId copy = (PartnerId) super.clone();
		copy.setPk((PartnerIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PARTNER_ID:[" + this.getPk().toString() + "]";
	}
}
