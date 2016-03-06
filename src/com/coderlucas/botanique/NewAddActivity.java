package com.coderlucas.botanique;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.coderlucas.botanique.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewAddActivity extends Activity
{

	private HeadBar mHeadBar = null;
	private int mStepCount = 0;

	private RelativeLayout mLayout1 = null;
	private TextView mTextName = null;
	private EditText mEditName = null;
	private TextView mTextDescribe = null;
	private EditText mEditDescribe = null;
	private TextView mTextPosition = null;
	private EditText mEditPosition = null;
	private TextView mTextUser = null;
	private EditText mEditUser = null;
	private TextView mTextNext = null;

	private GridLayout mGridLayout = null;
	private RelativeLayout mLayout2 = null;
	private ImageView mBtnRoot = null;
	private ImageView mBtnStem = null;
	private ImageView mBtnLeaf = null;
	private ImageView mBtnFlower = null;
	private ImageView mBtnFruit = null;
	private TextView mTextOkBtn = null;

	private int mGridLayoutColumnCount = 4;

	private String mStrName = null;
	private String mStrDescribe = null;
	private String mStrPosition = null;
	private String mStrUser = null;

	private List<Uri> mOutPutFileUri = new ArrayList<Uri>();

	// �ļ���Botanique_img
	String mPath = Environment.getExternalStorageDirectory()
			.toString() + "/Botanique_img";
	
	File mFilePath = new File(mPath);
	File mFileFullName = null;
	String mStrFileName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_newadd);

		/* ���ñ����� */
		mHeadBar = (HeadBar) findViewById(R.id.newadd_headbar);
		mHeadBar.setTextTitleResource(getString(R.string.text_addnew_headbar_title));
		mHeadBar.setImageResource(R.drawable.headbar_back);
		mHeadBar.setHeadBarOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				if (mStepCount == 1)
				{
					NewAddActivity.this.finish();
				}
				else if (mStepCount == 2)
				{
					// ���صڶ���
					mStepCount = 1;
					mLayout2.setVisibility(View.GONE);
					// ��ʾ��һ��
					mLayout1.setVisibility(View.VISIBLE);
					mEditName.setText(mStrName);
					mEditDescribe.setText(mStrDescribe);
					mEditPosition.setText(mStrPosition);
					mEditUser.setText(mStrUser);
				}
			}
		});

		/* ���õ�һ�� */
		mLayout1 = (RelativeLayout) findViewById(R.id.newadd_layout1);
		mTextName = (TextView) findViewById(R.id.newadd_nametext);
		mEditName = (EditText) findViewById(R.id.newadd_nameedit);
		mTextDescribe = (TextView) findViewById(R.id.newadd_describtext);
		mEditDescribe = (EditText) findViewById(R.id.newadd_describedit);
		mTextPosition = (TextView) findViewById(R.id.newadd_positiontext);
		mEditPosition = (EditText) findViewById(R.id.newadd_positionedit);
		mTextUser = (TextView) findViewById(R.id.newadd_usertext);
		mEditUser = (EditText) findViewById(R.id.newadd_useredit);
		mTextNext = (TextView) findViewById(R.id.newadd_next);

		/* ���õڶ��� */
		mLayout2 = (RelativeLayout) findViewById(R.id.newadd_layout2);

		mTextOkBtn = (TextView) findViewById(R.id.newadd_ok);

		LayoutParams _Params = getSizeOfScreen();

		mBtnRoot = (ImageView) findViewById(R.id.newadd_img_root);
		LayoutParams _BtnTempParams = mBtnRoot.getLayoutParams();
		_BtnTempParams.width = _Params.width;
		_BtnTempParams.height = _Params.height;
		mBtnRoot.setLayoutParams(_BtnTempParams);
		mBtnRoot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "��", Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				if (!mFilePath.exists())
				{
					mFilePath.mkdirs();
				}
				mStrFileName = "root" + System.currentTimeMillis() + ".jpg";
				mFileFullName = new File(mFilePath, mStrFileName);
				mOutPutFileUri.add(Uri.fromFile(mFileFullName));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileFullName));
				startActivityForResult(intent, Keys.KEY_NEWADD_IMG_ROOT);
			}
		});

		mBtnStem = (ImageView) findViewById(R.id.newadd_img_stem);
		LayoutParams _BtnTempParams1 = mBtnStem.getLayoutParams();
		_BtnTempParams1.width = _Params.width;
		_BtnTempParams1.height = _Params.height;
		mBtnStem.setLayoutParams(_BtnTempParams1);
		mBtnStem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "��", Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				if (!mFilePath.exists())
				{
					mFilePath.mkdirs();
				}
				mStrFileName = "stem" + System.currentTimeMillis() + ".jpg";
				mFileFullName = new File(mFilePath, mStrFileName);
				mOutPutFileUri.add(Uri.fromFile(mFileFullName));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileFullName));
				startActivityForResult(intent, Keys.KEY_NEWADD_IMG_STEM);
			}
		});

		mBtnLeaf = (ImageView) findViewById(R.id.newadd_img_leaf);
		LayoutParams _BtnTempParams2 = mBtnLeaf.getLayoutParams();
		_BtnTempParams2.width = _Params.width;
		_BtnTempParams2.height = _Params.height;
		mBtnLeaf.setLayoutParams(_BtnTempParams2);
		mBtnLeaf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Ҷ", Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				if (!mFilePath.exists())
				{
					mFilePath.mkdirs();
				}
				mStrFileName = "leaf" + System.currentTimeMillis() + ".jpg";
				mFileFullName = new File(mFilePath, mStrFileName);
				mOutPutFileUri.add(Uri.fromFile(mFileFullName));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileFullName));
				startActivityForResult(intent, Keys.KEY_NEWADD_IMG_LEAF);
			}
		});

		mBtnFlower = (ImageView) findViewById(R.id.newadd_img_flower);
		LayoutParams _BtnTempParams3 = mBtnFlower.getLayoutParams();
		_BtnTempParams3.width = _Params.width;
		_BtnTempParams3.height = _Params.height;
		mBtnFlower.setLayoutParams(_BtnTempParams3);
		mBtnFlower.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "��", Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				if (!mFilePath.exists())
				{
					mFilePath.mkdirs();
				}
				mStrFileName = "flower" + System.currentTimeMillis() + ".jpg";
				mFileFullName = new File(mFilePath, mStrFileName);
				mOutPutFileUri.add(Uri.fromFile(mFileFullName));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileFullName));
				startActivityForResult(intent, Keys.KEY_NEWADD_IMG_FLOWER);
			}
		});

		mBtnFruit = (ImageView) findViewById(R.id.newadd_img_fruit);
		LayoutParams _BtnTempParams4 = mBtnFruit.getLayoutParams();
		_BtnTempParams4.width = _Params.width;
		_BtnTempParams4.height = _Params.height;
		mBtnFruit.setLayoutParams(_BtnTempParams4);
		mBtnFruit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "��ʵ",
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				if (!mFilePath.exists())
				{
					mFilePath.mkdirs();
				}
				mStrFileName = "fruit" + System.currentTimeMillis() + ".jpg";
				mFileFullName = new File(mFilePath, mStrFileName);
				mOutPutFileUri.add(Uri.fromFile(mFileFullName));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileFullName));
				startActivityForResult(intent, Keys.KEY_NEWADD_IMG_FRUIT);
			}
		});

		// ����л�
		mTextNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// ����Ϊ�ڶ���
				mStepCount = 2;
				mLayout1.setVisibility(View.GONE);
				mLayout2.setVisibility(View.VISIBLE);

				// �����һ������
				mStrName = mEditName.getText().toString();
				mStrDescribe = mEditDescribe.getText().toString();
				mStrPosition = mEditPosition.getText().toString();
				mStrUser = mEditUser.getText().toString();

			}
		});

		mTextOkBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// �ϴ�����

				// ����Activity
				NewAddActivity.this.finish();
			}
		});

		// ��ʼΪ��һ��
		mStepCount = 1;
		mLayout2.setVisibility(View.GONE);
		Log.i("NewAddActivity", "NullPoint flag 16");
	}

	public LayoutParams getSizeOfScreen()
	{

		DisplayMetrics _DisplayMetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(_DisplayMetrics);

		LayoutParams _Params = new LayoutParams(
				((_DisplayMetrics.widthPixels - 10) * 1 / 2),
				((_DisplayMetrics.widthPixels - 10) * 1 / 2));
		// _Params.height = _DisplayMetrics.heightPixels * 1 / 2;
		// _Params.width = _DisplayMetrics.widthPixels * 1 / 2;
		// _Params.height = _Params.width;
		return _Params;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/*
		 * Log.d("NewAdd", "onActivityResult: requestCode: " + requestCode +
		 * ", resultCode: " + requestCode + ", data: " + data);
		 */

		Uri imageUri = null;

		switch (requestCode)
		{
			case Keys.KEY_NEWADD_IMG_ROOT:

				/*
				 * Toast.makeText(getApplicationContext(), "��ʾ��ͼ",
				 * Toast.LENGTH_SHORT).show();
				 */

				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// ��������ͼ
						mBtnRoot.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// ����mOutPutFileUri�е�����ͼ��
				
					Log.i("root", mFilePath.toString()+mStrFileName);
					if(searchFile(mStrFileName, mFilePath))
					{
						mBtnRoot.setImageResource(R.drawable.root_img_finish);
					}
				}

				break;

			case Keys.KEY_NEWADD_IMG_STEM:
				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// ��������ͼ
						mBtnStem.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// ����mOutPutFileUri�е�����ͼ��
					Log.i("stem", mFilePath.toString()+mStrFileName);
					if(searchFile(mStrFileName, mFilePath))
					{
						mBtnStem.setImageResource(R.drawable.stem_img_finish);
					}
				}
				break;

			case Keys.KEY_NEWADD_IMG_LEAF:
				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// ��������ͼ
						mBtnLeaf.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// ����mOutPutFileUri�е�����ͼ��
					Log.i("leaf", mFilePath.toString()+mStrFileName);
					if(searchFile(mStrFileName, mFilePath))
					{
						mBtnLeaf.setImageResource(R.drawable.leaf_img_finish);
					}
				}
				break;

			case Keys.KEY_NEWADD_IMG_FLOWER:
				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// ��������ͼ
						mBtnFlower.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// ����mOutPutFileUri�е�����ͼ��
					Log.i("flower", mFilePath.toString()+mStrFileName);
					if(searchFile(mStrFileName, mFilePath))
					{
						mBtnFlower.setImageResource(R.drawable.flower_img_finish);
					}
				}
				break;

			case Keys.KEY_NEWADD_IMG_FRUIT:
				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// ��������ͼ
						mBtnFruit.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// ����mOutPutFileUri�е�����ͼ��
					// mBtnFruit.setImageBitmap(getBitmapFromUri(mOutPutFileUri));
					Log.i("fruit", mFilePath.toString()+mStrFileName);
					if(searchFile(mStrFileName, mFilePath))
					{
						mBtnFruit
							.setBackgroundResource(R.drawable.fruit_img_finish);
					}
				}
				break;

			default:
				break;
		}
	}

	/*
	 * searchFile �����ļ������뵽ArrayList ����ȥ
	 * 
	 * @String keyword ���ҵĹؼ���
	 * 
	 * @File filepath ���ҵ�Ŀ¼
	 */
	private boolean searchFile(String keyword, File filepath)
	{

		// �ж�SD���Ƿ����
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
		{
			File[] files = filepath.listFiles();

			if (files.length > 0)
			{
				for (File file : files)
				{
					if (file.isDirectory())
					{
						// ���Ŀ¼�ɶ���ִ�У�һ��Ҫ�ӣ���Ȼ��ҵ���
						if (file.canRead())
						{
							Log.i("search file", file.toString());
							searchFile(keyword, file); // �����Ŀ¼���ݹ����
						}
					}
					else
					{
						// �ж����ļ���������ļ����ж�
						try
						{
							if (file.getName().indexOf(keyword) > -1
									|| file.getName().indexOf(
											keyword.toUpperCase()) > -1)
							{
								Log.i("search file", "true");
								return true;
							}
						}
						catch (Exception e)
						{
							Toast.makeText(this, "���ҷ�������", Toast.LENGTH_SHORT)
									.show();
						}
					
					}
				}
			}
			return false;
		}
		return false;
	}

	private Bitmap getBitmapFromUri(Uri uri)
	{
		try
		{
			// ��ȡuri���ڵ�ͼƬ
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), uri);
			return bitmap;
		}
		catch (Exception e)
		{
			Log.e("[Android]", e.getMessage());
			Log.e("[Android]", "Ŀ¼Ϊ��" + uri);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();

		if (mStepCount == 2)
		{
			// ���ص�һ��
			mLayout1.setVisibility(View.GONE);
			mEditName.setText(mStrName);
			mEditDescribe.setText(mStrDescribe);
			mEditPosition.setText(mStrPosition);
			mEditUser.setText(mStrUser);
			// ��ʾ�ڶ���
			// mStepCount = 2;
			mLayout2.setVisibility(View.VISIBLE);
		}

	}

}
