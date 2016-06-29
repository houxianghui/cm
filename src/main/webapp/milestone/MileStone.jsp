<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>里程碑维护</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name='endDate']").click(function(){
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

<html:form method="post" action="MileStone.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="mileStoneId"/>
<%=ViewUtil.getTitle("里程碑维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 项目编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectId" size="10" readonly="true"/> 
        
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;里程碑名：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="stoneName" styleClass="Textfield" size="40"/> 
         </td>
       </tr> 
  
		 <tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;里程碑完成时间：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				<html:text property="endDate" readonly="true" size="8"></html:text>
			</td>
		</tr>
		
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;备注：</td>
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
 

