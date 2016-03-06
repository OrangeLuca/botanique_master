package com.coderlucas.botanique;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

public class MapButton extends Button
{
	private Bitmap mBitmap;

	public MapButton(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MapButton(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
		this.setClickable(true);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_switch_btn_bg);

	}

/*	public void setIcon(int resourceId)
	{
		this.mBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
		invalidate();
	}*/

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO Auto-generated method stub

		// 图片顶部居中
		int x = (this.getMeasuredWidth() - mBitmap.getWidth()) / 2;
		int y = 0;
		canvas.drawBitmap(mBitmap, x, y, null);
		// 坐标需要转换，因为默认情况下Button中的文字居中显示
		// 这里需要让文字在底部显示
		canvas.translate(0,
				(this.getMeasuredHeight() / 2) - (int) this.getTextSize());

		super.onDraw(canvas);
	}

}
