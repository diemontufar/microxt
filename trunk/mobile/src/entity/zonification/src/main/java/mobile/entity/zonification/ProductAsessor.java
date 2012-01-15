package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the PRODUCT_ASESSOR database table. Products per
 * Asessor
 */
@Entity
@Table(name = "PRODUCT_ASESSOR")
public class ProductAsessor extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductAsessorPk pk;

	/**
	 * Observations
	 */
	@Column(name = "OBSERVATIONS", nullable = true)
	private String observations;

	public ProductAsessor() {
	}

	public ProductAsessor(ProductAsessorPk pk) {
		this.pk = pk;
	}

	public ProductAsessorPk getPk() {
		return this.pk;
	}

	public void setPk(ProductAsessorPk pk) {
		this.pk = pk;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProductAsessorPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProductAsessor copy = (ProductAsessor) super.clone();
		copy.setPk((ProductAsessorPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PRODUCT_ASESSOR:[" + this.getPk().toString() + ", "
				+ this.getObservations() + "]";
	}
}
