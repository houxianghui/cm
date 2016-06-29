package com.blue.travel;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts.taglib.logic.ForwardTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.abc.logic.IbatisBO;
import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.blue.taxi.TaxiInfoDAO;
import com.blue.taxi.TaxiInfoExample;
import com.blue.travel.TravelInfoExample.Criteria;
import com.blue.travel.detail.TravelDetail;
import com.blue.travel.detail.TravelDetailDAO;
import com.blue.travel.detail.TravelDetailExample;
import com.blue.userdepartauth.UserDepartAuth;
import com.blue.userdepartauth.UserDepartAuthBO;
import com.blue.userdepartauth.UserDepartAuthDAO;
import com.blue.userdepartauth.UserDepartAuthExample;
import com.eis.base.BaseForm;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;


public class TravelInfoBO extends IbatisBO {
	@Autowired
	private TravelInfoDAO travelInfoDao;
	@Autowired
	private TravelDetailDAO travelDetailDao;
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
		travelInfoDao.updateByPrimaryKeySelective((TravelInfo)obj);
	}

	@Override
	public void insert(Object obj) throws Exception {
		travelInfoDao.insert((TravelInfo)obj);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return travelInfoDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}


	public List queryForList(Object obj, UserContext user) throws Exception {
		TravelInfoExample e = new TravelInfoExample();
		Criteria ca = e.createCriteria();
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getProject_f())){
			ca.andProjectNoLike(((TravelInfoForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getDepart_f())){
			ca.andDeptEqualTo(((TravelInfoForm)obj).getDepart_f());
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
		return travelInfoDao.selectByExample(e);
	}

	public List queryForReport(Object obj, UserContext user) throws Exception {
		TravelInfoExample e = new TravelInfoExample();
		Criteria ca = e.createCriteria();
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getProject_f())){
			ca.andProjectNoLike(((TravelInfoForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getDepart_f())){
			ca.andDeptEqualTo(((TravelInfoForm)obj).getDepart_f());
		}
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getRegDate_from())){
			ca.andRegDateGreaterThanOrEqualTo(((TravelInfoForm)obj).getRegDate_from());
		}
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getRegDate_to())){
			ca.andRegDateLessThanOrEqualTo(((TravelInfoForm)obj).getRegDate_to());
		}
		if(!CheckUtil.isEmptry(((TravelInfoForm)obj).getUserId())){
			ca.andCurrUserEqualTo(((TravelInfoForm)obj).getUserId());
		}
		e.setOrderByClause(" EXPENSES_ID DESC");
		return travelInfoDao.selectByExample(e);
	}
	@Override
	public void delete(Object obj) throws Exception {
		travelInfoDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
		TravelDetailExample example = new TravelDetailExample();
		example.createCriteria().andExpensesIdEqualTo(Long.parseLong(obj.toString()));
		travelDetailDao.deleteByExample(example);
		TaxiInfoExample exle = new TaxiInfoExample();
		exle.createCriteria().andExpensesIdEqualTo(Long.parseLong(obj.toString()));
		taxiInfoDao.deleteByExample(exle);
	}
	
	public void transInsertDetail(List listjson,Long expensesId){
		TravelDetailExample e = new TravelDetailExample();
		e.createCriteria().andExpensesIdEqualTo(expensesId);
		travelDetailDao.deleteByExample(e);
		for (int i = 0; i < listjson.size(); i++) {
			travelDetailDao.insert((TravelDetail)listjson.get(i));			
		}
		
	}
	public void setTravelInfoDao(TravelInfoDAO dao) {
		this.travelInfoDao = dao;
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void delereToUp(BaseForm form) throws Exception{
		TravelInfo vo = (TravelInfo)queryForObject(((TravelInfoForm)form).getExpensesId());
		double lastTotalAmt = vo.getTotalAmt();
		String lastRegDate = vo.getRegDate().substring(0, 6);
		
		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(vo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(vo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt);
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		delete(((TravelInfoForm)form).getExpensesId());
	}
	
	public void updateToUp(TravelInfo vo,UserContext user) throws Exception{
		//城市字典小于等于50的为一级城市
		if(Integer.parseInt(vo.getCity())<=50){
			vo.setAllowancePerday(100.0);
			vo.setAllowancePerday2(70.0);
		}
		//其他城市
		else{
			vo.setAllowancePerday(60.0);
			vo.setAllowancePerday2(42.0);
		}
		//出差天数
		int days = Integer.parseInt(vo.getDateTo())-Integer.parseInt(vo.getDateFrom())+1;
		//按天数计算补贴
		if(days<0){
			throw new MessageException("日期填写有误");
		}else if(days>30){
			vo.setAllowanceDays(30);
			vo.setAllowanceDays2(days-30);
		}else{
			vo.setAllowanceDays(days);
			vo.setAllowanceDays2(0);
		}
		if(vo.getAllowancePerday()!=null&&vo.getAllowanceDays()!=null)
			vo.setAllowanceTotal(vo.getAllowancePerday()*vo.getAllowanceDays());
		else
			vo.setAllowanceTotal(0.0);
		if(vo.getAllowancePerday2()!=null&&vo.getAllowanceDays2()!=null)
			vo.setAllowanceTotal2(vo.getAllowancePerday2()*vo.getAllowanceDays2());
		else
			vo.setAllowanceTotal2(0.0);
		vo.setCurrUser(user.getUserID());
		//金额类赋值
		if(vo.getExpensesId()==null||vo.getExpensesId()==0)
			vo.setTravelAmt(0.0);
		else{
			vo.setTravelAmt(travelDetailDao.getTotal(String.valueOf(vo.getExpensesId())));
			vo.setTaxiAmt(taxiInfoDao.getTotal(String.valueOf(vo.getExpensesId())));
			vo.setTaxiInvoiceno(taxiInfoDao.getInvoiceNoSum(String.valueOf(vo.getExpensesId())));
		}
		double totalAmt = vo.getAllowanceTotal()+vo.getAllowanceTotal2()+vo.getLodgingAmt()+vo.getTaxiAmt()+vo.getTravelAmt();
		if(vo.getExpensesId()==null||vo.getExpensesId()==0){
			long ExpensesId = KeyGenerator.getNextKey("TRAVEL_INFO");
			vo.setTotalAmt(totalAmt);
			vo.setExpensesId(ExpensesId);
			vo.setRegDate(DateUtil.getDTStr());
			String lastRegDate = vo.getRegDate().substring(0, 6);
			insert(vo);
			BudgetDetailExample bexample = new BudgetDetailExample();
			bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(vo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//部门
			List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
			BudgetDetail brecord;
			if(brecordlist.size()>0){
				brecord = brecordlist.get(0);
				brecord.setFeeBal(brecord.getFeeBal()+vo.getTotalAmt());
				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
			}
			bexample = new BudgetDetailExample();
			bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(vo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//project
			brecordlist = budgetDetailDAO.selectByExample(bexample);
			if(brecordlist.size()>0){
				brecord = brecordlist.get(0);
				brecord.setFeeBal(brecord.getFeeBal()+vo.getTotalAmt());
				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
			}
		}else{
			TravelInfo tvo = (TravelInfo)queryForObject(vo.getExpensesId());
			double lastTotalAmt = tvo.getTotalAmt();
			vo.setTotalAmt(totalAmt);
			String lastRegDate = tvo.getRegDate().substring(0, 6);
			update(vo);
			
			BudgetDetailExample bexample = new BudgetDetailExample();
			bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(vo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//部门
			List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
			BudgetDetail brecord;
			if(brecordlist.size()>0){
				brecord = brecordlist.get(0);
				brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+vo.getTotalAmt());
				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
			}
			bexample = new BudgetDetailExample();
			bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(vo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//project
			brecordlist = budgetDetailDAO.selectByExample(bexample);
			if(brecordlist.size()>0){
				brecord = brecordlist.get(0);
				brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+vo.getTotalAmt());
				budgetDetailDAO.updateByExampleSelective(brecord, bexample);
			}
		}
	}

	
}

