<%@page import="com.yly.issue.Mwsissuetb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>���мӹ����б�</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(formNo_var) { 
	document.forms[0].formNo.value=formNo_var; 
	
} 

function doIssue(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].formNo.value == null ||document.forms[0].formNo.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	

	document.forms[0].act.value = "issueInit";
	document.forms[0].submit();
	
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Mwsissuetb.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery> 
<html:hidden property="taskCtrlNo"/>
<html:hidden property="issueDoneAmt"/>
<html:hidden property="phiTypeId"/>
<html:hidden property="prodId"/>
<html:hidden property="formNo"/>
<%=ViewUtil.getTitle("���мӹ����б�")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td nowrap>
			��������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>


			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>���ݱ��</td>
			<td nowrap>����ʱ��</td>
			<td nowrap>�ӹ������</td>	
			<td nowrap>��Ʒ����</td>	
			<td nowrap>��Կ����</td>	
			<td nowrap>ͨ������</td>	
			<td nowrap>Ӧ������</td>		
			<td nowrap>���κ�</td>
			<td nowrap>�ӹ�����</td>
			<td nowrap>�ѷ�������</td>
			<td nowrap>��ǰSAM��</td>	
			<td nowrap>����SAM��</td>
			<td nowrap>����״̬</td>
			<td nowrap>��ѡ</td>	
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Mwsissuetb vo = (Mwsissuetb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><a href="Issuetaskctrl.do?act=ql&taskCtrlNo=<%=vo.getTaskCtrlNo()%>"><%=vo.getFormNo()%></a></td>	
			<td><%=vo.getFormTime()%></td>
			<td><%=vo.getWsSnr()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID,vo.getApplyAttr()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,vo.getBatchId())%></td>	
			<td><%=vo.getWorkSheetAmt()%></td>
			<td><%=vo.getIssueDoneAmt()%></td>	
			<td><%=vo.getSamId()%></td>		
			<td><%=vo.getSamIdEnd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, String.valueOf(vo.getFormState()))%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
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
			<td nowrap class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="��������" class="Button" onClick="doIssue()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


