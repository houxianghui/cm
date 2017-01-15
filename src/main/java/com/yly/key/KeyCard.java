package com.yly.key;

import com.eis.db.DBUtil;
import com.eis.util.StringUtil;
import com.yly.presscard.Presscardapptb;

public class KeyCard extends Product {
	KeyCard(){}
	KeyCard(Presscardapptb vo){
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
		if(card_s!=null)
			card_s=card_s.substring(5, 13);
		else card_s="00000000";
		for(int i=0;i<vo.getPurchaseAmt();i++){
			card_s=StringUtil.addZero(String.valueOf(Integer.parseInt(card_s)+1), 8);
			String checkNum=getCheckNum(card_s);
			cards[i]=vo.getCardType()+vo.getCommRate()+vo.getManufacId()+card_s+checkNum;
		}
		
		return cards;
	}
	private String getCheckNum(String s)throws Exception{
		int singleSum=0;
		int doubleSum=0;
		String checkString="0000"+s+"9999";
		int length=checkString.length();
		for(int i=0;i<length;i+=2){
			singleSum=singleSum+Integer.parseInt(checkString.substring(i, i+1))*(length-i-1);	    	   
      	}
		for(int i=1;i<length;i+=2){
			doubleSum=doubleSum+Integer.parseInt(checkString.substring(i, i+1))*(length-i+1);
		}
		int k=Math.abs(singleSum-doubleSum);
		String k_val=String.valueOf(k);
		String checkNum=k_val.substring(k_val.length()-1);
		return checkNum;
	}
	private String getStartCard(Presscardapptb vo)	throws Exception {
		StringBuffer sb = new StringBuffer("select max(PressCardNo) from PressCardTb where PressCardNo like '"+vo.getCardType()+vo.getCommRate()+vo.getManufacId()+"%' and ClassId='"+vo.getClassId()+"'");
		DBUtil db = new DBUtil();
		String cardno = null;
		try{
			cardno = db.sqlQuerySingle(sb.toString());
		}finally{
			db.close();
		}
		return cardno;
	}
	

}
