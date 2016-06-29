<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="taxiInfoForm" scope="request" class="com.blue.taxi.TaxiInfoForm"/>
<html> 
 <head>
<title>修改出租车报销信息</title> 
<script language="javascript"> 

function doEdit(){ 
	//增加 
 
	//执行校验 
	var field = new Array("taxiDate","taxiReason","taxiPath","taxiAmt"); 
	var info = new Array("日期时间","报销原因","始发地-目的地","金额"); 
 
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
 
<html:form method="post" action="TaxiInfo.do"> 
<input type=hidden name=act value=edit> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=taxiId value="<%=taxiInfoForm.getTaxiId()%>">
 
<%=ViewUtil.getTitle("出租车费明细表")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;关联报销单编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="expenseId" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr>
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;日期时间：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiDate" styleClass="Textfield"  size="14" maxlength="14" />(格式：20130319160700) 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;报销原因：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiReason" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;始发地-目的地：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiPath" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;金额：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiAmt" styleClass="Textfield"  size="12" maxlength="12" /> 
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
 

