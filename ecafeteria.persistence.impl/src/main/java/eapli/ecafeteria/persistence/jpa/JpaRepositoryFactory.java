package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.BookingAlertRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.DishAllergenRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealBatchRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.NutricionalProfileAllergenRepository;
import eapli.ecafeteria.persistence.NutricionalProfileRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalContext;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
        return new JpaOrganicUnitRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return new JpaTransactionalContext(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MenuRepository menus() {
        return new JpaMenuRepository();
    }

    @Override
    public MealTypeRepository mealTypes() {
        return new JpaMealTypeRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    @Override
    public AllergenRepository allergens() {
        return new JpaAllergenRepository();
    }

    @Override
    public DishAllergenRepository dishAllergens() {
        return new JpaDishAllergenRepository();
    }

    @Override
    public BookingRepository reserves() {
        return new JpaBookingRepository();
    }

    @Override
    public NutricionalProfileRepository nutricionalProfiles() {
        return new JpaNutricionalProfileRepository();
    }

    @Override
    public NutricionalProfileAllergenRepository nutricionalProfileAllergens() {
        return new JpaNutricionalProfileAllergenRepository();
    }

    @Override
    public BatchRepository batches() {
        return new JpaBatchRepository();
    }

    @Override
    public MealBatchRepository mealBatches() {
        return new JpaMealBatchRepository();
    }

    @Override
    public CashRegisterRepository cashRegisters() {
        return new JpaCashRegisterRepository();
    }

    @Override
    public ShiftRepository shifts() {
        return new JpaShiftRepository();
    }

    @Override
    public RatingRepository ratings() {
        return new JpaRatingRepository();
    }

    @Override
    public CommentRepository comments() {
        return new JpaCommentRepository();
    }

    @Override
    public MealPlanRepository mealPlans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuPlanRepository menuPlans() {
        return new JpaMenuPlanRepository();
    }

    @Override
    public BookingAlertRepository bookingAlerts() {
        return new JpaBookingAlertRepository();
    }
}
