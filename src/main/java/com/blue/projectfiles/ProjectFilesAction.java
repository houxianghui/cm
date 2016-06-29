package com.blue.projectfiles;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;

public class ProjectFilesAction extends IbatisBaseAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equalsIgnoreCase(act)){
			return list(request,mapping);
		}else if("new".equals(act)){
			return init(request,mapping);
		}else if("add".equals(act)){
			return add(form,mapping,request,response,user);
		}else if("delete".equals(act)){
			return delete(form,request,mapping);
		}else if("download".equals(act)){
			return download(request,response);
		}else if("all".equals(act)){
			return all(request,mapping);
		}
		return null;
	}

	private ActionForward download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String filename = null;
		
		String id = request.getParameter("id");
		ProjectFiles pf = (ProjectFiles)bo.queryForObject(id);
		byte[] b = pf.getContent();
		response.setContentType("application/octet-stream");
		filename = pf.getFileName();
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		out.write(b);
		out.close();
		return null;
	}

	private ActionForward delete(BaseForm form, HttpServletRequest request,ActionMapping mapping)throws Exception {
		String ids = (String)request.getParameter("ids");
		String[] s = ids.split(",");
		for(String id:s){
			bo.delete(id);
		}
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(request.getParameter("projectId"),request.getParameter("versionId")));
	}

	private ActionForward list(HttpServletRequest request, ActionMapping mapping) throws Exception{
		request.setAttribute("projectId", request.getParameter("projectId"));
		request.setAttribute("versionId", request.getParameter("versionId"));
		setPageResult(request, ((ProjectFilesBO)bo).queryForList(request.getParameter("projectId"),request.getParameter("versionId")));
		return mapping.findForward("list");
	}
	private ActionForward all(HttpServletRequest request, ActionMapping mapping) throws Exception{
		setPageResult(request, bo.queryForList(null));
		return mapping.findForward("all");
	}

	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ProjectFilesForm f = (ProjectFilesForm)form;
		FormFile file = f.getFile();
		ProjectFiles pf = new ProjectFiles();
		pf.setFileName(file.getFileName());
		pf.setProjectId(f.getProjectId());
		pf.setVersionId(f.getVersionId());
		pf.setFileSize(file.getFileSize());
		if(file.getFileName().lastIndexOf(".")!=-1){
			pf.setFileType(file.getFileName().substring(file.getFileName().lastIndexOf(".")+1));
		}else{
			pf.setFileType("未知");
		}
		pf.setUserId(user.getUserID());
		pf.setUpdateDate(DateUtil.getDTStr());
		InputStream is = file.getInputStream();
		byte[] b = new byte[is.available()];
		is.read(b);
		is.close();
		pf.setContent(b);
		pf.setMemo(f.getMemo());
		bo.insert(pf);
		
		return forwardSuccessPage(request, mapping, "上传成功",getBackUrl(pf.getProjectId(),pf.getVersionId()));
	}

	private ActionForward init(HttpServletRequest request, ActionMapping mapping) {
		request.setAttribute("projectId", request.getParameter("projectId"));
		request.setAttribute("versionId", request.getParameter("versionId"));
		return mapping.findForward("new");
	}
	private String getBackUrl(String projectId,String versionId){
		if(CheckUtil.isEmptry(projectId) && CheckUtil.isEmptry(versionId)){
			return "ProjectFiles.do?act=all";
		}
		return "ProjectFiles.do?act=list&projectId="+projectId+"&versionId="+versionId;
	}
}
