package com.openshift.clientv3;

import java.io.IOException;

import com.openshift.client.IHttpClient;
import com.openshift.client.IOpenShiftConnection;
import com.openshift.client.OpenShiftException;
import com.openshift.clientv3.adapters.IOpenShiftConnectionAdapter;
import com.openshift.internal.client.AbstractOpenShiftConnectionFactory;
import com.openshift.internal.client.IRestService;
import com.openshift.internal.client.RestService;
import com.openshift.internal.client.httpclient.UrlConnectionHttpClientBuilder;
import com.openshift.internal.client.httpclient.request.JsonMediaType;
import com.openshift.internal.client.response.OpenShiftJsonDTOFactory;

public class ConnectionFactory extends AbstractOpenShiftConnectionFactory {
	
	public Connection connect()throws IOException, OpenShiftException {
		return connect(System.getProperty("os.username"),System.getProperty("os.password"));
	}
	public Connection connect(final String login, final String password) throws IOException, OpenShiftException {
		String serverUrl = System.getProperty("libra.server", "https://openshift.redhat.com");
		try {
			IHttpClient httpClient =
					new UrlConnectionHttpClientBuilder()
						.setCredentials(login, password)
						.setConfigTimeout(180000)
						.client();
			IRestService service = new RestService(serverUrl, "v3OpenShiftPrototype", new JsonMediaType(),
					IHttpClient.MEDIATYPE_APPLICATION_JSON, new OpenShiftJsonDTOFactory(), httpClient);
			IOpenShiftConnection connection = getConnection(service, login, password, null);
			return new IOpenShiftConnectionAdapter(connection);
		} catch (IOException e) {
			throw new OpenShiftException(e, "Failed to establish connection for user ''{0}}''", login);
		}	
	}
}
