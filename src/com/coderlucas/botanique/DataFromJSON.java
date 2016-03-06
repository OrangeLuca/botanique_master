package com.coderlucas.botanique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataFromJSON implements Serializable
{
	private static final long serialVersionUID = -758459502806858414L;  

	//ֲ��������
	private String mId;
	
	//��γ��
	private double latitude;  
	private double longitude;  

	//ͼƬID����ʵ��Ŀ�п�����ͼƬ·��   
	private int imgId;  
	private String imgPath; 
	//ֲ������
	private String name;  
	 
	//����
	private String distance;  

	//������
	private int zan;  
	
	public static List<DataFromJSON> infos = new ArrayList<DataFromJSON>();  

	static  
	{ 
		infos.add(new DataFromJSON("SZ10000001", 22.530892, 113.931781, R.drawable.lizhi, "url.image1", "��֦", "����5��", 1456));  
		infos.add(new DataFromJSON("SZ10000002", 22.530802, 113.931801, R.drawable.longyan, "url.image2","����", "����3��", 456));  
		infos.add(new DataFromJSON("SZ10000003", 22.530792, 113.931991, R.drawable.diaodengshu, "url.image3","������",  "����2��", 1456));  
		infos.add(new DataFromJSON("SZ10000004", 22.535932, 113.933831, R.drawable.jiabinglang, "url.image4","������",  "����6��", 1456));  
	}  
	
	public DataFromJSON()  
	{  
	}  
	
	public DataFromJSON(String mId, double latitude, double longitude, int imgId, String imgPath,
			String name, String distance, int zan)
	{
		super();
		this.mId = mId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.imgId = imgId;
		this.imgPath = imgPath;
		this.name = name;
		this.distance = distance;
		this.zan = zan;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public String getName()
	{
		return name;
	}

	public String getClassifyId()
	{
		return mId;
	}
	
	public int getImgId()
	{
		return imgId;
	}
	
	public String getImgPath()
	{
		return imgPath;
	}

	public void setImgId(int imgId)
	{
		this.imgId = imgId;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDistance()
	{
		return distance;
	}

	public void setDistance(String distance)
	{
		this.distance = distance;
	}

	public int getZan()
	{
		return zan;
	}

	public void setZan(int zan)
	{
		this.zan = zan;
	}


}
