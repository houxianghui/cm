<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>��ϵͳ�汾ά��</title> 
 
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
			alert('��ѡ����ϵͳ');
			return;
		}
	
		if(!$("input[name='nextVersion']").val() || $.trim($("input[name='nextVersion']").val())==""){
			alert('����д�°汾��');
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
<%=ViewUtil.getTitle("��ϵͳ�汾ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ϵͳ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="sysName">
         	<html:optionsCollection name="subSysVersionForm" property="subSysCollection"/>
         </html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ǰһ�汾�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="preVersion"></html:select>
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �°汾�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="nextVersion"/> 
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
 

