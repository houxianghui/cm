<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>����Ӧ������</title> 
 
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
 
<html:form method="post" action="Applytypeinfo.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("����Ӧ������")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         

         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>Ӧ�����ʹ��룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
  			<html:text property="applyTypeId" styleClass="Textfield"  size="6" maxlength="6" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>        
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>Ӧ������������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="applyIdDesc" styleClass="Textfield"  size="50" maxlength="100"/> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>֧�ֻ�ͨ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isHLCard",SingleDic.YES_OR_NO,"0")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>SAM��Ƭ��Ʒ����ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="prodClassId" styleClass="Textfield"  size="3" maxlength="3"/> 
         </td> 
       </tr>        
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>PKI���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isPki",SingleDic.YES_OR_NO,"0")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ά2���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isV2",SingleDic.YES_OR_NO,"0")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ά2��ʹ��Ȩ�ޣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isV2Sign",SingleDic.YES_OR_NO,"0")%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��ֵ��Կʹ��Ȩ�޲���Ҫ��֤��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isIsamSign",SingleDic.YES_OR_NO)%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������Կ�ⲿ��֤��ԿΪȫ�㣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getRadio("isIsamTestAllO",SingleDic.YES_OR_NO)%> 
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
 

