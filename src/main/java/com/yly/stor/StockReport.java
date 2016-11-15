package com.yly.stor;

import java.util.HashMap;
import java.util.List;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Config;
import com.blue.report.base.ExcelTools;
import com.blue.report.base.Tools;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
 
public class StockReport extends BaseReport {

	private StoAppInfoBO stoAppBO;
	 
	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}


	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}


	protected void setValueMultiSheet(HashMap map, Object obj,String i) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		try{
			List<Stoappinfo> l = (List)stoAppBO.getStockMap(obj);
			
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(Stoappinfo p : l){
				if(p.getProdId().equals(i)){
					if(p.getOperationType()==94 ||p.getOperationType()==92){
						tools.setCell(rowIndex, colIndex++, p.getCurrDate(),-1);
						tools.setCell(rowIndex, colIndex++, p.getProjName()+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(p.getOperationType())),-1);
					}else{
						tools.setCell(rowIndex, colIndex++, p.getCurrDate());
						tools.setCell(rowIndex, colIndex++, p.getProjName()+SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(p.getOperationType())));	
					}
					if(p.getOperationType()<20||p.getOperationType()==61 ||p.getOperationType()==92){
						tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
						tools.setCell(rowIndex, colIndex++, p.getUnitPrice());
						tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt()*p.getUnitPrice());
					}else{
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
					}
					if(p.getOperationType()>20 && p.getOperationType()!=61 && p.getOperationType()!=92){
						tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
						tools.setCell(rowIndex, colIndex++, p.getUnitPrice()/p.getPurchaseAmt());
						tools.setCell(rowIndex, colIndex++, p.getUnitPrice());
					}else{
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
					}
					if(p.getOperationType()<20||p.getOperationType()==61 ||p.getOperationType()==92){
						tools.setCell(rowIndex, colIndex++, p.getCurrPeriodAmt());
						tools.setCell(rowIndex, colIndex++, p.getUnitPrice());
						tools.setCell(rowIndex, colIndex++, p.getCurrPeriodAmt()*p.getUnitPrice());
					}else{
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
						tools.setCell(rowIndex, colIndex++, "");
					}
					tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, p.getProdId()));
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
