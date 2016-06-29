<%@ include file = "/includes/common.jsp" %>
<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>升级信息维护</title>
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
			<li><a href="CodeChg.do?act=list&versionId=<%=versionId%>">程序清单</a></li>
			<li><a href="DatabaseChg.do?act=list&versionId=<%=versionId%>">数据库结构</a></li>
			<li><a href="ParamChg.do?act=list&versionId=<%=versionId%>">参数变更</a></li>
			<li><a href="UpdateStep.do?act=list&versionId=<%=versionId%>">升级步骤</a></li>
			
		</ul>
	</div>
	<div style="text-align: center;width: 98%" class=""ui-tabs ui-widget ui-widget-content ui-corner-all">
		<button id="back" >返回版本列表</button>
	</div>
</body>
</html>