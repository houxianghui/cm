<%@page import="com.blue.report.projectplan.StepPlan"%>
<%@page import="com.blue.report.projectplan.ProjectPlanBO"%>
<%@page import="com.blue.project.ProjectList"%>
<%@ include file="/includes/common.jsp"%>
<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>项目计划表</title>

<script language="javascript">
	function doQuery() {
		document.forms[0].act.value = "list";
		document.forms[0].submit();
	}

	function turnPage(pagenm) {
		document.forms[0].act.value = "list";
		document.forms[0].pageNO.value = pagenm;
		document.forms[0].submit();
	}
	$(function() {
		$(":input[type='button']").attr("class","Button");
		$("#search").click(function(){
			doQuery();
		});
		$("#export").attr("class", "Button").click(
				function() {
					document.forms[0].act.value="down";
					document.forms[0].submit();
				});
		$(":input[name*=Date]").attr("class", "Textfield").attr("readonly", "true").click(
				function() {
					new Calendar().show(this);
				});
	});
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<html:form method="post" action="ProjectPlan.do">
		<input type="hidden" name="act"
			value="<%=request.getParameter("act")%>" />

		<%=ViewUtil.getTitle("贷记卡项目开发实施计划表")%>
	
		<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			项目编号：
			<html:text property="projectId" styleClass="Textfield" ></html:text>
			<input type="button" id="search" value="搜索">
			<input type="button" id="export" value="导出">
			</td>
		</tr>
	</table>
	<%ProjectList pl = (ProjectList)request.getAttribute("project"); 
	if(pl == null){
		pl = new ProjectList();
	}
	Map<String,StepPlan> m = (Map)request.getAttribute("map");
	String[] steps = ProjectPlanBO.steps;
	String[] desc = ProjectPlanBO.stepDesc;
	int i = 0;
	double total = 0;%>
	<table  width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center">
			<td class="dtPanel_Top01" height="28">项目名称</td>
			<td colspan="7" class="dtPanel_Main" ><%=pl.getProjectName()%></td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">项目编号</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=pl.getProjectId()%></td>
			<td class="dtPanel_Top01" height="28">立项时间</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getSetupDate())%></td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">项目经理</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,  pl.getProjectManager())%></td>
			<td class="dtPanel_Top01" height="28">需求部门</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=pl.getOwning()%></td>
		</tr>
		<tr align="center">
			<td colspan="8" class="dtPanel_Main" height="28" align="center">开发总体计划</td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">计划开始时间</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getPlanStartDate())%></td>
			<td class="dtPanel_Top01" height="28">预计完成时间</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getPlanEndDate())%></td>
		</tr>
		<tr class="dtPanel_Top01" height="28" align="center">
			<td class="dtPanel_Top01" height="28">实施阶段</td>
			<td class="dtPanel_Top01" height="28">开始日期</td>
			<td class="dtPanel_Top01" height="28">结束日期</td>
			<td class="dtPanel_Top01" height="28">预计工作量<br>（人天）</td>
			<td class="dtPanel_Top01" height="28">各阶段负责人</td>
			<td colspan="3" class="dtPanel_Top01" height="28">计划参与人员名单</td>
		</tr>
		<%for(String t:steps){ %>
		<tr align="center">
			<td class="dtPanel_Top01" height="28"><%=desc[i] %></td>
			<%StepPlan s = m.get(t); 
			i++;
			if(s == null){
				s = new StepPlan();
				s.setUsers(new ArrayList());
			}
			total+=s.getWorkLoad();%>
				<td class="dtPanel_Main2"><%=DateUtil.format(s.getStartDate()) %></td>
				<td class="dtPanel_Main2"><%=DateUtil.format(s.getEndDate()) %></td>
				<td class="dtPanel_Main2"><%=s.getWorkLoad()==0?"":s.getWorkLoad()+"" %></td>
				<td class="dtPanel_Main2"><%=s.getManager() %></td>
				<td colspan="3" class="dtPanel_Main2">
				<%for(String n : s.getUsers()){ %>
					<%=n %>&nbsp;
				<%} %>
				</td>
		</tr>
		<%} %>
		<tr align="center">
			<td class="dtPanel_Top01" height="28" >工作量总计</td>
			<td colspan="2" class="dtPanel_Main" onclick="_clickTr( this )"><%=total %></td>
			<td class="dtPanel_Top01" height="28" >计划投产日期</td>
			<td colspan="4" class="dtPanel_Main" onclick="_clickTr( this )"></td>
		</tr>
		<tr align="center">
			<td height="28">备注</td>
			<td colspan="7" ><%=pl.getMemo() %></td>
		</tr>
	</table>
	
	</html:form>

	<p>&nbsp;</p>
</body>
</html>