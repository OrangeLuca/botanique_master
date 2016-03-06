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
	 * ���ߵ�ͼ����
	 */
	private MKOfflineMap mOfflineMap;
	private ListView mListView;
	private LayoutInflater mInflater;
	/**
	 * ���ߵ�ͼ������
	 */
	private List<OfflineMapCityBean> mDatas = new ArrayList<OfflineMapCityBean>();
	/**
	 * ������
	 */
	private MyOfflineCityBeanAdapter mAdapter;
	private Context context;
	/**
	 * Ŀǰ�������ض��еĳ���
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

		/* ���ñ����� */
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
		 * ��ʼ�����ߵ�ͼ
		 */
		initOfflineMap();
		/**
		 * ��ʼ��ListView����
		 */
		initData();
		/**
		 * ��ʼ��ListView
		 */
		initListView();
		
		// Log.i("syssettings", "flag 2");
		/* ��������ҳ */
		// mLLBasic = (LinearLayout) findViewById(R.id.settings_theme);

		// mLLMap = (LinearLayout) findViewById(R.id.settings_map);
		// Log.i("syssettings", "flag 3");

		/* ����tab��ť */
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

		// ����������ų���
		/*ArrayList<MKOLSearchRecord> offlineCityList = mOfflineMap
				.getHotCityList();*/
		ArrayList<MKOLSearchRecord> offlineCityList = new ArrayList<MKOLSearchRecord>();
		
		// �ֶ��������
		MKOLSearchRecord shenzhen = new MKOLSearchRecord();
		shenzhen.cityID = 340;
		shenzhen.cityName = "������";
		offlineCityList.add(shenzhen);
		
		// ��������Ѿ����صĳ����б�
		ArrayList<MKOLUpdateElement> allUpdateInfo = mOfflineMap
				.getAllUpdateInfo();
		// �����������ݵ�״̬
		for (MKOLSearchRecord record : offlineCityList)
		{
			OfflineMapCityBean cityBean = new OfflineMapCityBean();
			cityBean.setCityName(record.cityName);
			cityBean.setCityCode(record.cityID);

			if (allUpdateInfo != null)//û���κ����ؼ�¼������null
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
	 * �������Ƴ����ض���
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
	 * ������������������ض���
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
	 * ��ʼ�����ߵ�ͼ
	 */
	private void initOfflineMap()
	{
		mOfflineMap = new MKOfflineMap();
		// ���ü���
		mOfflineMap.init(new MKOfflineMapListener() {
			@Override
			public void onGetOfflineMapState(int type, int state)
			{
				switch (type)
				{
					case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
						// ���ߵ�ͼ���ظ����¼�����
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
						// �������ߵ�ͼ��װ
						Log.e("initOfflineMap", "TYPE_NEW_OFFLINE");
						break;
					case MKOfflineMap.TYPE_VER_UPDATE:
						// �汾������ʾ
						break;
				}

			}
		});
	}
	
	/**
	 * ���ų��е�ͼ�б��Adapter
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
			// ���ݽ��������������ʾ
			if (progress == 0)
			{
				progressMsg = "δ����";
			}
			else if (progress == 100)
			{
				bean.setFlag(Flag.NO_STATUS);
				progressMsg = "������";
			}
			else
			{
				progressMsg = progress + "%";
			}
			// ���ݵ�ǰ״̬��������ʾ
			switch (bean.getFlag())
			{
				case PAUSE:
					progressMsg += "���ȴ����ء�";
					break;
				case DOWNLOADING:
					progressMsg += "���������ء�";
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
