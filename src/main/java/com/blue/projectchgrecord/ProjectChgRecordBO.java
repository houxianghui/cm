package com.blue.projectchgrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.decorator.MailDecorator;
import com.blue.decorator.StringDecorator;
import com.blue.mailconfig.MailConfigBO;
import com.blue.project.changerecord.ChangeRecord;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.portal.UserContext;
import com.eis.util.MailSender;

public class ProjectChgRecordBO extends IbatisBO {
	private ProjectChgRecordDAO projectChgRecordDao;
	@Autowired
	private MailConfigBO mailConfigBO;
	public void update(Object obj,UserContext user) throws Exception {
		ProjectChgRecord r = (ProjectChgRecord)obj;
		projectChgRecordDao.updateByPrimaryKeySelective(r);
		String[] to = mailConfigBO.getNotifyUsers(this, null);
		MailSender.sendMail(user.getUserName()+ "修改数据库变更记录", getContent((ProjectChgRecord)obj), to);

	}

	private String getContent(ProjectChgRecord p) {
		StringDecorator d = new MailDecorator();
		d.add("所属模块", SingleDicMap.getDicItemVal(SingleDic.DB_CHG_MODULE,p.getModule()));
		d.add("变更提出日期", p.getFireDate());
		d.add("变更完成日期", p.getFinishDate());
		d.add("变更类型", SingleDicMap.getDicItemVal(SingleDic.DB_CHG_TYPE, p.getChangeType()));
		d.add("变更申请人", p.getFireUser());
		d.add("变更后版本号", p.getVersionAfter());
		d.add("变更实施人",p.getOperUser());
		d.add("目标版本", p.getTargetVersion());
		d.add("变更描述", p.getMemo());
		return d.decorate();
	}

	public void insert(Object obj,UserContext user) throws Exception {
		projectChgRecordDao.insertSelective((ProjectChgRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this, null);
		MailSender.sendMail(user.getUserName()+ "新增数据库变更记录", getContent((ProjectChgRecord)obj), to);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return projectChgRecordDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		ProjectChgRecordExample e = new ProjectChgRecordExample();
		return projectChgRecordDao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		projectChgRecordDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));

	}

	public void setProjectChgRecordDao(ProjectChgRecordDAO projectChgRecordDao) {
		this.projectChgRecordDao = projectChgRecordDao;
	}

	@Override
	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
