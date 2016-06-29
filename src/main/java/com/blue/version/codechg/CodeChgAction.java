package com.blue.version.codechg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.DetailType;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;

public class CodeChgAction extends IbatisBaseAction {
	@Autowired
	private CodeChgBO bo;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,response,user);
		}
		if("upload".equals(act)){
			return upload(mapping,form,request,response,user);
		}
		if("input".equals(act)){
			return input(mapping,form,request,response,user);
		}
		if("maintain".equals(act)){
			return maintain(mapping,form,request,response,user);
		}
		if("getlist".equals(act)){
			getlist(mapping, form, request, response, user);
		}
		return null;
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) {
		String versionId = request.getParameter("versionId");
		setPageResult(request, bo.queryForList(versionId));
		((CodeChgForm)form).setVersionId(versionId);
		return mapping.findForward("list");
	}

	private ActionForward maintain(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) {
		String versionId = request.getParameter("versionId");
		List<CodeChg> l = bo.queryForList(versionId);
		StringBuffer sb = new StringBuffer();
		for(CodeChg c : l){
			sb.append(c.getChgType());
			sb.append(",");
			sb.append(c.getFileName());
			sb.append("\n");
		}
		((CodeChgForm)form).setMemo(sb.toString());
		return mapping.findForward("edit");
	}

	private ActionForward upload(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user)throws Exception {
		CodeChgForm f = (CodeChgForm)form;
		FormFile file = f.getFile();
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"GB18030"));
		updateCodeList(f.getVersionId(), br);
		br.close();
		return forwardSuccessPage(request, mapping, "维护成功",getbackurl(f.getVersionId()));
	}

	private ActionForward input(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception{
		CodeChgForm f = (CodeChgForm)form;
		String memo = f.getMemo();
		BufferedReader br = new BufferedReader(new StringReader(memo));
		updateCodeList(f.getVersionId(), br);
		return forwardSuccessPage(request, mapping,"维护成功", getbackurl(f.getVersionId()));
	}
	private String getbackurl(String versionId){
		return "VersionHis.do?act=tabs&versionId="+versionId+"&type="+DetailType.code;
	}
	private void updateCodeList(String versionId,BufferedReader br)throws Exception{
		if(br == null){
			return;
		}
		String s = null;
		List<CodeChg> l = new ArrayList<CodeChg>();
		while((s = br.readLine()) != null){
			String[] t = s.split(",");
			if(t.length < 2){
				throw new MessageException("格式错误，错误记录为["+s+"]");
			}
			CodeChg c = new CodeChg();
			c.setChgType(t[0]);
			c.setFileName(t[1]);
			c.setVersionId(versionId);
			l.add(c);
		}
		bo.transUpdate(l, versionId);
	}
	private void getlist(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user)throws Exception {
		String versionId = request.getParameter("versionId");
		List<CodeChg> l = bo.queryForList(versionId);
		
		writeList(request, response, l);
	}


}
