<%@page import="com.yly.stor.Stoappinfo"%>
<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="stoAppForm" scope="request"  class="com.yly.stor.StoAppInfoForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>冲回记录</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "popList";
	document.forms[0].submit(); 
} 
 
function setPKey(formNo_var) { 
	opener.document.forms[0].rsvd.value=formNo_var;
	window.close();	
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "popList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="popList">
<html:hidden property="prodId"/>
<html:hidden property="operationType"/>
<html:hidden property="currPeriodAmt"/>

<%=ViewUtil.getTitle("冲回记录")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			冲回日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			厂商:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="manufacIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
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
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoappinfo vo = (Stoappinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
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
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
			
		</tr>

		<%}
}%>

	
<% 
}
		%>
</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

</html:form>

<p>&nbsp;</p>
</body>
</html>


