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
		//���ز���
		this.mInflater = LayoutInflater.from(context);
		//���ݴ���mData
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
		//�ڴ��������е����ݼ���Ŀ��
		return mData.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		//��ȡ���ݼ�����ָ��������Ӧ��������
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		//��ȡ���б�����ָ��������Ӧ����id
		return position;
	}

	
	 (non-Javadoc) 
	 * <p>Title: getView</p> 
	 * <p>Description: ��ȡһ�������ݼ���ָ����������ͼ����ʾ����</p> 
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
        //�������convertViewΪ�գ�����Ҫ����View
        if(convertView == null)
        {
        	_ViewHolder = new ViewHolder();
            //�����Զ����Item���ּ��ز���
            convertView = mInflater.inflate(R.layout.menu_item, null);
            _ViewHolder.img = (ImageView)convertView.findViewById(R.id.listview_item_imageview);
            _ViewHolder.title = (TextView)convertView.findViewById(R.id.listview_item_title);
            _ViewHolder.info = (TextView)convertView.findViewById(R.id.listview_item_info);
            //�����úõĲ��ֱ��浽�����У�������������Tag��Ա���淽��ȡ��Tag
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
