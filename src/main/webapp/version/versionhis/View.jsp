<%@page import="com.blue.version.versionhis.VersionHis"%>
<%@page import="com.blue.version.subsysversion.SubSysVersion"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file = "/includes/common.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<script type="text/javascript">
$(function(){
	
	$(":input[name='release']").click(function(){
		var isChecked = $(":checkbox[name='isChecked']:checked");
		isChecked = isChecked.attr("checked");
		if(!isChecked){
			alert("����ִ�з���ǰ���");
			return;
		}
		document.forms[0].submit();
	});
});
</script>
<title>�汾����</title>
</head>
<body>
<%VersionHis v = (VersionHis)request.getAttribute("v"); 
List<String> p = (List<String>)request.getAttribute("p");
List<SubSysVersion> s = (List<SubSysVersion>)request.getAttribute("s");
%>
<table width="98%">
<tr style="vertical-align: top;"><td>
	<table align="left" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
		<tr bgcolor="#FFFFFF">
		<td class="dtPanel_Top01">���汾��</td>
		<td class="dtPanel_Main2"><a href="VersionHis.do?act=viewDoc&versionId=<%=v.getVersionId() %>"> <%=v.getVersionId() %></a></td>
		</tr>
		<td colspan='2' class="dtPanel_Top01">��Ŀ�б�</td>
		</tr>
		<% 
		if(p != null){
			for(String t : p){
				out.print("<tr><td colspan='2' class=\"dtPanel_Main2\">");
				out.print(t);
				out.print("</td></tr>");
			}
		}
		%>
		<tr bgcolor="#FFFFFF">
			<td class="dtPanel_Top01">Ԥ�Ʒ�������</td><td class="dtPanel_Main2"><%=v.getPlanReleaseDate() %></td>
		</tr>
	</table>
	</td>
	<td>
		<table align="right" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0" >
			<tr>
			<td class="dtPanel_Top01">��ϵͳ</td><td class="dtPanel_Top01">ǰһ�汾</td><td class="dtPanel_Top01">�°汾</td>
			</tr>
			<%
			if(s != null){
				for(SubSysVersion t : s){
					out.print("<tr><td class=\"dtPanel_Main2\">");
					out.print(t.getSysName());
					out.print("</td><td class=\"dtPanel_Main2\">");
					out.print(t.getPreVersion());
					out.print("</td><td class=\"dtPanel_Main2\">");
					out.print(t.getNextVersion());
					out.print("</td></tr>");
				}
			}
			%>
		</table>
	</td>
</tr>
<html:form action="VersionHis.do">
<html:hidden property="versionId"/>
<html:hidden property="act" value="release"/>
	<tr><td colspan="2">
		<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
		<tr align="center"><td class="dtPanel_Top01">
		�Ѿ���ɷ���ǰ���<html:checkbox property="isChecked"></html:checkbox>
		<auth:button value="�����汾" onClick="" id="release" name="release"></auth:button>
		<input type="button" onclick="history.back()" value="����" class="Button">
		</td></tr>
		</table>
	</td></tr>
</table>
</html:form>
</body>
</html>