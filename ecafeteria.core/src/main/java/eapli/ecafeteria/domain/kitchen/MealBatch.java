/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 *
 * @author Pedro Tedim
 */
@Entity 
public class MealBatch implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue
//    private Long id;
    @Version
    private Long version;

    // business ID
    @EmbeddedId
    MealBatchKey mbk;

    protected MealBatch() {
        // for ORM
    }

    public MealBatch(Calendar mealDate, String matRef, String batchCode) {

        mbk = new MealBatchKey();
        
        mbk.mealDate = mealDate;
        mbk.matRef = matRef;
        mbk.batchCode = batchCode;
    }

    public Calendar date() {
        return mbk.mealDate;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof MealBatch)) {
//            return false;
//        }
//        MealBatch other = (MealBatch) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "eapli.ecafeteria.domain.kitchen.MealBatch[ id=" + id + " ]";
//    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
