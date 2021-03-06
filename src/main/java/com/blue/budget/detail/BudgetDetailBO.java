package com.blue.budget.detail;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.budget.detail.BudgetDetailExample.Criteria;
import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.blue.userdepartauth.UserDepartAuth;
import com.blue.userdepartauth.UserDepartAuthBO;
import com.blue.userdepartauth.UserDepartAuthDAO;
import com.blue.userdepartauth.UserDepartAuthExample;
import com.eis.base.BaseBO;
import com.eis.base.BaseDAO;
import com.eis.base.BaseForm;
import com.eis.base.BaseVO;
import com.eis.base.IbatisBaseBO;
import com.eis.base.PageObject;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;

public class BudgetDetailBO extends BaseBO{
	@Autowired
	private BudgetDetailDAO budgetDetailDao;
	@Autowired
	private UserDepartAuthDAO userDepartAuthDAO;
	@Autowired
	private ProjectListDAO projectListDAO;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public boolean queryForAll(long budgetID) throws Exception {
		BudgetDetailExample e = new BudgetDetailExample();
		e.createCriteria().andBudgetIdEqualTo(budgetID);
		List<BudgetDetail> detailList = budgetDetailDao.selectByExample(e);
		for(BudgetDetail bd :detailList){
			if(bd.getFeeBal()>0){
				return false;
			}
		}
		return true;
	}
	
	public List queryForReport(Object obj, UserContext user) throws Exception {
		BudgetDetailExample e = new BudgetDetailExample();
		Criteria ca = e.createCriteria();
		if(CheckUtil.isEmptry(((BudgetDetailForm)obj).getBudgetKind())){
			return new ArrayList();
		}else{
			ca.andBudgetKindEqualTo(((BudgetDetailForm)obj).getBudgetKind());			
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getFeeKind_f())){
			ca.andFeeKindEqualTo(((BudgetDetailForm)obj).getFeeKind_f());
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getProject_f())){
			ca.andProjectNoLike(((BudgetDetailForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getDepart_f())){
			ca.andDepartEqualTo(((BudgetDetailForm)obj).getDepart_f());
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_from())){
			ca.andYearmonthGreaterThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_from().substring(0, 6));
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_to())){
			ca.andYearmonthLessThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_to().substring(0, 6));
		}
		return budgetDetailDao.selectByExample(e);
	}
	
	public List selectByReportProject(Object obj, UserContext user) throws Exception {
		BudgetDetailExample e = new BudgetDetailExample();
		Criteria ca = e.createCriteria();
		ca.andBudgetKindEqualTo("0");
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_from())){
			ca.andYearmonthGreaterThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_from().substring(0, 6));
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_to())){
			ca.andYearmonthLessThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_to().substring(0, 6));
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getProject_f())){
			ca.andProjectNoEqualTo(((BudgetDetailForm)obj).getProject_f());
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getFeeKind_f())){
			ca.andFeeKindEqualTo(((BudgetDetailForm)obj).getFeeKind_f());
		}
		return budgetDetailDao.selectByReportProject(e);
	}
	
	public List selectByReportDept(Object obj, UserContext user) throws Exception {
		BudgetDetailExample e = new BudgetDetailExample();
		Criteria ca = e.createCriteria();
		ca.andBudgetKindEqualTo("1");
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_from())){
			ca.andYearmonthGreaterThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_from().substring(0, 6));
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getRegDate_to())){
			ca.andYearmonthLessThanOrEqualTo(((BudgetDetailForm)obj).getRegDate_to().substring(0, 6));
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getDepart_f())){
			ca.andDepartEqualTo(((BudgetDetailForm)obj).getDepart_f());
		}
		if(!CheckUtil.isEmptry(((BudgetDetailForm)obj).getFeeKind_f())){
			ca.andFeeKindEqualTo(((BudgetDetailForm)obj).getFeeKind_f());
		}
		return budgetDetailDao.selectByReportDept(e);
	}
	

	public void add(BaseVO vo, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(BaseVO vo, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(BaseVO vo, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List queryList(UserContext user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List queryList(PageObject page, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(PageObject page, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(UserContext user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseVO retrieve(String whereClause, UserContext user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

