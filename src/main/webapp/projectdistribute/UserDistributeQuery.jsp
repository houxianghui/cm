<%@page import="com.blue.scale.ScaleCache"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.enums.Status"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>员工工作查询</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "queryUserDist";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

$(function(){
	$(":button").attr("class","Button");
	$("#search").click(function(){
		document.forms[0].submit(); 
	});
});
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="queryUserDist">


<%=ViewUtil.getTitle("员工任务列表")%>
<%
	java.util.Calendar c = java.util.Calendar.getInstance();
	java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMdd");
	String today = sf.format(c.getTime());
	String msg = "";
%>
	
	<table class=heightspace_top3>
		<tr>
			<td>用户选择<html:select property="userId">
				<html:optionsCollection name="projectDistributeForm" property="staffCollection"/>
			</html:select>
			<input type="Button" value="搜索" id="search">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" >
			<td>进度</td>
			<td>附件</td>
			<td>倒计时</td>
			<td nowrap>项目编号</td>		
			<td >项目名称</td>	
			<td nowrap>项目所处阶段</td>	
			<td nowrap>任务阶段</td>	
			<td>活动项</td>
			<td>任务名称</td>	
			<td nowrap>开始时间</td>
			<td nowrap>结束时间</td>
			<td>成果物</td>
			<td>备注</td>
			<td>提醒</td>	
			<td>选择</td>
		</tr>
		<%if (pageResult != null) {
			StringBuffer warning = new StringBuffer("任务");
			boolean needAlert = false;
			List list = pageResult.getList();
			if (list != null) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					com.projectmaintain.ProjectDistributeVO vo = (com.projectmaintain.ProjectDistributeVO) iter.next();
					if(vo.getEndDate().compareTo(today)<0){
						msg = "任务已经超期限，请与负责人陈述延期原因";
					}else if(vo.getEndDate().compareTo(today) == 0){
						msg = "任务今天到期，请尽快完成";
					}else{			
						if("1".equals(vo.getReady())){
							msg = "任务处于可执行阶段";
						}else{
							msg = "";
						}
					}
					long days = DateUtil.getDays(today,vo.getEndDate());
					if(days<=vo.getNotifyDay() && days >=0){
						needAlert = true;
						warning.append(vo.getProjectId()+" ");
					}
					%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td align="center"><htm:indicator exclude="<%=false %>" date="<%=vo.getEndDate()%>"></htm:indicator></td>
			<td><htm:linkIndicator hasLink="<%=vo.getHasLink() %>" projectId="<%=vo.getProjectId() %>"></htm:linkIndicator></td>
			
			<td><%=vo.getReminingDays() %>天</td>
					<%
					boolean isProject = false;
					if("P".equals(vo.getType())){
						isProject = true;
					}
					if(isProject){ %>
						<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
					<%}else{%>
						<td><a href="WorkList.do?act=view&workId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
					<%} %>
					<td><%=vo.getProject().getProjectName() %></td>
					<td><%=Steps.valueOf(vo.getProject().getStep()).getDesc() %></td>
					<%if(isProject){ %>
						<td><%=Steps.valueOf(vo.getStep()).getDesc()%> </td>
					<%}else{ %>
						<td><%=Steps.valueOf(vo.getStep()).getDesc()%> </td>
					<%} %>
					<td><%=ScaleCache.getActionName(vo.getActionId()) %></td>
					<td align="left"><%=vo.getTaskName() %></td>
					<td><%=vo.getStartDate()%></td>
					<td><%=vo.getEndDate()%></td>
					<td><%=ScaleCache.getAchieves(vo.getActionId())%></td>
					<td><%=vo.getMemo()%></td>
					<td><%=msg%></td>
					<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStep()%>','<%=vo.getId()%>','<%=vo.getType()%>')">
					</label></td>
		</tr>

		<%	}
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
</html:form>

<p>&nbsp;</p>
</body>
</html>


