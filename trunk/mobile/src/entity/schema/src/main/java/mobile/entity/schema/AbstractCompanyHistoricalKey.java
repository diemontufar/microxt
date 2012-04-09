package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCompanyHistoricalKey extends AbstractEntityKey implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	@Column(name = "COMPANY_ID", unique = true, nullable = false, length = 4)
	private String companyId;

	@Column(name = "EXPIRED", unique = true, nullable = false)
	Timestamp expired;

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Timestamp getExpired() {
		return this.expired;
	}

	public void setExpired(Timestamp pExpired) {
		this.expired = pExpired;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
