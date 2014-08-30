import org.jbehave.core.model.*;
import org.jbehave.core.steps.*;
import org.jbehave.core.steps.StepCollector.Stage;
import org.jbehave.core.annotations.*;
import org.jbehave.core.embedder.*;

public aspect StoryRunnerAspect {
	public static Scenario scenario;

	before(StoryRunner s, Object r, Scenario c, Meta m, Stage g, ScenarioType t) : execution(* runBeforeOrAfterScenarioSteps(..)) && this(s) && args(r, c, m, g, t) {
		scenario = c;
	}
}