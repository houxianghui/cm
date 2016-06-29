<%@page import="com.blue.report.projectplan.StepPlan"%>
<%@page import="com.blue.report.projectplan.ProjectPlanBO"%>
<%@page import="com.blue.project.ProjectList"%>
<%@ include file="/includes/common.jsp"%>
<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>��Ŀ�ƻ���</title>

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

		<%=ViewUtil.getTitle("���ǿ���Ŀ����ʵʩ�ƻ���")%>
	
		<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			��Ŀ��ţ�
			<html:text property="projectId" styleClass="Textfield" ></html:text>
			<input type="button" id="search" value="����">
			<input type="button" id="export" value="����">
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
			<td class="dtPanel_Top01" height="28">��Ŀ����</td>
			<td colspan="7" class="dtPanel_Main" ><%=pl.getProjectName()%></td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">��Ŀ���</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=pl.getProjectId()%></td>
			<td class="dtPanel_Top01" height="28">����ʱ��</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getSetupDate())%></td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">��Ŀ����</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,  pl.getProjectManager())%></td>
			<td class="dtPanel_Top01" height="28">������</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=pl.getOwning()%></td>
		</tr>
		<tr align="center">
			<td colspan="8" class="dtPanel_Main" height="28" align="center">��������ƻ�</td>
		</tr>
		<tr align="center">
			<td class="dtPanel_Top01" height="28">�ƻ���ʼʱ��</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getPlanStartDate())%></td>
			<td class="dtPanel_Top01" height="28">Ԥ�����ʱ��</td>
			<td colspan="3" class="dtPanel_Main" onclick="_clickTr( this )"><%=DateUtil.format(pl.getPlanEndDate())%></td>
		</tr>
		<tr class="dtPanel_Top01" height="28" align="center">
			<td class="dtPanel_Top01" height="28">ʵʩ�׶�</td>
			<td class="dtPanel_Top01" height="28">��ʼ����</td>
			<td class="dtPanel_Top01" height="28">��������</td>
			<td class="dtPanel_Top01" height="28">Ԥ�ƹ�����<br>�����죩</td>
			<td class="dtPanel_Top01" height="28">���׶θ�����</td>
			<td colspan="3" class="dtPanel_Top01" height="28">�ƻ�������Ա����</td>
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
			<td class="dtPanel_Top01" height="28" >�������ܼ�</td>
			<td colspan="2" class="dtPanel_Main" onclick="_clickTr( this )"><%=total %></td>
			<td class="dtPanel_Top01" height="28" >�ƻ�Ͷ������</td>
			<td colspan="4" class="dtPanel_Main" onclick="_clickTr( this )"></td>
		</tr>
		<tr align="center">
			<td height="28">��ע</td>
			<td colspan="7" ><%=pl.getMemo() %></td>
		</tr>
	</table>
	
	</html:form>

	<p>&nbsp;</p>
</body>
</html>