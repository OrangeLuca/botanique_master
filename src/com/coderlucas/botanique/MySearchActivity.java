package com.coderlucas.botanique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.main.MainActivity;
import com.coderlucas.botanique.tabmenu.TabMapFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MySearchActivity extends Activity
{
	private MySearchBar mMySearchBar = null;
	private ListView mListResult = null;
	// private ListView mListHistory = null;
	private List<String> mClassifyId = null;
	private DataFromJSON mJSON = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_view);

		//查询得到Json数据
		mJSON = new DataFromJSON();
		
		mListResult = (ListView) findViewById(R.id.search_resultlist);
		mListResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO Auto-generated method stub
				Log.i("mListResult Item Click", "flag 1");
				Log.i("mListResult Item Click", "parent=" + parent.toString()
						+ ", view=" + view.getTag() + ", position=" + position
						+ ", id=" + id);
			}
		});

		
		mMySearchBar = (MySearchBar) findViewById(R.id.search_view_bar);
		mMySearchBar.setSearchBtnOnClickedListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"开始搜索" + mMySearchBar.getEditString(),
						Toast.LENGTH_SHORT).show();

				mClassifyId = getClassifyIdData(mJSON);

				mListResult.setAdapter(new SearchListViewAdapter(
						getApplicationContext(), getTitleData(mJSON),
						getImageData(mJSON), new OnClickListener() {

							@Override
							public void onClick(View v)
							{
								// TODO Auto-generated method stub
								/* 搜索位置信息，返回list<latlng>给map，关闭当前activity */

								// 用classifyId查询位置信息，得到list<latlng>
								// mClassifyId.get((int)v.getTag());
								List<MyLatLng> _LatLngList = new ArrayList<MyLatLng>();

								_LatLngList.add(new MyLatLng(22.530802,
										113.931711));
								_LatLngList.add(new MyLatLng(22.530952,
										113.931691));
								_LatLngList.add(new MyLatLng(22.530732,
										113.931611));

								// 传递参数到map
								Intent _Intent = new Intent();
								_Intent.putParcelableArrayListExtra(
										"SearchToMap",
										(ArrayList<? extends Parcelable>) _LatLngList);
								//_Intent.addFlags(Keys.KEY_SEARCH_TO_TABMAP);
								//_Intent.setClass(getBaseContext(), MainActivity.class);
								_Intent.setClass(getBaseContext(), MainActivity.class);
								MySearchActivity.this.setResult(Keys.KEY_SEARCH_TO_TABMAP,
										_Intent);
								//startActivity(_Intent);
								// 关闭当前Activity
								MySearchActivity.this.finish();
							}
						}, new OnClickListener() {
							
							@Override
							public void onClick(View v)
							{
								// TODO Auto-generated method stub
								// 从JSON获取分类号
								// String _ClassifyId = mClassifyId.get((int)v.getTag());
								String _ClassifyId = "SZ10000001";

								// 启动ClassifyInfoActivity
								Intent _Intent = new Intent();
								_Intent.setClass(MySearchActivity.this
										.getApplicationContext(),
										SearchClassifyInfoActivity.class);
								Bundle _Bundle = new Bundle();
								_Bundle.putString("SearchClassifyInfoKey", _ClassifyId);
								_Intent.putExtras(_Bundle);
								startActivity(_Intent);
							
							}
						}, new OnClickListener() {
							
							@Override
							public void onClick(View v)
							{
								// TODO Auto-generated method stub
								// 从JSON获取分类号
								// String _ClassifyId = mClassifyId.get((int)v.getTag());
								String _ClassifyId = "SZ10000001";

								// 启动ClassifyInfoActivity
								Intent _Intent = new Intent();
								_Intent.setClass(MySearchActivity.this
										.getApplicationContext(),
										SearchClassifyInfoActivity.class);
								Bundle _Bundle = new Bundle();
								_Bundle.putString("SearchClassifyInfoKey", _ClassifyId);
								_Intent.putExtras(_Bundle);
								startActivity(_Intent);
							}
						}));

			}
		});

		mMySearchBar.setBackOnClickedListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				MySearchActivity.this.finish();
			}
		});

	
	}

	private List<String> getClassifyIdData(DataFromJSON pJSON)
	{
		// 模拟JSON数据
		List<String> _Array = new ArrayList<String>();
		_Array.add("SZ10000001");
		_Array.add("SZ10000002");
		_Array.add("SZ10000001");

		// 读取JSON数据
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < _Array.size(); i++)
		{
			data.add(_Array.get(i));
		}
		_Array.clear();
		return data;
	}

	private List<String> getTitleData(DataFromJSON pJSON)
	{
		// 模拟JSON数据
		List<String> _Array = new ArrayList<String>();
		_Array.add("荔枝");
		_Array.add("荔枝荔枝");
		_Array.add("lizhi");

		// 读取JSON数据
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < _Array.size(); i++)
		{
			data.add(_Array.get(i));
		}
		_Array.clear();
		return data;
	}

	private List<Integer> getImageData(DataFromJSON pJSON)
	{
		// 模拟JSON数据
		List<Integer> _Array = new ArrayList<Integer>();
		_Array.add(R.drawable.lizhi);
		_Array.add(R.drawable.lizhi);
		_Array.add(R.drawable.lizhi);

		// 读取JSON数据
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < _Array.size(); i++)
		{
			data.add(_Array.get(i));
		}
		_Array.clear();
		return data;
	}
}
