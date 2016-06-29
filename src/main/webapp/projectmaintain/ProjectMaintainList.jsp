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
<title>��Ŀά��</title>

<script language="javascript"> 

function doAdd(){ 
	//���� 
	window.location="ProjectMaintainInit.do"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='ep';	 
	document.forms[0].submit(); 
} 
function doEditPreProject(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='epp';	 
	document.forms[0].submit(); 
} 

function requireChange(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="ChangeRecord.do?act=list&&projectId="+document.forms[0].projectId.value;
} 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
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
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="MileStone.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='finishProject']").attr("class","Button").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		if(!confirm("��Ŀ��ɺ�״̬�����ɻָ���ȷ��ִ�д˲���?")){
			return;
		}
		window.location.href="ProjectMaintain.do?act=finish&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='files']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="ProjectFiles.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='riskRecord']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="RiskRecord.do?act=list&projectId="+document.forms[0].projectId.value;
	});

	$(":button[name='projectPlan']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="ProjectPlan.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='reviewRecord']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="ReviewRecord.do?act=list&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='editProgram']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		window.location.href="ProgramMaintain.do?act=edit&&projectId="+document.forms[0].projectId.value;
	});
	$(":button[name='findConflick']").click(function(){
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		document.forms[0].act.value='gcp';	 
		document.forms[0].submit(); 
	});
	$(":button[name='doDistribute']").click(function(){
		//�޸� 
		//����Ƿ���ѡ�еļ�¼ 
		if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		//�ύ�� 
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
<%=ViewUtil.getTitle("��Ŀά��")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>��
			<html:text property="projectId_f" styleClass="Textfield" size="20"></html:text>
			��Ŀ���ƣ�
			<html:text property="projectName" styleClass="Textfield" size="20"></html:text>
			������ڣ�<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			���������<html:text property="day" styleClass="Textfield" size='4'/>
			
			״̬��
			<html:select property="status_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="statusCollection"/>
			</html:select>
			��ǰ�׶Σ�
			<html:select property="step_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="stepCollection"/>
			</html:select>
			���ȼ�:<html:select property="priority" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="priorityCollection"/>
			</html:select>
			����:<html:select property="owning" styleClass="Select">
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
			<td align="center">����</td>
			<td align="center">����</td>
			<td nowrap>&nbsp;��Ŀ���</td>
			<td>&nbsp;��Ŀ����</td>
			<td>��������</td>
			<td nowrap>&nbsp;��Ŀ�׶�</td>
			<td>���ȼ�</td>
			<td>��Ŀ״̬</td>
			<td>�ƻ���ʼ����</td>
			<td>�ƻ���������</td>
			<td>&nbsp;��ע</td>
			<td align="center">ѡ��</td>

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
//������ҳ��ע 
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
			<auth:button value="��Ŀ���" onClick="requireChange()" id="projectChange" name="projectChange"></auth:button>
			<auth:button value="���ռ�¼" onClick="return(0)" id="riskRecord" name="riskRecord"></auth:button>
			<auth:button value="�����¼" onClick="return(0)" id="reviewRecord" name="reviewRecord"></auth:button>
			<auth:button value="������Ŀ" onClick="doAdd()" id="addProject" name="addProject"></auth:button>
			<auth:button value="�޸���Ŀ" onClick="doEdit()" id="editProject" name="editProject"></auth:button>
			<auth:button value="ɾ����Ŀ" onClick="doDelete()" id="deleteProject" name="deleteProject"></auth:button>
			<auth:button value="�޸�ǰ����Ŀ" onClick="doEditPreProject()" id="preProject" name="preProject"></auth:button>
			<auth:button value="ά�������б�" onClick="return(0)" id="editProgram" name="editProgram"></auth:button>
			<auth:button value="��ͻ���" onClick="return(0)" id="findConflick" name="findConflick"></auth:button>
			<auth:button value="��Ŀ����" onClick="return(0)" id="doDistribute" name="doDistribute"></auth:button>
			<auth:button value="��̱�����" onClick="return(0)" id="setMileStone" name="setMileStone"></auth:button>
			<auth:button value="����ά��" onClick="return(0)" id="files" name="files"></auth:button>
			<auth:button value="��Ŀ���" onClick="return(0)" id="finishProject" name="finishProject"></auth:button>
			<auth:button value="��Ŀ�ƻ���" onClick="return(0)" id="projectPlan" name="projectPlan"></auth:button>
			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


