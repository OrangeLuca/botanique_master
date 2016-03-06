package com.coderlucas.botanique;

import java.net.URI;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SysUpdateActivity extends Activity
{
	private SurfaceView mSurfaceView = null;
	private LinearLayout mLLCheck = null;
	private LinearLayout mLLHasNew = null;
	private LinearLayout mLLNoNew = null;
	private LinearLayout mLLDownload = null;
	private LinearLayout mLLFinish = null;
	private ProgressBar mProbarCheck = null;
	private TextView mTextCheck = null;
	private TextView mTextHasNew = null;
	private Button mBtnHasNew = null;
	private TextView mTextNoNew = null;
	private Button mBtnNoNew = null;
	private TextView mTextDownload = null;
	private ProgressBar mProbarDownload = null;
	private Button mBtnDownload = null;
	private Button mBtnHasNewCancel = null;
	private TextView mTextFinish = null;
	private Button mBtnFinish = null;

	//应用版本号
	private String mSysVersion = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_update_layout);

		//获取版本号
		mSysVersion = getIntent().getExtras().getString("system_version"); 
		
		//配置界面
		mSurfaceView = (SurfaceView) findViewById(R.id.update_surface);
		
		mLLCheck = (LinearLayout) findViewById(R.id.update_llcheck);
		mProbarCheck = (ProgressBar) findViewById(R.id.update_checkprobar);
		mTextCheck = (TextView) findViewById(R.id.update_checktext);
		
		mLLHasNew = (LinearLayout) findViewById(R.id.update_llhasnew);
		mTextHasNew = (TextView) findViewById(R.id.update_hastext);
		mBtnHasNew = (Button) findViewById(R.id.update_downloadbtn);
		mBtnHasNewCancel = (Button) findViewById(R.id.update_downloadcancelbtn);
		
		mLLNoNew = (LinearLayout) findViewById(R.id.update_llnonew);
		mTextNoNew = (TextView) findViewById(R.id.update_nonewtext);
		mBtnNoNew = (Button) findViewById(R.id.update_backbtn);
		
		mLLDownload = (LinearLayout) findViewById(R.id.update_lldownloadnew);
		mTextDownload = (TextView) findViewById(R.id.update_downloadnewtext);
		mProbarDownload = (ProgressBar) findViewById(R.id.update_downloadnewprobar);
		mBtnDownload = (Button) findViewById(R.id.update_cancelbtn);
		
		mLLFinish = (LinearLayout) findViewById(R.id.update_llfinish);
		mTextFinish = (TextView) findViewById(R.id.update_finishtext);
		mBtnFinish = (Button) findViewById(R.id.update_finishbtn);

		mBtnHasNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mLLHasNew.setVisibility(View.GONE);
				mLLDownload.setVisibility(View.VISIBLE);
				//开始下载,与进度条、文本联动
				
			}
		});
		
		mBtnHasNewCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				SysUpdateActivity.this.finish();
			}
		});
		
		mBtnNoNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				SysUpdateActivity.this.finish();
			}
		});
		
		mBtnDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//进度条和联动文本归零
				mLLHasNew.setVisibility(View.VISIBLE);
				mLLDownload.setVisibility(View.GONE);
			}
		});
		
		mBtnFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				SysUpdateActivity.this.finish();
			}
		});
		
		//完成下载则打开finif界面
		mProbarDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mLLDownload.setVisibility(View.GONE);
				mLLFinish.setVisibility(View.VISIBLE);
			}
		});
		
		//初始化界面
		mSurfaceView.setBackgroundResource(R.color.color_syeupdate_surface);
		mLLCheck.setVisibility(View.VISIBLE);
		mLLDownload.setVisibility(View.GONE);
		mLLHasNew.setVisibility(View.GONE);
		mLLNoNew.setVisibility(View.GONE);
		mLLFinish.setVisibility(View.GONE);
		
		//获取新版本
		String _String = getNewVersion(mSysVersion);
		if(_String != null)
		{
			mLLCheck.setVisibility(View.GONE);
			mTextHasNew.setText("有新版本 " + _String + " 可以更新");
			mLLHasNew.setVisibility(View.VISIBLE);
		}
		else
		{
			mLLCheck.setVisibility(View.GONE);
			mTextNoNew.setText("当前是最新版本！");
			mLLNoNew.setVisibility(View.VISIBLE);
		}
		
	}
	
	
	public String getNewVersion(String pStr)
	{
		//查找最新版本号newVersion，并于当前版本pStr对比
		String newVersion = pStr;
		return newVersion;
		/*if(newVersion == null || newVersion == pStr)
		{
			return null;
		}
		else 
		{
			return newVersion;
		}*/
	}
	
/*	//获取下载地址
	public URI getNewVersionUri(String pStr)
	{
		//获取最新版本号newVersion下载地址
		URI _NewVersion = new URI("http://www.szu.edu.cn");

		return _NewVersion;
	}*/

}
