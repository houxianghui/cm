<%@ page import="com.yly.issue.Issueapp" %>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>换损申请单</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Issueapp.do?act=exchange_new"; 
} 

function doQuery() {  
	document.forms[0].formState.value=0;
	document.forms[0].act.value = "exchangeList";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "exchangeList";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(appNo_var,formStat_var,oper_var,unit_var) { 
	document.forms[0].appNo.value=appNo_var; 
	document.forms[0].formState.value=formStat_var; 
	document.forms[0].operationType.value=oper_var; 	
	document.forms[0].unitId.value=unit_var; 	
} 

function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].appNo.value == null ||document.forms[0].appNo.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	if(document.forms[0].formState.value!=0){
		alert('只能维护状态是暂存的申请'); 
		return; 
	}
	//提交表单 
	document.forms[0].act.value='exchangeMainTain'; 
	document.forms[0].submit(); 

} 
function doPrint(){
	if(document.forms[0].appNo.value == null ||document.forms[0].appNo.value == "") { 
		alert('请选择记录'); 
		return; 
	} 
	if(document.forms[0].formState.value!=3) { 
		alert('单据未执行完成不允许打印'); 
		return; 
	} 
	window.location="PdfMaker.do?act=print&formNo="+document.forms[0].appNo.value+"&operationType="+document.forms[0].operationType.value; 
}
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="exchangeList">
<input type=hidden name=requery > 
<html:hidden property="appNo"/>
<html:hidden property="formState"/>
<html:hidden property="operationType"/>
<input type=hidden name=unitId > 
<%=ViewUtil.getTitle("换损申请单")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			换损日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			申请单位:
			<html:select property="unitId_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
			</html:select>
			业务类型:
			<html:select property="operationType_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="exChangeTypecollection"/>
			</html:select>
			单据状态:
			<html:select property="formState_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="formStatecollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>OA申请号</td>
			<td>申请单位</td>
			<td>换损总数</td>
			<td>业务类型</td>
			<td>单位联系人</td>
			<td>联系电话</td>
			<td>操作员</td>
			<td>建立时间</td>
			<td>单据状态</td>
			<td>支付金额</td>
			<td>备注</td>
			<td>选择</td>
			
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Issueapp vo = (Issueapp) iter.next();%>
			<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><a href="Lsinfo.do?act=list&appNo=<%=vo.getAppNo()%>"><%=vo.getOAappNo()%></a></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>			
			<td><%=vo.getTaskAmt() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>
			<td><%=vo.getUnitperson()%></td>
			<td><%=vo.getUnittel()%></td>
			<td><%=vo.getDirector()%></td>
			<td><%=vo.getCurrDate()%></td>	
			<td><%=vo.getFormState()!=null?SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, vo.getFormState().toString()):"" %></td>
			<td><%=vo.getTotalPrice()%></td>	
			<td><%=vo.getRemarks()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getAppNo()%>','<%=vo.getFormState()%>','<%=vo.getOperationType()%>','<%=vo.getUnitId()%>')">
			</label></td>			
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
			<input type="button" value="维护" class="Button" onClick="doEdit()"/>
			<input type="button" value="打印单据" class="Button" onClick="doPrint()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


