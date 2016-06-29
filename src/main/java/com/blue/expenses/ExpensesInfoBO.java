package com.blue.expenses;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.abc.logic.IbatisBO;
import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.expenses.ExpensesInfoExample.Criteria;
import com.blue.expenses.detail.ExpensesDetail;
import com.blue.expenses.detail.ExpensesDetailDAO;
import com.blue.expenses.detail.ExpensesDetailExample;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.blue.taxi.TaxiInfoDAO;
import com.blue.taxi.TaxiInfoExample;
import com.blue.userdepartauth.UserDepartAuth;
import com.blue.userdepartauth.UserDepartAuthDAO;
import com.blue.userdepartauth.UserDepartAuthExample;
import com.eis.base.BaseForm;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;


public class ExpensesInfoBO extends IbatisBO {
	@Autowired
	private ExpensesInfoDAO expensesInfoDao;
	@Autowired
	private ExpensesDetailDAO expensesDetailDao;
	@Autowired
	private TaxiInfoDAO taxiInfoDao;
	@Autowired
	private UserDepartAuthDAO userDepartAuthDAO;
	@Autowired
	private ProjectListDAO projectListDAO;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	@Override
	public void update(Object obj) throws Exception {
		expensesInfoDao.updateByPrimaryKeySelective((ExpensesInfo)obj);
	}

	@Override
	public void insert(Object obj) throws Exception {
		expensesInfoDao.insert((ExpensesInfo)obj);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return expensesInfoDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	
	public List queryForList(Object obj, UserContext user) throws Exception {
		ExpensesInfoExample e = new ExpensesInfoExample();
		Criteria ca = e.createCriteria();
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getProject_f())){
			ca.andProjectNoLike(((ExpensesInfoForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getDepart_f())){
			ca.andDeptEqualTo(((ExpensesInfoForm)obj).getDepart_f());
		}
		if(user.getRoleName().equals("部门负责人")||user.getRoleName().equals("分管领导")||user.getRoleName().equals("行政助理")) {
			UserDepartAuthExample uda = new UserDepartAuthExample();
			uda.createCriteria().andUserIdEqualTo(user.getUserID());
			List<UserDepartAuth> userlist = userDepartAuthDAO.selectByExample(uda);
			List departIdList = new ArrayList();
			for(UserDepartAuth r : userlist){
				departIdList.add(r.getDepartId());
			}
			ca.andDeptIn(departIdList);
			e.or(e.createCriteria().andCurrUserEqualTo(user.getUserID()));
		}	
		else if(user.getRoleName().equals("项目经理")){	
			ProjectListExample ple = new ProjectListExample();
			ple.createCriteria().andProjectManagerEqualTo(user.getUserID());
			List<ProjectList> projectList = projectListDAO.selectByExample(ple);
			List projectIdList = new ArrayList();
			for(ProjectList p : projectList){
				projectIdList.add(p.getProjectId());
			}
			ca.andProjectNoIn(projectIdList);
			e.or(e.createCriteria().andCurrUserEqualTo(user.getUserID()));
		}else{
			ca.andCurrUserEqualTo(user.getUserID());
		}
		e.setOrderByClause(" EXPENSES_ID DESC");
		return expensesInfoDao.selectByExample(e);
	}
	public List queryForReport(Object obj, UserContext user) throws Exception {
		ExpensesInfoExample e = new ExpensesInfoExample();
		Criteria ca = e.createCriteria();
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getProject_f())){
			ca.andProjectNoLike(((ExpensesInfoForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getDepart_f())){
			ca.andDeptEqualTo(((ExpensesInfoForm)obj).getDepart_f());
		}
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getRegDate_from())){
			ca.andRegDateGreaterThanOrEqualTo(((ExpensesInfoForm)obj).getRegDate_from());
		}
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getRegDate_to())){
			ca.andRegDateLessThanOrEqualTo(((ExpensesInfoForm)obj).getRegDate_to());
		}
		if(!CheckUtil.isEmptry(((ExpensesInfoForm)obj).getUserId())){
			ca.andCurrUserEqualTo(((ExpensesInfoForm)obj).getUserId());
		}
		e.setOrderByClause(" EXPENSES_ID DESC");
		return expensesInfoDao.selectByExample(e);
	}
	@Override
	public void delete(Object obj) throws Exception {
		expensesInfoDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
		ExpensesDetailExample example = new ExpensesDetailExample();
		example.createCriteria().andExpensesIdEqualTo(Long.parseLong(obj.toString()));
		List<ExpensesDetail> edetail = expensesDetailDao.selectByExample(example);
		ExpensesInfo evo = expensesInfoDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
		for(ExpensesDetail ed: edetail){
			deleteExpensesDetail(ed, evo);
		}
		//expensesDetailDao.deleteByExample(example);
		TaxiInfoExample exle = new TaxiInfoExample();
		exle.createCriteria().andExpensesIdEqualTo(Long.parseLong(obj.toString()));
		taxiInfoDao.deleteByExample(exle);
		
	}
	
	public void transInsertDetail(List listjson,Long expensesId){
		ExpensesDetailExample e = new ExpensesDetailExample();
		e.createCriteria().andExpensesIdEqualTo(expensesId);
		expensesDetailDao.deleteByExample(e);
		for (int i = 0; i < listjson.size(); i++) {
			expensesDetailDao.insert((ExpensesDetail)listjson.get(i));			
		}
		
	}
	public void setExpensesInfoDao(ExpensesInfoDAO dao) {
		this.expensesInfoDao = dao;
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteToUp(BaseForm form) throws Exception{
		delete(((ExpensesInfoForm)form).getExpensesId());
		ExpensesInfo evo = (ExpensesInfo)queryForObject(((ExpensesInfoForm)form).getExpensesId());
		double lastTotalAmt = evo.getTotalAmt();
		String lastRegDate = evo.getRegDate().substring(0, 6);
		
//		BudgetDetailExample bexample = new BudgetDetailExample();
//		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(evo.getFeeKind());//部门
//		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
//		BudgetDetail brecord;
//		if(brecordlist.size()>0){
//			brecord = brecordlist.get(0);
//			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
//			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//		}
//		bexample = new BudgetDetailExample();
//		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(evo.getFeeKind());//project
//		brecordlist = budgetDetailDAO.selectByExample(bexample);
//		if(brecordlist.size()>0){
//			brecord = brecordlist.get(0);
//			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
//			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//		}
	}
	
	public void updateToUp(ExpensesInfo vo) throws Exception{
		if(vo.getExpensesId()==null||vo.getExpensesId()==0){
			long ExpensesId = KeyGenerator.getNextKey("TRAVEL_INFO");
			vo.setExpensesId(ExpensesId);
			vo.setRegDate(DateUtil.getDTStr());
			vo.setTotalAmt(0.0);
			insert(vo);
		}
		else{
			update(vo);
			
//			ExpensesInfo evo = (ExpensesInfo)queryForObject(vo.getExpensesId());
//			double lastTotalAmt = evo.getTotalAmt();
//			String lastRegDate = evo.getRegDate().substring(0, 6);
//			String lastfeekind = evo.getFeeKind();
//			
//			BudgetDetailExample bexample = new BudgetDetailExample();
//			bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(lastfeekind);//部门
//			List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
//			BudgetDetail brecord;
//			if(brecordlist.size()>0){
//				brecord = brecordlist.get(0);
//				brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
//				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//			}
//			bexample = new BudgetDetailExample();
//			bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(evo.getFeeKind());//部门
//			brecordlist = budgetDetailDAO.selectByExample(bexample);
//			if(brecordlist.size()>0){
//				brecord = brecordlist.get(0);
//				brecord.setFeeBal(brecord.getFeeBal()+evo.getTotalAmt());
//				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//			}
//			bexample = new BudgetDetailExample();
//			bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(lastfeekind);//project
//			brecordlist = budgetDetailDAO.selectByExample(bexample);
//			if(brecordlist.size()>0){
//				brecord = brecordlist.get(0);
//				brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
//				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//			}
//			bexample = new BudgetDetailExample();
//			bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(evo.getFeeKind());//project
//			brecordlist = budgetDetailDAO.selectByExample(bexample);
//			if(brecordlist.size()>0){
//				brecord = brecordlist.get(0);
//				brecord.setFeeBal(brecord.getFeeBal()+evo.getTotalAmt());
//				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
//			}
//			
		}
	}

	public void deleteExpensesDetail(ExpensesDetail expensesDetail,ExpensesInfo evo) throws Exception{
		//ExpensesDetail expensesDetail = (ExpensesDetail)queryForObject(((ExpensesDetailForm)form).getEdetailId());
		expensesDetailDao.deleteByPrimaryKey(expensesDetail.getEdetailId());
		String lastRegDate = evo.getRegDate().substring(0, 6);
		double lastAmt = expensesDetail.getAmt();

		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(expensesDetail.getFeeKind());//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastAmt+expensesDetail.getAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(expensesDetail.getFeeKind());//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastAmt+expensesDetail.getAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
	}
}

