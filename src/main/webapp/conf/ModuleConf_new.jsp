<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>ģ�����汾����</title> 
 
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
 
<html:form method="post" action="Moduleconf.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("����ģ�����汾")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         

         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ģ��������ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="moduleName" styleClass="Textfield"  size="50" maxlength="100"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ģ�����汾��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="moduleVersion" styleClass="Textfield"  size="50" maxlength="50"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>Ӧ����ҵ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="applyHy" styleClass="Textfield"  size="50" maxlength="50"  />
  		  </td> 
       </tr>       
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>����Ӳ���ͺţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="fitHardId" styleClass="Textfield"  size="50" maxlength="100"  />
  		  </td> 
       </tr>     
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�������ߣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="owner" styleClass="Textfield"  size="30" maxlength="30"  />
  		  </td> 
       </tr>         
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�������ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="issueDate" styleClass="Textfield"  size="8" maxlength="8"  />
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
 

