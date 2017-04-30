package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class ChangeMealTypeController implements Controller{

	private final MealTypeRepository mealTypeRepository = PersistenceContext.repositories().mealTypes();
	
	public MealType changeMealType(MealType mealType, String newDescription)
			throws DataIntegrityViolationException, DataConcurrencyException {
		Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
		
		if(newDescription.isEmpty() || newDescription == null){
			throw new IllegalStateException();
		}
		
		mealType.changeDescriptionTo(newDescription);

		return this.mealTypeRepository.save(mealType);
	}
	
	public Iterable<MealType> listMealTypes(){
        return this.mealTypeRepository.findAll();
    }
}
