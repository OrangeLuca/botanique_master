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

	// 文件夹Botanique_img
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

		/* 配置标题栏 */
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
					// 隐藏第二层
					mStepCount = 1;
					mLayout2.setVisibility(View.GONE);
					// 显示第一层
					mLayout1.setVisibility(View.VISIBLE);
					mEditName.setText(mStrName);
					mEditDescribe.setText(mStrDescribe);
					mEditPosition.setText(mStrPosition);
					mEditUser.setText(mStrUser);
				}
			}
		});

		/* 配置第一层 */
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

		/* 配置第二层 */
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
				Toast.makeText(getApplicationContext(), "根", Toast.LENGTH_SHORT)
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
				Toast.makeText(getApplicationContext(), "茎", Toast.LENGTH_SHORT)
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
				Toast.makeText(getApplicationContext(), "叶", Toast.LENGTH_SHORT)
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
				Toast.makeText(getApplicationContext(), "花", Toast.LENGTH_SHORT)
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
				Toast.makeText(getApplicationContext(), "果实",
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

		// 层次切换
		mTextNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// 设置为第二层
				mStepCount = 2;
				mLayout1.setVisibility(View.GONE);
				mLayout2.setVisibility(View.VISIBLE);

				// 保存第一层数据
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
				// 上传数据

				// 结束Activity
				NewAddActivity.this.finish();
			}
		});

		// 初始为第一层
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
				 * Toast.makeText(getApplicationContext(), "显示根图",
				 * Toast.LENGTH_SHORT).show();
				 */

				if (data != null)
				{
					if (data.hasExtra("data"))
					{
						Bitmap thunbnail = data.getParcelableExtra("data");

						// 处理缩略图
						mBtnRoot.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// 处理mOutPutFileUri中的完整图像
				
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

						// 处理缩略图
						mBtnStem.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// 处理mOutPutFileUri中的完整图像
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

						// 处理缩略图
						mBtnLeaf.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// 处理mOutPutFileUri中的完整图像
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

						// 处理缩略图
						mBtnFlower.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// 处理mOutPutFileUri中的完整图像
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

						// 处理缩略图
						mBtnFruit.setImageBitmap(thunbnail);
					}
				}
				else
				{
					// 处理mOutPutFileUri中的完整图像
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
	 * searchFile 查找文件并加入到ArrayList 当中去
	 * 
	 * @String keyword 查找的关键词
	 * 
	 * @File filepath 查找的目录
	 */
	private boolean searchFile(String keyword, File filepath)
	{

		// 判断SD卡是否存在
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
						// 如果目录可读就执行（一定要加，不然会挂掉）
						if (file.canRead())
						{
							Log.i("search file", file.toString());
							searchFile(keyword, file); // 如果是目录，递归查找
						}
					}
					else
					{
						// 判断是文件，则进行文件名判断
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
							Toast.makeText(this, "查找发生错误", Toast.LENGTH_SHORT)
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
			// 读取uri所在的图片
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), uri);
			return bitmap;
		}
		catch (Exception e)
		{
			Log.e("[Android]", e.getMessage());
			Log.e("[Android]", "目录为：" + uri);
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
			// 隐藏第一层
			mLayout1.setVisibility(View.GONE);
			mEditName.setText(mStrName);
			mEditDescribe.setText(mStrDescribe);
			mEditPosition.setText(mStrPosition);
			mEditUser.setText(mStrUser);
			// 显示第二层
			// mStepCount = 2;
			mLayout2.setVisibility(View.VISIBLE);
		}

	}

}
