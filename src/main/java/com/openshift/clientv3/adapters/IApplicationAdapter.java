package com.openshift.clientv3.adapters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.openshift.client.IApplication;
import com.openshift.clientv3.Capability;
import com.openshift.clientv3.Service;
import com.openshift.clientv3.capabilities.EnvironmentVariableCapability;

public class IApplicationAdapter implements Service{

	private IApplication application;
	private Map<Class<? extends Capability>, Capability> capabilities =  new HashMap<Class<? extends Capability>, Capability>();
	
	public IApplicationAdapter(IApplication application){
		this.application = application;
		loadCapabilities();
	}
	
	public IApplication getApplication(){
		return application;
	}
	
	private void loadCapabilities() {
		// evaluate capabilities based on links and other
		// punt for now
		if(application.canUpdateEnvironmentVariables()){
			capabilities.put(EnvironmentVariableCapability.class, new EnvironmentVariableCapability(this));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<? extends Capability> capability) {
		return (T) capabilities.get(capability);
	}

	@Override
	public String getName() {
		return application.getName();
	}

	@Override
	public Collection<Capability> capabilities() {
		return capabilities.values();
	}

}
