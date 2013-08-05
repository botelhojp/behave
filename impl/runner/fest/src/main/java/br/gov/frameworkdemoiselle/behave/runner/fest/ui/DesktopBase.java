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

import java.awt.Component;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;

import org.fest.swing.core.BasicComponentFinder;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.ComponentMatcher;

import br.gov.frameworkdemoiselle.behave.annotation.ElementLocatorType;
import br.gov.frameworkdemoiselle.behave.exception.BehaveException;
import br.gov.frameworkdemoiselle.behave.internal.ui.MappedElement;
import br.gov.frameworkdemoiselle.behave.runner.fest.FestRunner;
import br.gov.frameworkdemoiselle.behave.runner.ui.BaseUI;

/**
 * @author SERPRO
 */
public class DesktopBase extends MappedElement implements BaseUI {

	private Logger logger = Logger.getLogger(this.toString());

	protected FestRunner runner = (FestRunner) getRunner();

	public Component getElement() {
		ComponentFinder cf = BasicComponentFinder.finderWithCurrentAwtHierarchy();

		// Finder
		Collection<Component> findedComponents = cf.findAll(runner.currentContainer, new ComponentMatcher() {

			@Override
			public boolean matches(Component c) {
				return matcher(c);
			}
		});

		// Se encontrar mais de um elemento com o finder utiliza a anotação do índice
		logger.info("Total de elementos encontrados: " + findedComponents.size() + " | Tela: " + runner.getTitle());

		if (findedComponents.size() == 0) {
			throw new BehaveException("Elemento não encontrado.");
		} else {
			return (Component) findedComponents.toArray()[0];
		}
	}

	private boolean matcher(Component component) {
		if (component instanceof JButton) {
			JButton button = (JButton) component;

			if (button.getName() != null && getElementMap().locatorType() == ElementLocatorType.Name
					&& button.getName().equalsIgnoreCase(getElementMap().locator()[0])) {
				return true;
			} else if (getElementMap().locatorType() == ElementLocatorType.Label
					&& button.getText().equalsIgnoreCase(getElementMap().locator()[0])) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void setLocatorParameters(List<String> Parameters) {

	}

	@Override
	public String getText() {
		return null;
	}

	protected void waitThreadSleep(Long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ex) {
			throw new BehaveException("Thread sleep interrompido", ex);
		}
	}

}
