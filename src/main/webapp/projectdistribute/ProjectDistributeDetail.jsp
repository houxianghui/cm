<%@page import="com.blue.enums.YesOrNo"%>
<%@page import="com.blue.enums.Steps"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />
<html> 
 <head>
<title>项目分配</title> 
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProjectMaintain.do"> 
<html:hidden property="act"/>
<%=ViewUtil.getTitle("项目分配")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.projectId.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="projectId" name="projectDistributeForm"/>         	
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.status.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=Steps.valueOf(projectDistributeForm.getStep()).getDesc()%>       	
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <%=ReDefSDicMap.getDicItemVal("0003",projectDistributeForm.getUserId().trim())%>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务名称：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="taskName" name="projectDistributeForm"/>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="startDate" name="projectDistributeForm"/>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <bean:write property="endDate" name="projectDistributeForm"/>     
         </td> 
       </tr> 
		<tr>
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectDistributeForm.isDone.display" bundle="projectMaintain"/>：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
			<%=YesOrNo.valueOf(projectDistributeForm.getIsDone()).getDesc() %>
			</td>
		</tr>
		<tr >
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;投入百分比：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
			<bean:write property="percent" name="projectDistributeForm"/>
			</td>
		</tr>
		<tr >
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;里程碑：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
			<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MILE_STONE, projectDistributeForm.getMileStoneId()==null?"":projectDistributeForm.getMileStoneId().toString()) %>
			</td>
		</tr>
        <tr > 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.memo.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="memo" name="projectDistributeForm"/>
         </td> 
       </tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
			<td height="25" align="center" class="dtPanel_Bottom"> 				
				<html:button property="返回" styleClass="Button" onclick="history.back()" >返回</html:button>
	 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

