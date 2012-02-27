package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the RESPONSE database table. Values of responses
 */
@Entity
@Table(name = "RESPONSE")
public class Response extends AbstractEntity implements Multicompany, Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResponsePk pk;

	/**
	 * Description of response
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Response() {
	}

	public Response(ResponsePk pk) {
		this.pk = pk;
	}

	public Response(ResponsePk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public ResponsePk getPk() {
		return this.pk;
	}

	public void setPk(ResponsePk pk) {
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
		this.pk = (ResponsePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Response copy = (Response) super.clone();
		copy.setPk((ResponsePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "RESPONSE:[" + this.getPk().toString() + ", " + this.getDescription() + "]";
	}
}
