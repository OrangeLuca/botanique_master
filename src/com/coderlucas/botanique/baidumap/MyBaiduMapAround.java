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

	// 周围距离
	private int mDistance;

	// 当前坐标
	private LatLng mCurrentLocation = null;

	// 存储获取的数据
	private DataFromJSON mDataFromJSON = null;

	// 获取数据的图片ID, 但实际中需要使用图片Uri地址
	private ArrayList<Integer> mDrawablesId = null;
	// final ArrayList<Uri> mDrawablesUri = null;

	// 获取数据的标题
	private ArrayList<String> mNameStrings = null;

	// 获取数据的分类号
	private ArrayList<String> mClassifyIdStrings = null;

	// 获取数据的位置
	private ArrayList<LatLng> mMarkPostion = null;

	// 准备 marker 的图片
	BitmapDescriptor mMarkBitmapCommon = BitmapDescriptorFactory
			.fromResource(R.drawable.locate_mark_item);

	// 是否正在显示数据
	private boolean mIsShowflag = false;
	// 是否刷新数据
	private boolean mIsRefreshflag = false;
	
	// 数据marker
	private ArrayList<Marker> mMarkerList = null;
	
	
	
	
	public MyBaiduMapAround(BaiduMap pBaiduMap, MapView pMapView)
	{
		mBaiduMap = pBaiduMap;
		mMapView = pMapView;

		// 存储获取的数据
		mDataFromJSON = new DataFromJSON();

		// 获取数据的图片ID, 但实际中需要使用图片Uri地址
		mDrawablesId = new ArrayList<Integer>();
		// mDrawablesUri = new ArrayList<Uri>();

		// 获取数据的标题
		mNameStrings = new ArrayList<String>();

		// 获取数据的分类号
		mClassifyIdStrings = new ArrayList<String>();

		// 获取数据的位置
		mMarkPostion = new ArrayList<LatLng>();

		// 数据marker
		mMarkerList = new ArrayList<Marker>();
		
	}

	public List<DataFromJSON> getData(int pDistance)
	{
		// 从数据库查询数据，返回JSON数据，存储在DataFromJSON类中

		// 返回数据
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
			//清理显示的marker
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
			
			//清空前一次数据
			mDrawablesId = new ArrayList<Integer>();
			mDrawablesId = new ArrayList<Integer>();
			mNameStrings = new ArrayList<String>();
			mMarkPostion = new ArrayList<LatLng>();
			mClassifyIdStrings = new ArrayList<String>();
			
			//传入距离参数
			mDistance = pDistance;
			
			// 获取数据
			DataFromJSON.infos = getData(mDistance);
	
			//清空原marker集合
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
				
				// 获取数据位置
				LatLng _LatLngSrc = new LatLng(_DataFromJSON.getLatitude(),
						_DataFromJSON.getLongitude());
	
				// 坐标转换
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(_LatLngSrc);
				LatLng _LatLngDes = _Converter.convert();
	
				// 坐标微调
				double _LaRet = 0.00011;
				double _LoRet = 0.00011;
				_LatLngDes = new LatLng(_LatLngDes.latitude + _LaRet,
						_LatLngDes.longitude - _LoRet);
	
				// 添加mark
				OverlayOptions _OverlayOptions = new MarkerOptions().title("item").position(_LatLngDes)
						.icon(mMarkBitmapCommon).zIndex(5);
				
				Marker _Marker = (Marker) (mBaiduMap.addOverlay(_OverlayOptions));
				mMarkerList.add(_Marker);
				
				Bundle _Bundle = new Bundle();
				_Bundle.putSerializable("_DataFromJSON", _DataFromJSON);
				_Marker.setExtraInfo(_Bundle);
	
				//存储数据
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
		//允许刷新
		setRefreshFlag(true);
		Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 1");
		//removeAllAroundItem();
		Log.i("Class:MyBaiduMapAround", "Method:refreshMarkers() : flag 2");
		
		//临时Marker列表
		ArrayList<Marker> _MarkerList = new ArrayList<Marker>();
		
		if(mMarkerList == null)
		{
			Log.w("Class:MyBaiduMapAround", "Method:refreshMarkers() : mMarkerList is null");
		}
		else 
		{
			for(int i = 0; i < mMarkerList.size(); i++)
			{
				// 坐标转换
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(mMarkerList.get(i).getPosition());
				LatLng _LatLng = _Converter.convert();
				
				
				// 添加mark
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
			
			//清理临时marker
			_MarkerList.clear();
		}
		
		//刷新结束
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
