<%@page import="com.blue.version.databasechg.DatabaseChg"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>���ݿ���ά��</title>

<script language="javascript"> 

function doAdd(){
	window.location.href="DatabaseChg.do?act=new&versionId="+document.getElementById('databasechg').versionId.value;
}
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('databasechg').id.value == null ||document.getElementById('databasechg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('databasechg').act.value='edit';	 
	document.getElementById('databasechg').submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('databasechg').id.value == null ||document.getElementById('databasechg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('databasechg').act.value='delete'; 
	document.getElementById('databasechg').submit(); 
} 

 
function doQuery() {  
	document.getElementById('databasechg').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('databasechg').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('databasechg').id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('databasechg').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('databasechg').pageNO.value = pagenm;     
   	document.getElementById('databasechg').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="DatabaseChg.do" styleId="databasechg">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("���ݿ���ά��")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;���ݿ��</td>
			<td>��ϵͳ</td>
			<td>�������</td>
			<td>�ű�</td>
			<td>����</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			DatabaseChg vo = (DatabaseChg) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getTableName()%></td>
			<td><%=vo.getSubSys()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CHG_TYPE,vo.getChgType())%></td>
			<td><%=vo.getOriginl()%></td>
			<td><%=vo.getTarget()%></td>
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