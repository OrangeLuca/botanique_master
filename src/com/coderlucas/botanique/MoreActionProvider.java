package com.coderlucas.botanique;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.coderlucas.botanique.R;

/**
 * @ClassName: MoreActionProvider
 * @Description: ActionBar's More Action SubMenu
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月6日 下午2:37:19
 * 
 */
public class MoreActionProvider extends ActionProvider
{

	private Context context;

	private Intent mIntent = null;
	
	public MoreActionProvider(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public View onCreateActionView()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu)
	{
		// TODO Auto-generated method stub
		/* super.onPrepareSubMenu(subMenu); */
		subMenu.clear();

		// system settings
		subMenu.add(context.getString(R.string.action_more_settings))
				.setIcon(R.drawable.tools_settings)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item)
					{
						// TODO Auto-generated method stub
						//item.se
						Toast.makeText(context, "系统设置", Toast.LENGTH_SHORT).show();
						mIntent = new Intent();
						mIntent.setClass(context, SysSettingsActivity.class);
						context.startActivity(mIntent);
						return false;
					}
				});

		// donation
		subMenu.add(context.getString(R.string.action_more_donation))
				.setIcon(R.drawable.tools_donation)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item)
					{
						// TODO Auto-generated method stub
						Toast.makeText(context, "打开应用市场评分", Toast.LENGTH_SHORT).show();
						Uri uri = Uri.parse("market://details?id="+context.getPackageName());
						mIntent = new Intent(Intent.ACTION_VIEW,uri);
						mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(mIntent);

						return false;
					}
				});

		// system update
		subMenu.add(context.getString(R.string.action_more_update))
				.setIcon(R.drawable.tools_update)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item)
					{
						// TODO Auto-generated method stub
						Toast.makeText(context, "检查更新", Toast.LENGTH_SHORT).show();
						mIntent = new Intent();
						mIntent.setClass(context
								.getApplicationContext(),
								SysUpdateActivity.class);
						Bundle _Bundle = new Bundle();
						_Bundle.putString("system_version", getVersion());
						mIntent.putExtras(_Bundle);
						context.startActivity(mIntent);
						return false;
					}
				});

		// help & feedback
		subMenu.add(context.getString(R.string.action_more_feedback))
				.setIcon(R.drawable.tools_feedback)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item)
					{
						// TODO Auto-generated method stub
						Toast.makeText(context, "帮助与反馈", Toast.LENGTH_SHORT).show();
						mIntent = new Intent();
						mIntent.setClass(context, FeedbackActivity.class);
						context.startActivity(mIntent);
						return false;
					}
				});
	}

	@Override
	public boolean hasSubMenu()
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	//获取版本号
	public String getVersion() {
	    try {
	        PackageManager manager = context.getPackageManager();
	        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
	        String version = info.versionName;
	        return version;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
