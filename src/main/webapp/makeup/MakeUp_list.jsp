<%@ page import="com.yly.issue.Issueapp" %>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>�������뵥</title>
<script language="javascript"> 

function doQuery() {  
	document.forms[0].act.value = "makeupList";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "makeupList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(appNo_var,formStat_var,oper_var) { 
	document.forms[0].appNo.value=appNo_var; 
	document.forms[0].formState.value=formStat_var; 
	document.forms[0].operationType.value=oper_var; 	

} 

function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].appNo.value == null ||document.forms[0].appNo.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	if(document.forms[0].formState.value!=0){
		alert('ֻ��ά��״̬���ݴ������'); 
		return; 
	}
	//�ύ�� 
	document.forms[0].act.value='makeupMainTain'; 
	document.forms[0].submit(); 

} 
function doPrint(){
	if(document.forms[0].appNo.value == null ||document.forms[0].appNo.value == "") { 
		alert('��ѡ���¼'); 
		return; 
	} 
	if(document.forms[0].formState.value!=3) { 
		alert('����δִ����ɲ������ӡ'); 
		return; 
	} 
	window.location="PdfMaker.do?act=print&formNo="+document.forms[0].appNo.value+"&operationType="+document.forms[0].operationType.value; 
}
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="makeupList">
<input type=hidden name=requery > 
<html:hidden property="appNo"/>
<html:hidden property="formState"/>
<html:hidden property="operationType"/>

<%=ViewUtil.getTitle("�������뵥�б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			��������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			���뵥λ:
			<html:select property="unitId_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
			</html:select>
			ҵ������:
			<html:select property="operationType_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="makeupTypecollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>OA�����</td>
			<td>���뵥λ</td>
			<td>��������</td>
			<td>ҵ������</td>
			<td>��λ��ϵ��</td>
			<td>��ϵ�绰</td>
			<td>����Ա</td>
			<td>����ʱ��</td>
			<td>����״̬</td>
			<td>֧�����</td>
			<td>��ע</td>
			<td>ѡ��</td>
			
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Issueapp vo = (Issueapp) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="Lsinfo.do?act=list&appNo=<%=vo.getAppNo()%>"><%=vo.getOAappNo()%></a></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>			
			<td><%=vo.getTaskAmt() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=vo.getUnitperson()%></td>
			<td><%=vo.getUnittel()%></td>
			<td><%=vo.getDirector()%></td>
			<td><%=vo.getCurrDate()%></td>	
			<td><%=vo.getFormState()!=null?SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, vo.getFormState().toString()):"" %></td>
			<td><%=vo.getTotalPrice()%></td>
			<td><%=vo.getRemarks()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getAppNo()%>','<%=vo.getFormState()%>','<%=vo.getOperationType()%>')">
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
			<input type="button" value="ά��" class="Button" onClick="doEdit()"/>
			<input type="button" value="��ӡ����" class="Button" onClick="doPrint()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


