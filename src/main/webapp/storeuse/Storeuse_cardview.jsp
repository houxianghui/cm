<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="pageResultLsInfo" scope="request" class="com.eis.base.PageObject" />
<jsp:useBean id="storeuseForm" scope="request"  class="com.yly.reuse.StoreuseForm" />


<html>

<%int maxPage = 1;
if (pageResultLsInfo != null)
	maxPage = pageResultLsInfo.getTotalPage();
%>
<head>
<title>回收库产品明细</title>
</head>
<body>
<html:form method="post" action="Storeuse.do">
<input type=hidden name=act value="r">

<%=ViewUtil.getTitle("回收库产品明细")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM卡号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getSamId()%>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM印刷卡号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getSamCSN()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		OA申请号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getOAappNo()%>
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(storeuseForm.getProdId()))%> 
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%if(storeuseForm.getAppTypeId().equals("201") ||storeuseForm.getAppTypeId().equals("202")||storeuseForm.getAppTypeId().equals("301") ||storeuseForm.getAppTypeId().equals("302")){%>
		模块版本:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, storeuseForm.getBinFileVer())%> 
		<%}else{%>
		产品通信速率:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, String.valueOf(storeuseForm.getPhiTypeId()))%> 
		<%}%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品应用类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getAppTypeId()==null?"":ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(storeuseForm.getAppTypeId()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		密钥类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(storeuseForm.getKeyType()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		厂商代码:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(storeuseForm.getManufacId())) %>	
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		入库批次号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getBatchId()==null?"":storeuseForm.getBatchId()%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品状态:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(storeuseForm.getWkState()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品状态变更日期:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getWkStateChgDate()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		出回库状态:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(storeuseForm.getIOState()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		出回库状态变更日期:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getIOStateChgDate()%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		出库次数:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getReuseTime()%>
		</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		卡物理状态:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PHY_STAT,String.valueOf(storeuseForm.getCardPhyStat()))%>
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		申请单位:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(storeuseForm.getUnitId()))%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		发行时间:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getIssueTime()==null?"":storeuseForm.getIssueTime()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		检测结果:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN,String.valueOf(storeuseForm.getDetectSign()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		检测时间:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getDetectTime()==null?"":storeuseForm.getDetectTime()%>
		</td>
	</tr>		
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		支付方式 :
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(storeuseForm.getPaymentType()))%>				
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		领用类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(storeuseForm.getConsumeType()))%>
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		是否装载维护密钥二:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(storeuseForm.getW2Sign()))%> 
		</td>
	</tr>	
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		充值密钥使用权限不需要认证:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(storeuseForm.getAuthSign()))%> 
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		配件批次号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=storeuseForm.getBatchIdParts()==null?"":storeuseForm.getBatchIdParts()%>
		</td>
	</tr>		
</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
					<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


