<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<<jsp:useBean id="riskRecordForm" scope="request" class="com.blue.project.riskrecord.RiskRecordForm"></jsp:useBean>
<html> 
 <head>
<title>����ά��</title> 
 
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

<html:form method="post" action="RiskRecord.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="id"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("����ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <%if(CheckUtil.isEmptry(riskRecordForm.getProjectId())) {%>
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
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ���գ����⣩���ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="riskName" /> 
        
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;���գ����⣩���ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="riskType" styleClass="Select">
			<html:optionsCollection name="riskRecordForm" property="riskTypeCollection"/>
		 </html:select>
         </td>
       </tr> 
  
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���գ����⣩����</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:select property="riskLv" styleClass="Select">
					<html:optionsCollection name="riskRecordForm" property="riskLvCollection"/>
				 </html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���գ����⣩״̬��</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:select property="riskStatus" styleClass="Select">
					<html:optionsCollection name="riskRecordForm" property="riskStatusCollection"/>
				 </html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;�������ʱ�䣺</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="soluteDate"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���գ����⣩������</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="memo" styleClass="Textarea" cols="40" rows="4"></html:textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;���գ����⣩��ȡ��ʩ/������飺</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="solution" styleClass="Textarea" cols="40" rows="4"></html:textarea>
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
 

