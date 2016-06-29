package com.blue.dailyrecord;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.abc.logic.IbatisBO;
import com.blue.dailyrecord.DailyRecordExample.Criteria;
import com.blue.enums.CheckStatus;
import com.blue.enums.DailyType;
import com.blue.enums.ProjectType;
import com.blue.otherdaily.OtherDaily;
import com.blue.otherdaily.OtherDailyDAO;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.projectdistribute.ProjectDistribute;
import com.blue.projectdistribute.ProjectDistributeDAO;
import com.blue.projectdistribute.ProjectDistributeExample;
import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.projectmaintain.NotFullVO;

public class DailyRecordBO extends IbatisBO {
	private DailyRecordDAO dailyRecordDAO;
	
	private ProjectDistributeDAO projectDistributeDAO;
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private OtherDailyDAO otherDailyDAO;
	@Autowired
	private ProjectListDAO projectListDAO;
	
	public void update(Object obj) throws Exception {
		dailyRecordDAO.updateByPrimaryKeySelective((DailyRecord)obj);
	}

	
	public void insert(Object obj) throws Exception {
		dailyRecordDAO.insertSelective((DailyRecord)obj);
	}


	public Object queryForObject(Object obj) throws Exception {
		return dailyRecordDAO.selectByPrimaryKey((Long.parseLong(obj.toString())));
	}

	public List<DailyRecord> queryForList(Object obj,UserContext user) throws Exception {
		DailyRecordExample e = new DailyRecordExample();
		Criteria c = e.createCriteria();
		c.andUserIdEqualTo(user.getUserID());
		DailyRecordForm f = (DailyRecordForm)obj;
		if(!CheckUtil.isEmptry(f.getWorkDate())){
			c.andWorkDateEqualTo(f.getWorkDate());
		}
		if(!CheckUtil.isEmptry(f.getProjectId())){
			c.andProjectIdEqualTo(f.getProjectId());
		}
		c.andWorkDateGreaterThanOrEqualTo(DateUtil.getDateBeforeMonth(DateUtil.getDTStr(),1));
		e.or(e.createCriteria().andCheckedEqualTo(CheckStatus.R.toString()));
		e.setOrderByClause(" WORK_DATE desc");
		return dailyRecordDAO.selectByExample(e);
	}


	public void delete(Object obj) throws Exception {
		dailyRecordDAO.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

	public DailyRecordDAO getDailyRecordDAO() {
		return dailyRecordDAO;
	}

	public void setDailyRecordDAO(DailyRecordDAO dailyRecordDAO) {
		this.dailyRecordDAO = dailyRecordDAO;
	}

	public List queryForList(Object obj) throws Exception {
		return null;
	}
	public List queryForAll(BaseForm form,UserContext user) {
		DailyRecordExample e = new DailyRecordExample();
		Criteria c = e.createCriteria();
		c.andUserIdEqualTo(user.getUserID());
		DailyRecordForm f = (DailyRecordForm)form;
		if(!CheckUtil.isEmptry(f.getWorkDate())){
			c.andWorkDateEqualTo(f.getWorkDate());
		}
		if(!CheckUtil.isEmptry(f.getProjectId())){
			c.andProjectIdEqualTo(f.getProjectId());
		}
		e.setOrderByClause(" WORK_DATE desc");
		return dailyRecordDAO.selectByExample(e);
	}
	public List query(BaseForm form,UserContext user){
		DailyRecordForm f = (DailyRecordForm)form;
		DailyRecordExample e = new DailyRecordExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(f.getUserId())){
			c.andUserIdEqualTo(f.getUserId());
		}
		if(!CheckUtil.isEmptry(f.getStartDate())){
			c.andWorkDateGreaterThanOrEqualTo(f.getStartDate());
		}
		if(!CheckUtil.isEmptry(f.getEndDate())){
			c.andWorkDateLessThanOrEqualTo(f.getEndDate());
		}
		if(!CheckUtil.isEmptry(f.getProjectId())){
			c.andProjectIdEqualTo(f.getProjectId());
		}
		e.setOrderByClause(" WORK_DATE desc");
		return dailyRecordDAO.selectByExample(e);
	}
	public List queryForYestodayRecord(){
		DailyRecordExample e = new DailyRecordExample();
		e.createCriteria().andWorkDateEqualTo(DateUtil.getDateBefore(DateUtil.getDTStr(), 1));
		return dailyRecordDAO.selectByExample(e);
	}

	public void upload(BaseForm form, UserContext user) throws Exception{
		DailyRecordForm f = (DailyRecordForm)form;
		String fileName = f.getFile().getFileName();
		InputStream is = f.getFile().getInputStream();
		List<DailyRecord> dailys;
		List<OtherDaily> others;
		try {
			Workbook wb = null;
			if(fileName.endsWith("xlsx")){
				wb = new XSSFWorkbook(is); 
			}else{
				wb = new HSSFWorkbook(is);
			}
			
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			if(rows <=1){
				return;
			}
			dailys = new ArrayList<DailyRecord>();
			others = new ArrayList<OtherDaily>();
			Map<String, String> dailyTypeMap = new HashMap<String, String>();
			for(DailyType dt:DailyType.values()){
				dailyTypeMap.put(dt.getDesc(), dt.toString());
			}
			for(int i = 1;i<rows;i++){
				Row row = sheet.getRow(i);
				if(row == null){
					continue;
				}
				int cells = row.getPhysicalNumberOfCells();
				if(cells < 4){
					continue;
				}
				String userName = getCellValue(row.getCell(0));
				if(CheckUtil.isEmptry(userName)){
					continue;
				}
				UserVO u = userBO.getUserByUserName(userName);
				if(u == null){
					throw new MessageException("不存在"+row.getCell(0).getStringCellValue()+"这个用户,错误出现在"+i+"行");
				}
				if(row.getCell(2) == null){
					throw new MessageException("请选择项目信息，错误出现在"+(i)+"行");
				}
				String projectInfo = getCellValue(row.getCell(2));
				String[] s = projectInfo.split("-");
				StringBuffer p = new StringBuffer(s[0]);
				if(s.length>1){
					String t = s[1];
					boolean isId = true;
					for(char c:t.toCharArray()){
						if(c<'0' || c>'9'){
							isId = false;
						}
					}
					if(isId){
						p.append("-"+s[1]);
					}
				}
				String projectId = p.toString();
				if(isDaily(projectId)){
					ProjectList project = projectListDAO.selectByPrimaryKey(projectId);
					if(project == null){
						throw new MessageException("项目"+projectId+"不存在，错误出现在"+i+"行");
					}
					DailyRecord dr = new DailyRecord();
					dr.setUserId(u.getUser_id());
					dr.setId(-1);
					dr.setWorkDate(getWorkDate(getCellValue(row.getCell(1))));
					dr.setProjectId(projectId);
					dr.setTaskCost(new BigDecimal(getCellValue(row.getCell(3))));
					dr.setWorkMemo(getCellValue(row.getCell(4)));
					dr.setInputDate(DateUtil.getDTStr());
					dr.setStep(project.getStep());
					if(needCheck(u.getDepart_id())){
						dr.setChecked(CheckStatus.N.toString());
					}else{
						dr.setChecked(CheckStatus.Y.toString());
					}
					dailys.add(dr);
				}else{
					OtherDaily od = new OtherDaily();
					od.setInputUser(u.getUser_id());
					od.setWorkDate(getWorkDate(getCellValue(row.getCell(1))));
					String type = projectInfo.split("-")[1];
					if(dailyTypeMap.get(type) != null){
						od.setType(dailyTypeMap.get(type));
					}else{
						od.setType(DailyType.bingJia.toString());
					}
					od.setCost(new BigDecimal(getCellValue(row.getCell(3))));
					od.setMemo(getCellValue(row.getCell(4)));
					od.setInputDate(DateUtil.getDTStr());
					others.add(od);
				}
			}
		}finally{
			is.close();
		}
		transInsert(dailys,others);
		
	}
	
	private String getWorkDate(String cellValue) {
		if(!CheckUtil.isEmptry(cellValue)){
			if(cellValue.trim().length()== 6){
				return "20"+cellValue;
			}else{
				return cellValue;
			}
		}
		return "";
	}

	private String getCellValue(Cell cell){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		DecimalFormat intFormat = new DecimalFormat("#0");
		 switch (cell.getCellType()) {
	         case Cell.CELL_TYPE_STRING:
	            return cell.getStringCellValue();
	         case Cell.CELL_TYPE_NUMERIC:
	        	 if(cell.getNumericCellValue()%1==0){
	        		 return intFormat.format(cell.getNumericCellValue());
	        	 }else{
	        		 return df.format(cell.getNumericCellValue());
	        	 }
	         case Cell.CELL_TYPE_BOOLEAN:
	             return ""+cell.getBooleanCellValue();
	         case Cell.CELL_TYPE_FORMULA:
	             return cell.getCellFormula();
	         default:
	             return cell.getStringCellValue();
	     }
	}
	
	private void transInsert(List<DailyRecord> dailys, List<OtherDaily> others) {
		for(DailyRecord d : dailys){
			dailyRecordDAO.insertSelective(d);
		}
		for(OtherDaily o : others){
			otherDailyDAO.insertSelective(o);
		}
	}
	
	public void validateWorkDate(DailyRecordForm daily,UserContext user)throws Exception{
		String workDate = daily.getWorkDate();
		String today = DateUtil.getDTStr();
		if(workDate.compareTo(today)>0){
			throw new MessageException("工时不能大于当前日期");
		}
		ProjectDistributeExample e = new ProjectDistributeExample();
		e.createCriteria().andIdEqualTo(daily.getId()).andUserIdEqualTo(user.getUserID());
		
		List<ProjectDistribute> l = projectDistributeDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			throw new MessageException("当日未分配此任务给你，无法填写此工时");
		}
		boolean find = false;
		for(ProjectDistribute p : l){
			if(p.getStartDate().compareTo(workDate)<=0 && p.getEndDate().compareTo(workDate)>=0){
				find = true;
			}
		}
		if(!find){
			throw new MessageException("当日未分配此任务给你，无法填写此工时");
		}
	}
	
	private boolean isDaily(String projectId) {
		return !projectId.startsWith("R&DXX00");
	}

	public ProjectDistributeDAO getProjectDistributeDAO() {
		return projectDistributeDAO;
	}

	public void setProjectDistributeDAO(ProjectDistributeDAO projectDistributeDAO) {
		this.projectDistributeDAO = projectDistributeDAO;
	}

	public List<DailyRecordVO> getCheckList(BaseForm form) throws Exception{
		List<UserVO> users = userBO.getStaffByDeparts(((DailyRecordForm)form).getDepartIds(),false);
		Map<String,UserVO> m = new HashMap<String, UserVO>();
		for(UserVO u:users){
			m.put(u.getUser_id(), u);
		}
		
		DailyRecordExample e = new DailyRecordExample();
		e.createCriteria().andCheckedEqualTo(CheckStatus.N.toString());
		List<DailyRecord> l = dailyRecordDAO.selectByExample(e);
		List<DailyRecordVO> result = new ArrayList<DailyRecordVO>();
		for(DailyRecord d : l){
			if(m.get(d.getUserId()) != null){
				DailyRecordVO vo = new DailyRecordVO();
				BeanUtils.copyProperties(d, vo);
				ProjectList p = projectListDAO.selectByPrimaryKey(d.getProjectId());
				vo.setProjectName(p.getProjectName());
				result.add(vo);
			}
		}
		return result;
	}

	public void pass(HttpServletRequest request, UserContext user) {
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		for(String s : id){
			DailyRecord dr = dailyRecordDAO.selectByPrimaryKey(Long.parseLong(s));
			dr.setCheckUser(user.getUserID());
			dr.setCheckDate(DateUtil.getDTStr());
			dr.setTaskCost(dr.getInputCost());
			dr.setChecked(CheckStatus.Y.toString());
			dailyRecordDAO.updateByPrimaryKeySelective(dr);
		}
	}

	public void refuse(HttpServletRequest request, UserContext user,DailyRecordForm form)throws Exception {
		DailyRecord dr = dailyRecordDAO.selectByPrimaryKey(Long.parseLong(request.getParameter("ids")));
		dr.setCheckUser(user.getUserID());
		dr.setCheckDate(DateUtil.getDTStr());
		dr.setChecked(CheckStatus.R.toString());
		URLDecoder ud = new URLDecoder();
		String s = ud.decode(form.getReason(),"UTF-8");
		dr.setRefuseReason(s);
		dailyRecordDAO.updateByPrimaryKeySelective(dr);
		
	}

	public boolean needCheck(UserContext user) {
		String departId = user.getDeptID();
		String key = SingleDicMap.getDicItemLogicID(SingleDic.DEPART, departId);
		return "Y".equalsIgnoreCase(key);
	}
	public boolean needCheck(String departId) {
		String key = SingleDicMap.getDicItemLogicID(SingleDic.DEPART, departId);
		return "Y".equalsIgnoreCase(key);
	}
	public List<SummaryOfProject> sumaryOfProject(DailyRecordForm form){
		String[] ids = form.getDepartIds();
		StringBuffer sb = new StringBuffer("select a.PROJECT_ID as projectId," +
				"b.project_name as projectName," +
				"b.plan_cost as planCost," +
				"sum(a.TASK_COST) as taskCost," +
				"sum(a.INPUT_COST) as inputCost " +
				"from daily_record a ,project_list b " +
				"where a.PROJECT_ID = b.PROJECT_ID and b.stat not in ('F','D') ");
		sb.append(" and b.OWNING in(");
		if(ids != null){
			for(String t : ids){
				
				sb.append("'"+t+"',");
				
			}
		}
		sb.append("'')");
		sb.append(" group by a.PROJECT_ID,b.plan_cost,b.project_name");
		List<SummaryOfProject> l = jdbcTemplate.query(sb.toString(), new RowMapper<SummaryOfProject>(){
		
			public SummaryOfProject mapRow(ResultSet rs, int rowNum) throws SQLException {
				SummaryOfProject sp = new SummaryOfProject();
				sp.setProjectId(rs.getString("projectId"));
				sp.setProjectName(rs.getString("projectName"));
				sp.setPlanCost(rs.getDouble("planCost"));
				sp.setCheckedCost(rs.getDouble("taskCost"));
				sp.setInputCost(rs.getDouble("inputCost"));
				return sp;
			}
		});
		return l;
	}
	public List<SummaryOfType> sumaryOfType(DailyRecordForm form){
		String[] ids = form.getDepartIds();
		StringBuffer sb = new StringBuffer("select b.PROJECT_CLASS as type," +
				"sum(b.plan_cost) as planCost," +
				"sum(a.TASK_COST) as taskCost," +
				"sum(a.INPUT_COST) as inputCost " +
				"from daily_record a ,project_list b " +
				"where a.PROJECT_ID = b.PROJECT_ID and b.stat not in ('F','D')");
		sb.append(" and b.OWNING in(");
		if(ids != null){
			for(String t : ids){
				sb.append("'"+t+"',");
			}
		}
		sb.append("'')");
		sb.append(" group by b.PROJECT_CLASS");
		List<SummaryOfType> l = jdbcTemplate.query(sb.toString(), new RowMapper<SummaryOfType>(){
		
			public SummaryOfType mapRow(ResultSet rs, int rowNum) throws SQLException {
				SummaryOfType s = new SummaryOfType();
				s.setType(ProjectType.valueOf(rs.getString("type")));
				s.setPlanCost(rs.getDouble("planCost"));
				s.setCheckedCost(rs.getDouble("taskCost"));
				s.setInputCost(rs.getDouble("inputCost"));
				return s;
			}
		});
		return l;
	}
}
