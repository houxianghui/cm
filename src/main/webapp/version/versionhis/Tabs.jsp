<%@ include file = "/includes/common.jsp" %>
<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>������Ϣά��</title>
<%int tabId = request.getAttribute("tabId")==null?0:(Integer)request.getAttribute("tabId"); %>
<script type="text/javascript" charset="gb18030">
$(function(){
	var tab = $('#tabs').tabs().tabs('select',<%=tabId%>);
	$("#back").button().click(function(){
		window.location.href="VersionHis.do?act=list";
	});
});
</script>
</head>
<body>
	<%String versionId = (String)request.getAttribute("versionId"); %>
	<div id="tabs">
		<ul>
			<li><a href="CodeChg.do?act=list&versionId=<%=versionId%>">�����嵥</a></li>
			<li><a href="DatabaseChg.do?act=list&versionId=<%=versionId%>">���ݿ�ṹ</a></li>
			<li><a href="ParamChg.do?act=list&versionId=<%=versionId%>">�������</a></li>
			<li><a href="UpdateStep.do?act=list&versionId=<%=versionId%>">��������</a></li>
			
		</ul>
	</div>
	<div style="text-align: center;width: 98%" class=""ui-tabs ui-widget ui-widget-content ui-corner-all">
		<button id="back" >���ذ汾�б�</button>
	</div>
</body>
</html>