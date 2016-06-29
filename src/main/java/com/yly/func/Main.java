package com.yly.func;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Main {
	public static void main(String[] args) {
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"OpenSystemPort",new Variant("com3"),new Variant("0")).getInt();
		if(i==0){
			int j=Dispatch.call(disp,"WqWashPsamcard",new Variant("WATCHDATATimeCOS"),new Variant("WATCHDATATimeCOS")).getInt();
			System.out.println(j);
			disp.safeRelease();	   

		}else{
			
		}
	}
}
