package com.openshift.clientv3;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.openshift.client.OpenShiftException;
import com.openshift.clientv3.capabilities.EnvironmentVariableCapability;

public class PrototypeTest {

	/**
	 * Demonstrate how env variable capabilities could by
	 * enabled dynamically
	 */
	@Test
	public void test() throws OpenShiftException, IOException {
		
		//connection made
		//explorer window is loaded
		//applications are expanded
		Connection connection = new ConnectionFactory().connect();
		Project project = connection.getProjects().get(0);
		Service service = project.getServices().get(0);
		
		//right click application to see list of things i can do
		//presume we could dynamically update the menu items based
		//on the eclipse registrations and a class that can evaluate
		//to display items(e.g. edit variables) or not.  That code might
		//do something like the following given it should know what its
		//looking for
		MenuBuilder builder = new MenuBuilder(service);
		
		//action method from clicking might trigger:
		builder.onClickHandler();
	}
	
	class MenuBuilder{
		
		private EnvironmentVariableCapability envCapability;
		
		MenuBuilder(Service service){
			addEnvironmentVariableCapability(service.<EnvironmentVariableCapability>get(EnvironmentVariableCapability.class));
			//add other capability here
		}
		
		private void addEnvironmentVariableCapability(EnvironmentVariableCapability capability){
			if(capability == null) return;
			envCapability = capability;
			//add wigit
			//register onclick handler
		}
		
		void onClickHandler(){
			envCapability.addEnvironmentVariable("foo", "bar");
		}
	}

}
