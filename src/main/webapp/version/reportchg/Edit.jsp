<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>报表变更维护</title> 
 
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$(":input[name='subSys']").val() || $.trim($(":input[name='subSys']").val())==""){
			alert('请选择子系统');
			return;
		}
		if(!$(":input[name='chgType']").val() || $.trim($(":input[name='chgType']").val())==""){
			alert('请选择变更类型');
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
<%=ViewUtil.getTitle("报表变更维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 报表名：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="reportName" /> 
         </td>
       </tr> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 子系统：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="subSys">
         	<html:optionsCollection name="reportChgForm" property="subSysCollection"/>
         </html:select>
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 变更类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="chgType">
				<html:optionsCollection name="reportChgForm" property="chgTypeCollection"/>
			</html:select>
         </td>
       </tr>
      <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 变更前：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="originl"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 变更后：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="target"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 存放路径：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="location"/> 
         </td>
       </tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="save" value="保存"/>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

