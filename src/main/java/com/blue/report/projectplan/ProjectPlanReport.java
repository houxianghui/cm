package com.blue.report.projectplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.project.ProjectList;
import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.util.DateUtil;

public class ProjectPlanReport extends BaseReport {
	@Autowired
	private ProjectPlanBO bo;
	@Override
	protected void setValue(HashMap map, Object projectId) throws Exception {
		Tools tools = makeExcel(map);
		ProjectList pl = bo.getProjectInfo((String)projectId);
		if(pl == null){
			return;
		}
		tools.setCellValueUseString("projectName", pl.getProjectName());
		tools.setCellValueUseString("projectId", pl.getProjectId());
		tools.setCellValueUseString("setupDate", DateUtil.format(pl.getSetupDate()));
		tools.setCellValueUseString("manager", ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, pl.getProjectManager()));
		tools.setCellValueUseString("require", SingleDicMap.getDicItemVal(SingleDic.PROJECT_OWNING, pl.getOwning()));
		tools.setCellValueUseString("planStartDate", DateUtil.format(pl.getPlanStartDate()));
		tools.setCellValueUseString("planEndDate", DateUtil.format(pl.getPlanEndDate()));
		tools.setCellValueUseString("memo", pl.getMemo());
		
		CellAttributes ca = (CellAttributes)map.get("detail");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		
		Map<String,StepPlan> m = bo.getStepPlans((String)projectId);
		String[] steps = bo.steps;
		double total = 0;
		for(String s:steps){
			int ci = colIndex;
			StepPlan p = m.get(s);
			if(p == null){
				p = new StepPlan();
				p.setUsers(new ArrayList<String>());
			}
			CellAttributes c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setValue(DateUtil.format(p.getStartDate()));
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setValue(DateUtil.format(p.getEndDate()));
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setValue(p.getWorkLoad()+"");
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setValue(p.getManager());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			StringBuffer sb = new StringBuffer();
			for(String t:p.getUsers()){
				sb.append(t+" ");
			}
			c.setValue(sb.toString());
			c.setColSpan(3);
			tools.setCellValueUseCell(c);
			rowIndex++;
			total+= p.getWorkLoad();
		}
		tools.setCellValueUseString("total", total+"");
	}
	protected void setValueMultiSheet(HashMap map, Object obj,String i) throws Exception{
		;
	}	
}
