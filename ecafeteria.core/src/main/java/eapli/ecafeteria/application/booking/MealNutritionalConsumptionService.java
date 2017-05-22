/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *  Classe que permite obter informacao de consumos nutricionais
 * relacionados com o consumo de Meals reservadas. Re usa a NutricionalInfo para fazer um somatorio de NutricionalInfos.
 * @author Hugo & Pedro G3
 */
public class MealNutritionalConsumptionService {
 
    /**
     * Valor default de calorias e sal para um nutricional info vazia.
     */
    private static final int DEFAULT_VALUE_ON_EMPTY = 0;
    
    
    /**
     * Permite obter o consumo nutricional sob a forma de NutricionalInfo agendado para 
     * um utilizador para a semana de uma meal que pretende agendar. Inclui o consumo da
     * meal que indica.
     * @param cafeteriaUser utilizador em questao.
     * @param meal meal que pretende fazer booking.
     * @return um NutricionalInfo que é o somatorio de todas as meals agendadas + esta.
     */
    public NutricionalInfo planedWeekConsumption(CafeteriaUser cafeteriaUser, Meal meal){
        if ( meal == null) return this.createEmptyNutricionalInfo();
        
        Calendar originalMealDay = meal.date();     //Obtencao do dia a que a meal se refere.
        
        // Começamos com a da meal que vamos adicionar.
        NutricionalInfo nutricionalInfoTotal = meal.dish().nutricionalInfo(); 
        
        if(cafeteriaUser == null ) return nutricionalInfoTotal;     //    Se nao existir user devolvemos a da meal que recebemos, pk sem user nao ha bookings.
        
        //Obtencao de uma lista com todos os dias da semana que contem o dia da meal.
        List<Calendar> listDayOfWeekOfMeal = DateTime.listDaysOfWeekOfGivenDay(originalMealDay);
        
        // Somar os consumos nutricionais de cada dia da semana. Se nao existirem vem a zero.
        for(Calendar day : listDayOfWeekOfMeal){
            nutricionalInfoTotal = nutricionalInfoTotal.sumNutricionalInfo( this.plannedDayConsumption(cafeteriaUser, day));
        }
        
        return nutricionalInfoTotal;
    }
    
    
    /**
     * Devolve as meals booked para um determinado user em um determinado dia.
     * @param cafeteriaUser cafeteriaUser
     * @param day dia em questao
     * @return uma NutricionalInfo que é composta pela soma de todas as nutricionalInfo de todas as Meal daquele dia. A nao existir nenhuma, vem com zeros.
     */
    public NutricionalInfo plannedDayConsumption(CafeteriaUser cafeteriaUser, Calendar day){
        NutricionalInfo nutricionalInfoTotal = this.createEmptyNutricionalInfo();
        if (cafeteriaUser == null || day == null) return nutricionalInfoTotal;      //Zeros.
        
        
        
        try{
            Iterable<Booking> bookedMealsForDay = PersistenceContext.repositories().reserves().findNextReserves(day, day, cafeteriaUser);
        
            for(Booking booking : bookedMealsForDay){
                nutricionalInfoTotal = nutricionalInfoTotal.sumNutricionalInfo(booking.meal().dish().nutricionalInfo());
            }
        }
        catch(PersistenceException ex){
            String error = "MealNutritionalConsumptionService: Error interacting with BookingRepository() -    " + ex;
            Logger.getGlobal().severe(error);
        }
        
                
        return nutricionalInfoTotal;
    }
    
    
    /**
     * Cria uma nutricionaInfo vazia , //FIXME - nao fazer new nesta classe, usar talvez um builder.
     * @return uma nutricionaInfo vazia 0 calorias, 0 sal.
     */
    public NutricionalInfo createEmptyNutricionalInfo(){
        return new NutricionalInfo( DEFAULT_VALUE_ON_EMPTY ,DEFAULT_VALUE_ON_EMPTY ); //FIXME - nao fazer new nesta classe, usar talvez um builder.
    }
}
