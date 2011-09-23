package mobile.entity.common;


import javax.persistence.*;

import mobile.entity.schema.AbstractOptimisticLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.OptimisticLocking;


/**
 * The persistent class for the SEQUENTIAL database table.
 * Values of sequences
 */
@Entity
@Table(name = "SEQUENTIAL")
public class Sequential extends AbstractOptimisticLocking implements Multicompany, OptimisticLocking {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SequentialPk pk;

    /**
     * Value of sequential
     */
    @Column(name = "SEQUENTIAL_VALUE", nullable = false)
    private Long sequentialValue;

    public Sequential() {}

    public Sequential(SequentialPk pk) {
        this.pk = pk;
    }

    public Sequential(SequentialPk pk, Long sequentialValue) {
        this.pk = pk;
        this.sequentialValue = sequentialValue;
    }

    public SequentialPk getPk() {
        return this.pk;
    }

    public void setPk(SequentialPk pk) {
        this.pk = pk;
    }

    public Long getSequentialValue() {
        return this.sequentialValue;
    }

    public void setSequentialValue(Long sequentialValue) {
        this.sequentialValue = sequentialValue;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (SequentialPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Sequential copy = (Sequential) super.clone();

        copy.setPk((SequentialPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "SEQUENTIAL:[" + this.getPk().toString() + ", "
                + this.getSequentialValue() + ", " + this.getVersion() + "]";
    }
}
