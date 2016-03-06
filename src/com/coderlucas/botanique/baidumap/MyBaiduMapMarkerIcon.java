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
		// ����������������ǳ���Ҫ�����û�е�������������õ���bitmapΪnull
		this.measure(MeasureSpec.makeMeasureSpec(256, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(256, MeasureSpec.EXACTLY));
		// �������Ҳ�ǳ���Ҫ�����ò��ֵĳߴ��λ��
		Log.i("MyBaiduMapMarkerIcon", "flag 9");
		this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
		
		// ����bitmap
		Log.i("MyBaiduMapMarkerIcon", "flag 10");
		Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
				Bitmap.Config.RGB_565);
		// ����bitmap���ɻ���
		Log.i("MyBaiduMapMarkerIcon", "flag 11");
		Canvas canvas = new Canvas(bitmap);
		// ��view�е����ݻ����ڻ�����
		Log.i("MyBaiduMapMarkerIcon", "flag 12");
		this.draw(canvas);
		Log.i("MyBaiduMapMarkerIcon", "flag 13");
		return bitmap;
	}
}
