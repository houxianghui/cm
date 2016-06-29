<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>��Ŀά��</title> 
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
<%=ViewUtil.getTitle("��Ŀά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
      <tr bgcolor="#FFFFFF" id="hide"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %> ��Ŀ��ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectId" /> 
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %><bean:message key="projectMaintainForm.projectName.display" bundle="projectMaintain"/>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectName" /> 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %>��Ŀ���ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="projectClass" styleClass="Select">
			<html:optionsCollection name="projectMaintainForm" property="projectClassCollection"/>
		</html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF" id="scale"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <%=ViewUtil.must() %>��Ŀ��ģ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="scaleId" styleClass="Select">
		</html:select>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ���汾�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        	<html:select property="versionId"></html:select>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �漰��ϵͳ��</td> 
         <td align="left" class="dtPanel_Main2" > 
        	<div id="subSys"></div>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �漰��Ʒ��</td> 
         <td align="left" class="dtPanel_Main2" id="productIds">&nbsp; 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>�������ţ�</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="owning" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="owningCollection"/>
				</html:select>
			</td>
			
		</tr>
	
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>��Ŀ�׶Σ�</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="step" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���ȼ���</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="priority" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="priorityCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;������</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="requireManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;��Ŀ����</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="projectManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>��Ŀ���ƻ��Ƿ�����Ŀ������ɣ�</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="mainPlanDis">
					<html:optionsCollection name="projectMaintainForm" property="yesOrNo"/>
				</html:select>
			</td>
		</tr>
      	<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;��������</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="techManager">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;�����ˣ�</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="checker">
					<html:optionsCollection name="projectMaintainForm" property="staffCollection"/>
				</html:select>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;����ʱ�䣺</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="setupDate" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>�ƻ���ʼʱ�䣺</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planStartDate" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must() %>�ƻ�����ʱ�䣺</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planEndDate"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;�ƻ�Ͷ�빤ʱ(Сʱ)��</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="planCost"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;��ע��</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="memo" cols="28" rows="4"></html:textarea>
			</td>
		</tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:submit styleClass="Button" value="����"/>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

