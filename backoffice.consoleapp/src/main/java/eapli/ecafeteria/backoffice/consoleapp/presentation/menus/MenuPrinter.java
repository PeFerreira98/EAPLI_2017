package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.visitor.Visitor;

public class MenuPrinter implements Visitor<Menu> {

	@Override
	public void visit(Menu visitee) {
		System.out.printf("%-30s%-12s%-12s%-4s\n", 
				visitee.name(), 
				visitee.beginningDate().toString(),
                visitee.endingDate().toString(), 
                String.valueOf(visitee.isPublished())
                );
    }

}
