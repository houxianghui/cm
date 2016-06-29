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
			throw new MessageException("����Ȩɾ��������¼");
		}
		List<ProjectList> l = projectMaintainBO.getProjectsByVersionId(v.getVersionId());
		if(l != null && l.size() > 0){
			StringBuffer sb = new StringBuffer();
			for(ProjectList p : l){
				sb.append("["+p.getProjectId()+"]");
			}
			throw new MessageException("��Ŀ"+sb+"�������˰汾��������ִ��ɾ������");
		}
	}
	public void isEditable(VersionHis v,UserContext user)throws Exception{
		if(v.getInputUser().equals(user.getUserID())){
			return;
		}
		throw new MessageException("����Ȩ�޸�������¼");
	}
	public void isChecked(VersionHis v)throws Exception{
		if(v.getIsChecked().equals(YesOrNo.Y.toString())){
			return;
		}
		throw new MessageException("δ��ɸ��˲��������������汾");
	}
	
	public void isReleasable(VersionHis v,UserContext user)throws Exception{
		if(!v.getInputUser().equals(user.getUserID())){
			throw new MessageException("����Ȩ�����ð汾");
		}
		List<ProjectList> l = projectMaintainBO.getProjectsByVersionId(v.getVersionId());
		if(l == null || l.size() == 0){
			throw new MessageException("û�й����κ���Ŀ����������");
		}
		for(ProjectList p : l){
			if(Steps.valueOf(p.getStep()).getIndex() <= Steps.S.getIndex()){
				throw new MessageException("��Ŀ["+p.getProjectId()+"]-"+p.getProjectName()+"��δ��ɼ��ɲ��ԣ�������ִ�з�������");
			}
		}
	}
}
