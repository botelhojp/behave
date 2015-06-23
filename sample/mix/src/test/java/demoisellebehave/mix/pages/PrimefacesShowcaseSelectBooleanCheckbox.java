package demoisellebehave.mix.pages;

import br.gov.frameworkdemoiselle.behave.annotation.ElementLocatorType;
import br.gov.frameworkdemoiselle.behave.annotation.ElementMap;
import br.gov.frameworkdemoiselle.behave.annotation.ScreenMap;
import br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.primefaces.PrimeFacesCheckBox;

@ScreenMap(name = "Showcase CheckBox", location = "http://www.primefaces.org/showcase/ui/input/booleanCheckbox.xhtml")
public class PrimefacesShowcaseSelectBooleanCheckbox {

	@ElementMap(name = "Primefaces Checkbox Basic", locatorType = ElementLocatorType.XPath, locator = {
			"//div[@id='CONTENTSIDEindent']/div/form/table/tbody/tr[td[contains(text(),'Basic')]]/td/div",
			"//div[@id='CONTENTSIDEindent']/div/form/table/tbody/tr[td[contains(text(),'Basic')]]/td/div/div/input[@type='checkbox']"
	})
	private PrimeFacesCheckBox primefacesCheckBox;

}
