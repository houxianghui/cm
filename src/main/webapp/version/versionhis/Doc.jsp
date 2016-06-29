<%@page import="com.blue.projectfiles.ProjectFiles"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>�����б�</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

</script>
</head>
<body>

<html:form method="post" action="VersionHis.do">
<input type=hidden name=act value="viewDoc">


<%=ViewUtil.getTitle("�ĵ�����")%>

	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" >
			<td>��Ŀ���</td>
			<td>���汾��</td>
			<td>�ļ���</td>
			<td nowrap>�ļ���С(�ֽ�)</td>			
			<td nowrap>�ļ�����</td>
			<td>�ϴ�ʱ��</td>
			<td nowrap>�ϴ���Ա</td>
			<td>��ע</td>
		</tr>
		<%if (pageResult != null) {
			List list = pageResult.getList();
			if (list != null) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					ProjectFiles p = (ProjectFiles)iter.next();
		%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=p.getProjectId()==null?"":p.getProjectId() %></td>
			<td><%=p.getVersionId()==null?"":p.getVersionId() %></td>
			<td align="left"><a href="ProjectFiles.do?act=download&id=<%=p.getId() %>"><%=p.getFileName() %></a></td>
			<td><%=p.getFileSize() %></td>
			<td><%=p.getFileType() %></td>
			<td><%=p.getUpdateDate() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getUserId()) %></td>
			<td><%=p.getMemo()%></td>
		</tr>

		<%	}
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


