package com.coderlucas.botanique;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends Activity
{
	private HeadBar mHeadBar = null;
	private TextView mTextSugg = null;
	private EditText mEditSugg = null;
	private Button mBtnSugg = null;
	private TextView mTextContact = null;
	private ImageView mImageContact1 = null;
	private ImageView mImageContact2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_feedback);

		
		/* 配置标题栏 */
		mHeadBar = (HeadBar) findViewById(R.id.feedback_headbar);
		mHeadBar.setTextTitleResource(getString(R.string.text_feedback_headbar_title));
		mHeadBar.setImageResource(R.drawable.headbar_back);
		mHeadBar.setHeadBarOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				FeedbackActivity.this.finish();
			}
		});
		
		
		mTextSugg = (TextView) findViewById(R.id.feedback_sugtext);
		mEditSugg = (EditText) findViewById(R.id.feedback_sugedit);
		mBtnSugg = (Button) findViewById(R.id.feedback_sugbtn);
		mBtnSugg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String _StrSugg = mEditSugg.getText().toString();
				Log.i("suggestion", _StrSugg);
				Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();

				// 发送到服务器

				
			}
		});

		mTextContact = (TextView) findViewById(R.id.feedback_contacttext);
		mImageContact1 = (ImageView) findViewById(R.id.feedback_contactimg1);
		mImageContact1.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v)
			{
				// TODO Auto-generated method stub
				ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cmb.setText("Lucas_Hsueh");
				Toast.makeText(getApplicationContext(), "已复制微信号到剪贴板，请在微信中粘贴添加好友", Toast.LENGTH_LONG).show();
				return false;
			}
		});

		mImageContact2 = (ImageView) findViewById(R.id.feedback_contactimg2);
		mImageContact2.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v)
			{
				// TODO Auto-generated method stub
				String _StrUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1007840913";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(_StrUrl)));
				return false;
			}
		});

	}

}
