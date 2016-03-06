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
 * @date 2015��5��6�� ����2:39:51
 * 
 */
public class TabMapFragment extends Fragment
{

	private static TabMapFragment mThis = null;

	// ��ͼ��λ
	private LocationClient mLocationClient = null;
	// �Զ����ͼ������
	private MapToolsBar mMapToolsBar = null;
	// ��ͼ��ʾView
	private MapView mMapView = null;
	// �Զ��������ͼ������
	private MyBaiduMapBasic mMyBaiduMapBasic = null;
	// �Զ����ͼ��λ������
	private MyBaiduMapLocation mMyBaiduMapLocation = null;
	// �Զ����ͼ�ܱ߹�����
	private MyBaiduMapAround mMyBaiduMapAround = null;
	// ͼƬPop����
	private MapImagePopWindow mMapImagePopWindow = null;
	// ��ǰѡ��marker
	private Marker mCurrentMarker = null;
	// InfoWindow
	private MyInfoWindow mMyInfoWindowView = null;
	private InfoWindow mInfoWindow = null;

	//���е���
	private MyBaiduMapNavigation mMyBaiduMapNavigation = null;
	// ������Ϣ����
	private MapRoutePopWindow mMapRoutePopWindow = null;
	// ������Ҫ��Ϣ��
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

		// ��ȡ��ͼ��ʾView
		final View _View = inflater.inflate(R.layout.layout_map, container,
				false);
		mMapView = (MapView) _View.findViewById(R.id.bmapView);

		// ���û�����ͼ
		mMyBaiduMapBasic = new MyBaiduMapBasic(mMapView);

		// ��ͼ��λ����
		mLocationClient = new LocationClient(getActivity());
		mMyBaiduMapLocation = new MyBaiduMapLocation(mLocationClient,
				mMyBaiduMapBasic.getBaiduMap(), mMapView);

		// ��ͼ�ܱ�����
		mMyBaiduMapAround = new MyBaiduMapAround(
				mMyBaiduMapBasic.getBaiduMap(), mMapView);
		// ͼƬPop����
		mMapImagePopWindow = (MapImagePopWindow) _View
				.findViewById(R.id.map_image_popw);
		// ��ȡ��Ļ�ߴ磬����Pop���ڳߴ�
		DisplayMetrics _DisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(_DisplayMetrics);
		mMapImagePopWindow.setPopWindowSize(_DisplayMetrics.widthPixels,
				_DisplayMetrics.heightPixels);

		
		// ����������
		mMyBaiduMapNavigation = new MyBaiduMapNavigation(mMyBaiduMapBasic.getBaiduMap(), mMapView, getActivity());
		// ��������
		mMyBaiduMapNavigation.getMyOrientationListener().setOnOrientationListener(new OnOrientationListener() {
			
			@Override
			public void onOrientationChanged(float x)
			{
				// TODO Auto-generated method stub
				int _XDirection = (int) x;

				
				// ����ת��
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter.from(CoordinateConverter.CoordType.COMMON);
				_Converter.coord(mMyBaiduMapLocation.getMyPosition());
				LatLng _LatLngDes = _Converter.convert();
	
				// ����΢��
				double _LaRet = 0.00011;
				double _LoRet = 0.00011;
				_LatLngDes = new LatLng(_LatLngDes.latitude + _LaRet,
						_LatLngDes.longitude - _LoRet);
				
				
				
				// ���춨λ����
				MyLocationData _LocData = new MyLocationData.Builder()
						.accuracy(mMyBaiduMapLocation.getMyAccracy())
						// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
						.direction(_XDirection)
						.latitude(_LatLngDes.latitude)
						.longitude(_LatLngDes.longitude).build();
				
				// ���ö�λ����
				mMyBaiduMapBasic.getBaiduMap().setMyLocationData(_LocData);
				
				// �����Զ���ͼ��
				BitmapDescriptor _MarkBitmapMypoi;
				_MarkBitmapMypoi = BitmapDescriptorFactory
						.fromResource(R.drawable.map_navigate_image);

				MyLocationConfiguration config = new MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL, true, _MarkBitmapMypoi);  
				mMyBaiduMapBasic.getBaiduMap().setMyLocationConfigeration(config);  

			}
		});
		
		// �����Զ����ͼ������
		mMapToolsBar = (MapToolsBar) _View.findViewById(R.id.map_tools_bar);
		// ������ͼ
		mMapToolsBar.getBaiduMap(mMyBaiduMapBasic.getBaiduMap());
		// ������λ����
		mMapToolsBar.setLocateToolOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "��λ", Toast.LENGTH_SHORT).show();
				
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

		
		// ��ʾ����ֲ��
		mMapToolsBar.setAroundOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				if (!mMapToolsBar.getAroundStatu())
				{
					Toast.makeText(getActivity(),
							"��ʾ�ܱ�" + mMapToolsBar.getDistanceAround() + "��",
							Toast.LENGTH_SHORT).show();

					mMyBaiduMapBasic.getBaiduMap().clear();
					// ���Marker
					mMyBaiduMapAround.showAllAroundItem(mMapToolsBar
							.getDistanceAround());

					// �����ҵ�λ��
					mMyBaiduMapLocation.hideMyLocationMarker();
					
					// �������
					mMapImagePopWindow.getDataFromSource(
							mMyBaiduMapAround.getDrawableID(),
							mMyBaiduMapAround.getNameStrings());

					// �����¼���������
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

							// �ػ��mark
							BitmapDescriptor _MarkBitmapCurrent = BitmapDescriptorFactory
									.fromResource(R.drawable.locate_mark_current);

							mMyBaiduMapAround.getMarkerList()
									.get((int) v.getTag())
									.setIcon(_MarkBitmapCurrent);
							mCurrentMarker = mMyBaiduMapAround.getMarkerList()
									.get((int) v.getTag());

							// ��ʾInfoWindow
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

							// ����ת��
							CoordinateConverter _Converter = new CoordinateConverter();
							_Converter
									.from(CoordinateConverter.CoordType.COMMON);
							_Converter.coord(mMyBaiduMapAround
									.getMarkPosition().get((int) v.getTag()));
							LatLng _LatLngDes = _Converter.convert();

							mInfoWindow = new InfoWindow(mMyInfoWindowView,
									_LatLngDes, -120);

							// ��ʾInfoWindow
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

											// ��ѯ·��
											if (mMyBaiduMapLocation
													.getMyPosition() == null)
											{
												Log.w("Class:TabMapFragment",
														"Method:mMyInfoWindowView.setTextViewTipOnClickListener : mMyBaiduMapLocation.getMyPosition is null");
											}
											else
											{
												//·����ϸ��Ϣ����
												DisplayMetrics  _DisplayMetrics = new DisplayMetrics(); 
												getActivity().getWindowManager().getDefaultDisplay().getMetrics(_DisplayMetrics);      
												mMapRoutePopWindow = (MapRoutePopWindow)getActivity().findViewById(R.id.map_route_popw);
												mMapRoutePopWindow.setPopWindowSize(_DisplayMetrics.widthPixels, _DisplayMetrics.heightPixels);
												mMapRoutePopWindow.setAdapter();
												
												// ����ת��
												CoordinateConverter _Converter = new CoordinateConverter();
												_Converter
														.from(CoordinateConverter.CoordType.COMMON);
												_Converter.coord(new LatLng(mMyBaiduMapLocation.getMyPosition().latitude + 0.00011, mMyBaiduMapLocation.getMyPosition().longitude - 0.00011));
												LatLng _LatLngDes = _Converter.convert();
												mMyBaiduMapNavigation.getWalkingRoute(_LatLngDes, mMyInfoWindowView.getNavigationPosition());
												
												Log.i("TabMapFragment getWalkingSteps", mMyBaiduMapNavigation.getWalkingSteps().toString());
												mMapRoutePopWindow.getDataFromWalkingStep(mMyBaiduMapNavigation.getWalkingSteps());
											
												//·����Ϣ��
												mMapRouteInfoBar = (MapRouteInfoBar)getActivity().findViewById(R.id.map_route_bar);
												mMapRouteInfoBar.getRoutePopWindow(mMapRoutePopWindow);
												//��ϸ��ť�¼�
												mMapRouteInfoBar.setMoreInfoOnClickListener(new OnClickListener() {
													
													@Override
													public void onClick(View v)
													{
														// TODO Auto-generated method stub
														//mMapRoutePopWindow = (MapRoutePopWindow)getActivity().findViewById(R.id.map_route_popw);
														mMapRoutePopWindow.setVisibility(View.VISIBLE);
														
													}
												});
												//������ť�¼�
												mMapRouteInfoBar.setNavigateBtnOnClickListener(new OnClickListener() {
													
													@Override
													public void onClick(View v)
													{
														// TODO Auto-generated method stub
														if(!mMapRouteInfoBar.getNavigateFlag()) //�򿪵���
														{
															Toast.makeText(getActivity(), "��������", Toast.LENGTH_SHORT).show();
															//mMyBaiduMapLocation.setMyNavigateIcon();
															mMyBaiduMapBasic.getBaiduMap().setMyLocationEnabled(true);
															
															mMapRouteInfoBar.setNavigateIconOn();
															mMapRouteInfoBar.setCancelBtnHide();
															mMapRouteInfoBar.setNavigateFlag();
															//mMyBaiduMapLocation.hideMyLocationMarker();
															mMyBaiduMapNavigation.getMyOrientationListener().start();
															mLocationClient.start();
														}
														else //�رյ���
														{
															Toast.makeText(getActivity(), "�رյ���", Toast.LENGTH_SHORT).show();
															
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
												
												
												mMapRouteInfoBar.setTextInfo("ȫ�� "
														+ mMyBaiduMapNavigation.getDistanceString() + "     ��ʱ "
														+ mMyBaiduMapNavigation.getTimeString());
												mMapRouteInfoBar.setVisibility(View.VISIBLE);
											}
											
											//���InfoWindow
											mMyBaiduMapBasic.getBaiduMap().hideInfoWindow();

										}
									});

							// �ƶ���ͼ����
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
							// ��ȡ����id
							// String _ClassifyId =
							// mMyBaiduMapAround.getClassifyIdList().get((int)
							// v.getTag());
							String _ClassifyId = "SZ10000001";

							// ����ClassifyInfoActivity
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

					// ���ù������ܱ߰�ť״̬
					mMapToolsBar.setAroundStatu(true);
				}
				else
				{
					// �����ܱ���Ϣ
					Toast.makeText(getActivity(), "�����ܱ���Ϣ", Toast.LENGTH_SHORT)
							.show();

					// ���Marker
					// mMyBaiduMapAround.clearAllAroundItem();
					mMyBaiduMapAround.removeAllAroundItem();
					Log.i("Class:TabMapFragment", "MarkerList 5:"
							+ mMyBaiduMapAround.getMarkerList().toString());

					// ����Pop����
					mMapImagePopWindow.setVisibility(View.GONE);

					// ����InfoWindow
					// mMyInfoWindowView.setVisibility(View.GONE);
					mMyBaiduMapBasic.getBaiduMap().hideInfoWindow();
					// ������е���·��
					mMyBaiduMapNavigation.clearRouteLine();
					// �������·����Ϣ����
					if(mMapRouteInfoBar != null)
					{
						mMapRouteInfoBar.setVisibility(View.GONE);
						mMapRoutePopWindow.setVisibility(View.GONE);
					}
					// ���ù������ܱ߰�ť״̬
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
				
				// ����ת��
				CoordinateConverter _Converter = new CoordinateConverter();
				_Converter
						.from(CoordinateConverter.CoordType.COMMON);
				
				// TODO Auto-generated method stub
				if(arg0.getTitle() == "ClassifyPosition")
				{
					// ������ص�marker
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
					// ��ʾInfoWindow
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

					// ��ʾInfoWindow
					mMyBaiduMapBasic.getBaiduMap().showInfoWindow(
							mInfoWindow);
					
					// �ƶ���ͼ����
					MapStatusUpdate _MapStatusUpdate;
					_MapStatusUpdate = MapStatusUpdateFactory
							.newLatLng(arg0.getPosition());
					mMyBaiduMapBasic.getBaiduMap().setMapStatus(
							_MapStatusUpdate);
				}
				else  // ����ܱߵ�marker
				{
					// marker��ֲ������
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
		
						// �ػ��mark
						
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
						
						// ��ʾInfoWindow
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
		
						// ����ת��
						_Converter.coord(mMyBaiduMapAround
								.getMarkPosition().get(temp));
						LatLng _LatLngDes = _Converter.convert();
		
						mInfoWindow = new InfoWindow(mMyInfoWindowView,
								_LatLngDes, -120);
		
						// ��ʾInfoWindow
						mMyBaiduMapBasic.getBaiduMap().showInfoWindow(
								mInfoWindow);
						
						// �ƶ���ͼ����
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
		
		
		// ��ʼ��λ
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
				// classify���ؽ��
				ArrayList<? extends Parcelable> _List = data
						.getParcelableArrayListExtra("ClassifyToMap");
				ArrayList<MyLatLng> _List1 = (ArrayList<MyLatLng>) _List;
				
				
				
				showClassifyItem(_List1);
				
				// serach���ؽ��
				/*ArrayList<? extends Parcelable> _SearchList = data
						.getParcelableArrayListExtra("SearchToMap");
				ArrayList<MyLatLng> _SearchList1 = (ArrayList<MyLatLng>) _SearchList;
				showClassifyItem(_SearchList1);*/
				
				break;

			case Activity.RESULT_CANCELED:
				
				Toast.makeText(getActivity(), "�����ȡ����ť", Toast.LENGTH_SHORT).show();
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
				// serach���ؽ��
				
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
		
		// ׼�� marker ��ͼƬ
		BitmapDescriptor mMarkBitmapCommon = BitmapDescriptorFactory
				.fromResource(R.drawable.locate_mark_item);

		CoordinateConverter _Converter = new CoordinateConverter();
		_Converter
				.from(CoordinateConverter.CoordType.COMMON);
		
		for (int i = 0; i < list.size(); i++)
		{
			//����ת��
			_Converter.coord(new LatLng(list.get(i).getLantitude(), list.get(i).getLongtitude()));
			_DesLatLng = _Converter.convert();
		
			//���mark
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
