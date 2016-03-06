package com.coderlucas.botanique.tabmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.coderlucas.botanique.MenuItem;
import com.coderlucas.botanique.MenuItemAccount;
import com.coderlucas.botanique.R;

/** 
 * @ClassName: TabAccountFragment 
 * @Description: Contain MyAccount Tab
 * @author coderLucas Lucas_hsueh?foxmail?com 
 * @date 2015年5月6日 下午2:39:03 
 *  
 */ 
public class TabAccountFragment extends Fragment
{
	private static TabAccountFragment _this = null;
		
	private MenuItemAccount mMenuItemAccount;
	private MenuItem mMenuItemMyAlbum;
	private MenuItem mMenuItemMyMessage;
	private MenuItem mMenuItemMyFound;

	public TabAccountFragment()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public static TabAccountFragment newInstance()
	{
		if(_this == null)
		{
			_this = new TabAccountFragment();
			Bundle bundle = new Bundle();
			bundle.putString("abc", "abc");
			_this.setArguments(bundle);
		}
		return _this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View codeView = inflater.inflate(R.layout.layout_account, container, false);
		return codeView;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		mMenuItemAccount = (MenuItemAccount)getActivity().findViewById(R.id.item_account_info);
		mMenuItemAccount.setImageResource(R.drawable.logo);
		mMenuItemAccount.setTextTitleResource(getString(R.string.text_account_title));
		mMenuItemAccount.setTextInfoResource(getString(R.string.text_account_info));
		mMenuItemAccount.setMenuItemAccountOnClickListener(mMenuItemAccount, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "账户信息", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		mMenuItemMyAlbum = (MenuItem)getActivity().findViewById(R.id.item_account_myalbum);
		mMenuItemMyAlbum.setImageResource(R.drawable.logo);
		mMenuItemMyAlbum.setTextTitleResource(getString(R.string.text_account_album));
		mMenuItemMyAlbum.setMenuItemOnClickListener(mMenuItemMyAlbum, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "我的相册", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		mMenuItemMyMessage = (MenuItem)getActivity().findViewById(R.id.item_account_mymessage);
		mMenuItemMyMessage.setImageResource(R.drawable.logo);
		mMenuItemMyMessage.setTextTitleResource(getString(R.string.text_account_message));
		mMenuItemMyMessage.setMenuItemOnClickListener(mMenuItemMyMessage, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "我的树洞", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		mMenuItemMyFound = (MenuItem)getActivity().findViewById(R.id.item_account_myfound);
		mMenuItemMyFound.setImageResource(R.drawable.logo);
		mMenuItemMyFound.setTextTitleResource(getString(R.string.text_account_addnew));
		mMenuItemMyFound.setMenuItemOnClickListener(mMenuItemMyFound, new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "我的发现", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
	
	
}
