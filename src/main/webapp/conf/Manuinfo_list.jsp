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
<title>������Ϣ����</title>
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
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Manuinfo.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery>
<%=ViewUtil.getTitle("������Ϣ�б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>�������ƣ�
			<html:text property="manufacname_f" styleClass="Textfield" size="20" maxlength="30"  />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
  		  </td> 
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>���̴���</td>
			<td>��������</td>
			<td>������ϵ��</td>
			<td>������ϵ�绰</td>
			<td>������ϵ��ַ</td>
			<td>������д</td>						
			<td>����Ա</td>
			<td>¼������</td>
			<td>��ע</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Bimanufacinfotb vo = (Bimanufacinfotb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getManufacId() %></td>	
			<td><%=vo.getManufacname()%></td>	
			<td><%=vo.getManufacperson()%></td>		
			<td><%=vo.getManufactel()%></td>		
			<td><%=vo.getManufacaddr()%></td>		
			<td><%=vo.getManufacfax()%></td>		
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
			<input type="button" value="���ӳ���" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


