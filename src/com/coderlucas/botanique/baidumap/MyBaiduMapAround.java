package com.coderlucas.botanique.baidumap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.coderlucas.botanique.ClassifyInfoActivity;
import com.coderlucas.botanique.DataFromJSON;
import com.coderlucas.botanique.MapImagePopWindow;
import com.coderlucas.botanique.R;

public class MyBaiduMapAround
{

	private BaiduMap mBaiduMap = null;
	private MapView mMapView = null;

	// ��Χ����
	private int mDistance;

	// ��ǰ����
	private LatLng mCurrentLocation = null;

	// �洢��ȡ������
	private DataFromJSON mDataFromJSON = null;

	// ��ȡ���ݵ�ͼƬID, ��ʵ������Ҫʹ��ͼƬUri��ַ
	private ArrayList<Integer> mDrawablesId = null;
	// final ArrayList<Uri> mDrawablesUri = null;

	// ��ȡ���ݵı���
	private ArrayList<String> mNameStrings = null;

	// ��ȡ���ݵķ����
	private ArrayList<String> mClassifyIdStrings = null;

	// ��ȡ���ݵ�λ��
	private ArrayList<LatLng> mMarkPostion = null;

	// ׼�� marker ��ͼƬ
	BitmapDescriptor mMarkBitmapCommon = BitmapDescriptorFactory
			.fromResource(R.drawable.locate_mark_item);

	// �Ƿ�������ʾ����
	private boolean mIsShowflag = false;
	// �Ƿ�ˢ������
	private boolean mIsRefreshflag = false;
	
	// ����marker
	private ArrayList<Marker> mMarkerList = null;
	
	
	
	
	public MyBaiduMapAround(BaiduMap pBaiduMap, MapView pMapView)
	{
		mBaiduMap = pBaiduMap;
		mMapView = pMapView;

		// �洢��ȡ������
		mDataFromJSON = new DataFromJSON();

		// ��ȡ���ݵ�ͼƬID, ��ʵ������Ҫʹ��ͼƬUri��ַ
		mDrawablesId = new ArrayList<Integer>();
		// mDrawablesUri = new ArrayList<Uri>();

		// ��ȡ���ݵı���
		mNameStrings = new ArrayList<String>();

		// ��ȡ���ݵķ����
		mClassifyIdStrings = new ArrayList<String>();

		// ��ȡ���ݵ�λ��
		mMarkPostion = new ArrayList<LatLng>();

		// ����marker
		mMarkerList = new ArrayList<Marker>();
		
	}

	public List<DataFromJSON> getData(int pDistance)
	{
		// �����ݿ��ѯ���ݣ�����JSON���ݣ��洢��DataFromJSON����

		// ��������
		return DataFromJSON.infos;
	}

	public void removeAllAroundItem()
	{
		if(!getShowFlag() && !getRefreshFlag())
		{
			Log.i("Class:MyBaiduMapAround", "Method:clearAllAroundItem() : mIsShowFlag is false and mIsRefreshFlag is false");
		}
		else 
		{
			//������ʾ��marker
			for(int i = 0; i < mMarkerList.size(); i++)
			{
				mMarkerList.get(i).remove();
			}
			setShowFlag(false);
		}
	}
	
	public void clearAllAroundItem()
	{
		if(mMarkerList == null)
		{
			Log.i("Class:MyBaiduMapAround", "Method:clearAllAroundItem() : mMarkerList is null");
		}
		else 
		{
			mMarkerList.clear();
			Log.i("Class:MyBaiduMapAround", "Method:clearAllAroundItem() : mMarkerList size is "+mMarkerList.size());
		}

	}
	
	public void showAllAroundItem(int pDistance)
	{
		if(getShowFlag())
		{
			Log.i("Class:MyBaiduMapAround", "Method:showAllAroundItem() : mIsShowFlag is true");
		}
		else
		{
			removeAllAroundItem();
			//clearAllAroundItem();
			
			//���ǰһ������
			mDrawablesId = new ArrayList<Integer>();
			mDrawablesId = new ArrayList<Integer>();
			mNameStrings = new ArrayList<String>();
			mMarkPostion = new ArrayList<LatLng>();
			mClassifyIdStrings = new ArrayList<String>();
			
			//����������
			mDistance = pDistance;
			
			// ��ȡ����
			DataFromJSON.infos = getData(mDistance);
	
			//���ԭmarker����
			//mMarkerList.clear();
			clearAllAroundItem();
			
			if(mMarkerList.size() != 0)
			{
				Log.w("Class:MyBaiduMapAround", "Method:showAllAroundItem() : mMarkerList.size is not 0");
			}
			
			int i = 0;
			for (DataFromJSON _DataFromJSON : DataFromJSON.infos)
			{
				++i;
				
				// ��ȡ����λ��
				LatLng _LatLngSrc = new LatLng(_DataFromJSON.getLatitude(),
						_DataFromJSON.getLongitude());
	
				// ����ת��
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(_LatLngSrc);
				LatLng _LatLngDes = _Converter.convert();
	
				// ����΢��
				double _LaRet = 0.00011;
				double _LoRet = 0.00011;
				_LatLngDes = new LatLng(_LatLngDes.latitude + _LaRet,
						_LatLngDes.longitude - _LoRet);
	
				// ���mark
				OverlayOptions _OverlayOptions = new MarkerOptions().title("item").position(_LatLngDes)
						.icon(mMarkBitmapCommon).zIndex(5);
				
				Marker _Marker = (Marker) (mBaiduMap.addOverlay(_OverlayOptions));
				mMarkerList.add(_Marker);
				
				Bundle _Bundle = new Bundle();
				_Bundle.putSerializable("_DataFromJSON", _DataFromJSON);
				_Marker.setExtraInfo(_Bundle);
	
				//�洢����
				mDrawablesId.add(_DataFromJSON.getImgId());
				mNameStrings.add(_DataFromJSON.getName());
				mMarkPostion.add(new LatLng(_DataFromJSON.getLatitude(),
						_DataFromJSON.getLongitude()));
				mClassifyIdStrings.add(_DataFromJSON.getClassifyId());
			}
			Log.i("Class:MyBaiduMapAround", "Method:showAllAroundItem() : mMarkerList size after add is "+ mMarkerList.size());
			setShowFlag(true);
		}

	}
	
	public void setShowFlag(boolean arg0)
	{
		mIsShowflag = arg0;
	}
	
	public boolean getShowFlag()
	{
		return mIsShowflag;
	}
	
	public ArrayList<String> getNameStrings()
	{
		if(mNameStrings == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getNameStrings() : mNameStrings is null");
		}
		return mNameStrings;
	}
	
	public ArrayList<LatLng> getMarkPosition()
	{
		if(mMarkPostion == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getMarkPosition() : mMarkPostion is null");
		}
		return mMarkPostion;
	}
	
	public ArrayList<Integer> getDrawableID()
	{
		if(mDrawablesId == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getDrawableID() : mDrawablesId is null");
		}
		return mDrawablesId;
	}
	
	public ArrayList<Marker> getMarkerList()
	{
		if(mMarkerList == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getMarkerList() : mMarkerList is null");
		}
		return mMarkerList;
	}
	
	public ArrayList<String> getClassifyIdList()
	{
		if(mClassifyIdStrings == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getClassifyIdList() : mClassifyIdStrings is null");
		}
		return mClassifyIdStrings;
	}
	
	public void setMarkerList(int pPosition, Marker pMarker)
	{
		if(mMarkerList == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:setMarkerList() : mMarkerList is null");
		}
		else 
		{
			if(mMarkerList.size() < (pPosition + 1))
			{
				Log.w("Class:MyBaiduMapAround", "Method:setMarkerList() : mMarkerList is too short");
			}
			else 
			{
				mMarkerList.set(pPosition, pMarker);
				Log.i("Class:MyBaiduMapAround", "Method:setMarkerList() : Position is "+ pPosition);
				Log.i("Class:MyBaiduMapAround", "Method:setMarkerList() : mMarkerList size is "+ mMarkerList.size());
				
			}
		}
	}
	
	public void refreshMarkers()
	{
		//����ˢ��
		setRefreshFlag(true);
		Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 1");
		//removeAllAroundItem();
		Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 2");
		
		//��ʱMarker�б�
		ArrayList<Marker> _MarkerList = new ArrayList<Marker>();
		
		if(mMarkerList == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:refreshMarkers() : mMarkerList is null");
		}
		else 
		{
			for(int i = 0; i < mMarkerList.size(); i++)
			{
				// ����ת��
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(mMarkerList.get(i).getPosition());
				LatLng _LatLng = _Converter.convert();
				
				
				// ���mark
				OverlayOptions _OverlayOptions = new MarkerOptions().position(_LatLng)
						.icon(mMarkerList.get(i).getIcon()).zIndex(5);
				
				/*OverlayOptions _OverlayOptions = new MarkerOptions().position(mMarkerList.get(i).getPosition())
						.icon(mMarkerList.get(i).getIcon()).zIndex(5);*/
				
				Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 3");
				Marker _Marker = (Marker) (mBaiduMap.addOverlay(_OverlayOptions));
				Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 4");
				_MarkerList.add(_Marker);
				Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 5");
			}
			mMarkerList = _MarkerList;
			
			//������ʱmarker
			_MarkerList.clear();
		}
		
		//ˢ�½���
		setRefreshFlag(false);
	}
	
	public boolean getRefreshFlag()
	{
		return mIsRefreshflag;
	}
	
	public void setRefreshFlag(boolean arg0)
	{
		mIsRefreshflag = arg0;
	}
/*	public ArrayList<Uri> getDrawablesUri()
	{
		if(mDrawablesUri == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:getDrawableUri() : mDrawablesUri is null");
		}
		return mDrawablesUri;
	}*/
	
	public BaiduMap getBaiduMap()
	{
		return mBaiduMap;
	}
	
}
