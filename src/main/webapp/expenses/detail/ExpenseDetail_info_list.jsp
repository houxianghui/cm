<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.expenses.detail.ExpensesDetail" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
<jsp:useBean id="expensesDetailForm" scope="request" class="com.blue.expenses.detail.ExpensesDetailForm"/>
 
<html> 
<head> 
<title>费用报销维护</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	document.forms[0].act.value='c';	 
	document.forms[0].submit(); 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].edetailId.value == null ||document.forms[0].edetailId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].edetailId.value == null ||document.forms[0].edetailId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(id1,id2) { 
	document.forms[0].edetailId.value=id1; 
	document.forms[0].expensesId.value=id2; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="ExpensesDetail.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=edetailId>
<html:hidden property="expensesId" />
 
 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >时间</td>
						<td >内容</td>
						<td >单据张数</td> 
						<td >金额</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							ExpensesDetail vo = (ExpensesDetail) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getTime()%></td>
						<td><%=vo.getContent()%></td> 
						<td><%=vo.getInvoiceno()%></td> 
						<td><%=vo.getAmt()%></td> 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getEdetailId()%>','<%=vo.getExpensesId()%>')"> </label></td> 
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//产生翻页脚注 
				if(pageResult != null) { 
			%> 
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
			<br> 
 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<input type="button" class="Button" name="add" id="taxiInfo_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<input type="button" class="Button"  name="edit" id="taxiInfo_u" value="修改" onClick="doEdit()"/> &nbsp;  
						<input type="button" class="Button"  name="delete" id="taxiInfo_d" value="删除" onClick="doDelete()"/> 
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

