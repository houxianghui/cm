<%@page import="com.yly.conf.Callfuncconf"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>调用函数配置</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Callfuncconf.do?act=c"; 
} 

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
<html:form method="post" action="Callfuncconf.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("调用函数配置")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>业务类型：
			<html:select property="operationType" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="operationTypecollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
  		  </td> 
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>调用函数编号</td>
			<td>业务类型</td>
			<td>厂商</td>
			<td>产品类型</td>
			<td>应用类型</td>
			<td>函数1</td>						
			<td>函数2</td>
			<td>函数3</td>
			<td>函数4</td>
			<td>操作员</td>
			<td>录入日期</td>
			<td>备注</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Callfuncconf vo = (Callfuncconf) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getCallerId() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType()))%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, vo.getManufacId()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId()) %></td>
			<td><%=vo.getApplyTypeId()!=null?ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getApplyTypeId()):"/"%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc1()) %></td>						
			<td><%=vo.getFunc2()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc2()) %></td>
			<td><%=vo.getFunc3()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc3()) %></td>		
			<td><%=vo.getFunc4()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc4()) %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=vo.getRemarks()%></td>				
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
		<tr>
			<td height="25" align="center">
			<input type="button" value="增加调用函数配置" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


