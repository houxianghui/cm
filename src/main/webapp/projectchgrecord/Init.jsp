<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>数据库变更维护</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
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

<html:form method="post" action="ProjectChgRecord.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="id"/>
<%=ViewUtil.getTitle("数据库变更维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 所属模块：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="module">
         	<html:optionsCollection name="projectChgRecordForm" property="modules"/>
         </html:select>
        
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 变更提出日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="fireDate" size="8" readonly="true"/> 
        
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;变更完成日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="finishDate" styleClass="Textfield" size="8" readonly="true"/> 
         </td>
       </tr> 
  
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;变更类型：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:select property="changeType">
					<html:optionsCollection name="projectChgRecordForm" property="changeTypes"/>
				</html:select>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;变更申请人：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="fireUser" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;变更后版本号：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="versionAfter" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;变更实施人：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="operUser" ></html:text>
			</td>
		</tr>
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;目标版本：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="targetVersion"></html:text>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;变更描述：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="memo" styleClass="Textarea" cols="40" rows="4"></html:textarea>
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
 

