/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embeddable;

/**
 *
 * @author Pedro Tedim
 */
@Embeddable
public class MealBatchKey implements Serializable {
    
    public MealBatchKey(){
        
    }

    Calendar mealDate;
    String matRef;
    String batchCode;

}
