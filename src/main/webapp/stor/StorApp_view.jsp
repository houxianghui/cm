<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="stoAppForm" scope="request" class="com.yly.stor.StoAppInfoForm" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>


<html> 
<head>
<title>查看入库信息</title> 
<%String flag="1" ;%>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
</head>
<body> 
<p>&nbsp;</p> 
<html:form method="post" action="StoApp.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=operationType value=31> 
<%=ViewUtil.getTitle("入库信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>合同号:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getProjContNum()%>
		</td>
	</tr>
    <tr>
        <td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>合同名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getProjName()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品类型:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;	
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, stoAppForm.getProdId())%> 
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>厂商名称:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, stoAppForm.getManufacId())%> 
	</td>
	</tr>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>产品通信速率:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, stoAppForm.getPhiTypeId())%> 	
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>支持Pki:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, stoAppForm.getIsPki())%> 	
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>支持互联互通:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, stoAppForm.getIsHTCard())%> 	
		</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>数量:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getPurchaseAmt()%>
		&nbsp;&nbsp;&nbsp;单价:<%=stoAppForm.getUnitPrice()%>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>印刷卡号范围:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getPressCardScale()%>
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>经办人:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getPurchasePerson()%>
	</td>
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		备注:
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getRemarks()%>
	</td>
	</tr>		
<%if(stoAppForm.getProdId().equals("5")) {%>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>采购类型:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		&nbsp;<%=SingleDicMap.getDicItemVal(SingleDic.PURCH_TYPE, stoAppForm.getPurchaseType())%></div>
	</td>
	</tr>		
<%}else if(stoAppForm.getProdId().equals("4")) {%>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>模块版本号:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getModuleVersion()%>
	</td>
	</tr>		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%=ViewUtil.must()%>模块批次号:</div>
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
		<%=stoAppForm.getModuleBatchId()%>
	</td>
	</tr>				
<%} %>
	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="返回" class="Button" onClick="history.back()"/>
				</td>
	    </tr> 
  </table> 
 
</html:form> 

</body> 
</html> 
 

