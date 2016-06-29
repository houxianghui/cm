<%@page import="com.blue.projectchgrecord.ChangeDetail"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>变更明细维护</title>

<script language="javascript"> 

function doAdd(){ 
	document.forms[0].act.value='init';	 
	document.forms[0].submit();  
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].detailId.value == null ||document.forms[0].detailId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].detailId.value == null ||document.forms[0].detailId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].detailId.value=id_var; 
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
<html:form method="post" action="ChangeDetail.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="detailId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("数据库变更明细维护")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;序号</td>
			<td nowrap>&nbsp;变更表名</td>
			<td>&nbsp;操作类别</td>
			<td nowrap>&nbsp;描述</td>
			<td align="center">选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		int i = 1;
		while (iter.hasNext()) {
			ChangeDetail vo = (ChangeDetail) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td>&nbsp;<%=i++%></a></td>
			<td>&nbsp;<%=vo.getChangeContent()%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DB_CHG_ACT,  vo.getType())%></td>
			<td><%=vo.getMemo()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getDetailId()%>')">
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
			<input type="button" value="增加" class="Button" onClick="doAdd()"/>
			<input type="button" value="修改" class="Button" onClick="doEdit()"/>
			<input type="button" value="删除" class="Button" onclick="doDelete()"/>
			<input type="button" value="返回" class="Button" onclick="history.back(-1)">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>