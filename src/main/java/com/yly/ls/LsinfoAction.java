 package com.yly.ls;


import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.exstore.StoproductForm;


public class LsinfoAction extends IbatisBaseAction {
	private StoproductBO stoproductBO;

	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
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
			return queryList(form,mapping,request,user);
		}
		if("E".equals(act)){		//query active projects
			return exam(form,mapping,request,user);
		}
		if("lsDown".equals(act)){		//query active projects
			return lsDown(form,request,response);
		}
		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Lsinfo vo = new Lsinfo();
		LsinfoForm f = (LsinfoForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		((LsinfoBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Applytypeinfo.do?act=list");
		
	}
	public ActionForward exam(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Lsinfo vo = new Lsinfo();
		LsinfoForm f = (LsinfoForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		Stoproduct prod = new Stoproduct();
		StoproductForm sf= new StoproductForm();
		copyProperties(sf,vo);
		prod = stoproductBO.queryForObjByKey(sf);
		((LsinfoBO)bo).insert(vo); 
		return forwardSuccessPage(request,mapping,"保存成功","Applytypeinfo.do?act=list");
		
	}
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		LsinfoForm f = (LsinfoForm)form;
		Lsinfo vo= new Lsinfo();
		copyProperties(vo, f);
		setPageResult(request, ((LsinfoBO)bo).queryForList(vo));
		return mapping.findForward("list");
	}
	private ActionForward lsDown(BaseForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		LsinfoForm f = (LsinfoForm)form;
		Lsinfo vo= new Lsinfo();
		copyProperties(vo, f);
		List lsList = ((LsinfoBO)bo).queryForList(vo);
		String title="";
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			title = new String((f.getAppNo()+"流水记录").getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			title = URLEncoder.encode((f.getAppNo()+"流水记录"), "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+title+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<lsList.size();i++){
			Lsinfo tmp=(Lsinfo)lsList.get(i);
			byte[] b=null;
			if(!CheckUtil.isEmptry(tmp.getSamId())){
				 b= (tmp.getSamId()+","+tmp.getSamCSN()).getBytes();
			}else{
				continue;
			}
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}


}
