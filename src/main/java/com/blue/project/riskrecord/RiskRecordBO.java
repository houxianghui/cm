package com.blue.project.riskrecord;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.enums.RiskLv;
import com.blue.enums.RiskType;
import com.blue.enums.Status;
import com.blue.functiondef.FunctionDef;
import com.blue.functiondef.FunctionDefDAO;
import com.blue.functiondef.FunctionDefExample;
import com.blue.mailconfig.MailConfigBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.MailSender;
import com.projectmaintain.ProjectMaintainBO;

public class RiskRecordBO extends IbatisBO {
	private RiskRecordDAO riskRecordDAO;
	@Autowired
	private FunctionDefDAO functionDefDAO;
	@Autowired
	private MailConfigBO mailConfigBO;
	public void update(RiskRecord obj,UserContext user) throws Exception {
		riskRecordDAO.updateByPrimaryKeySelective((RiskRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail(user.getUserName()+"修改风险记录", getContent((RiskRecord)obj), to);
	}
	public void update(Object obj) throws Exception {
	}
	@Override
	public void insert(Object obj) throws Exception {
		RiskRecord r = (RiskRecord)obj;
		riskRecordDAO.insertSelective((RiskRecord)obj);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,r.getUserId())+"新增风险记录", getContent((RiskRecord)obj), to);

	}
	private String getContent(RiskRecord r){
		StringBuffer sb = new StringBuffer();
		sb.append("项目编号:["+r.getProjectId()+"]\r\n");
		sb.append("风险名称:["+r.getRiskName()+"]\r\n");
		sb.append("风险类型:["+RiskType.valueOf(r.getRiskType()).getDesc()+"]\r\n");
		sb.append("风险等级:["+RiskLv.valueOf(r.getRiskLv()).getDesc()+"]\r\n");
		sb.append("风险描述:["+r.getMemo()+"]");
		return sb.toString();
		
	}
	@Override
	public Object queryForObject(Object obj) throws Exception {
		return riskRecordDAO.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		RiskRecordExample e = new RiskRecordExample();
		if(obj != null){
			RiskRecordForm form = (RiskRecordForm)obj;
			if(!CheckUtil.isEmptry(form.getProjectId())){
				e.createCriteria().andProjectIdEqualTo(form.getProjectId());
			}
		}
		return riskRecordDAO.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		riskRecordDAO.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	public void setRiskRecordDAO(RiskRecordDAO riskRecordDAO) {
		this.riskRecordDAO = riskRecordDAO;
	}
	public int getMax(String projectId) {
		int i = jdbcTemplate.queryForInt("select max(RISK_ID) from risk_record where PROJECT_ID=?",projectId);
		return i+1;
	}
	
	public static String formatRiskId(RiskRecord r){
		DecimalFormat format = new DecimalFormat("00");
		return r.getProjectId()+"-FX"+format.format(r.getRiskId()==null?0:r.getRiskId());
	}
	public Object queryForListByDeparts(String[] ids) {
		StringBuffer departs = new StringBuffer();
		if(ids != null){
			for(String s : ids){
				departs.append("'"+s+"',");
			}
		}
		departs.append("''");
		List<String> l = jdbcTemplate.queryForList("select PROJECT_ID from project_list where OWNING in ("+departs+") and STAT not in ('"+Status.F+"')", String.class);
		if(l == null || l.size() == 0){
			return new ArrayList<String>();
		}
		RiskRecordExample e = new RiskRecordExample();
		e.createCriteria().andProjectIdIn(l);
		return riskRecordDAO.selectByExample(e);
	}
}
