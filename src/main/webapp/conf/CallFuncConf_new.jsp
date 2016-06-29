<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>增加调用函数配置</title> 
 
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
 
<html:form method="post" action="Callfuncconf.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("增加调用函数配置")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         

         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>业务类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="operationType" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="operationTypecollection"/>
			</html:select>
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>厂商：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="manufacIdCollection"/>
			</html:select>
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>产品类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="prodIdcollection"/>
			</html:select>
  		  </td> 
       </tr>       
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>应用类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="applyTypeId" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="appTypeIdcollection"/>
			</html:select>
  		  </td> 
       </tr>     
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>调用函数1：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="func1" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="funcId1collection"/>
			</html:select>
  		  </td> 
       </tr>         
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;调用函数2：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="func2" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="funcId2collection"/>
			</html:select>
  		  </td> 
       </tr>  
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;调用函数3：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="func3" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="funcId3collection"/>
			</html:select>
  		  </td> 
       </tr>    
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;调用函数4：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:select property="func4" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="funcId4collection"/>
			</html:select>
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
 

