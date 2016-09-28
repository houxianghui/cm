package com.yly.stor;

import java.util.HashMap;
import java.util.List;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.exception.MessageException;
 
public class StockReport extends BaseReport {

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
			List<Stoappinfo> l = (List)stoAppBO.getStockReport(obj);
			
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(Stoappinfo p : l){
				tools.setCell(rowIndex, colIndex++, p.getCurrDate());
				tools.setCell(rowIndex, colIndex++, p.getProjName());
				if(p.getOperationType()>20){
					tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
					tools.setCell(rowIndex, colIndex++, p.getUnitPrice());
					tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt()*p.getUnitPrice());
				}else{
					tools.setCell(rowIndex, colIndex++, "");
					tools.setCell(rowIndex, colIndex++, "");
					tools.setCell(rowIndex, colIndex++, "");
				}
				if(p.getOperationType()<20){
					tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
					tools.setCell(rowIndex, colIndex++, p.getUnitPrice());
					tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt()*p.getUnitPrice());
				}else{
					tools.setCell(rowIndex, colIndex++, "");
					tools.setCell(rowIndex, colIndex++, "");
					tools.setCell(rowIndex, colIndex++, "");
				}
				tools.setCell(rowIndex, colIndex++, "");
				tools.setCell(rowIndex, colIndex++, "");
				tools.setCell(rowIndex, colIndex++, "");
				rowIndex++;
				colIndex=colIndexBegin;
			}
		}finally{
			;
		}
	}
		
}
