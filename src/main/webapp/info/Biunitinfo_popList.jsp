<%@page import="com.yly.info.Biunitinfotb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>���뵥λ�б�</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "popList";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "popList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Biunitinfo.do">
<input type=hidden name=act value="popList">
<html:hidden property="leadStore"/>
<%=ViewUtil.getTitle("���뵥λ�б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
 			���뵥λ���:
			<html:text property="chnshort" styleClass="Textfield" size="16" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>��λ����</td>
			<td>��ҵ����</td>
			<td>��λ���ļ��</td>
			<td>��ϵ��</td>
			<td>�绰</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Biunitinfotb vo = (Biunitinfotb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getUnitid()%></td>	
			<td><%=vo.getHyName()%></td>	
			<td><%=vo.getChnshort()%></td>			
			<td><%=vo.getUnitperson() %></td>	
			<td><%=vo.getUnittel()%></td>
		</tr>

		<%}
}
}%>

</table>
	<%
//������ҳ��ע 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

</html:form>

<p>&nbsp;</p>
</body>
</html>


