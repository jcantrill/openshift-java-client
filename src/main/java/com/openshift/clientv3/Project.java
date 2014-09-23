package com.openshift.clientv3;

import java.util.List;

/**
 * A collection of OpenShift resources
 *
 */
public interface Project {

	List<Service> getServices();
}
