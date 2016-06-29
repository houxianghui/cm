<%@page import="com.blue.product.ModuleDefVO"%>
<%@page import="com.blue.product.ModuleDef"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>ģ�黮��</title>

<script language="javascript"> 

function doAdd(){
	window.location.href="ModuleDef.do?act=new&productCode="+$(":input[name='productCode']").val();
}
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('product').moduleId.value == null ||document.getElementById('product').moduleId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('product').act.value='edit';	 
	document.getElementById('product').submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('product').moduleId.value == null ||document.getElementById('product').moduleId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('product').act.value='delete'; 
	document.getElementById('product').submit(); 
} 

 
function doQuery() {  
	document.getElementById('product').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('product').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('product').moduleId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('product').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('product').pageNO.value = pagenm;     
   	document.getElementById('product').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="ModuleDef.do" styleId="product">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="productCode"/>
<html:hidden property="moduleId"/>
<%=ViewUtil.getTitle("ģ�����")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;ģ�����</td>
			<td nowrap>&nbsp;ģ������</td>
			<td nowrap>&nbsp;ģ�鸺����</td>
			<td>ģ�������</td>
			<td nowrap>&nbsp;�汾��</td>
			<td align="center">ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ModuleDefVO vo = (ModuleDefVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getModuleId()%></td>
			<td><%=vo.getName()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getManagerId())%></td>
			<td><%=vo.getParticipate() %></td>
			<td><%=vo.getVersion()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getModuleId()%>')">
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
			<input type="button"" value="����" onclick="history.back()" class="Button">
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>