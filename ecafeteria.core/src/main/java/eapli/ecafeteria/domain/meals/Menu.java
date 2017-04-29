/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.range.TimePeriod;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author zero_
 */
@Entity
public class Menu implements AggregateRoot<String>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String name;
    
    @Temporal(TemporalType.DATE)
    private Calendar beginningDate;
    @Temporal(TemporalType.DATE)
    private Calendar endingDate;
    
    private TimePeriod timePeriod;
    private boolean isPublished;

    protected Menu() {
        // ORM
    }

    public Menu(String name, Calendar beginningDate, Calendar endingDate) {
    	parameterValidation(name, beginningDate, endingDate);

        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.timePeriod = new TimePeriod(beginningDate, endingDate);
        this.isPublished = false;
    }
    
    @Override
    public String id() {
        return this.name;
    }

    @Override
    public boolean sameAs(Object other) {
        final Menu menu = (Menu) other;
        return this.equals(menu) && name().equals(menu.name()) && isPublished() == menu.isPublished();
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.name);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }

        final Menu other = (Menu) o;
        return id().equals(other.id());
    }
    
    private boolean parameterValidation(String name, Calendar beginningDate, Calendar endingDate){
    	if (name == null || beginningDate == null || endingDate == null) {
            throw new IllegalStateException();
        }
    	if(beginningDate.after(endingDate)){
    		throw new IllegalArgumentException("begin must be lower than end");
    	}
    	return true;
    }
    
    public void update(String newName, Calendar newBeginningDate, Calendar newEndingDate){
    	parameterValidation(newName, newBeginningDate, newEndingDate);
    	
    	this.name = newName;
        this.beginningDate = newBeginningDate;
        this.endingDate = newEndingDate;
        this.timePeriod = new TimePeriod(newBeginningDate, newEndingDate);
    }

    public String name() {
        return name;
    }
    
    public Calendar beginningDate(){
    	return this.beginningDate;
    }
    
    public Calendar endingDate(){
    	return this.endingDate;
    }

    public boolean isPublished() {
        return this.isPublished;
    }

    public void publishMenu() {
        this.isPublished = true;
    }
    
    public boolean isInBetween(Calendar date) {
        return this.timePeriod.includes(date);
    }


}
