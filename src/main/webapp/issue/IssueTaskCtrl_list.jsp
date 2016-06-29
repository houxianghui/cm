<%@page import="com.yly.issue.Issuetaskctrl"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>发行任务明细单</title>
<script language="javascript"> 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="list">
<html:hidden property="appNo"/>
<%=ViewUtil.getTitle("发行任务明细单")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>发行任务明细号</td>
			<td>申请单位</td>
			<td>发行数量</td>
			<td>起始SAM号</td>
			<td>结束SAM号</td>
			<td>领用方式</td>
			<td>支付方式</td>
			<td>单价(单位元)</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Issuetaskctrl vo = (Issuetaskctrl) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getTaskCtrlNo()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>	
			<td><%=vo.getIssueAmt()%></td>			
			<td><%=vo.getSamIdBegin() %></td>	
			<td><%=vo.getSamIdEnd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(vo.getPaymentType()))%></td>				
			<td><%=vo.getUnitPrice()%></td>
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
			<input type="button" value="返回" class="Button" onClick="history.back()"/>
			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


