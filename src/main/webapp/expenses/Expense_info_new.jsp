<%@ include file = "/includes/common.jsp" %> 
<jsp:useBean id="expensesInfoForm" scope="request" class="com.blue.expenses.ExpensesInfoForm"/>
<%@ page language="java" pageEncoding="gb18030"%>
<%@ page contentType="text/html; charset=GB18030"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />  
<title>���ӷ�����Ϣ</title> 
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
	//���� 
	
	//ִ��У�� 
	var field = new Array("projectNo","dept"); 
	var info = new Array("��Ŀ","����"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
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
<%=ViewUtil.getTitle("���ñ�����")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr bgcolor="#FFFFFF"> 
          <td width="15%" align="right" class="dtPanel_Left">&nbsp;���ţ�</td> 
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="dept"> 
         	<html:optionsCollection name="expensesInfoForm" property="depart_f_options" />               
            </html:select>                
         </td> 
          <td width="15%" align="right" class="dtPanel_Left">&nbsp;��Ŀ��</td> 
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
						<input name="add" id="saveE" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="window.location.href='ExpensesInfo.do?act=list'">   
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
			<td align="center">����
				<input type="button" id="add" value="����">&nbsp;<input type="button" id="remove" value="ɾ��">&nbsp;
			</td>
			<td align="center">���⳵
				<input type="button" id="notadd" value="����">&nbsp;<input type="button" id="notremove" value="ɾ��">&nbsp;
			</td>
		</tr>
	</table> 
<script type="text/javascript" src="js/calendar.js"></script>
</body> 
</html> 
 

