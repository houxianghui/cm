<%@page import="com.blue.dailyrecord.SummaryOfType"%>
<%@page import="com.blue.dailyrecord.SummaryOfProject"%>
<%@page import="com.blue.enums.CheckStatus"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.dailyrecord.DailyRecord"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>工时统计</title>

<script language="javascript"> 
$(function(){
	
	
	$("#all").button().click(function(){
		$("input[name='departIds']").attr("checked",true);
	});
	$("#allnot").button().click(function(){
		$("input[name='departIds']").attr("checked",false);
	});
});

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="DailyRecord.do">
<input type="hidden" name="act" value="summary"/>
<%=ViewUtil.getTitle("工时统计")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr class="dtPanel_Main" align="center">
		<td>
		<input type="button" id="all" value="全选" style="width:50px">
		</td>
		<td rowspan="2" width="90%" align="left">
		<%out.print(request.getAttribute("checkbox")); %>
		</td>
	</tr>
	<tr align="center"><td>
		<input type="button" id="allnot" value="全不选" style="width:50px">
		</td>
	</tr>
		<tr align="center" class="dtPanel_Main"><td colspan="2">
		<html:submit value="搜索" styleClass="Button"></html:submit>
		</td></tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目名称</td>
			<td>计划工时</td>
			<td>已审核工时</td>
			<td>待审核工时</td>
		</tr>
		<% List<SummaryOfProject> l = (List)request.getAttribute("project");
		if(l != null){
			
			for(SummaryOfProject s : l){
				out.print("<tr align=\"left\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\"  >");
				out.print("<td>"+s.getProjectId()+"</td>");
				out.print("<td>"+s.getProjectName()+"</td>");
				out.print("<td>"+s.getPlanCost()+"</td>");
				out.print("<td>"+s.getCheckedCost()+"</td>");
				out.print("<td>"+s.getInputCost()+"</td>");
				out.print("</tr>");
			}
			
		}
		%>
		</table>
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;项目类型</td>
			<td>计划工时</td>
			<td>已审核工时</td>
			<td>待审核工时</td>
		</tr>
		<% List<SummaryOfType> tl = (List)request.getAttribute("type");
		if(l != null){
			
			for(SummaryOfType s : tl){
				out.print("<tr align=\"left\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\"  >");
				out.print("<td>"+s.getType().getDesc()+"</td>");
				out.print("<td>"+s.getPlanCost()+"</td>");
				out.print("<td>"+s.getCheckedCost()+"</td>");
				out.print("<td>"+s.getInputCost()+"</td>");
				out.print("</tr>");
			}
			
		}
		%>
		</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>