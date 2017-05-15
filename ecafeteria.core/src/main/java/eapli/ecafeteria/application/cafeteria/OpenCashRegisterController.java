/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.domain.cashregister.CashRegister;
import eapli.ecafeteria.domain.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Alexandra Ferreira 1140388 - Nuno Costa 1131106
 */
public class OpenCashRegisterController {

    public Iterable<MealType> getMealTypes() {
        return PersistenceContext.repositories().mealTypes().findAll();
    }

    public MealType mealByDefault() {

        final MealTypeRepository mealRepository = PersistenceContext.repositories().mealTypes();

        return mealRepository.findByDefault();
    }

    public boolean open(String number, MealType mealType, Calendar date) throws DataConcurrencyException, DataIntegrityViolationException {

        CashRegister cashRegister = PersistenceContext.repositories().cashRegisters().findByNumber(number);

        Shift shift = null;

        boolean flag = false;

        try {
            shift = PersistenceContext.repositories().shifts().findByOpenedCashRegister(cashRegister);
            saveShift(shift);
            flag = true;
        } catch (Exception e) {
            shift = new Shift(mealType, cashRegister);
            saveShift(shift);
            flag = true;
        }

        saveCashRegister(cashRegister);

        return flag;
    }

    /**
     * This method updates any changes on the shift
     *
     * @param shift Shift
     * @throws DataConcurrencyException
     */
    private void saveShift(Shift shift) throws DataConcurrencyException, DataIntegrityViolationException {
        PersistenceContext.repositories().shifts().save(shift);
    }

    /**
     * This method persist any changes in cash register
     *
     * @param cashRegister Cash Register
     * @throws DataConcurrencyException
     */
    private void saveCashRegister(CashRegister cashRegister) throws DataConcurrencyException, DataIntegrityViolationException {
        PersistenceContext.repositories().cashRegisters().save(cashRegister);
    }

    /**
     * Method that allows to choose a meal given a certain day and meal type
     *
     * @param day Day of the meal
     * @param mealType Meal type (can be Dinner ,Lunch or Default)
     * @return new Shift that matches with the day and the type of meal received
     * previously
     */
    public boolean hasMeal(Calendar day, MealType mealType) {

        Iterable<Menu> menu = PersistenceContext.repositories().menus().findByDate(day);

        MealRepository mealRepo = PersistenceContext.repositories().meals();

        return mealRepo.mealsOfMenuByDateMealType(day, mealType, menu.iterator().next()).iterator().hasNext();
    }

}
