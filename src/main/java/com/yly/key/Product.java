package com.yly.key;

import com.eis.db.DBUtil;
import com.yly.presscard.Presscardapptb;

public abstract class Product {
	protected Presscardapptb vo;
	
	Product(){}
	public Product(Presscardapptb vo){
		this.vo = vo;
	}
	public String next()throws Exception{
		vo.setCardNo(getNext());
		while(isRepeated()){
			vo.setCardNo(getNext());
		}
		return vo.getCardNo();
	}
	/**
     * Create on 2007-9-13 9:52:27 houxh
     * ע�⣺�����һ������ǰ������Ҫ�������ñ�ĵ�ǰֵ��Ȼ����ȡ������ֹ�ظ�����Ĳ���
     * 
     * @return ��Ա���
     */
    public abstract String getNext();
	/**
     * Create on 2007-9-13 9:53:56 houxh
     * ������Ƿ���Ϲ����ظ��Լ���ΧУ��
     * 
     * @return boolean
     * @throws Exception
     */
    public boolean accept()throws Exception{
		if(isRepeated()){
			return false;
		}
		return true;
	}
	
	/**
     * Create on 2007-9-13 9:55:00 houxh
     * �ظ��Լ�飬���ں�����ظ�������У��
     * 
     * @return boolean
     * @throws Exception
     */
    private boolean isRepeated()throws Exception{
    	if(vo.getCardNo() == null || vo.getCardNo().trim().length() == 0){
    		return false;
    	}
		StringBuffer sb = new StringBuffer("select * from PressCardTb where PressCardNo='"+vo.getCardNo()+"'");
		DBUtil db = new DBUtil();
		int count = -1;
		try{
			count = db.sqlQueryCount(sb.toString());
		}finally{
			db.close();
		}
		if(count == 0){
			return false;
		}
		return true;
	}
}
