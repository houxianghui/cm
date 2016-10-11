<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>换损申请</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doAdd(){ 
	//执行校验 
	var field = new Array("OAappNo","taskAmt","unitperson","director"); 
	var info = new Array("OA申请号","数量","单位联系人","经办人"); 
 
	//检察输入信息是否为空 

	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	
	
	if(!isEmpty(document.forms[0].remarks.value) && document.forms[0].remarks.length>50){
		alert('备注不能超过50个长度'); 
		document.forms[0].remarks.focus();
		return;
	}
	
	if(!confirm('请确认填写信息,确定执行本次操作吗？')) { 
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
<%=ViewUtil.getTitle("增加换损申请")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>OA申请号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="OAappNo" styleClass="Textfield"  size="16" maxlength="30"  onblur="noChinese(this)"/>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>业务类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("operationType", RedefSDicCodes.EXCHANGETYPE, "41")%> 
		</td>
	</tr>	
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		项目名称:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:text property="projName" styleClass="Textfield"  size="20" maxlength="50" />
		</td>	
	</tr>
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		项目合同号:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:text property="projContNum" styleClass="Textfield"  size="20" maxlength="20" />
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>申请单位:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:select property="unitId" styleClass="Select">
			<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
		</html:select>
		</td>
	</tr>	   
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
        <%=ViewUtil.must()%>数量:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="taskAmt" styleClass="Textfield"  size="10" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>
	
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
                       支付金额:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="totalPrice" styleClass="Textfield"  size="10" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>	
		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>单位联系人:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unitperson" styleClass="Textfield"  size="10" maxlength="20" />
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		联系电话:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unittel" styleClass="Textfield"  size="10" maxlength="20"  onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
	</td>
	</tr>				
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>经办人:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="director" styleClass="Textfield"  size="10" maxlength="20" />
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		备注:
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
						<input	name="add" type="button" class="Button" value="下一步" onClick="doAdd()"> &nbsp; 
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

