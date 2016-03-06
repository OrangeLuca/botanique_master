package com.coderlucas.botanique.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

public class MyBaiduMapBasic
{
	private BaiduMap mBaiduMap = null;
	private MapView mMapView = null;

	private double INIT_LATITUDE = 22.538185;
	private double INIT_LONGITUDE = 113.941134;
	private LatLng INIT_LOCATION_DATA = new LatLng(INIT_LATITUDE, INIT_LONGITUDE);
	private int INIT_ZOOM = 20;
	
	public MyBaiduMapBasic(MapView pMapView)
	{
		// TODO Auto-generated constructor stub
		
		//传递参数	
		mMapView = pMapView;

		//获取地图管理器
		mBaiduMap = mMapView.getMap();
		
		//坐标转换器
		CoordinateConverter _Converter;
		_Converter = new CoordinateConverter();
		_Converter.from(CoordinateConverter.CoordType.COMMON);
		_Converter.coord(INIT_LOCATION_DATA);
		LatLng _LatLng = _Converter.convert();
		
		//配置基础地图
		mBaiduMap.setTrafficEnabled(false);// 关掉交通图
		setMapCenter(_LatLng, INIT_ZOOM);// 设置地图中心	
		//setMapCenter(INIT_LOCATION_DATA, INIT_ZOOM);// 设置地图中心	
		mMapView.showScaleControl(false);// 不显示比例尺	
		mMapView.showZoomControls(false);// 不显示缩放控件
		
		//开启定位图层
		//mBaiduMap.setMyLocationEnabled(true);
				
	}
	
	public void setMapCenter(LatLng pLatLng, int pMapZoom)
	{
		//坐标转换器
		CoordinateConverter _Converter;
		_Converter = new CoordinateConverter();
		_Converter.from(CoordinateConverter.CoordType.COMMON);
		_Converter.coord(pLatLng);
		LatLng _LatLng = _Converter.convert();
				
		mBaiduMap.setMyLocationEnabled(true);
		MapStatus _MapStatus = new MapStatus.Builder().target(_LatLng)
				.zoom(pMapZoom).build();
		MapStatusUpdate _MapStatusUpdate = MapStatusUpdateFactory
				.newMapStatus(_MapStatus);
		mBaiduMap.setMapStatus(_MapStatusUpdate);
	}
	
	public BaiduMap getBaiduMap()
	{
		return mBaiduMap;
	}
	
}
