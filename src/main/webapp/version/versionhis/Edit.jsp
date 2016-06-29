<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>版本维护</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$("input[name='planReleaseDate']").val() || $.trim($("input[name='planReleaseDate']").val())==""){
			alert('请选择计划发布日期');
			return;
		}
		if(!$("input[name='versionId']").val() || $.trim($("input[name='versionId']").val())==""){
			alert('请填写版本号');
			return;
		}
	
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="VersionHis.do"> 
<html:hidden property="act"/>
<%=ViewUtil.getTitle("版本维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 版本号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="versionId" /> 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 预计发布日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="planReleaseDate"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 版本描述：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="memo"/> 
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
 

