<%@page import="com.blue.report.costoftype.WorkLoadBO"%>
<%@page import="com.blue.report.costoftype.WorkLoad"%>
<%@ include file="/includes/common.jsp"%>
<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>工时统计</title>

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
		$("#all").click(function(){
			$("input[name='ids']").attr("checked",true);
		});
		$("#allnot").click(function(){
			$("input[name='ids']").attr("checked",false);
		});
	});
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<html:form method="post" action="WorkLoad.do">
		<input type="hidden" name="act"
			value="<%=request.getParameter("act")%>" />

		<%=ViewUtil.getTitle("工时报表")%>
	
		<table class="dtPanel_Line1" width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr class="dtPanel_Main">
		<td>
		<input type="button" id="all" value="全选" style="width:50px">
		</td>
		<td rowspan="2" align="left" width="90%">
		<%out.print(request.getAttribute("checkbox")); %>
		</td>
		<tr><td>
		<input type="button" id="allnot" value="全不选" style="width:50px">
		</td></tr>
		<tr class="dtPanel_Main">
			<td colspan="2">
			起始日期：
			<html:text property="startDate" styleClass="Textfield" size="8"></html:text>
			结束日期：
			<html:text property="endDate" styleClass="Textfield" size="8"/>
			<input type="button" id="search" value="搜索">
			<input type="button" id="export" value="导出">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;员工编号</td>
			<td nowrap>&nbsp;员工姓名</td>
			<td nowrap>所属部门</td>
			<%String[] desc = StringUtil.getTypesDesc();
			for(String s:desc){%>
				<td><%=s %></td>
			<%} %>
			<td>总计</td>
		</tr>
		<%
		Map<String,WorkLoad> m = (Map)request.getAttribute("map");
		Map<String,Double> totalTypes = WorkLoadBO.getTotalMap(m);
		double total = 0;
		if(m != null){
			Set<String> set = m.keySet();
			List<WorkLoad> l = new ArrayList<WorkLoad>();
			for(String s : set){
				l.add(m.get(s));
			}
			Collections.sort(l);
			String[] types = StringUtil.getTypes();
			
			for(WorkLoad wl:l){
				String s = wl.getUser().getUser_id();
			%>
			<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
				<td><%=wl.getUser().getUser_id() %></td>
				<td><%=wl.getUser().getUser_name() %></td>
				<td><%=SingleDicMap.getDicItemVal(SingleDic.DEPART, wl.getUser().getDepart_id()) %></td>
				<%for(String t : types) {%>
				<td><%=wl.getWorkCost()==null?"":wl.getWorkCost().get(t)==null?"":wl.getWorkCost().get(t) %></td>
				<%} %>
				<td><%=wl.getTotal() %></td>
			</tr>
			<%total+=wl.getTotal();} %>
			<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
				<td>人员合计</td><td><%=set.size() %></td>
				<td>工时合计</td>
				<%for(String s:types){ %>
				<td><%=totalTypes.get(s)==null?0:totalTypes.get(s) %></td>
				<%} %>
				<td></td>
			</tr>
			<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
				<td colspan="2">&nbsp;</td>
				<td>总计</td><td colspan="<%=types.length+1%>"><%=total %></td>
			</tr>	
		<%}%>
</table>
	</html:form>

	<p>&nbsp;</p>
</body>
</html>