
<%@page import="com.projectmaintain.NotFullVO"%>
<%@page import="com.eis.portal.user.UserVO"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="projectDistributeForm" class="com.projectmaintain.ProjectDistributeForm"></jsp:useBean>
<head>
<title>δ������ʱԱ��</title>
<script type="text/javascript">

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
<html:hidden property="act" value="nf"/>
	<%=ViewUtil.getTitle("δ������ʱԱ��")%>

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
		<html:text property="date" styleId="date" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"></html:text>����
		<html:text property="toDate" styleId="date" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"></html:text>
		<html:submit value="����" styleClass="Button"></html:submit>
		</td></tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td colspan="4">��Ա�б�</td>
		</tr>
		
			<%
				if(request.getAttribute("nf")!=null ){
					Map<String,List<NotFullVO>> m = (Map)request.getAttribute("nf");
					for(String s : m.keySet()){
						List<NotFullVO> l = m.get(s);
						String depart = "<td rowspan='"+l.size()+"'>"+SingleDicMap.getDicItemVal(SingleDic.DEPART, s)+"</td>";
						
						for(NotFullVO n : l){
							out.print("<tr align=\"left\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\">");
							out.print(depart);
							out.print("<td>"+n.getUserName() +"</td>");
							out.print("<td>"+n.getDate() +"</td>");
							out.print("<td>"+n.getCost() +"</td>");
							out.print("</tr>");
							depart = "";
						}
						
					}
					
				}%>
	</table>
	<table align=center width="98%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
