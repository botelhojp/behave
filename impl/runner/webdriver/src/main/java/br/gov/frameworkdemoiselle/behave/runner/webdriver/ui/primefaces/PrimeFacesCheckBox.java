package br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.primefaces;

import br.gov.frameworkdemoiselle.behave.runner.ui.CheckBox;
import br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.WebBase;

public class PrimeFacesCheckBox extends WebBase implements CheckBox {
	
	public Boolean isSelected() {
		super.waitVisibleClickableEnabled();
		return getElements().get(1).isSelected();
	}

	@Override
	public void click() {
		waitElement(0);
		getElements().get(0).click();
	}
}