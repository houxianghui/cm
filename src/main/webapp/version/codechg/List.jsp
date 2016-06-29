<%@page import="com.blue.version.codechg.CodeChg"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>代码变更维护</title>
<script language="javascript"> 
$(function(){
	$(":button").attr("class","Button");
	$("#return").click(function(){
		window.location.href="VersionHis.do?act=list";
	});
});
function doEdit(){ 
	document.getElementById('codechg').act.value='maintain';	 
	document.getElementById('codechg').submit(); 
} 

function turnPage( pagenm ) {   
   	document.getElementById('codechg').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('codechg').pageNO.value = pagenm;     
   	document.getElementById('codechg').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="CodeChg.do" styleId="codechg">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("代码变更维护")%>
	
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td>序号</td>
			<td>&nbsp;修改类型</td>
			<td nowrap>&nbsp;程序名</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 1;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			CodeChg vo = (CodeChg) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=i++ %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CHG_TYPE, vo.getChgType()) %></td>
			<td><%=vo.getFileName()%></td>
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
			<auth:button value="维护" onClick="doEdit()" id="release" name="edit"></auth:button>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>