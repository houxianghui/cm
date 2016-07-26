package com.yly.presscard;

import com.yly.presscard.Presscardapptb;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;

public class PressCardAppValidator {
	
	public void validate(Presscardapptb p)throws MessageException{
		CheckUtil.rejectEmpty(p.getCardType(), "卡片类型");
		CheckUtil.rejectEmpty(p.getManufacId(), "厂商");
		CheckUtil.rejectEmpty(p.getCommRate(), "通信速率");
		
		if(p.getPurchaseAmt()<1){
			throw new MessageException("数量必须大于0");
		}

	}
	public void queryValidate(PressCardForm p)throws MessageException{
		if(!CheckUtil.isEmptry(p.getBeginDate_f()) && !CheckUtil.isEmptry(p.getEndDate_f())){
			if(p.getBeginDate_f().compareTo(p.getEndDate_f())>0)
				throw new MessageException("开始日期不能小于结束日期");
		}

	}
	public void validateEreader(Presscardapptb p)throws MessageException{
		CheckUtil.rejectEmpty(p.getPurchaseType(), "采购类型");
		CheckUtil.rejectEmpty(p.getManufacId(), "厂商");
		CheckUtil.rejectEmpty(p.getApplyAttr(), "应用属性");
		
		if(p.getPurchaseAmt()<1){
			throw new MessageException("数量必须大于0");
		}

	}
	public void queryCardValidate(PressCardForm p)throws MessageException{
		if(CheckUtil.isEmptry(p.getPressCard_min()) || CheckUtil.isEmptry(p.getPressCard_max())){	
			throw new MessageException("开始卡号和结束卡号必须填写");
		}
		if(p.getPressCard_min().length()!=p.getPressCard_max().length()){
			throw new MessageException("开始卡号长度和结束卡号长度必须一致");
		}
		if(p.getPressCard_min().length()!=14 && p.getPressCard_min().length()!=16 ){
			throw new MessageException("卡号长度必须满足14或16位");
		}
		int flag=-1;
		if(p.getPressCard_min().length()==14){
			if(!p.getPressCard_min().substring(0,5).equals(p.getPressCard_max().substring(0,5)))
				flag=1;
		}
		if(p.getPressCard_min().length()==16){
			if(!p.getPressCard_min().substring(0,8).equals(p.getPressCard_max().substring(0,8)))
				flag=1;
		}
		if(flag>0)
			throw new MessageException("卡号前缀必须相同");		
		
		if(p.getPressCard_min().length()==14){
			if(p.getPressCard_min().substring(5).compareTo(p.getPressCard_max().substring(5))>0)
				flag=1;
		}
		if(p.getPressCard_min().length()==16){
			if(p.getPressCard_min().substring(8).compareTo(p.getPressCard_max().substring(8))>0)
				flag=1;
		}
		if(flag>0)
			throw new MessageException("开始卡号不能大于结束卡号");

	}

}
