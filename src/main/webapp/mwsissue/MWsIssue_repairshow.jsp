<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pageResultIssuetask" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="mwsissuetbForm" scope="request"  class="com.yly.issue.MWsIssuetbForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>



<html> 
<head>
<title>修复发行</title> 
<script language="javascript"> 

function doShow(){ 
	if(document.forms[0].origSamId.value == null ||document.forms[0].origSamId.value == ''){
		alert('请先录入原SAM号'); 
		return; 
	}
	document.forms[0].act.value = "repair";
	document.forms[0].submit();
}
function doRead(){ 
	$.get("Mwsissuetb.do?act=R&prodId="+document.forms[0].prodId.value+"&operationType=25&phiTypeId="+document.forms[0].phiTypeId.value,function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			alert(json.error);
		}else{
			document.forms[0].origSamId.value=json.origSamId;
			if(document.forms[0].prodId.value==4){
				$("#module").text(json.module);
			}
		 		
		}
		return;
	});

}
function doExam(){ 
	$.get("Mwsissuetb.do?act=E&operationType=25&applyAttr="+document.forms[0].appTypeId.value+"&prodId="+document.forms[0].prodId.value+"&manufacId="+document.forms[0].manufacId.value+"&phiTypeId="+document.forms[0].phiTypeId.value,function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			alert(json.error);
		}else{
	 		$("#detectSign"+json.flowNo).text(json.detectSign);
			$("#detect"+json.flowNo).hide();	
			alert(json.msg);
		}
		return;
	});
	
} 
function doIssue(){ 
	document.forms[0].act.value = "singleIssue";
	document.forms[0].submit();
	
} 
function prodAttr_fun(obj){
	if(obj.value==4){
		document.getElementById("button_module1").style.display="";
	}else{
		document.getElementById("button_module1").style.display="none";
	}
}
function keyType_fun(obj){
	if(obj.value==1){
		document.getElementById("hiddenId").style.display="";
		document.getElementById("showId").style.display="none";
	}else{
		document.getElementById("hiddenId").style.display="none";
		document.getElementById("showId").style.display="";
	}
}
function doDown1() {  
    document.getElementById('down1').style.color="red"; 
	$.get("Mwsissuetb.do?act=down",function(result){
 		return;
	});
} 
function doDown2() {  
    document.getElementById('down2').style.color="red"; 
	$.get("Mwsissuetb.do?act=down",function(result){
 		return;
	});
}
function applyAttr_fun(obj){
	if(obj.value==302){
		document.getElementById("button_module2").style.display="";
	}else{
		document.getElementById("button_module2").style.display="none";
	}
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
 <html:form method="post" action="Mwsissuetb.do" >
 	<input type=hidden name=act value="singleIssue">
	<input type=hidden name=requery>
	<input type=hidden name=operationType value="25">
 <%=ViewUtil.getTitle("修复发行")%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>SAM卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="origSamId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  value="<%=mwsissuetbForm.getOrigSamId()%>" />&nbsp; 
		<input	name="read" type="button" class="Button" value="读取SAMID(选择产品类型)" onClick="doRead()"> &nbsp; <div id=module></div>
		&nbsp;<input name="show" type="button" class="Button" value="显示原卡信息" onClick="doShow()"> 	
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>SAM印刷卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="cardcsn" styleClass="Textfield"  size="20" maxlength="20"  value="<%=mwsissuetbForm.getCardcsn()%>"  />&nbsp; 
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadioWithFun("prodId", SingleDic.PROD_ID, mwsissuetbForm.getProdId(),"prodAttr_fun(this)")%>
		</td>
	</tr>	   
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>申请单位:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:select property="unitId" styleClass="Select">
			<html:optionsCollection name="mwsissuetbForm" property="unitIdcollection"/>
		</html:select>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>密钥类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio_WithFun("keyType", SingleDic.KEYTYPE, String.valueOf( mwsissuetbForm.getKeyType()),"keyType_fun(this)")%> 
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>卡片厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, mwsissuetbForm.getManufacId())%> 
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品通信速率:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, mwsissuetbForm.getPhiTypeId())%> 
		</td>	
	</tr>	
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品应用类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<div id="hiddenId">
		<%=ReDefSDicMap.getRadioWithHiddenIdFun("appTypeId", RedefSDicCodes.APPTYPEID,String.valueOf(mwsissuetbForm.getAppTypeId()),"106","applyAttr_fun(this)") %>
		</div>
		<div id="showId"  style="display:none">
		<%=ReDefSDicMap.getRadioWithFun("appTypeId", RedefSDicCodes.APPTYPEID,String.valueOf(mwsissuetbForm.getAppTypeId()),"applyAttr_fun(this)") %>
		</div>
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>模块程序版本:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:select property="binFileVer" styleClass="Select">
				<html:optionsCollection name="mwsissuetbForm" property="moduleVerEffcollection"/>
		</html:select>
		</td>	
	</tr>	
	 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		主控密钥:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
			<html:select property="authkey" styleClass="Select">
				<html:optionsCollection name="mwsissuetbForm" property="authkeycollection"/>
			</html:select>
		</td>	
	</tr>
</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" align="center"> &nbsp;
						</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<div id="button_module1"  style="display:none"><input type="button" id="down1" value="下载" class="Button" onClick="doDown1()" />--></div>
		 		</td> 
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="修复发行" class="Button" onClick="doIssue()"/>--> 
		 		</td> 
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<div id="button_module2"  style="display:none"><input type="button"  id="down2" value="下载" class="Button" onClick="doDown2()" />--> </div>
		 		</td> 
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="检测" class="Button" onClick="doExam()"/> 
		 		</td>
	    </tr> 
  </table> 
</body> 
</html> 
 

