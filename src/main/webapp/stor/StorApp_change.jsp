<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>���������Ϣ</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doAdd(){ 
	//ִ��У�� 
	var field = new Array("projContNum","purchaseAmt","unitPrice","purchasePerson"); 
	var info = new Array("��ͬ��","�ɹ�����","�ɹ�����","�ɹ�������"); 
 
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
	
	if(!confirm('��ȷ����д��Ϣ,ȷ��ִ�б��β�����')) { 
		return; 
	} 
 
	document.forms[0].submit(); 
} 
function prodAttr_fun(obj){
	if(obj.value==7){
		document.getElementById("purchtype").style.display="";
		document.getElementById("purchtype1").style.display="";
		document.getElementById("commrate").style.display="none";
		document.getElementById("commrate1").style.display="none";
	}else{
		document.getElementById("purchtype").style.display="none";
		document.getElementById("purchtype1").style.display="none";
		document.getElementById("commrate").style.display="";
		document.getElementById("commrate1").style.display="";
	}
	 
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="StoApp.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=operationType value=31> 
<%=ViewUtil.getTitle("���������Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <td width="16%" align="left" class="dtPanel_Left"><%=ViewUtil.must()%>
		��ͬ��:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="projContNum" size="20" styleClass="Textfield" maxlength="20" />
		&nbsp;&nbsp;&nbsp;<%=ViewUtil.must()%>�ɹ�����:<html:text property="purchaseAmt" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ʒ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadioWithFun("prodId", SingleDic.PROD_ID, "01", "prodAttr_fun(this)")%> 
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, "1")%> 
	</td>
	</tr>
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<div id="purchtype" style="display:none">
		�ɹ�����:
		</div>
		<div id="commrate" >
		ͨ������:
		</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">
		<div id="purchtype1" style="display:none">
		&nbsp;<%=SingleDicMap.getRadio("purchaseType",SingleDic.PURCH_TYPE,"01")%> 
		</div>
		<div id="commrate1" >
		&nbsp;<%=SingleDicMap.getRadio("commRate",SingleDic.COMM_RATE,"1")%> 
		</div>
		</td>
	</tr>
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>�ɹ�����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unitPrice" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>�ɹ�������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="purchasePerson" styleClass="Textfield"  size="10" maxlength="20" />
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
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

