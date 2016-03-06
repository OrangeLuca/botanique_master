package com.coderlucas.botanique;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MySearchBar extends RelativeLayout
{
	private ImageView mImageBack = null;
	private EditText mEditText = null;
	private ImageView mImageDelete = null;
	private Button mBtnSearch = null;

	private String mString = null;

	public MySearchBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub

		LayoutInflater _LayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_LayoutInflater.inflate(R.layout.search_bar, this);

		mImageBack = (ImageView) findViewById(R.id.search_back);

		mEditText = (EditText) findViewById(R.id.search_edittext);
		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				// TODO Auto-generated method stub
				if (mEditText.getTextSize() != 0)
				{
					mImageDelete.setVisibility(View.VISIBLE);
				}
				else
				{
					mImageDelete.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub
				mString = mEditText.toString();
			}
		});

		mImageDelete = (ImageView) findViewById(R.id.search_delete);
		mImageDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mEditText.setText("");
				mImageDelete.setVisibility(View.GONE);
			}
		});

		mBtnSearch = (Button) findViewById(R.id.search_btn);

	}

	public void setBackOnClickedListener(OnClickListener listener)
	{
		mImageBack.setOnClickListener(listener);
	}

	public void setSearchBtnOnClickedListener(OnClickListener listener)
	{
		mBtnSearch.setOnClickListener(listener);
	}

	public String getEditString()
	{
		return mEditText.getText().toString();
	}

}
