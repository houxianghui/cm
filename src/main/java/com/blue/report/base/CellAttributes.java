package com.blue.report.base;

import java.util.ArrayList;
import java.util.List;

public class CellAttributes {
	
	private String property;	//��Ԫ������
	
	private int rowIndex;		//��
	private int colIndex;	//��
	private int colSpan;		//���и���
	private int rowSpan;		//���и���
	private boolean isBolder;	//�Ƿ����
	private boolean isHead;		//�Ƿ��ͷ
	private String value;		//��Ԫ��ֵ
	private String fontName;
	private int hight;			//�и�
	private String align;		//���뷽ʽ
	private String valign;		//��ֱ����
	private boolean topBorder = true;	//�Ͽ�
	private boolean bottomBorder = true;	//�¿�
	private boolean leftBorder = true;		//���
	private boolean rightBorder = true;	//�ҿ�
	private boolean wordWrap;		//���п���
	private int fontSize;			//�ַ���С
	private String bgColor;			//����ɫ 
	private int rowOffSet;			//��ƫ��
	private int colOffSet;			//��ƫ��
	
	//���ڶ�����ģ��ĳ������ã��뵥Ԫ��Ĺ�ϵ�Ǹ���rowSpan����λ
	private List<CellAttributes> children = new ArrayList<CellAttributes>();
	
	public void addChild(CellAttributes ca){
		children.add(ca);
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public boolean isWordWrap() {
		return wordWrap;
	}
	public void setWordWrap(boolean wordWrap) {
		this.wordWrap = wordWrap;
	}
	public boolean isTopBorder() {
		return topBorder;
	}
	public void setTopBorder(boolean topBorder) {
		this.topBorder = topBorder;
	}
	public boolean isBottomBorder() {
		return bottomBorder;
	}
	public void setBottomBorder(boolean bottomBorder) {
		this.bottomBorder = bottomBorder;
	}
	public boolean isLeftBorder() {
		return leftBorder;
	}
	public void setLeftBorder(boolean leftBorder) {
		this.leftBorder = leftBorder;
	}
	public boolean isRightBorder() {
		return rightBorder;
	}
	public void setRightBorder(boolean rightBorder) {
		this.rightBorder = rightBorder;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	public int getColIndex() {
		return colIndex;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex() {
		return colIndex;
	}
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	public int getColSpan() {
		return colSpan;
	}
	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}
	public int getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}
	public boolean isBolder() {
		return isBolder;
	}
	public void setBolder(boolean isBolder) {
		this.isBolder = isBolder;
	}
	public boolean isHead() {
		return isHead;
	}
	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValign() {
		return valign;
	}
	public void setValign(String valign) {
		this.valign = valign;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public int getRowOffSet() {
		return rowOffSet;
	}
	public void setRowOffSet(int rowOffSet) {
		this.rowOffSet = rowOffSet;
	}
	public int getColOffSet() {
		return colOffSet;
	}
	public void setColOffSet(int colOffSet) {
		this.colOffSet = colOffSet;
	}
	public List<CellAttributes> getChildren() {
		return children;
	}
	public void setChildren(List<CellAttributes> children) {
		this.children = children;
	}
	
}
