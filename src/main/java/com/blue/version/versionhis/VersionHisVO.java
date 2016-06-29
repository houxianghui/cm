package com.blue.version.versionhis;

import java.util.List;
import java.util.Map;

import com.blue.version.subsysversion.SubSysVersion;

public class VersionHisVO extends VersionHis {
	private int subSysCount;
	private int projectCount;
	
	private Map<String,SubSysVersion> subSys;
	
	public int getSubSysCount() {
		return subSysCount;
	}
	public void setSubSysCount(int subSysCount) {
		this.subSysCount = subSysCount;
	}
	public int getProjectCount() {
		return projectCount;
	}
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}
	public Map<String, SubSysVersion> getSubSys() {
		return subSys;
	}
	public void setSubSys(Map<String, SubSysVersion> subSys) {
		this.subSys = subSys;
	}

}
