package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new ECafeteriaBootstraper().execute();
    }

    @Override
    public UserRepository users(boolean tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new InMemoryDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
        return new InMemoryOrganicUnitRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers(boolean tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(boolean tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public DishRepository dishes() {
        return new InMemoryDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new InMemoryMaterialRepository();
    }

    @Override
    public MenuRepository menus() {
        return new InMemoryMenuRepository();
    }

    @Override
    public MealTypeRepository mealTypes() {
        return new InMemoryMealTypeRepository();
    }

    @Override
    public MealRepository meals() {
        return new InMemoryMealRepository();
    }

    @Override
    public AllergenRepository allergens() {
        return new InMemoryAllergenRepository();
    }

    @Override
    public DishAllergenRepository dishAllergens() {
        return new InMemoryDishAllergenRepository();
    }

    @Override
    public BookingRepository reserves() {
        return new InMemoryBookingRepository();
    }

    @Override
    public NutricionalProfileRepository nutricionalProfiles() {
        return new InMemoryNutricionalProfileRepository();
    }

    @Override
    public NutricionalProfileAllergenRepository nutricionalProfileAllergens() {
        return new InMemoryNutricionalProfileAllergenRepository();
    }
}
