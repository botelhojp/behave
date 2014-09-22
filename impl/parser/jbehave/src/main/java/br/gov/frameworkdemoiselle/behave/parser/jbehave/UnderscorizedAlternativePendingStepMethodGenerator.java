package br.gov.frameworkdemoiselle.behave.parser.jbehave;

import org.jbehave.core.steps.StepType;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.steps.StepCreator.PendingStep;
import java.text.Normalizer;
import java.io.StringWriter;
import static java.text.MessageFormat.format;

public class UnderscorizedAlternativePendingStepMethodGenerator implements AlternativePendingStepMethodGenerator {
	public String execute(Keywords keywords, PendingStep step) {
		this.keywords = keywords;
		return internal_proceed(step);
	}

	private String internal_proceed(PendingStep step) {
		String stepAsString = step.stepAsString();
        String previousNonAndStepAsString = step.previousNonAndStepAsString();
        StepType stepType = null;
        if (keywords.isAndStep(stepAsString) && previousNonAndStepAsString != null) {
            stepType = keywords.stepTypeFor(previousNonAndStepAsString);
        } else {
            stepType = keywords.stepTypeFor(stepAsString);
        }
        String stepPattern = keywords.stepWithoutStartingWord(stepAsString, stepType);
        String stepAnnotation = StringUtils.capitalize(stepType.name().toLowerCase());
        String methodName = methodName(stepType, stepPattern);
        String pendingAnnotation = Pending.class.getSimpleName();
        return format(METHOD_SOURCE, stepAnnotation, escape(stepPattern), pendingAnnotation, methodName, keywords.pending());
	}

    private static final String METHOD_SOURCE = "@{0}(\"{1}\")\n@{2}\npublic void {3}() '{'\n  // {4}\n'}'\n";

    private Keywords keywords;

    private String methodName(StepType stepType, String stepPattern) {
    	String name = stepType.name().toLowerCase() + " " + WordUtils.capitalize(stepPattern);
    	char filteredName[]=new char[name.length()];
        int index=0;
        for(int i=0;i<name.length();i++) {
            char ch=name.charAt(i);
            if(Character.isJavaIdentifierPart(ch) && ch!='$' && ch!=127 || ch==' ') {
            	filteredName[index++]=ch;
            }
        }
        return underscorize(normalize(new String(filteredName,0,index).toLowerCase()), "_");
    }

    private String normalize(String input) {
    	return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    private String underscorize(String input, String replacement) {
    	return input.replaceAll("\\s", replacement);
    }

    private String escape(String str) {
    	boolean escapeSingleQuote = false;
    	boolean escapeForwardSlash = false;
    	StringWriter out = new StringWriter(str.length() * 2);
    	if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return "";
        }
        int sz;
        sz = str.length();
        for (int i = 0; i < sz; i++) {
            char ch = str.charAt(i);

            if (ch < 32) {
                switch (ch) {
                    case '\b' :
                        out.write('\\');
                        out.write('b');
                        break;
                    case '\n' :
                        out.write('\\');
                        out.write('n');
                        break;
                    case '\t' :
                        out.write('\\');
                        out.write('t');
                        break;
                    case '\f' :
                        out.write('\\');
                        out.write('f');
                        break;
                    case '\r' :
                        out.write('\\');
                        out.write('r');
                        break;
                    default :
                        break;
                }
            } else {
                switch (ch) {
                    case '\'' :
                        if (escapeSingleQuote) {
                            out.write('\\');
                        }
                        out.write('\'');
                        break;
                    case '"' :
                        out.write('\\');
                        out.write('"');
                        break;
                    case '\\' :
                        out.write('\\');
                        out.write('\\');
                        break;
                    case '/' :
                        if (escapeForwardSlash) {
                            out.write('\\');
                        }
                        out.write('/');
                        break;
                    default :
                        out.write(ch);
                        break;
                }
            }
        }
        return out.toString();
    }
}