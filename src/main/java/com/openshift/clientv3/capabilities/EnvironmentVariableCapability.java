package com.openshift.clientv3.capabilities;

import com.openshift.client.IApplication;
import com.openshift.client.OpenShiftException;
import com.openshift.clientv3.Capability;
import com.openshift.clientv3.Service;
import com.openshift.clientv3.adapters.IApplicationAdapter;

public class EnvironmentVariableCapability implements Capability{
	
	private Service openshiftService;
	
	public EnvironmentVariableCapability(Service service) {
		this.openshiftService = service;
	}

	public void addEnvironmentVariable(String name, String value) throws OpenShiftException {
		//need access to underlying data for the service
		//but we will punt for now
		IApplication app = ((IApplicationAdapter)openshiftService).getApplication();
		app.addEnvironmentVariable(name, value);
	}
	
	public void updateEnvironmentVariable(String name, String value) throws OpenShiftException {
		
	}
	
	public void removeEnvironmentVariable(String targetName) {
		
	}
	
	public String getEnvironmentVariable(String name){
		return null;
	}

}
