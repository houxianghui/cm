<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=gb18030"%> 
<html> 
 <head>
<title>�ļ��ϴ�</title> 
<script type="text/javascript" src="js/adjust.js">
</script> 
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProjectFiles.do" enctype="multipart/form-data"> 
<input type=hidden name=act value="add">
<html:hidden property="projectId"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("�ļ��ϴ�")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ѡ���ļ���</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:file property="file" styleClass="Textfield"></html:file>
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="memo" styleClass="Textfield" cols="40" rows="10"></html:textarea>
         </td>
       </tr> 
	</table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:submit styleClass="Button" value="����"/>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

