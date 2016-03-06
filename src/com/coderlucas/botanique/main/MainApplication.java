package com.coderlucas.botanique.main;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * @ClassName: MainApplication
 * @Description: Initiialize BaiduMap SDK
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月6日 下午2:36:24
 * 
 */
public class MainApplication extends Application
{

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();

		// 初始化百度地图
		SDKInitializer.initialize(getApplicationContext());
	}

}
