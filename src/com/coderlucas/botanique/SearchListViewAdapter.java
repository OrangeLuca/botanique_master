package com.coderlucas.botanique;

import java.util.List;

import com.coderlucas.botanique.MapRouteListViewAdapter.ListItemView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchListViewAdapter extends BaseAdapter
{
	private List<Integer> mIconIDs;
	private List<String>  mTitles;
	private Context mContext;
	private LayoutInflater mInflater;
	Bitmap iconBitmap;
	private int selectIndex = -1;
	
	View.OnClickListener mBtnOnClickListener;
	View.OnClickListener mImageClickListener;
	View.OnClickListener mTitleClickListener;
	
	private static class ViewHolder
	{
		private ImageView mImage;
		private TextView mTitle;
		private ImageView mBtn;
	}
	
	ViewHolder mHolder;
	
	
	public SearchListViewAdapter(Context context, List<String> titles,
			List<Integer> ids, OnClickListener btnListener, OnClickListener imageClickListener, OnClickListener titleClickListener)
	{
		mBtnOnClickListener = btnListener;
		mImageClickListener = imageClickListener;
		mTitleClickListener = titleClickListener;
		this.mContext = context;
		this.mIconIDs = ids;
		this.mTitles = titles;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// LayoutInflater.from(mContext);
	}

	public void setSelectIndex(int i)
	{
		selectIndex = i;
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mIconIDs.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		if (convertView == null)
		{
			mHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.search_listview_item, null);
			mHolder.mImage = (ImageView) convertView
					.findViewById(R.id.search_list_item_image);
			mHolder.mTitle = (TextView) convertView
					.findViewById(R.id.search_list_item_title);
			mHolder.mBtn = (ImageView) convertView
					.findViewById(R.id.search_list_item_btn);
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}
		if (position == selectIndex)
		{
			convertView.setSelected(true);
		}
		else
		{
			convertView.setSelected(false);
		}

		mHolder.mTitle.setText(mTitles.get(position));
		mHolder.mTitle.setTag(position);
		Log.i("mHolder.mTitle.setTag", ""+position);
		mHolder.mTitle.setOnClickListener(mTitleClickListener);
		
		mHolder.mImage.setImageResource(mIconIDs.get(position));
		mHolder.mImage.setTag(position);
		Log.i("mHolder.mImage.setTag", ""+position);
		mHolder.mImage.setOnClickListener(mImageClickListener);
		
		mHolder.mBtn.setTag(position);
		Log.i("mHolder.mBtn.setTag", ""+position);
		mHolder.mBtn.setOnClickListener(mBtnOnClickListener);
		return convertView;
	}

}

	
