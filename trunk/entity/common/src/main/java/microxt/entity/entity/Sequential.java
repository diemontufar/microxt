package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SEQUENTIAL database table.
 * 
 */
@javax.persistence.Entity
@Table(name="SEQUENTIAL")
public class Sequential implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SequentialPK id;

	@Column(name="SEQUENTIAL_VALUE", nullable=false, precision=10)
	private BigDecimal sequentialValue;

	//uni-directional many-to-one association to Company
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COMPANY_ID", nullable=false, insertable=false, updatable=false)
	private Company company;

    public Sequential() {
    }

	public SequentialPK getId() {
		return this.id;
	}

	public void setId(SequentialPK id) {
		this.id = id;
	}
	
	public BigDecimal getSequentialValue() {
		return this.sequentialValue;
	}

	public void setSequentialValue(BigDecimal sequentialValue) {
		this.sequentialValue = sequentialValue;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}