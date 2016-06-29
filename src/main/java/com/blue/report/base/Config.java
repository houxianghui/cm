 /*
  *  @(#) ReportGenerator.java	1.0	2008-05-09  houxh
  *
  *  Copyright  (c)  2008	Huateng. All Right Reserve.
  */
package com.blue.report.base;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 本类用来解析配置文件config.xml，对外提供的对xml所有属性的访问方法，不提供修改方法
 * 
 * @author houxh
 *
 */
public class Config {
	public Config(String file) throws Exception{
		setDocument(file);
	}
	private Document doc;
	
	private List getList(String nodeName) throws Exception{
		Element cells = getRootElement().element(nodeName);
		List reports = new ArrayList();
		CellAttributes rc = null;
		for(Iterator it = cells.elementIterator();it.hasNext();){
			rc = new CellAttributes();
			setCellAttributes(rc, (Element)it.next());
			reports.add(rc);
		}
		return reports;
	}
	public List getCells()throws Exception{		
		return getList("cells");
	}
	public List getProperties()throws Exception{
		return getList("properties");
	}
	private void setCellAttributes(CellAttributes rc,Element e) throws Exception{
		rc.setRowIndex(Integer.parseInt(getAttribute(e, "rowIndex")));
		rc.setColIndex(Integer.parseInt(getAttribute(e,"colIndex")));
		if(!isEmpty(getAttribute(e,"property"))){
			rc.setProperty(getAttribute(e, "property"));
		}
		if(!isEmpty(getAttribute(e,"rowOffSet"))){
			rc.setRowOffSet(Integer.parseInt(getAttribute(e,"rowOffSet")));
		}
		if(!isEmpty(getAttribute(e,"colOffSet"))){
			rc.setColOffSet(Integer.parseInt(getAttribute(e,"colOffSet")));
		}
		if(!isEmpty(getAttribute(e,"bgColor"))){
			rc.setBgColor(getAttribute(e,"bgColor"));
		}
		if(!isEmpty(getAttribute(e, "rowSpan"))){
			rc.setRowSpan(Integer.parseInt(getAttribute(e, "rowSpan")));
		}
		if(!isEmpty(getAttribute(e, "colSpan"))){
			rc.setColSpan(Integer.parseInt(getAttribute(e, "colSpan")));
		}
		if(!isEmpty(getAttribute(e, "fontSize"))){
			rc.setFontSize(Integer.parseInt(getAttribute(e, "fontSize")));
		}
		if(!isEmpty(getAttribute(e, "value"))){
			rc.setValue(getAttribute(e, "value"));
		}
		if(!isEmpty(getAttribute(e, "fontName"))){
			rc.setFontName(getAttribute(e, "fontName"));
		}		
		if(!isEmpty(getAttribute(e,"hight"))){
			rc.setHight(Integer.parseInt(getAttribute(e,"hight")));
		}
		if(!isEmpty(getAttribute(e,"align"))){
			rc.setAlign(getAttribute(e,"align"));
		}
		if(!isEmpty(getAttribute(e, "valign"))){
			rc.setValign(getAttribute(e, "valign"));
		}
		if(!isEmpty(getAttribute(e,"wordWrap"))){
			if(getAttribute(e,"wordWrap").equals("true")){
				rc.setWordWrap(true);
			}else{
				rc.setWordWrap(false);
			}
		}
		if(!isEmpty(getAttribute(e,"bolder"))){
			if(getAttribute(e,"bolder").equals("true")){
				rc.setBolder(true);
			}else{
				rc.setBolder(false);
			}
		}
		if(!isEmpty(getAttribute(e,"rightBorder"))){
			if(getAttribute(e,"rightBorder").equals("false")){
				rc.setRightBorder(false);
			}else{
				rc.setRightBorder(true);
			}
		}else{
			rc.setRightBorder(true);
		}
		if(!isEmpty(getAttribute(e,"topBorder"))){
			if(getAttribute(e,"topBorder").equals("false")){
				rc.setTopBorder(false);
			}else{
				rc.setTopBorder(true);
			}
		}else{
			rc.setTopBorder(true);
		}
		if(!isEmpty(getAttribute(e,"bottomBorder"))){
			if(getAttribute(e,"bottomBorder").equals("false")){
				rc.setBottomBorder(false);
			}else{
				rc.setBottomBorder(true);
			}
		}else{
			rc.setBottomBorder(true);
		}
		if(!isEmpty(getAttribute(e,"leftBorder"))){
			if(getAttribute(e,"leftBorder").equals("false")){
				rc.setLeftBorder(false);
			}else{
				rc.setLeftBorder(true);
			}
		}else{
			rc.setLeftBorder(true);
		}
		if( e.elements() !=null){
			List<Element> children = e.elements();
			for(Element c : children){
				CellAttributes ca = new CellAttributes();
				setCellAttributes(ca, c);
				rc.addChild(ca);
			}
		}
	}
	private boolean isEmpty(String s){
		if(s==null || s.trim().length() <= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获得给定节点的属性值
	 * 
	 * @param e
	 * @param attrib
	 * @return
	 */
	private String getAttribute(Element e,String attrib){
		if(!isEmpty(e.attributeValue(attrib))){
			return e.attributeValue(attrib);
		}
		if(!isEmpty(e.elementText(attrib))){
			return e.elementText(attrib);
		}
		if(e.element(attrib) != null){
			return e.element(attrib).attributeValue("value");
		}
		return "";
	}
	private Element getRootElement(){
		return doc.getRootElement();
	}

	private void setDocument(String file)throws Exception{
		SAXReader reader = new SAXReader();
		InputStream is = getClass().getClassLoader().getResourceAsStream("templates/"+file);
		doc = reader.read(is);
	}	
	public String getSheetName(){
		return getAttribute(getRootElement(),"sheetName");
	}
}
