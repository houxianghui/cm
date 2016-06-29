<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<<jsp:useBean id="changeRecordForm" scope="request" class="com.blue.project.changerecord.ChangeRecordForm"></jsp:useBean>
<html> 
 <head>
<title>���ά��</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript" src="js/project/findProjects.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		var projectId = $("input[name='projectId']").val();
		if(!projectId || $.trim(projectId) == ''){
			alert("��ѡ����Ŀ");
			return;
		}
		if($("input[name='act']").val() == 'init'){
			document.forms[0].act.value="add";
			document.forms[0].submit();
		}else{
			document.forms[0].act.value="update";
			document.forms[0].submit();
		}
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ChangeRecord.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="recordId"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("���ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <%if(CheckUtil.isEmptry(changeRecordForm.getProjectId())) {%>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��Ŀ��ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <input type="text" name="projectName" /> 
         </td>
       </tr> 
       <%}else{ %>
         <input type="hidden" name="projectName"/>
       <%} %>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ������ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="changeTitle" /> 
        
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="changeType" styleClass="Select">
			<html:optionsCollection name="changeRecordForm" property="changeTypeCollection"/>
		 </html:select>
         </td>
       </tr> 
  
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;�������</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:select property="changeLevel" styleClass="Select">
					<html:optionsCollection name="changeRecordForm" property="changeLevelCollection"/>
				 </html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���ԭ��</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="reason" styleClass="Textarea" cols="40" rows="4"></html:textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;������ݣ�</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="content" styleClass="Textarea" cols="40" rows="4"></html:textarea>
			</td>
		</tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="save" value="����"/>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

