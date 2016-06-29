<%@page import="com.yly.ls.Lsinfo"%>
<%@ page import="com.yly.issue.Mwsissuetb"%>
<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="mwsissuetbForm" scope="request"  class="com.yly.issue.MWsIssuetbForm" />
<jsp:useBean id="pageResultLsInfo" scope="request" class="com.eis.base.PageObject" />


<html>

<%int maxPage = 1;
if (pageResultLsInfo != null)
	maxPage = pageResultLsInfo.getTotalPage();
%>
<head>
<title>发行加工单</title>
<script language="javascript"> 

function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
}
function doIssue(){ 
	document.forms[0].act.value = "issue";
	document.forms[0].submit();
	
} 
function doIssueDone(){ 
	document.forms[0].act.value = "closePort";
	document.forms[0].submit();
	
} 
function doExam(){ 
	document.forms[0].act.value = "E";
	document.forms[0].submit();
	
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Mwsissuetb.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery> 
<html:hidden property="formNo"/>
<%=ViewUtil.getTitle("发行加工单")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		单据编号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormNo()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		任务明细号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getTaskCtrlNo()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		制单时间:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormTime()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		加工单序号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWsSnr()%>
		</td>
	</tr>		

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(mwsissuetbForm.getProdId()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		密钥类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(mwsissuetbForm.getKeyType()))%> 
		</td>		
	</tr>	   

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品通信速率:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, String.valueOf(mwsissuetbForm.getPhiTypeId()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		产品应用属性:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.APPLY_ATTR, String.valueOf(mwsissuetbForm.getApplyAttr()))%> 
		</td>		
	</tr>	


	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		isam认证方式:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getAuthSign()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		是否装载维护密钥二:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getW2Sign()))%> 
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		厂商代码:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, String.valueOf(mwsissuetbForm.getManufacId()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		批次号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getBatchId()%>&nbsp;&nbsp;印刷卡号范围:<%=mwsissuetbForm.getPressCardScale()%>
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		加工数量:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWorkSheetAmt()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		起始SAM号--结束SAM号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamIdBegin()%>--<%=mwsissuetbForm.getSamIdEnd()%>
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM卡号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamId()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		已发行数量:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getIssueDoneAmt()%>
		</td>		
	</tr>	
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		单据状态:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, String.valueOf(mwsissuetbForm.getFormState()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		发行操作员:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, mwsissuetbForm.getIssueOperID()) %>
		</td>		
	</tr>


</table>

 <%=ViewUtil.getTitle("已发行列表")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">流水号</td>
					<td width="10%">SAM卡号</td>
					<td width="10%">SAM印刷卡号</td>
					<td width="10%">检测结果</td>	
					<td width="10%">错误码</td>						
					<td width="10%">原SAM号</td>	
					<td width="10%">原SAM印刷号</td>
					<td width="10%">发行员</td>	
					<td width="10%">发行时间</td>						
				</tr>
				<%List list = pageResultLsInfo.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Lsinfo vo = (Lsinfo)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
					<td><%=vo.getFlowNo()%></td>	
					<td><%=vo.getSamId()%></td>
					<td><%=vo.getSamCSN()%></td>
					<td><%=vo.getDetectSign()%></td>
					<td><%=vo.getErrorCode()%></td>
					<td><%=vo.getSamIdOld()%></td>
					<td><%=vo.getSamCSNOld()%></td>			
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td><%=vo.getCurrDate()%></td>		
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>
	<%
//产生翻页脚注 
if (pageResultLsInfo != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td nowrap class="dtPanel_Pager"><%=pageResultLsInfo.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<%if(mwsissuetbForm.getFormState()!=3){ %>
			<input type="button" value="发行" class="Button" onClick="doIssue()"/>
			<%}else{ %>
			<input type="button" value="发行完成" class="Button" onClick="doIssueDone()"/>
			<%} %>
			&nbps;&nbps;
			<input type="button" value="检测" class="Button" onClick="doExam()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


