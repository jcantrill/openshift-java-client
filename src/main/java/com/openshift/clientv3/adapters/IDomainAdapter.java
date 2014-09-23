package com.openshift.clientv3.adapters;

import java.util.ArrayList;
import java.util.List;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.clientv3.Project;
import com.openshift.clientv3.Service;

public class IDomainAdapter implements Project{

	private IDomain domain;
	private List<Service> services;

	public IDomainAdapter(IDomain domain) {
		this.domain = domain;
	}

	@Override
	public List<Service> getServices() {
		if(services == null){
			List<IApplication> applications = domain.getApplications();
			services = new ArrayList<Service>(applications.size());
			for (IApplication application : applications) {
				services.add(new IApplicationAdapter(application));
			}
		}
		return services;
	}
	
}