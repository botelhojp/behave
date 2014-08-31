import org.jbehave.core.steps.PendingStepMethodGenerator;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.steps.StepCreator.PendingStep;

import br.gov.frameworkdemoiselle.behave.config.BehaveConfig;
import br.gov.frameworkdemoiselle.behave.parser.jbehave.AlternativePendingStepMethodGenerator;

public aspect PendingStepMethodGeneratorAspect {
    private Keywords keywords;

	before(PendingStepMethodGenerator p, Keywords k) : execution(PendingStepMethodGenerator.new(Keywords)) && this(p) && args(k) {
		keywords = k;
	}

	String around(PendingStepMethodGenerator p, PendingStep step) : execution(* generateMethod(PendingStep)) && this(p) && args(step) { 
        try{
            AlternativePendingStepMethodGenerator apsmg = ((AlternativePendingStepMethodGenerator)Class.forName(BehaveConfig.getProperty("behave.alternative.pending.step.method.generator.impl", null)).newInstance());
            return apsmg.execute(keywords, step);
        }catch(Throwable t){
            return proceed(p, step);
        }
		//return internal_proceed(step);
	}
}