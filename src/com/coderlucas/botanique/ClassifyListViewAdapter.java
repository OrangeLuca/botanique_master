package com.coderlucas.botanique;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ClassifyListViewAdapter extends BaseAdapter
{
	private Context mContext;
	private List<Map<String, Object>> mListItems;
	private LayoutInflater mListContainer;
	
	public final class ListItemView
	{
		public TextView mTextView;
	}

	public ClassifyListViewAdapter(Context context,
			List<Map<String, Object>> listItems)
	{
		this.mContext = context;
		 // 创建视图容器并设置上下文
		mListContainer = LayoutInflater.from(context);
		this.mListItems = listItems;

	}

	 
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mListItems.size();
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

	
	
	 /**  
	  * ListView Item 
	  */  
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		 //final int selectID = position;   
		 //自定义视图   
		 ListItemView _ListItemView = null;
		 if(convertView == null)
		 {
			 _ListItemView = new ListItemView();
			 //获取布局文件
			 convertView = mListContainer.inflate(R.layout.map_listview_item, null);
			 //获取对象
			 _ListItemView.mTextView = (TextView)convertView.findViewById(R.id.list_text);
			 //设置控件到convertView
			 convertView.setTag(_ListItemView);
		 }
		 else 
		 {
			 _ListItemView = (ListItemView)convertView.getTag();
		 }
		 
		 _ListItemView.mTextView.setText((String)mListItems.get(position).get("classify"));
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

}
