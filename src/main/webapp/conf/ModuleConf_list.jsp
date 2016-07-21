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
<title>���ú�������</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
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

<%=ViewUtil.getTitle("ģ�����汾����")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>ģ�����汾��
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
			<td>ģ��������</td>
			<td>ģ���������</td>
			<td>ģ�����汾</td>
			<td>Ӧ����ҵ</td>
			<td>����Ӳ���ͺ�</td>
			<td>��������</td>						
			<td>��������</td>
			<td>����Ա</td>
			<td>¼������</td>
			<td>��ע</td>
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
//������ҳ��ע 
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
			<input type="button" value="����ģ�����汾" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


