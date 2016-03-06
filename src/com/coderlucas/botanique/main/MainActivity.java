package com.coderlucas.botanique.main;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.coderlucas.botanique.ClassifyInfoActivity;
import com.coderlucas.botanique.Keys;
import com.coderlucas.botanique.MyLatLng;
import com.coderlucas.botanique.MySearchActivity;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.qr.MipcaActivityCapture;
import com.coderlucas.botanique.tabmenu.TabMapFragment;
import com.coderlucas.botanique.tabmenu.TabMapFragment;
import com.coderlucas.botanique.tabmenu.TabMenuChangeListener;

/**
 * @ClassName: MainActivity
 * @Description: main activity
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月6日 下午2:32:03
 * 
 */
public class MainActivity extends FragmentActivity
{
	// private final static String ACCESS_KEY = "stnqz4DirN4HHHiTubrp4iNT";
	// private static final String CATEGORY_SDK_BOTANIQUE =
	// "android.intent.category.BAIDUNAVISDK_BOTANIQUE";
	/**
	 * @Fields mTabMenu : Tab menu
	 */
	private RadioGroup mTabMenu;

	/* private HeadBar mHeadBar; */

	private ActionBar mActionBar;
	
	@Override
	protected void onCreate(Bundle saveInstanceState)
	{
		// TODO Auto-generated method stub
		// Log.i("MainActivity OnCreate", "TabMapFragment first instant1");
		super.onCreate(saveInstanceState);

		// 设置工具栏
		mActionBar = getActionBar();
		mActionBar.setHomeButtonEnabled(true);

		setContentView(R.layout.main_activity);

		setOverFlowShowAlways();

		// 配置Tab选项卡按钮
		mTabMenu = (RadioGroup) findViewById(R.id.tab_menu);
		TabMenuChangeListener listener = new TabMenuChangeListener(this);
		mTabMenu.setOnCheckedChangeListener(listener);

		// 初始化第一个Tab选项卡
		if (findViewById(R.id.fragment_container) != null)
		{
			/*getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, TabMapFragment.newInstance())
					.commit();*/
			//Test Map Code
			getSupportFragmentManager().beginTransaction()
			.add(R.id.fragment_container, TabMapFragment.newInstance())
			.commit();
		}

	}

	/*
	 * (non-Javadoc) <p>Title: onOptionsItemSelected</p> <p>Description:
	 * ActionBar 菜单处理函数</p>
	 * 
	 * @param item
	 * 
	 * @return
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
			case android.R.id.home:
				/*
				 * saveBitmapToFile(); shareMessage("分享到",
				 * getString(R.string.text_share_title),
				 * getString(R.string.text_share_message),
				 * Environment.getExternalStorageDirectory
				 * ().getPath()+File.pathSeparator+"share.png");
				 */

				/*
				 * Intent _Intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
				 * _Intent.setType("image/*");
				 * _Intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,
				 * getUriListForImages());
				 * _Intent.putExtra(Intent.EXTRA_SUBJECT, "好东西，齐分享");
				 * _Intent.putExtra(Intent.EXTRA_TEXT,
				 * "深大校园里的植物你知道多少，开来长姿势！(分享自Botanique)");
				 * _Intent.putExtra(Intent.EXTRA_TITLE, "Botanique");
				 * _Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 * startActivity(Intent.createChooser(_Intent, "分享到"));
				 */

				Intent _Intent = new Intent(Intent.ACTION_SEND);
				_Intent.setType("image/*");
				_Intent.putExtra(Intent.EXTRA_SUBJECT, "好东西，齐分享");
				_Intent.putExtra(Intent.EXTRA_TEXT,
						"深大校园里的植物你知道多少，开来长姿势！ (分享自Botanique)");
				_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(_Intent, getTitle()));

				break;

			case R.id.action_search:
				// Todo: 打开搜索用的Activity
				TabMapFragment.newInstance().setSearchOnFlag(true);
				Toast.makeText(this, "Action Search is Clicked",
						Toast.LENGTH_SHORT).show();
				Intent _Intent1 = new Intent();
				_Intent1.setClass(getApplication(), MySearchActivity.class);
				//startActivity(_Intent1);
				startActivityForResult(
						_Intent1,
						Keys.KEY_SEARCH_TO_TABMAP);
				
				break;

			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode)
		{
			case Keys.KEY_SEARCH_TO_TABMAP:

				// serach返回结果
				ArrayList<? extends Parcelable> _SearchList = data
						.getParcelableArrayListExtra("SearchToMap");
				ArrayList<MyLatLng> _SearchList1 = (ArrayList<MyLatLng>) _SearchList;
				Log.i("MainAcitity", "_SearchList1="+_SearchList1.toString());
				/*
				Bundle _Bundle = new Bundle();
				_Bundle.putParcelableArrayList("searchinfo", _SearchList);
				getFragmentManager().getFragment(_Bundle, "search");
				Log.i("MainAcitity", "_SearchList="+_SearchList.toString());*/
				TabMapFragment.newInstance().setMyLatLngArrayList(_SearchList1);

				break;
				
			default:
				break;
		}
	}

	
	/**
	 * 分享功能
	 * 
	 * @param context
	 *            上下文
	 * @param activityTitle
	 *            Activity的名字
	 * @param msgTitle
	 *            消息标题
	 * @param msgText
	 *            消息内容
	 * @param imgPath
	 *            图片路径，不分享图片则传null
	 */
	public void shareMessage(String activityTitle, String msgTitle,
			String msgText, String imgPath)
	{
		Intent _Intent = new Intent(Intent.ACTION_SEND);
		/*
		 * if (imgPath == null || imgPath.equals("")) {
		 * _Intent.setType("text/plain"); // 纯文本 } else {
		 */
		File f = new File(imgPath);
		// if (f != null && f.exists() && f.isFile())
		// {
		_Intent.setType("image/*");
		Uri u = Uri.fromFile(f);
		_Intent.putExtra(Intent.EXTRA_STREAM, u);
		// }
		// }
		_Intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		_Intent.putExtra(Intent.EXTRA_TEXT, msgText);
		_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(_Intent, getTitle()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO Auto-generated method stub
		// 主菜单
		getMenuInflater().inflate(R.menu.main_menu, menu);

		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		// TODO Auto-generated method stub

		// 菜单设置
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if (menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method _Method = menu.getClass().getDeclaredMethod(
							"setOptionalIconVisible", Boolean.TYPE);
					_Method.setAccessible(true);
					_Method.invoke(menu, true);
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	private void setOverFlowShowAlways()
	{
		try
		{
			ViewConfiguration _ViewConfiguration = ViewConfiguration.get(this);
			Field _MenuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanetMenuKey");
			_MenuKeyField.setAccessible(true);
			_MenuKeyField.setBoolean(_ViewConfiguration, false);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
