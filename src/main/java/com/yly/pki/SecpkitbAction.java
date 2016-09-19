/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.pki;

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
import com.yly.presscard.PressCardBO;
import com.yly.presscard.PressCardForm;
import com.yly.presscard.Presscardapptb;


public class SecpkitbAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){		//query active projects
			return List(form,mapping,request,user);
		}else if("cardDown".equals(act)){		//query active projects
			return cardDown(form,request,response);
		}
		return forwardError(request,mapping,"Ò³ÃæÎ´ÕÒµ½,´íÎó·¢ÉúÔÚ"+this.getClass().getName());
	}
	

	
	
	public ActionForward List(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null || requery == null) {			
			return mapping.findForward("qlist");
	    }
		SecpkitbForm f = (SecpkitbForm)form;
		((SecpkitbBO)bo).querySamIdValidate(f); 
		setPageResult(request, ((SecpkitbBO)bo).queryForListByScale(f));
		return mapping.findForward("qlist");
	}
	private ActionForward cardDown(BaseForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		SecpkitbForm f = (SecpkitbForm)form;
		((SecpkitbBO)bo).querySamIdValidate(f);
		String scale="";
		String title="";
		if(f.getBeginDate_f()!=null ||f.getEndDate_f()!=null){
			 title=f.getBeginDate_f()+"_"+f.getEndDate_f();
		}else{
			 title=f.getSamId_min()+"_"+f.getSamId_max();
		}
		List pkiList = ((SecpkitbBO)bo).queryForListByScale(f);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			scale = new String(title.getBytes("UTF-8"), "ISO8859-1");//firefoxä¯ÀÀÆ÷
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			scale = URLEncoder.encode(title, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+scale+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<pkiList.size();i++){
			Secpkitb tmp=(Secpkitb)pkiList.get(i);
			byte[] b= (tmp.getSamId()+","+tmp.getSamCSN()+","+tmp.getPubKey()).getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}


}
