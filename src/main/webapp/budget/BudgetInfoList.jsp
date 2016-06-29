<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.budget.BudgetInfo" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head> 
<title>预算信息维护</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="BudgetInfo.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].budgetId.value == null ||document.forms[0].budgetId.value == "") { 
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
	if(document.forms[0].budgetId.value == null ||document.forms[0].budgetId.value == "") { 
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
 
function setPKey(budgetId_var) { 
	document.forms[0].budgetId.value=budgetId_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="BudgetInfo.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=budgetId> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
            
          <tr class="dtPanel_Top02"> 
      
            <td>&nbsp;部门：
            <html:select property="depart_f"> 
	         	<html:optionsCollection name="budgetInfoForm" property="depart_f_options" />               
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
						<td >预算编号</td>
						<td >预算类别</td> 
						<td >部门</td> 
						<td >项目</td>
						<td >费用类型</td> 
						<td >录入员</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							BudgetInfo vo = (BudgetInfo) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getBudgetId()%></td>
						<td><%=SingleDicMap.getDicItemVal("7000", vo.getBudgetKind())%></td>
						<td><%=vo.getDepart()==null?"":SingleDicMap.getDicItemVal("9990", vo.getDepart())%></td> 
						<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectNo()==null?"":vo.getProjectNo()%>"><%=vo.getProjectNo()==null?"":vo.getProjectNo()%></a></td>
						<td><%=SingleDicMap.getDicItemVal("7001", vo.getFeeKind())%></td> 
						<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getUserId()) %></td>
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getBudgetId()%>')"> </label></td> 
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
						<input type="button" class="Button" name="add" id="budget_info_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<input type="button" class="Button"  name="edit" id="budget_info_u" value="修改" onClick="doEdit()"/> &nbsp;  
						<input type="button" class="Button"  name="delete" id="budget_info_d" value="删除" onClick="doDelete()"/> 
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

