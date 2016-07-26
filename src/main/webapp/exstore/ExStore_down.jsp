<%@page import="com.yly.stor.Stoappinfo"%>
<%@page import="com.yly.issue.IssueappForm"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="issueappForm" scope="request"  class="com.yly.issue.IssueappForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>POS原料出库</title>
<script language="javascript"> 

function doDownProgram() {  
	if(document.forms[0].batchIdParts.value == null ||document.forms[0].batchIdParts.value == "") { 
		alert('请选择配件批次号'); 
		return; 
	} 
	$.get("Issueapp.do?act=down&appNo=<%=issueappForm.getAppNo()%>&taskAmt=<%=issueappForm.getTaskAmt()%>&batchIdParts="+document.forms[0].batchIdParts.value,function(result){
		var json = $.parseJSON(result);
 		$("#buttonDown").hide();	
 		$("#buttonExOver").show();	
		return;
	});
} 
function setPKey(formNo_var) { 
	document.forms[0].batchIdParts.value=formNo_var; 
} 
function doOver() { 
	document.forms[0].act.value = "exlist";
	document.forms[0].submit();
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="exlist">
<input type=hidden name=requery > 
<html:hidden property="appNo"/>
<html:hidden property="formState"/>
<html:hidden property="batchIdParts"/>
<input type="hidden" id="selected" value="<%=(String)request.getAttribute("batchIdParts")%>"/>
<html:hidden property="taskAmt"/>
<%=ViewUtil.getTitle("POS原料出库")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<%=ViewUtil.getTitle("批次号:"+issueappForm.getFormNo()+"出库总数:"+issueappForm.getTaskAmt())%>	
	</table>
<%=ViewUtil.getTitle("配件原料列表")%> 
<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
				<td>批次号</td>
				<td>当前库存</td>
				<td>产品类型</td>
				<td>入库类型</td>
				<td>支持互通</td>
				<td>采购单价</td>
				<td>生产厂商</td>		
				<td>通信速率</td>
				<td>Pki存在</td>
				<td>印刷卡号范围</td>
				<td>选择</td>				
				</tr>
				<%List list = pageResult.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Stoappinfo vo = (Stoappinfo)iter.next();%>
		<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
			<td><a href="StoApp.do?act=r&formNo=<%=vo.getFormNo()%>"><%=vo.getFormNo()%></a></td>	
			<td><%=vo.getCurrPeriodAmt()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsHTCard())%></td>			
			<td><%=vo.getUnitPrice()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, vo.getManufacId()) %></td>							
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,vo.getPhiTypeId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsPki())%></td>
			<td><%=vo.getPressCardScale()==null?"":vo.getPressCardScale().trim()%></td>	
			<td align="center">
			<label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
			</label></td>
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>

</table>


	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<div id="buttonDown" style="display:" >
			<input type="button" value="选择配件并下载程序" class="Button" onClick="doDownProgram()"/>
			</div>
			<div id="buttonExOver" style="display:none" >
			<input type="button" value="出库完成" class="Button" onClick="doOver()"/>
			</div>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


