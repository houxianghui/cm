package com.eis.timer;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.portal.user.UserDAO;
import com.eis.portal.user.UserVO;
import com.eis.util.DateUtil;
import com.eis.util.MailSender;
import com.projectmaintain.GetNotWriteNoteStuffBO;
public class MailNoticeTask implements Job{
	@Autowired
	private GetNotWriteNoteStuffBO bo;
	@Autowired
	private UserDAO userDao;
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		send();
	}
	public void send() {
		if(DateUtil.isWeekEnd(Calendar.getInstance())){
			return;
		}
		try {
			List<UserVO> userIds = bo.getNotWriteListByDate(DateUtil.getDTStr());
			for(UserVO u: userIds){
				MailSender.sendMail("工时填报提醒", "你今日未填写工时", u.getEmail());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
