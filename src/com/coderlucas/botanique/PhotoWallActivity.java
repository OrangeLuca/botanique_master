package com.coderlucas.botanique;

import com.coderlucas.botanique.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoWallActivity extends Activity
{
	private HeadBar mHeadbar;
	private ImageView mImageView;
	/**
	 * 用于展示照片墙的GridView
	 */
	private GridView mPhotoWallGridView;

	/**
	 * GridView的适配器
	 */
	private PhotoWallAdapter mPhotoWallAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photowall_activity);
		
		mHeadbar = (HeadBar)findViewById(R.id.photowall_headbar);
		mHeadbar.setTextTitleResource(getString(R.string.text_photo_title));
		mHeadbar.setImageResource(R.drawable.headbar_back);
		//Log.i("Classify Class" , "flag 5");
		mHeadbar.setHeadBarOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "返回主界面", Toast.LENGTH_SHORT).show();
				//返回主界面
				PhotoWallActivity.this.finish();
			}
		});
		mImageView = new ImageView(getApplicationContext(), null);
		mImageView.setFitsSystemWindows(true);
		mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mImageView.setVisibility(View.GONE);
			}
		});
		
		mPhotoWallGridView = (GridView) findViewById(R.id.photo_wall);
		mPhotoWallAdapter = new PhotoWallAdapter(this, 0,
				Images.imageThumbUrls, mPhotoWallGridView);
		mPhotoWallGridView.setAdapter(mPhotoWallAdapter);
		mPhotoWallGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "全屏显示选中的图片:"+ id, Toast.LENGTH_SHORT).show();
				Uri _Str = Uri.parse(Images.imageThumbUrls[(int) id]);
				Log.i("mPhotoWallGridView.setOnItemClickListener", "_Str="+_Str);
				mImageView.setImageURI(_Str);
				mImageView.setVisibility(View.VISIBLE);
				 
			}
			
		});

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// 退出程序时结束所有的下载任务
		mPhotoWallAdapter.cancelAllTasks();
	}

}
