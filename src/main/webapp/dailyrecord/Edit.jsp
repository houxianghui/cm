<%@page import="com.blue.enums.ProjectType"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.dailyrecord.DailyRecordAction"%>
<%@page import="com.blue.project.ProjectList"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>��ʱ��д</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly",true).click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if(!$("input[name='workDate']").val() || $.trim($("input[name='workDate']").val())==""){
			alert('��ѡ�������');
			return;
		}
		if(!$("input[name='taskCost']").val() || $.trim($("input[name='taskCost']").val())==""){
			alert('����д��ʱ');
			return;
		}
		if(!$(":input[name='workMemo']").val() || $.trim($(":input[name='workMemo']").val())==""){
			alert('����д��������');
			return;
		}
		document.forms[0].submit();
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="DailyRecord.do"> 
<html:hidden property="act"/>
<html:hidden property="recordId"/>
<html:hidden property="id"/>
<html:hidden property="step"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("��Ŀ��Ϣ")%> 
	<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
	<%ProjectList pl = (ProjectList)request.getAttribute(DailyRecordAction.PROjECT_KEY); %>
		<tr class="dtPanel_Top01">
			<td>��Ŀ���</td>
			<td>��Ŀ����</td>
			<td>�漰��ϵͳ</td>
			<td>��������</td>
			<td>��Ŀ�����׶�</td>
			<td>��Ŀ����</td>
			<td>���ȼ�</td>
			<td>��Ŀ����</td>
			<td>������</td>
			<td>��������</td>
		</tr>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=pl.getProjectId() %></td>
			<td><%=pl.getProjectName() %></td>
			<td><%=pl.getSubSys() %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_OWNING, pl.getOwning()) %></td>
			<td><%=Steps.valueOf(pl.getStep()).getDesc() %></td>
			<td><%=ProjectType.valueOf(pl.getProjectClass()).getDesc() %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PRIORITY, pl.getPriority().toString()) %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,pl.getProjectManager()) %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,pl.getRequireManager()) %></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,pl.getTechManager()) %></td>
		</tr>
	</table>
<%=ViewUtil.getTitle("��ʱ��д")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ����ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workDate" /> 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ʱ(Сʱ)��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="taskCost" onblur="onlyNum(this)" onkeydown="onlyNum(this)"/> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������ݣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="workMemo" /> 
         </td>
       </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������⣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="workIssue" /> 
         </td>
       </tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="save" value="����"/>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

