<%@page import="com.blue.version.datachg.DataChg"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>����ά��</title>

<script language="javascript"> 
function doAdd(){ 
	window.location.href="DataChg.do?act=new&versionId="+document.getElementById('datachg').versionId.value;
} 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('datachg').id.value == null ||document.getElementById('datachg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('datachg').act.value='edit';	 
	document.getElementById('datachg').submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('datachg').id.value == null ||document.getElementById('datachg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('datachg').act.value='delete'; 
	document.getElementById('datachg').submit(); 
} 

 
function doQuery() {  
	document.getElementById('datachg').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('datachg').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('datachg').id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('datachg').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('datachg').pageNO.value = pagenm;     
   	document.getElementById('datachg').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="DataChg.do" styleId="datachg">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("����ά��")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;�ű�����</td>
			<td>&nbsp;˵��</td>
			<td align="center">ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			DataChg vo = (DataChg) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getShellName()%></td>
			<td><%=vo.getMemo() %></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>')">
			</label></td>
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
			<%String flag = (String)request.getAttribute("NOACT");if(CheckUtil.isEmptry(flag)){ %>
			<auth:button value="����" onClick="doAdd()" id="release" name="add"></auth:button>
			<auth:button value="�޸�" onClick="doEdit()" id="release" name="edit"></auth:button>
			<auth:button value="ɾ��" onClick="doDelete()" id="release" name="delete"></auth:button>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>