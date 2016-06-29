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
<title>我的项目</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qmp";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

function doFinish(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	if(!confirm('执行完成操作后，你将无法继续填写此任务的工时，确定吗？')) { 
		return; 
	} 
	document.forms[0].act.value="doFinish";
	document.forms[0].submit();
}
function doOther(){
	
	var projectId = document.forms[0].projectId.value;
	var step = document.forms[0].status.value;
	window.location.href="TASK_LIST.do?act=c&project_no=无&curr_step=8";
}
function addTaskDaily(){
	window.location.href="WorkDaily.do?act=new";
}
function setPKey(projectId_var,step_var,id_var,type_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].step.value=step_var;
	document.forms[0].id.value=id_var;	
	document.forms[0].type.value=type_var;
} 
$(function(){
	$(":button").attr("class","Button");
	$("#link").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 	
		window.location.href="ProjectFiles.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$("#risk").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 	
		window.location.href="RiskRecord.do?act=list&projectId="+document.forms[0].projectId.value;

	});
	$("#review").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 	
		window.location.href="ReviewRecord.do?act=list&projectId="+document.forms[0].projectId.value;

	});
	$("#finish").click(function(){
		doFinish();
	});
	$("#add").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		}
		window.location.href="DailyRecord.do?act=new&projectId="+$("input[name='projectId']").val()+"&step="+$("input[name='step']").val()+"&id="+$("input[name='id']").val();
	
	});
	$("#other").click(function(){
		window.location.href="OtherDaily.do?act=list";
	
	});
	$("#product").click(function(){
		window.location.href="OtherDaily.do?act=product"
	});
});
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="qmp">
<html:hidden property="projectId"/>
<html:hidden property="id"/>
<html:hidden property="step"/>
<html:hidden property="type"/>

<%=ViewUtil.getTitle("我的任务")%>
<%
	java.util.Calendar c = java.util.Calendar.getInstance();
	java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMdd");
	String today = sf.format(c.getTime());
	String msg = "";
%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
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
	if(needAlert){
		String first = (String)request.getSession().getAttribute("firstTime");
		if(CheckUtil.isEmptry(first)){
			warning.append("即将到期，请注意进度");
			request.getSession().setAttribute("firstTime","done");
		%>
		<script language="javascript">
		alert('<%=warning %>');
		</script>
		<%
		}
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
				<input type="button" value="风险提交" id="risk">
				<input type="button" value="评审提交" id="review">
				<input type="button" value="文档提交" id="link">
				<input type="button" value="任务完成" id="finish">
				<input type="button" value="填写项目工时" id="add">
				<input type="button" value="填写产品工时" id="product">
			</td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


