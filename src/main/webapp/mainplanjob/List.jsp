<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.enums.Status"%>
<%@page import="com.blue.project.ProjectList"%>
<%@page import="com.blue.pm.mainplanjob.MainPlanJob"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>分配给我的项目</title>

<script language="javascript"> 
$(function(){
	$(":button").attr("class","Button");
});
function doAdd(){
	window.location.href="MainPlanJob.do?act=new";
}
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.getElementById('project').act.value='edit';	 
	document.getElementById('project').submit(); 
} 
function doPlan(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value;
} 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.getElementById('project').act.value='delete'; 
	document.getElementById('project').submit(); 
} 

 
function doQuery() {  
	document.getElementById('project').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('project').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('project').projectId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('project').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('project').pageNO.value = pagenm;     
   	document.getElementById('project').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="MainPlanJob.do" styleId="project">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("主计划管理")%>
<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td align="center">进度</td>
			<td align="center">附件</td>
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目名称</td>
			<td>所属部门</td>
			<td nowrap>&nbsp;项目阶段</td>
			<td>优先级</td>
			<td>项目状态</td>
			<td>计划开始日期</td>
			<td>计划结束日期</td>
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
			<htm:linkIndicator hasLink="<%=vo.getIsInContract() %>" projectId="<%=vo.getProjectId()%>"></htm:linkIndicator>
			</td>
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>">&nbsp;<%=vo.getProjectId()%></a></td>
			<td>&nbsp;<%=vo.getProjectName()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DEPART, vo.getOwning()) %></td>			
			<td>&nbsp;<%=Steps.valueOf(vo.getStep()).getDesc()%> </td>
			<td>&nbsp;<%=SingleDicMap.getDicItemVal(SingleDic.PRIORITY,vo.getPriority()+"") %></td>
			<td><htm:statusInd status="<%=vo.getStat() %>"></htm:statusInd></td>	
			<td align="center"><%=DateUtil.format(vo.getPlanStartDate()) %></td>
			<td align="center"><%=DateUtil.format(vo.getPlanEndDate())%></td>
			<td>&nbsp;<%=vo.getMemo()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>')">
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
			<input type="Button" value="主计划已完成" onclick="doEdit()">
			<input type="Button" value="建立主计划" onclick="doPlan()">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>