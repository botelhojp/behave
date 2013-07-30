package br.gov.frameworkdemoiselle.behave.runner.webdriver.ui;

import org.openqa.selenium.interactions.Actions;

import br.gov.frameworkdemoiselle.behave.runner.ui.Menu;

public class WebMenu extends WebBase implements Menu {
	
	public void click() {
		waitElement(0);
		
		// Clica
		getElements().get(0).click();
	}
	
	public void mouseOver() {
		waitElement(0);
		
		// mouse over
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getElements().get(0)).build().perform();
	}

}
