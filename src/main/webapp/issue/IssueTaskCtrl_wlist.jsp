<%@page import="com.yly.issue.IssueappForm"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>待分配发行任务列表</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "wq";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "wq";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function setPKey(taskCtrlNo_var,issueAmt_var,phiTypeId_var,issueDoneAmt_var,prod_var,operation_var,applytype_var) { 
	document.forms[0].taskCtrlNo.value=taskCtrlNo_var; 
	document.forms[0].issueAmt.value=issueAmt_var; 	
	document.forms[0].phiTypeId.value=phiTypeId_var; 		
	document.forms[0].issueDoneAmt.value=issueDoneAmt_var; 
	document.forms[0].prodId.value=prod_var; 
	document.forms[0].operationType.value=operation_var; 	
	document.forms[0].appTypeId.value=applytype_var; 
} 
function checkAll(field) //全选
{
	var checkflag = "false";
	if (checkflag == "false") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "false")
			{ document.forms[0].cx.checked = true;}
		
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = true;
			}
			checkflag = "true";
			return ;
		}	
	}
} 
function cancelCheckAll(field)//全部取消
{
	var checkflag = "true";
	if (checkflag == "true") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "true")
			{ document.forms[0].cx.checked = false;}
			
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = false;
			}
			checkflag = "false";
			return ;
		}
	}
}
function doBatchAssign(){ 

	var flag_cc = 0;
	var cx_field = document.forms[0].cx;
	var cx_len = document.forms[0].cx.length;
	
	if(cx_len == undefined){
		cx_len = 1;
		for(var cc = 0 ; cc < cx_len ; cc++){
			if(document.forms[0].cx.checked){
				flag_cc = 1;
				break;
			}	
		}
	}
	else{
		for(var cc = 0 ; cc < cx_len ; cc++){	
			if(document.forms[0].cx[cc].checked){
				flag_cc = 1;
				break;
			}
		
		}
	}
	if(flag_cc == 0){
		alert('请先选择记录');
		return;
	}
	document.forms[0].act.value = "batch_assign";
	document.forms[0].submit();
	
} 
function doSingleAssign(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].taskCtrlNo.value == null ||document.forms[0].taskCtrlNo.value == "") { 
		alert('请先选择单选纪录'); 
		return; 
	} 
	var s = prompt('请输入分配数量','');
	if(s == null || s==""||s==0){
		alert("请输入分配数量");
		return;
	}
	var s1=document.forms[0].issueAmt.value-document.forms[0].issueDoneAmt.value;
	if(eval(s)> eval(s1) ){
		alert("数量必须小于"+s1);
		return;
	}
		
	s = encodeURI(encodeURI(s));
	document.forms[0].issueAmt.value =s;
	document.forms[0].act.value = "single_assign";
	document.forms[0].submit();
	
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Issueapp.do">
<input type=hidden name=act value="wq">
<input type=hidden name=requery> 
<html:hidden property="appNo"/>
<html:hidden property="taskCtrlNo"/>
<html:hidden property="issueAmt"/>
<html:hidden property="issueDoneAmt"/>
<html:hidden property="appTypeId"/>
<html:hidden property="operationType"/>
<%=ViewUtil.getTitle("待分配发行任务列表")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			录入日期:			
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
 			申请单位:
			<html:select property="unitId_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="unitIdcollection"/>
			</html:select>
			业务类型:
			<html:select property="operationType_f" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="operationTypecollection"/>
			</html:select>
			产品:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="prodIdcollection"/>
			</html:select>			
			通信速率:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="issueappForm" property="phiTypeIdcollection"/>
			</html:select>			
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>多选</td>		
			<td>OA申请号</td>
			<td>发行任务明细号</td>
			<td>业务类型</td>
			<td>产品类型</td>	
			<td>密钥类型</td>	
			<td>通信速率</td>	
			<td>应用类型</td>		
			<td>模块程序版本</td>		
			<td>申请单位</td>
			<td>发行数量</td>
			<td>已分配数量</td>
			<td>起始SAM号</td>
			<td>结束SAM号</td>
			<td>领用方式</td>
			<td>支付方式</td>
			<td>单选</td>	
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			IssueappForm vo = (IssueappForm) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td>
			<label>
			<input type="checkbox" name="cx" value="<%=vo.getTaskCtrlNo()+","+vo.getPhiTypeId()+","+vo.getIssueAmt()+","+vo.getProdId()+","+vo.getIssueDoneAmt()+","+vo.getOperationType()+","+vo.getAppTypeId()%>"> 
			</label></td> 
			<td><a href="Issuetask.do?act=list&appNo=<%=vo.getAppNo()%>"><%=vo.getOAappNo()%></a></td>		
			<td><%=vo.getTaskCtrlNo()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>				
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId())) %></td>
			<td><%=vo.getBinFileVer()!=null?ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION,vo.getBinFileVer()):""%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>	
			<td><%=vo.getIssueAmt()%></td>
			<td><%=vo.getIssueDoneAmt()%></td>			
			<td><%=vo.getSamIdBegin() %></td>	
			<td><%=vo.getSamIdEnd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(vo.getPaymentType()))%></td>				
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getTaskCtrlNo()%>','<%=vo.getIssueAmt()%>','<%=vo.getPhiTypeId()%>','<%=vo.getIssueDoneAmt()%>','<%=vo.getProdId()%>','<%=vo.getOperationType()%>','<%=vo.getAppTypeId()%>')">
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
			<input type="button" name="check_all" class="Button" value="全选" onClick="checkAll(document.forms[0].cx);"/>  
			<input type="button" name="check2" class="Button" value="取消全选" onClick="cancelCheckAll(document.forms[0].cx);"/>  
			<input type="button" value="批量分配任务" class="Button" onClick="doBatchAssign()"/>
			<input type="button" value="单笔分配任务" class="Button" onClick="doSingleAssign()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


