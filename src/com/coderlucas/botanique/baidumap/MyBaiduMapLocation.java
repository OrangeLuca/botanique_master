package com.coderlucas.botanique.baidumap;

import android.util.Log;

import com.coderlucas.botanique.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

public class MyBaiduMapLocation
{
	// 定位客户端
	private LocationClient mLocationClient;
	// 定位监听器
	public MyLocationListener mMyLocationListener;
	// 当前定位的模式
	private LocationMode mCurrentMode = LocationMode.NORMAL;
	// 高精度模式
	// private LocationMode tempMode = LocationMode.Device_Sensors;
	// private LocationMode tempMode = LocationMode.Battery_Saving;
	// private com.baidu.location.LocationClientOption.LocationMode
	// mCurrentModeL =
	// com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy;
	// 国测局加密经纬度坐标
	private String mLocateTempcoor = "gcj02";
	// 百度加密经纬度坐标
	// private String tempcoor="bd09ll";
	// 定位时间间隔1000ms
	private int mLocateFrequence = 1000;
	// 定位信息
	private StringBuffer mStringBuffer = new StringBuffer(256);
	// 经纬度
	private LatLng mLocationLatLng = null;
	// 方向
	private double mLocationDirect;
	// 精度
	private float mLocationAccracy;
	// 单次定位
	private boolean mIsSingleLocation = false;

	private BaiduMap mBaiduMap = null;
	
	private MapView mMapView = null;

	private MyLocationListener mListener = null;

	private Marker mMyLocationMarker = null;
	
	public MyBaiduMapLocation(LocationClient pLocationClient, BaiduMap pBaiduMap, MapView pMapView)
	{
		// 获取定位客户端
		mLocationClient = pLocationClient;
		mBaiduMap = pBaiduMap;
		mMapView = pMapView;
		
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		Log.i("Class:MyLocationListener",
				"MyBaiduMapLocation: setMyLocationEnabled : " + mBaiduMap.isMyLocationEnabled());
		
		// 注册监听函数
		mListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mListener);

		// 定位初始化
		initMyLocation();
	}

	// 初始化
	private void initMyLocation()
	{
		// 定位初始化
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		// 设置定位的相关配置
		LocationClientOption _LocationClientOption = new LocationClientOption();
		// 设置定位模式
		// _LocationClientOption.setLocationMode(mCurrentModeL);
		_LocationClientOption.setOpenGps(true);// 打开GPS
		_LocationClientOption.setCoorType(mLocateTempcoor); // 设置坐标类型 国家测绘局
		_LocationClientOption.setScanSpan(mLocateFrequence); // 定位时间间隔
		// 反地理编码
		_LocationClientOption.setIsNeedAddress(true);
		mLocationClient.setLocOption(_LocationClientOption);
	}

	public class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation location)
		{
			// TODO Auto-generated method stub
			if (location == null)
			{
				Log.i("Class:MyLocationListener",
						"Method:onReceiveLocation(BDLocation) : param is null");
				return;
			}

			// 获取定位信息
			mStringBuffer.append("time:");
			mStringBuffer.append(location.getTime());
			mStringBuffer.append("\nerror code:");
			mStringBuffer.append(location.getLocType());
			mStringBuffer.append("\nlatitude:");
			mStringBuffer.append(location.getLatitude());
			mStringBuffer.append("\nlongtitude:");
			mStringBuffer.append(location.getLongitude());
			mStringBuffer.append("\nradius:");
			mStringBuffer.append(location.getRadius());

			// 添加GPS或NewWork定位参数
			if (location.getLocType() == BDLocation.TypeGpsLocation)
			{
				mStringBuffer.append("\nspeed:");
				mStringBuffer.append(location.getSpeed());
				mStringBuffer.append("\nsatelite:");
				mStringBuffer.append(location.getSatelliteNumber());
			}
			else if (location.getLocType() == BDLocation.TypeNetWorkLocation)
			{
				mStringBuffer.append("naddr:");
				mStringBuffer.append(location.getAddrStr());
			}

			mStringBuffer.append("\nsdk version:");
			mStringBuffer.append(mLocationClient.getVersion());
			mStringBuffer.append("\nisCellChangeFlag:");
			mStringBuffer.append(location.isCellChangeFlag());

			// 获取定位精度
			mLocationAccracy = location.getRadius();
			// 获得定位方向
			mLocationDirect = location.getDirection();
			// 获取定位经纬度
			mLocationLatLng = null;
			mLocationLatLng = new LatLng(location.getLatitude(),
					location.getLongitude());
			Log.i("Class:MyLocationListener", "mLocationLatLng = "
					+ mLocationLatLng.toString());
			// 单次定位设置
			if (mLocationLatLng != null)
			{
				Log.i("Class:MyLocationListener",
						"Method:onReceiveLocation(BDLocation) : mLocationLatLng is not null");
				if (mIsSingleLocation)
				{
					Log.i("Class:MyLocationListener", "mIsSingleLocation="
							+ mIsSingleLocation);

					mLocationClient.stop();

					try
					{
						Thread.currentThread();
						Thread.sleep(5);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					showMyLocationOnBaiduMap(20);
					Log.i("Class:MyLocationListener",
							"Method:onReceiveLocation(BDLocation) : mLocationClient stop");
				}
				else
				{
					Log.i("Class:MyLocationListener", "mIsSingleLocation="
							+ mIsSingleLocation);
				}
			}

		}

	}
	
	public void setMyLocationIcon()
	{
		BitmapDescriptor _MarkBitmapMypoi;
		_MarkBitmapMypoi = BitmapDescriptorFactory
				.fromResource(R.drawable.locate_mark_mypoi);
		MyLocationConfiguration config = new MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL, true, _MarkBitmapMypoi);  
		mBaiduMap.setMyLocationConfigeration(config);  
	}
	
	public void setMyNavigateIcon()
	{
		BitmapDescriptor _MarkBitmapMypoi;
		_MarkBitmapMypoi = BitmapDescriptorFactory
				.fromResource(R.drawable.map_navigate_image);
		MyLocationConfiguration config = new MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL, true, _MarkBitmapMypoi);  
		mBaiduMap.setMyLocationConfigeration(config);  
	}

	public void showMyLocationOnBaiduMap(int pMapZoom)
	{
		// 坐标转换
		CoordinateConverter _Converter = new CoordinateConverter();
		_Converter.from(CoordinateConverter.CoordType.COMMON);
		_Converter.coord(mLocationLatLng);
		LatLng _LatLng = _Converter.convert();
		
		mBaiduMap.setMyLocationEnabled(true);

		if(_LatLng != null)
		{
			// 坐标微调
			double _LaRet = 0.00011;
			double _LoRet = 0.00011;
			_LatLng = new LatLng(_LatLng.latitude + _LaRet, _LatLng.longitude - _LoRet);
			
			// 地图移动到以当前定位位置为中心
	
			MapStatus _MapStatus = new MapStatus.Builder().target(_LatLng)
					.zoom(pMapZoom).build();
			MapStatusUpdate _MapStatusUpdate = MapStatusUpdateFactory
					.newMapStatus(_MapStatus);
			mBaiduMap.setMapStatus(_MapStatusUpdate);
			// pBaiduMap.animateMapStatus(_MapStatusUpdate);
	
		/*	// 地图上mark定位位置
			BitmapDescriptor mMarkBitmapMypoi;
			mMarkBitmapMypoi = BitmapDescriptorFactory
					.fromResource(R.drawable.locate_mark_mypoi);
			
			Log.i("Class:MyLocationListener",
					"showMyLocationOnBaiduMap: setMyLocationEnabled : " + mBaiduMap.isMyLocationEnabled());
			
			MyLocationConfiguration _MyLocationConfiguration = new MyLocationConfiguration(mCurrentMode, true, null);
			mBaiduMap.setMyLocationConfigeration(_MyLocationConfiguration);
	
			Log.i("Class:MyLocationListener",
					"Method:showMyLocationOnBaiduMap(BaiduMap) : setMyLocationConfigeration");
			
	
			
			OverlayOptions _OverlayOptions = new MarkerOptions().position(_LatLng).icon(mMarkBitmapMypoi).zIndex(20);
			if(mMyLocationMarker != null)
			{
				mMyLocationMarker.remove();
			}
			mMyLocationMarker = (Marker) (mBaiduMap.addOverlay(_OverlayOptions));
			mMyLocationMarker.setAnchor(0.5f, 1.0f);
			*/
			// 构造定位数据
			MyLocationData _LocData = new MyLocationData.Builder()
					.accuracy(mLocationAccracy)
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(0)
					.latitude(_LatLng.latitude)
					.longitude(_LatLng.longitude).build();
			
			// 设置定位数据
			mBaiduMap.setMyLocationData(_LocData);
			
			// 设置自定义图标
			BitmapDescriptor _MarkBitmapMypoi;
			_MarkBitmapMypoi = BitmapDescriptorFactory
					.fromResource(R.drawable.locate_mark_mypoi);

			MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, _MarkBitmapMypoi);  
			mBaiduMap.setMyLocationConfigeration(config); 
			
		}
		else 
		{
			Log.i("Class:MyLocationListener",
					"Method:showMyLocationOnBaiduMap() : _LatLng is null");
		}

	}

	public void setLocationOn()
	{
		mLocationClient.start();
		Log.i("Class:MyLocationListener",
				"Method:setLocationOn() : mLocationClient start");
	}

	public void setLocationOff()
	{
		mLocationClient.stop();
		Log.i("Class:MyLocationListener",
				"Method:setLocationOff() : mLocationClient stop");
	}

	public LatLng getMyPosition()
	{
		return mLocationLatLng;
	}
	
	public float getMyAccracy()
	{
		return mLocationAccracy;
	}

	public double getMyDirect()
	{
		return mLocationDirect;
	}

	public void setSingleLocation(boolean arg0)
	{
		mIsSingleLocation = arg0;
		mLocationClient.start();
		Log.i("Class:MyLocationListener",
				"Method:setSingleLocation(boolean) : mIsSingleLocation is "
						+ mIsSingleLocation);
	}

	public boolean isSingleLocation()
	{
		return mIsSingleLocation;
	}

	public void hideMyLocationMarker()
	{
		mBaiduMap.setMyLocationEnabled(false);
		//mMyLocationMarker.setVisible(false);
	}
	
	public void showMyLocationMarker()
	{
		mBaiduMap.setMyLocationEnabled(false);
		//mMyLocationMarker.setVisible(true);
	}
	
}
