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
<style type="text/css">
tr{
	background-color:#F7F7F7;
	COLOR:#333333;
	FONT-SIZE:9pt;
	FOTN-FAMILY:����,verdana,Arial, Helvetica;
	height:23px;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��ؼ�¼</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "exbackList";
	document.forms[0].submit(); 
} 
 
function setPKey(formNo_var) { 
	document.forms[0].formNo.value=formNo_var; 
	
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "exbackList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doPrint(){
	if(document.forms[0].formNo.value == null ||document.forms[0].formNo.value == "") { 
		alert('��ѡ���¼'); 
		return; 
	} 
	window.location="PdfMaker.do?act=print&formNo="+document.forms[0].formNo.value+"&operationType="+document.forms[0].operationType.value; 
}
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="exbackList">
<html:hidden property="appNo"/>
<html:hidden property="operationType"/>
<html:hidden property="formNo"/>
<input type="hidden" name="currPeriodAmt" value=<%=String.valueOf(stoAppForm.getCurrPeriodAmt())%> id="currPeriodAmt"/>
<input name='txtTRLastIndex' type='hidden' id='ex.index' value="1" />



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
			<td>ģ�����κ�/�ɹ�����/ͨ������</td>
			<td>ģ��汾��/Pki����</td>
			<td>ӡˢ���ŷ�Χ/ģ��������κ�</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoappinfo vo = (Stoappinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">		
			<td><a href="Lsinfo.do?act=list&formNo=<%=vo.getFormNo()%>&operationType=<%=vo.getOperationType()%>"><%=vo.getFormNo()%></a></td>			
			<td><%=vo.getCurrPeriodAmt()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsHTCard())%></td>			
			<td><%=vo.getUnitPrice()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, vo.getManufacId()) %></td>
			<%if(vo.getProdId().equals("4")) {%>
			<td><%=vo.getModuleBatchId()%></td>			
			<td><%=vo.getModuleVersion()%></td>					
			<td><%=vo.getRsvd()%></td>	
			<%}else if(vo.getProdId().equals("5")){ %>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PURCH_TYPE,vo.getPurchaseType())%></td>	
			<td></td>
			<td></td>
			<%}else {%>									
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,vo.getPhiTypeId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsPki())%></td>
			<td><%=vo.getPressCardScale()==null?"":vo.getPressCardScale().trim()%></td>	
			<%} %>
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td height="25" align="center">
			<input type="button" value="��ӡ����" class="Button" onClick="doPrint()"/>
			</td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


