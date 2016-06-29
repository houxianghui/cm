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
<title>卡号管理</title>
<script language="javascript"> 
function doCardDown(){ 
	document.forms[0].act.value = "cardDown";
	document.forms[0].submit(); 
} 

function doQuery() {  
	document.forms[0].act.value = "list_cardno";
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list_cardno";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="PressCard.do">
<input type=hidden name=act value="list_cardno">

<%=ViewUtil.getTitle("卡号管理")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			卡号:			
			从<html:text property="pressCard_min" styleClass="Textfield" size="16" />
			到<html:text property="pressCard_max" styleClass="Textfield" size="16" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>卡号</td>
			<td>表单编号</td>
			<td>产品分类</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Presscardapptb vo = (Presscardapptb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><%=vo.getCardNo()%></td>		
			<td><a href="PressCard.do?act=down&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo() %></a></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CLASS_ID, vo.getClassId())%></td>				
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
			<input type="button" value="导出" class="Button" onClick="doCardDown()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


