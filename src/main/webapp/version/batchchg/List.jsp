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
<title>批量变更维护</title>

<script language="javascript"> 

function doAdd(){
	window.location.href="BatchChg.do?act=new&versionId="+document.getElementById('batchchg').versionId.value;
}
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.getElementById('batchchg').id.value == null ||document.getElementById('batchchg').id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.getElementById('batchchg').act.value='edit';	 
	document.getElementById('batchchg').submit(); 
} 

function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.getElementById('batchchg').id.value == null ||document.getElementById('batchchg').id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.getElementById('batchchg').act.value='delete'; 
	document.getElementById('batchchg').submit(); 
} 

 
function doQuery() {  
	document.getElementById('batchchg').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('batchchg').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('batchchg').id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('batchchg').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('batchchg').pageNO.value = pagenm;     
   	document.getElementById('batchchg').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="BatchChg.do" styleId="batchchg">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("批量变更维护")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;作业名</td>
			<td>&nbsp;子系统</td>
			<td>变更类型</td>
			<td>触发条件</td>
			<td>上一步骤</td>
			<td>下一步骤</td>
			<td align="center">选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			BatchChg vo = (BatchChg) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getBatchName()%></td>
			<td><%=vo.getSubSys() %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CHG_TYPE, vo.getChgType())%></td>
			<td><%=vo.getTriggerType()%></td>
			<td><%=vo.getPreStep()%></td>
			<td><%=vo.getNextStep()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>')">
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
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>