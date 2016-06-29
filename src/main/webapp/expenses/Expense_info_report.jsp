<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.expenses.ExpensesInfo" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
<script type="text/javascript" src="js/calendar.js"></script> 
<html> 
<head> 
<title>费用信息报表</title> 

 
<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	})
}) 
function doAdd(){ 
	//增加 
	window.location="ExpensesInfo.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].expensesId.value == null ||document.forms[0].expensesId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
function doPrint(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].expensesId.value == null ||document.forms[0].expensesId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='v';	 
	document.forms[0].submit(); 
}  
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].expensesId.value == null ||document.forms[0].expensesId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='d'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "report"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(expenses_id_var) { 
	document.forms[0].expensesId.value=expenses_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
 
<html:form method="post" action="ExpensesInfo.do"> 
<input type=hidden name=act value="report"> 
<input type=hidden name=requery > 
<input type=hidden name=expensesId> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
            
          <tr class="dtPanel_Top02"> 
      
            <td>&nbsp;部门：
            <html:select property="depart_f"> 
	         	<html:optionsCollection name="expensesInfoForm" property="depart_f_options" />               
	        </html:select>  
			项目编号：<html:text property="project_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;&nbsp;&nbsp;&nbsp;
			费用类别：
            <html:select property="feeKind_f"> 
	         	<html:optionsCollection name="expensesInfoForm" property="fee_kind_options" />               
	        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
	                            日期从：<html:text property="regDate_from" styleClass="Textfield" size="8" maxlength="8" onclick="pickDate(this)"/>&nbsp;&nbsp;&nbsp;&nbsp;
			到：<html:text property="regDate_to" styleClass="Textfield" size="8" maxlength="8" />&nbsp;&nbsp;&nbsp;&nbsp;
        	 用户选择<html:select property="userId">
				<html:optionsCollection name="expensesInfoForm" property="staffCollection"/>
			</html:select>&nbsp;&nbsp;&nbsp;&nbsp;		
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >部门名称</td> 
						<td >项目编号</td>
						<td >项目名称</td>
						<td >日期</td>
						<td >个人名称</td>
						<td >金额</td> 
 
					</tr> 
			<% 
					List list = (List)request.getAttribute("list"); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							ExpensesInfo vo = (ExpensesInfo) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main"> 					
						<td><%=vo.getDept()==null?"":SingleDicMap.getDicItemVal("9990", vo.getDept())%></td> 
						<td><%=vo.getProjectNo()==null?"":vo.getProjectNo()%></td>
						<td><%=vo.getProjectName()%></td>
						<td><%=vo.getRegDate()%></td>
						<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getCurrUser()) %></td>
						<td><%=vo.getTotalAmt()%></td> 
					</tr> 
 
				<% 
							} 
						} 
				%> 
				<tr align="center" class="dtPanel_Main"> 
					<td colspan="5" align="left">小计：</td>
					<td align="center"> <%= (java.lang.Double)request.getAttribute("totAmt")%></td> </tr>
				</table> 

			<br> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

