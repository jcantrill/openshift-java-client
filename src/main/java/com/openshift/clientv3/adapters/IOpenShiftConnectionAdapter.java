package com.openshift.clientv3.adapters;

import java.util.ArrayList;
import java.util.List;

import com.openshift.client.IDomain;
import com.openshift.client.IOpenShiftConnection;
import com.openshift.clientv3.Connection;
import com.openshift.clientv3.Project;

public class IOpenShiftConnectionAdapter implements Connection{

	private IOpenShiftConnection connection;
	private List<Project> projects;

	public IOpenShiftConnectionAdapter(IOpenShiftConnection connection) {
		this.connection = connection;
	}

	@Override
	public List<Project> getProjects() {
		if(projects == null){
			List<IDomain> domains = connection.getDomains();
			projects = new ArrayList<Project>(domains.size());
			for (IDomain domain : domains) {
				projects.add(new IDomainAdapter(domain));
			}
		}
		return projects;
	}
	
}