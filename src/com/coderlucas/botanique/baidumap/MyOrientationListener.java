package com.coderlucas.botanique.baidumap;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyOrientationListener implements SensorEventListener
{
	private Context mContext;
	private SensorManager mSensorManager;
	private Sensor mSensor;

	private float mLastX;

	private OnOrientationListener mOnOrientationListener;

	public MyOrientationListener(Context context)
	{
		this.mContext = context;
	}

	// ��ʼ
	public void start()
	{
		// ��ô�����������
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager != null)
		{
			// ��÷��򴫸���
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}
		// ע��
		if (mSensor != null)
		{
			mSensorManager.registerListener(this, mSensor,
					SensorManager.SENSOR_DELAY_UI);
		}

	}

	// ֹͣ���
	public void stop()
	{
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{

	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// ���ܷ����Ӧ��������
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			// �������ǿ��Եõ����ݣ�Ȼ�������Ҫ������
			float x = event.values[SensorManager.DATA_X];

			if (Math.abs(x - mLastX) > 1.0)
			{
				mOnOrientationListener.onOrientationChanged(x);
			}

			mLastX = x;
		}
	}

	public void setOnOrientationListener(
			OnOrientationListener onOrientationListener)
	{
		this.mOnOrientationListener = onOrientationListener;
	}

	public interface OnOrientationListener
	{
		void onOrientationChanged(float x);
	}

}
