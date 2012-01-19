package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the RESPONSE database table.
 */
@Embeddable
public class ResponsePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Response Id
	 */
	@Column(name = "RESPONSE_ID", nullable = false)
	private String responseId;

	public ResponsePk() {
	}

	public ResponsePk(String responseId) {
		this.responseId = responseId;
	}

	public String getResponseId() {
		return this.responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getResponseId() + "]";
	}
}
