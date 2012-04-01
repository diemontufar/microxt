package mobile.entity.common;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
* The persistent class for the DATABASE_TYPE database table.
* Values of database types
*/
@Entity
@Table(name="DATABASE_TYPE")
public class DatabaseType extends AbstractEntity implements GeneralEntity{
private static final long serialVersionUID = 1L;

@EmbeddedId
private DatabaseTypePk pk;

/**
* Data type of database
*/
@Column(name="DATABASE_TYPE", nullable=false)
private String databaseType;

public DatabaseType() {
}
public DatabaseType(DatabaseTypePk pk) {
this.pk = pk;
}
public DatabaseType(DatabaseTypePk pk,String databaseType) {
this.pk = pk;
this.databaseType = databaseType;
}
public DatabaseTypePk getPk() {
return this.pk;
}
public void setPk(DatabaseTypePk pk) {
this.pk = pk;
}
public String getDatabaseType() {
return this.databaseType;
}
public void setDatabaseType(String databaseType) {
this.databaseType = databaseType;
}

@Override
public void setPk(Object pk) {
this.pk=(DatabaseTypePk) pk;
}

@Override
public Object clone() throws CloneNotSupportedException {
DatabaseType copy = (DatabaseType) super.clone();
copy.setPk((DatabaseTypePk) this.pk.clone());
return copy;
}

@Override
public String toString() {
return "DATABASE_TYPE:[" +
this.getPk().toString() + ", " +
this.getDatabaseType() + "]";
}
}
