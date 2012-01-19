package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the RECOMMENDATION database table. Recommendation of
 * microcredit
 */
@Entity
@Table(name = "RECOMMENDATION")
public class Recommendation extends AbstractHistoricalLocking implements Multicompany, Multilanguage, Historical,
		OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecommendationPk pk;

	/**
	 * Comments about documents
	 */
	@Column(name = "DOCUMENTS", nullable = false)
	private String documents;

	/**
	 * Comments about economic unit
	 */
	@Column(name = "ECONOMIC_UNIT", nullable = false)
	private String economicUnit;

	/**
	 * Comments about family unit
	 */
	@Column(name = "FAMILY_UNIT", nullable = false)
	private String familyUnit;

	/**
	 * Comments about payment morale
	 */
	@Column(name = "PAYMENT_MORALE", nullable = false)
	private String paymentMorale;

	/**
	 * Comments about credit history
	 */
	@Column(name = "CREDIT_HISTORY", nullable = false)
	private String creditHistory;

	/**
	 * Proposal
	 */
	@Column(name = "PROPOSAL", nullable = false)
	private String proposal;

	public Recommendation() {
	}

	public Recommendation(RecommendationPk pk) {
		this.pk = pk;
	}

	public Recommendation(RecommendationPk pk, String documents, String economicUnit, String familyUnit,
			String paymentMorale, String creditHistory, String proposal) {
		this.pk = pk;
		this.documents = documents;
		this.economicUnit = economicUnit;
		this.familyUnit = familyUnit;
		this.paymentMorale = paymentMorale;
		this.creditHistory = creditHistory;
		this.proposal = proposal;
	}

	public RecommendationPk getPk() {
		return this.pk;
	}

	public void setPk(RecommendationPk pk) {
		this.pk = pk;
	}

	public String getDocuments() {
		return this.documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	public String getEconomicUnit() {
		return this.economicUnit;
	}

	public void setEconomicUnit(String economicUnit) {
		this.economicUnit = economicUnit;
	}

	public String getFamilyUnit() {
		return this.familyUnit;
	}

	public void setFamilyUnit(String familyUnit) {
		this.familyUnit = familyUnit;
	}

	public String getPaymentMorale() {
		return this.paymentMorale;
	}

	public void setPaymentMorale(String paymentMorale) {
		this.paymentMorale = paymentMorale;
	}

	public String getCreditHistory() {
		return this.creditHistory;
	}

	public void setCreditHistory(String creditHistory) {
		this.creditHistory = creditHistory;
	}

	public String getProposal() {
		return this.proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (RecommendationPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Recommendation copy = (Recommendation) super.clone();
		copy.setPk((RecommendationPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "RECOMMENDATION:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getDocuments()
				+ ", " + this.getEconomicUnit() + ", " + this.getFamilyUnit() + ", " + this.getPaymentMorale() + ", "
				+ this.getCreditHistory() + ", " + this.getProposal() + ", " + this.getVersion() + "]";
	}
}
