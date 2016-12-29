/*

 * @(#) RedefSDicCodes.java V(1.0) 2007-7-29 houxh
 * 
 * Copyright  (c)  2007 	Huateng. All Right Reserver. 
 */

package com.eis.cache;

/**
 * @author houxh
 *
 * 
 */
public interface RedefSDicCodes {
	String USER = "0003";
	String MILE_STONE = "0004";	//省行机构编码
	String INDEP_CODE = "0016";	//独立审批人
	String STUFF_COLLECTION="9999";	//员工
	String PM = "9998";	//项目经理
	String TM = "9997";	//需求经理
	String UNITID = "1001"; //申请一级单位
	String ALL_UNITID = "1002"; //申请单位
	String INOPERATIONTYPE="0010"; //入库操作类型
	String ISSOPERATIONTYPE="0020"; //发行操作类型 配置
	String EXOPERATIONTYPE="0030"; //出库操作类型 配置
	String EXCHANGETYPE="0040"; //换损操作类型 配置
	String MAKEUPTYPE="0050"; //补办操作类型 配置	
	String APPTYPEID = "0012";//产品应用类型	
	String MODULEVERSION = "0013";//模块版本	
	String EFFMODULEVER = "0014";//有效模块版本	
	String MAUN_ID = "0015"; //厂商信息
	String TESTAPPTYPEID = "0016";// 同号不同属性发行的应用类型	
	
}
