<%@page import="com.yly.conf.Moduleconf"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>调用函数配置</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Moduleconf.do?act=c"; 
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
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Moduleconf.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("模块程序版本配置")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>模块程序版本：
			<html:select property="moduleId" styleClass="Select">
				<html:optionsCollection name="moduleconfForm" property="moduleVersioncollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
  		  </td> 
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>模块程序代码</td>
			<td>模块程序名称</td>
			<td>模块程序版本</td>
			<td>应用行业</td>
			<td>适用硬件型号</td>
			<td>程序作者</td>						
			<td>发布日期</td>
			<td>操作员</td>
			<td>录入日期</td>
			<td>备注</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Moduleconf vo = (Moduleconf) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getModuleId() %></td>	
			<td><%=vo.getModuleName()%></td>	
			<td><%=vo.getModuleVersion()%></td>		
			<td><%=vo.getApplyHy()%></td>		
			<td><%=vo.getFitHardId()%></td>		
			<td><%=vo.getOwner()%></td>		
			<td><%=vo.getIssueDate()%></td>						
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=vo.getRemarks()%></td>				
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
			<input type="button" value="增加模块程序版本" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


