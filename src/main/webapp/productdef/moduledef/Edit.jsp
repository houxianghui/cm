<%@page import="com.blue.product.ModuleDefForm"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="moduleDefForm" class="com.blue.product.ModuleDefForm"></jsp:useBean>
<html> 
 <head>
<title>ģ�����</title> 
 
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$(":input[name='moduleId']").val() || $.trim($(":input[name='moduleId']").val())==""){
			alert('������ģ�����');
			return;
		}
		if(!$(":input[name='name']").val() || $.trim($(":input[name='name']").val())==""){
			alert('������ģ������');
			return;
		}
		document.forms[0].submit();
	}).attr("class","Button");
	$.get("User.do?act=getUserCheckBox&selected="+$("#selected").val(),function(result){
		var json = $.parseJSON(result);
		$("#participate").append(json.msg);
	});
	$("#participate").css("width",500).css("padding-left","15px");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ModuleDef.do"> 

<html:hidden property="act"/>
<%=ViewUtil.getTitle("ģ�����")%> 
<html:hidden property="productCode"/>
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ģ����ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="moduleId" ></html:text>
         </td>
       </tr>
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ģ�����ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="name" ></html:text>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ģ�鸺���ˣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="managerId">
         	<html:optionsCollection name="moduleDefForm" property="staffCollection"/>
         </html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ģ������ˣ�</td> 
         <td align="left" class="dtPanel_Main2" ><div id="participate"></div>
         <input type="hidden" id="selected" value="<%=(String)request.getAttribute("selected")%>"/>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �汾�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="version" ></html:text>
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
 <script src="js/calendar.js">
 
 </script>
</body> 
</html> 
 

