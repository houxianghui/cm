<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="biunitinfoForm" scope="request"  class="com.yly.info.BiunitinfotbForm" />

<html> 
<head>
<title>�޸����뵥λ��Ϣ</title> 
 
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
 
<html:form method="post" action="Biunitinfo.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
<html:hidden property="unitid" />
<%=ViewUtil.getTitle("�޸����뵥λ��Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��λ���룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<%=biunitinfoForm.getUnitid()%>
  		  </td> 
       </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ҵ���룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="hyid" styleClass="Textfield"  size="4" maxlength="4"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ҵ���ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="hyName" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��λ���ͣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unittype" styleClass="Textfield"  size="2" maxlength="2"  />
  		  </td> 
       </tr>       
        </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��λ����ȫ�ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitdes" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��λ���ļ�ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="chnshort" styleClass="Textfield"  size="16" maxlength="16"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ϵ�ˣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitperson" styleClass="Textfield"  size="20" maxlength="20"  />
  		  </td> 
       </tr>       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�绰��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unittel" styleClass="Textfield"  size="20" maxlength="20"  />
  		  </td> 
       </tr>       
        </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ַ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitaddr" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�ϼ�������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="leadStore" styleClass="Textfield"  size="9" maxlength="9"  />
  		  </td> 
       </tr>            
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��λ�ȼ���</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<%=SingleDicMap.getRadio("unitlevel", SingleDic.UNIT_LEVEL, biunitinfoForm.getUnitlevel())%>
  		  </td> 
       </tr>     
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��ע��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        		<html:textarea property="rsvd" cols="50" rows="1" styleClass="Textfield" />
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>״̬��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<%=SingleDicMap.getRadio("unittestflag",SingleDic.STATE, biunitinfoForm.getUnittestflag())%>
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
 

