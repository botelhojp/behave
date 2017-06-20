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
package br.gov.frameworkdemoiselle.behave.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.gov.frameworkdemoiselle.behave.config.BehaveConfig;

/**
 *  Class that creates {@link BehaveMessage BehaveMessage} according to the base name and locale
 *  of the resource bundle.
 * 
 * @author SERPRO
 *
 */
public final class BehaveMessageFactory {

	/**
	 * Singleton instance of BehaveMessageFactory.
	 */
	private static BehaveMessageFactory instance;
	
	/**
	 * List of already created BehaveMessage.
	 */
	private List<BehaveMessage> behaveMessages;
	
	/**
	 * Default constructor.
	 * Initiates the list of already created BehaveMessage. 
	 */
	private BehaveMessageFactory() {
		behaveMessages = new ArrayList<BehaveMessage>();
	}
	
	/**
	 * Gets the single instance of BehaveMessageFactory.
	 * 
	 * @return the single instance of BehaveMessageFactory.
	 */
	synchronized public static BehaveMessageFactory getInstance() {
		
		if (instance != null) {
			return instance;
		} else {
			instance = new BehaveMessageFactory();
			return instance;
		}
		
	}
	
	/**
	 * Gets the instance of BehaveMessage according to the resource bundle base name.
	 * 
	 * @param baseName the resource bundle base name
	 * 
	 * @return instance of BehaveMessage
	 */
	public BehaveMessage getBehaveMessage(String baseName){
		Locale locale = new Locale(BehaveConfig.getProperty("behave.message.locale", "pt"));
				
		return getBehaveMessage(baseName, locale);
	}
	
	/**
	 * Gets the instance of BehaveMessage according to the resource bundle base name and locale.
	 * 
	 * @param baseName the resource bundle base name
	 * @param locale the resource bundle locale
	 * 
	 * @return instance of BehaveMessage
	 */
	public BehaveMessage getBehaveMessage(String baseName, Locale locale){		
		BehaveMessage behaveMessage = findBehaveMessage(baseName, locale);
		
		if (behaveMessage == null) {
			behaveMessage = new BehaveMessage(baseName, locale);
			behaveMessages.add(behaveMessage);
		}
		
		return behaveMessage;
	}
	
	/**
	 * Finds a BehaveMessage based on its resource bundle base name and locale.
	 * 
	 * @param baseName the resource bundle base name
	 * @param locale the resource bundle locale
	 * 
	 * @return instance of BehaveMessage
	 */
	private BehaveMessage findBehaveMessage(String baseName, Locale locale){
		
		for (BehaveMessage behaveMessage : behaveMessages) {
			String objBundleName = behaveMessage.getResourceBundle().getBaseBundleName();
			Locale objBundleLocale = behaveMessage.getResourceBundle().getLocale(); 	
			
			if (objBundleName.equals(baseName) && objBundleLocale.equals(locale)) {
				return behaveMessage;
			}
		}
		
		return null;
	}
	
}
