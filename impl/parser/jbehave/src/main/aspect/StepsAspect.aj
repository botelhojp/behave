import org.jbehave.core.steps.*;
import org.jbehave.core.configuration.*;
import org.jbehave.core.steps.StepCollector.Stage;
import java.util.*;
import java.util.regex.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.jbehave.core.model.*;
import br.gov.frameworkdemoiselle.behave.annotation.Filter;

import static java.util.Arrays.*;

public aspect StepsAspect {
	List<Method> around(Steps s, Class<? extends Annotation> c) :  execution(* methodsAnnotatedWith(..)) && this(s) && args(c) {
		return internal_proceed(s, c);
	}

	private List<Method> internal_proceed(Steps s, Class<? extends Annotation> c) {
        List<Method> annotated = new ArrayList<Method>();
        List<Method> methods = allMethods(s.instance().getClass());
        for (Method method : methods) {
            if (method.isAnnotationPresent(c) && filtered(method, c)) {
                annotated.add(method);
            }
        }
        return annotated;
	}

	private List<Method> allMethods(Class type) {
        return asList(type.getMethods());
    }

	private boolean filtered(Method m, Class<? extends Annotation> c) {
		return !has(m) || matches(m, c);
	}

	private boolean matches(Method m, Class<? extends Annotation> c) {
		return Pattern.compile(filter(m)).matcher(step(c)).find();
	}

	private String step(Class<? extends Annotation> c) {
		return story(c, "Before") || story(c, "After")
				? description().replace("Funcionalidade:", "")
				: (scenario(c, "Before") || scenario(c, "After") 
						? title() 
						: "");
	}

	private String title() {
		Scenario s = StoryRunnerAspect.scenario;
		return s == null ? null : s.getTitle().trim();
	}

	private String description() {
		Story s = MarkUnmatchedStepsAsPendingAspect.story;
		return s == null ? null : s.getDescription().asString().trim();
	}

	private boolean scenario(Class<? extends Annotation> c, String local) {
		boolean r = to("Scenario", c, local);
		return r;
	}

	private boolean story(Class<? extends Annotation> c, String local) {
		return to("Story", c, local);
	}

	private boolean to(String type, Class<? extends Annotation> c, String local) {
		return c.getName().startsWith("org.jbehave.core.annotations." + local) && c.getName().endsWith(type);
	}

	private boolean has(Method m){
		return m.isAnnotationPresent(Filter.class);
	}

	private String filter(Method m) {
		return m.getAnnotation(Filter.class).value();
	}
}