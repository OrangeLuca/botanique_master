package com.coderlucas.botanique;

import com.coderlucas.botanique.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;

public class MapToolsBar extends LinearLayout
{
	private Button mMapSwitchBtn;
	private Button mMapLocateBtn;
	private Button mMapAroundBtn;
	private BaiduMap mBaiduMap;
	private Context mContext;
	private VerticalSeekBar mVerticalSeekBar;
	private EditText mEditText;
	
	private int mDistanceAround = 10;
	private boolean mAroundBtnStatu = false;
	
	final int MAX_DISTANCE = 100; //100米
	final int MIN_DISTANCE = 10; //10米

	private static Drawable mDrawableMapNormal;
	private static Drawable mDrawableMapSatellite;
	private static Drawable mDrawableMapLocate;
	private static Drawable mDrawableMapLocateProcess;
	private static Drawable mDrawableMapAroundShow;
	private static Drawable mDrawableMapAroundHide;
	//private static Drawable mDrawableMapLocateRun;
	

	
	//普通地图模式
	boolean MAP_SWITCH_FLAG = false;
	
	public MapToolsBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		//Log.i("MapToolsBar", "flag 1");
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.layout_map_toolbar, this);
		//Log.i("MapToolsBar", "flag 2");
		mMapSwitchBtn = (Button)this.findViewById(R.id.map_switch_btn);
		
		
		/* 普通地图模式 */
		MAP_SWITCH_FLAG = true;
		//mMapSwitchBtn.setText("卫星");
		//mMapSwitchBtn.setTextColor(Color.WHITE);
		mDrawableMapNormal = getResources().getDrawable(R.drawable.map_switch_button_m);
		mDrawableMapSatellite  = getResources().getDrawable(R.drawable.map_switch_button_s);
		
		//mMapSwitchBtn.setCompoundDrawables(null, _Drawable, null, null);
		mMapSwitchBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapSatellite, null, null, null);
		//mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		//mMapSwitchBtn.setBackground(getResources().getDrawable(R.drawable.map_switch_button_s, null));
		/*mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);*/
		
		
		mMapSwitchBtn.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if(MAP_SWITCH_FLAG)
				{
					/* 卫星地图模式 */
					MAP_SWITCH_FLAG = false;
					//mMapSwitchBtn.setText("地图");
					//mMapSwitchBtn.setTextSize(10);
					//mMapSwitchBtn.setTextColor(Color.GREEN);
/*					mMapSwitchBtn.setCompoundDrawables(null, 
							getResources().getDrawable(R.drawable.map_switch_button_m), null, null);*/
					mMapSwitchBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapNormal, null, null, null);
					//设置卫星地图
					mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
					
				}
				else 
				{
					/* 普通地图模式 */
					MAP_SWITCH_FLAG = true;
					//mMapSwitchBtn.setText("卫星");
					//mMapSwitchBtn.setTextSize(10);
					//mMapSwitchBtn.setTextColor(Color.WHITE);
					/*mMapSwitchBtn.setCompoundDrawables(null, 
							getResources().getDrawable(R.drawable.map_switch_button_m, null), null, null);*/
					mMapSwitchBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapSatellite, null, null, null);
					mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
				}
			}
		});
		
		mMapLocateBtn = (Button)this.findViewById(R.id.locate_btn);
		//mMapLocateBtn.setText("定位");
		//mMapLocateBtn.setTextSize(10);
		//mMapLocateBtn.setTextColor(Color.WHITE);
		mDrawableMapLocate = getResources().getDrawable(R.drawable.map_locate_button_n);
		mDrawableMapLocateProcess = getResources().getDrawable(R.drawable.map_locate_button_p);
		mMapLocateBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapLocate, null, null, null);
		
		/*mMapLocateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(mContext, "map tool bar: Locate", Toast.LENGTH_SHORT).show();
				InitLocation();
				mLocationClient.start();
			}
		});*/
		//Log.i("MapToolsBar", "flag 3");
		
		mDrawableMapAroundShow = getResources().getDrawable(R.drawable.map_toolbar_around_s);
		mDrawableMapAroundHide = getResources().getDrawable(R.drawable.map_toolbar_around_h);
		mMapAroundBtn = (Button)findViewById(R.id.map_around_btn);
		mMapAroundBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapAroundShow, null, null, null);
		
		mEditText = (EditText)findViewById(R.id.edittext_around);
		mEditText.setText(String.valueOf(mDistanceAround)+"m");
		/*mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "EditText afterTextChanged ", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub
				//mSeekBar.setProgress(Integer.valueOf(mEditText.getText().toString()));
				Toast.makeText(mContext, "EditText afterTextChanged ", Toast.LENGTH_SHORT).show();
				mVerticalSeekBar.setProgress(Integer.valueOf(mEditText.getText().toString()));
			}
		});
*/
		//mDistanceAround = Integer.valueOf(mEditText.getText().toString());
				
		//Log.i("MapToolsBar", "flag 4");
		mVerticalSeekBar = (VerticalSeekBar)findViewById(R.id.seekBar_around);
		//Log.i("MapToolsBar", "flag 4.1");
		//mSeekBar.setMinimumHeight(200);
		mVerticalSeekBar.setMax(MAX_DISTANCE-MIN_DISTANCE);
		//Log.i("MapToolsBar", "flag 4.2");
		mVerticalSeekBar.setProgress(mDistanceAround-MIN_DISTANCE);
		//Log.i("MapToolsBar", "flag 5");
		mVerticalSeekBar.setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(VerticalSeekBar VerticalSeekBar)
			{
				// TODO Auto-generated method stub
				//mDistanceAround = Integer.valueOf(mEditText.getText().toString());
				//Toast.makeText(mContext, "mSeekBar:mDistanceAround value is " + mDistanceAround, Toast.LENGTH_SHORT).show();
				//Toast.makeText(mContext, "SeekBar onStopTrackingTouch ", Toast.LENGTH_SHORT).show();
				mDistanceAround = mVerticalSeekBar.getProgress() + MIN_DISTANCE;
				mEditText.setAlpha(0.5f);
				mVerticalSeekBar.setAlpha(0.4f);
			}
			
			@Override
			public void onStartTrackingTouch(VerticalSeekBar VerticalSeekBar)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(mContext, "SeekBar onStartTrackingTouch ", Toast.LENGTH_SHORT).show();
				mEditText.setAlpha(0.8f);
				mVerticalSeekBar.setAlpha(0.8f);
			}
			
			@Override
			public void onProgressChanged(VerticalSeekBar VerticalSeekBar, int progress, boolean fromUser)
			{
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				//seekBar.setProgress(progress);
				//mDistanceAround = seekBar.getProgress();
				//mEditText.setText(progress);
				//Toast.makeText(mContext, "mDistanceAround value is " + mDistanceAround, Toast.LENGTH_SHORT).show();
				//progress = Integer.valueOf(mEditText.getText().toString());
				//Toast.makeText(mContext, "SeekBar onProgressChanged ", Toast.LENGTH_SHORT).show();

				mEditText.setText(String.valueOf(progress + MIN_DISTANCE)+"m");
			}
		});
			
		//Log.i("MapToolsBar", "flag 6");
	}
	
	public int getDistanceAround()
	{
		return mDistanceAround;
	}
	
	public boolean getAroundStatu()
	{
		return mAroundBtnStatu;
	}
	
	public void setAroundStatu(boolean arg0)
	{
		mAroundBtnStatu = arg0;
		if(arg0)
		{
			mMapAroundBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapAroundHide, null, null, null);
		}
		else
		{
			mMapAroundBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapAroundShow, null, null, null);
		}
	}
	
	public void getBaiduMap(BaiduMap bmap)
	{
		mBaiduMap = bmap;
	}
	
	private LocationClient mLocationClient;
	
	public void getLocationClient(LocationClient mLoc)
	{
		mLocationClient = mLoc;
	}
	
	public void setLocateToolOnClickListener(OnClickListener listener)
	{
		mMapLocateBtn.setOnClickListener(listener);
	}
	
	
	public void setAroundOnClickListener(OnClickListener listener)
	{
		mMapAroundBtn.setOnClickListener(listener);
	}
	
	public void setAroundIconShow()
	{
		mMapAroundBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapAroundShow, null, null, null);
	}
	
	public void setAroundIconHide()
	{
		mMapAroundBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapAroundHide, null, null, null);
	}
	
	public void setLocateIconWait()
	{
		mMapLocateBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapLocate, null, null, null);
	}
	
	public void setLocateIconProcess()
	{
		mMapLocateBtn.setCompoundDrawablesWithIntrinsicBounds(mDrawableMapLocateProcess, null, null, null);
	}
	


}
