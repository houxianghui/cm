package com.blue.scale;

import java.util.ArrayList;
import java.util.List;

/**
 * 阶段定义
 * @author blue
 *
 */
public class Step {
	private String step;	//阶段
	private List<Action> actions = new ArrayList<Action>();//活动列表
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}
