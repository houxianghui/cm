<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.enums.Status"%>
<%@page import="com.blue.project.ProjectList"%>
<%@page import="com.blue.pm.mainplanjob.MainPlanJob"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>������ҵ���Ŀ</title>

<script language="javascript"> 
$(function(){
	$(":button").attr("class","Button");
});
function doAdd(){
	window.location.href="MainPlanJob.do?act=new";
}
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.getElementById('project').act.value='edit';	 
	document.getElementById('project').submit(); 
} 
function doPlan(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value;
} 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.getElementById('project').projectId.value == null ||document.getElementById('project').projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.getElementById('project').act.value='delete'; 
	document.getElementById('project').submit(); 
} 

 
function doQuery() {  
	document.getElementById('project').act.value = "<%=request.getParameter("act") %>";
	document.getElementById('project').submit(); 
} 
 
function setPKey(id_var) { 
	document.getElementById('project').projectId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.getElementById('project').act.value = "<%=request.getParameter("act") %>";  
   	document.getElementById('project').pageNO.value = pagenm;     
   	document.getElementById('project').submit(); 
} 

</script>
</head>
<body>
<html:form method="post" action="MainPlanJob.do" styleId="project">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("���ƻ�����")%>
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
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>')">
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
			<input type="Button" value="���ƻ������" onclick="doEdit()">
			<input type="Button" value="�������ƻ�" onclick="doPlan()">
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>