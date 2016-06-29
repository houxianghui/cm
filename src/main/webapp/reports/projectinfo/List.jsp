<%@page import="com.blue.enums.Status"%>
<%@page import="com.blue.mile.MileStone"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.report.projectinfo.ProjectInfo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>项目信息</title>

<script language="javascript"> 

 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
} 

function turnPage( pagenm ) {   
   	document.forms[0].act.value = "list";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 
$(function(){
	$("#export").attr("class","Button").click(function(){
		window.location.href="ProjectInfo.do?act=down";
	});
	
});
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="ProjectInfo.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>

<%=ViewUtil.getTitle("项目信息")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目名称</td>
			<td>项目经理</td>
			<td>需求经理</td>
			<td>当前阶段</td>
			<td>项目状态</td>
			<td>立项时间</td>
			<td>计划开始时间</td>
			<td>计划结束时间</td>
			<td>延期天数</td>
			<td>计划工作量(天)</td>
			<td>实际工作量(小时)</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectInfo vo = (ProjectInfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getProject().getProjectId()%></td>
			<td><%=vo.getProject().getProjectName() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getProject().getProjectManager()) %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getProject().getRequireManager()) %></td>
			<td><%=Steps.valueOf(vo.getProject().getStep()).getDesc() %></td>
			<td><%=Status.valueOf(vo.getProject().getStat()).getDesc() %></td>
			<td><%=vo.getProject().getSetupDate() %></td>
			<td><%=DateUtil.format(vo.getProject().getPlanStartDate()) %></td>
			<td><%=DateUtil.format(vo.getProject().getPlanEndDate()) %></td>
			<td><%=vo.getDelayDays() %></td>
			<td><%=vo.getPlanWorkLoad()%></td>
			<td><%=vo.getRealWorkLoad() %></td>
			</label></td>
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
				<input type="button" id="export" value="导出">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>