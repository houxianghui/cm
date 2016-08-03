<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="pageResultLsInfo" scope="request" class="com.eis.base.PageObject" />
<jsp:useBean id="stoproductForm" scope="request"  class="com.yly.exstore.StoproductForm" />


<html>

<%int maxPage = 1;
if (pageResultLsInfo != null)
	maxPage = pageResultLsInfo.getTotalPage();
%>
<head>
<title>��Ʒ���Ʒ��ϸ</title>
</head>
<body>
<html:form method="post" action="Stoproduct.do">
<input type=hidden name=act value="r">

<%=ViewUtil.getTitle("��Ʒ���Ʒ��ϸ")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getSamId()%>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAMӡˢ����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getSamCSN()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		OA�����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getOAappNo()%>
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ʒ����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(stoproductForm.getProdId()))%> 
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%if(stoproductForm.getProdId().equals("4")){%>
		ģ��汾:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, stoproductForm.getBinFileVer())%> 
		<%}else{%>
		��Ʒͨ������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, String.valueOf(stoproductForm.getPhiTypeId()))%> 
		<%}%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��ƷӦ������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getAppTypeId()==null?"":ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(stoproductForm.getAppTypeId()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Կ����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getKeyType()==0?"":SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(stoproductForm.getKeyType()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���̴���:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(stoproductForm.getManufacId())) %>	
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������κ�:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getBatchId()%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ʒ״̬:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(stoproductForm.getWkState()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ʒ״̬�������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getWkStateChgDate()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���ؿ�״̬:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(stoproductForm.getIOState()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���ؿ�״̬�������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getIOStateChgDate()==null?"":stoproductForm.getIOStateChgDate()%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getReuseTime()%>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������״̬:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PHY_STAT,String.valueOf(stoproductForm.getCardPhyStat()))%>
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���뵥λ:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(stoproductForm.getUnitId()))%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����ʱ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getIssueTime()==null?"":stoproductForm.getIssueTime()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getDetectSign()==null?"":stoproductForm.getDetectSign()%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���ʱ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getDetectTime()==null?"":stoproductForm.getDetectTime()%>
		</td>
	</tr>		
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		֧����ʽ :
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(stoproductForm.getPaymentType()))%>				
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(stoproductForm.getConsumeType()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�Ƿ�װ��ά����Կ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(stoproductForm.getW2Sign()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��֤��ʽ:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(stoproductForm.getAuthSign()))%> 
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������κ�:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=stoproductForm.getBatchIdParts()==null?"":stoproductForm.getBatchIdParts()%>
		</td>
	</tr>		
</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
					<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


