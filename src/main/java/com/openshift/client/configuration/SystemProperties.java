/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.openshift.client.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.openshift.client.OpenShiftException;

/**
 * @author André Dietisheim
 * @author Corey Daley
 */
public class SystemProperties extends AbstractOpenshiftConfiguration {

	protected static final String KEY_OPENSHIFT_TIMEOUT = "express.timeout";
	
	public SystemProperties(IOpenShiftConfiguration parentConfiguration) throws OpenShiftException, IOException {
		super(parentConfiguration);
	}

	@Override
	protected Properties getProperties(File file, Properties defaultProperties) {
		Properties properties = new Properties(defaultProperties);
		copySystemProperty(KEY_LIBRA_DOMAIN, properties);
		copySystemProperty(KEY_LIBRA_SERVER, properties);
		copySystemProperty(KEY_RHLOGIN, properties);
		copySystemProperty(KEY_PASSWORD, properties);
		copySystemProperty(KEY_CLIENT_ID, properties);
		copySystemProperty(KEY_OPENSHIFT_TIMEOUT, properties);
		return properties;
	}

	private void copySystemProperty(String key, Properties properties) {
		Object value = System.getProperties().get(key);
		if (key.equals(KEY_OPENSHIFT_TIMEOUT) && value != null) {
			properties.put(KEY_TIMEOUT, value);
		} else if (value != null) {
			properties.put(key, value);
		}
	}
}
