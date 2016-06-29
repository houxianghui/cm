<%@page import="com.blue.enums.CheckStatus"%>
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
<title>工时记录</title>

<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	})
})
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].recordId.value == null ||document.forms[0].recordId.value == "") { 
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
	if(document.forms[0].recordId.value == null ||document.forms[0].recordId.value == "") { 
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
function detail() { 
	//检查是否有选中的纪录 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 	window.location.href="DailyRecord.do?act=list&recordId="+document.forms[0].id.value;
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
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="recordId"/>
<%=ViewUtil.getTitle("工时记录")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			项目编号：
			<html:text property="projectId" styleClass="Textfield" size="10"></html:text>
			填报日期：
			<html:text property="workDate" styleClass="Textfield" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目阶段</td>
			<td>填报日期</td>
			<td>工时（小时）</td>
			<td>录入日期</td>
			<td>审批状态</td>
			<td>拒绝原因</td>
			<td align="center">选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			DailyRecord vo = (DailyRecord) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )"  >
			<td><%=vo.getProjectId()%></td>
			<td><%=Steps.valueOf(vo.getStep()).getDesc() %></td>
			<td><%=vo.getWorkDate()%></td>
			<td><%=vo.getTaskCost() %></td>
			<td><%=vo.getInputDate() %></td>
			<%if(vo.getChecked().equals(CheckStatus.R.toString())){ 
				out.print("<td bgcolor=\"yellow\">");
			}else{
				out.print("<td>");
			}%>
			<%=CheckStatus.valueOf(vo.getChecked()).getDesc() %></td>
			<td><%=vo.getRefuseReason()==null?"":vo.getRefuseReason() %></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getRecordId()%>')">
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
			<%String flag = (String)request.getAttribute("NOACT");if(CheckUtil.isEmptry(flag)){ %>
			<input type="button" value="修改" class="Button" onClick="doEdit()"/>
			<input type="button" value="删除" class="Button" onclick="doDelete()"/>
			<input type="button" value="返回" class="Button" onclick="history.back(-1)">
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>