package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import beans.ServerRequest;
import beans.ServerResponse;

public class TransceiverModule {

	public ServerResponse serviceRequest (ServerRequest serviceRequest){
		ServerResponse serverResponse = null ;

		try {
			URL serviceURL = new URL(serviceRequest.getHostLocation().getIpAddress()+":"+
					serviceRequest.getHostLocation().getPortNumber());

			HttpURLConnection conn = (HttpURLConnection) serviceURL.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) 
				System.out.println(inputLine);

			serverResponse = new ServerResponse(serviceRequest.getTimeOfRequest(), System.currentTimeMillis(), conn.getResponseCode());
			in.close();

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL Exception message " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return serverResponse;
	}

}
