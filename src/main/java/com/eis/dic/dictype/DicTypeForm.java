package com.eis.dic.dictype;

import com.eis.base.BaseForm;

/** 
 * 说明：字典归类信息的数据对象 
 */
public class DicTypeForm extends BaseForm {

	/** 
	 * 构造函数 
	 */
	public DicTypeForm() {
		super();
	}

	private String type_id_f;
	private String type_name_f;
	private String dic_level_caption;

	/** 
	 * 归类码 
	 */
	private String type_id;

	/** 
	 * 名称 
	 */
	private String type_name;

	/** 
	 * 级别标志 
	 */
	private String dic_level;

	public String getType_id_f() {
		return type_id_f;
	}
	public String getType_name_f() {
		return type_name_f;
	}
	/** 
	 *  获得归类码 
	 */
	public String getType_id() {
		return type_id;
	}

	/** 
	 *  获得名称 
	 */
	public String getType_name() {
		return type_name;
	}

	/** 
	 *  获得级别标志 
	 */
	public String getDic_level() {
		return dic_level;
	}

	public void setType_id_f(String str) {
		type_id_f = str;
	}
	public void setType_name_f(String str) {
		type_name_f = str;
	}
	/** 
	 *  设置归类码 
	 */
	public void setType_id(String str) {
		type_id = str;
	}

	/** 
	 *  设置名称 
	 */
	public void setType_name(String str) {
		type_name = str;
	}

	/** 
	 *  设置级别标志 
	 */
	public void setDic_level(String str) {
		dic_level = str;
	}

	/**
	 * @return
	 */
	public String getDic_level_caption() {
		//进行字典数据转换
		if (getDic_level().equals("1"))
			dic_level_caption = "单级字典";
		else
			dic_level_caption = "多级字典";

		return dic_level_caption;
	}

	/**
	 * @param string
	 */
	public void setDic_level_caption(String string) {
		dic_level_caption = string;
	}

}
