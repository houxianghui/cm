package com.blue.report.weekly;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.Roles;
import com.blue.project.riskrecord.RiskRecord;
import com.blue.project.riskrecord.RiskRecordBO;
import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.eis.util.DateUtil;
public class WeeklyReport extends BaseReport {
	@Autowired
	private UserBO userBO;
	@Autowired
	private RiskRecordBO riskRecordBO;
	@Autowired
	private WeeklyReportBO weeklyReportBO;
	@Override
	protected void setValue(HashMap map, Object obj) throws Exception{
		Tools tools = makeExcel(map);
		WeeklyReportForm form = (WeeklyReportForm)obj;
		tools.setCellValueUseString("reportStartDate", DateUtil.getMondayStr(form.getDate()));
		tools.setCellValueUseString("reportEndDate", DateUtil.getSundayStr(form.getDate()));
//		tools.setCellValueUseString("startDate", DateUtil.getMondayStr(form.getDate()));
//		tools.setCellValueUseString("endDate", DateUtil.getSundayStr(form.getDate()));
		int weeklyLastIndex = setWeeklyReport(map, tools,form);
		int planLastIndex = setPlan(map,tools,weeklyLastIndex,form);
//		int staffLastIndex = setStaff(map,tools,weeklyLastIndex);
		setRisk(map,tools,planLastIndex,form);
	}
	
	private int setRisk(HashMap map, Tools tools, int staffLastIndex,WeeklyReportForm form) throws Exception{
		CellAttributes ca = (CellAttributes)map.get("solution");
		int rowIndex = staffLastIndex;
		int colIndex = ca.getColIndex();
		
		ca.setRowIndex(rowIndex);
		tools.setCellValueUseCell(ca);
		
		List<CellAttributes> children = ca.getChildren();
		for(CellAttributes c :children){
			int row = rowIndex+c.getRowOffSet();
			c.setRowIndex(row);
			tools.setCellValueUseCell(c);
		}
		
		List<RiskRecord> l = (List)riskRecordBO.queryForListByDeparts(form.getIds());
		int r = rowIndex+2;
		
		for(RiskRecord risk : l){
			int c = colIndex;
			tools.setCell(r, c++, 1, 1, risk.getProjectId());
			tools.setCell(r, c++, 1, 2, risk.getRiskName(),null,null,0,false,true);
			c++;
			tools.setCell(r, c++, 1, 1, risk.getSolution(),null,null,0,false,true);
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, risk.getInputDate());
			tools.setCell(r, c++, 1, 1, risk.getMemo());
			r++;
		}
		return r;
	}

	/**
	 * @deprecated √ª”√¡À
	 * @param map
	 * @param tools
	 * @param planLastIndex
	 * @return
	 * @throws Exception
	 */
	private int setStaff(HashMap map, Tools tools, int planLastIndex)throws Exception {
		CellAttributes ca = (CellAttributes)map.get("staff");
		int rowIndex = planLastIndex;
		int colIndex = ca.getColIndex();
		
		ca.setRowIndex(rowIndex);
		tools.setCellValueUseCell(ca);
		
		List<CellAttributes> children = ca.getChildren();
		int rowSpan = 0;
		for(CellAttributes c :children){
			if(c.getRowSpan()>rowSpan){
				rowSpan = c.getRowSpan();
			}
			int row = rowIndex+c.getRowOffSet();
			c.setRowIndex(row);
			tools.setCellValueUseCell(c);
		}
		
		List<UserVO> l = userBO.getUsersByRole("", Roles.stuff);
		int r = rowIndex+2;
		for(UserVO w : l){
			int c = colIndex;
			tools.setCell(r, c++, 1, 1, w.getUser_name());
			tools.setCell(r, c++, 1, 1, 5+"");
			tools.setCell(r, c++, 1, 1, 5+"");
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, "");
			tools.setCell(r, c++, 1, 1, "");
			r++;
		}
		return r;
	}

	private int setPlan(HashMap map, Tools tools, int weeklyLastIndex,WeeklyReportForm form)throws Exception {
		CellAttributes ca = (CellAttributes)map.get("plan");
		int rowIndex = weeklyLastIndex;
		int colIndex = ca.getColIndex();
		
		ca.setRowIndex(rowIndex);
		tools.setCellValueUseCell(ca);
		
		List<CellAttributes> children = ca.getChildren();
		for(CellAttributes c:children){
			int row = rowIndex+c.getRowOffSet();
			c.setRowIndex(row);
			tools.setCellValueUseCell(c);
		}
		List<Weekly> l = weeklyReportBO.getPlan(form);
		int r = rowIndex+2;
		for(Weekly w : l){
			int c = colIndex;
			tools.setCell(r, c++, 1, 2, w.getProjectName());
			c++;
			tools.setCell(r, c++, 1, 1, w.getStep());
			tools.setCell(r, c++, 1, 1, w.getDating());
			tools.setCell(r, c++, 1, 3, w.getMemo());
			r++;
		}
		return r;
	}

	private int setWeeklyReport(HashMap map,Tools tools,WeeklyReportForm form)throws Exception{
		CellAttributes ca = (CellAttributes)map.get("report");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		List<Weekly> l = weeklyReportBO.getReport(form);
		int r = rowIndex;
		for(Weekly w : l){
			int c = colIndex;
			tools.setCell(r, c++, 1, 2, w.getProjectName());
			c++;
			tools.setCell(r, c++, 1, 1, w.getStep());
			tools.setCell(r, c++, 1, 1, w.getDating());
			tools.setCell(r, c++, 1, 3, w.getMemo());
			r++;
		}
		return r;
	}
}
