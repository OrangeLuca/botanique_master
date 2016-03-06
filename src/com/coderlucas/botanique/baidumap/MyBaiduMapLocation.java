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
	// ��λ�ͻ���
	private LocationClient mLocationClient;
	// ��λ������
	public MyLocationListener mMyLocationListener;
	// ��ǰ��λ��ģʽ
	private LocationMode mCurrentMode = LocationMode.NORMAL;
	// �߾���ģʽ
	// private LocationMode tempMode = LocationMode.Device_Sensors;
	// private LocationMode tempMode = LocationMode.Battery_Saving;
	// private com.baidu.location.LocationClientOption.LocationMode
	// mCurrentModeL =
	// com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy;
	// ����ּ��ܾ�γ������
	private String mLocateTempcoor = "gcj02";
	// �ٶȼ��ܾ�γ������
	// private String tempcoor="bd09ll";
	// ��λʱ����1000ms
	private int mLocateFrequence = 1000;
	// ��λ��Ϣ
	private StringBuffer mStringBuffer = new StringBuffer(256);
	// ��γ��
	private LatLng mLocationLatLng = null;
	// ����
	private double mLocationDirect;
	// ����
	private float mLocationAccracy;
	// ���ζ�λ
	private boolean mIsSingleLocation = false;

	private BaiduMap mBaiduMap = null;
	
	private MapView mMapView = null;

	private MyLocationListener mListener = null;

	private Marker mMyLocationMarker = null;
	
	public MyBaiduMapLocation(LocationClient pLocationClient, BaiduMap pBaiduMap, MapView pMapView)
	{
		// ��ȡ��λ�ͻ���
		mLocationClient = pLocationClient;
		mBaiduMap = pBaiduMap;
		mMapView = pMapView;
		
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		Log.i("Class:MyLocationListener",
				"MyBaiduMapLocation: setMyLocationEnabled : " + mBaiduMap.isMyLocationEnabled());
		
		// ע���������
		mListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mListener);

		// ��λ��ʼ��
		initMyLocation();
	}

	// ��ʼ��
	private void initMyLocation()
	{
		// ��λ��ʼ��
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		// ���ö�λ���������
		LocationClientOption _LocationClientOption = new LocationClientOption();
		// ���ö�λģʽ
		// _LocationClientOption.setLocationMode(mCurrentModeL);
		_LocationClientOption.setOpenGps(true);// ��GPS
		_LocationClientOption.setCoorType(mLocateTempcoor); // ������������ ���Ҳ���
		_LocationClientOption.setScanSpan(mLocateFrequence); // ��λʱ����
		// ���������
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

			// ��ȡ��λ��Ϣ
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

			// ���GPS��NewWork��λ����
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

			// ��ȡ��λ����
			mLocationAccracy = location.getRadius();
			// ��ö�λ����
			mLocationDirect = location.getDirection();
			// ��ȡ��λ��γ��
			mLocationLatLng = null;
			mLocationLatLng = new LatLng(location.getLatitude(),
					location.getLongitude());
			Log.i("Class:MyLocationListener", "mLocationLatLng = "
					+ mLocationLatLng.toString());
			// ���ζ�λ����
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
		// ����ת��
		CoordinateConverter _Converter = new CoordinateConverter();
		_Converter.from(CoordinateConverter.CoordType.COMMON);
		_Converter.coord(mLocationLatLng);
		LatLng _LatLng = _Converter.convert();
		
		mBaiduMap.setMyLocationEnabled(true);

		if(_LatLng != null)
		{
			// ����΢��
			double _LaRet = 0.00011;
			double _LoRet = 0.00011;
			_LatLng = new LatLng(_LatLng.latitude + _LaRet, _LatLng.longitude - _LoRet);
			
			// ��ͼ�ƶ����Ե�ǰ��λλ��Ϊ����
	
			MapStatus _MapStatus = new MapStatus.Builder().target(_LatLng)
					.zoom(pMapZoom).build();
			MapStatusUpdate _MapStatusUpdate = MapStatusUpdateFactory
					.newMapStatus(_MapStatus);
			mBaiduMap.setMapStatus(_MapStatusUpdate);
			// pBaiduMap.animateMapStatus(_MapStatusUpdate);
	
		/*	// ��ͼ��mark��λλ��
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
			// ���춨λ����
			MyLocationData _LocData = new MyLocationData.Builder()
					.accuracy(mLocationAccracy)
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(0)
					.latitude(_LatLng.latitude)
					.longitude(_LatLng.longitude).build();
			
			// ���ö�λ����
			mBaiduMap.setMyLocationData(_LocData);
			
			// �����Զ���ͼ��
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
