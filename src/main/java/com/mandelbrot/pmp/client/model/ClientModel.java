package com.mandelbrot.pmp.client.model;

public class ClientModel {
	public int width;
    public int pictureHeight;
    public double xCenter;
    public double yCenter;
    public double crMin;
    public double crMax;
    public double ciMin;
    private double ciMax;
    
	public ClientModel() {
		super();
	}

	

	public ClientModel(int width, int pictureHeight, double xCenter, double yCenter, double crMin,
			double crMax, double ciMin, double ciMax) {
		super();
		this.width = width;
		this.pictureHeight = pictureHeight;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.crMin = crMin;
		this.crMax = crMax;
		this.ciMin = ciMin;
		this.ciMax = ciMax;
	}



	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getPictureHeight() {
		return pictureHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		this.pictureHeight = pictureHeight;
	}

	public double getxCenter() {
		return xCenter;
	}

	public void setxCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public double getyCenter() {
		return yCenter;
	}

	public void setyCenter(double yCenter) {
		this.yCenter = yCenter;
	}

	public double getCrMin() {
		return crMin;
	}

	public void setCrMin(double crMin) {
		this.crMin = crMin;
	}

	public double getCrMax() {
		return crMax;
	}

	public void setCrMax(double crMax) {
		this.crMax = crMax;
	}

	public double getCiMin() {
		return ciMin;
	}

	public void setCiMin(double ciMin) {
		this.ciMin = ciMin;
	}

	public double getCiMax() {
		return ciMax;
	}

	public void setCiMax(double ciMax) {
		this.ciMax = ciMax;
	}
	
    
}
