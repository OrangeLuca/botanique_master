package com.coderlucas.botanique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.coderlucas.botanique.R;
import com.coderlucas.botanique.main.MainActivity;
import com.coderlucas.botanique.tabmenu.TabMapFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClassifyInfoActivity extends Activity
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
		setContentView(R.layout.classify_info_activity);
		
		//Log.i("Classify Class" , "flag 1");
		//获取植物分类编号
		mClassifyId = getIntent().getExtras().getString("ClassifyInfokey"); 
		
		
		//Log.i("Classify Class" , "flag 2");
		
		//用植物分类号查询植物详细信息，得到mMoreInfoData
		//mMoreInfoData = new MoreInfoData();
		mMoreInfoData = new MoreInfoData("SZ10000001", "荔枝", "大荔、离支", "Lychee", "Litchi chinensis Sonn",
				"无患子科", "常绿乔木", "热带、亚热带地区", "花期春季，果期夏季。", "荔枝属亚热带果树，性喜温暖。 常绿乔木，高通常不超过10米，有时可达15米或更高，树皮灰黑色；小枝圆柱状，褐红色，密生白色皮孔。叶连柄长10-25厘米或过之；小叶2或3对，较少4对，薄革质或革质，披针形或卵状披针形，有时长椭圆状披针形，长6-15厘米，宽2-4厘米，顶端骤尖或尾状短渐尖，全缘，腹面深绿色，有光泽，背面粉绿色，两面无毛；侧脉常纤细，在腹面不很明显，在背面明显或稍凸起；小叶柄长7-8毫米。",
				R.drawable.lizhi, "url.image1", null);
		//Log.i("Classify Class" , "flag 3");
		//getMoreInfoData();
		//Log.i("Classify Class" , "flag 4");
		
		//标题栏
		mHeadbar = (HeadBar)findViewById(R.id.classifyinfo_headbar);
		mHeadbar.setTextTitleResource(getString(R.string.text_classify_title));
		mHeadbar.setImageResource(R.drawable.headbar_back);
		//Log.i("Classify Class" , "flag 5");
		mHeadbar.setHeadBarOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "返回主界面", Toast.LENGTH_SHORT).show();
				//返回主界面
				ClassifyInfoActivity.this.setResult(RESULT_CANCELED, new Intent());
				ClassifyInfoActivity.this.finish();
			}
		});
		
		//内容栏
		//Log.i("Classify Class" , "flag 6");
		mTextTitle = (TextView)findViewById(R.id.classifyinfo_title);
		mTextTitle.setText(mMoreInfoData.getCHName());
		
		mTextBasic = (TextView)findViewById(R.id.classifyinfo_basic);
		String _BasicInfo = 
				  "别    名：" + mMoreInfoData.getAliasName() 
				+ "\n英文名：" + mMoreInfoData.getENName() 
				+ "\n学    名："  + mMoreInfoData.getSciName() 
				+ "\n科    名：" + mMoreInfoData.getGenusName() 
				+ "\n类    型：" + mMoreInfoData.getTypeName()
				+ "\n原产地：" + mMoreInfoData.getOriginCountry()
				+ "\n习    性：" + mMoreInfoData.getCharacters();
		//Log.i("Classify Class" , "flag 7");
		mTextBasic.setText(_BasicInfo);
		//Log.i("Classify Class" , "flag 8");
		mTextMoreTitle = (TextView)findViewById(R.id.classifyinfo_moreinfo);
		
		mTextMoreShow = (TextView)findViewById(R.id.classifyinfo_moreinfo_show);
		mTextMoreShow.setText(mMoreInfoData.getCharacterAndUses());
		
		mTextFindBtn = (TextView)findViewById(R.id.classifyinfo_go);
		//Log.i("Classify Class" , "flag 9");
		mTextFindBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(ClassifyInfoActivity.this, "返回地图，显示位置", Toast.LENGTH_SHORT).show();
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 1");
				
				//从服务器查找该类植物地理坐标
				ArrayList<MyLatLng> _MyLatLngs = new ArrayList<MyLatLng>();
				_MyLatLngs.add(new MyLatLng(22.5307, 113.9312));
				_MyLatLngs.add(new MyLatLng(22.5308, 113.9313));
				_MyLatLngs.add(new MyLatLng(22.5307, 113.9314));
				_MyLatLngs.add(new MyLatLng(22.5303, 113.9310));
				_MyLatLngs.add(new MyLatLng(22.5303, 113.9318));
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 2");
				
				//返回地图，传送位置坐标
				
				//启动ClassifyInfoActivity
				//ArrayList _List = new ArrayList(); 
				//ArrayList<Double> _List = new ArrayList<Double>();
				//ArrayList<Double> _List2 = new ArrayList<Double>();
				//for(int i=0; i < _MyLatLngs.size() ; i++)
				//{
					//_List.add(_MyLatLngs.get(i).toString());
				//	_List.add(_MyLatLngs.get(i).getLantitude());
				//	Log.i("ClassifyInfoActivity", "_List("+i+")="+_List.get(i).toString());
				//	_List2.add(_MyLatLngs.get(i).getLongtitude());
				//	Log.i("ClassifyInfoActivity", "_List("+i+")="+_List2.get(i).toString());
				//}
				
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 3");
				Intent _Intent = new Intent();
				/*_Intent.setClass(ClassifyInfoActivity.this.getApplicationContext(),
						MainActivity.class);*/
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 4");
				//Bundle _Bundle = new Bundle();
				//_Bundle.putParcelableArrayList("ClassifyToMap", _List);
				//_Bundle.putString("ClassifyToMap", "hello");
				//_Bundle.putDoubleArray("ClassifyToMap", _List);
				//_Bundle.putDoubleArray("ClassifyToMap", _List.toString());
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 5");
				_Intent.putParcelableArrayListExtra("ClassifyToMap", (ArrayList<? extends Parcelable>) _MyLatLngs);
				//_Intent.putExtras(_Bundle);
				//startActivity(_Intent);
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 6");
				// getActivity().startActivity(_Intent);
				//startActivityForResult(_Intent, CLASSIFY_TO_MAP);
				ClassifyInfoActivity.this.setResult(RESULT_OK, _Intent);
				Log.i("ClassifyInfoActivity", _Intent.toString());
				Log.i("ClassifyInfoActivity", "mTextFindBtn.setOnClickListener flag 7");
				//结束Activity
				ClassifyInfoActivity.this.finish();
				
			}
		});

		
		mTextAlbumImage = (ImageView)findViewById(R.id.classifyinfo_album_image);
		mTextAlbumImage.setImageDrawable(getResources().getDrawable(mMoreInfoData.getImgId()));
		mTextAlbumImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(ClassifyInfoActivity.this, "打开图册", Toast.LENGTH_SHORT).show();
				//打开新页面进入图册Activity
				Intent _Intent = new Intent();
				_Intent.setClass(ClassifyInfoActivity.this.getApplicationContext(),
						PhotoWallActivity.class);
				//Bundle _Bundle = new Bundle();
				//_Bundle.putString("ClassifyInfoKey", _mClassifyId);
				
				//_Intent.putExtras(_Bundle);
				startActivity(_Intent);
			}
		});
		//Log.i("Classify Class" , "flag 10");
		mTextAlbum = (TextView)findViewById(R.id.classifyinfo_album);
		/*mTextAlbum.setCompoundDrawables(null, 
				getResources().getDrawable(mMoreInfoData.getImgId()), null, null);*/
		/*mTextAlbum.setCompoundDrawablesWithIntrinsicBounds(null, 
				getResources().getDrawable(mMoreInfoData.getImgId()), null, null);
		mTextAlbum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(ClassifyInfoActivity.this, "打开图册", Toast.LENGTH_SHORT).show();
				//打开新页面进入图册Activity
				
				
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
	
	
	//模拟数据
		public static List<MoreInfoData> datas = new ArrayList<MoreInfoData>();  

		static  
		{ 
			datas.add(new MoreInfoData("SZ10000001", "荔枝", "大荔、离支", "Lychee", "Litchi chinensis Sonn",
					"无患子科", "常绿乔木", "热带、亚热带地区", "花期春季，果期夏季。", "荔枝属亚热带果树，性喜温暖。 常绿乔木，高通常不超过10米，有时可达15米或更高，树皮灰黑色；小枝圆柱状，褐红色，密生白色皮孔。叶连柄长10-25厘米或过之；小叶2或3对，较少4对，薄革质或革质，披针形或卵状披针形，有时长椭圆状披针形，长6-15厘米，宽2-4厘米，顶端骤尖或尾状短渐尖，全缘，腹面深绿色，有光泽，背面粉绿色，两面无毛；侧脉常纤细，在腹面不很明显，在背面明显或稍凸起；小叶柄长7-8毫米。",
					R.drawable.lizhi, "url.image1", null));  
			datas.add(new MoreInfoData("SZ10000002", "龙眼", "大荔、离支", "Lychee", "Litchi chinensis Sonn",
					"无患子科", "常绿乔木", "热带、亚热带地区", "花期春季，果期夏季。", "常绿乔木，高通常10余米，间有高达40米、胸径达1米、具板根的大乔木；小枝粗壮，被微柔毛，散生苍白色皮孔。叶连柄长15-30厘米或更长；小叶4-5对，很少3或6对，薄革质，长圆状椭圆形至长圆状披针形，两侧常不对称，长6-15厘米，宽2.5-5厘米，顶端短尖，有时稍钝头，基部极不对称，上侧阔楔形至截平，几与叶轴平行，下侧窄楔尖，腹面深绿色，有光泽，背面粉绿色，两面无毛；侧脉12-15对，仅在背面凸起；小叶柄长通常不超过5毫米。",
					R.drawable.longyan, "url.image2", null));  
			datas.add(new MoreInfoData("SZ10000003", "吊灯树", "大荔、离支", "Lychee", "Litchi chinensis Sonn",
					"无患子科", "常绿乔木", "热带、亚热带地区", "花期春季，果期夏季。", "乔木，高13-20米，枝下高约2米，胸径约1米。奇数羽状复叶交互对生或轮生，叶轴长7.5-15厘米；小叶7-9枚，长圆形或倒卵形，顶端急尖，基部楔形，全缘，叶面光滑，亮绿色，背面淡绿色，被微柔毛，近革质，羽状脉明显。圆锥花序生于小枝顶端，花序轴下垂，长50-100厘米；花稀疏，6-10朵。花萼钟状，革质，长4.5-5厘米，直径约2厘米，3-5裂齿不等大，顶端渐尖。花冠桔黄色或褐红色，裂片卵圆形，上唇2片较小，下唇3片较大，开展，花冠筒外面具凸起纵肋。雄蕊4，2强，外露，花药个字形着生，药室2，纵裂。花盘环状。柱头2裂，子房1室，胚珠多数。果下垂，圆柱形，长38厘米左右，直径12-15厘米，坚硬，肥硕，不开裂，果柄长8厘米。种子多数，无翅，镶于木质的果肉内。",
					R.drawable.diaodengshu, "url.image3", null));  
			datas.add(new MoreInfoData("SZ10000004", "假槟榔", "大荔、离支", "Lychee", "Litchi chinensis Sonn",
					"无患子科", "常绿乔木", "热带、亚热带地区", "花期春季，果期夏季。", "乔木状，高达10-25米，茎粗约15厘米，圆柱状，基部略膨大。叶羽状全裂，生于茎顶，长2-3米，羽片呈2列排列，线状披针形，长达45厘米，宽1.2-2.5厘米，先端渐尖，全缘或有缺刻，叶面绿色，叶背面被灰白色鳞秕状物，中脉明显；叶轴和叶柄厚而宽，无毛或稍被鳞秕；叶鞘绿色，膨大而包茎，形成明显的冠茎。",
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
