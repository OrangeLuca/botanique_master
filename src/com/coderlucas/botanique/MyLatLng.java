package com.coderlucas.botanique;

import java.io.Serializable;

public class MyLatLng implements Serializable
{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private double mLantitude;
	private double mLongtitude;
	
	public MyLatLng(double lan, double lon)
	{
		// TODO Auto-generated constructor stub
		mLantitude = lan;
		mLongtitude = lon;
	}
	
	public double getLantitude()
	{
		return mLantitude;
	}
	
	public double getLongtitude()
	{
		return mLongtitude;
	}
}
