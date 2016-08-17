package com.yly.issue;

import java.util.HashMap;
import java.util.List;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
public class IssueAppInfoReport extends BaseReport {

	private MWsIssueBO mwsissuetbBO;
	public MWsIssueBO getMwsissuetbBO() {
		return mwsissuetbBO;
	}
	public void setMwsissuetbBO(MWsIssueBO mwsissuetbBO) {
		this.mwsissuetbBO = mwsissuetbBO;
	}
	
	protected void setValue(HashMap map, Object obj) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		try{
			List<MWsIssuetbForm> l = (List)mwsissuetbBO.getReport(obj);
			for(MWsIssuetbForm p : l){
				tools.setCell(rowIndex, colIndex++, p.getTaskCtrlNo());
				tools.setCell(rowIndex, colIndex++, p.getAppUnitId());
				tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(p.getUnitId())));
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(p.getProdId())));
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(p.getKeyType())));
				tools.setCell(rowIndex, colIndex++, p.getWorkSheetAmt());
				tools.setCell(rowIndex, colIndex++, p.getIssueDoneAmt());
				rowIndex++;
				colIndex=colIndexBegin;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
}
