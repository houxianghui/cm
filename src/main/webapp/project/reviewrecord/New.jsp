<%@page import="com.blue.project.reviewrecord.ReviewRecordForm"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<<jsp:useBean id="reviewRecordForm" scope="request" class="com.blue.project.reviewrecord.ReviewRecordForm"></jsp:useBean>
<html> 
 <head>
<title>评审维护</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript" src="js/project/findProjects.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if($("input[name='act']").val() == 'init'){
			document.forms[0].act.value="add";
			document.forms[0].submit();
		}else{
			document.forms[0].act.value="update";
			document.forms[0].submit();
		}
	}).attr("class","Button");
});
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ReviewRecord.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="id"/>
<html:hidden property="projectId"/>
<%=ViewUtil.getTitle("评审维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <%if(CheckUtil.isEmptry(reviewRecordForm.getProjectId())) {%>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 项目编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         
         <input type="text" name="projectName" /> 
         </td>
       </tr> 
       
       <%}else{ %>
       	<input type="hidden" name="projectName"/>
       <%} %>
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 标题：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="title" /> 
         </td>
       </tr> 
		
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;内容：</td>
			<td align="left" class="dtPanel_Main2">&nbsp;
				 <html:textarea property="content" styleClass="Textarea" cols="40" rows="4"></html:textarea>
			</td>
		</tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="save" value="保存"/>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

