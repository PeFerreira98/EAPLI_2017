/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Pedro Tedim
 */
@Entity
public class Batch implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String batchCode;
    private String description;

    protected Batch() {
        // for ORM
    }

    public Batch(String batchCode, String description) {
        if (Strings.isNullOrEmpty(batchCode)) {
            throw new IllegalStateException();
        }
        this.batchCode = batchCode;
        this.description = description;
    }

    public String description() {
        return this.description;
    }

    public void changeDescriptionTo(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id() != null ? id().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.id() == null && other.id() != null) || (this.id() != null && !this.id().equals(other.id()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.kitchen.Batch[ id=" + id() + " ]";
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(String batchCode) {
        return batchCode.equalsIgnoreCase(this.batchCode);
    }

    @Override
    public String id() {
        return this.batchCode;
    }

}
