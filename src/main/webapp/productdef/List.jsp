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
<title>产品管理</title>

<script language="javascript"> 

function doAdd(){
	window.location.href="ProductDef.do?act=new";
}
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.getElementById('productCode').act.value='edit';	 
	document.getElementById('productCode').submit(); 
} 

function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
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
	//检查是否有选中的纪录 
	if(document.getElementById('productCode').productCode.value == null ||document.getElementById('productCode').productCode.value == "") { 
		alert('请先选择纪录'); 
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
<%=ViewUtil.getTitle("产品管理")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;产品代号</td>
			<td nowrap>&nbsp;产品名称</td>
			<td nowrap>&nbsp;产品经理</td>
			<td nowrap>&nbsp;产品最新版本号</td>
			<td nowrap>&nbsp;最后发布日期</td>
			<td nowrap>&nbsp;下一发布日期</td>
			<td nowrap>&nbsp;子模块数</td>
			<td nowrap>&nbsp;产品描述</td>
			<td align="center">选择</td>
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
//产生翻页脚注 
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
			<auth:button value="增加" onClick="doAdd()" id="release" name="add"></auth:button>
			<auth:button value="修改" onClick="doEdit()" id="release" name="edit"></auth:button>
			<auth:button value="删除" onClick="doDelete()" id="release" name="delete"></auth:button>
			<auth:button value="模块划分" onClick="doModule()" id="release" name="module"></auth:button>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>