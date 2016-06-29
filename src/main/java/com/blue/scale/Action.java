package com.blue.scale;

import java.util.ArrayList;
import java.util.List;

public class Action {
	private ActionDef actionDef;
	private List<AchieveDef> achieveDefs=new ArrayList<AchieveDef>();
	public ActionDef getActionDef() {
		return actionDef;
	}
	public void setActionDef(ActionDef actionDef) {
		this.actionDef = actionDef;
	}
	public List<AchieveDef> getAchieveDefs() {
		return achieveDefs;
	}
	public void setAchieveDefs(List<AchieveDef> achieveDefs) {
		this.achieveDefs = achieveDefs;
	}
	
}
