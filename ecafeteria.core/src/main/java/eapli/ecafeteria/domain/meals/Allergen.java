/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author JoãoPedro
 */
@Entity
public class Allergen implements AggregateRoot<String>, Serializable {
    private static final long serialVersionUID = 1L;

	// ORM primary key
	@Id
	@GeneratedValue
	private Long pk;
	@Version
	private Long version;

	// business ID
	@Column(unique = true)
	private String name;
	private String description;
	private boolean active;

	protected Allergen() {
		// for ORM
	}

	/**
	 * DishType constructor.
	 *
	 * @param name        Mandatory
	 * @param description Mandatory
	 */
	public Allergen(String name, String description) {
		setName(name);
		setDescription(description);
		this.active = true;
	}

	/**
	 * Sets and validates newDescription.
	 *
	 * @param newDescription
	 */
	private void setDescription(String newDescription) {
		if (descriptionMeetsMinimumRequirements(newDescription)) {
			this.description = newDescription;
		} else {
			throw new IllegalArgumentException("Invalid Description");
		}
	}

	/**
	 * Sets and validates newName.
	 *
	 * @param newName
	 */
	private void setName(String newName) {
		if (nameMeetsMinimumRequirements(newName)) {
			this.name = newName;
		} else {
			throw new IllegalArgumentException("Invalid Name");
		}
	}

	/**
	 * Ensure name is not null or empty.
	 *
	 * @param name
	 * @return True if name meets minimum requirements. False if name does not meet minimum requirements.
	 */
	private boolean nameMeetsMinimumRequirements(String name) {
		return !Strings.isNullOrEmpty(name);
	}

	/**
	 * Ensure description is not null or empty.
	 *
	 * @param description
	 * @return True if description meets minimum requirements. False if description does not meet minimum requirements.
	 */
	private boolean descriptionMeetsMinimumRequirements(String description) {
		return !Strings.isNullOrEmpty(description);
	}


	public String description() {
		return this.description;
	}

	public boolean isActive() {
		return this.active;
	}

	/**
	 * Toggles the state of the Allergen, activating it or deactivating it
	 * accordingly.
	 *
	 * @return whether the allergen is active or not
	 */
	public boolean toogleState() {

		this.active = !this.active;
		return isActive();
	}

	/**
	 * Change Allergen description
	 *
	 * @param newDescription New description.
	 */
	public void changeDescriptionTo(String newDescription) {
		if (!descriptionMeetsMinimumRequirements(newDescription)) {
			throw new IllegalArgumentException();
		}
		this.description = newDescription;
	}

	@Override
	public boolean is(String id) {
		return id.equalsIgnoreCase(this.name);
	}

	@Override
	public String id() {
		return this.name;
	}

	@Override
	public boolean sameAs(Object other) {
		final Allergen allergen = (Allergen) other;
		return this.equals(allergen) && description().equals(allergen.description()) && isActive() == allergen.isActive();
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Allergen)) {
			return false;
		}

		final Allergen other = (Allergen) o;
		return id().equals(other.id());
	}
}
