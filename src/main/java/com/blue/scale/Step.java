package com.blue.scale;

import java.util.ArrayList;
import java.util.List;

/**
 * �׶ζ���
 * @author blue
 *
 */
public class Step {
	private String step;	//�׶�
	private List<Action> actions = new ArrayList<Action>();//��б�
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
