package com.blue.report.costofproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.project.ProjectList;
import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class ProjectCostReport extends BaseReport {
	@Autowired
	private ProjectCostBO bo;
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(getClass());
			
	@Override
	protected void setValue(HashMap map, Object param) throws Exception {
		Map<String,Object> p = (Map)param;
		Tools tools = makeExcel(map);
		ProjectCostForm form = (ProjectCostForm)p.get("form");
		List<ProjectList> types = bo.getActiveProjects();
		
		CellAttributes ca = (CellAttributes)map.get("title");
		ca.setColSpan(types.size()+4);
		ca.setValue("项目工时明细报表("+form.getStartDate()+"-"+form.getEndDate()+")");
		tools.setCellValueUseCell(ca);
		
		setTypes(map, tools,types);
		Map<String,ProjectLoad> m = bo.getProjectLoad(form);
		logger.debug("begin generate detail------------");
		int row = setDetail(map, tools,m,types);
		logger.debug("end generate detail--------------");
		int last = setTotals(map, tools, m, row,types);
		logger.debug("end generate total--------------");
//		setProjectInfo(map,tools,types,last);
	}

	private void setProjectInfo(Map map,Tools tools, List<ProjectList> types, int last) {
		CellAttributes ca = (CellAttributes)map.get("projects");
		int rowIndex = last+1;
		int colIndex = ca.getColIndex();
		for(ProjectList p:types){
			int ci = colIndex;
			CellAttributes c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setValue(p.getProjectId());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci++);
			c.setColSpan(3);
			c.setValue(p.getProjectName());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(ci+2);
			c.setColSpan(types.size());
			c.setValue(p.getMemo());
			tools.setCellValueUseCell(c);
			
			rowIndex++;
		}
		
	}

	public int setTotals(HashMap map, Tools tools, Map<String, ProjectLoad> m, int row,List<ProjectList> types) {
		CellAttributes ca = (CellAttributes)map.get("totalInfo");
		ca.setRowIndex(row);
		tools.setCellValueUseCell(ca);
		
		ca = (CellAttributes)map.get("userCount");
		ca.setRowIndex(row);
		ca.setValue(m.keySet().size()+"");
		tools.setCellValueUseCell(ca);
		
		ca = (CellAttributes)map.get("costTotal");
		ca.setRowIndex(row);
		int colIndex = ca.getColIndex();
		tools.setCellValueUseCell(ca);
		
		Map<String,Double> totals = ProjectCostBO.getTotalMap(m);
		double total = 0;
		for(ProjectList s:types){
			ca = new CellAttributes();
			ca.setRowIndex(row);
			ca.setColIndex(++colIndex);
			ca.setValue(totals.get(s.getProjectId())==null?"0":totals.get(s.getProjectId())+"");
			tools.setCellValueUseCell(ca);
			total+= totals.get(s.getProjectId())==null?0:totals.get(s.getProjectId());
		}
		ca = new CellAttributes();
		ca.setRowIndex(row);
		ca.setColIndex(++colIndex);
		ca.setValue(total+"");
		tools.setCellValueUseCell(ca);
		
		int last = row+1;
		ca = (CellAttributes)map.get("NUL");
		ca.setRowIndex(last);
		ca.setValue("");
		tools.setCellValueUseCell(ca);
		
		ca = (CellAttributes)map.get("totalOfAll");
		ca.setRowIndex(last);
		tools.setCellValueUseCell(ca);
		
		ca = (CellAttributes)map.get("total");
		ca.setRowIndex(last);
		ca.setColSpan(types.size()+1);
		ca.setValue(total+"");
		ca.setBgColor("PALE_BLUE");
		tools.setCellValueUseCell(ca);
		
		return last+1;
	}

	public int setDetail(HashMap map, Tools tools,Map<String,ProjectLoad> m,List<ProjectList> types) throws Exception {
		
		CellAttributes ca = (CellAttributes)map.get("detail");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		
		Set<String> set = m.keySet();
		List<ProjectLoad> l = new ArrayList<ProjectLoad>();
		for(String s : set){
			l.add(m.get(s));
		}
		Collections.sort(l);
		
		for(ProjectLoad pl : l){
			String s = pl.getUser().getUser_id();
			int i = colIndex;
			CellAttributes c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(s).getUser().getUser_id());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(s).getUser().getUser_name());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(SingleDicMap.getDicItemVal(SingleDic.DEPART, m.get(s).getUser().getDepart_id()));
			tools.setCellValueUseCell(c);
			
			for(ProjectList t:types){
				c = new CellAttributes();
				c.setRowIndex(rowIndex);
				c.setColIndex(i++);
				c.setValue(m.get(s).getCostMap().get(t.getProjectId())==null?"0":m.get(s).getCostMap().get(t.getProjectId()).toString());
				tools.setCellValueUseCell(c);
			}
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(s).getTotal()+"");
			tools.setCellValueUseCell(c);
			
			rowIndex++;
		}
		return rowIndex;
	}

	public void setTypes(HashMap map, Tools tools,List<ProjectList> projects) {
		CellAttributes ca = (CellAttributes)map.get("types");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		for(ProjectList t :projects){
			CellAttributes c = new CellAttributes();
			c.setColIndex(colIndex);
			c.setRowIndex(rowIndex);
			c.setValue(t.getProjectId());
			c.setBgColor("PALE_BLUE");
			tools.setCellValueUseCell(c);
			CellAttributes name = new CellAttributes();
			name.setColIndex(colIndex);
			name.setRowIndex(rowIndex+1);
			name.setValue(t.getProjectName());
			name.setBgColor("PALE_BLUE");
			tools.setCellValueUseCell(name);
			CellAttributes pm = new CellAttributes();
			pm.setColIndex(colIndex);
			pm.setRowIndex(rowIndex+2);
			pm.setBgColor("PALE_BLUE");
			pm.setValue(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, t.getProjectManager()));
			tools.setCellValueUseCell(pm);
			colIndex++;
		}
		CellAttributes c = new CellAttributes();
		c.setRowIndex(rowIndex);
		c.setColIndex(colIndex);
		c.setRowSpan(2);
		c.setValue("合计");
		c.setBgColor("yellow");
		tools.setCellValueUseCell(c);
	}
}
