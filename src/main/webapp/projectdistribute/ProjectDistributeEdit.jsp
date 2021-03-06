<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>项目维护</title> 
<script language="javascript"> 
function doCommit(){
	var s = prompt('请输入修改原因','');
	if(s == null || s==""){
		alert("请输入修改原因");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	
$(function(){
	$.get("ModuleDef.do?act=getModuleOption&selected=<%=projectDistributeForm.getModuleId()%>",function(data){
		$(data).appendTo("#moduleId");
	});
	$.get("ScaleDef.do?act=getActions&selected=<%=projectDistributeForm.getActionId()%>&step=<%=projectDistributeForm.getStep()%>&scaleId=<%=request.getAttribute("scaleId")%>",function(data){
		$(data).appendTo("#actionId");
	});
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 
<script type="text/javascript" src="js/calendar.js"></script>

<html:form method="post" action="ProjectDistribute.do"> 
<input type=hidden name=act value="update"> 
<html:hidden property="projectId"/>
<html:hidden property="id"/>
<html:hidden property="isDone" value="0"/>
<html:hidden property="reason"/>
<%=ViewUtil.getTitle("项目分配")%> 

    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务名称：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="taskName" styleClass="Textarea" cols="40" rows="6"></html:textarea>
         </td> 
       </tr>    
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.status.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="step" styleClass="Select" disabled="true">
         	<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
         </html:select>  
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="userId" styleClass="Select" disabled="true">
         	<html:optionsCollection name="projectDistributeForm" property="stuff"/>
         </html:select>
         <html:hidden property="userId"/>
         <html:messages property="userId" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="startDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
          <html:messages property="startDate" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
		 <html:messages property="endDate" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr>   
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 投入百分比：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="percent" styleClass="Textfield" size="8" />
         </td> 
       </tr>  
       	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 所属模块：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
        	<select name="moduleId" id="moduleId" class="Select"></select>
         </td> 
       </tr>
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 所属活动项：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
        	<select name="actionId" id="actionId" class="Select"></select>
         </td> 
       </tr>
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.memo.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="memo" styleClass="Textarea" cols="40" rows="6"></html:textarea>
		 <html:messages property="memo" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr>     
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:button styleClass="Button" value="保存" property="baocun" onclick="doCommit()"></html:button>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

