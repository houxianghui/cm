<%@page import="com.blue.enums.RiskStatus"%>
<%@page import="com.blue.enums.RiskType"%>
<%@page import="com.blue.enums.RiskLv"%>
<%@page import="com.blue.project.riskrecord.RiskRecord"%>
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
	document.forms[0].act.value='init';	 
	document.forms[0].submit();  
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
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
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
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
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 	window.location.href="RiskRecord.do?act=list&id="+document.forms[0].id.value;
} 
 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "list";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="RiskRecord.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<input type="hidden" name="projectId" value="<%=request.getAttribute("projectId") %>"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("����ά��")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;���</td>
			<td>&nbsp;���գ����⣩����</td>
			<td nowrap>&nbsp;���գ����⣩����</td>
			<td>���գ����⣩����</td>
			<td>���գ����⣩״̬</td>
			<td>�����</td>
			<td>�������</td>
			<td>�����������</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			RiskRecord vo = (RiskRecord) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=++i%></td>
			<td><%=vo.getRiskName() %></td>
			<td><%=RiskType.valueOf(vo.getRiskType()).getDesc() %></td>
			<td><%=RiskLv.valueOf(vo.getRiskLv()).getDesc() %></td>
			<td><%=vo.getRiskStatus() == null?"":RiskStatus.valueOf(vo.getRiskStatus()).getDesc() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getUserId()) %></td>
			<td align="center"><%=DateUtil.format(vo.getInputDate()) %></td>
			<td align="center"><%=DateUtil.format(vo.getSoluteDate()) %></td>
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
			<input type="button" value="����" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸�" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ��" class="Button" onclick="doDelete()"/>
			<input type="button" value="����" class="Button" onclick="history.back(-1)">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>