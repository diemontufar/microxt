package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the QUOTA_TYPE_ID database table.
 */
@Entity
@Table(name = "QUOTA_TYPE_ID")
public class QuotaTypeId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Fee type
	 */
	@Id
	@Column(name = "QUOTA_TYPE_ID", nullable = false)
	private String quotaTypeId;

	public QuotaTypeId() {
	}

	public QuotaTypeId(String quotaTypeId) {
		this.quotaTypeId = quotaTypeId;
	}

	public String getQuotaTypeId() {
		return this.quotaTypeId;
	}

	public void setQuotaTypeId(String quotaTypeId) {
		this.quotaTypeId = quotaTypeId;
	}

	@Override
	public Object getPk() {
		return this.quotaTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.quotaTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "QUOTA_TYPE_ID:[" + this.getPk().toString() + "]";
	}
}
