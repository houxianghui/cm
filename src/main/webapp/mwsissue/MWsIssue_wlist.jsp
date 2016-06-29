<%@page import="com.yly.issue.Mwsissuetb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>发行加工单列表</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(formNo_var) { 
	document.forms[0].formNo.value=formNo_var; 
	
} 

function doIssue(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].formNo.value == null ||document.forms[0].formNo.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	

	document.forms[0].act.value = "issueInit";
	document.forms[0].submit();
	
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Mwsissuetb.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery> 
<html:hidden property="taskCtrlNo"/>
<html:hidden property="issueDoneAmt"/>
<html:hidden property="phiTypeId"/>
<html:hidden property="prodId"/>
<html:hidden property="formNo"/>
<%=ViewUtil.getTitle("发行加工单列表")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td nowrap>
			分配日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>


			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>单据编号</td>
			<td nowrap>分配时间</td>
			<td nowrap>加工单序号</td>	
			<td nowrap>产品类型</td>	
			<td nowrap>密钥类型</td>	
			<td nowrap>通信速率</td>	
			<td nowrap>应用类型</td>		
			<td nowrap>批次号</td>
			<td nowrap>加工数量</td>
			<td nowrap>已发行数量</td>
			<td nowrap>当前SAM号</td>	
			<td nowrap>结束SAM号</td>
			<td nowrap>单据状态</td>
			<td nowrap>单选</td>	
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Mwsissuetb vo = (Mwsissuetb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><a href="Issuetaskctrl.do?act=ql&taskCtrlNo=<%=vo.getTaskCtrlNo()%>"><%=vo.getFormNo()%></a></td>	
			<td><%=vo.getFormTime()%></td>
			<td><%=vo.getWsSnr()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID,vo.getApplyAttr()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE,vo.getBatchId())%></td>	
			<td><%=vo.getWorkSheetAmt()%></td>
			<td><%=vo.getIssueDoneAmt()%></td>	
			<td><%=vo.getSamId()%></td>		
			<td><%=vo.getSamIdEnd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, String.valueOf(vo.getFormState()))%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
		</tr>

		<%}
}
}%>

</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td nowrap class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value="发行任务" class="Button" onClick="doIssue()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


