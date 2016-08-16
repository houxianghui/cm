/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.presscard;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.nested.bean.NestedDefineTei;
import org.springframework.beans.factory.annotation.Autowired;

import com.yly.key.EreaderCard;
import com.yly.key.KeyCard;
import com.yly.key.ProCardFactory;
import com.yly.presscard.PressCardForm;
import com.blue.projectfiles.ProjectFiles;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;
import com.ibm.icu.text.DecimalFormat;


public class PressCardAction extends IbatisBaseAction {
	private static String EREADER = "E";
	private PressCardAppValidator validator;
	public PressCardAppValidator getValidator() {
		return validator;
	}

	public void setValidator(PressCardAppValidator validator) {
		this.validator = validator;
	}
	private PressCardReport pressCardReport;

	public PressCardReport getPressCardReport() {
		return pressCardReport;
	}

	public void setPressCardReport(PressCardReport pressCardReport) {
		this.pressCardReport = pressCardReport;
	}

	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}else{
				return addApply(form,mapping,request,user);
			}
			
		}
		if("list".equals(act)){		//query active projects
			return queryPressAppList(form,mapping,request,user);
		}
		if("c_ereader".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new_ereader");
			}else{
				return addEreaderApply(form,mapping,request,user);
			}
			
		}
		if("list_ereader".equals(act)){		//query active projects
			return queryEreaderAppList(form,mapping,request,user);
		}
		if("down".equals(act)){		//query active projects
			return download(request,response);
		}
		if("cardDown".equals(act)){		//query active projects
			return cardDown(request,response);
		}
		if("list_cardno".equals(act)){		//query active projects
			return list_cardno(form,mapping,request,user);
		}
		if("Down".equals(act)){		//query active projects
			return down(request,response,form,user); 
		}
		if("statics".equals(act)){		//query active projects
			return mapping.findForward("statics");
		}
		return forwardError(request,mapping,"Ò³ÃæÎ´ÕÒµ½,´íÎó·¢ÉúÔÚ"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Presscardapptb vo = new Presscardapptb();
		PressCardForm f = (PressCardForm)form;
		copyProperties(vo,f);
		validator.validate(vo);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		vo.setClassId(f.getCardType());
		KeyCard k = (KeyCard)ProCardFactory.getInstance(vo);
		String[] cards=k.getCardNo(vo);
		((PressCardBO)bo).transinsert(vo,cards);		
		return forwardSuccessPage(request,mapping,"±£´æ³É¹¦,ÉêÇë±àºÅÎª"+vo.getFormNo(),"PressCard.do?act=list&formNo="+vo.getFormNo());
		
	}
	
	public ActionForward queryPressAppList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String formNo = request.getParameter("formNo");
		if (pageNo == null && requery == null && formNo == null) {			
			return mapping.findForward("list");
	    }
		PressCardForm f = (PressCardForm)form;
		validator.queryValidate(f);
		setPageResult(request, ((PressCardBO)bo).getAppList(f));
		return mapping.findForward("list");
	}
	private ActionForward download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String formNo = request.getParameter("formNo");
		Presscardapptb p = new Presscardapptb();
		p.setFormNo(formNo);
		List presscardList = ((PressCardBO)bo).queryForList(p);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			formNo = new String(formNo.getBytes("UTF-8"), "ISO8859-1");//firefoxä¯ÀÀÆ÷
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			formNo = URLEncoder.encode(formNo, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+formNo+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<presscardList.size();i++){
			Presscardapptb tmp=(Presscardapptb)presscardList.get(i);
			byte[] b= tmp.getCardNo().getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
	public ActionForward addEreaderApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Presscardapptb vo = new Presscardapptb();
		PressCardForm f = (PressCardForm)form;
		copyProperties(vo,f);
		validator.validateEreader(vo);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		vo.setClassId(EREADER);
		EreaderCard k = (EreaderCard)ProCardFactory.getInstance(vo);
		String[] cards=k.getCardNo(vo);
		((PressCardBO)bo).transinsert(vo,cards);		
		return forwardSuccessPage(request,mapping,"±£´æ³É¹¦,ÉêÇë±àºÅÎª"+vo.getFormNo(),"PressCard.do?act=list_ereader&formNo="+vo.getFormNo());
		
	}
	
	public ActionForward queryEreaderAppList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String formNo = request.getParameter("formNo");
		if (pageNo == null && requery == null && formNo == null) {			
			return mapping.findForward("list_ereader");
	    }
		PressCardForm f = (PressCardForm)form;
		validator.queryValidate(f);
		setPageResult(request, ((PressCardBO)bo).getEreaderAppList(f));
		return mapping.findForward("list_ereader");
	}
	public ActionForward list_cardno(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		
		if (pageNo == null && requery == null) {			
			return mapping.findForward("list_cardno");
	    }
		PressCardForm f = (PressCardForm)form;
		validator.queryCardValidate(f);
		setPageResult(request, ((PressCardBO)bo).queryCardList(f));
		return mapping.findForward("list_cardno");
	}
	private ActionForward cardDown(HttpServletRequest request, HttpServletResponse response)throws Exception{
		PressCardForm f = new PressCardForm();
		validator.queryCardValidate(f);
		String date="Ó¡Ë¢¿¨ºÅ"+DateUtil.getTimeStr();
		List presscardList = ((PressCardBO)bo).queryCardList(f);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			date = new String(date.getBytes("UTF-8"), "ISO8859-1");//firefoxä¯ÀÀÆ÷
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			date = URLEncoder.encode(date, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+date+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<presscardList.size();i++){
			Presscardapptb tmp=(Presscardapptb)presscardList.get(i);
			String classId=","+SingleDicMap.getDicItemVal(SingleDic.CLASS_ID, tmp.getClassId());
			String commRate=","+(tmp.getCommRate()==null?"":SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, tmp.getCommRate()));
			String manuId=","+(tmp.getClassId().equals("E")?SingleDicMap.getDicItemVal(SingleDic.EREADERMAUN_ID, tmp.getManufacId()):SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, tmp.getManufacId()));
			byte[] b= (tmp.getCardNo()+classId+commRate+manuId).getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
	private ActionForward down(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		PressCardForm f = new PressCardForm();
		Presscardapptb vo = new Presscardapptb();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		pressCardReport.createExcel(vo, false);
		response.setContentType("application/octet-stream");
		String filename = pressCardReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefoxä¯ÀÀÆ÷
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		pressCardReport.getEt().write(out);
		out.close();
		return null;
	}
}
