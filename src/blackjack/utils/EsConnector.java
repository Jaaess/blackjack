package blackjack.utils;

import java.net.InetSocketAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/*
 * Method to connect to elasticsearch cluster named karl on localhost server
 */

public class EsConnector {
	
	public static Client connectToClient() {
		String clusterName = "karl";
		Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
		
		Client client = TransportClient.builder().settings(settings).build()
				.addTransportAddress(new 
						InetSocketTransportAddress(new InetSocketAddress("localhost", 9300)));
		
		return client;
		
	}

}
