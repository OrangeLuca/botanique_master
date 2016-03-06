package com.coderlucas.botanique.tabmenu;

import java.util.Hashtable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coderlucas.botanique.MenuItem;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.qr.MipcaActivityCapture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.mining.app.zxing.image.RGBLuminanceSource;

/**
 * @ClassName: TabQRCodeFragment
 * @Description: QRCode Tab, Menu of choosing camera or local pic.
 * @author coderLucas Lucas_hsueh?foxmail?com
 * @date 2015年5月6日 下午2:42:19
 * 
 */
public class TabQRCodeFragment extends Fragment
{
	/*
	 * private final static int SCANNIN_PIC_GREQUEST_CODE = 1; private final
	 * static int SCANNIN_CAMERA_GREQUEST_CODE = 2;
	 */

	private static TabQRCodeFragment _this = null;
	private MenuItem mPicMenuItem;
	private MenuItem mCameraMenuItem;

	public TabQRCodeFragment()
	{
		// TODO Auto-generated constructor stub
	}

	public static TabQRCodeFragment newInstance()
	{
		if (_this == null)
		{
			_this = new TabQRCodeFragment();
			Bundle bundle = new Bundle();
			bundle.putString("abc", "abc");
			_this.setArguments(bundle);
		}
		return _this;
	}

	/*
	 * Button _mPicBtn; Button _mCameraBtn;
	 */

	/*
	 * ViewfinderView mViewfinderView; boolean mHasSurface; InactivityTimer
	 * mInactivityTimer;
	 */
	/* int SCANNIN_PIC_GREQUEST_CODE = 1; */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View _CodeView = inflater.inflate(R.layout.layout_qrcode_menu,
				container, false);

		/*
		 * mCameraBtn = (MenuItem)_CodeView.findViewById(R.id.qr_camera_btn);
		 * mCameraBtn.setTextTitleResource(getString(R.string.text_camera));
		 * mCameraBtn.setImageResource(R.drawable.logo);
		 * mCameraBtn.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * Log.i("QR Fragment","camerbtn clicked"); // TODO Auto-generated
		 * method stub Intent _Intent = new Intent();
		 * _Intent.setClass(getActivity().getApplicationContext(),
		 * MipcaActivityCapture.class); //getActivity().startActivity(_Intent);
		 * startActivityForResult(_Intent, CAMERA_REQUEST_CODE); } });
		 * 
		 * 
		 * mPicBtn = (MenuItem)_CodeView.findViewById(R.id.qr_pic_btn);
		 * mPicBtn.setTextTitleResource(getString(R.string.text_local_pic));
		 * mPicBtn.setImageResource(R.drawable.logo);
		 * mPicBtn.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent _InnerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		 * _InnerIntent.setType("image/*"); Intent _WrapperIntent =
		 * Intent.createChooser(_InnerIntent, "选择图片");
		 * startActivityForResult(_WrapperIntent, PIC_REQUEST_CODE);
		 * 
		 * } });
		 */
		/*
		 * Intent _Intent = new Intent(); _Intent.setClass(getActivity(),
		 * MipcaActivityCapture.class);
		 * _Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 * getActivity().startActivityForResult(_Intent,
		 * SCANNIN_PIC_GREQUEST_CODE);
		 * 
		 * Log.i("TabQRCodeFragment", "startActivity");
		 */
		/*
		 * _mPicBtn = (Button)_CodeView.findViewById(R.id.qr_pic_btn);
		 * _mCameraBtn = (Button)_CodeView.findViewById(R.id.qr_camera_btn);
		 * 
		 * _mPicBtn.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent _Intent = new Intent(); _Intent.setClass(getActivity(),
		 * QRPicActivity.class);
		 * _Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 * startActivityForResult(_Intent, SCANNIN_PIC_GREQUEST_CODE); } });
		 * 
		 * _mCameraBtn.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent _Intent = new Intent(); _Intent.setClass(getActivity(),
		 * QRCameraActivity.class);
		 * _Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 * startActivityForResult(_Intent, SCANNIN_CAMERA_GREQUEST_CODE); } });
		 */

		/*
		 * CameraManager.init(getActivity().getApplication()); mViewfinderView =
		 * (ViewfinderView)getActivity().findViewById(R.id.viewfinder_view);
		 * 
		 * Button _BackButton
		 * =(Button)getActivity().findViewById(R.id.button_back);
		 * _BackButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * 
		 * } });
		 * 
		 * mHasSurface = false; mInactivityTimer = new
		 * InactivityTimer(getActivity());
		 */

		return _CodeView;
	}

	private final static int CAMERA_REQUEST_CODE = 9990;
	private final static int PIC_REQUEST_CODE = 9991;
	private static final int PARSE_BARCODE_SUC = 300;
	private static final int PARSE_BARCODE_FAIL = 303;
	private TextView mTextView;
	private String mPhotoPath;
	private ProgressDialog mProgressDialog;

	private  Handler mHandler = new Handler(){

		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			mProgressDialog.dismiss();
			switch (msg.what)
			{
				case PARSE_BARCODE_SUC:
					onResultHandler((String) msg.obj, mScanBitmap);
					break;
				case PARSE_BARCODE_FAIL:
					Toast.makeText(getActivity(), (String) msg.obj,
							Toast.LENGTH_LONG).show();
					break;
			}
		}
	};

	private void onResultHandler(String resultString, Bitmap bitmap)
	{
		if (TextUtils.isEmpty(resultString))
		{
			Toast.makeText(getActivity(), "Scan failed!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Intent resultIntent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("result", resultString);
		bundle.putParcelable("bitmap", bitmap);
		resultIntent.putExtras(bundle);
		getActivity().setResult(Activity.RESULT_OK, resultIntent);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		mCameraMenuItem = (MenuItem)getActivity().findViewById(R.id.qr_camera_btn);
		mCameraMenuItem.setTextTitleResource(getString(R.string.text_camera));
		mCameraMenuItem.setImageResource(R.drawable.icon_qrcode_camera);
		mCameraMenuItem.setMenuItemOnClickListener(mCameraMenuItem, new View.OnClickListener(){

			@Override
			public void onClick(View v)
			{
				//Log.i("QR Fragment", "camerbtn clicked");
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(getActivity().getApplicationContext(),
						MipcaActivityCapture.class);
				// getActivity().startActivity(_Intent);
				startActivityForResult(_Intent, CAMERA_REQUEST_CODE);
			}
		});

		mPicMenuItem = (MenuItem)getActivity().findViewById(R.id.qr_pic_btn);
		//Log.i("mPicMenuItem", mPicMenuItem.toString());
		mPicMenuItem.setTextTitleResource(getString(R.string.text_local_pic));
		mPicMenuItem.setImageResource(R.drawable.icon_qrcode_pic);
		mPicMenuItem.setMenuItemOnClickListener(mPicMenuItem, new View.OnClickListener() {

			@Override
			public void onClick(View v)
			{
				//Log.i("mPicBtn", "Clicked!");
				// TODO Auto-generated method stub
				Intent _InnerIntent = new Intent(Intent.ACTION_GET_CONTENT);
				_InnerIntent.setType("image/*");
				Intent _WrapperIntent = Intent.createChooser(_InnerIntent,
						"选择图片");
				startActivityForResult(_WrapperIntent, PIC_REQUEST_CODE);

			}
		});
		

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
			case CAMERA_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK)
				{
					Bundle bundle = data.getExtras();
					// 显示扫描到的内容
					mTextView = (TextView) getActivity().findViewById(
							R.id.textview_result);
					mTextView.setText(bundle.getString("result"));

				}
				break;

			case PIC_REQUEST_CODE:

				Cursor cursor = getActivity().getContentResolver().query(
						data.getData(), null, null, null, null);
				
				if (cursor.moveToFirst())
				{
					mPhotoPath = cursor.getString(cursor
							.getColumnIndex(MediaStore.Images.Media.DATA));
				}
				cursor.close();

				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setMessage("正在扫描...");
				mProgressDialog.setCancelable(false); 
				mProgressDialog.show();
				 
				new Thread(new Runnable() {
					@Override
					public void run()
					{
						Result result = scanningImage(mPhotoPath);
						//Log.i("mPhotoPath", mPhotoPath);
						//Log.i("result", result.toString());

						if (result == null)
						{
							Looper.prepare();
							Message _Message = mHandler.obtainMessage();
							_Message.what = PARSE_BARCODE_FAIL;
							_Message.obj = "扫描失败！";
							mHandler.sendMessage(_Message);
							Looper.loop();
						}
						else
						{
							//Log.i("result", result.toString());
							Message _Message = mHandler.obtainMessage();
							_Message.what = PARSE_BARCODE_SUC;
							_Message.obj = result.getText();
							mHandler.sendMessage(_Message);
						}
					}
				}).start();
				break;

			default:
				break;
		}
	}

	private Bitmap mScanBitmap;

	public Result scanningImage(String path)
	{
		if (TextUtils.isEmpty(path))
		{
			return null;
		}

		Hashtable<DecodeHintType, String> _Hints = new Hashtable<DecodeHintType, String>();
		_Hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); // 设置二维码内容的编码

		BitmapFactory.Options _Options = new BitmapFactory.Options();
		_Options.inJustDecodeBounds = true; // 先获取原大小
		mScanBitmap = BitmapFactory.decodeFile(path, _Options);
		_Options.inJustDecodeBounds = false; // 获取新的大小

		int sampleSize = (int) (_Options.outHeight / (float) 200);
		if (sampleSize <= 0)
		{
			sampleSize = 1;
		}

		_Options.inSampleSize = sampleSize;
		mScanBitmap = BitmapFactory.decodeFile(path, _Options);
		RGBLuminanceSource _RGBLuminanceSource = new RGBLuminanceSource(
				mScanBitmap);
		BinaryBitmap _BinaryBitmap = new BinaryBitmap(new HybridBinarizer(
				_RGBLuminanceSource));
		QRCodeReader _QRCodeReader = new QRCodeReader();

		try
		{
			return _QRCodeReader.decode(_BinaryBitmap, _Hints);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (ChecksumException e)
		{
			e.printStackTrace();
		}
		catch (FormatException e)
		{
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * private String mCharacterSet; private Vector<BarcodeFormat>
	 * mDecodeFormats; private boolean mPlayBeep; private boolean mVibrate;
	 * 
	 * 
	 * @Override public void onResume() { // TODO Auto-generated method stub
	 * super.onResume();
	 * 
	 * SurfaceView _SurfaceView =
	 * (SurfaceView)getActivity().findViewById(R.id.preview_view); SurfaceHolder
	 * _SurfaceHolder = _SurfaceView.getHolder();
	 * 
	 * if(mHasSurface) { initCamera(_SurfaceHolder); } else {
	 * _SurfaceHolder.addCallback((Callback) getActivity());
	 * //_SurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); }
	 * mDecodeFormats = null; mCharacterSet = null;
	 * 
	 * mPlayBeep = true; AudioManager audioService =
	 * (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
	 * 
	 * if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
	 * mPlayBeep = false; }
	 * 
	 * initBeepSound(); mVibrate = true; }
	 * 
	 * 
	 * private CaptureActivityHandler mHandler;
	 * 
	 * @Override public void onPause() { // TODO Auto-generated method stub
	 * super.onPause();
	 * 
	 * if(mHandler != null) { mHandler.quitSynchronously(); mHandler = null; }
	 * CameraManager.get().closeDriver(); }
	 * 
	 * @Override public void onDestroy() { mInactivityTimer.shutdown();
	 * super.onDestroy(); }
	 * 
	 * int RESULT_OK = 3;
	 * 
	 * public void handleDecode(Result result, Parcelable barcode) {
	 * mInactivityTimer.onActivity(); playBeepSoundAndVibrate(); String
	 * resultString = result.getText();
	 * 
	 * if(resultString.equals("")) { Toast.makeText(getActivity(),
	 * "Scan failed!", Toast.LENGTH_SHORT).show(); } else { Intent resultIntent
	 * = new Intent(); Bundle bundle = new Bundle(); bundle.putString("result",
	 * resultString); bundle.putParcelable("bitmap", barcode);
	 * resultIntent.putExtras(bundle); getActivity().setResult(RESULT_OK,
	 * resultIntent); } //MipcaActivityCapture.this.finish(); }
	 * 
	 * private void initCamera(SurfaceHolder surfaceHolder) { try {
	 * CameraManager.get().openDriver(surfaceHolder); } catch(IOException ioe) {
	 * return; } catch(RuntimeException e) { return; } if(mHandler == null) {
	 * //mHandler = new CaptureActivityHandler(getActivity(), mDecodeFormats,
	 * mCharacterSet); } }
	 * 
	 * public void surfaceChanged(SurfaceHolder holder, int format, int width,
	 * int height) {
	 * 
	 * }
	 * 
	 * 
	 * public void surfaceCreated(SurfaceHolder holder) { if(!mHasSurface) {
	 * mHasSurface = true; initCamera(holder); }
	 * 
	 * }
	 * 
	 * public void surfaceDestroyed(SurfaceHolder holder) { mHasSurface = false;
	 * }
	 * 
	 * public ViewfinderView getViewfinderView() { return mViewfinderView; }
	 * 
	 * public Handler getHandler() { return mHandler; }
	 * 
	 * public void drawViewfinder() { mViewfinderView.drawViewfinder(); }
	 * 
	 * private MediaPlayer mMediaPlayer; private static final float BEEP_VOLUME
	 * = 0.10f;
	 * 
	 * private void initBeepSound() { if(mPlayBeep && mMediaPlayer == null) { //
	 * The volume on STREAM_SYSTEM is not adjustable, and users found it // too
	 * loud, // so we now play on the music stream.
	 * getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
	 * mMediaPlayer = new MediaPlayer();
	 * mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	 * mMediaPlayer.setOnCompletionListener(beepListener);
	 * 
	 * AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
	 * try { mMediaPlayer.setDataSource(file.getFileDescriptor(),
	 * file.getStartOffset(), file.getLength()); file.close();
	 * mMediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME); mMediaPlayer.prepare();
	 * } catch (IOException e) { mMediaPlayer = null; } } }
	 * 
	 * private static final long VIBRATE_DURATION = 200L;
	 * 
	 * private void playBeepSoundAndVibrate() { if(mPlayBeep && mMediaPlayer !=
	 * null) { mMediaPlayer.start(); } if(mVibrate) { Vibrator vibrator =
	 * (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
	 * vibrator.vibrate(VIBRATE_DURATION); } }
	 *//**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	/*
	 * private final OnCompletionListener beepListener = new
	 * OnCompletionListener() { public void onCompletion(MediaPlayer
	 * mediaPlayer) { mediaPlayer.seekTo(0); } };
	 */
}
