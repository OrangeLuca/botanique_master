package com.coderlucas.botanique;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * @ClassName: NavigationActivity
 * @Description: 导航界面
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月13日 上午9:56:55
 * 
 */
public class NavigationActivity extends Activity
{
/*
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		Log.i("NavigationActivity", "flag 1");
		
		super.onCreate(savedInstanceState);
		Log.i("NavigationActivity", "flag 1.1");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.layout_navigation);
		Log.i("NavigationActivity", "flag 1.2");
		// 创建NmapView
		MapGLSurfaceView _MapView = BaiduNaviManager.getInstance()
				.createNMapView(this);
		Log.i("NavigationActivity", "flag 1.3");
		// 创建导航视图
		View _NavigatorView = BNavigator.getInstance().init(
				NavigationActivity.this, getIntent().getExtras(), _MapView);

		Log.i("NavigationActivity", "flag 2");
		
		// 填充视图
		setContentView(_NavigatorView);

		Log.i("NavigationActivity", "flag 3");
		
		BNavigator.getInstance().setListener(mBNavigatorListener);
		BNavigator.getInstance().startNav();

		// 初始化TTS. 开发者也可以使用独立TTS模块，不用使用导航SDK提供的TTS
		BNTTSPlayer.initPlayer();

		// 设置TTS播放回调
		BNavigatorTTSPlayer.setTTSPlayerListener(new IBNTTSPlayerListener() {

			@Override
			public int playTTSText(String arg0, int arg1)
			{
				// 开发者可以使用其他TTS的API
				return BNTTSPlayer.playTTSText(arg0, arg1);
			}

			@Override
			public void phoneHangUp()
			{
				// 手机挂断
			}

			@Override
			public void phoneCalling()
			{
				// 通话中
			}

			@Override
			public int getTTSState()
			{
				// 开发者可以使用其他TTS的API,
				return BNTTSPlayer.getTTSState();
			}
		});

		BNRoutePlaner.getInstance().setObserver(
				new RoutePlanObserver(this, new IJumpToDownloadListener() {

					@Override
					public void onJumpToDownloadOfflineData()
					{
						// TODO Auto-generated method stub

					}
				}));
		
		Log.i("NavigationActivity", "flag 4");

	}

	// 导航监听器
	private IBNavigatorListener mBNavigatorListener = new IBNavigatorListener() {

		@Override
		public void onYawingRequestSuccess()
		{
			// TODO 偏航请求成功

		}

		@Override
		public void onYawingRequestStart()
		{
			// TODO 开始偏航请求

		}

		@Override
		public void onPageJump(int jumpTiming, Object arg)
		{
			// TODO 页面跳转回调
			if (IBNavigatorListener.PAGE_JUMP_WHEN_GUIDE_END == jumpTiming)
			{
				finish();
			}
			else if (IBNavigatorListener.PAGE_JUMP_WHEN_ROUTE_PLAN_FAIL == jumpTiming)
			{
				finish();
			}
		}

		@Override
		public void notifyGPSStatusData(int arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void notifyLoacteData(LocData arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void notifyNmeaData(String arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void notifySensorData(SensorData arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void notifyStartNav()
		{
			// TODO Auto-generated method stub
			BaiduNaviManager.getInstance().dismissWaitProgressDialog();
		}

		@Override
		public void notifyViewModeChanged(int arg0)
		{
			// TODO Auto-generated method stub

		}
	};

	@Override
	public void onResume()
	{
		BNavigator.getInstance().resume();
		super.onResume();
		BNMapController.getInstance().onResume();
	};

	@Override
	public void onPause()
	{
		BNavigator.getInstance().pause();
		super.onPause();
		BNMapController.getInstance().onPause();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		BNavigator.getInstance().onConfigurationChanged(newConfig);
		super.onConfigurationChanged(newConfig);
	}

	public void onBackPressed()
	{
		BNavigator.getInstance().onBackPressed();
	}

	@Override
	public void onDestroy()
	{
		BNavigator.destory();
		BNRoutePlaner.getInstance().setObserver(null);
		super.onDestroy();
	}

	
*/
}
