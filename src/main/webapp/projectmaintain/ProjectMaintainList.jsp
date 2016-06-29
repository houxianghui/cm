<%@page import="com.blue.enums.Status"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.project.ProjectList"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.projectmaintain.ProjectMaintainVO" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目维护</title>

<script language="javascript"> 

function doAdd(){ 
	//增加 
	window.location="ProjectMaintainInit.do"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='ep';	 
	document.forms[0].submit(); 
} 
function doEditPreProject(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='epp';	 
	document.forms[0].submit(); 
} 

function requireChange(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	window.location.href="ChangeRecord.do?act=list&&projectId="+document.forms[0].projectId.value;
} 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='dp'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "qpl";
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var,status_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].stat.value=status_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qpl";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

$(function(){
	$(":button[name!='query']").attr("class","Button");
	$(":button[name='setMileStone']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="MileStone.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='finishProject']").attr("class","Button").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		if(!confirm("项目完成后，状态将不可恢复，确认执行此操作?")){
			return;
		}
		window.location.href="ProjectMaintain.do?act=finish&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='files']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="ProjectFiles.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='riskRecord']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="RiskRecord.do?act=list&projectId="+document.forms[0].projectId.value;
	});

	$(":button[name='projectPlan']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="ProjectPlan.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='reviewRecord']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="ReviewRecord.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='editProgram']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		window.location.href="ProgramMaintain.do?act=edit&&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='findConflick']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		document.forms[0].act.value='gcp';	 
		document.forms[0].submit(); 
	});
	$(":button[name='doDistribute']").click(function(){
		//修改 
		//检查是否有选中的纪录 
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('请先选择纪录'); 
			return; 
		} 
		//提交表单 
		window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value+"&status="+document.forms[0].stat.value;
	});
});

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="ProjectMaintain.do">
<input type=hidden name=act value="qpl">
<html:hidden property="projectId"/>
<html:hidden property="stat"/>
<%=ViewUtil.getTitle("项目维护")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>：
			<html:text property="projectId_f" styleClass="Textfield" size="20"></html:text>
			项目名称：
			<html:text property="projectName" styleClass="Textfield" size="20"></html:text>
			检查日期：<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			检查天数：<html:text property="day" styleClass="Textfield" size='4'/>
			
			状态：
			<html:select property="status_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="statusCollection"/>
			</html:select>
			当前阶段：
			<html:select property="step_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
			</html:select>
			优先级:<html:select property="priority" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="priorityCollection"/>
			</html:select>
			部门:<html:select property="owning" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="owningCollection"/>
			</html:select>
			</td></tr><tr align="left" class="dtPanel_Top01" height="28"><td class="dtPanel_Top01">
			<input id="query" name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td align="center">进度</td>
			<td align="center">附件</td>
			<td nowrap>&nbsp;项目编号</td>
			<td>&nbsp;项目名称</td>
			<td>所属部门</td>
			<td nowrap>&nbsp;项目阶段</td>
			<td>优先级</td>
			<td>项目状态</td>
			<td>计划开始日期</td>
			<td>计划结束日期</td>
			<td>&nbsp;备注</td>
			<td align="center">选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectList vo = (ProjectList) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td align="center">
			<htm:indicator exclude="<%=Status.F.toString().equals(vo.getStat()) %>" date="<%=vo.getPlanEndDate() %>"></htm:indicator>
			</td>
			<td align="center">
			<htm:linkIndicator hasLink="<%=vo.getIsInContract() %>" projectId="<%=vo.getProjectId()%>"></htm:linkIndicator>
			</td>
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>">&nbsp;<%=vo.getProjectId()%></a></td>
			<td>&nbsp;<%=vo.getProjectName()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DEPART, vo.getOwning()) %></td>			
			<td>&nbsp;<%=Steps.valueOf(vo.getStep()).getDesc()%> </td>
			<td>&nbsp;<%=SingleDicMap.getDicItemVal(SingleDic.PRIORITY,vo.getPriority()+"") %></td>
			<td><htm:statusInd status="<%=vo.getStat() %>"></htm:statusInd></td>	
			<td align="center"><%=DateUtil.format(vo.getPlanStartDate()) %></td>
			<td align="center"><%=DateUtil.format(vo.getPlanEndDate())%></td>
			<td>&nbsp;<%=vo.getMemo()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStat()%>')">
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
			<auth:button value="项目变更" onClick="requireChange()" id="projectChange" name="projectChange"></auth:button>
			<auth:button value="风险记录" onClick="return(0)" id="riskRecord" name="riskRecord"></auth:button>
			<auth:button value="评审记录" onClick="return(0)" id="reviewRecord" name="reviewRecord"></auth:button>
			<auth:button value="增加项目" onClick="doAdd()" id="addProject" name="addProject"></auth:button>
			<auth:button value="修改项目" onClick="doEdit()" id="editProject" name="editProject"></auth:button>
			<auth:button value="删除项目" onClick="doDelete()" id="deleteProject" name="deleteProject"></auth:button>
			<auth:button value="修改前驱项目" onClick="doEditPreProject()" id="preProject" name="preProject"></auth:button>
			<auth:button value="维护程序列表" onClick="return(0)" id="editProgram" name="editProgram"></auth:button>
			<auth:button value="冲突检测" onClick="return(0)" id="findConflick" name="findConflick"></auth:button>
			<auth:button value="项目分配" onClick="return(0)" id="doDistribute" name="doDistribute"></auth:button>
			<auth:button value="里程碑设置" onClick="return(0)" id="setMileStone" name="setMileStone"></auth:button>
			<auth:button value="附件维护" onClick="return(0)" id="files" name="files"></auth:button>
			<auth:button value="项目完成" onClick="return(0)" id="finishProject" name="finishProject"></auth:button>
			<auth:button value="项目计划表" onClick="return(0)" id="projectPlan" name="projectPlan"></auth:button>
			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


