<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>用户可操作部门配置</title>

<script language="javascript"> 

$(function(){
	$("#userDepartAuth").attr("width","98%").attr("class","dtPanel_Line1").attr("border","0").attr("cellspaceing","1").attr("align","center").attr("cellpadding","0");
	$("tr").attr("align","center").attr("class","dtPanel_Top01").attr("height","28");
	$("#save").click(function(){
		var id = "";
		$(":checkbox").each(function(){
			if($(this).attr("checked")){
				id+=$(this).attr("id")+",";
			}
		});
		window.location.href="UserDepartAuth.do?act=update&ids="+id;
	});
});
</script>
</head>
<body>
<html:form method="post" action="UserDepartAuth.do">
<%=ViewUtil.getTitle("用户可操作部门配置")%>
<span style="align:center"><%out.print(request.getAttribute("table")); %></span>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="25" align="center">
		<input type="button" id="save" value="保存" class="Button"/>
		</td>
	</tr>
</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>