package com.mandelbrot.pmp.server.model;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ApiModel {
@PathParam("crMin")
private double crMin;
@PathParam("ciMin")
private double ciMin;
@PathParam("crMax")
private double crMax;
@PathParam("ciMax")
private double ciMax;
@PathParam("x")
private int x;
@PathParam("y")
private int y;
@PathParam("itr")
private int itr;
public ApiModel() {
	super();
}
public ApiModel(double crMin, double ciMin, double crMax, double ciMax, int x, int y, int itr) {
	super();
	this.crMin = crMin;
	this.ciMin = ciMin;
	this.crMax = crMax;
	this.ciMax = ciMax;
	this.x = x;
	this.y = y;
	this.itr = itr;
}
public double getCrMin() {
	return crMin;
}
public void setCrMin(double crMin) {
	this.crMin = crMin;
}
public double getCiMin() {
	return ciMin;
}
public void setCiMin(double ciMin) {
	this.ciMin = ciMin;
}
public double getCrMax() {
	return crMax;
}
public void setCrMax(double crMax) {
	this.crMax = crMax;
}
public double getCiMax() {
	return ciMax;
}
public void setCiMax(double ciMax) {
	this.ciMax = ciMax;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getItr() {
	return itr;
}
public void setItr(int itr) {
	this.itr = itr;
}


}
