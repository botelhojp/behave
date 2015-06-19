package br.gov.frameworkdemoiselle.behave.parser.jbehave;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.gov.frameworkdemoiselle.behave.exception.BehaveException;
import br.gov.frameworkdemoiselle.behave.runner.ui.CheckBox;
import br.gov.frameworkdemoiselle.behave.runner.ui.Element;
import br.gov.frameworkdemoiselle.behave.runner.ui.Radio;

public class PrimeFacesSteps extends CommonSteps {

	/**
	 * Verifica se um PrimeFacesCheckBox ou PrimeFacesRadioButton está desmarcado.
	 * 
	 * Nota: para este funcionar o elemento precisa ser um PrimeFacesCheckBox ou PrimeFacesRadioButton
	 * @param elementName Nome do PrimeFacesCheckBox ou PrimeFacesRadioButton
	 */
	@Given("o campo \"$elementName\" está desmarcado")
	@When("o campo \"$elementName\" está desmarcado")
	@Then("o campo \"$elementName\" está desmarcado")
	public void primefacesFieldIsNotSelected(String elementName) {
		
		Element element = runner.getElement(currentPageName, elementName);

		if (element instanceof CheckBox || element instanceof Radio) {			
			if(((CheckBox) element).isSelected()){
				throw new BehaveException("O elemento com identificador ["+element.getElementMap().locator()[0]+"] deveria estar DESmarcado, mas está marcado!"); 
			}
		}
	}
	
	/**
	 * Verifica se um PrimeFacesCheckBox ou PrimeFacesRadioButton está marcado.
	 * 
	 * Nota: para este funcionar o elemento precisa ser um PrimeFacesCheckBox ou PrimeFacesRadioButton
	 * @param elementName nome do elemento PrimeFacesCheckBox ou PrimeFacesRadioButton
	 */
	@Given("o campo \"$elementName\" está marcado")
	@When("o campo \"$elementName\" está marcado")
	@Then("o campo \"$elementName\" está marcado")
	public void primefacesFieldIsSelected(String elementName) {
		
		Element element = runner.getElement(currentPageName, elementName);

		if (element instanceof CheckBox || element instanceof Radio) {			
			if(!((CheckBox) element).isSelected()){
				throw new BehaveException("O elemento com identificador ["+element.getElementMap().locator()[0]+"] deveria estar marcado, mas está DESmarcado!"); 
			}
		}
	}

}
