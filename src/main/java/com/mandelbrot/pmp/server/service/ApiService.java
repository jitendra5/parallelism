package com.mandelbrot.pmp.server.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ApiService {
	public byte[] mandelbrot(double crMin,double crMax,double ciMin,double ciMax,int width, int height, int iterations) throws IOException{
		final double zoom=500;   
		int black = 0x000000, white = 0xFFFFFF;
		System.out.println("crmin max:"+crMin+crMax);
		BufferedImage img= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
    	 for (int y = 0; y < height; y++) {
             for (int x = 0; x < width; x++) {
                double zx=0, zy = 0; double tmp;
                double cX = (x - width/2) / zoom;
                double cY = (y - height/2) / zoom;
                 
          	   int iter = iterations;
          	 if((cX>=crMin)&&(cX<=crMax)&&(cY>=ciMin)&&(cY<=ciMax)){
                 while (zx * zx + zy * zy < 4 && iter > 0) {
                     tmp = zx * zx - zy * zy + cX;
                     zy = 2.0 * zx * zy + cY;
                     zx = tmp;
                     iter--;
                 }
          	 }
          	 if (iter >0) img.setRGB(x, y, black);
             else img.setRGB(x, y, white);
         }
     }
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 ImageIO.write( img, "png", baos );
		 byte[] imageInByte = baos.toByteArray();
    	 return imageInByte;
	}

}
