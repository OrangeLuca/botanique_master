package com.coderlucas.botanique;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

public class MyInfoWindow extends LinearLayout
{
	private Context mContext;
	private TextView mTextView;
	private TextView mTextViewTip;
	private ImageView mImageView;
	
	
/*	private LatLng mStartLatLng;
	private LatLng mEndLatLng;*/
	private LatLng mLatLng;
/*	private double mLantitude = 0;
	private double mLongtitude = 0;*/
	
	public MyInfoWindow(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		
		mConverter = new CoordinateConverter();
		mConverter.from(CoordinateConverter.CoordType.COMMON);
		
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.layout_infowindow, this);
		
		mTextViewTip = (TextView)findViewById(R.id.infowindow_tip);
		
		
		mTextView = (TextView)findViewById(R.id.infowindow_title);
		mTextView.setTextColor(Color.WHITE);
		mTextView.setTextSize(18);
		mTextView.setBackgroundColor(Color.BLUE);
		mTextView.setClickable(false);
		mTextView.setFocusable(false);
		
		/*mImageView = (ImageView)findViewById(R.id.infowindow_image);
		mImageView.setScaleType(ScaleType.CENTER_INSIDE);*/

	}

	
	public void setImage(int resId)
	{
		/*mImageView.setBackgroundResource(resId);
		LayoutParams _Params = new LayoutParams(240, 180);
		mImageView.setLayoutParams(_Params);
		mTextViewTip.setVisibility(TextView.VISIBLE);*/
	}
	
	public void setTextTitle(String str)
	{
		mTextView.setText(str);
	}
	
	public void setImageClickable(Boolean arg0)
	{
		/*mImageView.setClickable(arg0);*/
	}
	
	CoordinateConverter mConverter;
	
	public double getLantitude()
	{
		return mLatLng.latitude;
	}
	
	public double getLongtitude()
	{
		return mLatLng.longitude;
	}
	
	public LatLng getNavigationPosition()
	{
		return mLatLng;
	}
	
	public void setNavigationPosition(LatLng latLng)
	{
		/*mConverter.coord(latLng);
		mLatLng = mConverter.convert();*/
		mLatLng = latLng;
	}
	
	private String mName = "";
	
	public void setNavigationName(String str)
	{
		mName = str;
	}
	
	public String getNavigationName()
	{
		return mName;
	}
	
	public void setTextViewTipOnClickListener(OnClickListener listener)
	{
		mTextViewTip.setOnClickListener(listener);
	}
	
	public void reset()
	{
		/*mImageView = (ImageView)findViewById(R.id.infowindow_image);*/
	}
	
	public void hideImageInfo()
	{
		/*mImageView.setVisibility(GONE);*/
		//mTextViewTip.setVisibility(GONE);
	}
	
	public void showImageInfo()
	{
/*		mImageView.setVisibility(VISIBLE);*/
		//mTextViewTip.setVisibility(VISIBLE);
	}
	
	public void hideTitle()
	{
		mTextView.setVisibility(GONE);
	}
	
	public void hideTip()
	{
		mTextViewTip.setVisibility(GONE);
	}
	
	public void showTip()
	{
		mTextViewTip.setVisibility(VISIBLE);
	}
	
	public void showTitle()
	{
		mTextView.setVisibility(VISIBLE);
	}
	
}
