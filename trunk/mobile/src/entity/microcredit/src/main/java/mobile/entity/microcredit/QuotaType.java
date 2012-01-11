package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the QUOTA_TYPE database table. Quota type
 */
@Entity
@Table(name = "QUOTA_TYPE")
public class QuotaType extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuotaTypePk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public QuotaType() {
	}

	public QuotaType(QuotaTypePk pk) {
		this.pk = pk;
	}

	public QuotaType(QuotaTypePk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public QuotaTypePk getPk() {
		return this.pk;
	}

	public void setPk(QuotaTypePk pk) {
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
		this.pk = (QuotaTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		QuotaType copy = (QuotaType) super.clone();
		copy.setPk((QuotaTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "QUOTA_TYPE:[" + this.getPk().toString() + ", "
				+ this.getDescription() + "]";
	}
}
