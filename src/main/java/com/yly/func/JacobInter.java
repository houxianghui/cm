package com.yly.func;

import java.io.UnsupportedEncodingException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobInter {
	public static int openSystemPort(Para para) {
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"OpenSystemPort",new Variant("com3"),new Variant(para.getCardtype())).getInt();
		return i;
	}
	
	public static int closeSystemPort(Para para) {
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"CloseSystemPort",new Variant(para.getCardtype())).getInt();
		return i;
	}	
	
 	public static int readSamCardcsn(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[16];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;
			i = Dispatch.call(disp,"ReadSamCardcsn",v1).getInt();
			String ret = new String(v1.toString());
			para.setCardcsn(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
	

    }
	public static int readEsamCardcsn(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[16];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;
			i=Dispatch.call(disp,"ReadEsamCardcsn",v1).getInt();
			String ret = new String(v1.toString());
			para.setCardcsn(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }
	
	public static int wqWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"WqWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int wqWashEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"WqWashEsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int tyWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"TyWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int tyWashEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"TyWashEsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int gdWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"GdWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	
	public static int wqIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;
			i=Dispatch.call(disp,"WqIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }
	public static int wqIssueAccessPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;
		    i=Dispatch.call(disp,"WqIssueAccessPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
		    String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	public static int wqIssueEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
		    i=Dispatch.call(disp,"WqIssueEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),v1).getInt();
		    String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	public static int wqIssueMotPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;			
		    i=Dispatch.call(disp,"WqIssueMotPsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),v1).getInt();
		    String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	
	public static int wqIssueMotEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;			
			i=Dispatch.call(disp,"WqIssueMotEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getInpki()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueQzEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=-1;
		try {
			byte[] b = new byte[256];
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;					
			i=Dispatch.call(disp,"WqIssueQzEasyTermPsam",v1,new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki())).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueZqEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;			
			i=Dispatch.call(disp,"WqIssueZqEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueIsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;			
			i=Dispatch.call(disp,"WqIssueIsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getAuthSign()),new Variant(para.getZeroExauthFlag()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	public static int tyIssueMotPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;			
			i=Dispatch.call(disp,"TyIssueMotPsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	
	public static int tyIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;					
			i=Dispatch.call(disp,"TyIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	
	
	public static int tyIssueEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;					
			i=Dispatch.call(disp,"TyIssueEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	public static int tyIssueMotEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
			i=Dispatch.call(disp,"TyIssueMotEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getInpki()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }
	
	public static int gdIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
			i=Dispatch.call(disp,"GdIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	
	
	public static int wqIssueEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
			i=Dispatch.call(disp,"WqIssueEsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
			String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	
	public static int tyIssueEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[256];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
		    i=Dispatch.call(disp,"TyIssueEsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05PORT()),new Variant(para.getSJL05IP()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),v1).getInt();
		    String ret = new String(v1.toString());
			para.setRetpki(ret);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }		
	
	
	
	public static int readSamCardno(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[12];
		byte[] c = new byte[1];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;		
			Variant v2 = new Variant (new String(c, "UNICODE"), true) ;		
			i=Dispatch.call(disp,"ReadSamCardno",v1,v2).getInt();
			String samId = new String(v1.toString());
			para.setSamId(samId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }
	
	
	public static int readEsamCardno(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[12];
		byte[] c = new byte[1];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;	
			Variant v2 = new Variant (new String(c, "UNICODE"), true) ;		
		    i=Dispatch.call(disp,"ReadEsamCardno",v1,v2).getInt();
		    String samId = new String(v1.toString());
		    String modelflag = new String(v2.toString());
			para.setSamId(samId);
			para.setModelflag(Integer.parseInt(modelflag));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }
	
	public static int readModelVersion(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		byte[] b = new byte[50];
		int i=-1;
		try {
			Variant v1 = new Variant (new String(b, "UNICODE"), true) ;				
		    i=Dispatch.call(disp,"ReadModelVersion",v1,new Variant(para.getModelflag())).getInt();
		    String version = new String(v1.toString());
			para.setVersion(version);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		disp.safeRelease();
		return i;
    }	

	public static int wqRepairPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqRepairPsamcard",new Variant(para.getAuthkey()),new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }	
	public static int wqRepairEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqRepairEsamcard",new Variant(para.getAuthkey()),new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int gdRepairPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"gdRepairPsamcard",new Variant(para.getAuthkey()),new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int tyRepairPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyRepairPsamcard",new Variant(para.getAuthkey()),new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }		
	public static int tyRepairEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyRepairEsamcard",new Variant(para.getAuthkey()),new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }			
	
}
