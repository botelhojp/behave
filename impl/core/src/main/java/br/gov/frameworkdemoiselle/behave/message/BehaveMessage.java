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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class that manipulates Demoiselle Behave resource bundles.
 * 
 * @author SERPRO
 *
 */
public class BehaveMessage {

	/**
	 * Messages resource bundle.
	 */
	private ResourceBundle resourceBundle = null;

	/**
	 * Creates a BehaveMessage according to passed resource bundle base name and locale.
	 * 
	 * @param baseName the resource bundle base name
	 * @param locale the resource bundle locale
	 */
	protected BehaveMessage(String baseName, Locale locale) {
		setResourceBundle(baseName, locale);
	}

	/**
	 * Gets the resource bundle.
	 * 
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Sets the resource bundle base name and locale.
	 * 
	 * @param baseName the resource bundle base name
	 * @param locale the resource bundle locale
	 */
	private void setResourceBundle(String baseName, Locale locale) {
		
		try {
			resourceBundle = ResourceBundle.getBundle(baseName, locale);
		} catch (MissingResourceException ex) {	
			resourceBundle = ResourceBundle.getBundle(baseName, new Locale("pt","BR"));
		}
	}

	/**
	 * Gets the resource bundle message based on a key and some parameters.
	 * 
	 * @param key message key
	 * @param params message parameteres
	 * 
	 * @return resource bundle message
	 */
	public String getString(String key, Object... params) {
		
		if (params == null || params.length == 0) {
			return getString(key);
		} else {
			return MessageFormat.format(getString(key), params);
		}
		
	}
	
	/**
	 * Gets the resource bundle message based on a key.
	 * 
	 * @param key message key
	 * 
	 * @return resource bundle message
	 */
	public String getString(String key) {
		
		if (resourceBundle.containsKey(key)) {
			return resourceBundle.getString(key);
		} else {
			return "??{" + key + "}??";
		}
		
	}
	
}
