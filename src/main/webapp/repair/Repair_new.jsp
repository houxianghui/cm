<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<jsp:useBean id="repairForm" scope="request"  class="com.yly.repair.RepairForm" />



<html> 
<head>
<title>��Ʒ�޸�</title> 

<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doRead(){ 

	$.get("Repair.do?act=read&manufacId="+document.forms[0].manufacId.value+"&prodId="+document.forms[0].prodId.value,function(result){
		var json = $.parseJSON(result);	
		if(json.error!=null){
			alert(json.error);
		}else{
			document.forms[0].cardcsn.value=json.csn;
		}
	
		return;
	});
} 
function doRepair(){ 
 	alert(document.forms[0].cardcsn.value);
	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Repair.do"> 
<input type=hidden name=act value=repair> 
<input type=hidden name=step value="commit"> 

<%=ViewUtil.getTitle("��Ʒ�޸�")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Ʒ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("manufacId", SingleDic.MAUN_ID, "WQ")%> 	
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Ʒ���:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, "1")%> 
		</td>
	</tr>	
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		ӡˢ����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;	 
		<html:text property="cardcsn" styleClass="Textfield"  size="20" maxlength="20" />
		</td>	
	</tr>
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������Կ:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
			<html:select property="authkey" styleClass="Select">
				<html:optionsCollection name="repairForm" property="authkeycollection"/>
			</html:select>
		</td>	
	</tr>
	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="read" type="button" class="Button" value="��ȡӡˢ����" onClick="doRead()"> &nbsp; 
						<input	name="repair" type="button" class="Button" value="��Ʒ�޸�" onClick="doRepair()">
							
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

