<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>子系统版本维护</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$(":input[name='sysName']").change(function(){
		$(":input[name='preVersion']").empty();
		$.get('SubSysVersion.do?act=getPreVersion&sysName='+$(":input[name='sysName']").val()+"&preVersion="+$("input[name='preversion']").val(),function(data){
			$(":input[name='preVersion']").append(data);
		});
	});
	
	$("#save").click(function(){
		if(!$(":input[name='sysName']").val() || $.trim($(":input[name='sysName']").val())==""){
			alert('请选择子系统');
			return;
		}
	
		if(!$("input[name='nextVersion']").val() || $.trim($("input[name='nextVersion']").val())==""){
			alert('请填写新版本号');
			return;
		}
	
		document.forms[0].submit();
	}).attr("class","Button");
	getSubVersion();
	function getSubVersion(){
		$.get('SubSysVersion.do?act=getPreVersion&sysName='+$(":input[name='sysName']").val()+"&preVersion=<%=request.getAttribute("preVersion")%>",function(data){
			$(":input[name='preVersion']").append(data);
		});
	}
});

</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="SubSysVersion.do"> 
<html:hidden property="act"/>
<html:hidden property="id"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("子系统版本维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 子系统：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="sysName">
         	<html:optionsCollection name="subSysVersionForm" property="subSysCollection"/>
         </html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 前一版本号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="preVersion"></html:select>
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 新版本号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="nextVersion"/> 
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
 

