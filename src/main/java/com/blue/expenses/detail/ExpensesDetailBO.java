package com.blue.expenses.detail;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abc.logic.IbatisBO;
import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.expenses.ExpensesInfo;
import com.blue.expenses.ExpensesInfoDAO;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public class ExpensesDetailBO {
	@Autowired
	private ExpensesDetailDAO expensesDetailDao;
	@Autowired
	private ExpensesInfoDAO expensesInfoDao;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	private Logger logger = LoggerFactory.getLogger(getClass());
	public void update(Object obj) throws Exception {
		expensesDetailDao.updateByPrimaryKeySelective((ExpensesDetail)obj);
	}

	public List<ExpensesDetail> getSelectedStaff(HttpServletRequest request,boolean sort,UserContext user)throws Exception{
		ExpensesDetailExample e  = new ExpensesDetailExample();
		ExpensesDetailExample.Criteria c = e.createCriteria();
		String expensesId = request.getParameter("expensesId");
		if(CheckUtil.isEmptry(expensesId))
			return new ArrayList<ExpensesDetail>();
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		return expensesDetailDao.selectByExample(e);
	}
	public void insert(Object obj) throws Exception {
		expensesDetailDao.insert((ExpensesDetail)obj);
	}

	public double getTotal(String expensesId)throws Exception{
		double total=0;
		ExpensesDetailExample e  = new ExpensesDetailExample();
		ExpensesDetailExample.Criteria c = e.createCriteria();
		if(CheckUtil.isEmptry(expensesId))
			return total;
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		List<ExpensesDetail> tlist = expensesDetailDao.selectByExample(e);
		for (ExpensesDetail travelDetail : tlist) {
			total += travelDetail.getAmt();
		}
		return total;
	}
	
	public Object queryForObject(Object obj) throws Exception {
		return expensesDetailDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	
	public List queryForList(Object obj) throws Exception {
		ExpensesDetailExample e = new ExpensesDetailExample();
		e.createCriteria().andExpensesIdEqualTo(((ExpensesDetail)obj).getExpensesId());
		return expensesDetailDao.selectByExample(e);
	}

	
	public void delete(Object obj) throws Exception {
		expensesDetailDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	
	public void updateExpensesDetail(ExpensesDetail vo,BaseForm form) throws Exception{	
		if(vo.getEdetailId()==null||vo.getEdetailId()==0){
			insert(vo);
		}else{
			update(vo);
		}
		ExpensesInfo evo = expensesInfoDao.selectByPrimaryKey(((ExpensesDetailForm)form).getExpensesId());
		double lastTotalAmt = evo.getTotalAmt();
		String lastRegDate = evo.getRegDate().substring(0, 6);
		evo.setExpensesId(vo.getExpensesId());
		evo.setTotalAmt(getTotal(String.valueOf(vo.getExpensesId())));
		expensesInfoDao.updateByPrimaryKeySelective(evo);
		
		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(vo.getFeeKind());//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+evo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(vo.getFeeKind());//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+evo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
	}
	
	
	public void deleteToUp(BaseForm form) throws Exception{
		ExpensesDetail expensesDetail = (ExpensesDetail)queryForObject(((ExpensesDetailForm)form).getEdetailId());
		delete(((ExpensesDetailForm)form).getEdetailId());
		ExpensesInfo evo = expensesInfoDao.selectByPrimaryKey(expensesDetail.getExpensesId());
		double lastTotalAmt = evo.getTotalAmt();
		String lastRegDate = evo.getRegDate().substring(0, 6);
		evo.setTotalAmt(getTotal(String.valueOf(expensesDetail.getExpensesId())));
		expensesInfoDao.updateByPrimaryKeySelective(evo);
		
		BudgetDetailExample bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("1").andDepartEqualTo(evo.getDept()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(expensesDetail.getFeeKind());//部门
		List<BudgetDetail> brecordlist = budgetDetailDAO.selectByExample(bexample);
		BudgetDetail brecord;
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+evo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
		bexample = new BudgetDetailExample();
		bexample.createCriteria().andBudgetKindEqualTo("0").andProjectNoEqualTo(evo.getProjectNo()).andYearmonthEqualTo(lastRegDate).andFeeKindEqualTo(expensesDetail.getFeeKind());//project
		brecordlist = budgetDetailDAO.selectByExample(bexample);
		if(brecordlist.size()>0){
			brecord = brecordlist.get(0);
			brecord.setFeeBal(brecord.getFeeBal()-lastTotalAmt+evo.getTotalAmt());
			budgetDetailDAO.updateByExampleSelective(brecord, bexample);
		}
	}
}

