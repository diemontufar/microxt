package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the MICRO_ACCOUNT_QUOTA_ID database table.
 */
@Entity
@Table(name = "MICRO_ACCOUNT_QUOTA_ID")
public class MicroAccountQuotaId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MicroAccountQuotaIdPk pk;

	public MicroAccountQuotaId() {
	}

	public MicroAccountQuotaId(MicroAccountQuotaIdPk pk) {
		this.pk = pk;
	}

	public MicroAccountQuotaIdPk getPk() {
		return this.pk;
	}

	public void setPk(MicroAccountQuotaIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (MicroAccountQuotaIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MicroAccountQuotaId copy = (MicroAccountQuotaId) super.clone();
		copy.setPk((MicroAccountQuotaIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MICRO_ACCOUNT_QUOTA_ID:[" + this.getPk().toString() + "]";
	}
}
