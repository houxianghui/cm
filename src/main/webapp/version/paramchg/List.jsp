<%@page import="com.blue.version.paramchg.ParamChg"%>
<%@page import="com.blue.version.reportchg.ReportChg"%>
<%@page import="com.blue.version.batchchg.BatchChg"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>�������ά��</title>

<script language="javascript"> 

function doAdd(){ 
	window.location.href="ParamChg.do?act=new&versionId="+document.getElementById('paramchg').versionId.value;
} 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('paramchg').id.value == null ||document.getElementById('paramchg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('paramchg').act.value='edit';	 
	document.getElementById('paramchg').submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('paramchg').id.value == null ||document.getElementById('paramchg').id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('paramchg').act.value='delete'; 
	document.getElementById('paramchg').submit(); 
} 

 
function doQuery() {  
	document.getElementById('paramchg').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('paramchg').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('paramchg').id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('paramchg').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('paramchg').pageNO.value = pagenm;     
   	document.getElementById('paramchg').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="ParamChg.do" styleId="paramchg">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("�������ά��")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;��ϵͳ</td>
			<td>&nbsp;һ���˵�</td>
			<td>�����˵�</td>
			<td>��������</td>
			<td>�������</td>
			<td>���ǰ</td>
			<td>�����</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ParamChg vo = (ParamChg) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getSubSys()%></td>
			<td><%=vo.getParentMenu() %></td>
			<td><%=vo.getSubMenu() %></td>
			<td><%=vo.getMemo()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CHG_TYPE, vo.getChgType())%></td>
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