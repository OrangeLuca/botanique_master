package com.coderlucas.botanique;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.SearchView;

public class MySearchView extends SearchView
{

	public MySearchView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		// TODO Auto-generated constructor stub
	}
	
	public int getSearchIconId() {
	    TypedValue outValue = new TypedValue();
	   // getContext().getTheme().resolveAttribute(com.coderlucas.botanique.R.drawable.tools_search_unselected,outValue, true);
	    return outValue.resourceId;

	}

}
