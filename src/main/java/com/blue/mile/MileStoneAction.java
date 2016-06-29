package com.blue.mile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;

public class MileStoneAction extends IbatisBaseAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act = request.getParameter("act");
		if(act.equals("init")){
			request.setAttribute("act", act);
			request.setAttribute("projectId", request.getParameter("projectId"));
			return mapping.findForward("edit");
		}
		if(act.equals("edit")){
			request.setAttribute("act", act);
			return edit(request, form, mapping);
		}
		if(act.equals("update")){
			return update(form,mapping,request,user);
		}
		if(act.equals("add")){
			return add(form,mapping,user,request);
		}
		if(act.equals("list")){
			return list(form,mapping,request);
		}
		if(act.equals("delete")){
			return delete(form,mapping,request);
		}
		if(act.equals("getMilesStone")){
			makeMileStoneSelect(request,response);
			return null;
		}
		if(act.equals("getMileOptions")){
			makeMileOptions(request,response);
			return null;
		}
		return null;
	}

	private void makeMileOptions(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MileStone> l = bo.queryForList(request.getParameter("projectId"));
		String selected = request.getParameter("selected");
		Map<String, Object> map = new HashMap<String, Object>();
		for(MileStone m : l){
			map.put(m.getMileStoneId().toString(),m.getStoneName());
		}
		writeAjaxResponse(response, makeOptions(map,selected));
	}

	private void makeMileStoneSelect(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MileStone> l = bo.queryForList(request.getParameter("projectId"));
		Map<String, Object> map = new HashMap<String, Object>();
		for(MileStone m : l){
			map.put(m.getMileStoneId().toString(),m.getStoneName());
		}
		writeAjaxResponse(response, makeSelect(map));
	}

	private ActionForward delete(BaseForm form, ActionMapping mapping,
			HttpServletRequest request)throws Exception {
		bo.delete(request.getParameter("mileStoneId"));
		return forwardSuccessPage(request, mapping, "删除成功",getListUrl(request.getParameter("projectId")));
	}

	private ActionForward list(BaseForm form, ActionMapping mapping,HttpServletRequest request) throws Exception{
		setPageResult(request, bo.queryForList(request.getParameter("projectId")));
		return mapping.findForward("list");
	}

	private ActionForward add(BaseForm form, ActionMapping mapping,
			UserContext user,HttpServletRequest request) throws Exception{
		MileStone ms = new MileStone();
		copyProperties(ms, form);
		ms.setInputDate(DateUtil.getDTStr());
		ms.setInputUser(user.getUserID());
		ms.setMileStoneId(null);
		bo.insert(ms);
		return forwardSuccessPage(request, mapping, "增加成功",getListUrl(ms.getProjectId()));
	}

	private ActionForward update(BaseForm form, ActionMapping mapping,
			HttpServletRequest request, UserContext user) throws Exception{
		MileStone ms = new MileStone();
		copyProperties(ms, form);
		bo.update(ms);
		return forwardSuccessPage(request, mapping, "修改成功",getListUrl(ms.getProjectId()));
	}

	private ActionForward edit(HttpServletRequest request,BaseForm form,ActionMapping mapping) throws Exception{
		MileStone ms = (MileStone)bo.queryForObject(((MileStoneForm)form).getMileStoneId());
		copyProperties(form, ms);
		
		return mapping.findForward("edit");
	}
	private String getListUrl(String projectId){
		return "MileStone.do?act=list&projectId="+projectId;
	}
	
}
