package com.yly.exstore;

import java.util.HashMap;
import java.util.List;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.yly.stor.Stoappinfo;
public class ExStoreInfoReport extends BaseReport {

	private StoproductBO stoproductBO;
	
	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

//	protected void setValue(HashMap map, Object obj) throws Exception{
//		Tools tools = makeExcel(map);
//		CellAttributes ca = (CellAttributes)map.get("list");
//		int rowIndex = ca.getRowIndex();
//		int colIndex = ca.getColIndex();
//		int colIndexBegin=colIndex;
//		try{
//			List<StoproductForm> l = (List)stoproductBO.getReport(obj);
//			if(l.size()<1){
//				throw new MessageException("不存在数据");
//			}
//			for(StoproductForm p : l){
//				tools.setCell(rowIndex, colIndex++, p.getUnitId());
//				tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(p.getExUnitId())));
//				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(p.getProdId())));
//				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(p.getKeyType())));
//				tools.setCell(rowIndex, colIndex++, p.getIssueAmt());
//				tools.setCell(rowIndex, colIndex++, p.getExAmt());
//				rowIndex++;
//				colIndex=colIndexBegin;
//			}
//		}finally{
//			;
//		}
//	}
	protected void setValueMultiSheet(HashMap map, Object obj,String i) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		try{
			List<StoproductForm> l = (List)stoproductBO.getReport(obj);
			
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(StoproductForm p : l){
				if(p.getProdId().equals(i)){
					tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(p.getExUnitId())));
					tools.setCell(rowIndex, colIndex++, p.getProdId().equals("5")?"互通卡":SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(p.getProdId())));
					tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(p.getKeyType())));
					tools.setCell(rowIndex, colIndex++, p.getIssueAmt());
					tools.setCell(rowIndex, colIndex++, p.getExAmt());
					rowIndex++;
					colIndex=colIndexBegin;
				}
			}

		}finally{
			;
		}
	}
	protected void setValue(HashMap map, Object obj) throws Exception{
		;
	}
}
