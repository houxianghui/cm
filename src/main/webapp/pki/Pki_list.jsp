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
<title>��Կ��Ϣ��ѯ</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
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
<html:form method="post" action="Secpkitb.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery>
<%=ViewUtil.getTitle("��Կ��Ϣ�б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
         <td>
			���п���:			
			��<html:text property="samId_min" styleClass="Textfield" size="12" />
			��<html:text property="samId_max" styleClass="Textfield" size="12" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>��Կ��Ϣ</td>
			<td>���п���</td>
			<td>ӡˢ����</td>
			<td>����ָ��E</td>
			<td>��Կ����</td>
			<td>����ʱ��</td>						
			<td>����ʱ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Secpkitb vo = (Secpkitb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getPubKey() %></td>	
			<td><%=vo.getSamId()%></td>	
			<td><%=vo.getSamCSN()%></td>		
			<td><%=vo.getPubExponent()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>	
			<td><%=vo.getIssueTime()%></td>		
			<td><%=vo.getCurrPeriod()%></td>		
		
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
			<input type="button" value="����" class="Button" onClick="doCardDown()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


