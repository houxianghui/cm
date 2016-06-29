package com.blue.scale;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.eis.db.DBUtil;
import com.thoughtworks.xstream.XStream;

public class ScaleCache {
	private static boolean isLoad = false;
	private static Logger logger = LoggerFactory.getLogger(ScaleCache.class);
	private ScaleCache(){}
	private static Map<Integer, Map<String,Step>> scaleMap = new HashMap<Integer, Map<String,Step>>();
	private static Map<Integer,Action> actionMap = new HashMap<Integer,Action>(); 
	private static void cache(ScaleDef sd) {
		scaleMap.put(sd.getScaleId(), new HashMap<String,Step>());
		XStream x = new XStream();
		Scale scale = (Scale)x.fromXML(sd.getScale());
		for(Step s : scale.getSteps()){
			scaleMap.get(sd.getScaleId()).put(s.getStep(), s);
			for(Action a : s.getActions()){
				actionMap.put(a.getActionDef().getActionId(), a);
			}
		}
	}
	/**
	 * 获取阶段定义
	 * @param step
	 * @return
	 */
	public static Step getStep(String step,Integer scaleId){
		if(!isLoad){
			reload();
		}
		Map<String, Step> m =  scaleMap.get(scaleId);
		if(m == null){
			return null;
		}else{
			return m.get(step);
		}
	}
	public static void reload(){
		DBUtil db = null;
		try{
			db = new DBUtil();
			ResultSet rs = db.sqlQuery("select * from scale_def");
			while(rs.next()){
				ScaleDef sd = new ScaleDef();
				sd.setScaleId(rs.getInt("SCALE_ID"));
				sd.setScaleDesc(rs.getString("SCALE_DESC"));
				LobHandler lh = new DefaultLobHandler();
				String s = lh.getClobAsString(rs, "SCALE");
				sd.setScale(s);
				cache(sd);
			}
			isLoad = true;
			
		}catch(Exception e){
			logger.error("初始化项目规模失败",e);
		}finally{
			db.close();
		}
	}
	/**
	 * 获取活动项的中文名
	 * @param actionId
	 * @return
	 */
	public static String getActionName(Integer actionId){
		if(!isLoad){
			reload();
		}
		if(actionId == null){
			return "";
		}
		Action a = actionMap.get(actionId);
		if(a == null){
			return "";
		}else{
			return a.getActionDef().getName();
		}
	}
	
	
	/**
	 * 查询活动项的提交物
	 * @param actionId
	 * @return
	 */
	public static String getAchieves(Integer actionId){
		if(!isLoad){
			reload();
		}
		if(actionId == null){
			return "";
		}
		Action a = actionMap.get(actionId);
		if(a == null){
			return "";
		}
		List<AchieveDef> l = a.getAchieveDefs();
		StringBuffer sb = new StringBuffer();
		for(AchieveDef ad : l){
			sb.append(ad.getAchieve());
			sb.append("<br>");
		}
		return sb.toString();
	}
	public static Map<Integer, Action> getActionMap() {
		if(!isLoad){
			reload();
		}
		return actionMap;
	}
}
