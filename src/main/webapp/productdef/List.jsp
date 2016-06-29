<%@page import="com.blue.product.ProductDefVO"%>
<%@page import="com.blue.product.ProductDef"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>��Ʒ����</title>

<script language="javascript"> 

function doAdd(){
	window.location.href="ProductDef.do?act=new";
}
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('productCode').act.value='edit';	 
	document.getElementById('productCode').submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('productCode').act.value='delete'; 
	document.getElementById('productCode').submit(); 
} 

 
function doQuery() {  
	document.getElementById('productCode').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('productCode').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('productCode').productCode.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('productCode').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('productCode').pageNO.value = pagenm;     
   	document.getElementById('productCode').submit(); 
} 
function doModule(){
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	window.location.href="ModuleDef.do?act=list&productCode="+$(":input[name='productCode']").val();
}
</script>
</head>
<body>
<html:form method="post" action="ProductDef.do" styleId="productCode">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="productCode"/>
<%=ViewUtil.getTitle("��Ʒ����")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;��Ʒ����</td>
			<td nowrap>&nbsp;��Ʒ����</td>
			<td nowrap>&nbsp;��Ʒ����</td>
			<td nowrap>&nbsp;��Ʒ���°汾��</td>
			<td nowrap>&nbsp;��󷢲�����</td>
			<td nowrap>&nbsp;��һ��������</td>
			<td nowrap>&nbsp;��ģ����</td>
			<td nowrap>&nbsp;��Ʒ����</td>
			<td align="center">ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProductDefVO vo = (ProductDefVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getProductCode()%></td>
			<td><%=vo.getName()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getManagerId())%></td>
			<td><%=vo.getLatestVersion()%></td>
			<td><%=vo.getLastPrdDate() %></td>
			<td><%=vo.getNextPrdDate()%></td>
			<td><%=vo.getModuleCount()%></td>
			<td><%=vo.getMemo()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProductCode()%>')">
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
			<auth:button value="ģ�黮��" onClick="doModule()" id="release" name="module"></auth:button>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>