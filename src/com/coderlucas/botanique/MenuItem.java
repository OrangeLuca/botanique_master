package com.coderlucas.botanique;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuItem extends RelativeLayout
{
	private ImageView mImageView;
	private TextView mTextViewTitle;

	
	public MenuItem(Context context)
	{
		super(context);
	}
	
	public MenuItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//Log.i("MenuItem", "flag10");
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.menu_item, this);
		//Log.i("MenuItem", "flag11");
		mImageView = (ImageView)findViewById(R.id.item_imageview);
		mTextViewTitle = (TextView)findViewById(R.id.item_title);
		
		//Log.i("MenuItem", "flag12");
		//mTextViewTitle.setText("AAA");
		//mTextViewInfo.setText("aaa");	
		//mImageView.setImageResource(R.drawable.logo);
		//Log.i("MenuItem", "flag13");
	}
	
	public void setImageResource(int resId)
	{
		mImageView.setImageResource(resId);
	}
	
	public void setTextTitleResource(String text)
	{
		//Log.i("MenuItem", "flag11 "+ text);
		mTextViewTitle.setText(text);
	}
	
	public void setMenuItemOnClickListener(View v, OnClickListener listener)
	{
		
		if (v instanceof ViewGroup)
		{
			ViewGroup group = (ViewGroup) v;
			int count = group.getChildCount();
			for (int i = 0; i < count; i++)
			{
				View child = group.getChildAt(i);
				if (child instanceof LinearLayout
						|| child instanceof RelativeLayout)
				{
					setMenuItemOnClickListener(child, listener);
				}

				/*
				 * if (child instanceof TextView) { TextView text =
				 * (TextView)child; text.setFocusable(false); }
				 */
				child.setOnClickListener(listener);
			}
		}
	}

	
}
