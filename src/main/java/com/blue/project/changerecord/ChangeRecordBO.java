package com.blue.project.changerecord;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.decorator.MailDecorator;
import com.blue.decorator.StringDecorator;
import com.blue.enums.ChangeLevel;
import com.blue.enums.ChangeType;
import com.blue.mailconfig.MailConfigBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.util.CheckUtil;
import com.eis.util.MailSender;
public class ChangeRecordBO extends IbatisBO{
	@Autowired
	private ChangeRecordDAO changeRecordDAO;
	@Autowired
	private MailConfigBO mailConfigBO;
	@Override
	public void update(Object obj) throws Exception {
		ChangeRecord r = (ChangeRecord)obj;
		changeRecordDAO.updateByPrimaryKeySelective((ChangeRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this, null);
		MailSender.sendMail(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, r.getUserId())+ "新增变更记录"+r.getChangeId(), getContent((ChangeRecord)obj), to);
	}
	private String getContent(ChangeRecord r){
		StringDecorator d = new MailDecorator();
		d.add("项目编号", r.getProjectId());
		d.add("变更名称", r.getChangeTitle());
		d.add("变更级别", ChangeLevel.valueOf(r.getChangeLevel()).getDesc());
		d.add("变更类型", ChangeType.valueOf(r.getChangeType()).getDesc());
		d.add("变更原因", r.getReason());
		d.add("变更内容", r.getContent());
		return d.decorate();
	}
	@Override
	public void insert(Object obj) throws Exception {
		ChangeRecord r = (ChangeRecord)obj;
		changeRecordDAO.insertSelective((ChangeRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this, null);
		MailSender.sendMail(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, r.getUserId())+ "修改变更记录"+r.getChangeId(), getContent((ChangeRecord)obj), to);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		
		return changeRecordDAO.selectByPrimaryKey(Integer.parseInt(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		ChangeRecordForm form =(ChangeRecordForm)obj;
		ChangeRecordExample e = new ChangeRecordExample();
		if(!CheckUtil.isEmptry(form.getProjectId())){
			e.createCriteria().andProjectIdEqualTo(form.getProjectId());
		}
		return changeRecordDAO.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		changeRecordDAO.deleteByPrimaryKey(Integer.parseInt((String)obj));
	}

	public static String formatChangeId(ChangeRecord r){
		DecimalFormat df = new DecimalFormat("00");
		return r.getProjectId()+"-BG"+df.format(r.getChangeId()==null?0:r.getChangeId());
	}

	public int getMax(String projectId) {
		int id = jdbcTemplate.queryForInt("select max(CHANGE_ID) from change_record where PROJECT_ID=?",projectId);
		return id+1;
	}
	
}
