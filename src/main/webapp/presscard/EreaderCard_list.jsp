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
<title>Ereader卡号管理</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="PressCard.do?act=c_ereader"; 
} 

function doQuery() {  
	document.forms[0].act.value = "list_ereader";
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list_ereader";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="PressCard.do">
<input type=hidden name=act value="list_ereader">

<%=ViewUtil.getTitle("Ereader卡号申请")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			厂商:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="pressCardForm" property="ereaderManufacIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>申请编号</td>
			<td>采购类型</td>
			<td>厂商名称</td>
			<td>采购数量</td>
			<td>应用属性</td>
			<td>硬件版本</td>
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
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PURCH_TYPE, vo.getPurchaseType())%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.EREADERMAUN_ID, vo.getManufacId()) %></td>
			<td><%=vo.getPurchaseAmt() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.APPLY_ATTR, vo.getApplyAttr())%></td>
			<td><%=vo.getHardVersion()%></td>
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


