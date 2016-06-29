package com.blue.version.versionhis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.Steps;
import com.blue.enums.YesOrNo;
import com.blue.project.ProjectList;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.projectmaintain.ProjectMaintainBO;

public class VersionHisValidator {
	@Autowired
	private VersionHisBO bo;
	@Autowired
	private ProjectMaintainBO projectMaintainBO;
	public void isDeletable(VersionHis v,UserContext user)throws Exception{
		if(!v.getInputUser().equals(user.getUserID())){
			throw new MessageException("您无权删除这条记录");
		}
		List<ProjectList> l = projectMaintainBO.getProjectsByVersionId(v.getVersionId());
		if(l != null && l.size() > 0){
			StringBuffer sb = new StringBuffer();
			for(ProjectList p : l){
				sb.append("["+p.getProjectId()+"]");
			}
			throw new MessageException("项目"+sb+"关联到此版本，不允许执行删除操作");
		}
	}
	public void isEditable(VersionHis v,UserContext user)throws Exception{
		if(v.getInputUser().equals(user.getUserID())){
			return;
		}
		throw new MessageException("您无权修改这条记录");
	}
	public void isChecked(VersionHis v)throws Exception{
		if(v.getIsChecked().equals(YesOrNo.Y.toString())){
			return;
		}
		throw new MessageException("未完成复核操作，不允许发布版本");
	}
	
	public void isReleasable(VersionHis v,UserContext user)throws Exception{
		if(!v.getInputUser().equals(user.getUserID())){
			throw new MessageException("您无权发布该版本");
		}
		List<ProjectList> l = projectMaintainBO.getProjectsByVersionId(v.getVersionId());
		if(l == null || l.size() == 0){
			throw new MessageException("没有关联任何项目，不允许发布");
		}
		for(ProjectList p : l){
			if(Steps.valueOf(p.getStep()).getIndex() <= Steps.S.getIndex()){
				throw new MessageException("项目["+p.getProjectId()+"]-"+p.getProjectName()+"尚未完成集成测试，不可以执行发布操作");
			}
		}
	}
}
