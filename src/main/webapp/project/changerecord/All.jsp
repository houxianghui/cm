<%@page import="com.blue.enums.ChangeLevel"%>
<%@page import="com.blue.enums.ChangeType"%>
<%@page import="com.blue.project.changerecord.ChangeRecordBO"%>
<%@page import="com.blue.project.changerecord.ChangeRecord"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>���ά��</title>

<script language="javascript"> 

function doAdd(){ 
	document.forms[0].act.value='init';	 
	document.forms[0].submit();  
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].recordId.value == null ||document.forms[0].recordId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].recordId.value == null ||document.forms[0].recordId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
function detail() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].recordId.value == null ||document.forms[0].recordId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 	window.location.href="ChangeRecord.do?act=list&id="+document.forms[0].recordId.value;
} 
 
function doQuery() {  
	document.forms[0].act.value = "all";
	document.forms[0].submit(); 
} 
function setPKey(id_var) { 
	document.forms[0].recordId.value=id_var; 
} 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "all";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="ChangeRecord.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="recordId"/>
<%=ViewUtil.getTitle("���ά��")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;���</td>
			<td nowrap>&nbsp;������</td>
			<td>&nbsp;�������</td>
			<td nowrap>&nbsp;������</td>
			<td>�������</td>
			<td>�����</td>
			<td>�������</td>
			<td align="center">ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ChangeRecord vo = (ChangeRecord) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td align="center"><%=++i%></td>
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>">&nbsp;<%=ChangeRecordBO.formatChangeId(vo) %></a></td>
			<td><%=vo.getChangeTitle() %></td>
			<td><%=ChangeType.valueOf(vo.getChangeType()).getDesc() %></td>
			<td><%=ChangeLevel.valueOf(vo.getChangeLevel()).getDesc() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getUserId()) %></td>
			<td align="center"><%=DateUtil.format(vo.getChangeDate()) %></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getRecordId()%>')">
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
			<input type="button" value="����" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸�" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ��" class="Button" onclick="doDelete()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>