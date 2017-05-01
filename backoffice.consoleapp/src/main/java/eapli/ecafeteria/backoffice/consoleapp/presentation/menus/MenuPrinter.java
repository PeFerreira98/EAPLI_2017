package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import java.text.SimpleDateFormat;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.visitor.Visitor;

public class MenuPrinter implements Visitor<Menu> {

	@Override
	public void visit(Menu visitee) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.printf("%-30s%-6s%-12s to %-12s\n", 
				visitee.name(), 
				String.valueOf(visitee.isPublished()),
				format1.format(visitee.beginningDate().getTime()),
				format1.format(visitee.endingDate().getTime())                
                );
    }

}
