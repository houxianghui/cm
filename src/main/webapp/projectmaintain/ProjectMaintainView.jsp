<%@page import="com.blue.enums.ProjectType"%>
<%@page import="com.blue.enums.Status"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>��Ŀά��</title> 
<script language="javascript">
function getPreProject(){
	document.forms[0].act.value="gpp";
	document.forms[0].submit();
}
function getDistribute(){
	window.location.href="ProjectDistribute.do?act=qd&projectId="+document.forms[0].projectId.value;
}	
function getProgramList(){
	window.location.href="ProgramMaintain.do?act=ql&projectId="+document.forms[0].projectId.value;
}
function getChangeList(){
	window.location.href="ChangeRecord.do?act=list&projectId="+document.forms[0].projectId.value;
}
function getRequireChangeList(){
	window.location.href="RequireChange.do?act=list&projectId="+document.forms[0].projectId.value;
}
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProjectMaintain.do"> 
<html:hidden property="act"/>
<%=ViewUtil.getTitle("��Ŀά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="projectId" name="projectMaintainForm"/>
         	<html:hidden property="projectId"/>
         </td>
       </tr> 
          
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.projectName.display" bundle="projectMaintain"/>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="projectName" name="projectMaintainForm"/>
         	<html:hidden property="projectName"/>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��Ŀ���ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=ProjectType.valueOf(projectMaintainForm.getProjectClass()).getDesc() %>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;���汾�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="versionId" name="projectMaintainForm"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�漰��ϵͳ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="subSys" name="projectMaintainForm"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�������ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_OWNING, projectMaintainForm.getOwning()) %>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��Ŀ״̬��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=Status.valueOf(projectMaintainForm.getStat()).getDesc() %>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;���ȼ���</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getDicItemVal(SingleDic.PRIORITY, projectMaintainForm.getPriority().toString()) %>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, projectMaintainForm.getRequireManager()) %>
         </td>
       </tr> <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��Ŀ����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, projectMaintainForm.getProjectManager()) %>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��Ŀ����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, projectMaintainForm.getProjectManager()) %>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, projectMaintainForm.getTechManager()) %>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;����ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="setupDate" name="projectMaintainForm"/>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ƻ���ʼʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="planStartDate" name="projectMaintainForm"/>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ƻ�����ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="planEndDate" name="projectMaintainForm"/>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ƻ�Ͷ�빤ʱ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="planCost" name="projectMaintainForm"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ʵ�ʽ���ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="realEndDate" name="projectMaintainForm"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.memo.display" bundle="projectMaintain"/>��</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="memo" name="projectMaintainForm"/>
         </td> 
       </tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
			<td height="25" align="center" class="dtPanel_Bottom"> 
				<html:button property="ǰ����Ŀ" styleClass="Button" onclick="getPreProject()">ǰ����Ŀ</html:button>
				<html:button property="�����б�" styleClass="Button" onclick="getProgramList()">�����б�</html:button>
				<html:button property="��Ŀ����" styleClass="Button" onclick="getDistribute()">��Ŀ����</html:button>
				<html:button property="��Ŀ�����ʷ" styleClass="Button" onclick="getChangeList()">��Ŀ�����ʷ</html:button>
				<html:button property="��������ʷ" styleClass="Button" onclick="getRequireChangeList()">��������ʷ</html:button>
				<html:button property="����" styleClass="Button" onclick="history.back()" >����</html:button>
	 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

