/*
 * @(#) SingleDic.java V(1.0) 2007-7-23 houxh
 * 
 * Copyright  (c)  2007 	Huateng. All Right Reserved 
 */

package com.eis.cache;

/**
 * @author houxh 用来保存单级数据字典常量，不要在程序中直接使用该数字
 * 
 */
public interface SingleDic {

	String PROJECT_STAT = "9994"; // 项目状态
	String YES_OR_NO = "0000"; // yes or no
	// 自定义单级数据字典
	String WORK_STATUS = "1001"; // 任务状态
	String TITLE = "1002"; // 报表标题
	String PROJECT_STEP = "9989"; // 项目阶段
	String PRIORITY = "9995"; // 优先级
	String PROJECT_CLASS = "9986"; // 项目类别
	String PROJECT_OWNING = "9990"; // 项目归属
	
	String DB_CHG_MODULE = "8000";//所属模块
	String DB_CHG_ACT = "8001";//变更类型
	String DB_CHG_TYPE = "8002";//变更粒度
	
	String DEPART = "9990";	//员工部门
	
	String SUB_SYS = "9000";	//子系统
	String CHG_TYPE = "9001";	//一般变更类型
	String CARD_TYPE="0309"; //卡片类型
	String MAUN_ID="0310"; //厂商代码
	String COMM_RATE="0311"; //通信速率
	String PURCH_TYPE="0312"; //采购类型
	String APPLY_ATTR="0313"; //产品应用属性
	String EREADERMAUN_ID="0314"; //ereader厂商代码
	String CLASS_ID="0315"; //产品区分E,K
	String PROD_ID="0316"; //产品类型
	String OPERATIONTYPE="0010"; //业务类型
	String UNITID="0318"; //申请单位	
	String ISSUETYPE="0319"; //发行类型:申领,换损,补办,不使用.
	String KEYTYPE = "0320";//密钥类型
	String BINFILEVER = "0321";//模块程序版本
	String FORMTYPE = "0322";//单据状态
	String CONSUTYPE = "0327";//领用类型
	String PAYMETYPE = "0326";//支付类型
	String DETECSIGN = "0325";//检测结果
	String WKSTATE="0323";//产品状态
	String ERRORCODE="0328";//错误代码
	String FUNCID="0330";//函数	
	
	
}
