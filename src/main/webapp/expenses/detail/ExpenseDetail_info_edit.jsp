<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="expensesDetailForm" scope="request" class="com.blue.expenses.detail.ExpensesDetailForm"/>
<html> 
 <head>
<title>�޸ķ�����ϸ������Ϣ</title> 
<script language="javascript"> 

function doEdit(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("expensesFrom","expensesTo","invoiceno","fee"); 
	var info = new Array("����","����","��������","���"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
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
 
<%=ViewUtil.getTitle("������ϸ��")%> 
 
   <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������������ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="expensesId" styleClass="Textfield"  size="20" maxlength="20" readonly="true" />
         </td> 
       </tr>
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="time" styleClass="Textfield"  size="14" maxlength="14" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�������ݣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="content" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
      <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;����������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="invoiceno" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="amt" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       
 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doEdit()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

