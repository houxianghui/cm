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
<title>POSԭ�ϳ���</title>
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

<%=ViewUtil.getTitle("POSԭ�ϳ���")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<%=ViewUtil.getTitle("���κ�:"+issueappForm.getFormNo()+"��������:"+issueappForm.getTaskAmt())%>	
		��δ���:<div id="taskAmtLeft" ></div><%=issueappForm.getTaskAmtLeft()%>	
	</table>
 

</table>


	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="���س���" class="Button" onClick="doDownProgram()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


