/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.issue;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;

import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.StringUtil;

import com.yly.info.BiunitinfoBO;
import com.yly.info.Biunitinfotb;




public class IssuetaskCtrlAction extends IbatisBaseAction {
	private BiunitinfoBO biunitinfoBO;
	private IssuetaskCtrlBO issuetaskctrlBO;
	private IssuetaskBO issuetaskBO;



	public IssuetaskBO getIssuetaskBO() {
		return issuetaskBO;
	}

	public void setIssuetaskBO(IssuetaskBO issuetaskBO) {
		this.issuetaskBO = issuetaskBO;
	}

	public BiunitinfoBO getBiunitinfoBO() {
		return biunitinfoBO;
	}

	public IssuetaskCtrlBO getIssuetaskctrlBO() {
		return issuetaskctrlBO;
	}

	public void setIssuetaskctrlBO(IssuetaskCtrlBO issuetaskctrlBO) {
		this.issuetaskctrlBO = issuetaskctrlBO;
	}

	public void setBiunitinfoBO(BiunitinfoBO biunitbo) {
		this.biunitinfoBO = biunitbo;
	}

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}
			else{
				return addApply(form,mapping,request,user);
			}
			
		} else if("listpop".equals(act)){		//query active projects
			return queryListpop(form,mapping,request,user);
		} else if("batchAdd".equals(act)){		//query active projects
			return makeLists(mapping,request,user,form);
		}else if("list".equals(act)){		//query active projects
			return List(form,mapping,request,user);
		}else if("ql".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else return null;
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssuetaskctrlForm f = (IssuetaskctrlForm)form;
		Issuetaskctrl vo = new Issuetaskctrl();
		copyProperties(vo,f);
		((IssuetaskCtrlBO)bo).insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功","Issueapp.do?act=u&appNo="+vo.getAppNo());
		
	}

	private List getUnitList(Integer leadStore) throws Exception{
		Biunitinfotb un =  new Biunitinfotb();
		un.setLeadStore(leadStore);
		un.setUnitstat((short)0);
		List UnitList = biunitinfoBO.queryList(un);
		if(UnitList.size()<1){
			un =  new Biunitinfotb();
			un.setUnitid(leadStore);
			UnitList = biunitinfoBO.queryList(un);
		}
		return UnitList;
		
	}
	private boolean isOwnUnit(Integer leadStore,Integer unitId) throws Exception{
		Biunitinfotb un =  new Biunitinfotb();
		un.setLeadStore(leadStore);
		un.setUnitstat((short)0);
		un.setUnitid(unitId);
		List UnitList = biunitinfoBO.queryList(un);
		if(UnitList.size()<1){
			return false;
		}
		return true;
		
	}
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssuetaskctrlForm f = (IssuetaskctrlForm)form;
		f.setTaskCtrlNo(request.getParameter("taskCtrlNo"));
		setPageResult(request, ((IssuetaskCtrlBO)bo).getListByPrimaryKey(f));
		return mapping.findForward("list");
	}
	public ActionForward List(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssuetaskctrlForm f = (IssuetaskctrlForm)form;
		f.setTaskNo(request.getParameter("taskNo"));
		setPageResult(request, ((IssuetaskCtrlBO)bo).getAppList(f));
		return mapping.findForward("list");
	}
	public ActionForward queryListpop(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssuetaskctrlForm f = (IssuetaskctrlForm)form;
		String strPtname = request.getParameter("OAappNo"); 
		strPtname = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8");  
		f.setOAappNo(strPtname);
		List<Issuetaskctrl> l=((IssuetaskCtrlBO)bo).queryList(f.getTaskNo());
		if(l.size()>0){
			return popClosePage(request, mapping, "已分配单位","");
		}
		return mapping.findForward("listpop");
	}
	private ActionForward makeLists(ActionMapping mapping,
			HttpServletRequest request, UserContext user,BaseForm form)
			throws Exception {
		
		IssuetaskctrlForm f = (IssuetaskctrlForm)form;
		Enumeration<String> e =request.getParameterNames();
		List<Issuetaskctrl> issues = new ArrayList<Issuetaskctrl>();
		Pattern iPattern = Pattern.compile("issue_\\d+$");
		Long tot=0l;
		HashMap unitMap = new HashMap();
		String[] samNo=new String[2];
		while(e.hasMoreElements()){
			String s = e.nextElement();
			Matcher m = iPattern.matcher(s);
			if(m.find()){
				Issuetaskctrl di = new Issuetaskctrl();
				if(CheckUtil.isEmptry(request.getParameter(s))){
					throw new MessageException("必须录入单位代码");
				}
				if(!isOwnUnit(f.getUnitId(),Integer.parseInt(request.getParameter(s)))){
					throw new MessageException("录入单位代码必须为当前申请单位及其辖属机构");
				}
				di.setOAappNo(f.getOAappNo());
				di.setUnitId((Integer.parseInt(request.getParameter(s).trim())));
				di.setIssueAmt((Long.valueOf(request.getParameter(s+"_Amt"))));
				di.setPaymentType((Short.valueOf(request.getParameter(s+"_Ptp"))));
				di.setConsumeType((Short.valueOf(request.getParameter(s+"_Ctp"))));
				di.setUnitPrice((Long.valueOf(request.getParameter(s+"_Pri"))));
				tot+=di.getIssueAmt();
				if(autoGenCard(f.getOperationType(),f.getProdId())){
					if(unitMap.size()!=0 && unitMap.get(di.getUnitId())!=null){	
						String samId=String.valueOf(unitMap.get(di.getUnitId()));
						long tmp=Long.parseLong(samId);
						String tmp_s=String.valueOf(++tmp);
						di.setSamIdBegin(StringUtil.addZeroB(tmp_s, 12-tmp_s.length()));
						String tmpEnd_s=String.valueOf(tmp+di.getIssueAmt()-1);
						di.setSamIdEnd(StringUtil.addZeroB(tmpEnd_s, 12-tmpEnd_s.length()));				
						unitMap.put(di.getUnitId(), di.getSamIdEnd());
					}else{
						samNo=((IssuetaskCtrlBO)bo).getSamNo(f,di);
						di.setSamIdBegin(samNo[0]);
						di.setSamIdEnd(samNo[1]);
						unitMap.put(di.getUnitId(), di.getSamIdEnd());
					}
				}else{
					if(!CheckUtil.isEmptry(f.getSamIdBegin())){
						di.setSamIdBegin(f.getSamIdBegin());
						di.setSamIdEnd(di.getSamIdBegin());
					}else{
						di.setSamIdBegin("0");
						di.setSamIdEnd("0");
					}
			
				}
				
				di.setTaskNo(f.getTaskNo());
				di.setAppNo(f.getAppNo());
				di.setIssueDoneAmt(0L);
				issues.add(di);
				continue;
			}
			
		}
		if(tot!=f.getTaskAmt().longValue()){
			throw new MessageException("数量总和必须等于该类产品总发行数量");
		}
		
		((IssuetaskCtrlBO)bo).batchinsert(issues);
		return popClosePage(request, mapping, "保存成功","");
	}
    private boolean autoGenCard(String operType,String prod){
    	boolean flag = false;
    	if(operType.equals("21") ||operType.equals("22")|| operType.equals("23") || operType.equals("43") || operType.equals("53")){
    		if(!prod.equals("5"))//不是Ereader就自动生成卡号
    			flag=true;
    	}
    	return flag;
    	
    }
	
}
