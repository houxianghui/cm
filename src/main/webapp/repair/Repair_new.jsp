<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="repairForm" scope="request"  class="com.yly.repair.RepairForm" />



<html> 
<head>
<title>原料修复</title> 

<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doRead(){ 

	$.get("Repair.do?act=read&manufacId="+document.forms[0].manufacId.value+"&prodId="+document.forms[0].prodId.value+"&phiTypeId="+document.forms[0].phiTypeId.value,function(result){
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
	document.forms[0].submit(); 
} 
function doDown1() {  
    document.getElementById('down1').style.color="red"; 
	$.get("Mwsissuetb.do?act=down",function(result){
 		return;
	});
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Repair.do"> 
<input type=hidden name=act value=repair> 
<input type=hidden name=step value="commit"> 

<%=ViewUtil.getTitle("原料修复")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品厂商(模块填写ESAM厂商):
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, "1")%> 	
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品类别:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, "1")%> 
		</td>
	</tr>	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品通信速率:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, "1")%> 
		</td>	
	</tr>		
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		印刷卡号:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;	 
		<html:text property="cardcsn" styleClass="Textfield"  size="20" maxlength="20" />"修复时必填"
		</td>	
	</tr>
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		主控密钥:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
			<html:select property="authkey" styleClass="Select">
				<html:optionsCollection name="repairForm" property="authkeycollection"/>
			</html:select>
			"修复时必填"
		</td>	
	</tr>
	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input type="button" id="down1" value="联机模块请先下载" class="Button" onClick="doDown1()" /> &nbsp; 
						<input	name="repair" type="button" class="Button" value="产品修复" onClick="doRepair()"> &nbsp; 
						<input	name="read" type="button" class="Button" value="读取印刷卡号" onClick="doRead()"> &nbsp; 
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

