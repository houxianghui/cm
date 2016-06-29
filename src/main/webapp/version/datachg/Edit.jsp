<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>数据维护</title> 
 
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$(":input[name='shellName']").val() || $.trim($(":input[name='shellName']").val())==""){
			alert('请输入脚本名称');
			return;
		}
		if(!$(":input[name='memo']").val() || $.trim($(":input[name='memo']").val())==""){
			alert('请输入说明');
			return;
		}
	
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="DataChg.do"> 
<html:hidden property="act"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("数据维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;脚本名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="shellName"></html:text>
         </td>
       </tr>
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 说明：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="memo" /> 
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
 

