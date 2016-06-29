package com.blue.report.projectinfo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.Status;
import com.blue.enums.Steps;
import com.blue.mile.MileStone;
import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;

public class ProjectInfoReport extends BaseReport {
	@Autowired
	private ProjectInfoBO projectInfoBO;
	@Override
	protected void setValue(HashMap map, Object projectId) {
		Tools tools = makeExcel(map);
		
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin = colIndex;
		try {
			List<ProjectInfo> l = projectInfoBO.queryForList(null);
			for(ProjectInfo p : l){
				tools.setCell(rowIndex, colIndex++, p.getProject().getProjectId());
				tools.setCell(rowIndex, colIndex++, p.getProject().getProjectName());
				tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getProject().getProjectManager()));
				tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getProject().getRequireManager()));
				tools.setCell(rowIndex, colIndex++, Steps.valueOf(p.getProject().getStep()).getDesc());
				tools.setCell(rowIndex, colIndex++, Status.valueOf(p.getProject().getStat()).getDesc());
				tools.setCell(rowIndex, colIndex++, p.getProject().getSetupDate());
				tools.setCell(rowIndex, colIndex++, p.getProject().getPlanStartDate());
				tools.setCell(rowIndex, colIndex++, p.getProject().getPlanEndDate());
				tools.setCell(rowIndex, colIndex++, p.getDelayDays());
				tools.setCell(rowIndex, colIndex++, p.getPlanWorkLoad());
				tools.setCell(rowIndex, colIndex++, p.getRealWorkLoad());
				tools.setCell(rowIndex, colIndex++, p.getProject().getMemo());
				rowIndex++;
				colIndex = colIndexBegin;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
