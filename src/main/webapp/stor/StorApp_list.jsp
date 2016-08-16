<%@page import="com.yly.stor.Stoappinfo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>������</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
	window.location="StoApp.do?act=c"; 
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
function setPKey(appNo_var,operType_var) { 
	document.forms[0].formNo.value=appNo_var; 
	document.forms[0].operationType.value=operType_var; 
} 
function doPrint(){
	if(document.forms[0].formNo.value == null ||document.forms[0].formNo.value == "") { 
		alert('��ѡ������'); 
		return; 
	} 
	window.location="PdfMaker.do?act=print&formNo="+document.forms[0].formNo.value+"&operationType="+document.forms[0].operationType.value; 
}
function doDownload(){ 
	document.forms[0].act.value = "resultDown";
	document.forms[0].submit(); 
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="list">
<html:hidden property="formNo"/>
<html:hidden property="operationType"/>
<%=ViewUtil.getTitle("��������б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			¼������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			����:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="manufacIdCollection"/>
			</html:select>
			��Ʒ:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="prodIdCollection"/>
			</html:select>
			�������:
			<html:select property="operationType_f" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="operationTypeCollection"/>
			</html:select>	
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			<input	name="�������" type="button" class="Button_Search"  onclick="doDownload()">
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
			<td>ӡˢ���ŷ�Χ/������κ�</td>
			<td>¼������</td>
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
			<td><%=vo.getCurrDate()%></td>	
			<td align="center">
			<label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>','<%=vo.getOperationType()%>')">
			</label></td>
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
			<input type="button" value="�����������" class="Button" onClick="doAdd()"/>
			<input type="button" value="��ӡ����" class="Button" onClick="doPrint()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


