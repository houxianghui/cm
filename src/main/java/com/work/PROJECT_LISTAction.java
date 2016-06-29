 
package com.work; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.cache.*;
 
 
/** 
 * 说明：工作列表的控制类 
 */ 
public class PROJECT_LISTAction extends BaseAction { 
 
	/** 
	 * 构造函数 
	 */ 
	public PROJECT_LISTAction() { 
		super(); 
	} 
 
	/** 
	 * 执行请求处理 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		if (act.equals("c")) { //增加 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，跳转到增加页面 
 				return init(mapping,form,request,user);
				
 
			} else //用户已提交新增数据，执行数据保存 
 
				return add(mapping, form, request, response, user); 
 
		} else if (act.equals("u")) { //修改 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 
 
				return editInfo(mapping, form, request, response, user); 
 
			} else //用户已提交修改后的数据，执行数据保存 
 
				return update(mapping, form, request, response, user); 
 
		} else if (act.equals("r")) { //查询明细信息 
 
			return retrieve(mapping, form, request, response, user); 
 
		} else if (act.equals("d")) { //删除数据 
 
			return delete(mapping, form, request, response, user); 
 
		} else if (act.equals("list")) { //返回维护列表 
 
			return list(mapping, form, request, response, user); 
 
		} else if (act.equals("ql")) { //查询列表 
 
			return queryList(mapping, form, request, response, user); 
 
		} else 
			return null; 
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		return mapping.findForward("new"); 
 	}
 
	/** 
	 * 查询维护列表 
	 */ 
	public ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//在此处通过page.addFilter()方法添加过滤条件 
 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		//调用业务对象的list（）方法,返回列表结果 
		bo.list(page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("list"); 
	} 
 
 
	/** 
	 * 查询列表 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		//调用业务对象的queryList（）方法,返回列表结果 
		((PROJECT_LISTBO)bo).queryList(form,page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("querylist"); 
	} 
 
 
	/** 
	 * 增加数据 
	 */ 
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//进行数据传输 
		copyProperties(vo, form); 
 		vo.setUser_id(user.getUserID());
		//在此处进行其它字段的赋值，例如：vo.setReg_dt(DateUtil.getDTStr()); 
		//调用业务对象的add（）方法,返回列表结果 
		bo.add(vo, user); 
 
		//执行页面跳转,返回到列表页面 
		return new ActionRedirect("PROJECT_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * 查询维护信息明细 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//获得查询主键 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(PROJECT_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * 修改数据 
	 */ 
	public ActionForward update(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//在此处进行其它字段的赋值，例如：form.setReg_dt(DateUtil.getDTStr()); 
 
		//进行数据传输 
		copyProperties(vo, form); 
 
		//调用业务对象的update（）方法,返回列表结果 
		bo.update(vo, user); 
 
		//传递数据保存结果 
		request.setAttribute("success", "y"); 
 
		//执行页面跳转 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * 删除数据 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//获得删除纪录主键 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//调用业务对象的delete（）方法,执行数据删除 
		bo.delete(vo, user); 
 
		//执行页面跳转 
		return forwardSuccessPage(request,mapping,"数据删除成功！","PROJECT_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * 查询明细信息 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//获得查询主键 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(PROJECT_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view"); 
	} 
 
 
} 

