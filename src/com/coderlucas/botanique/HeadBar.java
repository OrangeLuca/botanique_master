package com.coderlucas.botanique;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeadBar extends RelativeLayout
{
	//private static final int default_background_color = Color.rgb(26, 26, 26);  

	private ImageView mImageView;
	private TextView mTextView;
	
	public HeadBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.layout_headbar, this);
		
		mImageView = (ImageView)this.findViewById(R.id.headbar_image);

		mTextView = (TextView)this.findViewById(R.id.headbar_text);
		//mImageView.setClickable(true);
	}

	public void setImageResource(int resId)
	{
		mImageView.setImageResource(resId);
	}
	
	public void setTextTitleResource(String text)
	{
		mTextView.setText(text);
	}
	
	public void setHeadBarOnClickListener(OnClickListener listener)
	{
		mImageView.setOnClickListener(listener);
		
		/*if (v instanceof ViewGroup)
		{
			ViewGroup group = (ViewGroup) v;
			int count = group.getChildCount();
			for (int i = 0; i < count; i++)
			{
				View child = group.getChildAt(i);
				if (child instanceof ImageView)
				{
					setHeadBarOnClickListener(child, listener);
				}

				child.setOnClickListener(listener);
			}
		}*/
	}
	
}
