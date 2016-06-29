<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="stoAppForm" scope="request" class="com.yly.stor.StoAppInfoForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>修改入库信息</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doUpdates(){ 
	//执行校验 
	var field = new Array("projContNum","purchaseAmt","unitPrice","purchasePerson"); 
	var info = new Array("合同号","采购数量","采购单价","采购经办人"); 
 
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
function doAddCards(){
	openWin("StorCards.do?act=new&prodId=prodId&formNo=formNo","storcards");
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="StoApp.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=operationType value=31> 
<%=ViewUtil.getTitle("修改采购入库信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <td width="16%" align="left" class="dtPanel_Left"><%=ViewUtil.must()%>
		合同号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="projContNum" size="20" styleClass="Textfield" maxlength="20" />
		&nbsp;&nbsp;&nbsp;<%=ViewUtil.must()%>采购数量:<html:text property="purchaseAmt" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadioWithFun("prodId", SingleDic.PROD_ID, stoAppForm.getProdId(), "prodAttr_fun(this)")%> 
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("manufacId", SingleDic.MAUN_ID, stoAppForm.getManufacId())%> 
	</td>
	</tr>
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<div id="purchtype" style="display:none">
		采购类型:
		</div>
		<div id="commrate" >
		通信速率:
		</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">
		<div id="purchtype1" style="display:none">
		&nbsp;<%=SingleDicMap.getRadio("purchaseType",SingleDic.PURCH_TYPE,stoAppForm.getPurchaseType())%> 
		</div>
		<div id="commrate1" >
		&nbsp;<%=SingleDicMap.getRadio("commRate",SingleDic.COMM_RATE,stoAppForm.getCommRate())%> 
		</div>
		</td>
	</tr>
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>采购单价:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unitPrice" styleClass="Textfield"  size="6" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>采购经办人:
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
	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="提交 " onClick="doUpdate()"> &nbsp; 
						<input	name="addCard" type="button" class="Button" value="增加入库明细" onClick="doAddCards()"> &nbsp; 		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

