package com.coderlucas.botanique.tabmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.style.SuperscriptSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.coderlucas.botanique.ClassifyInfoActivity;
import com.coderlucas.botanique.Keys;
import com.coderlucas.botanique.MapImagePopWindow;
import com.coderlucas.botanique.MapRouteInfoBar;
import com.coderlucas.botanique.MapRoutePopWindow;
import com.coderlucas.botanique.MapToolsBar;
import com.coderlucas.botanique.MyInfoWindow;
import com.coderlucas.botanique.MyLatLng;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.baidumap.MyBaiduMapAround;
import com.coderlucas.botanique.baidumap.MyBaiduMapBasic;
import com.coderlucas.botanique.baidumap.MyBaiduMapLocation;
import com.coderlucas.botanique.baidumap.MyBaiduMapNavigation;
import com.coderlucas.botanique.baidumap.MyOrientationListener.OnOrientationListener;
import com.coderlucas.botanique.main.MainActivity;

/**
 * @ClassName: TabMapFragment
 * @Description: Contain Map Tab
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月6日 下午2:39:51
 * 
 */
public class TabMapFragment extends Fragment
{

	private static TabMapFragment mThis = null;

	// 地图定位
	private LocationClient mLocationClient = null;
	// 自定义地图工具条
	private MapToolsBar mMapToolsBar = null;
	// 地图显示View
	private MapView mMapView = null;
	// 自定义基本地图管理器
	private MyBaiduMapBasic mMyBaiduMapBasic = null;
	// 自定义地图定位管理器
	private MyBaiduMapLocation mMyBaiduMapLocation = null;
	// 自定义地图周边管理器
	private MyBaiduMapAround mMyBaiduMapAround = null;
	// 图片Pop窗口
	private MapImagePopWindow mMapImagePopWindow = null;
	// 当前选中marker
	private Marker mCurrentMarker = null;
	// InfoWindow
	private MyInfoWindow mMyInfoWindowView = null;
	private InfoWindow mInfoWindow = null;

	//步行导航
	private MyBaiduMapNavigation mMyBaiduMapNavigation = null;
	// 导航信息窗口
	private MapRoutePopWindow mMapRoutePopWindow = null;
	// 导航简要信息条
	private MapRouteInfoBar mMapRouteInfoBar = null;
	
	private ArrayList<MyLatLng> mMyLatLngs = new ArrayList<MyLatLng>();
	
	public void setMyLatLngArrayList(ArrayList<MyLatLng> pList)
	{
		mMyLatLngs = pList;
	}
	
	public static TabMapFragment newInstance()
	{
		if (mThis == null)
		{
			mThis = new TabMapFragment();
			Bundle bundle = new Bundle();
			bundle.putString("abc", "abc");
			mThis.setArguments(bundle);
		}
		return mThis;
	}

	public TabMapFragment()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub

		// 获取地图显示View
		final View _View = inflater.inflate(R.layout.layout_map, container,
				false);
		mMapView = (MapView) _View.findViewById(R.id.bmapView);

		// 设置基本地图
		mMyBaiduMapBasic = new MyBaiduMapBasic(mMapView);

		// 地图定位配置
		mLocationClient = new LocationClient(getActivity());
		mMyBaiduMapLocation = new MyBaiduMapLocation(mLocationClient,
				mMyBaiduMapBasic.getBaiduMap(), mMapView);

		// 地图周边配置
		mMyBaiduMapAround = new MyBaiduMapAround(
				mMyBaiduMapBasic.getBaiduMap(), mMapView);
		// 图片Pop窗口
		mMapImagePopWindow = (MapImagePopWindow) _View
				.findViewById(R.id.map_image_popw);
		// 获取屏幕尺寸，设置Pop窗口尺寸
		DisplayMetrics _DisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(_DisplayMetrics);
		mMapImagePopWindow.setPopWindowSize(_DisplayMetrics.widthPixels,
				_DisplayMetrics.heightPixels);

		
		// 导航管理器
		mMyBaiduMapNavigation = new MyBaiduMapNavigation(mMyBaiduMapBasic.getBaiduMap(), mMapView, getActivity());
		// 导航监听
		mMyBaiduMapNavigation.getMyOrientationListener().setOnOrientationListener(new OnOrientationListener() {
			
			@Override
			public void onOrientationChanged(float x)
			{
				// TODO Auto-generated method stub
				int _XDirection = (int) x;

				
				// 坐标转换
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(mMyBaiduMapLocation.getMyPosition());
				LatLng _LatLngDes = _Converter.convert();
	
				// 坐标微调
				double _LaRet = 0.00011;
				double _LoRet = 0.00011;
				_LatLngDes = new LatLng(_LatLngDes.latitude + _LaRet,
						_LatLngDes.longitude - _LoRet);
				
				
				
				// 构造定位数据
				MyLocationData _LocData = new MyLocationData.Builder()
						.accuracy(mMyBaiduMapLocation.getMyAccracy())
						// 此处设置开发者获取到的方向信息，顺时针0-360
						.direction(_XDirection)
						.latitude(_LatLngDes.latitude)
						.longitude(_LatLngDes.longitude).build();
				
				// 设置定位数据
				mMyBaiduMapBasic.getBaiduMap().setMyLocationData(_LocData);
				
				// 设置自定义图标
				BitmapDescriptor _MarkBitmapMypoi;
				_MarkBitmapMypoi = BitmapDescriptorFactory
						.fromResource(R.drawable.map_navigate_image);

				MyLocationConfiguration config = new MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL, true, _MarkBitmapMypoi);  
				mMyBaiduMapBasic.getBaiduMap().setMyLocationConfigeration(config);  

			}
		});
		
		// 设置自定义地图工具条
		mMapToolsBar = (MapToolsBar) _View.findViewById(R.id.map_tools_bar);
		// 关联地图
		mMapToolsBar.getBaiduMap(mMyBaiduMapBasic.getBaiduMap());
		// 关联定位功能
		mMapToolsBar.setLocateToolOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "定位", Toast.LENGTH_SHORT).show();
				
				mMyBaiduMapBasic.getBaiduMap().clear();
				if(mMapToolsBar != null)
				{
					mMapToolsBar.setAroundStatu(false);
				}
				mMapToolsBar.setLocateIconProcess();
				mMyBaiduMapLocation.setSingleLocation(true);
				mMyBaiduMapLocation.showMyLocationOnBaiduMap(20);
				mMapToolsBar.setLocateIconWait();

			}
		});

		
		// 显示附近植物
		mMapToolsBar.setAroundOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				if (!mMapToolsBar.getAroundStatu())
				{
					Toast.makeText(getActivity(),
							"显示周边" + mMapToolsBar.getDistanceAround() + "米",
							Toast.LENGTH_SHORT).show();

					mMyBaiduMapBasic.getBaiduMap().clear();
					// 添加Marker
					mMyBaiduMapAround.showAllAroundItem(mMapToolsBar
							.getDistanceAround());

					// 隐藏我的位置
					mMyBaiduMapLocation.hideMyLocationMarker();
					
					// 添加数据
					mMapImagePopWindow.getDataFromSource(
							mMyBaiduMapAround.getDrawableID(),
							mMyBaiduMapAround.getNameStrings());

					// 设置事件监听函数
					mMapImagePopWindow.setAdapter(new OnClickListener() {

						@Override
						public void onClick(View v)
						{
							if (mCurrentMarker == mMyBaiduMapAround
									.getMarkerList().get((int) v.getTag()))
							{
								Log.i("Class:TabMapFragment",
										"mCurrentMarker == mMyBaiduMapAround.getMarkerList().get()");
							}
							else
							{
								if (mCurrentMarker != null)
								{
									BitmapDescriptor _MarkBitmapCommon = BitmapDescriptorFactory
											.fromResource(R.drawable.locate_mark_item);
									mCurrentMarker.setIcon(_MarkBitmapCommon);
								}

							}

							// 重绘该mark
							BitmapDescriptor _MarkBitmapCurrent = BitmapDescriptorFactory
									.fromResource(R.drawable.locate_mark_current);

							mMyBaiduMapAround.getMarkerList()
									.get((int) v.getTag())
									.setIcon(_MarkBitmapCurrent);
							mCurrentMarker = mMyBaiduMapAround.getMarkerList()
									.get((int) v.getTag());

							// 显示InfoWindow
							if(mMyInfoWindowView == null)
							{
								mMyInfoWindowView = new MyInfoWindow(getActivity()
										.getApplicationContext(), null);
							}
							
							mMyInfoWindowView.setTextTitle(mMyBaiduMapAround
									.getNameStrings().get((int) v.getTag()));
							mMyInfoWindowView
									.setNavigationPosition(mMyBaiduMapAround
											.getMarkerList()
											.get((int) v.getTag())
											.getPosition());
							mMyInfoWindowView.showTitle();
							mMyInfoWindowView.showTip();
							mMyInfoWindowView.setVisibility(View.VISIBLE);

							// 坐标转换
							CoordinateConverter _Converter = new CoordinateConverter();
							_Converter
									.from(CoordinateConverter.CoordType.COMMON);
							_Converter.coord(mMyBaiduMapAround
									.getMarkPosition().get((int) v.getTag()));
							LatLng _LatLngDes = _Converter.convert();

							mInfoWindow = new InfoWindow(mMyInfoWindowView,
									_LatLngDes, -120);

							// 显示InfoWindow
							mMyBaiduMapBasic.getBaiduMap().showInfoWindow(
									mInfoWindow);
							mMyInfoWindowView
									.setTextViewTipOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v)
										{
											// TODO Auto-generated method stub
											if (mMapImagePopWindow
													.getVisibility() == View.VISIBLE)
											{
												mMapImagePopWindow
														.setVisibility(View.GONE);
											}

											// 查询路线
											if (mMyBaiduMapLocation
													.getMyPosition() == null)
											{
												Log.w("Class:TabMapFragment",
														"Method:mMyInfoWindowView.setTextViewTipOnClickListener : mMyBaiduMapLocation.getMyPosition is null");
											}
											else
											{
												//路线详细信息窗口
												DisplayMetrics  _DisplayMetrics = new DisplayMetrics(); 
												getActivity().getWindowManager().getDefaultDisplay().getMetrics(_DisplayMetrics);      
												mMapRoutePopWindow = (MapRoutePopWindow)getActivity().findViewById(R.id.map_route_popw);
												mMapRoutePopWindow.setPopWindowSize(_DisplayMetrics.widthPixels, _DisplayMetrics.heightPixels);
												mMapRoutePopWindow.setAdapter();
												
												// 坐标转换
												CoordinateConverter _Converter = new CoordinateConverter();
												_Converter
														.from(CoordinateConverter.CoordType.COMMON);
												_Converter.coord(new LatLng(mMyBaiduMapLocation.getMyPosition().latitude + 0.00011, mMyBaiduMapLocation.getMyPosition().longitude - 0.00011));
												LatLng _LatLngDes = _Converter.convert();
												mMyBaiduMapNavigation.getWalkingRoute(_LatLngDes, mMyInfoWindowView.getNavigationPosition());
												
												Log.i("TabMapFragment getWalkingSteps", mMyBaiduMapNavigation.getWalkingSteps().toString());
												mMapRoutePopWindow.getDataFromWalkingStep(mMyBaiduMapNavigation.getWalkingSteps());
											
												//路线信息条
												mMapRouteInfoBar = (MapRouteInfoBar)getActivity().findViewById(R.id.map_route_bar);
												mMapRouteInfoBar.getRoutePopWindow(mMapRoutePopWindow);
												//详细按钮事件
												mMapRouteInfoBar.setMoreInfoOnClickListener(new OnClickListener() {
													
													@Override
													public void onClick(View v)
													{
														// TODO Auto-generated method stub
														//mMapRoutePopWindow = (MapRoutePopWindow)getActivity().findViewById(R.id.map_route_popw);
														mMapRoutePopWindow.setVisibility(View.VISIBLE);
														
													}
												});
												//导航按钮事件
												mMapRouteInfoBar.setNavigateBtnOnClickListener(new OnClickListener() {
													
													@Override
													public void onClick(View v)
													{
														// TODO Auto-generated method stub
														if(!mMapRouteInfoBar.getNavigateFlag()) //打开导航
														{
															Toast.makeText(getActivity(), "开启导航", Toast.LENGTH_SHORT).show();
															//mMyBaiduMapLocation.setMyNavigateIcon();
															mMyBaiduMapBasic.getBaiduMap().setMyLocationEnabled(true);
															
															mMapRouteInfoBar.setNavigateIconOn();
															mMapRouteInfoBar.setCancelBtnHide();
															mMapRouteInfoBar.setNavigateFlag();
															//mMyBaiduMapLocation.hideMyLocationMarker();
															mMyBaiduMapNavigation.getMyOrientationListener().start();
															mLocationClient.start();
														}
														else //关闭导航
														{
															Toast.makeText(getActivity(), "关闭导航", Toast.LENGTH_SHORT).show();
															
															mMapRouteInfoBar.setNavigateIconOff();
															mMapRouteInfoBar.setCancelBtnShow();
															mMapRouteInfoBar.clearNavigateFlag();
															
															mMyBaiduMapNavigation.getMyOrientationListener().stop();
															mMyBaiduMapBasic.getBaiduMap().clear();
															mMyBaiduMapBasic.getBaiduMap().setMyLocationEnabled(false);
															//mMyBaiduMapLocation.setMyLocationIcon();
															mLocationClient.stop();
															mMapRouteInfoBar.setVisibility(View.GONE);
															mMyBaiduMapLocation.showMyLocationOnBaiduMap(20);
														}
													}
												});
												
												
												mMapRouteInfoBar.setTextInfo("全程 "
														+ mMyBaiduMapNavigation.getDistanceString() + "     用时 "
														+ mMyBaiduMapNavigation.getTimeString());
												mMapRouteInfoBar.setVisibility(View.VISIBLE);
											}
											
											//清除InfoWindow
											mMyBaiduMapBasic.getBaiduMap().hideInfoWindow();

										}
									});

							// 移动地图中心
							MapStatusUpdate _MapStatusUpdate;
							_MapStatusUpdate = MapStatusUpdateFactory
									.newLatLng(mMyBaiduMapAround
											.getMarkerList()
											.get((int) v.getTag())
											.getPosition());
							mMyBaiduMapBasic.getBaiduMap().setMapStatus(
									_MapStatusUpdate);

						}
					}, new OnClickListener() {

						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							// 获取物种id
							// String _ClassifyId =
							// mMyBaiduMapAround.getClassifyIdList().get((int)
							// v.getTag());
							String _ClassifyId = "SZ10000001";

							// 启动ClassifyInfoActivity
							Intent _Intent = new Intent();
							_Intent.setClass(getActivity()
									.getApplicationContext(),
									ClassifyInfoActivity.class);
							Bundle _Bundle = new Bundle();
							_Bundle.putString("ClassifyInfoKey", _ClassifyId);
							_Intent.putExtras(_Bundle);
							startActivityForResult(
									_Intent,
									Keys.KEY_TABMAPFRAGMENT_CLASSIFYINFOACTIVITY);
						}
					});

					mMapImagePopWindow.setVisibility(View.VISIBLE);

					// 设置工具栏周边按钮状态
					mMapToolsBar.setAroundStatu(true);
				}
				else
				{
					// 隐藏周边信息
					Toast.makeText(getActivity(), "隐藏周边信息", Toast.LENGTH_SHORT)
							.show();

					// 清除Marker
					// mMyBaiduMapAround.clearAllAroundItem();
					mMyBaiduMapAround.removeAllAroundItem();
					Log.i("Class:TabMapFragment", "MarkerList 5:"
							+ mMyBaiduMapAround.getMarkerList().toString());

					// 隐藏Pop窗口
					mMapImagePopWindow.setVisibility(View.GONE);

					// 隐藏InfoWindow
					// mMyInfoWindowView.setVisibility(View.GONE);
					mMyBaiduMapBasic.getBaiduMap().hideInfoWindow();
					// 清除步行导航路线
					mMyBaiduMapNavigation.clearRouteLine();
					// 清除导航路线信息窗口
					if(mMapRouteInfoBar != null)
					{
						mMapRouteInfoBar.setVisibility(View.GONE);
						mMapRoutePopWindow.setVisibility(View.GONE);
					}
					// 设置工具栏周边按钮状态
					mMapToolsBar.setAroundStatu(false);
				}
			}
		});
		
		mMyBaiduMapBasic.getBaiduMap().setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0)
			{
				BitmapDescriptor _MarkBitmapCurrent = BitmapDescriptorFactory
						.fromResource(R.drawable.locate_mark_current);
				BitmapDescriptor _MarkBitmapCommon = BitmapDescriptorFactory
						.fromResource(R.drawable.locate_mark_item);
				
				// 坐标转换
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter
						.from(CoordinateConverter.CoordType.COMMON);
				
				// TODO Auto-generated method stub
				if(arg0.getTitle() == "ClassifyPosition")
				{
					// 点击返回的marker
					if(mCurrentMarker == arg0)
					{
						Log.i("Class:TabMapFragment",
								"mCurrentMarker == arg0");
					}
					else 
					{
						if (mCurrentMarker != null)
						{
							mCurrentMarker.setIcon(_MarkBitmapCommon);
						}
					}
					arg0.setIcon(_MarkBitmapCurrent);
					mCurrentMarker = arg0;
					Log.i("Class:TabMapFragment", "Method:OnMarkerClick() : flag 1");
					// 显示InfoWindow
					if(mMyInfoWindowView == null)
					{
						mMyInfoWindowView = new MyInfoWindow(getActivity()
								.getApplicationContext(), null);
					}
					
					mMyInfoWindowView.hideTitle();
					mMyInfoWindowView
							.setNavigationPosition(arg0.getPosition());
					mMyInfoWindowView.showTip();
					mMyInfoWindowView.setVisibility(View.VISIBLE);
					
					
					mInfoWindow = new InfoWindow(mMyInfoWindowView,
							arg0.getPosition(), -80);

					// 显示InfoWindow
					mMyBaiduMapBasic.getBaiduMap().showInfoWindow(
							mInfoWindow);
					
					// 移动地图中心
					MapStatusUpdate _MapStatusUpdate;
					_MapStatusUpdate = MapStatusUpdateFactory
							.newLatLng(arg0.getPosition());
					mMyBaiduMapBasic.getBaiduMap().setMapStatus(
							_MapStatusUpdate);
				}
				else  // 点击周边的marker
				{
					// marker是植物数据
					if(arg0.getTitle().equals("item"))
					{
						Log.i("Class:TabMapFragment", "Method:OnMarkerClick() : flag 9");
						if (mCurrentMarker == arg0 )
						{
							Log.i("Class:TabMapFragment",
									"mCurrentMarker == Marker");
						}
						else
						{
							if (mCurrentMarker != null)
							{
								mCurrentMarker.setIcon(_MarkBitmapCommon);
							}
		
						}
		
						// 重绘该mark
						
						int temp = 0;
						
						for(int i = 0; i < mMyBaiduMapAround.getMarkerList().size(); i++)
						{
							if(arg0 == mMyBaiduMapAround.getMarkerList().get(i))
							{
								mMyBaiduMapAround.getMarkerList().get(i).setIcon(_MarkBitmapCurrent);
								mCurrentMarker = mMyBaiduMapAround.getMarkerList().get(i);
								temp = i;
								break;
							}
						}
						
						// 显示InfoWindow
						if(mMyInfoWindowView == null)
						{
							mMyInfoWindowView = new MyInfoWindow(getActivity()
									.getApplicationContext(), null);
						}
						
						mMyInfoWindowView.setTextTitle(mMyBaiduMapAround
								.getNameStrings().get(temp));
						/*mMyInfoWindowView
								.setNavigationPosition(mMyBaiduMapAround
										.getMarkerList()
										.get(temp)
										.getPosition());*/
						mMyInfoWindowView
								.setNavigationPosition(mCurrentMarker.getPosition());
						mMyInfoWindowView.showTitle();
						mMyInfoWindowView.showTip();
						mMyInfoWindowView.setVisibility(View.VISIBLE);
		
						// 坐标转换
						_Converter.coord(mMyBaiduMapAround
								.getMarkPosition().get(temp));
						LatLng _LatLngDes = _Converter.convert();
		
						mInfoWindow = new InfoWindow(mMyInfoWindowView,
								_LatLngDes, -120);
		
						// 显示InfoWindow
						mMyBaiduMapBasic.getBaiduMap().showInfoWindow(
								mInfoWindow);
						
						// 移动地图中心
						MapStatusUpdate _MapStatusUpdate;
						_MapStatusUpdate = MapStatusUpdateFactory
								.newLatLng(mMyBaiduMapAround
										.getMarkerList()
										.get(temp)
										.getPosition());
						mMyBaiduMapBasic.getBaiduMap().setMapStatus(
								_MapStatusUpdate);
					}
					else 
					{
						if(arg0 == null)
							Log.i("Class:TabMapFragment", "Method:OnMarkerClick() : flag 10");
						Toast.makeText(getActivity(), "Route Node", Toast.LENGTH_SHORT).show();
	
					}
				}

				return false;
			}
		});
		
		
		// 初始定位
		mMapToolsBar.setLocateIconProcess();
		mMapToolsBar.refreshDrawableState();
		mMyBaiduMapLocation.setSingleLocation(true);
		mMyBaiduMapLocation.showMyLocationOnBaiduMap(20);
		mMapToolsBar.setLocateIconWait();

		return _View;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
		switch (resultCode)
		{
			case Activity.RESULT_OK:
				mMapToolsBar.setAroundStatu(false);
				// classify返回结果
				ArrayList<? extends Parcelable> _List = data
						.getParcelableArrayListExtra("ClassifyToMap");
				ArrayList<MyLatLng> _List1 = (ArrayList<MyLatLng>) _List;
				
				
				
				showClassifyItem(_List1);
				
				// serach返回结果
				/*ArrayList<? extends Parcelable> _SearchList = data
						.getParcelableArrayListExtra("SearchToMap");
				ArrayList<MyLatLng> _SearchList1 = (ArrayList<MyLatLng>) _SearchList;
				showClassifyItem(_SearchList1);*/
				
				break;

			case Activity.RESULT_CANCELED:
				
				Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
				break;

			/*case Keys.KEY_SEARCH_TO_TABMAP:
				Log.i("TabMapFragment", "onActivityResult():KEY_SEARCH_TO_TABMAP");
				ArrayList<? extends Parcelable> _ListSearch = data
						.getParcelableArrayListExtra("ClassifyToMap");
				ArrayList<MyLatLng> _ListSearch1 = (ArrayList<MyLatLng>) _ListSearch;
				if(mMyLatLngs != null)
				{
					mMyLatLngs.clear();
				}
				if(_ListSearch1 != null)
				{
					Log.i("TabMapFragment", "_ListSearch1="+_ListSearch1.toString());
					showClassifyItem(_ListSearch1);
				}
				// serach返回结果
				
				break;*/
				
			default:
				break;
		}
	}

	private void showClassifyItem(List<MyLatLng> list)
	{

		mMyBaiduMapBasic.getBaiduMap().clear();
		mMapImagePopWindow.setVisibility(View.GONE);
		
		LatLng _DesLatLng = null;
		
		OverlayOptions _OverlayOptions = null;
		Marker _Marker = null;
		final List<LatLng> _MarkPostion = new ArrayList<LatLng>();
		
		// 准备 marker 的图片
		BitmapDescriptor mMarkBitmapCommon = BitmapDescriptorFactory
				.fromResource(R.drawable.locate_mark_item);

		CoordinateConverter _Converter = new CoordinateConverter();
		_Converter
				.from(CoordinateConverter.CoordType.COMMON);
		
		for (int i = 0; i < list.size(); i++)
		{
			//坐标转换
			_Converter.coord(new LatLng(list.get(i).getLantitude(), list.get(i).getLongtitude()));
			_DesLatLng = _Converter.convert();
		
			//添加mark
			_OverlayOptions = new MarkerOptions().title("ClassifyPosition").position(_DesLatLng).icon(mMarkBitmapCommon).zIndex(5);
			_Marker = (Marker) (mMyBaiduMapBasic.getBaiduMap().addOverlay(_OverlayOptions));
			Log.i("showClassifyItem","_Marker="+_Marker.toString());
			Bundle _Bundle = new Bundle();
			//_Bundle.putSerializable("_DataFromJSON", _DataFromJSON);
			_Marker.setExtraInfo(_Bundle);
			_MarkPostion.add(new LatLng(list.get(i).getLantitude(), list.get(i).getLongtitude()));	
			Log.i("showClassifyItem","_MarkPostion="+_MarkPostion.toString());
		}
		
	}
	
	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}

	private boolean mSearchOnFlag = false;
	
	
	public void setSearchOnFlag(boolean arg0)
	{
		mSearchOnFlag = arg0;
	}
	
	public boolean getSearchOnFlag()
	{
		return mSearchOnFlag;
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();

		if(getSearchOnFlag() && (mMyLatLngs != null))
		{
			Log.i("TabMapFragment", "mMyLatLngs="+mMyLatLngs.toString());
			setSearchOnFlag(false);
			showClassifyItem(mMyLatLngs);
		}
		
		
		
	}

	@Override
	public void onStop()
	{
		// TODO Auto-generated method stub
		mLocationClient.stop();
		super.onStop();
	}
}
