/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.info;

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
import org.kie.api.task.model.User;

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


public class BiunitinfoAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}else{
				return add(form,mapping,request,user);
			}
			
		}else if ("u".equals(act)) { 								//修改申请资料信息 
            String step = request.getParameter("step");
            if (null == step) { 									//初始化阶段，查询明细信息并跳转到修改页面 
                return initEdit(form,mapping, request); 
            } else 												//用户已提交修改后的数据，执行数据保存 
                return update(form,mapping, request, user);
        }else if("list".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else if("popList".equals(act)){		//query active projects
			return popList(form,mapping,request,user);
		}

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward add(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Biunitinfotb vo = new Biunitinfotb();
		BiunitinfotbForm f = (BiunitinfotbForm)form;
		copyProperties(vo,f);
		vo.setCurrDate(DateUtil.getDTStr());
		vo.setOperId(user.getUserID());
		((BiunitinfoBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Biunitinfo.do?act=list");
		
	}
	public ActionForward initEdit(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		Biunitinfotb vo =((BiunitinfoBO)bo).queryForObject(Integer.parseInt(request.getParameter("unitId")));
		copyProperties(form,vo);
		return mapping.findForward("edit");
		
	}
	public ActionForward update(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Biunitinfotb vo = new Biunitinfotb();
		BiunitinfotbForm f = (BiunitinfotbForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());		
		vo.setCurrDate(DateUtil.getTimeStr());
		((BiunitinfoBO)bo).update(vo);	
		return forwardSuccessPage(request,mapping,"更新成功","Biunitinfo.do?act=list");
	}
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String requery = request.getParameter("requery");
		if (requery == null) {			
			return mapping.findForward("qlist");
	    }
		BiunitinfotbForm f = (BiunitinfotbForm)form;
		setPageResult(request, ((BiunitinfoBO)bo).getCardList(f));
		return mapping.findForward("qlist");
	}

	public ActionForward popList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{

		BiunitinfotbForm f = (BiunitinfotbForm)form;
		setPageResult(request, ((BiunitinfoBO)bo).getList(f));
		return mapping.findForward("list");
	}

}
