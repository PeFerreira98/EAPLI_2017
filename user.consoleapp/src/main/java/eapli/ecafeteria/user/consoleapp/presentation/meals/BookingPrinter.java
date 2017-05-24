package eapli.ecafeteria.user.consoleapp.presentation.meals;

import java.text.SimpleDateFormat;

import eapli.ecafeteria.domain.mealbooking.Booking;
import eapli.framework.visitor.Visitor;

public class BookingPrinter implements Visitor<Booking> {

	@Override
	public void visit(Booking visitee) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        System.out.printf("%-12s%-30s%-12s%-30s%-10s\n", 
        		format1.format(visitee.meal().date().getTime()),
        		visitee.meal().dish().name(),
        		visitee.meal().mealType().description(),
        		visitee.meal().menu().id(),
        		visitee.state().name()
        		);
		
	}

}
