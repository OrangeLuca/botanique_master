package com.coderlucas.botanique.baidumap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager.AppTask;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.baidumap.MyOrientationListener.OnOrientationListener;

public class MyBaiduMapNavigation implements OnGetRoutePlanResultListener
{
	private BaiduMap mBaiduMap = null;
	private MapView mMapView = null;

	private int mNeedTime = 0;
	private int mDistance = 0;

	// 路线与路线搜索
	private RouteLine mRouteLine = null;

	private WalkingRouteOverlay mRouteOverlay = null;

	private RoutePlanSearch mRoutePlanSearch = null;

	private com.baidu.mapapi.search.route.PlanNode mStartNode = null;

	private List<String> mStepDetails = new ArrayList<String>();
	private List<WalkingStep> mWalkingSteps = new ArrayList<WalkingStep>();
	
	/* 绘制marker */
	private Boolean mUseDefaultIcon = false;

	private WalkingRouteOverlay mWalkingRouteOverlay = null;
	
	// 方向传感器
	private MyOrientationListener mMyOrientationListener = null;
	private Marker mMyNaviLocationMarker = null;
	
	public MyBaiduMapNavigation(BaiduMap pBaiduMap, MapView pMapView, Context pContext)
	{
		// TODO Auto-generated constructor stub
		mBaiduMap = pBaiduMap;
		mMapView = pMapView;
		
		mRoutePlanSearch = RoutePlanSearch.newInstance();
		mRoutePlanSearch.setOnGetRoutePlanResultListener(this);
		
		mMyOrientationListener = new MyOrientationListener(pContext);
	}
	
	public MyOrientationListener getMyOrientationListener()
	{
		return mMyOrientationListener;
	}
	
	
	public void getWalkingRoute(LatLng pStartLatLng, LatLng pEndLatLng)
	{
		// 查询路线 
		
		WalkingRoutePlanOption _WalkingRoutePlanOption = new WalkingRoutePlanOption();
		
		_WalkingRoutePlanOption
				.from(PlanNode
						.withLocation(pStartLatLng))
				.to(PlanNode
						.withLocation(pEndLatLng));
		mRoutePlanSearch
				.walkingSearch(_WalkingRoutePlanOption);
	}

	public RoutePlanSearch getRoutePlanSearch()
	{
		return mRoutePlanSearch;
	}
	
	public List<String> getStepDetails()
	{
		return mStepDetails;
	}
	
	public List<WalkingStep> getWalkingSteps()
	{
		Log.i("getWalkingSteps()", mWalkingSteps.toString());
		return mWalkingSteps;
	}
	
	public int getDistance()
	{
		return mDistance;
	}
	
	public String getDistanceString()
	{
		return distanceFormatter(mDistance);
	}
	
	public int getTime()
	{
		return mNeedTime;
	}
	
	public String getTimeString()
	{
		return timeFormatter(mNeedTime);
	}
	
	
	public void setUseDefaultIcon(boolean pUseDefaultIcon)
	{
		mUseDefaultIcon = pUseDefaultIcon;
	}
	
	// 时间转换
		public static String timeFormatter(int second)
		{
			int _Hour = second / 3600;
			int _Minute = second % 3600 / 60;
			int _Second = second % 60;
			String _Str = "";
			if(_Hour != 0)
			{
				_Str = _Str + " "  + _Hour + " h";
			}
			if(_Minute != 0)
			{
				_Str = _Str + " " + _Minute + " m";
			}
			if(_Second != 0)
			{
				_Str = _Str + " " + _Second + " s";
			}
			
			return _Str;

		}
		
	// 距离转换
		public static String distanceFormatter(int distance)
		{
			if (distance < 1000)
			{
				return distance + " m";
			}
			else if (distance % 1000 == 0)
			{
				return distance / 1000 + " km";
			}
			else
			{
				DecimalFormat df = new DecimalFormat("0.00");
				int a1 = distance / 1000; // 十位

				double a2 = distance % 1000;
				double a3 = a2 / 1000; // 得到个位

				String result = df.format(a3);
				double total = Double.parseDouble(result) + a1;
				return total + " km";
			}
		}
		
	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult arg0)
	{
		// TODO Auto-generated method stub

	}
	
	public void clearRouteLine()
	{
		if(mWalkingRouteOverlay != null)
		{
			mWalkingRouteOverlay.removeFromMap();
		}
	}
	
	@Override
	public void onGetWalkingRouteResult( WalkingRouteResult result)
	{
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
		{
			Log.i("onGetWalkingRouteResult", "未找到结果");
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR)
		{
			Log.i("onGetWalkingRouteResult", "错误");
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR)
		{
			Log.i("onGetWalkingRouteResult() start", mWalkingSteps.toString());
			mBaiduMap.clear();
			if (mStepDetails.size() != 0)
			{
				mStepDetails.clear();
			}
			if (mDistance != 0)
			{
				mDistance = 0;
			}
			if (mNeedTime != 0)
			{
				mNeedTime = 0;
			}
			mRouteLine = result.getRouteLines().get(0);
			// 自定义
			mWalkingRouteOverlay = new MyWalkingRouteOverlay(
					mBaiduMap);
			mRouteOverlay = mWalkingRouteOverlay;

			mWalkingRouteOverlay.setData(result.getRouteLines().get(0));
			// 将所有Overlay 添加到地图上
			mWalkingRouteOverlay.addToMap();
			// 缩放地图，使所有Overlay都在合适的视野内注:该方法只对Marker类型的overlay有效
			mWalkingRouteOverlay.zoomToSpan();

			List<WalkingRouteLine> routeLines = result.getRouteLines();
			List<WalkingStep> steps = routeLines.get(0).getAllStep();
			
			// 分为N步
			for (int i = 0; i < steps.size(); i++)
			{
				
				String instructions = steps.get(i).getInstructions();
				int direction = steps.get(i).getDirection();
				int distance = steps.get(i).getDistance();
				// 叠加每一个step的distance
				this.mDistance += distance;
				String entraceInstructions = steps.get(i)
						.getEntraceInstructions();
				String title = steps.get(i).getEntrace().getTitle();
				mStepDetails.add((i + 1) + "." + instructions);
			}

			// 步行速度1.5m/s
			mNeedTime = mDistance * 2 / 3;
			
			mWalkingSteps = steps;
			Log.i("onGetWalkingRouteResult() end", mWalkingSteps.toString());
			
		}
	}

	public WalkingRouteOverlay getRouteOverlay()
	{
		return mRouteOverlay;
	}
	
	class MyWalkingRouteOverlay extends WalkingRouteOverlay
	{

		public MyWalkingRouteOverlay(BaiduMap baiduMap)
		{
			super(baiduMap);
		}

		@Override
		public BitmapDescriptor getStartMarker()
		{
			if (mUseDefaultIcon)
			{
				return BitmapDescriptorFactory
						.fromResource(R.drawable.locate_mark_current);
			}
			return null;
		}

		@Override
		public BitmapDescriptor getTerminalMarker()
		{
			if (mUseDefaultIcon)
			{
				return BitmapDescriptorFactory
						.fromResource(R.drawable.locate_mark_item);
			}
			return null;
		}
	}
}
