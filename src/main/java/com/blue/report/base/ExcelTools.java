package com.blue.report.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.contrib.HSSFRegionUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.eis.util.CheckUtil;

public class ExcelTools {
	
	private final static int DEFAULT_FONT_SIZE = 10; 
	private final static short BORDER_STYLE = HSSFCellStyle.BORDER_THIN;
	
	private HSSFWorkbook wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
	private HSSFSheet sheet;
	private HSSFFont defaultFont;
	private HSSFFont font;
	private HSSFCellStyle style = wb.createCellStyle();
	private void setDefaultFont(){
		if(defaultFont == null){
			defaultFont = wb.createFont();
			defaultFont.setFontHeightInPoints((short)DEFAULT_FONT_SIZE);
			defaultFont.setFontName("宋体");
		}
		if(font == null){
			font = wb.createFont();
		}
	}
	/**
	 * 设置边框显示
	 */
	private void setCellBorder(CellAttributes ca,HSSFCellStyle style) {
		if(ca.isTopBorder()){
			style.setBorderTop(BORDER_STYLE);	
		}
		if(ca.isBottomBorder()){
			style.setBorderBottom(BORDER_STYLE) ;
		}
		if(ca.isLeftBorder()){
			style.setBorderLeft(BORDER_STYLE);
		}
		if(ca.isRightBorder()){
			style.setBorderRight(BORDER_STYLE);
		}
			
	}
	
	public void setSheetName(String name){
		if(name == null || name.trim().length() == 0){
			sheet = wb.createSheet();
		}else{
			sheet = wb.createSheet(name);
		}			
	}
	public String getSheetName(){
		return wb.getSheetName(0);
	}
	
	private void setCellValue(HSSFCell cell,String value,HSSFFont font){
		HSSFRichTextString rs = new HSSFRichTextString(value);
		cell.setCellValue(rs);
	}
	public void write(OutputStream os)throws IOException{
		wb.write(os);
	}
	public void write(String path)throws Exception{
		FileOutputStream fileOut = new FileOutputStream(path);
	    wb.write(fileOut);
	    fileOut.close();
	}
	
	public void setCellValue(CellAttributes ca){
		
		setDefaultFont();	//设置默认字体
		adjustSheet(ca);	//宽度自动调整
		
		//生成单元格
		HSSFRow row = null;
		if((row = sheet.getRow(ca.getRowIndex())) == null){
			row = sheet.createRow(ca.getRowIndex());			
		}
		if(ca.getHight() > 0){
			row.setHeight((short)ca.getHight());
		}
		HSSFCell cell = null;
		if((cell=row.getCell(ca.getColumnIndex())) == null){
			cell = row.createCell(ca.getColumnIndex());
		}
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		//~~单元格生成结束
		HSSFCellStyle style = null;
		if(!CheckUtil.isEmptry(ca.getBgColor())){
			style = wb.createCellStyle();
		}else{
			style = this.style;
		}
		
		setCellBorder(ca,style);	//设置边框
		setAlign(ca,style);			//设置对齐方式
		setWordWrap(ca,style);		//设置自动换行
		merge(ca);					//合并单元格
		setBgColor(ca,style);		//设置背景色
		setFont(ca,style);			//设置字体
		cell.setCellStyle(style);	//应用单元格格式
		
		setCellValue(cell,ca.getValue(),font);
	}
	private void setBgColor(CellAttributes ca,HSSFCellStyle style){
		if(ca.getBgColor() == null || ca.getBgColor().length() == 0){
			return;
		}
		String color = ca.getBgColor().toUpperCase();
		try{
			Class c = Class.forName("org.apache.poi.hssf.util.HSSFColor$"+color.trim());
			Field f = c.getField("index");
			style.setFillForegroundColor(f.getShort(c.newInstance()));
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			return;
		}catch (Exception e){
		}
		try{
			Pattern p = Pattern.compile(",");
			String[] s = p.split(color);
			HSSFPalette palette = wb.getCustomPalette();
			palette.setColorAtIndex(HSSFColor.BLUE.index, (byte)Integer.parseInt(s[0]), (byte)Integer.parseInt(s[1]), (byte)Integer.parseInt(s[2]));
			style.setFillForegroundColor(HSSFColor.BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		}catch(Exception e){
			System.out.println("Color not Found");
			return;
		}
		try{
			style.setFillBackgroundColor(Short.parseShort(ca.getBgColor()));
		}catch(Exception e){
			System.out.println("Color not Found");
			return;
		}
	}
	private HSSFFont setFont(CellAttributes ca,HSSFCellStyle style){
		if((ca.getFontName() == null || ca.getFontName().length()==0) && ca.getFontSize() == 0 && ca.isBolder() == false){
			style.setFont(defaultFont);
			return defaultFont;
		}else{
			if(ca.getFontName() != null){
				font.setFontName(ca.getFontName());
			}else{
				font.setFontName("宋体");
			}
			if(ca.getFontSize() != 0){
				font.setFontHeightInPoints((short)ca.getFontSize());
			}else{
				font.setFontHeightInPoints((short)DEFAULT_FONT_SIZE);
			}
			if(ca.isBolder()){
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			}
			style.setFont(font);
			return font;
		}
		
	}
	private void setWordWrap(CellAttributes ca,HSSFCellStyle style){
		style.setWrapText(ca.isWordWrap());
	}
	private void setAlign(CellAttributes ca,HSSFCellStyle style){
		if(ca.getAlign()== null || ca.getAlign().length() == 0){
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}else {
			if("left".equals(ca.getAlign())){		
				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			}else if("rihgt".equalsIgnoreCase(ca.getAlign())){
				style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			}else{
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			}
		}
		if(ca.getValign() == null || ca.getValign().length() == 0){
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}else{
			if("top".equals(ca.getValign())){
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			}else if("bottom".equalsIgnoreCase(ca.getValign())){
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
			}else
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}
	}
	
	private void adjustSheet(CellAttributes ca){
		sheet.autoSizeColumn((short)ca.getColumnIndex());
		if(ca.getColSpan() > 1){
			for(int i = 1;i < ca.getColSpan();i++){
				sheet.autoSizeColumn((short)(ca.getColumnIndex()+i));
			}
		}
	}
	
	private void merge(CellAttributes ca){
		boolean flag = false;
		int lastRow =ca.getRowIndex();
		int lastColumn  = ca.getColumnIndex();
		if(ca.getRowSpan() > 1){
			lastRow = ca.getRowIndex() + ca.getRowSpan() -1;
			flag = true;
		}
		if(ca.getColSpan() > 1){
			lastColumn = ca.getColumnIndex() + ca.getColSpan() -1;
			flag = true;
		}
		if(!flag){
			return;
		}		
		CellRangeAddress range = new CellRangeAddress(ca.getRowIndex(),lastRow,ca.getColIndex(),lastColumn);
		setRegionBorder(range,ca);
		sheet.addMergedRegion(range);		
	}	
	private void setRegionBorder(CellRangeAddress range,CellAttributes ca){
		if(ca.isBottomBorder())
			HSSFRegionUtil.setBorderBottom(BORDER_STYLE, range, sheet, wb);
		if(ca.isLeftBorder())
			HSSFRegionUtil.setBorderLeft(BORDER_STYLE, range, sheet, wb);
		if(ca.isRightBorder())
			HSSFRegionUtil.setBorderRight(BORDER_STYLE, range, sheet, wb);
		if(ca.isTopBorder())
			HSSFRegionUtil.setBorderTop(BORDER_STYLE, range, sheet, wb);
	}
	void setTemplate(Config c) throws Exception{
		List l = c.getCells();
		Iterator it = l.iterator();
		while(it.hasNext()){
			CellAttributes rc = (CellAttributes)it.next();
			setCellValue(rc);
		}
		l = c.getProperties();
		it = l.iterator();
		while(it.hasNext()){
			CellAttributes rc = (CellAttributes)it.next();
			rc.setValue("");
			if(rc.getRowIndex()>=0 && rc.getColIndex()>=0){
				setCellValue(rc);
			}
		}
	}
	HashMap getPropertyMap(Config c) throws Exception{
		HashMap map = new HashMap();
		List l = c.getProperties();
		Iterator it = l.iterator();		
		while(it.hasNext()){
			CellAttributes rc = (CellAttributes)it.next();
			map.put(rc.getProperty(), rc);			
		}
		return map;
	}
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}	
}
