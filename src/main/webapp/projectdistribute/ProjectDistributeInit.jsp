<%@page import="com.blue.scale.ScaleCache"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.enums.YesOrNo"%>
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
<title>项目维护</title>
<%
	String projectId = (String)request.getAttribute("projectId");
	String pStatus = (String)request.getAttribute("pStatus");
%>
<script language="javascript">

function doAdd(){ 
	//增加 
	window.location.href="ProjectDistribute.do?act=new&projectId=<%=projectId%>&pStatus=<%=pStatus%>"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "dl"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(id_var,projectId_var) { 
	document.forms[0].id.value=id_var;
	document.forms[0].projectId.value=projectId_var;
} 
 
function turnPage( pagenm ) {   
	document.forms[0].act.value = "dl";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 
function getWorkTable(){
	window.open("ProjectDistribute.do?act=display");
}
function checkScore(){
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	document.forms[0].act.value = "score"; 
	document.forms[0].submit(); 
}
function recordIssue(){
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	document.forms[0].act.value = "issueRecord"; 
	document.forms[0].submit(); 
}
$(function(){
	$("#projectId").append($("input[name='projectId']").val());
});
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="dl">
<html:hidden property="projectId"/>
<input type=hidden name=id>
<table class="dtPanel_Line3" width="98%" cellpadding="0" border="0" align="center" cellspacing="1">
<tbody>
<tr class="dtPanel_Top01" align="center">
<td class="dtPanel_Top01" id="projectId">项目分配</td>
</tr>
</tbody>
</table>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td>进度</td>
			<td>项目阶段</td>
			<td nowrap>活动项</td>
			<td>任务名称</td>
			<td><bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/></td>
			<td>所属模块</td>
			<td>投入百分比</td>
			<td >备注</td>
			<td><bean:message key="projectDistributeForm.isDone.display" bundle="projectMaintain"/></td>
			<td>选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectDistribute vo = (ProjectDistribute) iter.next();%>
		
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td>
			
			<htm:indicator exclude="<%=vo.getIsDone().equals(YesOrNo.Y.toString()) %>" date="<%=vo.getEndDate() %>"></htm:indicator>
			</td>
			<td><a href="ProjectDistribute.do?act=detail&id=<%=vo.getId()%>"><%=Steps.valueOf(vo.getStep()).getDesc()%></a></td>
			<td><%=ScaleCache.getActionName(vo.getActionId()) %></td>
			<td align="left"><%=vo.getTaskName()%></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			
			<td><%=vo.getModuleId() %></td>
			<td><%=vo.getPercent() %></td>
			<td><%=vo.getMemo()%></td>	
			<td><%=YesOrNo.valueOf(vo.getIsDone()).getDesc()%></td>
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>','<%=vo.getProjectId().toString()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%

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
			<input type="button" value="人员工作安排" class="Button" onclick="getWorkTable()"/>
			<input type="button" value="增加" class="Button" onClick="doAdd()"/>
			<input type="button" value="修改" class="Button" onClick="doEdit()"/>
			<input type="button" value="删除" class="Button" onclick="doDelete()"/>	
			<input type="button" value="问题记录" class="Button" onClick="recordIssue()"/>				
			<input type="button" value="返回" class="Button" onclick="history.back()"/>	
			</td>		
		</tr>
	</table>

</html:form>
<p>&nbsp;</p>
</body>
</html>


