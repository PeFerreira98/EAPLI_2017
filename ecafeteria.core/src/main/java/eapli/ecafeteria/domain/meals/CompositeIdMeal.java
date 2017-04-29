/**
 * 
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embeddable;
import eapli.framework.domain.ValueObject;

/**
 * @author zero_
 *
 */
@Embeddable
public class CompositeIdMeal implements ValueObject, Serializable{

	private static final long serialVersionUID = 1L;
	
	private String menu;
	private String dish;
	private String mealType;
	private Calendar calendar;
	
	protected CompositeIdMeal(){
		// for ORM
	}
	
	public CompositeIdMeal(Menu menu, Dish dish, MealType mealType, Calendar calendar){
		if (menu == null || mealType == null || dish == null || calendar == null) {
            throw new IllegalArgumentException("Null parameter inserted");
        }
		if (!menu.isInBetween(calendar)){
			throw new IllegalArgumentException("Meal date does not correspond to menu");
		}
		this.dish = dish.id().toString();
        this.mealType = mealType.id();
        this.menu = menu.id();
        this.calendar = calendar;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
		
        if (!(o instanceof CompositeIdMeal)) {
            return false;
        }

        final CompositeIdMeal other = (CompositeIdMeal) o;
        if (!this.menu.equals(other.menu)) {
            return false;
        }

        if (!this.dish.equals(other.dish)) {
            return false;
        }

        if (!this.mealType.equals(other.mealType)) {
            return false;
        }

        return this.calendar.equals(other.calendar);
    }
	
	@Override
    public int hashCode() {
        return this.dish.hashCode();
    }
}
