package com.coderlucas.botanique;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchClassifyInfoActivity extends Activity
{
	private HeadBar mHeadbar;

	private TextView mTextTitle;
	private TextView mTextBasic;
	private TextView mTextMoreTitle;
	private TextView mTextMoreShow;
	private TextView mTextFindBtn;
	private TextView mTextAlbum;
	private ImageView mTextAlbumImage;
	
	private String mClassifyId = null;
	private MoreInfoData mMoreInfoData;
	private int CLASSIFY_TO_MAP = 888;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_classify_info_activity);
		
		//Log.i("Classify Class" , "flag 1");
		//��ȡֲ�������
		mClassifyId = getIntent().getExtras().getString("SearchClassifyInfokey"); 
		
		
		//Log.i("Classify Class" , "flag 2");
		
		//��ֲ�����Ų�ѯֲ����ϸ��Ϣ���õ�mMoreInfoData
		//mMoreInfoData = new MoreInfoData();
		mMoreInfoData = new MoreInfoData("SZ10000001", "��֦", "������֧", "Lychee", "Litchi chinensis Sonn",
				"�޻��ӿ�", "������ľ", "�ȴ������ȴ�����", "���ڴ����������ļ���", "��֦�����ȴ���������ϲ��ů�� ������ľ����ͨ��������10�ף���ʱ�ɴ�15�׻���ߣ���Ƥ�Һ�ɫ��С֦Բ��״���ֺ�ɫ��������ɫƤ�ס�Ҷ������10-25���׻��֮��СҶ2��3�ԣ�����4�ԣ������ʻ���ʣ������λ���״�����Σ���ʱ����Բ״�����Σ���6-15���ף���2-4���ף���������β״�̽��⣬ȫԵ����������ɫ���й��󣬱������ɫ��������ë����������ϸ���ڸ��治�����ԣ��ڱ������Ի���͹��СҶ����7-8���ס�",
				R.drawable.lizhi, "url.image1", null);
		//Log.i("Classify Class" , "flag 3");
		//getMoreInfoData();
		//Log.i("Classify Class" , "flag 4");
		
		//������
		mHeadbar = (HeadBar)findViewById(R.id.search_classifyinfo_headbar);
		mHeadbar.setTextTitleResource(getString(R.string.text_classify_title));
		mHeadbar.setImageResource(R.drawable.headbar_back);
		//Log.i("Classify Class" , "flag 5");
		mHeadbar.setHeadBarOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "����������", Toast.LENGTH_SHORT).show();
				//����������
				SearchClassifyInfoActivity.this.setResult(RESULT_CANCELED, new Intent());
				SearchClassifyInfoActivity.this.finish();
			}
		});
		
		//������
		//Log.i("Classify Class" , "flag 6");
		mTextTitle = (TextView)findViewById(R.id.search_classifyinfo_title);
		mTextTitle.setText(mMoreInfoData.getCHName());
		
		mTextBasic = (TextView)findViewById(R.id.search_classifyinfo_basic);
		String _BasicInfo = 
				  "��    ����" + mMoreInfoData.getAliasName() 
				+ "\nӢ������" + mMoreInfoData.getENName() 
				+ "\nѧ    ����"  + mMoreInfoData.getSciName() 
				+ "\n��    ����" + mMoreInfoData.getGenusName() 
				+ "\n��    �ͣ�" + mMoreInfoData.getTypeName()
				+ "\nԭ���أ�" + mMoreInfoData.getOriginCountry()
				+ "\nϰ    �ԣ�" + mMoreInfoData.getCharacters();
		//Log.i("Classify Class" , "flag 7");
		mTextBasic.setText(_BasicInfo);
		//Log.i("Classify Class" , "flag 8");
		mTextMoreTitle = (TextView)findViewById(R.id.search_classifyinfo_moreinfo);
		
		mTextMoreShow = (TextView)findViewById(R.id.search_classifyinfo_moreinfo_show);
		mTextMoreShow.setText(mMoreInfoData.getCharacterAndUses());
		
		
		mTextAlbumImage = (ImageView)findViewById(R.id.search_classifyinfo_album_image);
		mTextAlbumImage.setImageDrawable(getResources().getDrawable(mMoreInfoData.getImgId()));
		mTextAlbumImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(SearchClassifyInfoActivity.this, "��ͼ��", Toast.LENGTH_SHORT).show();
				//����ҳ�����ͼ��Activity
				Intent _Intent = new Intent();
				_Intent.setClass(SearchClassifyInfoActivity.this.getApplicationContext(),
						PhotoWallActivity.class);
				//Bundle _Bundle = new Bundle();
				//_Bundle.putString("ClassifyInfoKey", _mClassifyId);
				
				//_Intent.putExtras(_Bundle);
				startActivity(_Intent);
			}
		});
		//Log.i("Classify Class" , "flag 10");
		mTextAlbum = (TextView)findViewById(R.id.search_classifyinfo_album);
		/*mTextAlbum.setCompoundDrawables(null, 
				getResources().getDrawable(mMoreInfoData.getImgId()), null, null);*/
		/*mTextAlbum.setCompoundDrawablesWithIntrinsicBounds(null, 
				getResources().getDrawable(mMoreInfoData.getImgId()), null, null);
		mTextAlbum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(ClassifyInfoActivity.this, "��ͼ��", Toast.LENGTH_SHORT).show();
				//����ҳ�����ͼ��Activity
				
				
			}
		});*/
		Log.i("Classify Class" , "flag 11");
	}

    
	
	private BitmapDrawable BitmapDrawable(Bitmap _Bitmap)
	{
		// TODO Auto-generated method stub
		return null;
	}


	public void getDataFromServer()
	{
		
	}
	
	
	//ģ������
		public static List<MoreInfoData> datas = new ArrayList<MoreInfoData>();  

		static  
		{ 
			datas.add(new MoreInfoData("SZ10000001", "��֦", "������֧", "Lychee", "Litchi chinensis Sonn",
					"�޻��ӿ�", "������ľ", "�ȴ������ȴ�����", "���ڴ����������ļ���", "��֦�����ȴ���������ϲ��ů�� ������ľ����ͨ��������10�ף���ʱ�ɴ�15�׻���ߣ���Ƥ�Һ�ɫ��С֦Բ��״���ֺ�ɫ��������ɫƤ�ס�Ҷ������10-25���׻��֮��СҶ2��3�ԣ�����4�ԣ������ʻ���ʣ������λ���״�����Σ���ʱ����Բ״�����Σ���6-15���ף���2-4���ף���������β״�̽��⣬ȫԵ����������ɫ���й��󣬱������ɫ��������ë����������ϸ���ڸ��治�����ԣ��ڱ������Ի���͹��СҶ����7-8���ס�",
					R.drawable.lizhi, "url.image1", null));  
			datas.add(new MoreInfoData("SZ10000002", "����", "������֧", "Lychee", "Litchi chinensis Sonn",
					"�޻��ӿ�", "������ľ", "�ȴ������ȴ�����", "���ڴ����������ļ���", "������ľ����ͨ��10���ף����иߴ�40�ס��ؾ���1�ס��߰���Ĵ���ľ��С֦��׳����΢��ë��ɢ���԰�ɫƤ�ס�Ҷ������15-30���׻������СҶ4-5�ԣ�����3��6�ԣ������ʣ���Բ״��Բ������Բ״�����Σ����ೣ���Գƣ���6-15���ף���2.5-5���ף����˶̼⣬��ʱ�Զ�ͷ�����������Գƣ��ϲ���Ш������ƽ������Ҷ��ƽ�У��²�խШ�⣬��������ɫ���й��󣬱������ɫ��������ë������12-15�ԣ����ڱ���͹��СҶ����ͨ��������5���ס�",
					R.drawable.longyan, "url.image2", null));  
			datas.add(new MoreInfoData("SZ10000003", "������", "������֧", "Lychee", "Litchi chinensis Sonn",
					"�޻��ӿ�", "������ľ", "�ȴ������ȴ�����", "���ڴ����������ļ���", "��ľ����13-20�ף�֦�¸�Լ2�ף��ؾ�Լ1�ס�������״��Ҷ����������������Ҷ�᳤7.5-15���ף�СҶ7-9ö����Բ�λ����Σ����˼��⣬����Ш�Σ�ȫԵ��Ҷ��⻬������ɫ�����浭��ɫ����΢��ë�������ʣ���״�����ԡ�Բ׶��������С֦���ˣ��������´�����50-100���ף���ϡ�裬6-10�䡣������״�����ʣ���4.5-5���ף�ֱ��Լ2���ף�3-5�ѳݲ��ȴ󣬶��˽��⡣���ڽۻ�ɫ��ֺ�ɫ����Ƭ��Բ�Σ��ϴ�2Ƭ��С���´�3Ƭ�ϴ󣬿�չ������Ͳ�����͹�����ߡ�����4��2ǿ����¶����ҩ������������ҩ��2�����ѡ����̻�״����ͷ2�ѣ��ӷ�1�ң�������������´���Բ���Σ���38�������ң�ֱ��12-15���ף���Ӳ����˶�������ѣ�������8���ס����Ӷ������޳ᣬ����ľ�ʵĹ����ڡ�",
					R.drawable.diaodengshu, "url.image3", null));  
			datas.add(new MoreInfoData("SZ10000004", "������", "������֧", "Lychee", "Litchi chinensis Sonn",
					"�޻��ӿ�", "������ľ", "�ȴ������ȴ�����", "���ڴ����������ļ���", "��ľ״���ߴ�10-25�ף�����Լ15���ף�Բ��״�����������Ҷ��״ȫ�ѣ����ھ�������2-3�ף���Ƭ��2�����У���״�����Σ�����45���ף���1.2-2.5���ף��ȶ˽��⣬ȫԵ����ȱ�̣�Ҷ����ɫ��Ҷ���汻�Ұ�ɫ����״��������ԣ�Ҷ���Ҷ�����������ë���Ա�������Ҷ����ɫ�������������γ����ԵĹھ���",
					R.drawable.jiabinglang, "url.image4", null));  
		}  
	public void getMoreInfoData()
	{
		for(int i=0; i < datas.size(); i++)
		{
			if(datas.get(i).getClassifyId() == mClassifyId)
			{
				mMoreInfoData = datas.get(i);
				Log.i("getMoreInfoData", mMoreInfoData.toString());

			}
		}
	}

}