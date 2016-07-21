<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>模块程序版本配置</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	if(!confirm('请确认填写信息,确定执行本次操作吗？')) { 
		return; 
	} 
 
	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Moduleconf.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("增加模块程序版本")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         

         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>模块程序名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="moduleName" styleClass="Textfield"  size="50" maxlength="100"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>模块程序版本：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="moduleVersion" styleClass="Textfield"  size="50" maxlength="50"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>应用行业：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="applyHy" styleClass="Textfield"  size="50" maxlength="50"  />
  		  </td> 
       </tr>       
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>适用硬件型号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="fitHardId" styleClass="Textfield"  size="50" maxlength="100"  />
  		  </td> 
       </tr>     
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>程序作者：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="owner" styleClass="Textfield"  size="30" maxlength="30"  />
  		  </td> 
       </tr>         
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>发布日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
				<html:text property="issueDate" styleClass="Textfield"  size="8" maxlength="8"  />
  		  </td> 
       </tr>       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;备注：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        		<html:textarea property="remarks" cols="50" rows="1" styleClass="Textfield" value=""/>
         </td> 
       </tr> 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

