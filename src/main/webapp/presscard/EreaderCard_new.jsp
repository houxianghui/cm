<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>���ӹ����б�</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	if(!confirm('��ȷ����д��Ϣ,ȷ��ִ�б��β�����')) { 
		return; 
	} 
 
	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="PressCard.do"> 
<input type=hidden name=act value=c_ereader> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("����Ereader����������Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         

         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���̴��룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("manufacId",SingleDic.EREADERMAUN_ID,"01")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�ɹ�������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="purchaseAmt" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�ɹ����ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("purchaseType",SingleDic.PURCH_TYPE,"01")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ƷӦ�����ԣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("applyAttr",SingleDic.APPLY_ATTR,"1")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ƷӲ���汾��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="hardVersion" styleClass="Textfield"  size="1" maxlength="1" /> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��ע��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        		<html:textarea property="remarks" cols="50" rows="1" styleClass="Textfield" value=""/>
         </td> 
       </tr> 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

