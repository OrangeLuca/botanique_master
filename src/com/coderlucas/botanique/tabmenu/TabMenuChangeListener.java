package com.coderlucas.botanique.tabmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.coderlucas.botanique.R;

/** 
 * @ClassName: TabMenuChangeListener 
 * @Description: Manage Tabs
 * @author coderLucas Lucas_hsueh?foxmail?com 
 * @date 2015年5月6日 下午2:40:57 
 *  
 */ 
public class TabMenuChangeListener implements OnCheckedChangeListener
{
	private FragmentActivity context;
	
	public TabMenuChangeListener(FragmentActivity context)
	{
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	private static Fragment mLastFragment = TabMapFragment.newInstance();
	
	private void exchange(Fragment newFragment)
	{
		if(newFragment != mLastFragment)
		{
			context.getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, newFragment)
					.remove(mLastFragment)
					.commit();
		}
		mLastFragment = newFragment;
	}
	
	/* (non-Javadoc) 
	 * <p>Title: onCheckedChanged</p> 
	 * <p>Description: Dynamic display Tabs</p> 
	 * @param group
	 * @param checkedId 
	 * @see android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android.widget.RadioGroup, int) 
	 */ 
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		switch (checkedId)
		{
			case R.id.tab_map:
				TabMapFragment _MapFragment = TabMapFragment.newInstance();
				exchange(_MapFragment);
				break;
				
			case R.id.tab_qrcode:
				TabQRCodeFragment _QRCodeFragment = TabQRCodeFragment.newInstance();
				exchange(_QRCodeFragment);	
				break;
				
			case R.id.tab_addnew:
				TabAddNewFragment _AddNewFragment = TabAddNewFragment.newInstance();
				exchange(_AddNewFragment);
				break;
				
			case R.id.tab_account:
				TabAccountFragment _AccountFragment = TabAccountFragment.newInstance();
				exchange(_AccountFragment);
				break;

			default:
				try
				{
					Log.e("TabMenuChangeListener", "Cannot get tab ID.");
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
				break;
		}
		// TODO Auto-generated method stub

	}

}
