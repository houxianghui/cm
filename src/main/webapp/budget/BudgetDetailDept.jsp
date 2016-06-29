<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.budget.detail.BudgetDetail" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
<html> 
<head> 
<title>预算报表</title> 

<script type="text/javascript" src="js/calendar.js"></script> 
<script language="javascript"> 

$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	})
})  

function doPrint(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].budgetId.value == null ||document.forms[0].budgetId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='v';	 
	document.forms[0].submit(); 
}  

 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 

 	
	document.forms[0].act.value = "dept"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(budget_id_var) { 
	document.forms[0].budgetId.value=budget_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "dept";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
 
<html:form method="post" action="BudgetDetail.do"> 
<input type=hidden name=act value="dept"> 
<input type=hidden name=requery > 
<input type=hidden name=budgetId> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
            
          <tr class="dtPanel_Top02"> 
            <td>&nbsp;部门：
            <html:select property="depart_f"> 
	         	<html:optionsCollection name="budgetDetailForm" property="depart_f_options" />               
	        </html:select>  &nbsp;&nbsp;
			费用类别：
            <html:select property="feeKind_f"> 
	         	<html:optionsCollection name="budgetDetailForm" property="fee_kind_options" />               
	        </html:select>&nbsp;&nbsp;
	                            月份从：<html:text property="regDate_from" styleClass="Textfield" size="8" maxlength="8" />&nbsp;&nbsp;
			到：<html:text property="regDate_to" styleClass="Textfield" size="8" maxlength="8" />&nbsp;&nbsp;
        		
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >部门名称</td> 
						<td >费用类别</td>
						<td >预算金额</td>
						<td >已使用金额</td> 
						<td >已使用比例</td> 
						<td >余额</td>
 
					</tr> 
			<% 
					List list = (List)request.getAttribute("list"); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							BudgetDetail vo = (BudgetDetail) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main"> 					
						<td><%=vo.getDepart()==null?"":SingleDicMap.getDicItemVal("9990", vo.getDepart())%></td> 
						<td><%=SingleDicMap.getDicItemVal("7001", vo.getFeeKind())%></td>
						<td><%=vo.getFeeAmt()%></td>
						<td><%=vo.getFeeBal() %></td>
						<td><%=Format.formatPercent(vo.getFeeBal()/vo.getFeeAmt())%></td>
						<td><%=vo.getFeeAmt()-vo.getFeeBal()%></td>  
					</tr> 
 
				<% 
							} 
						} 
				%> 
				<tr align="center" class="dtPanel_Main"> 
					<td colspan="2" align="left">小计：</td>
					<td><%= (java.lang.Double)request.getAttribute("totAmt")%></td>
					<td><%= (java.lang.Double)request.getAttribute("totBal")%></td>
					<td><%=Format.formatPercent((java.lang.Double)request.getAttribute("totBal")/(java.lang.Double)request.getAttribute("totAmt"))%></td>					
					<td><%= (java.lang.Double)request.getAttribute("totOTB")%></td> 
				</table> 

			<br> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

