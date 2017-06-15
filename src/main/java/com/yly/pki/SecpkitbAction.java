package com.yly.pki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.yly.ls.Lsinfo;
import com.yly.reuse.Storeuse;
import com.yly.reuse.StoreuseForm;



public class SecpkitbAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){		//query active projects
			return List(form,mapping,request,user);
		}else if("upload".equals(act)){		//query active projects
			return processFile(form,mapping,request,response,user);
		}else if("cardDown".equals(act)){		//query active projects
			return cardDown(form,request,response);
		}
		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
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
		String title="";
		if(!CheckUtil.isEmptry(f.getBeginDate_f())||!CheckUtil.isEmptry(f.getEndDate_f())){
			 title=f.getBeginDate_f()+"_"+f.getEndDate_f();
		}else{
			 title=f.getSamId_min()+"_"+f.getSamId_max();
		}
		List pkiList = ((SecpkitbBO)bo).queryForListByScale(f);
		return orgDownFile(request, response, title, pkiList);
	}




	public ActionForward orgDownFile(HttpServletRequest request,
			HttpServletResponse response, String title,
			List pkiList) throws UnsupportedEncodingException, IOException {
		String scale="";
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			scale = new String(title.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			scale = URLEncoder.encode(title, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+scale+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<pkiList.size();i++){
			Secpkitb tmp=(Secpkitb)pkiList.get(i);
			byte[] b= (tmp.getSamId()+","+tmp.getPubKey()).getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
	public ActionForward processFile(BaseForm form,ActionMapping mapping ,HttpServletRequest request,HttpServletResponse response,UserContext user)throws Exception{
		SecpkitbForm sf = (SecpkitbForm)form;
		FormFile file = sf.getFile();
		if(file == null){
			return forwardError(request,mapping,"请选择文件");
		}
		List<Secpkitb> secpkitbCard = new ArrayList<Secpkitb>();
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"GBK"));
		List<SecpkitbForm> cards=processCards(sf,br);
		String title=cards.get(0).getSamId()+"_"+cards.get(cards.size()-1).getSamId();
		for(SecpkitbForm lf:cards){
			Secpkitb pki=((SecpkitbBO)bo).queryForObjectBySamAndCsn(lf);
			secpkitbCard.add(pki);
		}
		return orgDownFile(request, response, title, secpkitbCard);
		}
	private List<SecpkitbForm> processCards(SecpkitbForm f,BufferedReader br) throws IOException, Exception {
		String s=null;
		ArrayList al = new ArrayList();
		ArrayList<SecpkitbForm> cards = new ArrayList();
		while((s=br.readLine()) != null){
			if(s.trim().length() == 0){
				continue;
			}
			String[] card=s.split(",");
			SecpkitbForm lf = new SecpkitbForm();
			lf.setSamId(card[0]);
			lf.setSamCSN(card[1]);
			cards.add(lf);
			al.add(s.trim());
		}
		if(al.size() == 0){
			throw new MessageException("内容不能为空");
		}
		br.close();
		return cards;
	} 

}
