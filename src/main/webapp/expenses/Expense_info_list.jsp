<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.expenses.ExpensesInfo" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head> 
<title>费用信息维护</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
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
function doPrintTaxi(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].expensesId.value == null ||document.forms[0].expensesId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	 
	window.location="TaxiInfo.do?act=v&expensesId="+document.forms[0].expensesId.value; 
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
 
 
	document.forms[0].act.value = "list"; 
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
<input type=hidden name=act value="list"> 
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
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
        
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >费用编号</td>
						<td >部门</td> 
						<td >项目</td>
						<td >录入员</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							ExpensesInfo vo = (ExpensesInfo) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getExpensesId()%></td>
						<td><%=vo.getDept()==null?"":SingleDicMap.getDicItemVal("9990", vo.getDept())%></td> 
						<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectNo()==null?"":vo.getProjectNo()%>"><%=vo.getProjectNo()==null?"":vo.getProjectNo()%></a></td>
						<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getCurrUser()) %></td>
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getExpensesId()%>')"> </label></td> 
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
						<input type="button" class="Button" name="add" id="expenses_info_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<input type="button" class="Button"  name="edit" id="expenses_info_u" value="修改" onClick="doEdit()"/> &nbsp;  
						<input type="button" class="Button"  name="delete" id="expenses_info_d" value="删除" onClick="doDelete()"/> &nbsp;  
						<input type="button" class="Button"  name="print" id="expenses_info_p" value="打印页面" onClick="doPrint()"/> &nbsp;
						<input type="button" class="Button"  name="print" id="expenses_taxi_p" value="出租车打印页面" onClick="doPrintTaxi()"/>  
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

