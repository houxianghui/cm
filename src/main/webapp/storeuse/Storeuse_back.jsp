<%@page import="com.yly.ls.Lsinfo"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pageResult" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="storeuseForm" scope="request"  class="com.yly.reuse.StoreuseForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>



<html> 
<head>
<title>��Ʒ�˻�</title> 
<script language="javascript"> 

function doShow(){ 
	if(document.forms[0].samId.value == null ||document.forms[0].samId.value == ''){
		alert('����¼��ԭSAM��'); 
		return; 
	}
	document.forms[0].act.value = "show";
	document.forms[0].submit();
}
function doRead(){ 
	document.forms[0].detectSign.value=0;
	$.get("Mwsissuetb.do?act=R&prodId="+document.forms[0].prodId.value+"&operationType="+document.forms[0].operationType.value+"&phiTypeId="+document.forms[0].phiTypeId.value,function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			document.forms[0].samId.value=0;
			document.forms[0].detectSign.value=2;
			alert(json.error);
		}else{
			document.forms[0].samId.value=json.origSamId;
			document.forms[0].detectSign.value=1;
			if(document.forms[0].prodId.value==4){
				$("#module").text(json.module);
			}
		 		
		}
		return;
	});

}
function doBack(){ 
	if(document.forms[0].detectSign.value == 2 ||document.forms[0].cardPhyStat.value == 2){
		if(!confirm('��Ҫ��Ʒ�����ϴ�����?')) { 
			document.forms[0].wkState.value = "13";
		}else{
			document.forms[0].wkState.value = "15";
		} 
	}else{
		if(document.forms[0].samId.value.length!=12){
			alert("�ÿ��������뿨��");
			return;
		}
		document.forms[0].wkState.value = "12";
	}
	if(document.forms[0].unitId.value == 0){
		document.forms[0].unitId.value = <%=storeuseForm.getUnitId()%>;
	}
	document.forms[0].act.value = "back";
	document.forms[0].submit();
	
} 

</script> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="Storeuse.do" >
<input type=hidden name=act value="back">
<input type=hidden name=requery>
<input type=hidden name=operationType value="61">
<html:hidden property="appNo"/>
<html:hidden property="taskAmt"/>
<html:hidden property="wkState"/>
<html:hidden property="OAappNo"/>
 <%=ViewUtil.getTitle("��Ʒ�˻�,����"+storeuseForm.getTaskAmt())%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAM����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  />&nbsp; 
		<input	name="read" type="button" class="Button" value="��ȡSAM����(ѡ���Ʒ���ͼ�����)" onClick="doRead()"> &nbsp; <div id=module></div>
	
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAMӡˢ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samCSN" styleClass="Textfield"  size="20" maxlength="20"   />&nbsp; 	
		&nbsp;<input name="show" type="button" class="Button" value="��ʾԭ����Ϣ" onClick="doShow()"> 	
	</td>
	</tr>	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("detectSign", SingleDic.DETECSIGN, "0")%> 
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Ʒ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, "1")%>
		</td>
	</tr>	   
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ���뵥λ:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:select property="unitId" styleClass="Select">
			<html:optionsCollection name="storeuseForm" property="unitIdcollection"/>
		</html:select>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��Կ����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("keyType", SingleDic.KEYTYPE, "1")%> 
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, "1") %>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��Ʒͨ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, "1")%> 
		</td>	
	</tr>	
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��ƷӦ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("appTypeId", RedefSDicCodes.APPTYPEID, "101") %>
		</td>	
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ģ�����汾:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<html:select property="binFileVer" styleClass="Select">
				<html:optionsCollection name="storeuseForm" property="moduleVerEffcollection"/>
		</html:select>
		</td>	
	</tr>	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������״̬:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("cardPhyStat", SingleDic.PHY_STAT, "1")%> 
		</td>	
	</tr>	
</table>
 <%=ViewUtil.getTitle("�˻���ˮ��Ϣ")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">��ˮ��</td>
					<td width="10%">SAM����</td>
					<td width="10%">SAMӡˢ����</td>
					<td width="10%">ҵ������</td>
					<td width="10%">��Ʒ����</td>
					<td width="10%">�����</td>	
					<td width="10%">������</td>						
					<td width="10%">����Ա</td>	
					<td width="10%">����ʱ��</td>					
				</tr>
				<%List list = pageResult.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Lsinfo vo = (Lsinfo)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
					<td><%=vo.getFlowNo()%></td>	
					<td><%=vo.getSamId()%></td>
					<td><%=vo.getSamCSN()%></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId()) %></td>
					<td><%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign())) %></td>
					<td><%=(vo.getErrorDesc()==null?"":vo.getErrorDesc())+(vo.getErrorCode()==null?"":SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(vo.getErrorCode())))%></td>
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td><%=vo.getCurrDate()%></td>		
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>

</html:form>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="back" type="button" class="Button" value="��Ʒ�˻�" onClick="doBack()">
		 		</td> 
	    </tr> 
  </table> 
</body> 
</html> 
 

