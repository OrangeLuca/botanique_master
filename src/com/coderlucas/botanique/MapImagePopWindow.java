package com.coderlucas.botanique;

import java.util.ArrayList;
import java.util.List;

import android.R.drawable;
import android.content.Context;
import android.provider.ContactsContract.Contacts.Data;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class MapImagePopWindow extends RelativeLayout
{

	private com.coderlucas.botanique.HorizontalListView mListView;
	

	
	public MapImagePopWindow(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//Log.i("MapRoutePopWindow", "Flag -1");
		
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.map_image_popwindow, this);
		
		//Log.i("MapRoutePopWindow", "Flag 0");
		/*mStringArray = new ArrayList<String>();
		mImageArray = new ArrayList<Integer>();*/
		
		//mAdapter = new HorizontalListViewAdapter(getContext(), getStringData(), getImageData(), imageListener, tipListener);
		
		//Log.i("MapRoutePopWindow", "Flag 1");
		/*WindowManager _WindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
	    Display _Display = _WindowManager.getDefaultDisplay(); 
*/
		/*DisplayMetrics  _DisplayMetrics = new DisplayMetrics(); 

		context.getWindowManager().getDefaultDisplay().getMetrics(_DisplayMetrics);      
		int screenWidth = dm.widthPixels;                
		int screenHeight = dm.heightPixels;

		
		Log.i("MapRoutePopWindow", "Flag 2");
		RelativeLayout.LayoutParams _Params = (RelativeLayout.LayoutParams)this.getLayoutParams();
		Log.i("MapRoutePopWindow", "flag 2.5");
		_Params.height = _Display.getHeight() * 3 / 5;
		_Params.width = _Display * 3 / 5;
		Log.i("MapRoutePopWindow", _Params.toString());
		this.setLayoutParams(_Params);*/

		//Log.i("MapRoutePopWindow", "Flag 3");
		mListView = (com.coderlucas.botanique.HorizontalListView)this.findViewById(R.id.map_image_poplist);
		
		//mListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,getData()));
		//Log.i("MapRoutePopWindow", "Flag 3");
		//mListView.setAdapter(new MapRouteListViewAdapter(getContext(), getData()));
		
		//Log.i("MapRoutePopWindow", "Flag 3.5");
		//mListView.setAlpha(0.8f);
		//mListView.setMinimumHeight(150);
		//Log.i("MapRoutePopWindow", "Flag 4");
		//mCancelBtn =  (TextView)this.findViewById(R.id.map_route_popbtn);
		//Log.i("MapRoutePopWindow", "Flag 5");
		/*mCancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				MapImagePopWindow.this.setVisibility(GONE);
			}
		});
		Log.i("MapRoutePopWindow", "Flag 6");*/
		MapImagePopWindow.this.setVisibility(GONE);
	}

	private ArrayList<String>  mStringArray; 
	private ArrayList<Integer>  mImageArray; 

 
	
	HorizontalListViewAdapter mAdapter;
	
	public void setAdapter(OnClickListener imageListener, OnClickListener tipListener)
	{
		//mListView.setAdapter(new HorizontalListViewAdapter(getContext(), getStringData(), getImageData()));
		mAdapter = new HorizontalListViewAdapter(getContext(), getStringData(), getImageData(), imageListener, tipListener);
		
		mListView.setAdapter(mAdapter);
	
	}
	
	public void clearImageArray()
	{
		mImageArray.clear();
	}
	
	public void clearStringArray()
	{
		mStringArray.clear();
	}
	
	public HorizontalListViewAdapter getAdapter()
	{
		return mAdapter;
	}
	


/*	public void setHorizontalListViewItemOnClickedListener(OnClickListener imageListener, OnClickListener tipListener)
	{
		Log.i("mAdapter", "setHorizontalListViewItemOnClickedListener start");
		mAdapter.setImageOnClickListener(imageListener);
		Log.i("mAdapter", "setImageOnClickListener start");
		mAdapter.setTextTipOnClickListener(tipListener);
		Log.i("mAdapter", "setTextTipOnClickListener start");
	}*/
	
	public void getDataFromSource(ArrayList<Integer> drawables, ArrayList<String> strings)
	{
		mImageArray = drawables;
		mStringArray = strings;
	}

	
	private List<Integer> getImageData()
	{
		List<Integer> data = new ArrayList<Integer>();
		for(int i=0; i < mImageArray.size(); i++)
		{
			data.add(mImageArray.get(i));

		}
		mImageArray = null;
		return data;
	}
	
	private List<String> getStringData()
	{
		List<String> data = new ArrayList<String>();
		
		for(int i=0; i < mStringArray.size(); i++)
		{
			data.add(mStringArray.get(i));
		}

		return data;
	}
	
	/*private List<Map<String, Object>> getImageData()
	{
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		
		for(int i = 0; i < mImageArray.size(); i++)
		{
			Map<String, Object> _Map = new HashMap<String, Object>();
			_Map.put("map_image_pic", mImageArray.get(i));
			
			data.add(_Map);
		}	
		mImageArray.clear();
		//mWalkingSteps.clear();
		return data;
	}*/
	
	/*private List<Map<String, Object>> getStringData()
	{
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		
		for(int i = 0; i < mStringArray.size(); i++)
		{
			Map<String, Object> _Map = new HashMap<String, Object>();
			_Map.put("map_image_name", mStringArray.get(i));
			
			data.add(_Map);
		}	
		mStringArray.clear();
		//mWalkingSteps.clear();
		return data;
	}
	*/
	/*private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		
		for(int i = 0; i < mStringArray.length; i++)
		{
			Map<String, Object> _Map = new HashMap<String, Object>();
			_Map.put("route_info", mStringArray[i]);
			
			data.add(_Map);
		}	
		return data;
	}*/
	
	public void setVerticalScrollBarEnabled(Boolean arg0)
	{
		mListView.setVerticalScrollBarEnabled(arg0);
	}
	
	public void setPopWindowSize(int width, int height)
	{
		Log.i("Class:MapImagePopWindow", "Method:setPopWindowSize(int,int) : flag 1");
		RelativeLayout.LayoutParams _Params = (RelativeLayout.LayoutParams)this.getLayoutParams();
		Log.i("Class:MapImagePopWindow", "Method:setPopWindowSize(int,int) : flag 2");
		_Params.height = height * 1 / 3;
		_Params.width = width;
		Log.i("Class:MapImagePopWindow", "Method:setPopWindowSize(int,int) : flag 3");
		Log.i("MapImagePopWindow", _Params.toString());
		this.setLayoutParams(_Params);
		Log.i("Class:MapImagePopWindow", "Method:setPopWindowSize(int,int) : flag 4");
	}
	
/*	public void addItem(String str)
	{
		mListView.add
	}*/
	

	
}
