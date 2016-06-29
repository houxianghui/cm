<%@page import="com.blue.version.subsysversion.SubSysVersion"%>
<%@page import="com.blue.version.versionhis.VersionHisVO"%>
<%@page import="com.blue.enums.YesOrNo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>备选版本维护</title>

<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	});
	$("#sub").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		//提交表单 
		location.href="SubSysVersion.do?act=list&versionId="+document.forms[0].versionId.value;
	}).attr("class","Button");
	$("#version").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		document.forms[0].act.value='tabs';	 
		document.forms[0].submit(); 
	});
	$("#release").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		//提交表单 
		document.forms[0].act.value='view';	 
		document.forms[0].submit(); 
	});

});
function doUpload(){
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	window.location.href="ProjectFiles.do?act=list&versionId="+document.forms[0].versionId.value;
}
function doAdd(){
	location.href="VersionHis.do?act=new";
}
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
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
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
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
function detail() { 
	//检查是否有选中的纪录 
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 	window.location.href="VersionHis.do?act=view&versionId="+document.forms[0].versionId.value;
} 
 
function doQuery() {  
	document.forms[0].act.value = "<%=request.getParameter("act") %>";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].versionId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "<%=request.getParameter("act") %>";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="VersionHis.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("备选版本维护")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			备选版本号：
			<html:text property="versionId_f" styleClass="Textfield" size="10"></html:text>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	
	<%List<String> subSys = (List)request.getAttribute("subSys");%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td rowspan="2" nowrap="nowrap">&nbsp;预计发布日期</td>
			<td rowspan="2" nowrap="nowrap">&nbsp;备选版本号</td>
			<td rowspan="2" nowrap="nowrap">是否已发布</td>
			<td rowspan="2" nowrap="nowrap">实际发布日期</td>
			<td colspan="<%=subSys.size() %>" align="center">子系统</td>
			<td rowspan="2" nowrap="nowrap">版本描述</td>

		</tr>
		<tr align="left" class="dtPanel_Top01" height="28">
		<%
		for(String t : subSys){
		%>
			<td align="center"><%out.print(t); %></td>
		<%} %>
		</tr>
		<%
	List list = (List)request.getAttribute("list");
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			VersionHisVO vo = (VersionHisVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			
			<td><%=vo.getPlanReleaseDate() %></td>
			<td><%=vo.getVersionId()%></td>
			<td><%=YesOrNo.valueOf(vo.getIsReleased()).getDesc()%></td>
			<td><%=vo.getReleaseDate()%></td>
			<%for(String t : subSys){ 
				SubSysVersion v = null;
				if(vo.getSubSys() != null){
					v = vo.getSubSys().get(t);
				}
				if(v != null){
					out.print("<td>"+v.getNextVersion()+"</td>");
				}else{
					out.print("<td></td>");
				}
			}%>
			
			
			<td><%=vo.getMemo()%></td>
			</label></td>
		</tr>

		<%}
}%>

</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>