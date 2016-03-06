package com.coderlucas.botanique.tabmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coderlucas.botanique.MenuItem;
import com.coderlucas.botanique.NewAddActivity;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.qr.MipcaActivityCapture;

/** 
 * @ClassName: TabAddNewFragment 
 * @Description: Contain AddNew Tab 
 * @author coderLucas Lucas_hsueh?foxmail?com 
 * @date 2015年5月6日 下午2:39:25 
 *  
 */ 
public class TabAddNewFragment extends Fragment
{
	private static TabAddNewFragment _this = null;
/*	private List<Map<String, Object>> mData;
	private ListView mListMenu;
	*/
	public TabAddNewFragment()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public static TabAddNewFragment newInstance()
	{
		if(_this == null)
		{
			_this = new TabAddNewFragment();
			Bundle bundle = new Bundle();
			bundle.putString("abc", "abc");
			_this.setArguments(bundle);
		}
		return _this;
	}

	private MenuItem mMenuItemNew;
	private MenuItem mMenuItemMessage;
	private MenuItem mMenuItemAround;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Log.i("Tab", "flag12");
		//mMenuItemNew = (MenuItem)getActivity().findViewById(R.id.item_addnew_new);
		//Log.i("Tab", "flag12");
		//mMenuItemNew.setImageResource(R.drawable.logo);
		//Log.i("Tab", getString(R.string.text_addnew_newitem));
		//mMenuItemNew.setTextTitleResource(getString(R.string.text_addnew_newitem));
		
		/*Log.i("Tab", "flag12");
		mMenuItemNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "新发现", Toast.LENGTH_SHORT).show();
				
			}
		});
		Log.i("MenuItem", "flag13");*/
		//mMenuItemMessage = (MenuItem)getActivity().findViewById(R.id.item_addnew_message);
		/*mMenuItemMessage.setImageResource(R.drawable.logo);
		mMenuItemMessage.setTextTitleResource(getString(R.string.text_addnew_message));
		mMenuItemMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "树洞", Toast.LENGTH_SHORT).show();
				
			}
		});
		*/
		//mMenuItemAround = (MenuItem)getActivity().findViewById(R.id.item_addnew_around);
		/*mMenuItemAround.setImageResource(R.drawable.logo);
		mMenuItemAround.setTextTitleResource(getString(R.string.text_addnew_around));
		mMenuItemAround.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "附近的", Toast.LENGTH_SHORT).show();
				
			}
		});*/
		
		/*
		MenuItem _MenuItemNew = (MenuItem)getActivity().findViewById(R.id.item_addnew_new);
		_MenuItemNew.setImageResource(R.drawable.ic_launcher);
		_MenuItemNew.setTextTitleResource(getResources().getString(R.string.text_addnew_newitem));
		_MenuItemNew.setTextInfoResource(getResources().getString(R.string.text_addnew_newitem));*/
		
		/*mListMenu = (ListView)getActivity().findViewById(R.id.addnew_list_menu);
		mData = getData();
		
		ListViewMenuAdapter _Adapter = new ListViewMenuAdapter(this.getActivity());
		mListMenu.setAdapter(_Adapter);*/
	}


/*	private Context mContext;
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		//mContext = getActivity();
		
		
/*		MenuItem _ItemNew = new MenuItem(getActivity());
		_ItemNew.setTextTitleResource("新发现");
		_ItemNew.setTextInfoResource("等待添加");
		_ItemNew.setImageResource(R.drawable.ic_launcher);*/
/*		
		mListMenu = (ListView)getActivity().findViewById(R.id.addnew_list_menu);
		mAdapter = new ListViewMenuAdapter(mData, getActivity());
		mListMenu.setAdapter(mAdapter);
		*/
		
		View _CodeView = inflater.inflate(R.layout.layout_addnew, container, false);
		return _CodeView;
		//return null;
	}
	
	/*private Handler mHandler;
	private ListViewMenuAdapter mAdapter;
	*/
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
			//mHandler = new Handler();
		mMenuItemNew = (MenuItem)getActivity().findViewById(R.id.item_addnew_new);
		mMenuItemNew.setImageResource(R.drawable.addnew_newfind);
		mMenuItemNew.setTextTitleResource(getString(R.string.text_addnew_newitem));
		mMenuItemNew.setMenuItemOnClickListener(mMenuItemNew, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "新发现", Toast.LENGTH_SHORT).show();
				
				Intent _Intent = new Intent();
				_Intent.setClass(getActivity().getApplicationContext(),
						NewAddActivity.class);
				// getActivity().startActivity(_Intent);
				startActivity(_Intent);
				
				
			}
		});
		
		mMenuItemMessage = (MenuItem)getActivity().findViewById(R.id.item_addnew_message);
		mMenuItemMessage.setImageResource(R.drawable.logo);
		mMenuItemMessage.setTextTitleResource(getString(R.string.text_addnew_message));
		mMenuItemMessage.setMenuItemOnClickListener(mMenuItemMessage, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "树洞纸条", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		mMenuItemAround = (MenuItem)getActivity().findViewById(R.id.item_addnew_around);
		mMenuItemAround.setImageResource(R.drawable.logo);
		mMenuItemAround.setTextTitleResource(getString(R.string.text_addnew_around));
		mMenuItemAround.setMenuItemOnClickListener(mMenuItemAround, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "附近的", Toast.LENGTH_SHORT).show();
				
			}
		});
		/*new Thread(new Runnable() {
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				
				mData = getData();
				
				mAdapter = new ListViewMenuAdapter(mData, mContext);
				//mListMenu.setAdapter(_Adapter);
				
				mHandler.post(new Runnable() {
					
					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						mListMenu = (ListView)getActivity().findViewById(R.id.addnew_list_menu);
						mListMenu.setAdapter(mAdapter);
						//不显示分隔线
						mListMenu.setDividerHeight(1);
						//防止背景变黑
						mListMenu.setCacheColorHint(Color.TRANSPARENT);
						mListMenu.setOnItemClickListener(new OnItemClickListener() {
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id){
								//Intent newAc
							}
						});
					}
				});
			}
		}).start();*/
		
	}


/*	//获取adapter数据
	private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.ic_launcher);
            map.put("title", "跆拳道");
            map.put("info", "快乐源于生活...");
            list.add(map);
        }
        return list;

	}*/
	
	
}
