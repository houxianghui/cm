<%@page import="com.yly.stor.Stoappinfo"%>
<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="stoAppForm" scope="request"  class="com.yly.stor.StoAppInfoForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>补办出库</title>
<script language="javascript"> 
function doAdd(){ 
	//进行确认提示 
	if(!confirm('确定此批次原料进行操作吗?')) { 
		return; 
	}

	window.location="Issueapp.do?act=exOver&appNo="+document.forms[0].appNo.value+"&formNo="+document.forms[0].formNo.value+"&operationType=<%=String.valueOf(stoAppForm.getOperationType())%>&taskAmt=<%=String.valueOf(stoAppForm.getCurrPeriodAmt())%>";

}

function doQuery() {  
	document.forms[0].act.value = "ql";
	document.forms[0].submit(); 
} 
 
function setPKey(formNo_var) { 
	document.forms[0].formNo.value=formNo_var; 
	
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="makeupList">
<html:hidden property="appNo"/>
<html:hidden property="operationType"/>
<html:hidden property="formNo"/>
<input type="hidden" name="currPeriodAmt" value=<%=String.valueOf(stoAppForm.getCurrPeriodAmt())%> id="currPeriodAmt"/>



<%=ViewUtil.getTitle("补办原料出库")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			厂商:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="manufacIdCollection"/>
			</html:select>
			入库类型:
			<html:select property="operationType" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="operationTypeCollection"/>
			</html:select>	
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
		<tr><td>操作类型:<%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(stoAppForm.getOperationType()))%>
		补办数量:<%=stoAppForm.getCurrPeriodAmt()%></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>批次号</td>
			<td>当前库存</td>
			<td>产品类型</td>
			<td>入库类型</td>
			<td>支持互通</td>
			<td>采购单价</td>
			<td>生产厂商</td>		
			<td>模块批次号/采购类型/通信速率</td>
			<td>模块版本号/Pki存在</td>
			<td>印刷卡号范围</td>
			<td>选择</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoappinfo vo = (Stoappinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="StoApp.do?act=r&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo()%></a></td>	
			<td><%=vo.getCurrPeriodAmt()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsHTCard())%></td>			
			<td><%=vo.getUnitPrice()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, vo.getManufacId()) %></td>
			<%if(vo.getProdId().equals("4")) {%>
			<td><%=vo.getModuleBatchId()%></td>			
			<td><%=vo.getModuleVersion()%></td>					
			<td></td>	
			<%}else if(vo.getProdId().equals("5")){ %>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PURCH_TYPE,vo.getPurchaseType())%></td>	
			<td></td>
			<td></td>
			<%}else {%>									
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,vo.getPhiTypeId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsPki())%></td>
			<td><%=vo.getPressCardScale()==null?"":vo.getPressCardScale().trim()%></td>	
			<%} %>
			<td align="center">
			<label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
			</label></td>
		</tr>

		<%}
}%>

	
<% 
}
		%>
</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="补办出库" class="Button" onClick="doAdd()"/>			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


