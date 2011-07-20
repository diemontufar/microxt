package microxt.entity.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractHistoricalAndLanguageAndCompanyKey extends AbstractMultilanguageAndCompanyKey
		implements HistoricalKey{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "EXPIRED", unique = true, nullable = false)
    private Timestamp expired;
	
	public Timestamp getExpired(){
		return this.expired;
	}
	
	public void setExpired(Timestamp expired){
		this.expired = expired;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		AbstractHistoricalAndLanguageAndCompanyKey castOther = 
			(AbstractHistoricalAndLanguageAndCompanyKey)other;
		return 
			this.expired.equals(castOther.getExpired())
			&& super.equals(other);
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.expired.hashCode(); 
		hash = hash * prime + super.hashCode();
		return hash;
	}


}
