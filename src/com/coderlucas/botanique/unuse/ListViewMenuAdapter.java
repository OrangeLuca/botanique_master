package com.coderlucas.botanique.unuse;
/*
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewMenuAdapter extends BaseAdapter
{

	private LayoutInflater mInflater = null;
	public List<Map<String, Object>> mData;

	public void getData(List<Map<String, Object>> data)
	{
		mData = data;
	}
	
	public ListViewMenuAdapter(List<Map<String, Object>> mData, Context context)
	{
		//加载布局
		this.mInflater = LayoutInflater.from(context);
		//数据传到mData
		//mData = getData();
	}
	
	
	static class ViewHolder
    {
        public ImageView img;
        public TextView title;
        public TextView info;
    }

	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		//在此适配器中的数据集条目数
		return mData.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		//获取数据集中与指定索引对应的数据项
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		//获取在列表中与指定索引对应的行id
		return position;
	}

	
	 (non-Javadoc) 
	 * <p>Title: getView</p> 
	 * <p>Description: 获取一个在数据集中指定索引的视图来显示数据</p> 
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return 
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) 
	  
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder _ViewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
        	_ViewHolder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.menu_item, null);
            _ViewHolder.img = (ImageView)convertView.findViewById(R.id.listview_item_imageview);
            _ViewHolder.title = (TextView)convertView.findViewById(R.id.listview_item_title);
            _ViewHolder.info = (TextView)convertView.findViewById(R.id.listview_item_info);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(_ViewHolder);
        }
        else
        {
        	_ViewHolder = (ViewHolder)convertView.getTag();
        }
        _ViewHolder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
        _ViewHolder.title.setText((String)mData.get(position).get("title"));
        _ViewHolder.info.setText((String)mData.get(position).get("info"));
        
        return convertView;
	}

}*/
