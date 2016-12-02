<%@page import="com.yly.issue.IssueappForm"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>�����䷢�������б�</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "wq";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "wq";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(taskCtrlNo_var,issueAmt_var,phiTypeId_var,issueDoneAmt_var,prod_var,operation_var,applytype_var) { 
	document.forms[0].taskCtrlNo.value=taskCtrlNo_var; 
	document.forms[0].issueAmt.value=issueAmt_var; 	
	document.forms[0].phiTypeId.value=phiTypeId_var; 		
	document.forms[0].issueDoneAmt.value=issueDoneAmt_var; 
	document.forms[0].prodId.value=prod_var; 
	document.forms[0].operationType.value=operation_var; 	
	document.forms[0].appTypeId.value=applytype_var; 
} 
function checkAll(field) //ȫѡ
{
	var checkflag = "false";
	if (checkflag == "false") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "false")
			{ document.forms[0].cx.checked = true;}
		
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = true;
			}
			checkflag = "true";
			return ;
		}	
	}
} 
function cancelCheckAll(field)//ȫ��ȡ��
{
	var checkflag = "true";
	if (checkflag == "true") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "true")
			{ document.forms[0].cx.checked = false;}
			
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = false;
			}
			checkflag = "false";
			return ;
		}
	}
}
function doBatchAssign(){ 

	var flag_cc = 0;
	var cx_field = document.forms[0].cx;
	var cx_len = document.forms[0].cx.length;
	
	if(cx_len == undefined){
		cx_len = 1;
		for(var cc = 0 ; cc < cx_len ; cc++){
			if(document.forms[0].cx.checked){
				flag_cc = 1;
				break;
			}	
		}
	}
	else{
		for(var cc = 0 ; cc < cx_len ; cc++){	
			if(document.forms[0].cx[cc].checked){
				flag_cc = 1;
				break;
			}
		
		}
	}
	if(flag_cc == 0){
		alert('����ѡ���¼');
		return;
	}
	document.forms[0].act.value = "batch_assign";
	document.forms[0].submit();
	
} 
function doSingleAssign(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].taskCtrlNo.value == null ||document.forms[0].taskCtrlNo.value == "") { 
		alert('����ѡ��ѡ��¼'); 
		return; 
	} 
	var s = prompt('�������������','');
	if(s == null || s==""||s==0){
		alert("�������������");
		return;
	}
	var s1=document.forms[0].issueAmt.value-document.forms[0].issueDoneAmt.value;
	if(eval(s)> eval(s1) ){
		alert("��������С��"+s1);
		return;
	}
		
	s = encodeURI(encodeURI(s));
	document.forms[0].issueAmt.value =s;
	document.forms[0].act.value = "single_assign";
	document.forms[0].submit();
	
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="wq">
<input type=hidden name=requery> 
<html:hidden property="appNo"/>
<html:hidden property="taskCtrlNo"/>
<html:hidden property="issueAmt"/>
<html:hidden property="issueDoneAmt"/>
<html:hidden property="appTypeId"/>
<html:hidden property="operationType"/>
<%=ViewUtil.getTitle("�����䷢�������б�")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			¼������:			
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			���뵥λ:
			<html:select property="unitId_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
			</html:select>
			ҵ������:
			<html:select property="operationType_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="operationTypecollection"/>
			</html:select>
			��Ʒ:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="prodIdcollection"/>
			</html:select>			
			ͨ������:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="phiTypeIdcollection"/>
			</html:select>			
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>��ѡ</td>		
			<td>OA�����</td>
			<td>����������ϸ��</td>
			<td>ҵ������</td>
			<td>��Ʒ����</td>	
			<td>��Կ����</td>	
			<td>ͨ������</td>	
			<td>Ӧ������</td>		
			<td>ģ�����汾</td>		
			<td>���뵥λ</td>
			<td>��������</td>
			<td>�ѷ�������</td>
			<td>��ʼSAM��</td>
			<td>����SAM��</td>
			<td>���÷�ʽ</td>
			<td>֧����ʽ</td>
			<td>��ѡ</td>	
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			IssueappForm vo = (IssueappForm) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td>
			<label>
			<input type="checkbox" name="cx" value="<%=vo.getTaskCtrlNo()+","+vo.getPhiTypeId()+","+vo.getIssueAmt()+","+vo.getProdId()+","+vo.getIssueDoneAmt()+","+vo.getOperationType()+","+vo.getAppTypeId()%>"> 
			</label></td> 
			<td><a href="Issuetask.do?act=list&appNo=<%=vo.getAppNo()%>"><%=vo.getOAappNo()%></a></td>		
			<td><%=vo.getTaskCtrlNo()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>				
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId())) %></td>
			<td><%=vo.getBinFileVer()!=null?ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION,vo.getBinFileVer()):""%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>	
			<td><%=vo.getIssueAmt()%></td>
			<td><%=vo.getIssueDoneAmt()%></td>			
			<td><%=vo.getSamIdBegin() %></td>	
			<td><%=vo.getSamIdEnd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(vo.getPaymentType()))%></td>				
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getTaskCtrlNo()%>','<%=vo.getIssueAmt()%>','<%=vo.getPhiTypeId()%>','<%=vo.getIssueDoneAmt()%>','<%=vo.getProdId()%>','<%=vo.getOperationType()%>','<%=vo.getAppTypeId()%>')">
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
			<input type="button" name="check_all" class="Button" value="ȫѡ" onClick="checkAll(document.forms[0].cx);"/>  
			<input type="button" name="check2" class="Button" value="ȡ��ȫѡ" onClick="cancelCheckAll(document.forms[0].cx);"/>  
			<input type="button" value="������������" class="Button" onClick="doBatchAssign()"/>
			<input type="button" value="���ʷ�������" class="Button" onClick="doSingleAssign()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


