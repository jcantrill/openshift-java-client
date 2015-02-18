/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. Distributed under license by Red Hat, Inc.
 * All rights reserved. This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Red Hat, Inc.
 ******************************************************************************/
package com.openshift3.internal.client.model.build;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.jboss.dmr.ModelNode;
import org.junit.BeforeClass;
import org.junit.Test;

import com.openshift.client.utils.Samples;
import com.openshift3.client.IClient;
import com.openshift3.client.ResourceKind;
import com.openshift3.client.images.DockerImageURI;
import com.openshift3.client.model.IBuildConfig;
import com.openshift3.client.model.build.BuildSourceType;
import com.openshift3.client.model.build.BuildStrategyType;
import com.openshift3.client.model.build.BuildTriggerType;
import com.openshift3.client.model.build.IBuildStrategy;
import com.openshift3.client.model.build.IBuildTrigger;
import com.openshift3.client.model.build.IGitBuildSource;
import com.openshift3.client.model.build.ISTIBuildStrategy;
import com.openshift3.internal.client.model.BuildConfig;
import com.openshift3.internal.client.model.ImageChangeTrigger;
import com.openshift3.internal.client.model.build.WebhookTrigger;
import com.openshift3.internal.client.model.properties.ResourcePropertiesRegistry;

public class V1Beta1BuildConfigTest {
	
	private static IBuildConfig config;
	
	@BeforeClass
	public static void setup(){
		IClient client = mock(IClient.class);
		ModelNode node = ModelNode.fromJSONString(Samples.BUILD_CONFIG_MINIMAL.getContentAsString());
		config = new BuildConfig(node, client, ResourcePropertiesRegistry.getInstance().get("v1beta1", ResourceKind.BuildConfig));
	}
	
	@Test
	public void getBuildTriggers(){
		IBuildTrigger [] exp = new IBuildTrigger[]{
				new WebhookTrigger(BuildTriggerType.github, "secret101"),
				new WebhookTrigger(BuildTriggerType.generic, "secret101"),
				new ImageChangeTrigger("openshift/ruby-20-centos", "ruby-20-centos", "latest")
		};
		assertArrayEquals(exp, config.getBuildTriggers().toArray());
	}
	
	@Test
	public void getOutputRespositoryName(){
		assertEquals("origin-ruby-sample",config.getOutputRepositoryName());
	}
	
	@Test
	public void getSourceURI(){
		assertEquals("git@github.com:jcantrill/javaparks.git", config.getSourceURI());
	}
	
	@Test
	public void getGitBuildSource(){
		IGitBuildSource source = config.<IGitBuildSource>getBuildSource();
		assertEquals(BuildSourceType.Git, source.getType());
		assertEquals("git@github.com:jcantrill/javaparks.git", source.getURI());
		assertEquals("Exp. to get the source ref","", source.getRef());
	}
	
	@Test
	public void getSTIBuildStrategy() {
		IBuildStrategy strategy = config.getBuildStrategy();
		assertEquals(BuildStrategyType.STI, strategy.getType());
		ISTIBuildStrategy sti = (ISTIBuildStrategy)strategy;
		assertEquals(new DockerImageURI("openshift/wildfly-8-centos:latest"), sti.getImage());
		assertEquals("alocation", sti.getScriptsLocation());
		assertTrue(sti.forceClean());
		assertEquals(1, sti.getEnvironmentVariables().size());
		assertTrue("Exp. to find the environment variable",sti.getEnvironmentVariables().containsKey("foo"));
		assertEquals("bar",sti.getEnvironmentVariables().get("foo"));
	}

}
