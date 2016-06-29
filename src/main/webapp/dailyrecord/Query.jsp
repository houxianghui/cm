<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.dailyrecord.DailyRecord"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>工时填写查询</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "qa"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qa";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 <script type="text/javascript" src="js/calendar.js"></script>
 
<html:form method="post" action="DailyRecord.do"> 
<input type=hidden name=act value="qa"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
    	<tr class="dtPanel_Top01">
    		<td align="center">工时填写查询</td>
    	</tr>
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 姓名：
            <html:select property="userId">
            	<html:optionsCollection name="dailyRecordForm" property="staff"/>
            </html:select>
            	项目编号：
            <html:text property="projectId" styleClass="Textfield" size="15"></html:text>
           &nbsp;起始日期：<html:text property="startDate" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            &nbsp;截止日期：<html:text property="endDate" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td nowrap>&nbsp;项目编号</td>
						<td nowrap>姓名</td>
						<td nowrap>&nbsp;项目阶段</td>
						<td nowrap>填报日期</td>
						<td nowrap>工时（小时）</td>
						<td nowrap>录入日期</td>
						<td>工作内容</td>
 						<td>遗留问题</td>
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							DailyRecord vo = (DailyRecord) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><%=vo.getProjectId()%></td>
						<td nowrap><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getUserId())%></td>
						<td><%=Steps.valueOf(vo.getStep()).getDesc()%></td> 
						<td><%=vo.getWorkDate() %></td>
						<td><%=vo.getTaskCost() %></td>
						<td><%=vo.getInputDate() %></td>
						<td width="30%" align="left"><%=vo.getWorkMemo() %></td>
						<td align='left' width="30%"><%=vo.getWorkIssue()%></td>  
						
 
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
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

