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
<style type="text/css">
tr{
	background-color:#F7F7F7;
	COLOR:#333333;
	FONT-SIZE:9pt;
	FOTN-FAMILY:宋体,verdana,Arial, Helvetica;
	height:23px;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>原料批次选择</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "ql";
	document.forms[0].submit(); 
} 
 
function setPKey(formNo_var) { 
	document.forms[0].formNo.value=formNo_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doExCardCSN(){
	var tbName = "ex";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<input name='ex_"+rowID+"' id='ex_"+rowID+"' type='text' class='TextField' size='20' maxLength='20' onblur=noChinese(this) onkeyup='EngToUpperCase(this)' onkeydown='EngToUpperCase(this)' value=''/>";
	
	addRow(tbName,new Array(innerHtml1));
}
function doAdd(){
	//进行确认提示 
	if(document.forms[0].formNo.value==null || document.forms[0].formNo.value=="") { 
		alert('请先选择批次'); 
		return; 
	}
	document.forms[0].act.value = "exCardCSN";
	document.forms[0].submit(); 
}
function doDownProgram() {  
	if(document.forms[0].formNo.value == null ||document.forms[0].formNo.value == "") { 
		alert('请选择批次号'); 
		return; 
	} 
	$.get("Issueapp.do?act=down",function(result){
		var json = $.parseJSON(result);
		return;
	});
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/apply/cardApply.js"></script>
<html:form method="post" action="StoApp.do">
<input type=hidden name=act value="ql">
<html:hidden property="appNo"/>
<html:hidden property="operationType"/>
<html:hidden property="formNo"/>
<input type="hidden" name="currPeriodAmt" value=<%=String.valueOf(stoAppForm.getCurrPeriodAmt())%> id="currPeriodAmt"/>
<input name='txtTRLastIndex' type='hidden' id='ex.index' value="1" />



<%=ViewUtil.getTitle(SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(stoAppForm.getOperationType()))+"批次选择")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			厂商:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="manufacIdCollection"/>
			</html:select>
			产品:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="stoAppForm" property="prodIdCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
		<tr><td>
		出库数量:<%=stoAppForm.getCurrPeriodAmt()%></td>
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
			<td>模块批次号/采购类型/通信速率</td>
			<td>模块版本号/Pki存在</td>
			<td>印刷卡号范围/模块配件批次号</td>
			<td>录入日期</td>
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
			<%if(vo.getProdId().equals("4")) {%>
			<td><%=vo.getModuleBatchId()%></td>			
			<td><%=vo.getModuleVersion()%></td>					
			<td><%=vo.getRsvd()%></td>
			<%}else if(vo.getProdId().equals("5")){ %>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PURCH_TYPE,vo.getPurchaseType())%></td>	
			<td></td>
			<td></td>
			<%}else {%>									
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE,vo.getPhiTypeId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsPki())%></td>
			<td><%=vo.getPressCardScale()==null?"":vo.getPressCardScale().trim()%></td>	
			<%} %>
			<td><%=vo.getCurrDate()%></td>
			<td align="center">
			<label><input type="radio" name="param"	onClick="setPKey('<%=vo.getFormNo()%>')">
			</label></td>
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
		<table id="ex" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">序号</td><td width="20%">产品印刷卡号<input type="button" class="Button" onclick="doExCardCSN()" value="添加出库产品印刷卡号"></button></td><td width="5%">操作</td>
			</tr>
		</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<%if(stoAppForm.getOperationType()==34 ||stoAppForm.getOperationType()==42 ||stoAppForm.getOperationType()==52){%>
 			<input type="button" value="请先下载程序" class="Button" onClick="doDownProgram()"/> 
 			<%} %>
 			<input type="button" value="原料出库" class="Button" onClick="doAdd()"/>
 			</td>	
	
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


