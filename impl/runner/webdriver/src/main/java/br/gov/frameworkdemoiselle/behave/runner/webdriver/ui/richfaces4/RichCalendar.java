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
package br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.richfaces4;

import br.gov.frameworkdemoiselle.behave.exception.BehaveException;
import br.gov.frameworkdemoiselle.behave.runner.ui.Button;
import br.gov.frameworkdemoiselle.behave.runner.webdriver.ui.WebTextField;

/**
 * Componente para mapear elementos de tela referentes ao componente rich:calendar
 * 
 * Utiliza a API Javascript do Richfaces para a correta manipulação do componente.
 * O locator do mapeamento de tela deve retornar o primeiro div gerado pelo componente rich:calendar,
 * aquele div que possui 'class="rf-cal"' ou ainda que possui o mesmo ID informado no arquivo XHTML,
 * porém, o ID não é obrigatório, basta selecionar o primeiro div gerado pelo componente.
 * 
 * @author SERPRO
 *
 */
public class RichCalendar extends WebTextField implements Button {

	@Override
	public void click() {
		waitElement(0);

		checkRichfacesComponent();
		
		// Abre ou fecha o menu de acordo com o estado do componente
		if( (Boolean) getJavascirptExecutor().executeScript("return RichFaces.$('"+getId()+"').isVisible;") )
			hideCalendar();
		else {
			showCalendar();
		}
	}

	public void setValue(String value) {
		checkRichfacesComponent();
		getJavascirptExecutor().executeScript("RichFaces.$('"+getId()+"').setValue('"+value+"');");
		
	}
	
	public void clear(){
		checkRichfacesComponent();
		setValue("");
	}
	
	public void sendKeys(CharSequence... keysToSend) {
		checkRichfacesComponent();
		setValue((String)keysToSend[0]);
	}
	
	/**
	 * Utiliza a API Javascritp do Richfaces para mostrar o Calendário do rich:calendar
	 */
	public void showCalendar() {
		checkRichfacesComponent();
		getJavascirptExecutor().executeScript("RichFaces.$('"+getId()+"').showPopup();");
	}
	
	/**
	 * Utiliza a API Javascritp do Richfaces para ocultar o Calendário do rich:calendar
	 */
	public void hideCalendar() {
		checkRichfacesComponent();
		getJavascirptExecutor().executeScript("RichFaces.$('"+getId()+"').hidePopup();");
	}
		
	/**
	 * Utiliza a API Javascritp do Richfaces definir o calendário para a data de hoje
	 */
	public void today() {
		checkRichfacesComponent();
		getJavascirptExecutor().executeScript("RichFaces.$('"+getId()+"').today();");
	}
	
	/**
	 * Verifica se o componente selecionado é realmente um coponente rich:calendar
	 * @return
	 */
	public boolean isRichCalendar(){
		String jsCodeCheckComponent = "return (function(tipo, id) { var rf = RichFaces.$(id); return (typeof(rf) == \"object\" && typeof(rf.name) == \"string\" && rf.name == tipo);})('Calendar','"+getId()+"');";
		return (Boolean) getJavascirptExecutor().executeScript(jsCodeCheckComponent);
	}
	
	/**
	 * Método para garantir que o componente correto foi selecionado
	 */
	private void checkRichfacesComponent() {
		if(!isRichCalendar())
			throw new BehaveException("O elemento [" + this.getElementMap().name() + "] selecionado possui ID ["+getId()+"] mas não é um componente do tipo rich:calendar.");

	}
}
