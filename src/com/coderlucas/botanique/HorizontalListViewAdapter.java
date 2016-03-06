package com.coderlucas.botanique;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalListViewAdapter extends BaseAdapter
{
	private List<Integer> mIconIDs;
	private List<String>  mTitles;
	private Context mContext;
	private LayoutInflater mInflater;
	Bitmap iconBitmap;
	private int selectIndex = -1;

	public List<Integer> getIconId()
	{
		return mIconIDs;
	}
	
	public HorizontalListViewAdapter(Context context, List<String> titles,
			List<Integer> ids, OnClickListener imageListener, OnClickListener tipListener)
	{
		mImageOnClickListener = imageListener;
		mTipOnClickListener = tipListener;
		this.mContext = context;
		this.mIconIDs = ids;
		this.mTitles = titles;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// LayoutInflater.from(mContext);
	}

	@Override
	public int getCount()
	{
		return mIconIDs.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
	ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		//ViewHolder holder;
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.map_image_item, null);
			holder.mImage = (ImageView) convertView
					.findViewById(R.id.map_image);
			holder.mTitle = (TextView) convertView
					.findViewById(R.id.map_image_char);
			holder.mGo = (TextView) convertView.findViewById(R.id.map_image_go);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		if (position == selectIndex)
		{
			convertView.setSelected(true);
		}
		else
		{
			convertView.setSelected(false);
		}

		holder.mTitle.setText(mTitles.get(position));
		//iconBitmap = getPropThumnail(mIconIDs.get(position));
		//holder.mImage.setImageBitmap(iconBitmap);
		holder.mImage.setImageResource(mIconIDs.get(position));
		Log.i("holder.mImage", "setOnClickListener start");
		holder.mImage.setTag(position);
		Log.i("holder.mImage.setTag", ""+position);
		holder.mImage.setOnClickListener(mImageOnClickListener);
		Log.i("holder.mImage", "setOnClickListener success");
		Log.i("holder.mGo", "setOnClickListener start");
		holder.mGo.setOnClickListener(mTipOnClickListener);
		Log.i("holder.mGo", "setOnClickListener success");
		return convertView;
	}

	View.OnClickListener mImageOnClickListener;
	View.OnClickListener mTipOnClickListener;
	

	
	private static class ViewHolder
	{
		private TextView mTitle;
		private ImageView mImage;
		private TextView mGo;
	}

	private Bitmap getPropThumnail(int id)
	{
		// Drawable d = mContext.getResources().getDrawable(id);
		// Bitmap b = BitmapUtil.drawableToBitmap(d);
		// Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
		// int w =
		// mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
		// int h =
		// mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);

		// Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);

		// return thumBitmap;
		return null;
	}

	public void setSelectIndex(int i)
	{
		selectIndex = i;
	}
	

}