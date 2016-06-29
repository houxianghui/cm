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
     * 注意：获得下一个号码前，首先要更新配置表的当前值，然后再取出，防止重复号码的产生
     * 
     * @return 会员编号
     */
    public abstract String getNext();
	/**
     * Create on 2007-9-13 9:53:56 houxh
     * 检查编号是否符合规则，重复以及范围校验
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
     * 重复性检查，对于号码的重复与否进行校验
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
