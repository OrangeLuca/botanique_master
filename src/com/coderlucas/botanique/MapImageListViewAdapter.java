package com.coderlucas.botanique;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MapImageListViewAdapter extends BaseAdapter
{
	private Context mContext;
	private List<Map<String, Object>> mListStringItems;
	private List<Map<String, Object>> mListImageItems;
	private LayoutInflater mListContainer;
	
	public final class ListItemView
	{
		public TextView mTextViewTitle;
		public TextView mTextViewGo;
		public ImageView mImageView;
	}

	public MapImageListViewAdapter(Context context,
			List<Map<String, Object>> listStringItems, 
			List<Map<String, Object>> listImageItems)
	{
		this.mContext = context;
		 // 创建视图容器并设置上下文
		mListContainer = LayoutInflater.from(context);
		this.mListStringItems = listStringItems;
		this.mListImageItems = listImageItems;
	}

	 
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mListStringItems.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	ListItemView _ListItemView = null;
	 /**  
	  * ListView Item 
	  */  
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		 //final int selectID = position;   
		 //自定义视图   
		// ListItemView _ListItemView = null;
		 if(convertView == null)
		 {
			 _ListItemView = new ListItemView();
			 //获取布局文件
			 convertView = mListContainer.inflate(R.layout.map_image_item, null);
			 
			 //获取对象
			 _ListItemView.mImageView = (ImageView)convertView.findViewById(R.id.map_image);
			 _ListItemView.mTextViewTitle = (TextView)convertView.findViewById(R.id.map_image_char);
			 _ListItemView.mTextViewGo = (TextView)convertView.findViewById(R.id.map_image_go);
			 //设置控件到convertView
			 convertView.setTag(_ListItemView);
		 }
		 else 
		 {
			 _ListItemView = (ListItemView)convertView.getTag();
		 }
		 
		 _ListItemView.mTextViewTitle.setText((String)mListStringItems.get(position).get("map_image_name"));
		 _ListItemView.mImageView.setBackgroundResource((Integer)mListImageItems.get(position).get("map_image_pic"));
		 /*_ListItemView.mTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "List view TextView Clicked", Toast.LENGTH_SHORT).show();
			}
		});*/
		 
		return convertView;
	}
	
	public void setImageOnClickedListener(OnClickListener listener)
	{
		_ListItemView.mImageView.setOnClickListener(listener);
	}
	
	
	public void setTextOnClickedListener(OnClickListener listener)
	{
		_ListItemView.mTextViewGo.setOnClickListener(listener);
	}

}
