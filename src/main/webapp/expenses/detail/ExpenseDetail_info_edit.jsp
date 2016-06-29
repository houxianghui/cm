<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="expensesDetailForm" scope="request" class="com.blue.expenses.detail.ExpensesDetailForm"/>
<html> 
 <head>
<title>修改费用明细报销信息</title> 
<script language="javascript"> 

function doEdit(){ 
	//增加 
 
	//执行校验 
	var field = new Array("expensesFrom","expensesTo","invoiceno","fee"); 
	var info = new Array("出发","到达","单据张数","金额"); 
 
	//检察输入信息是否为空 
	
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
 
	document.forms[0].submit(); 
} 

</script> 
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="ExpensesDetail.do"> 
<input type=hidden name=act value=edit> 
<input type=hidden name=step value="commit">
<html:hidden property="edetailId" /> 
 
<%=ViewUtil.getTitle("费用明细表")%> 
 
   <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;关联报销单编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="expensesId" styleClass="Textfield"  size="20" maxlength="20" readonly="true" />
         </td> 
       </tr>
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;时间：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="time" styleClass="Textfield"  size="14" maxlength="14" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;报销内容：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="content" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
      <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;单据张数：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="invoiceno" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;金额：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="amt" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       
 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doEdit()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

