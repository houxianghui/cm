<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>产品管理</title> 
 
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$(":input[name='productCode']").val() || $.trim($(":input[name='productCode']").val())==""){
			alert('请输入产品代号');
			return;
		}
		if(!$(":input[name='name']").val() || $.trim($(":input[name='name']").val())==""){
			alert('请输入产品名称');
			return;
		}
	
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProductDef.do"> 

<html:hidden property="act"/>
<%=ViewUtil.getTitle("产品管理")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;产品代号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="productCode" ></html:text>
         </td>
       </tr>
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 产品名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="name" ></html:text>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 产品经理：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="managerId">
         	<html:optionsCollection name="productDefForm" property="staffCollection"/>
         </html:select>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 产品最新版本号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="latestVersion" ></html:text>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 最后发布日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="lastPrdDate" ></html:text>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 下一发布日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="nextPrdDate" ></html:text>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 产品描述：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="memo" ></html:textarea>
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
 <script src="js/calendar.js">
 
 </script>
</body> 
</html> 
 

