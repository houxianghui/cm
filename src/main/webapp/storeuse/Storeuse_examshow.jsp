<%@page import="com.yly.ls.Lsinfo"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pageResult" scope="request"	class="com.eis.base.PageObject" />
<jsp:useBean id="storeuseForm" scope="request"  class="com.yly.reuse.StoreuseForm" />



<html> 
<head>
<title>��Ʒ���</title> 
<script language="javascript"> 

function doShow(){ 
	if(document.forms[0].samId.value == null ||document.forms[0].samId.value == ''){
		alert('����¼��ԭSAM��'); 
		return; 
	}
	document.forms[0].act.value = "examshow";
	document.forms[0].submit();
}
function doRead(){ 
	$.get("Mwsissuetb.do?act=R&prodId="+document.forms[0].prodId.value+"&operationType="+document.forms[0].operationType.value+"&phiTypeId="+document.forms[0].phiTypeId.value,function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			document.forms[0].samId.value=0;
			alert(json.error);
		}else{
			document.forms[0].samId.value=json.origSamId;
			if(document.forms[0].prodId.value==4){
				$("#module").text(json.module);
			}
		 		
		}
		return;
	});

}


</script> 
</head>
<body> 
<p>&nbsp;</p> 
 <html:form method="post" action="Storeuse.do" >
<input type=hidden name=act value="back">
<input type=hidden name=requery>
<html:hidden property="operationType"/>
<html:hidden property="detectSign"/>
<html:hidden property="taskAmt"/>

<%=ViewUtil.getTitle("��Ʒ���")%> 

 <table id="issue" align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAM����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samId" styleClass="Textfield"  size="12" maxlength="12"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"  value="<%=storeuseForm.getSamId()%>" />&nbsp; 
		<input	name="read" type="button" class="Button" value="��ȡSAM����(ѡ���Ʒ���ͼ�����)" onClick="doRead()"> &nbsp; <div id=module></div>
	
	</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 SAMӡˢ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<html:text property="samCSN" styleClass="Textfield"  size="20" maxlength="20"  value="<%=storeuseForm.getSamCSN()%>" />&nbsp; 	
		&nbsp;<input name="show" type="button" class="Button" value="��ʾԭ����Ϣ" onClick="doShow()"> 	
	</td>
	</tr>
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�����:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(storeuseForm.getDetectSign())) %> 
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>��Ʒ����:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("prodId", SingleDic.PROD_ID, storeuseForm.getProdId())%>
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
		<%=SingleDicMap.getRadio("keyType", SingleDic.KEYTYPE,String.valueOf(storeuseForm.getKeyType()))%> 
		</td>	
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��������:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("manufacId", RedefSDicCodes.MAUN_ID, storeuseForm.getManufacId()) %>
		</td>
	</tr>	   	
     <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��Ʒͨ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getRadio("phiTypeId", SingleDic.COMM_RATE, storeuseForm.getPhiTypeId())%> 
		</td>	
	</tr>	
	 <tr>
		<td width="16%" align="left" class="dtPanel_Left">
		 ��ƷӦ������:
		</td>
		<td colspan="3"  class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getRadio("appTypeId", RedefSDicCodes.APPTYPEID, String.valueOf(storeuseForm.getAppTypeId())) %>
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
		<%=SingleDicMap.getRadio("cardPhyStat", SingleDic.PHY_STAT, String.valueOf(storeuseForm.getCardPhyStat()))%> 
		</td>	
	</tr>	
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
</html:form>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
  </table> 
</body> 
</html> 
 

