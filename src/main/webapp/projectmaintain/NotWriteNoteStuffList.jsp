
<%@page import="com.eis.portal.user.UserVO"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="projectDistributeForm" class="com.projectmaintain.ProjectDistributeForm"></jsp:useBean>
<head>
<title>δ��д��ʱԱ��</title>
<script type="text/javascript">
function download(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('��ѡ������');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('��ѡ�񱨱�ͷ����');
		return;
	}
	window.location.href="WorkDaily.do?act=download&date="+date+"&title="+title;
}
function preView(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('��ѡ������');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('��ѡ�񱨱�ͷ����');
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

	<%=ViewUtil.getTitle("δ��д��ʱԱ��")%>

	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr class="dtPanel_Main" align="center">
		<td>
		<input type="button" id="all" value="ȫѡ" style="width:50px">
		</td>
		<td rowspan="2" width="90%" align="left">
		<%out.print(request.getAttribute("checkbox")); %>
		</td>
	</tr>
	<tr align="center"><td>
		<input type="button" id="allnot" value="ȫ��ѡ" style="width:50px">
		</td>
	</tr>
		<tr align="center" class="dtPanel_Main"><td colspan="2">������ڣ�
		<html:text property="date" styleId="date" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"></html:text>
		<html:submit value="����" styleClass="Button"></html:submit>
		</td></tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td colspan="4">��Ա�б�</td>
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
			 <input name="return" type="button"	class="Button" value="����" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
