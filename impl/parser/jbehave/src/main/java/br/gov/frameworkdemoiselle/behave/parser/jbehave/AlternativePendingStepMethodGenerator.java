package br.gov.frameworkdemoiselle.behave.parser.jbehave;

import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.steps.StepCreator.PendingStep;

public interface AlternativePendingStepMethodGenerator {
	public String execute(Keywords keywords, PendingStep step);
}