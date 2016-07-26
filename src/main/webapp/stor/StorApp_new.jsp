<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>增加入库信息</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doAdd(){ 
	//执行校验 
	var field = new Array("projContNum","purchaseAmt","purchasePerson"); 
	var info = new Array("合同号","入库数量","经办人"); 
 
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
function prodAttr_fun(obj){
	if(obj.value==5){
		document.getElementById("purchtype").style.display="";
		document.getElementById("purchtype1").style.display="";
		document.getElementById("moduver").style.display="none";
		document.getElementById("moduver1").style.display="none";
		document.getElementById("modubatId").style.display="none";
		document.getElementById("modubatId1").style.display="none";

	}else if(obj.value==4){
		document.getElementById("purchtype").style.display="none";
		document.getElementById("purchtype1").style.display="none";
		document.getElementById("moduver").style.display="";
		document.getElementById("moduver1").style.display="";
		document.getElementById("modubatId").style.display="";
		document.getElementById("modubatId1").style.display="";
		
	}else{
		document.getElementById("purchtype").style.display="none";
		document.getElementById("purchtype1").style.display="none";
		document.getElementById("moduver").style.display="none";
		document.getElementById("moduver1").style.display="none";
		document.getElementById("modubatId").style.display="none";
		document.getElementById("modubatId1").style.display="none";
		
	}
	 
}

function operType_fun(obj){
	if(obj.value==13){
		document.getElementById("exFormNo").style.display="";
	}else{
		document.getElementById("exFormNo").style.display="none";
	}
	 
}
function dofindExFormNo(){
	openWin("Issueapp.do?act=popList&formState=1&operationType="+document.forms[0].operationType.value+"","issueapp_pop");
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="StoApp.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<%=ViewUtil.getTitle("增加入库信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>业务类型:
		</td>
		<td  class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadioWithFun("operationType", RedefSDicCodes.INOPERATIONTYPE, "11", "operType_fun(this)")%> 
		</td>	
		<td width="40%"  align="left" class="dtPanel_Left"  >
		<div  id="exFormNo" style="display:none">
		&nbsp;&nbsp;&nbsp;更换批次号: <html:text property="exFormNo" styleClass="Textfield"  size="16" maxlength="16" />
		<input	name="findExFormNo" type="button" class="Button" value="查找" onClick="dofindExFormNo()"> </div>
		
	</tr>     
      <tr>
        <td width="16%" align="left" class="dtPanel_Left">
        <%=ViewUtil.must()%>合同号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="projContNum" size="20" styleClass="Textfield" maxlength="20" />
		</td>
	</tr>
      <tr>
        <td width="16%" align="left" class="dtPanel_Left">
        <%=ViewUtil.must()%>合同名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="projName" size="50" styleClass="Textfield" maxlength="50" />		
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadioWithFun("prodId", SingleDic.PROD_ID, "1", "prodAttr_fun(this)")%> 
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, "1") %>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品通信速率:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, "1")%> 
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品是否支持Pki:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("isPki", SingleDic.YES_OR_NO, "0")%> 
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品是否支持互联互通:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("isHTCard", SingleDic.YES_OR_NO, "0")%> 
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>数量:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="purchaseAmt" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		&nbsp;&nbsp;&nbsp;单价:<html:text property="unitPrice" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		印刷卡号范围:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="pressCardScale" styleClass="Textfield"  size="100" maxlength="200" />
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>经办人:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="purchasePerson" styleClass="Textfield"  size="10" maxlength="20" />
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
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<div id="purchtype" style="display:none">
		<%=ViewUtil.must()%>采购类型:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<div id="purchtype1" style="display:none">
		&nbsp;<%=SingleDicMap.getRadio("purchaseType",SingleDic.PURCH_TYPE,"01")%></div>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<div id="moduver" style="display:none">
		<%=ViewUtil.must()%>模块版本号:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<div id="moduver1" style="display:none">
		&nbsp;<html:text property="moduleVersion" size="20" styleClass="Textfield" maxlength="20" /></div>
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<div id="modubatId" style="display:none">
		<%=ViewUtil.must()%>模块批次号:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<div id="modubatId1" style="display:none">
		&nbsp;<html:text property="moduleBatchId" styleClass="Textfield"  size="20" maxlength="20" /></div>
	</td>
	</tr>				

	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

