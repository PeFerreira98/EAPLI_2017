/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.rating;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author Pedro Pereira
 */
@Entity
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    
    
    private int rank;   
    @ManyToOne
    private CafeteriaUser cafeteriaUser;
    @ManyToOne
    private Meal meal;
    @ManyToOne
    private Comment comment;
    
    protected Ranking(){
        // ORM
    }

    public Ranking(int rank, CafeteriaUser cafeteriaUser, Meal meal, Comment comment){
        this.rank = rank;
        this.cafeteriaUser = cafeteriaUser;
        this.meal = meal;
        this.comment = comment;
    }
    
    public int readRank(){
        return this.rank;
    }

    public CafeteriaUser cafeteriaUser(){
        return this.cafeteriaUser;
    }
    
    public Meal meal(){
        return this.meal;
    }
    
    public Comment comment(){
        return this.comment;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ranking)) {
            return false;
        }
        Ranking other = (Ranking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
