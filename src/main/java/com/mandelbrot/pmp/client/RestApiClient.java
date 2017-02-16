package com.mandelbrot.pmp.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.mandelbrot.pmp.client.model.ClientModel;


public class RestApiClient {

	public static void main(String[] args) {
		Client client=ClientBuilder.newClient();
        double xCenter = -0.38;
        double yCenter = 0;
        int count=1;
		System.out.println("Enter the data ciMin, ciMax, crMin, crMax, iterations, width, height, divisions,list of servers");
		Scanner sc=new Scanner(System.in);
		String input = sc.nextLine();
		String[] parameters = input.split(" ");
		double crMin=Double.parseDouble(parameters[0]);
		double crMax=Double.parseDouble(parameters[1]);
		double ciMin=Double.parseDouble(parameters[2]);
		double ciMax=Double.parseDouble(parameters[3]);
		System.out.println("ciMax:"+ciMax);
		int maxIterations=Integer.parseInt(parameters[4]);
		int width=Integer.parseInt(parameters[5]);
		int height=Integer.parseInt(parameters[6]);
		int divisions=Integer.parseInt(parameters[7]);
		System.out.println("divisions:"+divisions);
		int parts=divisions;
		int subPicHeight=height/divisions;
		//rendering the medlebrot based on height keeping the width constant
		double render=(crMax-crMin)/divisions;
		System.out.println("width:"+width);
		System.out.println("height:"+height);
		//list of servers
		List<String> serversList= new ArrayList<>();
		for(int i=8;i<parameters.length;i++){
			serversList.add(parameters[i]);
			//System.out.println(serversList.get(j));
		}
		ClientModel model[]=new ClientModel[divisions];
        // For every sub picture in the array...
        for (int j = 0; (j < divisions); j++) {
        	crMax=crMin+render;
            // Create the model
        	model[j] = new ClientModel(width, height, xCenter, yCenter,crMin,crMax,ciMin,ciMax);
            // Update the height
        	crMin=crMax;
        }
        for(int i=0;i<divisions;i++){
        	System.out.println(
        	model[i].getCiMin()+" "+
        	model[i].getCiMax()+" "+
        	model[i].getCrMin()+" "+
        	model[i].getCrMax()+" "+
        	model[i].getPictureHeight()+" "+
        	model[i].getWidth()+" "+
        	model[i].getxCenter()+" "+
        	model[i].getyCenter());
        }
        Thread[] threads = new Thread[divisions];
		if(serversList.size()>=divisions){
			for(int i=0;i<divisions;i++){
				threads[i]=new Thread(new MandelbrotRender(model[i],serversList.get(i),maxIterations,count));
				count++;
			}
		}
		else{
			int j=0;
			while(parts>=0){
			for(int i=0;i<serversList.size()&&j<divisions;i++,j++){
				threads[j]=new Thread(new MandelbrotRender(model[j],serversList.get(i),maxIterations,count));
				count++;
			}
			parts=parts-serversList.size();
			}
		}
        
      for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            }
            catch (Exception e) {
            	System.out.println(e);
            }
        }
		
	}
	}
