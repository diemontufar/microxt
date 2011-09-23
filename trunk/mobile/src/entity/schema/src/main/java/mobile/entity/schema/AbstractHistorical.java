package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/* 
 * Clase abstracta que provee dos caracteristicas basicas de una 
 * entidad que maneja historia:
 * Una clave con campo expired
 * Un campo de fecha create 
 */
@MappedSuperclass
public abstract class AbstractHistorical implements Historical{
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", unique = true, nullable = false)
	Timestamp created;

    public Timestamp getCreated(){
    	return this.created;
    }

    public void setCreated(Timestamp pCreated){
    	this.created = pCreated;
    }
    
	public Object clone() throws CloneNotSupportedException{
    	return super.clone();
    }
}
