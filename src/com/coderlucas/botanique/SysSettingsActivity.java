package com.coderlucas.botanique;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.coderlucas.botanique.baidumap.OfflineMapCityBean;
import com.coderlucas.botanique.baidumap.OfflineMapCityBean.Flag;

public class SysSettingsActivity extends Activity
{

	private HeadBar mHeadBar = null;

	/**
	 * 离线地图功能
	 */
	private MKOfflineMap mOfflineMap;
	private ListView mListView;
	private LayoutInflater mInflater;
	/**
	 * 离线地图的数据
	 */
	private List<OfflineMapCityBean> mDatas = new ArrayList<OfflineMapCityBean>();
	/**
	 * 适配器
	 */
	private MyOfflineCityBeanAdapter mAdapter;
	private Context context;
	/**
	 * 目前加入下载队列的城市
	 */
	private List<Integer> mCityCodes = new ArrayList<Integer>();
	
	// private LinearLayout mLinearLayoutBtn = null;
	// private TextView mTextLLBtn1 = null;
	// private TextView mTextLLBtn2 = null;
	// private LinearLayout mLLBasic = null;
	// private LinearLayout mLLMap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbtn_settings_activity);

		/* 配置标题栏 */
		mHeadBar = (HeadBar) findViewById(R.id.settings_headbar);
		mHeadBar.setTextTitleResource(getString(R.string.text_settings_headbar_title));
		mHeadBar.setImageResource(R.drawable.headbar_back);
		mHeadBar.setHeadBarOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				SysSettingsActivity.this.finish();
			}
		});

		
		context = this;
		mInflater = LayoutInflater.from(this);
		/**
		 * 初始化离线地图
		 */
		initOfflineMap();
		/**
		 * 初始化ListView数据
		 */
		initData();
		/**
		 * 初始化ListView
		 */
		initListView();
		
		// Log.i("syssettings", "flag 2");
		/* 配置内容页 */
		// mLLBasic = (LinearLayout) findViewById(R.id.settings_theme);

		// mLLMap = (LinearLayout) findViewById(R.id.settings_map);
		// Log.i("syssettings", "flag 3");

		/* 配置tab按钮 */
		// mLinearLayoutBtn = (LinearLayout)
		// findViewById(R.id.settings_layoutbtn);
		// mTextLLBtn1 = (TextView) findViewById(R.id.settings_btntext1);
		// mTextLLBtn2 = (TextView) findViewById(R.id.settings_btntext2);
		/*
		 * Log.i("syssettings", "flag 4"); mTextLLBtn1.setOnClickListener(new
		 * OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub mTextLLBtn1.setTextColor(getResources().getColor(
		 * R.color.color_tab_unselected));
		 * mTextLLBtn2.setTextColor(getResources().getColor(
		 * R.color.color_tab_selected)); mLinearLayoutBtn
		 * .setBackgroundResource(R.drawable.settings_btn_bg_1);
		 * 
		 * mLLMap.setVisibility(View.GONE);
		 * mLLBasic.setVisibility(View.VISIBLE); } }); Log.i("syssettings",
		 * "flag 5"); mTextLLBtn2.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub mTextLLBtn2.setTextColor(getResources().getColor(
		 * R.color.color_tab_unselected));
		 * mTextLLBtn1.setTextColor(getResources().getColor(
		 * R.color.color_tab_selected)); mLinearLayoutBtn
		 * .setBackgroundResource(R.drawable.settings_btn_bg_2);
		 * 
		 * mLLBasic.setVisibility(View.GONE);
		 * mLLMap.setVisibility(View.VISIBLE); } }); Log.i("syssettings",
		 * "flag 6");
		 */
	}

	private void initData()
	{

		// 获得所有热门城市
		/*ArrayList<MKOLSearchRecord> offlineCityList = mOfflineMap
				.getHotCityList();*/
		ArrayList<MKOLSearchRecord> offlineCityList = new ArrayList<MKOLSearchRecord>();
		
		// 手动添加深圳
		MKOLSearchRecord shenzhen = new MKOLSearchRecord();
		shenzhen.cityID = 340;
		shenzhen.cityName = "深圳市";
		offlineCityList.add(shenzhen);
		
		// 获得所有已经下载的城市列表
		ArrayList<MKOLUpdateElement> allUpdateInfo = mOfflineMap
				.getAllUpdateInfo();
		// 设置所有数据的状态
		for (MKOLSearchRecord record : offlineCityList)
		{
			OfflineMapCityBean cityBean = new OfflineMapCityBean();
			cityBean.setCityName(record.cityName);
			cityBean.setCityCode(record.cityID);

			if (allUpdateInfo != null)//没有任何下载记录，返回null
			{
				for (MKOLUpdateElement ele : allUpdateInfo)
				{
					if (ele.cityID == record.cityID)
					{
						cityBean.setProgress(ele.ratio);
					}
				}

			}
			mDatas.add(cityBean);
		}

	}

	
	private void initListView()
	{
		mListView = (ListView) findViewById(R.id.settings_maplist);
		mAdapter = new MyOfflineCityBeanAdapter();
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				int cityId = mDatas.get(position).getCityCode();
				if (mCityCodes.contains(cityId))
				{
					removeTaskFromQueue(position, cityId);
				} else
				{
					addToDownloadQueue(position, cityId);
				}

			}
		});
	}
	
	
	/**
	 * 将任务移除下载队列
	 * 
	 * @param pos
	 * @param cityId
	 */
	public void removeTaskFromQueue(int pos, int cityId)
	{
		mOfflineMap.pause(cityId);
		mDatas.get(pos).setFlag(Flag.NO_STATUS);
		mAdapter.notifyDataSetChanged();
	}

	/**
	 * 将下载任务添加至下载队列
	 * 
	 * @param pos
	 * @param cityId
	 */
	public void addToDownloadQueue(int pos, int cityId)
	{
		mCityCodes.add(cityId);
		mOfflineMap.start(cityId);
		mDatas.get(pos).setFlag(Flag.PAUSE);
		mAdapter.notifyDataSetChanged();
	}
	
	
	/**
	 * 初始化离线地图
	 */
	private void initOfflineMap()
	{
		mOfflineMap = new MKOfflineMap();
		// 设置监听
		mOfflineMap.init(new MKOfflineMapListener() {
			@Override
			public void onGetOfflineMapState(int type, int state)
			{
				switch (type)
				{
					case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
						// 离线地图下载更新事件类型
						MKOLUpdateElement update = mOfflineMap
								.getUpdateInfo(state);
						Log.e("initOfflineMap", update.cityName + " ," + update.ratio);
						for (OfflineMapCityBean bean : mDatas)
						{
							if (bean.getCityCode() == state)
							{
								bean.setProgress(update.ratio);
								bean.setFlag(Flag.DOWNLOADING);
								break;
							}
						}
						mAdapter.notifyDataSetChanged();
						Log.e("initOfflineMap", "TYPE_DOWNLOAD_UPDATE");
						break;
					case MKOfflineMap.TYPE_NEW_OFFLINE:
						// 有新离线地图安装
						Log.e("initOfflineMap", "TYPE_NEW_OFFLINE");
						break;
					case MKOfflineMap.TYPE_VER_UPDATE:
						// 版本更新提示
						break;
				}

			}
		});
	}
	
	/**
	 * 热门城市地图列表的Adapter
	 */
	class MyOfflineCityBeanAdapter extends BaseAdapter
	{

		@Override
		public boolean isEnabled(int position)
		{
			if (mDatas.get(position).getProgress() == 100)
			{
				return false;
			}
			return super.isEnabled(position);
		}

		@Override
		public int getCount()
		{
			return mDatas.size();
		}

		@Override
		public Object getItem(int position)
		{
			return mDatas.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			OfflineMapCityBean bean = mDatas.get(position);
			ViewHolder holder = null;
			if (convertView == null)
			{
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.offlinemap_item, parent,
						false);
				holder.cityName = (TextView) convertView
						.findViewById(R.id.id_cityname);
				holder.progress = (TextView) convertView
						.findViewById(R.id.id_progress);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			holder.cityName.setText(bean.getCityName());
			int progress = bean.getProgress();
			String progressMsg = "";
			// 根据进度情况，设置显示
			if (progress == 0)
			{
				progressMsg = "未下载";
			}
			else if (progress == 100)
			{
				bean.setFlag(Flag.NO_STATUS);
				progressMsg = "已下载";
			}
			else
			{
				progressMsg = progress + "%";
			}
			// 根据当前状态，设置显示
			switch (bean.getFlag())
			{
				case PAUSE:
					progressMsg += "【等待下载】";
					break;
				case DOWNLOADING:
					progressMsg += "【正在下载】";
					break;
				default:
					break;
			}
			holder.progress.setText(progressMsg);
			return convertView;
		}

		private class ViewHolder
		{
			TextView cityName;
			TextView progress;

		}
	}

	
	@Override
	protected void onDestroy()
	{
		mOfflineMap.destroy();
		super.onDestroy();
	}
}
