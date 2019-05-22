/*
 * Demoiselle Framework
 * Copyright (C) 2013 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.behave.runner.fest.ui;

import java.util.GregorianCalendar;

import br.gov.frameworkdemoiselle.behave.config.BehaveConfig;
import br.gov.frameworkdemoiselle.behave.exception.BehaveException;
import br.gov.frameworkdemoiselle.behave.message.BehaveMessage;
import br.gov.frameworkdemoiselle.behave.message.BehaveMessageFactory;
import br.gov.frameworkdemoiselle.behave.runner.fest.FestRunner;
import br.gov.frameworkdemoiselle.behave.runner.ui.Screen;
import junit.framework.Assert;

public class DesktopScreen extends DesktopBase implements Screen {

	private BehaveMessage message = BehaveMessageFactory.getInstance().getBehaveMessage(FestRunner.MESSAGEBUNDLE);

	@Override
	public void waitText(String text) {
		waitText(text, BehaveConfig.getRunner_ScreenMaxWait());
	}

	public void waitText(String text, Long timeout) {
		long startedTime = GregorianCalendar.getInstance().getTimeInMillis();

		while (true) {

			if (findText(text)) {
				break;
			}

			waitThreadSleep(BehaveConfig.getRunner_ScreenMinWait());
			if ((GregorianCalendar.getInstance().getTimeInMillis() - startedTime) > timeout) {
				Assert.fail(message.getString("message-text-not-found", text));
			}

		}
				
	}

	@Override
	public void waitNotText(String text) {
		long startedTime = GregorianCalendar.getInstance().getTimeInMillis();
		boolean textFound = false;
		boolean waitTimeExpired = false;

		while (!waitTimeExpired) {
			textFound = findText(text);

			if (textFound) {
				Assert.fail(message.getString("message-text-found", text));
			}
			
			waitThreadSleep(BehaveConfig.getRunner_ScreenMinWait());
			waitTimeExpired = (GregorianCalendar.getInstance().getTimeInMillis() - startedTime) > BehaveConfig.getRunner_ScreenMaxWait();
		}
		
	}
	
	private String makePattern(String field, String text) {
		String pattern = "(.*?)(" + field + "=')(.*?)(" + text + ")(.*?)";
		
		return pattern;
	}
	
	private boolean findText(String text) {
		
		try {
			String hierarchy = super.runner.getHierarchy();
			hierarchy = hierarchy.replaceAll("\n", "");
			
			boolean foundText = hierarchy.matches(makePattern("text", text));
			boolean foundMessage = hierarchy.matches(makePattern("message", text));
			
			return foundText || foundMessage;
		} catch (BehaveException be) {
			throw be;
		} catch (Exception e) {
			throw new BehaveException(message.getString("exception-unexpected", e.getMessage()), e);
		}
		
	}
	
}
