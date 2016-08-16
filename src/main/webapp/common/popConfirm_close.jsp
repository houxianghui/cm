

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");	
	String samCSN =  (String)request.getAttribute("samCSN");
	String samId =  (String)request.getAttribute("samId");	
	String prodId =  (String)request.getAttribute("prodId");
	String manufacId =  (String)request.getAttribute("manufacId");
	String url =  (String)request.getAttribute("backurl");
	String url1 = "Stoproduct.do?act=insertBad&wkState=13&samCSN="+samCSN+"&samId="+samId+"&prodId="+prodId+"&manufacId="+manufacId+"&url="+url;
%>

<HTML>
<HEAD>

<TITLE>信息提示</TITLE>
</HEAD>
<BODY>
<script language="javascript">
if(!confirm("<%=msg%>"))
	location.href ="<%=url%>";
	else location.href ="<%=url1%>";
</script>
</BODY>
</HTML>
  