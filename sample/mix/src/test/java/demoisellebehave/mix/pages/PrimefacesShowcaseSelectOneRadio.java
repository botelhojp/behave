package demoisellebehave.mix.pages;

import br.gov.frameworkdemoiselle.behave.annotation.ElementLocatorType;
import br.gov.frameworkdemoiselle.behave.annotation.ElementMap;
import br.gov.frameworkdemoiselle.behave.annotation.ScreenMap;
import br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.primefaces.PrimeFacesCheckBox;
import br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.primefaces.PrimeFacesRadioButton;

@ScreenMap(name = "Showcase SelectOneRadio", location = "http://www.primefaces.org/showcase/ui/input/oneRadio.xhtml")
public class PrimefacesShowcaseSelectOneRadio {

	@ElementMap(name = "Primefaces SelectOneRadio Basic - Console - First Option", locatorType = ElementLocatorType.XPath, locator = {
			"//div[@id='CONTENTSIDEindent']/div/form/table[1]/tbody/tr/td/table/tbody/tr/td[1]/div[1]",
			"//div[@id='CONTENTSIDEindent']/div/form/table[1]/tbody/tr/td/table/tbody/tr/td[1]/div[1]/div/input[@type='radio']"
	})
	private PrimeFacesRadioButton primefacesRadioButton;

}
