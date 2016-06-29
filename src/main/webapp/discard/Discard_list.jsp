<%@page import="com.yly.discard.Disproduct"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="disproductForm" scope="request"  class="com.yly.discard.DisproductForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>报废记录列表</title>
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


</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Disproduct.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery > 
<html:hidden property="OAappNo"/>
<%=ViewUtil.getTitle("报废记录列表")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
 			sam卡号段:	
			从<html:text property="samId_min" styleClass="Textfield" size="12" />
			到<html:text property="samId_max" styleClass="Textfield" size="12" />
			</td>
		</tr>
		<tr>
			<td>
			产品类型:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="prodIdcollection"/>
			</html:select>
			通信速率:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="phiTypeIdcollection"/>
			</html:select>
			应用类型:
			<html:select property="appTypeId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="appTypeIdcollection"/>
			</html:select>						
			<input type="button" value="搜索" class="Button" onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>SAM卡号</td>
			<td>OA申请号</td>
			<td>申请单位</td>
			<td>产品类型</td>
			<td>产品通信速率</td>
			<td>产品应用类型</td>
			<td>密钥类型</td>
			<td>认证方式</td>
			<td>发行时间</td>
			<td>作废时间</td>
			<td>领用类型</td>
			<td>检测结果</td>
			<td>产品状态</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Disproduct vo = (Disproduct) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			 
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>&operationType=71"><%=vo.getSamId() %></a></td>	
			<td><%=vo.getOAappNo()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getAppTypeId()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getAuthSign().toString())%></td>
			<td><%=vo.getIssueTime() %></td>	
			<td><%=vo.getWkStateChgDate()%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE, String.valueOf(vo.getWkState()))%></td>						
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
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">

	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


