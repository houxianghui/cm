package com.blue.version.batchchg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.DetailType;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class BatchChgAction extends IbatisBaseAction {
	@Autowired
	private BatchChgBO bo;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,user);
		}
		if("edit".equals(act)){
			return edit(mapping,form,request,user);
		}
		if("new".equals(act)){
			return init(mapping,form,request,user);
		}
		if("add".equals(act)){
			return add(mapping,form,request,user);
		}
		if("update".equals(act)){
			return update(mapping,form,request,user);
		}
		if("delete".equals(act)){
			return delete(mapping,form,request,user);
		}
		
		return null;
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		BatchChg v = new BatchChg();
		copyProperties(v, form);
		bo.insert(v);
		return forwardSuccessPage(request, mapping, "����ɹ�",getBackUrl(v));
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		BatchChg v = bo.queryForObject(request.getParameter("id"));
		bo.delete(v.getId());
		return forwardSuccessPage(request, mapping, "ɾ���ɹ�",getBackUrl(v));
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		BatchChg v = new BatchChg();
		copyProperties(v, form);
		bo.update(v);
		return forwardSuccessPage(request, mapping, "�޸ĳɹ�",getBackUrl(v));
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		form.setAct("add");
		return mapping.findForward("edit");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		BatchChg v = bo.queryForObject(request.getParameter("id"));
		copyProperties(form, v);
		form.setAct("update");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, bo.queryForList(form));
		((BatchChgForm)form).setVersionId(request.getParameter("versionId"));
		return mapping.findForward("list");
	}
	private String getBackUrl(BatchChg v){
		return "VersionHis.do?act=tabs&versionId="+v.getVersionId()+"&type="+DetailType.batch;
	}

}
