<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="taxiInfoForm" scope="request" class="com.blue.taxi.TaxiInfoForm"/>
<html> 
 <head>
<title>�޸ĳ��⳵������Ϣ</title> 
<script language="javascript"> 

function doEdit(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("taxiDate","taxiReason","taxiPath","taxiAmt"); 
	var info = new Array("����ʱ��","����ԭ��","ʼ����-Ŀ�ĵ�","���"); 
 
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
 
<html:form method="post" action="TaxiInfo.do"> 
<input type=hidden name=act value=edit> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=taxiId value="<%=taxiInfoForm.getTaxiId()%>">
 
<%=ViewUtil.getTitle("���⳵����ϸ��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������������ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="expenseId" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr>
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;����ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiDate" styleClass="Textfield"  size="14" maxlength="14" />(��ʽ��20130319160700) 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;����ԭ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiReason" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ʼ����-Ŀ�ĵأ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiPath" styleClass="Textfield"  size="12" maxlength="12" /> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taxiAmt" styleClass="Textfield"  size="12" maxlength="12" /> 
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
 

