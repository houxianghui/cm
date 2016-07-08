<%@page import="com.yly.issue.IssueappForm"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="issueappForm" scope="request"  class="com.yly.issue.IssueappForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<OBJECT classid="clsid:1EAC4CE9-5EB3-44A1-8536-04476A985AE2" id="reader"  ></OBJECT>  
<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>POS原料出库</title>
<script language="javascript"> 

function doDownProgram() {  
	$.get("Issueapp.do?act=down",function(result){
		var json = $.parseJSON(result);
		$("#taskAmtLeft").append(json.taskAmtLeft);
		return;
	});
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="exlist">
<input type=hidden name=requery > 
<html:hidden property="appNo"/>
<html:hidden property="formState"/>

<%=ViewUtil.getTitle("POS原料出库")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<%=ViewUtil.getTitle("批次号:"+issueappForm.getFormNo()+"出库总数:"+issueappForm.getTaskAmt())%>	
		还未完成:<div id="taskAmtLeft" ></div><%=issueappForm.getTaskAmtLeft()%>	
	</table>
 

</table>


	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="下载程序" class="Button" onClick="doDownProgram()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


