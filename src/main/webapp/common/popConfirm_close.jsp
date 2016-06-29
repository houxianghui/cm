

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");	
	String samCSN =  (String)request.getAttribute("samCSN");
%>

<HTML>
<HEAD>

<TITLE>信息提示</TITLE>
</HEAD>
<BODY>
<script language="javascript">
if(!confirm(<%=msg%>)) { 
	return;
	window.close();
}
window.location="Stoproduct.do?act=u&wkState=13&samCSN"+<%=samCSN%>; 
window.close();
</script>
</BODY>
</HTML>
