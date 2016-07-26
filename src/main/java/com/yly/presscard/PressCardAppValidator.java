package com.yly.presscard;

import com.yly.presscard.Presscardapptb;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;

public class PressCardAppValidator {
	
	public void validate(Presscardapptb p)throws MessageException{
		CheckUtil.rejectEmpty(p.getCardType(), "��Ƭ����");
		CheckUtil.rejectEmpty(p.getManufacId(), "����");
		CheckUtil.rejectEmpty(p.getCommRate(), "ͨ������");
		
		if(p.getPurchaseAmt()<1){
			throw new MessageException("�����������0");
		}

	}
	public void queryValidate(PressCardForm p)throws MessageException{
		if(!CheckUtil.isEmptry(p.getBeginDate_f()) && !CheckUtil.isEmptry(p.getEndDate_f())){
			if(p.getBeginDate_f().compareTo(p.getEndDate_f())>0)
				throw new MessageException("��ʼ���ڲ���С�ڽ�������");
		}

	}
	public void validateEreader(Presscardapptb p)throws MessageException{
		CheckUtil.rejectEmpty(p.getPurchaseType(), "�ɹ�����");
		CheckUtil.rejectEmpty(p.getManufacId(), "����");
		CheckUtil.rejectEmpty(p.getApplyAttr(), "Ӧ������");
		
		if(p.getPurchaseAmt()<1){
			throw new MessageException("�����������0");
		}

	}
	public void queryCardValidate(PressCardForm p)throws MessageException{
		if(CheckUtil.isEmptry(p.getPressCard_min()) || CheckUtil.isEmptry(p.getPressCard_max())){	
			throw new MessageException("��ʼ���źͽ������ű�����д");
		}
		if(p.getPressCard_min().length()!=p.getPressCard_max().length()){
			throw new MessageException("��ʼ���ų��Ⱥͽ������ų��ȱ���һ��");
		}
		if(p.getPressCard_min().length()!=14 && p.getPressCard_min().length()!=16 ){
			throw new MessageException("���ų��ȱ�������14��16λ");
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
			throw new MessageException("����ǰ׺������ͬ");		
		
		if(p.getPressCard_min().length()==14){
			if(p.getPressCard_min().substring(5).compareTo(p.getPressCard_max().substring(5))>0)
				flag=1;
		}
		if(p.getPressCard_min().length()==16){
			if(p.getPressCard_min().substring(8).compareTo(p.getPressCard_max().substring(8))>0)
				flag=1;
		}
		if(flag>0)
			throw new MessageException("��ʼ���Ų��ܴ��ڽ�������");

	}

}
