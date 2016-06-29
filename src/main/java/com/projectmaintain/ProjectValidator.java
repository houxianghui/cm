package com.projectmaintain;

import com.blue.enums.ProjectType;
import com.blue.enums.Steps;
import com.blue.project.ProjectList;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;

public class ProjectValidator {
	public void validate(ProjectList p)throws MessageException{
		CheckUtil.rejectEmpty(p.getPlanStartDate(), "计划开始时间");
		CheckUtil.rejectEmpty(p.getPlanEndDate(), "计划结束时间");
		if(p.getStep().equals(Steps.A.toString())){
			throw new MessageException("阶段不可以选择[全部]");
		}
		if(CheckUtil.isEmptry(p.getProjectClass())){
			throw new MessageException("请选择项目类型");
		}
		if(ProjectType.valueOf(p.getProjectClass()).needProjectId()){
			CheckUtil.rejectEmpty(p.getProjectId(), "项目编号");
		}
		if(CheckUtil.isEmptry(p.getOwning())){
			throw new MessageException("请选择项目所属部门");
		}
		if(ProjectType.valueOf(p.getProjectClass()).isHasScale()){
			if(p.getScaleId()==null){
				throw new MessageException("请选择项目规模");
			}
		}
		if(CheckUtil.isEmptry(p.getMainPlanDis())){
			throw new MessageException("请选择是否由项目经理完成主计划建立");
		}
//		if(CheckUtil.isEmptry(p.getChecker())){
//			throw new MessageException("审批人为必输项");
//		}
		if("1".equals(p.getMainPlanDis())){
			if(CheckUtil.isEmptry(p.getProjectManager())){
				throw new MessageException("请从列表中选择项目经理");
			}
		}
//		if(CheckUtil.isEmptry(p.getProjectManager())){
//			throw new MessageException("请从列表中选择项目经理");
//		}
//		if(CheckUtil.isEmptry(p.getTechManager())){
//			throw new MessageException("请从列表中选择技术经理");
//		}
//		if(CheckUtil.isEmptry(p.getRequireManager())){
//			throw new MessageException("请从列表中选择需求经理");
//		}
	}
}
