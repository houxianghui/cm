<%@page import="com.blue.projectdistribute.ProjectDistribute"%>
<%@page import="com.blue.enums.Steps"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>完成工作列表</title>
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
	document.forms[0].act.value = "getDoneWorks"; 
	document.forms[0].submit(); 
} 
 
function setPKey(id_var,projectId_var) { 
	document.forms[0].id.value=id_var;
	document.forms[0].projectId.value=projectId_var;
} 
 
function turnPage( pagenm ) {   
	document.forms[0].act.value = "getDoneWorks";  
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
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="getDoneWorks">
<html:hidden property="projectId"/>
<input type=hidden name=id>

<%=ViewUtil.getTitle("完成工作列表")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>：
			<html:text property="project_f" styleClass="Textfield" size="6"></html:text>			
			检查日期：从<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到：<html:text property="endDate_t" styleClass="Textfield" size='8' readonly="true" onclick="new Calendar().show(this);"/>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td nowrap>项目编号</td>
			<td><bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/></td>
			<td><bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/></td>
			<td>实际完成时间</td>
			<td>项目阶段</td>
			<td>备注</td>

		</tr>
		
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectDistribute vo = (ProjectDistribute) iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><%=vo.getFinalEndDate() %></td>
			<td><%=Steps.valueOf(vo.getStep()).getDesc()%> </td>	
			<td><%=vo.getMemo()%></td>	
			
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

</html:form>

<p>&nbsp;</p>
</body>
</html>


