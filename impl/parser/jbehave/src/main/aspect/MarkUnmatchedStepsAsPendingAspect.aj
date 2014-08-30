import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.StepFinder;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.steps.Step;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.StepCollector.Stage;
import java.util.*;
import org.jbehave.core.model.*;

public aspect MarkUnmatchedStepsAsPendingAspect {
	public static Story story;

	List<Step> around(MarkUnmatchedStepsAsPending m, List<CandidateSteps> c, Story s, Stage e, boolean g) : execution(* collectBeforeOrAfterStorySteps(..)) && this(m) && args(c, s, e, g) {
		//System.out.println(s.getDescription().asString());
		story = s;
		return proceed(m, c, s, e, g);
	}
}