<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.enums.Status"%>
<%@page import="com.blue.project.ProjectList"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目维护</title>
<script language="javascript"> 
 
 
function doQuery() {  
	document.forms[0].act.value = "qal"; 
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var,step_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].step.value=step_var;
} 
function doDistribute(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value+"&status="+document.forms[0].step.value;
}  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qal";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>

<html:form method="post" action="ProjectMaintain.do">
<input type=hidden name=act value="qal">
<html:hidden property="projectId"/>
<html:hidden property="step"/>
<%=ViewUtil.getTitle("项目维护")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>：
			<html:text property="projectId_f" styleClass="Textfield" size="10"></html:text>
			<bean:message key="projectMaintainForm.status.display" bundle="projectMaintain"/>：
			<html:select property="step_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onClick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td align="center">进度</td>
			<td align="center">附件</td>
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目名称</td>
			<td nowrap>&nbsp;项目阶段</td>
			<td>项目状态</td>
			<td>计划开始日期</td>
			<td>计划结束日期</td>
			<td>实际结束日期</td>
			<td>&nbsp;备注</td>
			<td align="center">选择</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectList vo = (ProjectList) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td align="center">
			<htm:indicator exclude="<%=Status.F.toString().equals(vo.getStat()) %>" date="<%=vo.getPlanEndDate() %>"></htm:indicator>
			</td>
			<td align="center">
			<htm:linkIndicator hasLink="<%=vo.getIsInContract() %>" projectId="<%=vo.getProjectId() %>"></htm:linkIndicator>
			</td>
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>">&nbsp;<%=vo.getProjectId()%></a></td>
			<td>&nbsp;<%=vo.getProjectName()%></td>			
			<td>&nbsp;<%=Steps.valueOf(vo.getStep()).getDesc()%> </td>
			<td><htm:statusInd status="<%=vo.getStat() %>"></htm:statusInd></td>	
			<td align="center"><%=DateUtil.format(vo.getPlanStartDate()) %></td>
			<td align="center"><%=DateUtil.format(vo.getPlanEndDate())%></td>
			<td align="center"><%=DateUtil.format(vo.getRealEndDate()) %></td>
			<td>&nbsp;<%=vo.getMemo()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStep()%>')">
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
			
			<input type="button" value="项目分配" class="Button" onclick="doDistribute()"></td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


