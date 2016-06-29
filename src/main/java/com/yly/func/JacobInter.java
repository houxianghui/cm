package com.yly.func;

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
		int i=Dispatch.call(disp,"readSamCardcsn",new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int readEsamCardcsn(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"readEsamCardcsn",new Variant(para.getCardcsn())).getInt();
		disp.safeRelease();
		return i;
    }
	
	public static int wqWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int wqWashEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqWashEsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int tyWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int tyWashEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyWashEsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int gdWashPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"gdWashPsamcard",new Variant(para.getOldTranskey()),new Variant(para.getNewTranskey())).getInt();
		disp.safeRelease();
		return i;
    }
	
	public static int wqIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }
	public static int wqIssueAccessPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueAccessPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	public static int wqIssueEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	public static int wqIssueMotPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueMotPsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	
	public static int wqIssueMotEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueMotEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueQzEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueQzEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueZqEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueZqEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int wqIssueIsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueIsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getAuthSign()),new Variant(para.getZeroExauthFlag()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	public static int tyIssueMotPsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyIssueMotPsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	
	public static int tyIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	
	
	public static int tyIssueEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyIssueEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	public static int tyIssueMotEasyTermPsam(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyIssueMotEasyTermPsam",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getMotEf17()),new Variant(para.getInpki()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }
	
	public static int gdIssuePsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"gdIssuePsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }	
	
	public static int wqIssueEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"wqIssueEsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	public static int tyIssueEsamcard(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"tyIssueEsamcard",new Variant(para.getKeyType()),new Variant(para.getIsPki()),new Variant(para.getW2Sign()),new Variant(para.getW2Limits()),new Variant(para.getSJL05IP()),new Variant(para.getSJL05PORT()),new Variant(para.getFivePara()),new Variant(para.getEf15()),new Variant(para.getEf16()),new Variant(para.getEf17()),new Variant(para.getRetpki())).getInt();
		disp.safeRelease();
		return i;
    }		
	
	
	
	public static int readSamCardno(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"readSamCardno",new Variant(para.getSamId()),new Variant(para.getCardtype())).getInt();
		disp.safeRelease();
		return i;
    }
	
	
	public static int readEsamCardno(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"readEsamCardno",new Variant(para.getSamId()),new Variant(para.getModelflag())).getInt();
		disp.safeRelease();
		return i;
    }
	
	public static int readModelVersion(Para para){
		ActiveXComponent com = new ActiveXComponent("OCXISSUESAM.OCXIssueSAMCtrl.1") ;
		Dispatch disp = (Dispatch)com.getObject();
		int i=Dispatch.call(disp,"readModelVersion",new Variant(para.getVersion()),new Variant(para.getModelflag())).getInt();
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
