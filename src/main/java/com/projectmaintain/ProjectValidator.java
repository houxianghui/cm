package com.projectmaintain;

import com.blue.enums.ProjectType;
import com.blue.enums.Steps;
import com.blue.project.ProjectList;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;

public class ProjectValidator {
	public void validate(ProjectList p)throws MessageException{
		CheckUtil.rejectEmpty(p.getPlanStartDate(), "�ƻ���ʼʱ��");
		CheckUtil.rejectEmpty(p.getPlanEndDate(), "�ƻ�����ʱ��");
		if(p.getStep().equals(Steps.A.toString())){
			throw new MessageException("�׶β�����ѡ��[ȫ��]");
		}
		if(CheckUtil.isEmptry(p.getProjectClass())){
			throw new MessageException("��ѡ����Ŀ����");
		}
		if(ProjectType.valueOf(p.getProjectClass()).needProjectId()){
			CheckUtil.rejectEmpty(p.getProjectId(), "��Ŀ���");
		}
		if(CheckUtil.isEmptry(p.getOwning())){
			throw new MessageException("��ѡ����Ŀ��������");
		}
		if(ProjectType.valueOf(p.getProjectClass()).isHasScale()){
			if(p.getScaleId()==null){
				throw new MessageException("��ѡ����Ŀ��ģ");
			}
		}
		if(CheckUtil.isEmptry(p.getMainPlanDis())){
			throw new MessageException("��ѡ���Ƿ�����Ŀ����������ƻ�����");
		}
//		if(CheckUtil.isEmptry(p.getChecker())){
//			throw new MessageException("������Ϊ������");
//		}
		if("1".equals(p.getMainPlanDis())){
			if(CheckUtil.isEmptry(p.getProjectManager())){
				throw new MessageException("����б���ѡ����Ŀ����");
			}
		}
//		if(CheckUtil.isEmptry(p.getProjectManager())){
//			throw new MessageException("����б���ѡ����Ŀ����");
//		}
//		if(CheckUtil.isEmptry(p.getTechManager())){
//			throw new MessageException("����б���ѡ��������");
//		}
//		if(CheckUtil.isEmptry(p.getRequireManager())){
//			throw new MessageException("����б���ѡ��������");
//		}
	}
}
