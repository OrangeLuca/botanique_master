package com.coderlucas.botanique.baidumap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderlucas.botanique.R;

public class MyBaiduMapMarkerIcon extends RelativeLayout
{
	private ImageView mImageView;
	private TextView mTextView;

	public MyBaiduMapMarkerIcon(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		Log.i("MyBaiduMapMarkerIcon", "flag 1");
		LayoutInflater _LayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.i("MyBaiduMapMarkerIcon", "flag 2");
		_LayoutInflater.inflate(R.layout.baidumap_marker, this);

		Log.i("MyBaiduMapMarkerIcon", "flag 3");
		mImageView = (ImageView) findViewById(R.id.marker_image);
		Log.i("MyBaiduMapMarkerIcon", "flag 4");
		mTextView = (TextView) findViewById(R.id.marker_text);
		Log.i("MyBaiduMapMarkerIcon", "flag 5");
		//mTextView.setText(null);

	}

	public void setText(String pStr)
	{
		Log.i("MyBaiduMapMarkerIcon", "flag 6");
		mTextView.setText(pStr);
		Log.i("MyBaiduMapMarkerIcon", "flag 7");
	}

	public void setImage(Drawable pDrawable)
	{
		mImageView.setImageDrawable(pDrawable);
	}

	public Bitmap toBitmap()
	{
		Log.i("MyBaiduMapMarkerIcon", "flag 8");
		// 调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
		this.measure(MeasureSpec.makeMeasureSpec(256, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(256, MeasureSpec.EXACTLY));
		// 这个方法也非常重要，设置布局的尺寸和位置
		Log.i("MyBaiduMapMarkerIcon", "flag 9");
		this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
		
		// 生成bitmap
		Log.i("MyBaiduMapMarkerIcon", "flag 10");
		Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
				Bitmap.Config.RGB_565);
		// 利用bitmap生成画布
		Log.i("MyBaiduMapMarkerIcon", "flag 11");
		Canvas canvas = new Canvas(bitmap);
		// 把view中的内容绘制在画布上
		Log.i("MyBaiduMapMarkerIcon", "flag 12");
		this.draw(canvas);
		Log.i("MyBaiduMapMarkerIcon", "flag 13");
		return bitmap;
	}
}
