<%@page import="com.yly.stor.Stoappinfo"%>
<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="stoAppForm" scope="request"  class="com.yly.stor.StoAppInfoForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��ؼ�¼</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "popList";
	document.forms[0].submit(); 
} 
 
function setPKey(formNo_var) { 
	opener.document.forms[0].rsvd.value=formNo_var;
	window.close();	
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "popList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="popList">
<html:hidden property="prodId"/>
<html:hidden property="operationType"/>
<html:hidden property="currPeriodAmt"/>

<%=ViewUtil.getTitle("��ؼ�¼")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			�������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			����:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="manufacIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>���κ�</td>
			<td>��ǰ���</td>
			<td>��Ʒ����</td>
			<td>�������</td>
			<td>֧�ֻ�ͨ</td>
			<td>�ɹ�����</td>
			<td>��������</td>		
			<td>ͨ������</td>
			<td>Pki����</td>
			<td>ӡˢ���ŷ�Χ</td>
			<td>ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoappinfo vo = (Stoappinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="StoApp.do?act=r&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo()%></a></td>	
			<td><%=vo.getCurrPeriodAmt()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsHTCard())%></td>			
			<td><%=vo.getUnitPrice()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, vo.getManufacId()) %></td>								
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,vo.getPhiTypeId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsPki())%></td>
			<td><%=vo.getPressCardScale()==null?"":vo.getPressCardScale().trim()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
			
		</tr>

		<%}
}%>

	
<% 
}
		%>
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

</html:form>

<p>&nbsp;</p>
</body>
</html>

