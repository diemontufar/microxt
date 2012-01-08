package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the GEOGRAPHIC_ZONE database table. Geographic zones
 */
@Entity
@Table(name = "GEOGRAPHIC_ZONE")
public class GeographicZone extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GeographicZonePk pk;

	/**
	 * Description of the zone
	 */
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	/**
	 * Type of coordinate: Point, Route, Polygon
	 */
	@Column(name = "COORDINATE_TYPE", nullable = false)
	private String coordinateType;

	/**
	 * Point of coordinate 1,1
	 */
	@Column(name = "P11", nullable = false)
	private String p11;

	/**
	 * Point of coordinate 1,2
	 */
	@Column(name = "P12", nullable = false)
	private String p12;

	/**
	 * Point of coordinate 2,1
	 */
	@Column(name = "P21", nullable = true)
	private String p21;

	/**
	 * Point of coordinate 2,2
	 */
	@Column(name = "P22", nullable = true)
	private String p22;

	/**
	 * Point of coordinate 3,1
	 */
	@Column(name = "P31", nullable = true)
	private String p31;

	/**
	 * Point of coordinate 3,2
	 */
	@Column(name = "P32", nullable = true)
	private String p32;

	/**
	 * Point of coordinate 4,1
	 */
	@Column(name = "P41", nullable = true)
	private String p41;

	/**
	 * Point of coordinate 4,2
	 */
	@Column(name = "P42", nullable = true)
	private String p42;

	public GeographicZone() {
	}

	public GeographicZone(GeographicZonePk pk) {
		this.pk = pk;
	}

	public GeographicZone(GeographicZonePk pk, String coordinateType,
			String p11, String p12) {
		this.pk = pk;
		this.coordinateType = coordinateType;
		this.p11 = p11;
		this.p12 = p12;
	}

	public GeographicZonePk getPk() {
		return this.pk;
	}

	public void setPk(GeographicZonePk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoordinateType() {
		return this.coordinateType;
	}

	public void setCoordinateType(String coordinateType) {
		this.coordinateType = coordinateType;
	}

	public String getP11() {
		return this.p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}

	public String getP12() {
		return this.p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}

	public String getP21() {
		return this.p21;
	}

	public void setP21(String p21) {
		this.p21 = p21;
	}

	public String getP22() {
		return this.p22;
	}

	public void setP22(String p22) {
		this.p22 = p22;
	}

	public String getP31() {
		return this.p31;
	}

	public void setP31(String p31) {
		this.p31 = p31;
	}

	public String getP32() {
		return this.p32;
	}

	public void setP32(String p32) {
		this.p32 = p32;
	}

	public String getP41() {
		return this.p41;
	}

	public void setP41(String p41) {
		this.p41 = p41;
	}

	public String getP42() {
		return this.p42;
	}

	public void setP42(String p42) {
		this.p42 = p42;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (GeographicZonePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		GeographicZone copy = (GeographicZone) super.clone();
		copy.setPk((GeographicZonePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "GEOGRAPHIC_ZONE:[" + this.getPk().toString() + ", "
				+ this.getDescription() + ", " + this.getCoordinateType()
				+ ", " + this.getP11() + ", " + this.getP12() + ", "
				+ this.getP21() + ", " + this.getP22() + ", " + this.getP31()
				+ ", " + this.getP32() + ", " + this.getP41() + ", "
				+ this.getP42() + "]";
	}
}
