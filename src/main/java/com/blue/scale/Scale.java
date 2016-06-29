package com.blue.scale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blue.enums.Steps;
import com.blue.projectdistribute.ProjectDistribute;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.html.Button;
import com.eis.html.HtmlTD;
import com.eis.html.HtmlTR;

/**
 * 规模定义
 * @author blue
 *
 */
public class Scale {
	private List<Step> steps;//步骤定义
	
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public String makeHtml(Map<Integer,List<ProjectDistribute>> distributeMap){
		StringBuffer sb = new StringBuffer();
		List<HtmlTR> atrs = new ArrayList<HtmlTR>();
		for(Step s:steps){
			HtmlTR tr = new HtmlTR();
			HtmlTD td = new HtmlTD(Steps.valueOf(s.getStep()).getDesc());
			tr.addTD(td);
			int actionCount = 0;
			
			atrs.add(tr);
			int stepRows = 0;
			for(Action ad : s.getActions()){
				HtmlTR atr = null;
				if(actionCount++>0){
					atr = new HtmlTR();
					atrs.add(atr);
				}else{
					atr = tr;
				}
				int size = ad.getAchieveDefs().size();
				size=(size==0?1:size);
				stepRows+=size;
				
				HtmlTD t = new HtmlTD(ad.getActionDef().getName());
				t.setRowSpan(size);
				atr.addTD(t);
				t = new HtmlTD(ad.getActionDef().getAction());
				t.setRowSpan(size);
				atr.addTD(t);
				t = new HtmlTD(ad.getActionDef().getCommitLimit());
				t.setRowSpan(size);
				atr.addTD(t);
				t = new HtmlTD(getDistribute(ad.getActionDef().getActionId(),distributeMap));
				t.setRowSpan(size);
				atr.addTD(t);
				t = new HtmlTD(new Button("分配",ad.getActionDef().getActionId()+"-"+s.getStep(),"dist"));
				t.setRowSpan(size);
				atr.addTD(t);
				
				int achieveCount = 0;
				for(AchieveDef a:ad.getAchieveDefs()){
					HtmlTR in = null;
					if(achieveCount++>0){
						in = new HtmlTR();
						atrs.add(in);
					}else{
						in = atr;
					}
					in.addTD(new HtmlTD(a.getAchieve()));
					in.addTD(new HtmlTD(a.getCheckStyle()));
				}
				td.setRowSpan(stepRows);
			}
		}
		for(HtmlTR t : atrs){
			sb.append(t);
		}
		return sb.toString();
	}
	private String getDistribute(Integer actionId, Map<Integer, List<ProjectDistribute>> distributeMap) {
		List<ProjectDistribute> l = distributeMap.get(actionId);
		if(l == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(ProjectDistribute p : l){
			sb.append(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getUserId()));
			sb.append("<br>");
		}
		return sb.toString();
	}

}
