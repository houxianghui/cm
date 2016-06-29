<%@page import="com.blue.dailyrecord.DailyRecordVO"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.dailyrecord.DailyRecord"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>工时审核</title>

<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	});
	$(":button").button();
	$("#checkAll").click(function(){
		var checked = $(this).attr("checked");
		var flag = false;
		if(checked){
			flag = true;
		}
		$("input[name='ids']").attr("checked",flag);
	});
	$("#pass").click(function(){
		var checks = $(":checkbox[name='ids']:checked");
		var ids="";
		checks.each(function(){
			ids+=$(this).val()+",";
		});
		if(ids == ""){
			alert("请先选择记录");
			return;
		}
		window.location.href="DailyRecord.do?act=pass&ids="+ids;
	});
	$("#all").click(function(){
		$("input[name='departIds']").attr("checked",true);
	});
	$("#allnot").click(function(){
		$("input[name='departIds']").attr("checked",false);
	});
});

function doPassRecord(id){
	window.location.href="DailyRecord.do?act=pass&ids="+id;
}
function doRefuse(id){
	var s = prompt('请输入修改原因','');
	if(s == null || s==""){
		alert("请输入修改原因");
		return;
	}
	s = encodeURI(encodeURI(s));
	window.location.href="DailyRecord.do?act=refuse&ids="+id+"&reason="+s;
}

 
function doQuery() {  
	document.forms[0].act.value = "<%=request.getParameter("act") %>";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].recordId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "<%=request.getParameter("act") %>";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="DailyRecord.do">
<input type="hidden" name="act" value="checklist"/>
<html:hidden property="recordId"/>
<%=ViewUtil.getTitle("工时审核")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			</td>
		</tr>
	</table>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr class="dtPanel_Main" align="center">
		<td>
		<input type="button" id="all" value="全选" style="width:50px">
		</td>
		<td rowspan="2" width="90%" align="left">
		<%out.print(request.getAttribute("checkbox")); %>
		</td>
	</tr>
	<tr align="center"><td>
		<input type="button" id="allnot" value="全不选" style="width:50px">
		</td>
	</tr>
		<tr align="center" class="dtPanel_Main"><td colspan="2">
		<html:submit value="搜索" styleClass="Button"></html:submit>
		</td></tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td align="center"><input type="checkbox" id="checkAll"></td>
			<td nowrap>&nbsp;项目编号</td>
			<td>项目名称</td>
			<td>&nbsp;项目阶段</td>
			<td>填报日期</td>
			<td>工时（小时）</td>
			<td>工作内容</td>
			<td>拒绝原因</td>
			<td>姓名</td>
			<td align="center">审核</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			DailyRecordVO vo = (DailyRecordVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td align="center"><input type="checkbox" name="ids" value="<%=vo.getRecordId() %>"></td>
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<td><%=vo.getProjectName() %></td>
			<td><%=Steps.valueOf(vo.getStep()).getDesc() %></td>
			<td><%=vo.getWorkDate()%></td>
			<td><%=vo.getTaskCost() %></td>
			<td><%=vo.getWorkMemo() %></td>
			<td><%=vo.getRefuseReason()==null?"":vo.getRefuseReason() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,  vo.getUserId()) %></td>
			<td align="center"><label>
			
			<input type="button" value="拒绝" onclick="doRefuse('<%=vo.getRecordId()%>');return;"></td>
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
		<tr align="center">
		<td><input type="Button" value="批量通过" id="pass"></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>