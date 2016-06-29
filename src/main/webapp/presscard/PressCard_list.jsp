<%@page import="com.yly.presscard.Presscardapptb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��Կ��ӡˢ���Ź���</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
	window.location="PressCard.do?act=c"; 
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
<html:form method="post" action="PressCard.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("��Կ��ӡˢ��������")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			¼������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			��Ƭ����:
			<html:select property="cardType" styleClass="Select">
				<html:optionsCollection name="pressCardForm" property="cardTypeCollection"/>
			</html:select>
			����:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="pressCardForm" property="manufacIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>������</td>
			<td>��Ƭ����</td>
			<td>��������</td>
			<td>�ɹ�����</td>
			<td>ͨ������</td>
			<td>¼������</td>
			<td>����Ա</td>
			<td>��ע</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Presscardapptb vo = (Presscardapptb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="PressCard.do?act=down&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo() %></a></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CARD_TYPE, vo.getCardType())%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, vo.getManufacId()) %></td>
			<td><%=vo.getPurchaseAmt() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getCommRate())%></td>
			<td><%=vo.getCurrDate()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
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
			<input type="button" value="��������" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


