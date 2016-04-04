package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 */
class ListDishTypeService {

    public Iterable<DishType> listDishTypes() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        return dishTypeRepository.all();
    }
}
