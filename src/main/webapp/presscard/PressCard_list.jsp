<%@page import="com.yly.presscard.Presscardapptb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>密钥卡印刷卡号管理</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="PressCard.do?act=c"; 
} 

function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="PressCard.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("密钥卡印刷卡号申请")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			卡片类型:
			<html:select property="cardType" styleClass="Select">
				<html:optionsCollection name="pressCardForm" property="cardTypeCollection"/>
			</html:select>
			厂商:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="pressCardForm" property="manufacIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>申请编号</td>
			<td>卡片类型</td>
			<td>厂商名称</td>
			<td>采购数量</td>
			<td>通信速率</td>
			<td>录入日期</td>
			<td>操作员</td>
			<td>备注</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Presscardapptb vo = (Presscardapptb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="PressCard.do?act=down&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo() %></a></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CARD_TYPE, vo.getCardType())%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, vo.getManufacId()) %></td>
			<td><%=vo.getPurchaseAmt() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getCommRate())%></td>
			<td><%=vo.getCurrDate()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getRemarks()%></td>				
		</tr>

		<%}
}
}%>

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
			<input type="button" value="增加任务" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


