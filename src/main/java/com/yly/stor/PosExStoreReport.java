package com.yly.stor;

import java.util.HashMap;
import java.util.List;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.exception.MessageException;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
public class PosExStoreReport extends BaseReport {

	private StoAppInfoBO stoAppBO;
	private IssueappBO issueappBO;

	public IssueappBO getIssueappBO() {
		return issueappBO;
	}


	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}


	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}


	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}


	protected void setValue(HashMap map, Object obj) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		try{
			List<Stoappinfo> l = (List)stoAppBO.getPosChargeBackReport(obj);
			
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(Stoappinfo p : l){
				Issueapp app=issueappBO.queryPosExStoreInfo(p.getFormNo());
				Issueapp backApp=issueappBO.queryPosBackInfo(app.getOAappNo());
				tools.setCell(rowIndex, colIndex++, app.getAppNo());
				tools.setCell(rowIndex, colIndex++, app.getTaskAmt());
				tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
				if(backApp!=null && backApp.getTaskAmt()>0){
					tools.setCell(rowIndex, colIndex++, backApp.getTaskAmt());
				}else{
					tools.setCell(rowIndex, colIndex++, "");
				}
				tools.setCell(rowIndex, colIndex++, p.getProjName());
				rowIndex++;
				colIndex=colIndexBegin;
			}
		}finally{
			;
		}
	}
	protected void setValueMultiSheet(HashMap map, Object obj,String i) throws Exception{
		;
	}		
}
