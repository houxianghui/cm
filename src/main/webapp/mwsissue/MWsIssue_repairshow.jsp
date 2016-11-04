<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pageResultIssuetask" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="mwsissuetbForm" scope="request"  class="com.yly.issue.MWsIssuetbForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>



<html> 
<head>
<title>修复发行</title> 
<script language="javascript"> 
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
<html:hidden property="prodId"/>
<html:hidden property="unitId"/>
<html:hidden property="keyType"/>
<html:hidden property="manufacId"/>
<html:hidden property="phiTypeId"/>
<html:hidden property="appTypeId"/>
<html:hidden property="binFileVer"/>
 <%=ViewUtil.getTitle("修复发行")%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="origSamId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  value="<%=mwsissuetbForm.getOrigSamId()%>" readonly="true"/>
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM印刷卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="cardcsn" styleClass="Textfield"  size="20" maxlength="20"  value="<%=mwsissuetbForm.getCardcsn()%>"  readonly="true"/>&nbsp; 
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, mwsissuetbForm.getProdId())%>
		</td>
	</tr>	   
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		申请单位:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID,  String.valueOf(mwsissuetbForm.getUnitId())) %>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		密钥类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf( mwsissuetbForm.getKeyType()))%>
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		卡片厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, mwsissuetbForm.getManufacId()) %>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品通信速率:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, mwsissuetbForm.getPhiTypeId())%>
		</td>	
	</tr>	
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品应用类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(mwsissuetbForm.getAppTypeId())) %>
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		模块程序版本:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getBinFileVer()==""?"":ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, mwsissuetbForm.getBinFileVer()) %>
		</td>	
	</tr>	
	 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>主控密钥:
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
        	<%if(mwsissuetbForm.getProdId().equals("4")){%>
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="down1" value="下载" class="Button" onClick="doDown1()" />-->
		 		</td> 
		 	<%} %>
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="修复发行" class="Button" onClick="doIssue()"/>--> 
		 		</td> 
		 	<%if(mwsissuetbForm.getAppTypeId()==302){%>
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button"  id="down2" value="下载" class="Button" onClick="doDown2()" />--> 
		 		</td> 
		 	<%} %>	
		 		<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="检测" class="Button" onClick="doExam()"/> 
		 		</td>
	    </tr> 
  </table> 
</body> 
</html> 
 

