
<%@page import="com.eis.portal.user.UserVO"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="projectDistributeForm" class="com.projectmaintain.ProjectDistributeForm"></jsp:useBean>
<head>
<title>未填写工时员工</title>
<script type="text/javascript">
function download(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('请选择日期');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('请选择报表头名称');
		return;
	}
	window.location.href="WorkDaily.do?act=download&date="+date+"&title="+title;
}
function preView(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('请选择日期');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('请选择报表头名称');
		return;
	}
	openWin("WorkDaily.do?act=preview&date="+date+"&title="+title);
}
$(function() {
	$(":input[type='button']").attr("class","Button").attr("width","50");
	
	$("#all").click(function(){
		$("input[name='ids']").attr("checked",true);
	});
	$("#allnot").click(function(){
		$("input[name='ids']").attr("checked",false);
	});
});
</script>
</head>
<body class="body_bg_grey1">
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="NotWriteNoteStuff.do">

	<%=ViewUtil.getTitle("未填写工时员工")%>

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
		<tr align="center" class="dtPanel_Main"><td colspan="2">检查日期：
		<html:text property="date" styleId="date" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"></html:text>
		<html:submit value="搜索" styleClass="Button"></html:submit>
		</td></tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td colspan="4">人员列表</td>
		</tr>
		
			<%
				if(request.getAttribute("stuff")!=null ){
					java.util.List<UserVO> l = (List)request.getAttribute("stuff");
					int itemPerRow = 4;
					for(UserVO u : l){
						if(itemPerRow == 4){
							%>
							<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
							<%
						}
						%>
							<td>
							<%=u.getUser_name() %>-<%=SingleDicMap.getDicItemVal(SingleDic.DEPART, u.getDepart_id()) %>
							</td>
						
						<%
						itemPerRow--;
						if(itemPerRow == 0){
							itemPerRow = 4;
							%>
							</tr>
							<%
						}
					}
					while(itemPerRow != 0){
						itemPerRow--;
						%><td></td><%
					}
				}%>
	</table>
	<table align=center width="98%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom">
			 <input name="return" type="button"	class="Button" value="返回" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
