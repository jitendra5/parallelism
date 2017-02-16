package com.mandelbrot.pmp.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.simple.JSONObject;

import com.google.common.io.ByteStreams;
import com.mandelbrot.pmp.client.model.ClientModel;


public class MandelbrotRender implements Runnable{
	
	int iterations;
	ClientModel mValues;
	String serverName;
	JSONObject json= new JSONObject();

	int count;
	MandelbrotRender(ClientModel params, String serverName, int maxIterations, int count){
		this.mValues=params;
		this.serverName=serverName;
		this.iterations=maxIterations;
		this.count=count;
	}

	@Override
	public void run() {
			 String uri;
		final URI hostName = UriBuilder.fromPath("http://{host}")
				.path("/pmp/webapi/mandelbrot")
				.path("/{crMin}/{crMax}/{ciMin}/{ciMax}/{width}/{height}/{iterations}")
			    .resolveTemplate("host", serverName)
			    .resolveTemplate("crMin", mValues.getCrMin())
			    .resolveTemplate("crMax", mValues.getCrMax())
			    .resolveTemplate("ciMin", mValues.getCiMin())
			    .resolveTemplate("ciMax", mValues.getCiMax())
			    .resolveTemplate("width", mValues.getWidth())
			    .resolveTemplate("height", mValues.pictureHeight)
			    .resolveTemplate("iterations", iterations)
			    .build();
			 uri = hostName.toString();
			 System.out.println(uri);
			Client client=ClientBuilder.newClient();
			 Response response = client.target(hostName).request().get();
			 //System.out.println(response.getStatus());
			 InputStream readEntity = response.readEntity(InputStream.class);
		//	 File file = new File("C:/Users/admin/workspace-mandelrot/pmp/src/main/resources/Apioutput"+count+".png");
			 FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream("C:/Users/admin/workspace-mandelrot/pmp/src/main/resources/Apioutput"+count+".png");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			byte[] buffer = new byte[1024];
	            int bytesRead;
	            try {
					while ((bytesRead = readEntity.read(buffer)) != -1) {
					    outputStream.write(buffer, 0, bytesRead);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			 json.put(serverName, response);
			 System.out.println(json);
	}

}
