<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>程序变更维护</title> 
<style type="text/css">
p{
	padding-left: 5px;
	padding-bottom: 5px;
}
b{
	color: red;
}
</style>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");

	$("#save").click(function(){
		if(!$(":input[name='memo']").val() || $.trim($(":input[name='memo']").val())==""){
			alert('请输入程序列表');
			return;
		}
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="CodeChg.do" enctype="multipart/form-data"> 
<html:hidden property="act" value="upload"/>
<html:hidden property="versionId"/>

<%=ViewUtil.getTitle("程序变更维护")%> 

    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="center" class="dtPanel_Left">&nbsp;文件： 
			<html:file property="file"></html:file>
       		<html:submit value="提交" styleClass="Button"/>
         </td> 
       </tr>     
  </table>  
</html:form> 
</body> 

<html:form method="post" action="CodeChg.do">
<input type=hidden name=act value=input>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("程序列表")%> 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         <tr bgcolor="#FFFFFF">
         <td width="35%" align="center" class="dtPanel_Left">
			<html:textarea property="memo" cols="100" styleClass="Textarea" rows="20"></html:textarea>
         </td> 
         </tr>
         <tr>
         <td width="35%" align="center" class="dtPanel_Left">
         	<html:submit value="提交" styleClass="Button"/>
         	<html:button property="back" value="返回" styleClass="Button" onclick="history.back()"/>
         </td>
         </tr>   
  </table>  
</html:form>
 <p>程序变更文件内容格式为每行一条记录，内容以<b>,</b>分割,字段为</p>
<p>变更类型,代码完整路径（以/开头的代码完整路径）</p>
<p>变更类型包括<b>U-更新，D-删除，A-增加</b></p>
</body> 
</html> 
 

