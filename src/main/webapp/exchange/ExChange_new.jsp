<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>��������</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doAdd(){ 
	//ִ��У�� 
	var field = new Array("OAappNo","taskAmt","unitperson","director"); 
	var info = new Array("OA�����","����","��λ��ϵ��","������"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 

	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	
	
	if(!isEmpty(document.forms[0].remarks.value) && document.forms[0].remarks.length>50){
		alert('��ע���ܳ���50������'); 
		document.forms[0].remarks.focus();
		return;
	}
	
	if(!confirm('��ȷ����д��Ϣ,ȷ��ִ�б��β�����')) { 
		return; 
	} 
	
 
	document.forms[0].submit(); 
} 

</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Issueapp.do"> 
<input type=hidden name=act value=exchange_new> 
<input type=hidden name=step value="commit"> 
<%=ViewUtil.getTitle("���ӻ�������")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>OA�����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="OAappNo" styleClass="Textfield"  size="16" maxlength="30"  onblur="noChinese(this)"/>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>ҵ������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("operationType", RedefSDicCodes.EXCHANGETYPE, "41")%> 
		</td>
	</tr>	
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ŀ����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:text property="projName" styleClass="Textfield"  size="20" maxlength="50" />
		</td>	
	</tr>
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ŀ��ͬ��:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:text property="projContNum" styleClass="Textfield"  size="20" maxlength="20" />
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>���뵥λ:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:select property="unitId" styleClass="Select">
			<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
		</html:select>
		</td>
	</tr>	   
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
        <%=ViewUtil.must()%>����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="taskAmt" styleClass="Textfield"  size="10" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>
	
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
                       ֧�����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="totalPrice" styleClass="Textfield"  size="10" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>	
		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��λ��ϵ��:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unitperson" styleClass="Textfield"  size="10" maxlength="20" />
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��ϵ�绰:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unittel" styleClass="Textfield"  size="10" maxlength="20"  onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
	</td>
	</tr>				
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="director" styleClass="Textfield"  size="10" maxlength="20" />
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��ע:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:textarea property="remarks" cols="50" rows="1" styleClass="Textfield" value=""/>
	</td>
	</tr>	
	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="��һ��" onClick="doAdd()"> &nbsp; 
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

