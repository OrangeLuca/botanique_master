package com.coderlucas.botanique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoreInfoData implements Serializable
{
	private static final long serialVersionUID = -758459502806858414L;  
	
	//ֲ��������
	private String mClassifyId;
	//������
	private String mCHName;
	//����
	private String mAliasName;
	//Ӣ����
	private String mENName;
	//ѧ��
	private String mSciName;
	//����
	private String mGenusName;
	//����
	private String mTypeName;
	//ԭ����
	private String mOriginCountry;
	//ϰ��
	private String mCharacters;
	//��Ҫ�ص�����;
	private String mCharacterAndUses;
	//����ͼ
	private int mImgId;//ģ����
	private String mImgPath;
	//ͼ��
	private List<String> mImgPathList;
	
	
	
	
	
	public MoreInfoData()  
	{  
	}  
	
	public MoreInfoData(String classifyId, String chName, String aliasName, 
			String enName, String sciName, String genusName, String typeName, 
			String originCountry, String characters, String characterAndUses,
			int imgId, String imgPath, List<String> imgPathList)
	{
		super();
		
		this.mClassifyId = classifyId;
		this.mCHName = chName;
		this.mAliasName = aliasName;
		this.mENName = enName;
		this.mSciName = sciName;
		this.mGenusName = genusName;
		this.mTypeName = typeName;
		this.mOriginCountry = originCountry;
		this.mCharacters = characters;
		this.mCharacterAndUses = characterAndUses;
		this.mImgId = imgId;
		this.mImgPath= imgPath;
		this.mImgPathList = imgPathList;
		
	}

	public String getClassifyId()
	{
		return mClassifyId;
	}
	
	public String getCHName()
	{
		return mCHName;
	}
	
	public String getAliasName()
	{
		return mAliasName;
	}
	
	public String getENName()
	{
		return mENName;
	}
	
	public String getSciName()
	{
		return mSciName;
	}
	
	public String getGenusName()
	{
		return mGenusName;
	}
	
	public String getTypeName()
	{
		return mTypeName;
	}
	
	public String getOriginCountry()
	{
		return mOriginCountry;
	}
	
	public String getCharacters()
	{
		return mCharacters;
	}
	
	public String getCharacterAndUses()
	{
		return mCharacterAndUses;
	}
	
	public int getImgId()
	{
		return mImgId;
	}
	
	public String getImgPath()
	{
		return mImgPath;
	}
	
	public List<String> getImgPathList()
	{
		return mImgPathList;
	}
	
	/*
	
	public String getCHNameByClassifyId(String cId)
	{
		return mCHName;
	}
	
	public String getAliasNameByClassifyId(String cId)
	{
		return mAliasName;
	}
	
	public String getENNameByClassifyId(String cId)
	{
		return mENName;
	}
	
	public String getSciNameByClassifyId(String cId)
	{
		return mSciName;
	}
	
	public String getGenusNameByClassifyId(String cId)
	{
		return mGenusName;
	}
	
	public String getTypeNameByClassifyId(String cId)
	{
		return mTypeName;
	}
	
	public String getOriginCountryByClassifyId(String cId)
	{
		return mOriginCountry;
	}
	
	public String getCharactersByClassifyId(String cId)
	{
		return mCharacters;
	}
	
	public String getCharacterAndUsesByClassifyId(String cId)
	{
		return mCharacterAndUses;
	}
	
	public int getImgIdByClassifyId(String cId)
	{
		return mImgId;
	}
	
	public String getImgPathByClassifyId(String cId)
	{
		return mImgPath;
	}
	
	public List<String> getImgPathListByClassifyId(String cId)
	{
		return mImgPathList;
	}*/
}
