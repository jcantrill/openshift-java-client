/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package com.openshift.client;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.openshift.client.cartridge.ICartridge;
import com.openshift.client.cartridge.IEmbeddableCartridge;
import com.openshift.client.cartridge.IEmbeddedCartridge;
import com.openshift.client.cartridge.IStandaloneCartridge;
import com.openshift.internal.client.ApplicationResource;

/**
 * @author André Dietisheim
 * @author Syed Iqbal
 * @author Martes G Wigglesworth
 */
public interface IApplication extends IOpenShiftResource {

	/**
	 * Returns the name of this application.
	 *
	 * @return
	 */
	public String getName();

	/**
	 * Returns the uuid of this application.
	 *
	 * @return the uuid of this application.
	 */
	public String getUUID();

	/**
	 * Returns the uri at which the git repository of this application may be
	 * reached at.
	 *
	 * @return the uri of the git repo of this application.
	 */
	public String getGitUrl();

	/**
	 * Returns the url to use to connect with ssh.
	 *
	 * @return the url to use to connect with ssh.
	 */
	public String getSshUrl();

	/**
	 * Returns the git url that the application will get its initial code and
	 * configuration from.
	 *
	 * @return the initial git url
	 */
	public String getInitialGitUrl();
	
	/**
	 * Returns the deployment type for this application. Either "binary" or "git" currently.
	 * @return "binary" or "git"
	 */
	public String getDeploymentType();

	/**
	 * Returns the url at which this application may be reached at.
	 *
	 * @return the url of this application.
	 */
	public String getApplicationUrl();

	/**
	 * Returns true if scaling is enabled on this application (only set at
	 * creation time).
	 *
	 * @return true if scaling is enabled on this application (only set at
	 *         creation time).
	 */
	public ApplicationScale getApplicationScale();

	/**
	 * Returns true if scaling is enabled on this application (only set at
	 * creation time).
	 *
	 * @return true if scaling is enabled on this application (only set at
	 *         creation time).
	 */
	public IGearProfile getGearProfile();

	/**
	 * Returns the cartridge (application type) that this app is running on.
	 *
	 * @return the cartridge of this application
	 *
	 */
	public IStandaloneCartridge getCartridge();

	/**
	 * Adds the given embeddable cartridge to this application.
	 *
	 * @param cartridge
	 *            the cartridge that shall be added
	 * @throws OpenShiftException
	 */
	public IEmbeddedCartridge addEmbeddableCartridge(ICartridge cartridge) throws OpenShiftException;

	/**
	 * Adds the given embeddable cartridges to this application.
	 *
	 * @param cartridges
	 *            the cartridges that shall be added
	 * @throws OpenShiftException
	 */
	public List<IEmbeddedCartridge> addEmbeddableCartridges(ICartridge... cartridges) throws OpenShiftException;

	/**
	 * Adds all given embedded cartridges from this app, given their names.
	 *
	 * @param embeddedCartridges
	 * @throws OpenShiftException
	 *
	 * @see #addEmbeddableCartridge(IEmbeddedCartridge)
	 * @see #removeEmbeddedCartridge(IEmbeddedCartridge)
	 */
	public List<IEmbeddedCartridge> addEmbeddableCartridges(Collection<IEmbeddableCartridge> cartridge)
			throws OpenShiftException;

	/**
	 * Returns all embedded cartridges.
	 *
	 * @return all embedded cartridges.
	 * @throws OpenShiftException
	 *
	 * @see IEmbeddedCartridge
	 * @see #addEmbeddableCartridge(IEmbeddedCartridge)
	 * @see #removeEmbeddedCartridge(IEmbeddedCartridge)
	 */
	public List<IEmbeddedCartridge> getEmbeddedCartridges() throws OpenShiftException;

	/**
	 * Returns <code>true</code> if this application has an embedded cartridge.
	 * Returns <code>false</code> otherwise.
	 *
	 * @param the
	 *            name of the cartridge to look for
	 * @return true if there's an embedded cartridge with the given name
	 * @throws OpenShiftException
	 *
	 * @see IEmbeddedCartridge
	 * @see #addEmbeddableCartridge(IEmbeddedCartridge)
	 * @see #removeEmbeddedCartridge(IEmbeddedCartridge)
	 */
	public boolean hasEmbeddedCartridge(IEmbeddableCartridge cartridge) throws OpenShiftException;

	/**
	 * Returns <code>true</code> if this application has an embedded cartridge.
	 * Returns <code>false</code> otherwise.
	 *
	 * @param the
	 *            name of the cartridge to look for
	 * @return true if there's an embedded cartridge with the given name
	 * @throws OpenShiftException
	 *
	 * @see IEmbeddedCartridge
	 * @see #addEmbeddableCartridge(IEmbeddedCartridge)
	 * @see #removeEmbeddedCartridge(IEmbeddedCartridge)
	 */
	public boolean hasEmbeddedCartridge(String cartridgeName) throws OpenShiftException;

	/**
	 * Returns the embedded cartridge given its name. Returns <code>null</code>
	 * if none was found.
	 *
	 * @param cartridgeName
	 * @return the embedded cartridge with the given name
	 * @throws OpenShiftException
	 */
	public IEmbeddedCartridge getEmbeddedCartridge(String cartridgeName)
			throws OpenShiftException;

	/**
	 * Returns the embedded cartridge in this application. Returns
	 * <code>null</code> if none was found.
	 *
	 * @param cartridge
	 * @return the embedded cartridge
	 * @throws OpenShiftException
	 */

	public IEmbeddedCartridge getEmbeddedCartridge(IEmbeddableCartridge cartridge)
			throws OpenShiftException;

	/**
	 * Removes the given embedded cartridge that is equal to the given
	 * embeddable cartridge. Does nothing if the cartridge is not present in
	 * this application.
	 *
	 * @param cartridge
	 *            the cartridge that shall be removed
	 * @throws OpenShiftException
	 */
	public void removeEmbeddedCartridge(IEmbeddableCartridge cartridge) throws OpenShiftException;

	/**
	 * Removes the given embedded cartridges in this application that are equal
	 * to the given IEmbeddableCartridge. Does nothing if the cartridge is not
	 * present in this application.
	 *
	 * @param cartridges
	 *            the cartridges that shall get removed
	 * @throws OpenShiftException
	 */
	public void removeEmbeddedCartridges(Collection<IEmbeddableCartridge> cartridges) throws OpenShiftException;

	/**
	 * Returns the gear groups for this application. The collection is never
	 * cached, so each call will trigger a request to the OpenShift Broker.
	 *
	 * @return the collection of {@link IGearGroup} for this application.
	 * @throws OpenShiftException
	 */
	public Collection<IGearGroup> getGearGroups() throws OpenShiftException;

	/**
	 * Returns the timestamp at which this app was created.
	 *
	 * @return the creation time
	 *
	 * @throws OpenShiftException
	 */
	public Date getCreationTime();

	/**
	 * Destroys this application (and removes it from the list of available
	 * applications)
	 *
	 * @throws OpenShiftException
	 *
	 * @see IUser#getApplications()
	 */
	public void destroy() throws OpenShiftException;

	/**
	 * Starts this application. Has no effect if this app is already running.
	 *
	 * @throws OpenShiftException
	 */
	public void start() throws OpenShiftException;

	/**
	 * Restarts this application.
	 *
	 * @throws OpenShiftException
	 */
	public void restart() throws OpenShiftException;

	/**
	 * Stops this application.
	 *
	 * @throws OpenShiftException
	 */
	public void stop() throws OpenShiftException;

	/**
	 * Stops this application
	 *
	 * @param force
	 *            : true to force stop, false otherwise
	 *
	 * @throws OpenShiftException
	 */
	public void stop(boolean force) throws OpenShiftException;

	/**
	 * Waits for this application to become accessible on its public url.
	 *
	 * @param timeout
	 * @return
	 * @throws OpenShiftException
	 *
	 * @see IApplication#getApplicationUrl()
	 */
	public boolean waitForAccessible(long timeout) throws OpenShiftException;

	/**
	 * Returns a Future that the caller can use to wait for the application to
	 * become accessible on its public url.
	 *
	 * @param timeout
	 * @return
	 * @throws OpenShiftException
	 *
	 * @see IApplication#getApplicationUrl()
	 * @see IApplication#waitForAccessible(long)
	 * @see IOpenShiftConnection#getExecutorService()
	 * @see Future
	 */
	public Future<Boolean> waitForAccessibleAsync(final long timeout) throws OpenShiftException;

	/**
	 * Get the domain of the application.
	 *
	 * @return the domain
	 */
	public IDomain getDomain();

	/**
	 * Scale down application
	 *
	 * @throws OpenShiftException
	 */
	public void scaleDown() throws OpenShiftException;

	/**
	 * Scale up application
	 *
	 * @throws OpenShiftException
	 */
	public void scaleUp() throws OpenShiftException;

	/**
	 * Sets the deployment type for this application. Can be either "binary" or
	 * "git" currently.
	 * 
	 * @param deploymentType
	 * @return the deployment type that was set
	 */
	public String setDeploymentType(String deploymentType);

	/**
	 * Add application alias
	 *
	 * @throws OpenShiftException
	 */
	public void addAlias(String string) throws OpenShiftException;

	/**
	 * Retrieve all application aliases
	 *
	 * @return application aliases in an unmodifiable collection
	 */
	public List<String> getAliases();

	public boolean hasAlias(String name);

	/**
	 * Remove application alias
	 *
	 * @throws OpenShiftException
	 */
	public void removeAlias(String alias) throws OpenShiftException;

	/**
	 * Refresh the application but reloading its content from OpenShift. At the
	 * same time, this operation automatically set the embedded cartridges back
	 * to an 'unloaded' state.
	 *
	 * @throws OpenShiftException
	 */
	public void refresh() throws OpenShiftException;

	/**
	 * Sets the SSH session that this application will use to connect to
	 * OpenShift to perform some operations. This SSH session must be
	 * initialized out of the library, since the user's SSH settings may depend
	 * on the runtime environment (Eclipse, etc.).
	 *
	 * @param session
	 *            the SSH session
	 *
	 * @deprecated use
	 *             {@link IApplicationSSHSession#setSSHSession(com.jcraft.jsch.Session)}
	 */
	public void setSSHSession(Session session);

	/**
	 * Returns the SSH session that this application uses to connect to
	 * OpenShift.
	 *
	 * @return the SSH session that this application uses to connect to
	 *         OpenShift.
	 * @deprecated use {@link com.openshift.client.IApplicationSSHSession}
	 */
	public Session getSSHSession();

	/**
	 * Returns true if the application was already provided with an SSH session,
	 * and this session is still valid (connected).
	 *
	 * @return true if the application was already provided with an SSH session,
	 *         and this session is still valid (connected).
	 */
	public boolean hasSSHSession();

	/**
	 * Returns true if the port-forwarding has been started, false otherwise.
	 *
	 * @return true if the port-forwarding has been started, false otherwise.
	 * @throws OpenShiftSSHOperationException
	 *
	 * @deprecated use {@link IApplicationSSHSession#isPortFowardingStarted()}
	 */
	public boolean isPortFowardingStarted() throws OpenShiftSSHOperationException;

	/**
	 * Returns the list of forwardable ports on OpenShift for this application.
	 *
	 * @return the list of forwardable ports on OpenShift for this application.
	 * @throws OpenShiftSSHOperationException
	 * @deprecated use {@link IApplicationSSHSession#getForwardablePorts()}
	 */
	public List<IApplicationPortForwarding> getForwardablePorts() throws OpenShiftSSHOperationException;

	/**
	 * @ * Starts the port-forwarding for all ports.
	 *
	 * @return the list of forwardable ports on OpenShift for this application.
	 * @throws JSchException
	 *
	 * @deprecated use {@link IApplicationSSHSession#startPortForwarding()}
	 */
	public List<IApplicationPortForwarding> startPortForwarding() throws OpenShiftSSHOperationException;

	/**
	 * Stop the port-forwarding for all ports.
	 *
	 * @return the list of forwardable ports on OpenShift for this application.
	 * @throws OpenShiftSSHOperationException
	 *
	 * @deprecated use {@link IApplicationSSHSession#stopPortForwarding()}
	 */
	public List<IApplicationPortForwarding> stopPortForwarding() throws OpenShiftSSHOperationException;

	/**
	 * Refreshes the list of port-forwarding. Started ones are kept as-is.
	 *
	 * @return the list of forwardable ports on OpenShift for this application.
	 * @throws OpenShiftSSHOperationException
	 *
	 * @deprecated use {@link IApplicationSSHSession#refreshForwardablePorts()}
	 */
	public List<IApplicationPortForwarding> refreshForwardablePorts() throws OpenShiftSSHOperationException;

	/**
	 * Retrieves the list of environment properties.
	 *
	 * @return the list of environment properties.
	 * @throws OpenShiftSSHOperationException
	 *
	 * @deprecated use {@link IApplicationSSHSession#getEnvironmentProperties()}
	 */
	public List<String> getEnvironmentProperties() throws OpenShiftSSHOperationException;

	/**
	 * Retrieves the map of environment variables
	 *
	 * @return the Map<String,IEnvironmentVariable> of environment variables
	 * @throws OpenShiftSSHOperationException
	 */
	public Map<String, IEnvironmentVariable> getEnvironmentVariables() throws OpenShiftSSHOperationException;

	/**
	 * Checks if the environment variable is present in the application.
	 *
	 * @param name
	 *            Name of the environment variable
	 * @return <code>true</code> if the current instance has
	 *         IEnvironmentVariables to return <br>
	 *         <code>false</code> if the current instance has no
	 *         IEnvironmentVariables to return
	 * @throws OpenShiftSSHOperationException
	 */
	public boolean hasEnvironmentVariable(String name) throws OpenShiftException;

	/**
	 * Adds an environment variable to this application.
	 *
	 * @param name
	 *            key associated with the variable to add
	 * @param value
	 *            value of the new variable
	 * @throws OpenShiftSSHOperationException
	 *             - if the variable already exists
	 */
	public IEnvironmentVariable addEnvironmentVariable(String name, String value) throws OpenShiftException;

	/**
	 * Updates an environment variable to this application.
	 *
	 * @param name
	 *            key associated with the variable to update
	 * @param value
	 *            value of the new variable
	 * @throws OpenShiftSSHOperationException
	 *             - if the variable already exists
	 */
	public IEnvironmentVariable updateEnvironmentVariable(String name, String value) throws OpenShiftException;

	/**
	 * Adds a map of environment variables to the application
	 *
	 * @param environmentVariables
	 *            Map<String,String> of environment variables
	 * @throws OpenShiftSSHOperationException
	 */
	public Map<String, IEnvironmentVariable> addEnvironmentVariables(Map<String, String> environmentVariables)
			throws OpenShiftException;

	/**
	 * Return the environment variable for the specified name
	 *
	 * @param name
	 *            key to be used to locate the environment variable
	 * @return IEnvironmentVariable associated with the provided key
	 * @throws OpenShiftSSHOperationException
	 *             - thrown if the key does not exist
	 */
	public IEnvironmentVariable getEnvironmentVariable(String name) throws OpenShiftException;

	/**
	 * Return the environment variables value for the specified name
	 *
	 * @param name
	 *            key to be used to locate the environment variable
	 * @return String value associated with the provided key
	 * @throws OpenShiftSSHOperationException
	 *             - thrown if the key does not exist
	 */
	public String getEnvironmentVariableValue(String name);

	/**
	 * Removes the environment variables with the given name from this
	 * application.
	 *
	 * @param name
	 *            key associated with the IEnvironmentVariable to be removed
	 * @return
	 * @throws OpenShiftException
	 *             - thrown if there is no IEnvironmentVariable associated with
	 *             the explicit parameter.
	 */
	public void removeEnvironmentVariable(String name) throws OpenShiftException;

	/**
	 * Removes the environment variables with the given name from this
	 * application.
	 *
	 * @param environmentVariable
	 *            IEnvironmentVariable instance which should be removed
	 * @return
	 * @throws OpenShiftException
	 *             - thrown if there is no instance of IEnvironmentVariable
	 *             available to be removed
	 */
	public void removeEnvironmentVariable(IEnvironmentVariable environmentVariable);

	/**
	 * Used to determine if environment variables exist and are available to be
	 * retrieved
	 *
	 * @return Returns <code>true</code> if this application can list its
	 *         environment variables. <br>
	 *         Returns <code>false</code> if it cannot. Internally this
	 *         translates to the presence of the link to list environment
	 *         variables.
	 *
	 * @see #getEnvironmentVariablesMap()
	 * @see #getEnvironmentVariable(String)
	 * @see ApplicationResource#LINK_LIST_ENVIRONMENT_VARIABLES
	 */
	public boolean canGetEnvironmentVariables();

	/**
	 * Used to determine if the current instance is able to update environment
	 * variables.
	 *
	 * @return Returns <code>true</code> if this application can augment its
	 *         environment variables.<br>
	 *         Returns <code>false</code> if it cannot. <br>
	 *
	 * @see #addEnvironmentVariable(String, String)
	 * @see #addEnvironmentVariables(Map)
	 * @see ApplicationResource#LINK_SET_UNSET_ENVIRONMENT_VARIABLES
	 */
	public boolean canUpdateEnvironmentVariables();

}