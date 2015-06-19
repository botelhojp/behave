package br.gov.frameworkdemoiselle.behave.parser.jbehave;

import org.jbehave.core.annotations.When;

import br.gov.frameworkdemoiselle.behave.runner.ui.CheckBox;
import br.gov.frameworkdemoiselle.behave.runner.ui.Element;

public class PrimeFacesCheckBoxSteps extends PrimeFacesSteps {
	
	/**
	 * Tenta preencher repetidas vezes o checkbox.
	 *  
	 * Nota: para este funcionar o elemento precisa ser um PrimefacesCheckBox
	 * @param checkBoxName Nome do PrimefacesCheckBox
	 */
	@When("garanto que o campo \"$checkBoxName\" está desmarcado")
	public void forceUncheckField(String checkBoxName) {
		
		Element element = runner.getElement(currentPageName, checkBoxName);

		CheckBox cb = (CheckBox) element;
		Integer tentativas = 0;
		
		while (cb.isSelected() && tentativas < 5) {
			cb.click();
			tentativas++;
		}
		
	}
	
	/**
	 * Tenta preencher repetidas vezes o checkbox.
	 * 
	 * Nota: para este funcionar o elemento precisa ser um PrimefacesCheckBox
	 * @param checkBoxName Nome do PrimefacesCheckBox
	 */
	@When("garanto que o campo \"$checkBoxName\" está marcado")
	public void forceCheckField(String checkBoxName) {
		
		Element element = runner.getElement(currentPageName, checkBoxName);

		if (element instanceof CheckBox) {
			CheckBox cb = (CheckBox) element;
			Integer tentativas = 0;
			
			while (!cb.isSelected() && tentativas < 5) {
				cb.click();
				tentativas++;
			}
		}
	}
}
