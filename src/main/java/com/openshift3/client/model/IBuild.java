/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. Distributed under license by Red Hat, Inc.
 * All rights reserved. This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Red Hat, Inc.
 ******************************************************************************/
package com.openshift3.client.model;

public interface IBuild extends IResource {

	/**
	 * The status of the buld
	 * @return
	 */
	String getStatus();
	
	/**
	 * Details about the status of this build
	 * @return
	 */
	String getMessage();
	
	/**
	 * The name of the pod running the build
	 * @return
	 */
	String getPodName();
}
