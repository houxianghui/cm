package com.blue.project.reviewrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.functiondef.FunctionDef;
import com.blue.functiondef.FunctionDefDAO;
import com.blue.functiondef.FunctionDefExample;
import com.blue.mailconfig.MailConfigBO;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.MailSender;

public class ReviewRecordBO extends IbatisBO {
	private ReviewRecordDAO reviewRecordDAO;
	@Autowired
	private FunctionDefDAO functionDefDAO;
	@Autowired
	private MailConfigBO mailConfigBO;
	private int getFunctionId(){
		FunctionDefExample e = new FunctionDefExample();
		e.createCriteria().andClassNameEqualTo(getClass().getName());
		List<FunctionDef> l = functionDefDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			return -1;
		}
		return l.get(0).getFunctionId();
	}
	public void update(ReviewRecord obj,UserContext user) throws Exception {
		reviewRecordDAO.updateByPrimaryKeySelective((ReviewRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail(user.getUserName()+"–ﬁ∏ƒ∆¿…Ûº«¬º", getContent(obj), to);
	}
	private String getContent(ReviewRecord r){
		StringBuffer sb = new StringBuffer();
		sb.append("œÓƒø±‡∫≈:["+r.getProjectId()+"]\r\n");
		sb.append("∆¿…Û√˚≥∆:["+r.getTitle()+"]\r\n");
		sb.append("∆¿…Û√Ë ˆ:["+r.getContent()+"]");
		return sb.toString();
		
	}
	@Override
	public void insert(Object obj) throws Exception {
		
	}
	@Override
	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void insert(ReviewRecord obj,UserContext user) throws Exception {
		reviewRecordDAO.insertSelective((ReviewRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail(user.getUserName()+"–¬‘ˆ∆¿…Ûº«¬º", getContent(obj), to);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return reviewRecordDAO.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		ReviewRecordExample e = new ReviewRecordExample();
		if(obj != null){
			ReviewRecordForm form = (ReviewRecordForm)obj;
			if(!CheckUtil.isEmptry(form.getProjectId())){
				e.createCriteria().andProjectIdEqualTo(form.getProjectId());
			}
		}
		return reviewRecordDAO.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		reviewRecordDAO.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	public void setReviewRecordDAO(ReviewRecordDAO reviewRecordDAO) {
		this.reviewRecordDAO = reviewRecordDAO;
	}
}
