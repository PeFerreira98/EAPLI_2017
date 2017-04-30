/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menus;

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
    private Calendar beginDate;
    @Temporal(TemporalType.DATE)
    private Calendar endDate;
    
    private TimePeriod timePeriod;
    private boolean isPublished;

    protected Menu() {
        // ORM
    }

    public Menu(String name, Calendar beginDate, Calendar endDate) {
    	parameterValidation(name, beginDate, endDate);

        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.timePeriod = new TimePeriod(beginDate, endDate);
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
    
    private boolean parameterValidation(String name, Calendar beginDate, Calendar endDate){
    	if (name == null || beginDate == null || endDate == null) {
            throw new IllegalStateException();
        }
    	if(beginDate.after(endDate)){
    		throw new IllegalArgumentException("begin must be lower than end");
    	}
    	return true;
    }
    
    public void update(String newName, Calendar newBeginDate, Calendar newEndDate){
    	parameterValidation(newName, newBeginDate, newEndDate);
    	
    	this.name = newName;
        this.beginDate = newBeginDate;
        this.endDate = newEndDate;
        this.timePeriod = new TimePeriod(newBeginDate, newEndDate);
    }

    public String name() {
        return name;
    }
    
    public Calendar beginningDate(){
    	return this.beginDate;
    }
    
    public Calendar endingDate(){
    	return this.endDate;
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
