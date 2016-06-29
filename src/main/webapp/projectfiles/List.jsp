<%@page import="com.blue.projectfiles.ProjectFiles"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>附件列表</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

function setPKey(id_var) { 
	document.forms[0].id.value=id_var; 
} 
$(function(){
	$("#add").attr("class","Button").click(function(){
		window.location.href="ProjectFiles.do?act=new&projectId="+document.forms[0].projectId.value+"&versionId="+document.forms[0].versionId.value;
	});
	$("#delete").attr("class","Button").click(function(){
		var checks = $(":checkbox[name='ids']:checked");
		var ids="";
		checks.each(function(){
			ids+=$(this).val()+",";
		});
		if(ids == ""){
			alert("请先选择记录");
			return;
		}
		if(!confirm("确定删除吗？")){
			return;
		}
		window.location.href="ProjectFiles.do?act=delete&ids="+ids+"&projectId="+document.forms[0].projectId.value+"&versionId="+document.forms[0].versionId.value;
	});
	$("#back").attr("class","Button").click(function(){
		history.back(-1);
	});
	$("#checkAll").click(function(){
		var checked = $(this).attr("checked");
		var flag = false;
		if(checked){
			flag = true;
		}
		$("input[name='ids']").attr("checked",flag);
	});
});
</script>
</head>
<body>

<html:form method="post" action="ProjectFiles.do">
<input type=hidden name=act value="list">
<html:hidden property="projectId"/>
<html:hidden property="versionId"/>
<html:hidden property="id"/>


<%=ViewUtil.getTitle("附件列表")%>

	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" >
			<td><input type="checkbox" id="checkAll"></td>
			<td>文件名</td>
			<td nowrap>文件大小(字节)</td>			
			<td nowrap>文件类型</td>
			<td>上传时间</td>
			<td nowrap>上传人员</td>
			<td>备注</td>
		</tr>
		<%if (pageResult != null) {
			List list = pageResult.getList();
			if (list != null) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					ProjectFiles p = (ProjectFiles)iter.next();
		%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><input type="checkbox" name="ids" value="<%=p.getId() %>"></td>
			<td align="left"><a href="ProjectFiles.do?act=download&id=<%=p.getId() %>"><%=p.getFileName() %></a></td>
			<td><%=p.getFileSize() %></td>
			<td><%=p.getFileType() %></td>
			<td><%=p.getUpdateDate() %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getUserId()) %></td>
			<td><%=p.getMemo()%></td>
		</tr>

		<%	}
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
			<input type="button" id="add" value="增加附件" >
			<input type="button" id="delete" value="删除">
			<input type="button" id="back" value="返回">
			</td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


