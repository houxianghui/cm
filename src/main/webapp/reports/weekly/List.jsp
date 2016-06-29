<%@page import="com.blue.enums.RiskStatus"%>
<%@page import="com.blue.project.riskrecord.RiskRecord"%>
<%@page import="com.blue.report.weekly.Weekly"%>
<%@page import="com.blue.report.weekly.WeeklyReport"%>
<%@ include file="/includes/common.jsp"%>
<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	int maxPage = 1;
	if (pageResult != null)
		maxPage = pageResult.getTotalPage();
%>
<title>�ܱ�����</title>

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
		$("#export").attr("class", "Button").click(
				function() {
					document.forms[0].act.value = "down";
					document.forms[0].submit();
				});
		$("#date").attr("class", "Textfield").attr("readonly", "true").click(
				function() {
					new Calendar().show(this);
				});
		$("#all").click(function(){
			$("input[name='ids']").attr("checked",true);
		});
		$("#allnot").click(function(){
			$("input[name='ids']").attr("checked",false);
		});
		$("#search").click(function(){
			doQuery();
		});
	});
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<html:form method="post" action="WeeklyReport.do">
		<input type="hidden" name="act"
			value="<%=request.getParameter("act")%>" />

		<%=ViewUtil.getTitle("�ܱ�����")%>
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
			align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Main">
				<td><input type="button" id="all" value="ȫѡ"
					style="width: 50px" class="Button"></td>
				<td rowspan="2" align="left" width="90%">
					<%
						out.print(request.getAttribute("checkbox"));
					%>
				</td>
			<tr align="center" class="dtPanel_Main" >
				<td><input type="button" id="allnot" value="ȫ��ѡ"
					style="width: 50px" class="Button"></td>
			</tr>
			<tr align="center" class="dtPanel_Main" >
				<td colspan="2">ѡ�񵼳��ܣ�
				<html:text property="date" styleClass="Textfield" readonly="true" styleId="date"></html:text>
				<input type="button" id="search" value="����" class="Button">
				<input type="button" id="export" value="����">
				</td>
			</tr>
		</table>
	</html:form>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td colspan="7" align="center">���Ź����ܱ�</td>
		</tr>
		<tr>
			<td class="dtPanel_Top01" nowrap="nowrap">�ܱ���ֹʱ��</td><td><%=request.getAttribute("startDate") %></td>
			<td class="dtPanel_Top01">��</td><td colspan="4"><%=request.getAttribute("endDate") %></td>
		</tr>
		<tr>
			<td class="dtPanel_Top01">��������</td><td colspan="6" class="dtPanel_Main"></td>
		</tr>
		<tr>
			<td class="dtPanel_Top01">������</td><td colspan="6" class="dtPanel_Main"></td>
		</tr>
		<tr>
			<td colspan="7" class="dtPanel_Top01">�����������</td>
		</tr>
		<tr class="dtPanel_Main">
			<td colSpan="2" class="dtPanel_Top01">������������</td>
			<td class="dtPanel_Top01">�ɹ���</td>
			<td class="dtPanel_Top01">����ƻ���ֹʱ��</td>
			<td colSpan="3" class="dtPanel_Top01">˵��</td>
		</tr>
		<%List<Weekly> report = (List)request.getAttribute("report");
		if(report != null){
			for(Weekly w : report){%>
			<tr class="dtPanel_Main">
				<td colspan="2"><%=w.getProjectName() %></td>
				<td ><%=w.getStep() %></td>
				<td ><%=w.getDating() %></td>
				<td colspan="3"><%=w.getMemo() %></td>
			</tr>	
		<%	}
		}
		%>
		<tr>
			<td colspan="7" class="dtPanel_Top01">���ܼƻ�����</td>
		</tr>
		<tr>
			<td colspan="2" class="dtPanel_Top01">������������</td>
			<td class="dtPanel_Top01">�ɹ���</td>
			<td class="dtPanel_Top01">����ƻ���ֹʱ��</td>
			<td colspan="3" class="dtPanel_Top01">˵��</td>
		</tr>
		
		<%List<Weekly> plan = (List)request.getAttribute("plan");
		if(plan != null){
			for(Weekly w : plan){%>
			<tr class="dtPanel_Main">
				<td colspan="2"><%=w.getProjectName() %></td>
				<td ><%=w.getStep() %></td>
				<td ><%=w.getDating() %></td>
				<td colspan="3"><%=w.getMemo() %></td>
			</tr>	
		<%	}
		}
		%>
		
		<tr>
			<td colspan="7" class="dtPanel_Top01">��Ŀ�������⼰�������</td>
		</tr>
		<tr>
			<td class="dtPanel_Top01" nowrap="nowrap">���</td>
			<td colspan="2" class="dtPanel_Top01" nowrap="nowrap">������������</td>
			<td class="dtPanel_Top01" nowrap="nowrap">��ǰ��ȡ�Ĵ�ʩ</td>
			<td class="dtPanel_Top01" nowrap="nowrap">��Ҫ��֧��</td>
			<td class="dtPanel_Top01" nowrap="nowrap">�������</td>
			<td class="dtPanel_Top01" nowrap="nowrap">˵��</td>
		</tr>
		<%List<RiskRecord> l = (List)request.getAttribute("risk");
		if(l != null){
			for(RiskRecord w : l){%>
			<tr class="dtPanel_Main">
				<td ><%=w.getProjectId()%></td>
				<td colspan="2"><%=w.getRiskName()%></td>
				<td width="25%"><%=w.getSolution() %></td>
				<td ></td>
				<td ><%=w.getInputDate()%></td>
				<td width="25%"><%=w.getMemo()%></td>
			</tr>	
		<%	}
		}
		%>
	</table>
	<p>&nbsp;</p>
</body>
</html>