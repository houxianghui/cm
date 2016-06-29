package com.blue.report.projectinfo;

import java.util.List;

import com.blue.mile.MileStone;
import com.blue.project.ProjectList;

public class ProjectInfo {
	private ProjectList project;
	private List<MileStone> stones;
	private int delayDays;
	private double planWorkLoad;
	private double realWorkLoad;
	public List<MileStone> getStones() {
		return stones;
	}
	public void setStones(List<MileStone> stones) {
		this.stones = stones;
	}
	public int getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(int delayDays) {
		this.delayDays = delayDays;
	}
	public double getPlanWorkLoad() {
		return planWorkLoad;
	}
	public void setPlanWorkLoad(double planWorkLoad) {
		this.planWorkLoad = planWorkLoad;
	}
	public double getRealWorkLoad() {
		return realWorkLoad;
	}
	public void setRealWorkLoad(double realWorkLoad) {
		this.realWorkLoad = realWorkLoad;
	}
	public ProjectList getProject() {
		return project;
	}
	public void setProject(ProjectList project) {
		this.project = project;
	}
}
