<%@page import="com.yly.issue.Issuetask"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��������</title>
<script language="javascript"> 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="list">
<html:hidden property="appNo"/>
<%=ViewUtil.getTitle("��������")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
				<td width="10%">���������</td>
				<td width="10%">��Ʒ����</td>
				<td width="10%">��������</td>
				<td width="10%">��Կ����</td>	
				<td width="10%">��Ʒͨ������</td>						
				<td width="10%">��ƷӦ������</td>	
				<td width="10%">ģ�����汾</td>
				<td width="10%">�Ƿ���֤</td>
				<td width="10%">�Ƿ�װ��ά����Կ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Issuetask vo = (Issuetask) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
					<td><a href="Issuetaskctrl.do?act=list&taskNo=<%=vo.getTaskNo()%>"><%=vo.getTaskNo()%></a></td>	
					<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
					<td><%=vo.getIssueAmt()%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId())) %></td>
					<td><%=vo.getBinFileVer()!=null?SingleDicMap.getDicItemVal(SingleDic.BINFILEVER, vo.getBinFileVer()):""%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getAuthSign().toString())%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getW2Sign().toString())%></td>
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

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="����" class="Button" onClick="history.back()"/>
			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


