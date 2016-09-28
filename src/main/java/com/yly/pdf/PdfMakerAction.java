package com.yly.pdf;

 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.config.SysConfig;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.Format;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
import com.yly.issue.IssueappForm;
import com.yly.issue.Issuetaskctrl;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;
import com.yly.reuse.Storeuse;
import com.yly.reuse.StoreuseBO;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.StoAppInfoForm;
import com.yly.stor.Stoappinfo;




public class PdfMakerAction extends IbatisBaseAction {
	private StoAppInfoBO stoAppBO;
	private IssueappBO issueappBO;
	private LsinfoBO lsinfoBO;
	private StoproductBO stoproductBO;
	private StoreuseBO storeuseBO;

	
	public StoreuseBO getStoreuseBO() {
		return storeuseBO;
	}

	public void setStoreuseBO(StoreuseBO storeuseBO) {
		this.storeuseBO = storeuseBO;
	}

	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

	public LsinfoBO getLsinfoBO() {
		return lsinfoBO;
	}

	public void setLsinfoBO(LsinfoBO lsinfoBO) {
		this.lsinfoBO = lsinfoBO;
	}

	public IssueappBO getIssueappBO() {
		return issueappBO;
	}

	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}

	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}

	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}

	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("print".equals(act)){		//query active projects
			return print(form,mapping,request,user);
		}else return null;
	}
	
	public ActionForward print(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;	
		List<Stoappinfo> stoList = new ArrayList<Stoappinfo>();

		if(f.getOperationType()<20 ||f.getOperationType()==92 ||f.getOperationType()==94){
			Stoappinfo info = new Stoappinfo();
			info = stoAppBO.queryForObject(f.getFormNo());
			long totAmt=info.getPurchaseAmt()*info.getUnitPrice();
			info.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
			f.setCurrDate(info.getCurrDate());
			stoList.add(info);
		}else{
			Issueapp appvo=issueappBO.queryForObject(f.getFormNo());
			short opertype=appvo.getOperationType();
			f.setFormNo(appvo.getOAappNo());
			f.setCurrDate(appvo.getCurrDate());
			Lsinfo lsvo=new Lsinfo();
			lsvo.setAppNo(appvo.getAppNo());
			List<Lsinfo> lslist = lsinfoBO.queryReportLsForList(lsvo);
			
			HashMap prodMap = new HashMap();
			HashMap returnMap = new HashMap();
			
			if(lslist!=null && lslist.size()>0){
				if(opertype==31 ||opertype==53){   //成品出库，丢失补办:成品库
					for(Lsinfo lslistvo:lslist){
						Stoproduct stoprod = new Stoproduct();
						stoprod.setSamCSN(lslistvo.getSamCSN());
						stoprod.setSamId(lslistvo.getSamId());
						stoprod=stoproductBO.queryForObject(stoprod);
						String prodKey=stoprod.getProdId()+stoprod.getUnitId()+stoprod.getConsumeType()+String.valueOf(stoprod.getUnitPrice());
						if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
							int cnt=(int)(prodMap.get(prodKey));
							cnt++;
							prodMap.put(prodKey, cnt);
						}else{
							prodMap.put(prodKey, 1);
						} 
						
					}
					Set<String> key = prodMap.keySet();  
					for (String string : key) {  
						Stoappinfo newinfo = new Stoappinfo();
						newinfo.setProdId(string.substring(0,1));
						newinfo.setManufacId(string.substring(1,9));
						newinfo.setOperationType(Short.valueOf(string.substring(9,10)));
						newinfo.setUnitPrice(Long.valueOf(string.substring(10)));
						newinfo.setPurchaseAmt(Long.valueOf(prodMap.get(string).toString()));
						long totAmt=newinfo.getPurchaseAmt()*newinfo.getUnitPrice();
						newinfo.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
						stoList.add(newinfo);
					}  
					  
				}else if(opertype==33 ){   //坏卡出库，丢失补办:成品库
					for(Lsinfo lslistvo:lslist){
						Stoproduct stoprod = new Stoproduct();
						stoprod.setSamCSN(lslistvo.getSamCSN());
						stoprod.setSamId(lslistvo.getSamId());
						stoprod=stoproductBO.queryForObject(stoprod);
						String prodKey=stoprod.getProdId()+stoprod.getManufacId();
						if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
							int cnt=(int)(prodMap.get(prodKey));
							cnt++;
							prodMap.put(prodKey, cnt);
						}else{
							prodMap.put(prodKey, 1);
						}						
					}
					Set<String> key = prodMap.keySet();  
					for (String string : key) {  
						Stoappinfo info = new Stoappinfo();
						info.setProdId(string.substring(0,1));
						info.setManufacId(string.substring(1));
						info.setOperationType(opertype);
						info.setUnitPrice((long)0);
						info.setPurchaseAmt(Long.valueOf(prodMap.get(string).toString()));
						info.setRsvd(Format.formatCapR("0"));
						stoList.add(info);
					}  
				}else if(opertype==32 ||opertype==34 ||opertype==51 ||opertype==52){ //原料出库，pos出库，模块补办，pos原料补办：出库（单据、成品库），冲回（单据）.
					Stoappinfo info = new Stoappinfo();
					for(Lsinfo lslistvo:lslist){
						if(lslistvo.getOperationType()==92){
							Stoappinfo backinfo = new Stoappinfo();
							backinfo=stoAppBO.queryForObject(lslistvo.getFormNo());
							backinfo.setProdId(lslistvo.getProdId());
						
							backinfo.setUnitPrice((long)0);
							backinfo.setManufacId(String.valueOf(appvo.getUnitId()));
							backinfo.setOperationType(lslistvo.getOperationType());
							backinfo.setRsvd(Format.formatCapR("0"));
							stoList.add(backinfo);
						}else{
							if(CheckUtil.isEmptry(lslistvo.getSamCSN()) ||lslistvo.getSamCSN().equals("0")){
								info.setProdId(lslistvo.getProdId());
								info.setPurchaseAmt(appvo.getTaskAmt());
								info.setUnitPrice(appvo.getTotalPrice()/appvo.getTaskAmt());
								info.setManufacId(String.valueOf(appvo.getUnitId()));
								info.setOperationType(opertype);
								info.setRsvd(Format.formatCapR(String.valueOf(appvo.getTotalPrice())));
								
							}else{
								Stoproduct stoprod = new Stoproduct();
								stoprod.setSamCSN(lslistvo.getSamCSN());
								stoprod.setSamId(lslistvo.getSamId());
								stoprod=stoproductBO.queryForObject(stoprod);
								String prodKey=stoprod.getProdId()+stoprod.getUnitId()+opertype+String.valueOf(stoprod.getUnitPrice());
								if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
									int cnt=(int)(prodMap.get(prodKey));
									cnt++;
									prodMap.put(prodKey, cnt);
								}else{
									prodMap.put(prodKey, 1);
								} 
							}

						}
						
					}
					if(prodMap!=null &&prodMap.size()>0){
						Set<String> key = prodMap.keySet();  
						for (String string : key) {  
							Stoappinfo newinfo = new Stoappinfo();
							newinfo.setProdId(string.substring(0,1));
							newinfo.setManufacId(string.substring(1,9));
							newinfo.setOperationType(Short.valueOf(string.substring(9,11)));
							newinfo.setUnitPrice(Long.valueOf(string.substring(11)));
							newinfo.setPurchaseAmt(Long.valueOf(prodMap.get(string).toString()));
							long totAmt=newinfo.getPurchaseAmt()*newinfo.getUnitPrice();
							newinfo.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
							stoList.add(newinfo);
						}  
					}else{
						stoList.add(info);
					}
				}else if(opertype==41 ||opertype==42 ||opertype==43){   //换损：退回（成品库），更换（单据，成品库）
					Stoappinfo info = new Stoappinfo();
					for(Lsinfo lslistvo:lslist){
						if(lslistvo.getOperationType()==61){
							Stoproduct stoprod = new Stoproduct();
							stoprod.setSamCSN(lslistvo.getSamCSN());
							stoprod.setSamId(lslistvo.getSamId());
							stoprod=stoproductBO.queryForObject(stoprod);
							String prodKey=stoprod.getProdId()+stoprod.getUnitId()+"610";
							if(returnMap.size()!=0 && returnMap.get(prodKey)!=null){
								int cnt=(int)(returnMap.get(prodKey));
								cnt++;
								returnMap.put(prodKey, cnt);
							}else{
								returnMap.put(prodKey, 1);
							}
						}else{
							if(!CheckUtil.isEmptry(lslistvo.getSamId()) && !lslistvo.getSamId().equals("0")){
								Stoproduct stoprod = new Stoproduct();
								stoprod.setSamCSN(lslistvo.getSamCSN());
								stoprod.setSamId(lslistvo.getSamId());
								stoprod=stoproductBO.queryForObject(stoprod);
								String prodKey=stoprod.getProdId()+stoprod.getUnitId()+opertype+String.valueOf(stoprod.getUnitPrice());
								if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
									int cnt=(int)(prodMap.get(prodKey));
									cnt++;
									prodMap.put(prodKey, cnt);
								}else{
									prodMap.put(prodKey, 1);
								}
							}else{
								info.setProdId(lslistvo.getProdId());
								info.setPurchaseAmt(appvo.getTaskAmt());
								info.setUnitPrice(appvo.getTotalPrice()/appvo.getTaskAmt());
								info.setManufacId(String.valueOf(appvo.getUnitId()));
								info.setRsvd(Format.formatCapR(String.valueOf(appvo.getTotalPrice())));
								info.setOperationType(opertype);
								
							}

						}
						
					}
					if(prodMap!=null &&prodMap.size()>0){
						Set<String> key = prodMap.keySet();  
						for (String string : key) {  
							Stoappinfo newinfo = new Stoappinfo();
							newinfo.setProdId(string.substring(0,1));
							newinfo.setManufacId(string.substring(1,9));
							newinfo.setOperationType(Short.valueOf(string.substring(9,11)));
							newinfo.setUnitPrice(Long.valueOf(string.substring(11)));
							newinfo.setPurchaseAmt(Long.valueOf(prodMap.get(string).toString()));
							long totAmt=newinfo.getPurchaseAmt()*newinfo.getUnitPrice();
							newinfo.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
							stoList.add(newinfo);
						}
					}else{
						stoList.add(info);
					}
					if(returnMap!=null){
						Set<String> key = returnMap.keySet();  
						for (String string : key) {  
							Stoappinfo newinfo = new Stoappinfo();
							newinfo.setProdId(string.substring(0,1));
							newinfo.setManufacId(string.substring(1,9));
							newinfo.setOperationType(Short.valueOf(string.substring(9,11)));
							newinfo.setUnitPrice(Long.valueOf(string.substring(11)));
							newinfo.setPurchaseAmt(Long.valueOf(returnMap.get(string).toString()));
							long totAmt=newinfo.getPurchaseAmt()*newinfo.getUnitPrice();
							newinfo.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
							stoList.add(newinfo);
						}  
					}
				}else if(opertype==61){   //退回：成品库。
					for(Lsinfo lslistvo:lslist){
						Stoproduct stoprod = new Stoproduct();
						stoprod.setSamCSN(lslistvo.getSamCSN());
						stoprod.setSamId(lslistvo.getSamId());
						stoprod=stoproductBO.queryForObject(stoprod);
						if(stoprod==null ||CheckUtil.isEmptry(stoprod.getSamId())){
							Storeuse reuse = new Storeuse();
							reuse.setSamCSN(lslistvo.getSamCSN());
							reuse.setSamId(lslistvo.getSamId());
							reuse = (Storeuse)storeuseBO.queryForObject(reuse);
							copyProperties(stoprod, reuse);
						}
						String prodKey=stoprod.getProdId()+stoprod.getUnitId()+opertype+"0";
						if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
							int cnt=(int)(prodMap.get(prodKey));
							cnt++;
							prodMap.put(prodKey, cnt);
						}else{
							prodMap.put(prodKey, 1);
						}
					}	
					Set<String> key = prodMap.keySet();  
					for (String string : key) {  
						Stoappinfo info = new Stoappinfo();
						info.setProdId(string.substring(0,1));
						info.setManufacId(string.substring(1,9));
						info.setOperationType(Short.valueOf(string.substring(9,11)));
						info.setUnitPrice(Long.valueOf(string.substring(11)));
						info.setPurchaseAmt(Long.valueOf(prodMap.get(string).toString()));
						long totAmt=info.getPurchaseAmt()*info.getUnitPrice();
						info.setRsvd(Format.formatCapR(String.valueOf(totAmt)));
						stoList.add(info);
					}  
					
				}else throw new MessageException("业务类型出错");
			}else throw new MessageException("无相应流水记录");
		}
		pdfMaker.printPdf(stoList,f);
		String url=SysConfig.getProperty("pdf.download")+"/"+f.getFormNo()+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,String.valueOf(f.getOperationType()))+".pdf";
		return forwardSuccessPage(request,mapping,"生成成功,请打开文件进行打印"+url,"");

	}

	public void getProdMap(Stoappinfo info,HashMap prodMap,
			Lsinfo lslistvo) throws Exception {
		Stoproduct stoprod = new Stoproduct();
		stoprod.setSamCSN(lslistvo.getSamCSN());
		stoprod.setSamId(lslistvo.getSamId());
		stoprod=stoproductBO.queryForObject(stoprod);
		String prodKey=stoprod.getProdId()+stoprod.getUnitId()+stoprod.getConsumeType()+String.valueOf(stoprod.getUnitPrice());
		info.setProdId(stoprod.getProdId());
		info.setManufacId(String.valueOf(stoprod.getUnitId()));
		info.setOperationType(stoprod.getConsumeType());
		info.setUnitPrice(stoprod.getUnitPrice());
		if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
			int cnt=(int)(prodMap.get(prodKey));
			cnt++;
			prodMap.put(prodKey, cnt);
			info.setPurchaseAmt((long)cnt);
		}else{
			prodMap.put(prodKey, 1);
			info.setPurchaseAmt((long)1);
		} 
		
	}
	public void getExchangeProdMap(HashMap prodMap,
			Lsinfo lslistvo,String opertype) throws Exception {
		Stoproduct stoprod = new Stoproduct();
		stoprod.setSamCSN(lslistvo.getSamCSN());
		stoprod.setSamId(lslistvo.getSamId());
		stoprod=stoproductBO.queryForObject(stoprod);
		String prodKey=stoprod.getProdId()+stoprod.getUnitId()+opertype+String.valueOf(stoprod.getUnitPrice());
		if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
			int cnt=(int)(prodMap.get(prodKey));
			cnt++;
			prodMap.put(prodKey, cnt);
		}else{
			prodMap.put(prodKey, 1);
		}
	}
	public void getReturnProdMap(HashMap prodMap,
		Lsinfo lslistvo,String opertype) throws Exception {
		Stoproduct stoprod = new Stoproduct();
		stoprod.setSamCSN(lslistvo.getSamCSN());
		stoprod.setSamId(lslistvo.getSamId());
		stoprod=stoproductBO.queryForObject(stoprod);
		if(stoprod==null ||CheckUtil.isEmptry(stoprod.getSamId())){
			Storeuse reuse = new Storeuse();
			reuse.setSamCSN(lslistvo.getSamCSN());
			reuse.setSamId(lslistvo.getSamId());
			reuse = (Storeuse)storeuseBO.queryForObject(reuse);
			copyProperties(stoprod, reuse);
		}
		String prodKey=stoprod.getProdId()+stoprod.getUnitId()+opertype+String.valueOf(stoprod.getUnitPrice());
		if(prodMap.size()!=0 && prodMap.get(prodKey)!=null){
			int cnt=(int)(prodMap.get(prodKey));
			cnt++;
			prodMap.put(prodKey, cnt);
		}else{
			prodMap.put(prodKey, 1);
		}
	}
}
