package com.coderlucas.botanique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;

public class MapRoutePopWindow extends RelativeLayout
{

	private ListView mListView;
	private ImageView mCancelBtn;
	private List<WalkingStep> mWalkingSteps;
	
	public MapRoutePopWindow(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.i("MapRoutePopWindow", "Flag -1");
		
		LayoutInflater _LayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.map_route_popwindow, this);
		
		Log.i("MapRoutePopWindow", "Flag 0");
		mWalkingSteps = new ArrayList<WalkingRouteLine.WalkingStep>();
		
		Log.i("MapRoutePopWindow", "Flag 1");
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
		mListView = (ListView)this.findViewById(R.id.map_route_poplist);
		
		//mListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,getData()));
		Log.i("MapRoutePopWindow", "Flag 3");
		//mListView.setAdapter(new MapRouteListViewAdapter(getContext(), getData()));
		
		Log.i("MapRoutePopWindow", "Flag 3.5");
		//mListView.setAlpha(0.8f);
		//mListView.setMinimumHeight(150);
		//Log.i("MapRoutePopWindow", "Flag 4");
		mCancelBtn =  (ImageView)this.findViewById(R.id.map_route_popbtn);
		//Log.i("MapRoutePopWindow", "Flag 5");
		mCancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				MapRoutePopWindow.this.setVisibility(GONE);
			}
		});
		Log.i("MapRoutePopWindow", "Flag 6");
		MapRoutePopWindow.this.setVisibility(GONE);
	}

	ArrayList<String>  mStringArray = new ArrayList<String> (); 

/*	private String[] mStringArray = {
			"1. 向前100米", 
			"2. 向前100米向前100米", 
			"3. 向前100米向前100米向前100米", 
			"4. 向前100米向前100米向前100米向前100米", 
			"5. 向前100米向", 
			"6. 向前100米向",
			"7. 向前100米向",
			"8. 向前100米向",
			"9. 向前100米",
			"10. 向前100米",
			"11. 向前100米",
			"12. 向前100米",
			"13. 向前100米",
			"14. 向前100米",
			"15. 向前100米",
	};   */
	
	public void setAdapter()
	{
		mListView.setAdapter(new MapRouteListViewAdapter(getContext(), getData()));
	}
	
	
	public String getDirection(int arg0)
	{
		String str = "";
		if((arg0 >= 0 && arg0 < 15) || (arg0 >= 345 && arg0 < 360))
		{
			str = "北";
		}
		else 
		{	
			switch ((arg0-15)/45)
			{
				case 0:
					str = "东北";
					break;
				case 1:
					str = "东";
					break;
				case 2:
					str = "东南";
					break;
				case 3:
					str = "南";
					break;
				case 4:
					str = "西南";
					break;
				case 5:
					str = "西";
					break;
				case 6:
					str = "西北";
					break;
				default:
					break;
			}
		}
		return str;
	}
	
	public void getDataFromWalkingStep(List<WalkingStep> walkingSteps)
	{
		mWalkingSteps = walkingSteps;
		
		for(int i = 0; i < mWalkingSteps.size(); i++)
		{
			
			mStringArray.add((i+1) + ".向" + this.getDirection(mWalkingSteps.get(i).getDirection())
					+ "走" + mWalkingSteps.get(i).getDistance() + "米");
		}
		//Log.i("WalkingSteps", "Direction="+mWalkingSteps.get(0).getDirection());
		//Log.i("WalkingSteps", "Distance="+mWalkingSteps.get(0).getDistance());
	}
	
	private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		
		for(int i = 0; i < mStringArray.size(); i++)
		{
			Map<String, Object> _Map = new HashMap<String, Object>();
			_Map.put("route_info", mStringArray.get(i));
			
			data.add(_Map);
		}	
		mStringArray.clear();
		mWalkingSteps.clear();
		return data;
	}
	
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
		RelativeLayout.LayoutParams _Params = (RelativeLayout.LayoutParams)this.getLayoutParams();
		_Params.height = height * 3 / 5;
		_Params.width = width * 3 / 4;
		Log.i("MapRoutePopWindow", _Params.toString());
		this.setLayoutParams(_Params);
	}
	
/*	public void addItem(String str)
	{
		mListView.add
	}*/
	
}
