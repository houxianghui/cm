<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>������ά��</title> 
 
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$(":input[name='subSys']").val() || $.trim($(":input[name='subSys']").val())==""){
			alert('��ѡ����ϵͳ');
			return;
		}
		if(!$(":input[name='chgType']").val() || $.trim($(":input[name='chgType']").val())==""){
			alert('��ѡ��������');
			return;
		}
	
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ReportChg.do"> 
<html:hidden property="act"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("������ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="reportName" /> 
         </td>
       </tr> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ϵͳ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="subSys">
         	<html:optionsCollection name="reportChgForm" property="subSysCollection"/>
         </html:select>
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ������ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="chgType">
				<html:optionsCollection name="reportChgForm" property="chgTypeCollection"/>
			</html:select>
         </td>
       </tr>
      <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ���ǰ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="originl"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="target"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ���·����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="location"/> 
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
 

