<%@page import="com.yly.issue.Issuetask"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="issueappForm" scope="request"  class="com.yly.issue.IssueappForm" />
<jsp:useBean id="pageResultIssuetask" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="issuetaskForm" scope="request" class="com.yly.issue.IssuetaskForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>����������Ϣ</title> 
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
function doAdd(){ 
	//ִ��У�� 
	var field = new Array("issueAmt","appTypeId"); 
	var info = new Array("��������","Ӧ������"); 
	//���������Ϣ�Ƿ�Ϊ�� 

	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[1]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[1]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	if(!confirm('��ȷ����д��Ϣ,ȷ��ִ�б��β�����')) { 
		return; 
	} 
 	document.forms[1].taskAmt.value=document.forms[0].taskAmt.value;
	document.forms[1].submit(); 
} 

function setPKey(taskNo_var,prodId_var,phiTypeId_var,appTypeId_var,issueAmt_var,remarks_var) { 

	document.forms[1].taskNo.value=taskNo_var; 
	document.forms[1].prodId.value=prodId_var; 
	document.forms[1].phiTypeId.value=phiTypeId_var; 
	document.forms[1].appTypeId.value=appTypeId_var; 
	document.forms[1].taskAmt.value=issueAmt_var; 
	document.forms[1].origSamId.value=remarks_var; 

} 
function doAssignUnit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[1].taskNo.value == null ||document.forms[1].taskNo.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	openWin("Issuetaskctrl.do?act=listpop&taskAmt="+document.forms[1].taskAmt.value+"&unitId="+document.forms[0].unitId.value+"&prodId="+document.forms[1].prodId.value+"&phiTypeId="+document.forms[1].phiTypeId.value+"&appTypeId="+document.forms[1].appTypeId.value+"&taskNo="+document.forms[1].taskNo.value+"&appNo="+document.forms[0].appNo.value+"&operationType="+document.forms[1].operationType.value+"&samIdBegin="+document.forms[1].origSamId.value+"","org_pop");

}  
function doDelete(){ 
	//ִ��У�� 
	if(document.forms[1].taskNo.value == null ||document.forms[1].taskNo.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	document.forms[1].act.value='d';	 
	document.forms[1].submit(); 
} 
function doSub(){ 
	document.forms[0].submit(); 

}
function doShow(){ 
	if(document.forms[1].origSamId.value == null ||document.forms[1].origSamId.value == ''){
		alert('����¼��ԭSAM��'); 
		return; 
	}
	document.forms[0].origSamId.value = document.forms[1].origSamId.value 
	document.forms[0].act.value='show';
	document.forms[0].submit(); 
}
function doRead(){ 
	$.get("Mwsissuetb.do?act=R&prodId="+document.forms[1].prodId.value+"&operationType=<%=issueappForm.getOperationType()%>",function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			alert(json.error);
		}else{
			document.forms[1].origSamId.value=json.origSamId;
			if(document.forms[1].prodId.value==4){
				$("#module").text(json.module);
			}
		 		
		}
		return;
	});

}
function doQuery(){ 
	document.forms[0].act.value='list';
	document.forms[0].submit(); 

}
function prodType_fun(obj){
	if(obj.value==4){
		document.getElementById("modulename").style.display="";
		document.getElementById("moduleval").style.display="";
		document.getElementById("cardname").style.display="none";
		document.getElementById("cardval").style.display="none";
	}else{
		document.getElementById("modulename").style.display="none";
		document.getElementById("moduleval").style.display="none";
		document.getElementById("cardname").style.display="";
		document.getElementById("cardval").style.display="";
	}
	 
}
function keyType_fun(obj){
	if(obj.value==1){
		document.getElementById("hiddenId").style.display="";
		document.getElementById("showId").style.display="none";
	}else{
		document.getElementById("hiddenId").style.display="none";
		document.getElementById("showId").style.display="";
	}
}
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Issueapp.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
<html:hidden property="appNo" />
<html:hidden property="unitId" />
<html:hidden property="origSamId" />
<html:hidden property="operationType" />

<%=ViewUtil.getTitle("�ƶ���������")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		OA�����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="OAappNo" styleClass="Textfield"  size="16" maxlength="30" readonly="true"/>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���뵥λ:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(issueappForm.getUnitId()))%>
		</td>
	</tr>	   
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
                    ��������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="taskAmt" styleClass="Textfield"  size="10" maxlength="10" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
		</td>
	</tr>
 	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		ҵ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(issueappForm.getOperationType()))%> 
		</td>	
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��λ��ϵ��:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unitperson" styleClass="Textfield"  size="10" maxlength="20" readonly="true"/>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��ϵ�绰:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="unittel" styleClass="Textfield"  size="10" maxlength="20"  onblur="onlyNum(this)" onkeyup="onlyNum(this)" readonly="true"/>
	</td>
	</tr>							
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="director" styleClass="Textfield"  size="10" maxlength="20" readonly="true" />
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��ע:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:textarea property="remarks" cols="50" rows="1" styleClass="Textfield" value="" readonly="true"/>
	</td>
	</tr>	
</html:form> 
 <html:form method="post" action="Issuetask.do" >
 	<input type=hidden name=act value="c">
	<input type=hidden name=requery>
	<input type=hidden name=OAappNo value="<%=issueappForm.getOAappNo()%>">	
	<input type=hidden name=step value="commit"> 
	<input type=hidden name=appNo value="<%=issueappForm.getAppNo()%>">
	<input type=hidden name=operationType value="<%=issueappForm.getOperationType()%>">
	<input type=hidden name=taskAmt>
	<input type=hidden name=taskNo>		
 <%=ViewUtil.getTitle("���ӷ�������")%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>SAM����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="origSamId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)" value="<%=issueappForm.getOrigSamId()%>"/>
		<input	name="read" type="button" class="Button" value="��ȡSAMID" onClick="doRead()"> &nbsp; <div id=module></div>
		<input	name="show" type="button" class="Button" value="��ʾԭ����Ϣ" onClick="doShow()"> &nbsp; 

	</td>
	</tr>	

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="issueAmt" styleClass="Textfield"  size="10" maxlength="10"  onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Ʒ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, issueappForm.getProdId())%>
		</td>
	</tr>	   
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Կ����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio_WithFun("keyType", SingleDic.KEYTYPE, String.valueOf(issueappForm.getKeyType()),"keyType_fun(this)")%> 
		</td>	
	</tr>	
	<tr>
	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%if(issueappForm.getProdId().equals("4")){%>
		<%=ViewUtil.must()%>ģ�����汾:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:select property="binFileVer" styleClass="Select">
			<html:optionsCollection name="issuetaskForm" property="moduleVerEffcollection"/>
		</html:select>
		<%}else{%>
		<%=ViewUtil.must()%>��Ʒͨ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, issueappForm.getPhiTypeId())%> 
		<%}%>
		</td>	
	</tr>		
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��ƷӦ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<div id="hiddenId">
		<%=ReDefSDicMap.getRadioWithHiddenId("appTypeId", RedefSDicCodes.APPTYPEID, String.valueOf(issueappForm.getAppTypeId()),"106") %>
		</div>
		<div id="showId"  style="display:none">
		<%=ReDefSDicMap.getRadio("appTypeId", RedefSDicCodes.APPTYPEID, String.valueOf(issueappForm.getAppTypeId())) %>
		</div>
		</td>	
	</tr>
</table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
		 		</td> 
	    </tr> 
  </table> 
 <%=ViewUtil.getTitle("���������б�")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">���������</td>
					<td width="10%">��Ʒ����</td>
					<td width="10%">��������</td>
					<td width="10%">��Կ����</td>	
					<td width="10%">��Ʒͨ������</td>						
					<td width="10%">��ƷӦ������</td>	
					<td width="10%">ģ�����汾</td>
					<td width="10%">�Ƿ���֤</td>
					<td width="10%">�Ƿ�װ��ά����Կ��</td>
					<td>ѡ��</td>
				</tr>
				<%List list = pageResultIssuetask.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Issuetask vo = (Issuetask)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
					<td><a href="Issuetaskctrl.do?act=list&taskNo=<%=vo.getTaskNo()%>"><%=vo.getTaskNo()%></a></td>	
					<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
					<td><%=vo.getIssueAmt()%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId())) %></td>
					<td><%=vo.getBinFileVer()!=null?SingleDicMap.getDicItemVal(SingleDic.BINFILEVER, vo.getBinFileVer()):""%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getAuthSign().toString())%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getW2Sign().toString())%></td>
					<td><label><input type="radio" name="param"
						onClick="setPKey('<%=vo.getTaskNo()%>','<%=vo.getProdId()%>','<%=vo.getPhiTypeId()%>','<%=vo.getAppTypeId()%>','<%=vo.getIssueAmt()%>')">
					</label></td>
				</tr>

				<%}
}%>
			</table>
			</td>
		</tr>

		<tr>
			<td>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" align="center"> &nbsp;
						</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
 

 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input	name="add" type="button" class="Button" value="�ύ" onClick="doSub()"> &nbsp; 
					<input	name="selectBatch" type="button" class="Button" value="�������뵥λ" onClick="doAssignUnit()"> &nbsp; 
		 			<input	name="delete" type="button" class="Button" value="ɾ��" onClick="doDelete()"> &nbsp; 		
		 			<input type="button" value="����" class="Button" onClick="doQuery()"/>
		 			
		 		</td> 
	    </tr> 
  </table> 
 

</body> 
</html> 
 

