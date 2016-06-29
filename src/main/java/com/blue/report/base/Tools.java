package com.blue.report.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Tools {
	private HashMap cells;
	private HashMap rs;
	private ExcelTools et;

	public ExcelTools getEt() {
		return et;
	}

	public void setEt(ExcelTools et) {
		this.et = et;
	}

	public HashMap getCells() {
		return cells;
	}

	public void setCells(HashMap cells) {
		this.cells = cells;
	}

	public HashMap getRs() {
		return rs;
	}

	public void setRs(HashMap rs) {
		this.rs = rs;
	}

	public void setCellValueUseMap(String cellProperty, String columnName) {
		setCellValueUseString(cellProperty, (String) rs.get(columnName));
	}

	public void setCellValueUseString(String cellProperty, String value) {
		if ((CellAttributes) cells.get(cellProperty) == null) {
			return;
		}
		((CellAttributes) cells.get(cellProperty)).setValue(value);
		setCellValueUseCell((CellAttributes) cells.get(cellProperty));
	}

	public void setCellValueUseCell(CellAttributes cell) {
		et.setCellValue(cell);
	}

	public Date parseDate(String s) {
		Calendar c = Calendar.getInstance();
		try {
			int d = Integer.parseInt(s);
			int year = d / 10000;
			int month = (d % 10000) / 100;
			int day = d % 100;
			c.set(year, month - 1, day);
		} catch (Exception e) {
			return null;
		}
		return c.getTime();
	}

	public Calendar parseCalendar(String s) {
		Calendar c = Calendar.getInstance();
		try {
			int d = Integer.parseInt(s);
			int year = d / 10000;
			int month = (d % 10000) / 100;
			int day = d % 100;
			c.set(year, month - 1, day);
		} catch (Exception e) {
			return null;
		}
		return c;
	}

	/**
	 * 
	 * 获得给定两个日期之间相隔的天数
	 * 
	 * @param from
	 *            开始时间
	 * @param to
	 *            结束时间
	 * @return 天数
	 */
	public long getDays(Date from, Date to) {

		long s = from.getTime();
		long e = to.getTime();

		return Math.round(((e - s) / (24 * 60 * 60 * 1000.0))) + 1;
	}

	public int getDays(String from, String to) {
		return (int) getDays(parseDate(from), parseDate(to));
	}

	public boolean isWeekEnd(Calendar c) {
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
			return true;
		}
		return false;
	}

	public boolean isWeekEnd(String date) {
		return isWeekEnd(parseCalendar(date));
	}

	public int getDaysExceptWeekend(String from, String to) {
		if (from.compareTo(to) > 0) {
			String tmp = from;
			from = to;
			to = tmp;
		}
		Calendar begin = parseCalendar(from);
		Calendar end = parseCalendar(to);
		int sum = 0;

		while (!begin.equals(end)) {
			if (!isWeekEnd(begin)) {
				sum++;
			}
			begin.add(Calendar.DATE, 1);
		}

		if (!isWeekEnd(end)) {
			sum++;
		}
		return sum;
	}

	public String getNextDate(String date) {
		if (date == null || date.trim().length() == 0) {
			return "99999999";
		}
		Calendar c = parseCalendar(date);
		c.add(Calendar.DATE, 1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(c.getTime());
	}
	
	public void setCell(int rowIndex, int colIndex, int rowSpan, int colSpan,
			String value, String bgColor, String align, int hight,
			boolean bolder,boolean wordWrap) {
		CellAttributes ca = new CellAttributes();
		ca.setRowIndex(rowIndex);
		ca.setColIndex(colIndex);
		ca.setColSpan(colSpan);
		ca.setRowSpan(rowSpan);
		ca.setValue(value);
		ca.setWordWrap(wordWrap);
		
		if (align != null) {
			ca.setAlign(align);
		}
		if (bolder) {
			ca.setBolder(bolder);
		}
		if (hight != 0) {
			ca.setHight(hight);
		}
		if (bgColor != null) {
			ca.setBgColor(bgColor);
		}
		
		setCellValueUseCell(ca);
	}

	public void setCell(int rowIndex, int colIndex, Object value) {
		if (value == null) {
			value = "";
		}else if(value instanceof Integer){
			value=String.valueOf(value);
		}else if(value instanceof StringBuffer){
			value = value.toString();
		}else if(value instanceof Double){
			value = String.valueOf(value);
		}
		setCell(rowIndex, colIndex, 1, 1, (String) value, null, null, 0, false,false);
	}
	public void setCell(int rowIndex, int colIndex, Object value,boolean wordWrap) {
		if (value == null) {
			value = "";
		}else if(value instanceof Integer){
			value=String.valueOf(value);
		}else if(value instanceof StringBuffer){
			value = value.toString();
		}else if(value instanceof Double){
			value = String.valueOf(value);
		}
		setCell(rowIndex, colIndex, 1, 1, (String) value, null, null, 0, false,wordWrap);
	}
	public void setCell(int rowIndex, int colIndex, Object value, int isRed) {
		setCell(rowIndex, colIndex, 1, 1, (String) value, isRed < 0 ? "RED" :isRed>0? "GREEN":null,
				null, 0, false,false);
	}

	public void setCell(int rowIndex, int colIndex, int rowSpan, int colSpan,
			String value) {
		setCell(rowIndex, colIndex, rowSpan, colSpan, value, null, null, 0,
				false,false);
	}

	public void setCell(int rowIndex, int colIndex, int rowSpan, int colSpan,
			String value, String bgColor) {
		setCell(rowIndex, colIndex, rowSpan, colSpan, value, bgColor, null, 0,
				false,false);
	}

	public void setSingleCell(int rowIndex, int colIndex, int value) {
		setCell(rowIndex, colIndex, 1, 1, value + "", null, null, 0, false,false);
	}

	public void setSingleCell(int rowIndex, int colIndex, String value) {
		setCell(rowIndex, colIndex, 1, 1, value, null, null, 0, false,false);
	}
}
