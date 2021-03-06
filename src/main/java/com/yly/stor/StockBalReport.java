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
import com.yly.exstore.StoproductForm;
 
public class StockBalReport extends BaseReport {

	private StoAppInfoBO stoAppBO;
	 
	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}


	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}


//	protected void setValue(HashMap map, Object obj) throws Exception{
//		Tools tools = makeExcel(map);
//		CellAttributes ca = (CellAttributes)map.get("list");
//		int rowIndex = ca.getRowIndex();
//		int colIndex = ca.getColIndex();
//		int colIndexBegin=colIndex;
//	
//		try{
//			List<Stoappinfo> inl = (List)stoAppBO.getInStockBalReport(obj);
//			List<Stoappinfo> outl = (List)stoAppBO.getOutStockBalReport(obj);
//			List<Stoappinfo> issuel = (List)stoAppBO.getIssueStockBalReport(obj);			
//			List<Stoappinfo> backl = (List)stoAppBO.getBackStockBalReport(obj);
//			List<Stoappinfo> disl = (List)stoAppBO.getDisStockBalReport(obj);
//			if(inl.size()<1 && outl.size()<1 && backl.size()<1 && disl.size()<1 &&issuel.size()<1){
//				throw new MessageException("不存在数据");
//			}
//			for(int i=1;i<7;i++){
//				boolean flag=false;
//				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(i)));
//				if(inl.size()>0){
//					for(Stoappinfo p : inl){
//						if(p.getProdId().equals(String.valueOf(i))){
//							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
//							tools.setCell(rowIndex, colIndex++, p.getCurrPeriodAmt());
//							flag=true;
//							break;
//						}else {
//							continue;
//						}
//							
//					}
//					if(!flag){
//						tools.setCell(rowIndex, colIndex++, 0);
//						tools.setCell(rowIndex, colIndex++, 0);
//					}
//				}else{
//					tools.setCell(rowIndex, colIndex++, 0);
//					tools.setCell(rowIndex, colIndex++, 0);
//				}
//				if(outl.size()>0){
//					for(Stoappinfo p : outl){
//						if(p.getProdId().equals(String.valueOf(i))){
//							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
//							flag=true;
//							break;
//						}else {
//							flag=false;
//							continue;
//						}
//							
//					}
//					if(!flag){
//						tools.setCell(rowIndex, colIndex++, 0);
//					}
//				}else{
//					tools.setCell(rowIndex, colIndex++, 0);
//				}
//				if(backl.size()>0){
//					for(Stoappinfo p : backl){
//						if(p.getProdId().equals(String.valueOf(i))){
//							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
//							flag=true;
//							break;
//						}else {
//							flag=false;
//							continue;
//						}
//							
//					}
//					if(!flag){
//						tools.setCell(rowIndex, colIndex++, 0);
//					}
//				}else{
//					tools.setCell(rowIndex, colIndex++, 0);
//				}
//				if(issuel.size()>0){
//					for(Stoappinfo p : issuel){
//						if(p.getProdId().equals(String.valueOf(i))){
//							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
//							flag=true;
//							break;
//						}else {
//							flag=false;
//							continue;
//						}
//							
//					}
//					if(!flag){
//						tools.setCell(rowIndex, colIndex++, 0);
//					}
//				}else{
//					tools.setCell(rowIndex, colIndex++, 0);
//				}
//				if(disl.size()>0){
//					for(Stoappinfo p : disl){
//						if(p.getProdId().equals(String.valueOf(i))){
//							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
//							flag=true;
//							break;
//						}else {
//							flag=false;
//							continue;
//						}
//							
//					}
//					if(!flag){
//						tools.setCell(rowIndex, colIndex++, 0);
//					}
//				}else{
//					tools.setCell(rowIndex, colIndex++, 0);
//				}
//				rowIndex++;
//				colIndex=colIndexBegin;
//			}
//		}finally{
//			;
//		}
//	}
	protected void setValue(HashMap map, Object obj) throws Exception{
		;
	}	
	protected void setValueMultiSheet(HashMap map, Object obj,String i) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		i=String.valueOf(Integer.parseInt(i)-1);
		try{
			List<Stoappinfo> inl = (List)stoAppBO.getInStockBalReport(obj);
			List<Stoappinfo> outl = (List)stoAppBO.getOutStockBalReport(obj);
			List<Stoappinfo> issuel = (List)stoAppBO.getIssueStockBalReport(obj);			
			List<Stoappinfo> backl = (List)stoAppBO.getBackStockBalReport(obj);
			List<Stoappinfo> disl = (List)stoAppBO.getDisStockBalReport(obj);
			if(inl.size()<1 && outl.size()<1 && backl.size()<1 && disl.size()<1 &&issuel.size()<1){
				throw new MessageException("不存在数据");
			}
			for(int j=1;j<5;j++){
				boolean flag=false;
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(j)));
				if(inl.size()>0){
					for(Stoappinfo p:inl){
						if(p.getIsHTCard().equals(i) && p.getProdId().equals(String.valueOf(j))){
							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
							tools.setCell(rowIndex, colIndex++, p.getCurrPeriodAmt());
							flag=true;
							break;
						}else {
							continue;
						}
							
					}
					if(!flag){
						tools.setCell(rowIndex, colIndex++, 0);
						tools.setCell(rowIndex, colIndex++, 0);
					}
				}else{
					tools.setCell(rowIndex, colIndex++, 0);
					tools.setCell(rowIndex, colIndex++, 0);
				}
				if(outl.size()>0){
					for(Stoappinfo p:outl){
						if(p.getIsHTCard().equals(i) && p.getProdId().equals(String.valueOf(j))){
							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
	 						flag=true;
							break;
						}else {
							continue;
						}
							
					}
					if(!flag){
						tools.setCell(rowIndex, colIndex++, 0);
	 				}
				}else{
					tools.setCell(rowIndex, colIndex++, 0);
				}
				if(backl.size()>0){
					for(Stoappinfo p:backl){
						if(p.getIsHTCard().equals(i) && p.getProdId().equals(String.valueOf(j))){
							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
	 						flag=true;
							break;
						}else {
							continue;
						}
							
					}
					if(!flag){
						 tools.setCell(rowIndex, colIndex++, 0);
	 				}
				}else{
					tools.setCell(rowIndex, colIndex++, 0);
				}
				if(issuel.size()>0){
					for(Stoappinfo p:issuel){
						if(p.getIsHTCard().equals(i) && p.getProdId().equals(String.valueOf(j))){
							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
	 						flag=true;
							break;
						}else {
							continue;
						}
							
					}
					if(!flag){
						tools.setCell(rowIndex, colIndex++, 0);
	 				}
				}else{
					tools.setCell(rowIndex, colIndex++, 0);
				}
				if(disl.size()>0){
					for(Stoappinfo p:disl){
						if(p.getIsHTCard().equals(i) && p.getProdId().equals(String.valueOf(j))){
							tools.setCell(rowIndex, colIndex++, p.getPurchaseAmt());
	 						flag=true;
							break;
						}else {
							continue;
						}
							
					}
					if(!flag){
						tools.setCell(rowIndex, colIndex++, 0);
	 				}
				}else{
					tools.setCell(rowIndex, colIndex++, 0);
				}
				rowIndex++;
				colIndex=colIndexBegin;
				}
			}finally{
				;
			}
		
	}
}
