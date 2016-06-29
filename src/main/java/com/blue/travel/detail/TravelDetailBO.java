package com.blue.travel.detail;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.travel.TravelInfo;
import com.blue.travel.TravelInfoDAO;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;

public class TravelDetailBO {
	@Autowired
	private TravelDetailDAO travelDetailDao;
	@Autowired
	private TravelInfoDAO travelInfoDao;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	private Logger logger = LoggerFactory.getLogger(getClass());
	public void update(Object obj) throws Exception {
		travelDetailDao.updateByPrimaryKeySelective((TravelDetail)obj);
	}

	
	public List<TravelDetail> getSelectedStaff(HttpServletRequest request,boolean sort,UserContext user)throws Exception{
		TravelDetailExample e  = new TravelDetailExample();
		TravelDetailExample.Criteria c = e.createCriteria();
		String expensesId = request.getParameter("expensesId");
		if(CheckUtil.isEmptry(expensesId))
			return new ArrayList<TravelDetail>();
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		return travelDetailDao.selectByExample(e);
	}
	
	public double getTotal(String expensesId)throws Exception{
		return travelDetailDao.getTotal(expensesId);
	}
	
	public void insert(Object obj) throws Exception {
		travelDetailDao.insert((TravelDetail)obj);
	}

	
	public Object queryForObject(Object obj) throws Exception {
		return travelDetailDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	
	public List queryForList(Object obj) throws Exception {
		TravelDetailExample e = new TravelDetailExample();
		e.createCriteria().andExpensesIdEqualTo(((TravelDetail)obj).getExpensesId());
		return travelDetailDao.selectByExample(e);
	}

	
	public void delete(Object obj) throws Exception {
		travelDetailDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	
	public void deleteToUp(BaseForm form) throws Exception{
		TravelDetail travelDetail = (TravelDetail)queryForObject(((TravelDetailForm)form).getTdetailId());
		delete(((TravelDetailForm)form).getTdetailId());
		TravelInfo tvo = travelInfoDao.selectByPrimaryKey(travelDetail.getExpensesId());
		double lastTotalAmt = tvo.getTotalAmt();
		String lastRegDate = tvo.getRegDate().substring(0, 6);
		tvo.setTravelAmt(getTotal(String.valueOf(travelDetail.getExpensesId())));
		tvo.setTotalAmt(tvo.getTaxiAmt()+tvo.getAllowanceTotal()+tvo.getAllowanceTotal2()+tvo.getLodgingAmt()+tvo.getTravelAmt());
		travelInfoDao.updateByPrimaryKeySelective(tvo);
		
		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(tvo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+tvo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(tvo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+tvo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
	}
	
	public void updateToUp(TravelDetail vo,BaseForm form) throws Exception{
		if(vo.getTdetailId()==null||vo.getTdetailId()==0){
			insert(vo);
		}else{
			update(vo);
		}
		TravelInfo tvo = travelInfoDao.selectByPrimaryKey(((TravelDetailForm)form).getExpensesId());
		double lastTotalAmt = tvo.getTotalAmt();
		String lastRegDate = tvo.getRegDate().substring(0, 6);
		tvo.setTravelAmt(getTotal(String.valueOf(vo.getExpensesId())));
		tvo.setTotalAmt(tvo.getTaxiAmt()+tvo.getAllowanceTotal()+tvo.getAllowanceTotal2()+tvo.getLodgingAmt()+tvo.getTravelAmt());
		travelInfoDao.updateByPrimaryKeySelective(tvo);
		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(tvo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+tvo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(tvo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo("4");//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+tvo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
	}

}

