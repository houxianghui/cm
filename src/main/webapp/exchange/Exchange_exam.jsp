<%@page import="com.yly.ls.Lsinfo"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pageResult" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="stoproductForm" scope="request"  class="com.yly.exstore.StoproductForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>



<html> 
<head>
<title>检测及换损</title> 
<script language="javascript"> 

function doShow(){ 
	if(document.forms[0].samId.value == null ||document.forms[0].samId.value == ''){
		alert('请先录入原SAM号'); 
		return; 
	}
	document.forms[0].act.value = "show";
	document.forms[0].submit();
}
function doRead(){ 
	$.get("Mwsissuetb.do?act=R&prodId="+document.forms[0].prodId.value+"&operationType="+document.forms[0].operationType.value,function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			alert(json.error);
		}else{
			document.forms[0].samId.value=json.origSamId;
			if(document.forms[0].prodId.value==4){
				$("#module").text(json.module);
			}
		 		
		}
		return;
	});

}
function doBack(){ 
	if(document.forms[0].samCSN.value == null ||document.forms[0].samCSN.value == ''){
		alert('请录入SAM印刷卡号'); 
		return; 
	}
	document.forms[0].act.value = "back";
	document.forms[0].submit();
	
} 

</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Storeuse.do" >
<input type=hidden name=act value="back">
<input type=hidden name=requery>
<input type=hidden name=operationType value="61">
<html:hidden property="appNo"/>
 <%=ViewUtil.getTitle("产品检测及换损")%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAM卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  />&nbsp; 
		<input	name="read" type="button" class="Button" value="读取SAM卡号(选择产品类型)" onClick="doRead()"> &nbsp; <div id=module></div>
	
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAM印刷卡号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samCSN" styleClass="Textfield"  size="20" maxlength="20"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  />&nbsp; 	
		&nbsp;<input name="show" type="button" class="Button" value="显示原卡信息" onClick="doShow()"> 	
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, "1")%>
		</td>
	</tr>	   
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 申请单位:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:select property="unitId" styleClass="Select">
			<html:optionsCollection name="storeuseForm" property="unitIdcollection"/>
		</html:select>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 密钥类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("keyType", SingleDic.KEYTYPE, "1")%> 
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, "1") %>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 产品通信速率:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, "1")%> 
		</td>	
	</tr>	
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 产品应用类型:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("appTypeId", RedefSDicCodes.APPTYPEID, "101") %>
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 模块程序版本:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:select property="binFileVer" styleClass="Select">
				<html:optionsCollection name="storeuseForm" property="moduleVerEffcollection"/>
		</html:select>
		</td>	
	</tr>	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		卡物理状态:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("cardPhyStat", SingleDic.PHY_STAT, "1")%> 
		</td>	
	</tr>	
</table>
 <%=ViewUtil.getTitle("检测流水信息")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_L
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">流水号</td>
					<td width="10%">SAM卡号</td>
					<td width="10%">SAM印刷卡号</td>
					<td width="10%">业务类型</td>
					<td width="10%">检测结果</td>	
					<td width="10%">错误码</td>						
					<td width="10%">操作员</td>	
					<td width="10%">操作时间</td>	
					<td width="10%">选择</td>						
				</tr>
				<%List list = pageResult.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Lsinfo vo = (Lsinfo)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
					<td><%=vo.getFlowNo()%></td>	
					<td><%=vo.getSamId()%></td>
					<td><%=vo.getSamCSN()%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
					<td><div id="detectSign<%=vo.getFlowNo()%>"></div><div id="detect<%=vo.getFlowNo()%>"><%=vo.getDetectSign()%></div></td>
					<td><%=vo.getErrorCode()==null?"":SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(vo.getErrorCode()))%></td>
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td><%=vo.getCurrDate()%></td>		
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td nowrap class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>	
</html:form>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="back" type="button" class="Button" value="退回回收库" onClick="doBack()"> &nbsp; 	  
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
						
		 		</td> 
	    </tr> 
  </table> 
</body> 
</html> 
 

