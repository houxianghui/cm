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
		card_s = card_s.substring(5);
		for(int i=0;i<vo.getPurchaseAmt();i++){
			card_s=StringUtil.addZero(String.valueOf(Integer.parseInt(card_s)+1), 7);
			String checkNum=getCheckNum(card_s);
			cards[i]=vo.getManufacId()+vo.getApplyAttr()+vo.getHardVersion()+batch_s+card_s+checkNum;
		}
		
		return cards;
	}
	private String getCheckNum(String s)throws Exception{
		int cc=9999+Integer.parseInt(s);
		String checkNum =String.valueOf(cc).substring(0,1);
		
//		String aa =(s.substring(0,1)+'0'+s.substring(2)).toString();
//		long bb=Long.parseLong(aa);
//		String checkNum =Long.toString(bb%7);
		return checkNum;
	}
	private String getStartCard(Presscardapptb vo)	throws Exception {
		StringBuffer sb = new StringBuffer("select max(substring(PressCardNo,9,7)) from PressCardTb where PressCardNo like '"+vo.getManufacId()+"%' and ClassId='E'");
		StringBuffer sb_batch = new StringBuffer("select max(substring(PressCardNo,5,4)) from PressCardTb where PressCardNo like '"+vo.getManufacId()+"%'  and ClassId='E'");

		DBUtil db = new DBUtil();
		String cardno = null;
		String batchno = null;
		try{
			batchno  = db.sqlQuerySingle(sb_batch.toString());
			if(!CheckUtil.isEmptry(batchno))	
				cardno = db.sqlQuerySingle(sb.toString());
			else return null;
		}finally{
			db.close();
		}

		return batchno+cardno;
	}

}
