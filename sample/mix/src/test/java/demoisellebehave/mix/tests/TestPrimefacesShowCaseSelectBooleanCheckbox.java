package demoisellebehave.mix.tests;

import org.junit.Test;

import demoisellebehave.mix.steps.MySteps;
import br.gov.frameworkdemoiselle.behave.controller.BehaveContext;
import br.gov.frameworkdemoiselle.behave.parser.jbehave.PrimeFacesCheckBoxSteps;

public class TestPrimefacesShowCaseSelectBooleanCheckbox {

	private BehaveContext eng = null;

	public TestPrimefacesShowCaseSelectBooleanCheckbox() {
		eng = BehaveContext.getInstance();
		eng.addSteps(new MySteps());
		eng.addSteps(new PrimeFacesCheckBoxSteps());
	}

	@Test
	public void testAllStories() throws Throwable {
		eng.run("/stories/primefaces/test_primefaces_showcase_SelectBooleanCheckbox.story");
	}

}
