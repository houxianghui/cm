<%@ include file = "/includes/common.jsp" %> 
<jsp:useBean id="expensesInfoForm" scope="request" class="com.blue.expenses.ExpensesInfoForm"/>
<%@ page language="java" pageEncoding="gb18030"%>
<%@ page contentType="text/html; charset=GB18030"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />  
<title>增加费用信息</title> 
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT> 
<script type="text/javascript" src="js/pfee/efee.js"></script>
<script type="text/javascript" src="js/Map.js"></script>
<script type="text/javascript">
var map = new Map();
<%
java.util.HashMap tmap  = com.eis.cache.SingleDicMap.getDicMap("7001");
for (Iterator<String> keys = tmap.keySet().iterator(); keys.hasNext();) {
	   String key = (String) keys.next();
	   String value = (String) ((HashMap)tmap.get(key)).get("ITEM_VAL");
	   out.print("map.put('"+key+"','"+value+"');");
	} 
%>
function tranFormatter(cellvalue,options,rowObject){
	if(map.get(cellvalue)){
		return map.get(cellvalue);
	}else{
		return cellvalue;
	}
}
var result="";
for ( var int = 0; int < map.size(); int++) {
	result+=map.element(int).key+":";
	result+=map.element(int).value+";";
}
result=result.substring(0, result.length-1);
function doPopOrg() {
	openWin("ProjectDistribute.do?act=gmpPop&id_field=projectNo&name_field=projectName","project_dis");
}
function doAdd(){ 
	//增加 
	
	//执行校验 
	var field = new Array("projectNo","dept"); 
	var info = new Array("项目","部门"); 
 
	//检察输入信息是否为空 
	
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	document.forms[0].submit(); 
}
</script>
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="ExpensesInfo.do"> 
<input type=hidden name=act value=add> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=results /> 
<html:hidden property="expensesId" /> 
<%=ViewUtil.getTitle("费用报销单")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr bgcolor="#FFFFFF"> 
          <td width="15%" align="right" class="dtPanel_Left">&nbsp;部门：</td> 
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="dept"> 
         	<html:optionsCollection name="expensesInfoForm" property="depart_f_options" />               
            </html:select>                
         </td> 
          <td width="15%" align="right" class="dtPanel_Left">&nbsp;项目：</td> 
          <td align="left" class="dtPanel_Main2">&nbsp; 
 		  <html:text property="projectNo" readonly="true"  onclick="doPopOrg();"/>               
          <html:text property="projectName" styleClass="Textfield" size="20" maxlength="40" readonly="true" onclick="doPopOrg();"/>
         </td>
       </tr>
  </table> 
<div> 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="add" id="saveE" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="window.location.href='ExpensesInfo.do?act=list'">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
<table width="98%" align=center>
<tr>
			<td width="44%" align="center">
				<table id="selectedStaff" style="{align:right}"></table>
				<div id="selectedPager"></div>
			</td>
			<td width="44%" align="center">
				<table id="notselectedStaff" style="{align:right}"></table>
				<div id="notselectedPager"></div>
			</td>
		</tr>
		<tr>
			<td align="center">费用
				<input type="button" id="add" value="增加">&nbsp;<input type="button" id="remove" value="删除">&nbsp;
			</td>
			<td align="center">出租车
				<input type="button" id="notadd" value="增加">&nbsp;<input type="button" id="notremove" value="删除">&nbsp;
			</td>
		</tr>
	</table> 
<script type="text/javascript" src="js/calendar.js"></script>
</body> 
</html> 
 

