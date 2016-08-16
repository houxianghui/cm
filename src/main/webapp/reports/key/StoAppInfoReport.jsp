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
<title>入库信息统计报表</title>
<script language="javascript"> 
function doDown(){ 
	document.forms[0].act.value = "staticsDown";
	document.forms[0].submit(); 
} 

 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="staticsDown">

<%=ViewUtil.getTitle("入库信息统计报表")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			<input	value="统计下载"  type="button" class="Button"  onclick="doDown()">			
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


