package com.blue.report.costoftype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.util.StringUtil;
public class WorkLoadReport extends BaseReport {
	@Autowired
	private WorkLoadBO workLoadBO;
	
	@Override
	protected void setValue(HashMap map, Object param) throws Exception {
		Map<String,Object> p = (Map)param;
		Tools tools = makeExcel(map);
		WorkLoadForm form = (WorkLoadForm)p.get("form");
		String[] types = StringUtil.getTypes();
		
		CellAttributes ca = (CellAttributes)map.get("title");
		ca.setColSpan(types.length+4);
		ca.setValue("工时统计报表("+form.getStartDate()+"-"+form.getEndDate()+")");
		tools.setCellValueUseCell(ca);
		
		setTypes(map, tools);
		Map<String,WorkLoad> m = workLoadBO.getWorkLoadByType(form);
		int row = setDetail(map, tools,m);
		
		setTotals(map, tools, m, row);
	}

	public void setTotals(HashMap map, Tools tools, Map<String, WorkLoad> m, int row) {
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
		
		Map<String,Double> totals = WorkLoadBO.getTotalMap(m);
		String[] types = StringUtil.getTypes();
		double total = 0;
		for(String s:types){
			ca = new CellAttributes();
			ca.setRowIndex(row);
			ca.setColIndex(++colIndex);
			ca.setValue(totals.get(s)==null?"0":totals.get(s)+"");
			tools.setCellValueUseCell(ca);
			total+= totals.get(s)==null?0:totals.get(s);
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
		ca.setColSpan(types.length+1);
		ca.setValue(total+"");
		ca.setBgColor("PALE_BLUE");
		tools.setCellValueUseCell(ca);
	}

	public int setDetail(HashMap map, Tools tools,Map<String,WorkLoad> m) throws Exception {
		
		CellAttributes ca = (CellAttributes)map.get("detail");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		
		Set<String> set = m.keySet();
		List<WorkLoad> l = new ArrayList<WorkLoad>();
		for(String s: set){
			l.add(m.get(s));
		}
		Collections.sort(l);
		
		String[] types = StringUtil.getTypes();
		for(WorkLoad wl : l){
			int i = colIndex;
			CellAttributes c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(wl.getUser().getUser_id()).getUser().getUser_id());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(wl.getUser().getUser_id()).getUser().getUser_name());
			tools.setCellValueUseCell(c);
			
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(SingleDicMap.getDicItemVal(SingleDic.DEPART, m.get(wl.getUser().getUser_id()).getUser().getDepart_id()));
			tools.setCellValueUseCell(c);
			
			for(String t:types){
				c = new CellAttributes();
				c.setRowIndex(rowIndex);
				c.setColIndex(i++);
				c.setValue(m.get(wl.getUser().getUser_id()).getWorkCost().get(t)==null?"0":m.get(wl.getUser().getUser_id()).getWorkCost().get(t).toString());
				tools.setCellValueUseCell(c);
			}
			c = new CellAttributes();
			c.setRowIndex(rowIndex);
			c.setColIndex(i++);
			c.setValue(m.get(wl.getUser().getUser_id()).getTotal()+"");
			tools.setCellValueUseCell(c);
			
			rowIndex++;
		}
		return rowIndex;
	}

	public void setTypes(HashMap map, Tools tools) {
		String[] types = StringUtil.getTypesDesc();
		CellAttributes ca = (CellAttributes)map.get("types");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		for(String t :types){
			CellAttributes c = new CellAttributes();
			c.setColIndex(colIndex++);
			c.setRowIndex(rowIndex);
			c.setValue(t);
			c.setBgColor("PALE_BLUE");
			tools.setCellValueUseCell(c);
		}
		CellAttributes c = new CellAttributes();
		c.setRowIndex(rowIndex);
		c.setColIndex(colIndex);
		c.setValue("合计");
		c.setBgColor("yellow");
		tools.setCellValueUseCell(c);
	}

}
