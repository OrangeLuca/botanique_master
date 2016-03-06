package com.coderlucas.botanique;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapRouteInfoBar extends LinearLayout
{

	private TextView mTextViewInfo;
	private TextView mTextViewMore;
	private ImageView mCancelBtn;
	private ImageView mNavigateBtn;
	
	public MapRouteInfoBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.map_route_infobar, this);
		
		mTextViewInfo = (TextView)this.findViewById(R.id.map_route_text);
		
		mTextViewMore = (TextView)this.findViewById(R.id.map_route_moreinfo);
		
		mNavigateBtn = (ImageView)this.findViewById(R.id.map_route_navigate);
		
		mCancelBtn = (ImageView)this.findViewById(R.id.map_route_cancel_btn);
	
		mCancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				MapRouteInfoBar.this.setVisibility(GONE);
				mRoutePopWindow.setVisibility(GONE);
			}
		});
		MapRouteInfoBar.this.setVisibility(GONE);
	}
	
	
	private Boolean mNavigateFlag = false;
	
	public void setTextInfo(String str)
	{
		mTextViewInfo.setText(str);
	}
	
/*	public void setVisible(int arg0)
	{
		mTextViewInfo.setVisibility(arg0);
		mTextViewMore.setVisibility(arg0);
		mCancelBtn.setVisibility(arg0);
	}*/
	public void setMoreInfoOnClickListener(OnClickListener listener)
	{
		mTextViewMore.setOnClickListener(listener);
	}
	
	public void setNavigateBtnOnClickListener(OnClickListener listener)
	{
		mNavigateBtn.setOnClickListener(listener);
	}


	public void setNavigateFlag()
	{
		mNavigateFlag = true;
	}
	
	public void clearNavigateFlag()
	{
		mNavigateFlag = false;
	}
	
	public Boolean getNavigateFlag()
	{
		return mNavigateFlag;
	}

	public void setCancelBtnHide()
	{
		mCancelBtn.setVisibility(GONE);
	}
	
	public void setCancelBtnShow()
	{
		mCancelBtn.setVisibility(VISIBLE);
	}
	
	Drawable mDrawableNaviOn = getResources().getDrawable(R.drawable.navigation_bg_on);
	Drawable mDrawableNaviOff  = getResources().getDrawable(R.drawable.navigation_bg);
	
	public void setNavigateIconOn()
	{
		mNavigateBtn.setImageDrawable(mDrawableNaviOn);
	}
	
	public void setNavigateIconOff()
	{
		mNavigateBtn.setImageDrawable(mDrawableNaviOff);
	}
	
	View mRoutePopWindow;
	
	public void getRoutePopWindow(View v)
	{
		mRoutePopWindow = v;
	}
}


