package com.openshift.clientv3;

import java.util.Collection;

public interface Capable{
	
	<T> T get(Class<? extends Capability> capability);
	
	/**
	 * List of capabilities
	 * @return
	 */
	Collection<Capability> capabilities();
}
