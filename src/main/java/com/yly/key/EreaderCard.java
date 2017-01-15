package com.yly.key;

import com.eis.db.DBUtil;
import com.eis.util.CheckUtil;
import com.eis.util.StringUtil;
import com.yly.presscard.Presscardapptb;

public class EreaderCard extends Product {
	EreaderCard(){}
	EreaderCard(Presscardapptb vo){
		super(vo);
	}
	@Override
	public String getNext() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] getCardNo(Presscardapptb vo) throws Exception {
		String[] cards= new String[vo.getPurchaseAmt()];
		String card_s=getStartCard(vo);	
		if(CheckUtil.isEmptry(card_s))
			card_s = "00000000000";
		String batch_s =StringUtil.addZero(String.valueOf(Integer.parseInt(card_s.substring(0, 4))+1), 4);	
		card_s = card_s.substring(4);
		for(int i=0;i<vo.getPurchaseAmt();i++){
			card_s=StringUtil.addZero(String.valueOf(Integer.parseInt(card_s)+1), 7);
			String card_str="0"+vo.getManufacId()+vo.getApplyAttr()+vo.getHardVersion()+batch_s+card_s;
			String checkNum=getCheckNum(card_str);
			cards[i]=card_str.substring(1)+checkNum;
		}
		
		return cards;
	}
	private String getCheckNum(String s)throws Exception{
       int singleSum=0;
       int doubleSum=0;
       int length=s.length();
       for(int i=0;i<length;i+=2){
    	   singleSum=singleSum+Integer.parseInt(s.substring(i, i+1))*(length-i-1);   	   
       }
       for(int i=1;i<length;i+=2){
    	   doubleSum=doubleSum+Integer.parseInt(s.substring(i, i+1))*(length-i+1);
       }
       int k=Math.abs(singleSum-doubleSum);
       String k_val=String.valueOf(k);
       String checkNum=k_val.substring(k_val.length()-1);
       return checkNum;
	}
	private String getStartCard(Presscardapptb vo)	throws Exception {
		StringBuffer sb = new StringBuffer("select max(substring(PressCardNo,9,7)) from PressCardTb where PressCardNo like '"+vo.getManufacId()+vo.getApplyAttr()+"%' and  ClassId='E'");
		StringBuffer sb_batch = new StringBuffer("select max(substring(PressCardNo,5,4)) from PressCardTb where PressCardNo like '"+vo.getManufacId()+"%'  and ClassId='E'");

		DBUtil db = new DBUtil();
		String cardno = null;
		String batchno = null;
		try{
			batchno  = db.sqlQuerySingle(sb_batch.toString());
			if(!CheckUtil.isEmptry(batchno))	{
				cardno = db.sqlQuerySingle(sb.toString());
				if(CheckUtil.isEmptry(cardno))
					cardno="0000000";
			}
			else return null;
		}finally{
			db.close();
		}

		return batchno+cardno;
	}

}
