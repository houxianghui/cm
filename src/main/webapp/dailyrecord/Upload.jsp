<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=gb18030"%> 
<html> 
 <head>
<title>文件上传</title> 
<script type="text/javascript" src="js/adjust.js">
</script> 
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="DailyRecord.do" enctype="multipart/form-data"> 
<input type=hidden name=act value="upload">
<%=ViewUtil.getTitle("文件上传")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 选择文件：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:file property="file" styleClass="Textfield"></html:file>
         </td>
       </tr> 
	</table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:submit styleClass="Button" value="保存"/>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

