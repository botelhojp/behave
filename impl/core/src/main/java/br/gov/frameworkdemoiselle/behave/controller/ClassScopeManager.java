package br.gov.frameworkdemoiselle.behave.controller;

import org.junit.AfterClass;

import br.gov.frameworkdemoiselle.behave.config.BehaveConfig;
import br.gov.frameworkdemoiselle.behave.exception.BehaveException;
import br.gov.frameworkdemoiselle.behave.message.BehaveMessage;
import br.gov.frameworkdemoiselle.behave.message.BehaveMessageFactory;

public class ClassScopeManager {

	
	private static BehaveContext behaveContext = BehaveContext.getInstance();
	

	@AfterClass
	public static void clearClassLists() {
		BehaveMessage bm = BehaveMessageFactory.getInstance().getBehaveMessage(BehaveConfig.MESSAGEBUNDLE);
		if (!BehaveConfig.getRunner_LegacyRunner()){
			behaveContext.getStepsClass().clear();
			behaveContext.getStoriesClass().clear();
			behaveContext.getStoriesReuseClass().clear();
		}else{
			throw new BehaveException(bm.getString("exception-legacyRunner-false"));
		}
	}
	
}
