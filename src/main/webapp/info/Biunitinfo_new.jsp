<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>申请单位信息配置</title> 
 
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
 
<html:form method="post" action="Biunitinfo.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("增加申请单位信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>单位代码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitid" styleClass="Textfield"  size="9" maxlength="9"  />
  		  </td> 
       </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>行业编码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="hyid" styleClass="Textfield"  size="4" maxlength="4"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>行业名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="hyName" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>单位类型：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unittype" styleClass="Textfield"  size="2" maxlength="2"  />
  		  </td> 
       </tr>       
        </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>单位中文全称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitdes" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>单位中文简称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="chnshort" styleClass="Textfield"  size="16" maxlength="16"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>联系人：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitperson" styleClass="Textfield"  size="20" maxlength="20"  />
  		  </td> 
       </tr>       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>电话：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unittel" styleClass="Textfield"  size="20" maxlength="20"  />
  		  </td> 
       </tr>       
        </tr> 
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>地址：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unitaddr" styleClass="Textfield"  size="20" maxlength="40"  />
  		  </td> 
       </tr> 
          <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>测试标记：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="unittestflag" styleClass="Textfield"  size="1" maxlength="1"  />
  		  </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>上级机构：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<html:text property="leadStore" styleClass="Textfield"  size="9" maxlength="9"  />
  		  </td> 
       </tr>            
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>单位等级：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
			<%=SingleDicMap.getRadio("unitlevel", SingleDic.UNIT_LEVEL, "A")%>
  		  </td> 
       </tr>     
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;备注：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        		<html:textarea property="rsvd" cols="50" rows="1" styleClass="Textfield" value=""/>
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
 

