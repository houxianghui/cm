package com.yly.key;

import com.eis.util.CheckUtil;
import com.yly.presscard.Presscardapptb;

public class ProCardFactory  {
	
	public static Product getInstance(Presscardapptb vo){
		if(CheckUtil.isEmptry(vo.getCardType())){
			return new EreaderCard(vo);
		}else{
			return new KeyCard(vo);
		}
		
	}
}
