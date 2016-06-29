<%@page import="com.blue.enums.YesOrNo"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.projectdistribute.ProjectDistribute"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目分配</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qd";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="new">

<%=ViewUtil.getTitle("项目分配")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>进度</td>
			<td nowrap>项目编号</td>
			<td><bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/></td>
			<td>项目阶段</td>
			<td>投入百分比</td>
			<td>里程碑</td>
			<td>备注</td>
			<td><bean:message key="projectDistributeForm.isDone.display" bundle="projectMaintain"/></td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectDistribute vo = (ProjectDistribute) iter.next();%>
		
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td>
			
			<htm:indicator exclude="<%=vo.getIsDone().equals(YesOrNo.Y) %>" date="<%=vo.getEndDate() %>"></htm:indicator>
			</td>
			<td><a href="ProjectDistribute.do?act=detail&id=<%=vo.getId()%>"><%=vo.getProjectId()%></a></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><%=Steps.valueOf(vo.getStep()).getDesc()%> </td>	
			<td><%=vo.getPercent() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MILE_STONE, vo.getMileStoneId()==null?"":vo.getMileStoneId().toString())%></td>
			<td><%=vo.getMemo()%></td>	
			<td><%=YesOrNo.valueOf(vo.getIsDone()).getDesc()%></td>
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
			<input type="button" value="返回" class="Button" onclick="history.back()"></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


