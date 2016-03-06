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
		
		//���ݲ���	
		mMapView = pMapView;

		//��ȡ��ͼ������
		mBaiduMap = mMapView.getMap();
		
		//����ת����
		CoordinateConverter _Converter;
		_Converter = new CoordinateConverter();
		_Converter.from(CoordinateConverter.CoordType.COMMON);
		_Converter.coord(INIT_LOCATION_DATA);
		LatLng _LatLng = _Converter.convert();
		
		//���û�����ͼ
		mBaiduMap.setTrafficEnabled(false);// �ص���ͨͼ
		setMapCenter(_LatLng, INIT_ZOOM);// ���õ�ͼ����	
		//setMapCenter(INIT_LOCATION_DATA, INIT_ZOOM);// ���õ�ͼ����	
		mMapView.showScaleControl(false);// ����ʾ������	
		mMapView.showZoomControls(false);// ����ʾ���ſؼ�
		
		//������λͼ��
		//mBaiduMap.setMyLocationEnabled(true);
				
	}
	
	public void setMapCenter(LatLng pLatLng, int pMapZoom)
	{
		//����ת����
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
