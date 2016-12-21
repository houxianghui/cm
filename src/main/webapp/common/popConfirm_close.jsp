

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");	
	String samCSN =  (String)request.getAttribute("samCSN");
	String samId =  (String)request.getAttribute("samId");	
	String prodId =  (String)request.getAttribute("prodId");
	String manufacId =  (String)request.getAttribute("manufacId");
	String OAappNo =  (String)request.getAttribute("OAappNo");
	String phiTypeId =  (String)request.getAttribute("phiTypeId");
	String batchId =  (String)request.getAttribute("batchId");
	String url =  '"'+(String)request.getAttribute("backurl")+'"';
	String url1 = "Stoproduct.do?act=insertBad&wkState=13&samCSN="+samCSN+"&samId="+samId+"&prodId="+prodId+"&manufacId="+manufacId+"&url="+url;
%>

<HTML>
<HEAD>
<script language="javascript"> 
function doUpdate(){ 
	//Ôö¼Ó 
	window.location="Stoproduct.do?act=insertBad&wkState=13&samCSN="+"<%=(String)request.getAttribute("samCSN")%>"+"&samId="+"<%=(String)request.getAttribute("samId")%>"+"&prodId="+"<%=(String)request.getAttribute("prodId")%>"+"&manufacId="+"<%=(String)request.getAttribute("manufacId")%>"+"&url="+"<%=(String)request.getAttribute("backurl")%>"+"&OAappNo="+"<%=(String)request.getAttribute("OAappNo")%>"+"&phiTypeId="+"<%=(String)request.getAttribute("phiTypeId")%>"+"&batchId="+"<%=(String)request.getAttribute("batchId")%>";
	   
} 
function doBack() {  
	window.location="<%=request.getAttribute("backurl")%>"; 
} 
</script>
</head>
<BODY>
<script type="text/javascript" src="js/calendar.js"></script>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>
			<%=msg%>
			</td>
		</tr>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="ÊÇ" class="Button" onClick="doUpdate()"/>
			<input type="button" value="·ñ" class="Button" onClick="doBack()"/>
			</td>
		</tr>
	</table>
</script>
</BODY>
</HTML>
  