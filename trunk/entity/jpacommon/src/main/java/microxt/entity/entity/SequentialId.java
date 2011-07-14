package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SEQUENTIAL_ID database table.
 * 
 */
@javax.persistence.Entity
@Table(name="SEQUENTIAL_ID")
public class SequentialId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEQUENTIAL_ID", unique=true, nullable=false, length=30)
	private String sequentialId;

    public SequentialId() {
    }

	public String getSequentialId() {
		return this.sequentialId;
	}

	public void setSequentialId(String sequentialId) {
		this.sequentialId = sequentialId;
	}

}