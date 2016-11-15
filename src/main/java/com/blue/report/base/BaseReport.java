package com.blue.report.base;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseReport {
	protected String filePath;
	protected String fileName;
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	private DBQuery db;
	private ExcelTools et;
	protected String config;
	@Autowired
	public void setDb(DBQuery db) {
		this.db = db;
	}
	private void mkdirs(String path)throws Exception{
		if(path == null || path.trim().length() == 0){
			throw new Exception("请在templates/path.properties中配置的报表目录");
		}
		File f = new File(path);
		if(!f.exists())
		f.mkdirs();
	}
	public void setEt(ExcelTools et) {
		this.et = new ExcelTools();
	}
	public void createExcel(Object param,boolean write)throws Exception{		
		et = new ExcelTools();
		Config c = new Config(getConfig());
		et.setSheetName(c.getSheetName());		
		et.setTemplate(c);	
		HashMap map = et.getPropertyMap(c);
		setValue(map,param);
		if(write){
			mkdirs(filePath);
			et.write(filePath+"/"+fileName+param+".xls");
		}
	}	
	public void createExcelSheet(Object param,boolean write,List<String> sheetNames)throws Exception{	
		et = new ExcelTools();
		Config c = new Config(getConfig());
		int i=0;
		for(String l:sheetNames){
			i++;
 			et.setSheetName(l);		
			et.setTemplate(c);	
			HashMap map = et.getPropertyMap(c);
			setValueMultiSheet(map,param,String.valueOf(i));
		}
		if(write){
			mkdirs(filePath);
			et.write(filePath+"/"+fileName+param+".xls");
		}
		 
	}	
	protected abstract void setValue(HashMap map,Object projectId)throws Exception;
	protected abstract void setValueMultiSheet(HashMap map,Object projectId,String i)throws Exception;

	public DBQuery getDb() {
		return db;
	}

	public ExcelTools getEt() {
		return et;
	}
	public String getConfig(){
		return config;
	}
	public void setConfig(String config){
		this.config = config;
	}
	protected Tools makeExcel(HashMap map) {
		Tools tools = new Tools();
		tools.setEt(getEt());
		tools.setCells(map);
		return tools;
	}
}
