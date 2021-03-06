<%@page import="com.yly.pki.Secpkitb"%>
<%@page import="com.yly.manu.Bimanufacinfotb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>公钥信息查询</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Manuinfo.do?act=c"; 
} 

function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doCardDown(){ 
	document.forms[0].act.value = "cardDown";
	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" enctype="multipart/form-data" action="Secpkitb.do">
<input type=hidden name=act value="upload">
<input type=hidden name=requery>
<%=ViewUtil.getTitle("公钥信息列表")%>
	
	<table class=heightspace_top3 width="100%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
         <td>
			发行卡号:			
			从<html:text property="samId_min" styleClass="Textfield" size="12" />
			到<html:text property="samId_max" styleClass="Textfield" size="12" />
			发行日期:
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr> 		
	</table>
      <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr > 
          <td >批量上传文件： 
			<html:file property="file"></html:file>
       		<html:submit value="批量导出公钥(samid,samcsn)" styleClass="Button"/>
         </td> 
       </tr>     
      
  </table>  
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0" style=“table-layout:fixed;”>
		<tr align="center" class="dtPanel_Top01" height="28">
			<td width="50%">公钥信息</td>
			<td>发行卡号</td>
			<td>印刷卡号</td>
			<td>密钥类型</td>
			<td>发行时间</td>						
			<td>更新时间</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Secpkitb vo = (Secpkitb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td width="50%"><div style="width:600px;word-wrap:break-word;" ><%=vo.getPubKey() %></div></td>	
			<td><%=vo.getSamId()%></td>	
			<td><%=vo.getSamCSN()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>	
			<td><%=vo.getIssueTime()%></td>		
			<td><%=vo.getCurrPeriod()%></td>		
		
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
			<input type="button" value="导出" class="Button" onClick="doCardDown()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


