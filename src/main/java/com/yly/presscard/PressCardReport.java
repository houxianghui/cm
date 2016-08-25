package com.yly.presscard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.report.base.BaseReport;
import com.blue.report.base.CellAttributes;
import com.blue.report.base.Tools;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
public class PressCardReport extends BaseReport {

	private PressCardBO pressCardBO;

	public PressCardBO getPressCardBO() {
		return pressCardBO;
	}

	public void setPressCardBO(PressCardBO pressCardBO) {
		this.pressCardBO = pressCardBO;
	}

	protected void setValue(HashMap map, Object obj) throws Exception{
		Tools tools = makeExcel(map);
		CellAttributes ca = (CellAttributes)map.get("list");
		int rowIndex = ca.getRowIndex();
		int colIndex = ca.getColIndex();
		int colIndexBegin=colIndex;
		try{
			List<Presscardapptb> l = (List)pressCardBO.getReport(obj);
			if(l.size()<1){
				throw new MessageException("不存在数据");
			}
			for(Presscardapptb p : l){
				tools.setCell(rowIndex, colIndex++, p.getClassId().equals("E")?SingleDicMap.getDicItemVal(SingleDic.EREADERMAUN_ID, p.getManufacId()):SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, p.getManufacId()));
				tools.setCell(rowIndex, colIndex++, SingleDicMap.getDicItemVal(SingleDic.CLASS_ID, p.getClassId()));
				tools.setCell(rowIndex, colIndex++, p.getCommRate()==null?"":SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, p.getCommRate()));
				tools.setCell(rowIndex, colIndex++, p.getNum());
				rowIndex++;
				colIndex=colIndexBegin;
			}
		}finally{
			;
		}
	}
		
}
