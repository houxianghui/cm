<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>������ά��</title> 
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
			alert('����������б�');
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

<%=ViewUtil.getTitle("������ά��")%> 

    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="center" class="dtPanel_Left">&nbsp;�ļ��� 
			<html:file property="file"></html:file>
       		<html:submit value="�ύ" styleClass="Button"/>
         </td> 
       </tr>     
  </table>  
</html:form> 
</body> 

<html:form method="post" action="CodeChg.do">
<input type=hidden name=act value=input>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("�����б�")%> 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         <tr bgcolor="#FFFFFF">
         <td width="35%" align="center" class="dtPanel_Left">
			<html:textarea property="memo" cols="100" styleClass="Textarea" rows="20"></html:textarea>
         </td> 
         </tr>
         <tr>
         <td width="35%" align="center" class="dtPanel_Left">
         	<html:submit value="�ύ" styleClass="Button"/>
         	<html:button property="back" value="����" styleClass="Button" onclick="history.back()"/>
         </td>
         </tr>   
  </table>  
</html:form>
 <p>�������ļ����ݸ�ʽΪÿ��һ����¼��������<b>,</b>�ָ�,�ֶ�Ϊ</p>
<p>�������,��������·������/��ͷ�Ĵ�������·����</p>
<p>������Ͱ���<b>U-���£�D-ɾ����A-����</b></p>
</body> 
</html> 
 

