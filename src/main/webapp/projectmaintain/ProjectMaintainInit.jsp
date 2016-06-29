<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>项目维护</title> 
<style type="text/css">
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
* html .ui-autocomplete {
height: 100px;
}
 </style>
 <script type="text/javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/project/project.js"></script> 

<script type="text/javascript" src="js/adjust.js"></script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProjectMaintainAdd.do"> 
<input type=hidden name=act value="ap"> 
<%=ViewUtil.getTitle("项目维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
      <tr bgcolor="#FFFFFF" id="hide"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %> 项目编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectId" /> 
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %><bean:message key="projectMaintainForm.projectName.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectName" /> 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %>项目类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="projectClass" styleClass="Select">
			<html:optionsCollection name="projectMaintainForm" property="projectClassCollection"/>
		</html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF" id="scale"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %>项目规模：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="scaleId" styleClass="Select">
		</html:select>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 主版本号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        	<html:select property="versionId"></html:select>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 涉及子系统：</td> 
         <td align="left" class="dtPanel_Main2" > 
        	<div id="subSys"></div>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 涉及产品：</td> 
         <td align="left" class="dtPanel_Main2" id="productIds">&nbsp; 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>所属部门：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="owning" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="owningCollection"/>
				</html:select>
			</td>
			
		</tr>
	
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>项目阶段：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="step" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;优先级：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="priority" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="priorityCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;需求经理：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="requireManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;项目经理：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="projectManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>项目主计划是否由项目经理完成：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="mainPlanDis">
					<html:optionsCollection name="projectMaintainForm" property="yesOrNo"/>
				</html:select>
			</td>
		</tr>
      	<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;技术经理：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="techManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;审批人：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="checker">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;立项时间：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="setupDate" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>计划开始时间：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planStartDate" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>计划结束时间：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planEndDate"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;计划投入工时(小时)：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planCost"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;备注：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="memo" cols="28" rows="4"></html:textarea>
			</td>
		</tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:submit styleClass="Button" value="保存"/>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

