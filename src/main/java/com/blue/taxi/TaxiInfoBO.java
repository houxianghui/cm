package com.blue.taxi;


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

public class TaxiInfoBO {
	@Autowired
	private TaxiInfoDAO taxiInfoDao;
	@Autowired
	private TravelInfoDAO travelInfoDao;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;

	private Logger logger = LoggerFactory.getLogger(getClass());
	public void update(Object obj) throws Exception {
		taxiInfoDao.updateByPrimaryKeySelective((TaxiInfo)obj);
	}

	public List<TaxiInfo> getSelectedStaff(HttpServletRequest request,boolean sort,UserContext user)throws Exception{
		TaxiInfoExample e  = new TaxiInfoExample();
		TaxiInfoExample.Criteria c = e.createCriteria();
		String expensesId = request.getParameter("expensesId");
		if(CheckUtil.isEmptry(expensesId))
			return new ArrayList<TaxiInfo>();
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		return taxiInfoDao.selectByExample(e);
	}
	public void insert(Object obj) throws Exception {
		taxiInfoDao.insert((TaxiInfo)obj);
	}

	public double getTotal(String expensesId)throws Exception{
		double total=0;
		TaxiInfoExample e  = new TaxiInfoExample();
		TaxiInfoExample.Criteria c = e.createCriteria();
		if(CheckUtil.isEmptry(expensesId))
			return total;
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		List<TaxiInfo> tlist = taxiInfoDao.selectByExample(e);
		for (TaxiInfo taxiInfo : tlist) {
			total += taxiInfo.getTaxiAmt();
		}
		return total;
	}
	public Object queryForObject(Object obj) throws Exception {
		return taxiInfoDao.selectByPrimaryKey(Integer.parseInt(obj.toString()));
	}

	
	public List queryForList(Object obj) throws Exception {
		TaxiInfoExample e = new TaxiInfoExample();
		if(((TaxiInfo)obj).getExpensesId()!=null&&((TaxiInfo)obj).getExpensesId()!=0){
			e.createCriteria().andExpensesIdEqualTo(((TaxiInfo)obj).getExpensesId());
		}
		return taxiInfoDao.selectByExample(e);
	}

	
	public void delete(Object obj) throws Exception {
		taxiInfoDao.deleteByPrimaryKey(Integer.parseInt(obj.toString()));
	}

	
	public void deleteToUp(BaseForm form) throws Exception {
		TaxiInfo taxiInfo = (TaxiInfo)queryForObject(((TaxiInfoForm)form).getTaxiId());
		delete(((TaxiInfoForm)form).getTaxiId());
		TravelInfo tvo = travelInfoDao.selectByPrimaryKey(taxiInfo.getExpensesId());
		if(tvo!=null){
			double lastTotalAmt = tvo.getTotalAmt();
			String lastRegDate = tvo.getRegDate().substring(0, 6);
			tvo.setTaxiInvoiceno(taxiInfoDao.getInvoiceNoSum(String.valueOf(taxiInfo.getExpensesId())));
			tvo.setTaxiAmt(getTotal(String.valueOf(taxiInfo.getExpensesId())));
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
	
	public void updateToUp(BaseForm form,TaxiInfo vo) throws Exception{
		if(vo.getTaxiId()==null||vo.getTaxiId()==0){
			insert(vo);
		}else{
			update(vo);
		}
		TravelInfo tvo = travelInfoDao.selectByPrimaryKey(vo.getExpensesId());
		if(tvo!=null){
			tvo.setTaxiInvoiceno(taxiInfoDao.getInvoiceNoSum(String.valueOf(vo.getExpensesId())));
			double lastTotalAmt = tvo.getTotalAmt();
			String lastRegDate = tvo.getRegDate().substring(0, 6);
			tvo.setTaxiAmt(getTotal(String.valueOf(vo.getExpensesId())));
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

}

