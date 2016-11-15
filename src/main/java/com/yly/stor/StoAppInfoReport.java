package com.yly.stor;

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
public class StoAppInfoReport extends BaseReport {

	private StoAppInfoBO stoAppBO;


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
			List<Stoappinfo> l = (List)stoAppBO.getReport(obj);
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(Stoappinfo p : l){
				tools.setCell(rowIndex, colIndex++, p.getProjContNum());
				tools.setCell(rowIndex, colIndex++, ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, p.getManufacId()));
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, p.getProdId()));
				tools.setCell(rowIndex, colIndex++, p.getPhiTypeId()==null?"":SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, p.getPhiTypeId()));
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(p.getOperationType())));
				tools.setCell(rowIndex, colIndex++, p.getFormNo());
				tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
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
