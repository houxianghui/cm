<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="travelInfoForm" scope="request" class="com.blue.travel.TravelInfoForm"/>
<html> 
 <head>
<title>增加差旅费信息</title> 
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT> 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/Map.js"></script>
<script language="javascript">

$(function(){
	
	$("input[name*=date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	})
}) 
var map = new Map();
<%
java.util.HashMap tmap  = com.eis.cache.SingleDicMap.getDicMap("7003");
for (Iterator<String> keys = tmap.keySet().iterator(); keys.hasNext();) {
	   String key = (String) keys.next();
	   String value = (String) ((HashMap)tmap.get(key)).get("ITEM_VAL");
	   out.print("map.put('"+key+"','"+value+"');");
	} 
%>
var map7004 = new Map();
<%
java.util.HashMap tmap7004  = com.eis.cache.SingleDicMap.getDicMap("7004");
for (Iterator<String> keys = tmap7004.keySet().iterator(); keys.hasNext();) {
	   String key = (String) keys.next();
	   String value = (String) ((HashMap)tmap7004.get(key)).get("ITEM_VAL");
	   out.print("map7004.put('"+key+"','"+value+"');");
	} 
%>
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
function tranFormatter(cellvalue,options,rowObject){
	if(map.get(cellvalue)){
		return map.get(cellvalue);
	}else{
		return cellvalue;
	}
}
function map7004Formatter(cellvalue,options,rowObject){
	if(map7004.get(cellvalue)){
		return map7004.get(cellvalue);
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

var result7004="";
for ( var int = 0; int < map7004.size(); int++) {
	result7004+=map7004.element(int).key+":";
	result7004+=map7004.element(int).value+";";
}
result7004=result7004.substring(0, result7004.length-1);

</script> 
<script type="text/javascript" src="js/pfee/tfee.js"></script>
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="TravelInfo.do"> 
<input type=hidden name=act value=add> 
<input type=hidden name=step value="commit"> 
<html:hidden property="expensesId" /> 
<%=ViewUtil.getTitle("差旅费报销单")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 


 
  </table> 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="window.location.href='TravelInfo.do?act=list'">   
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
			<td align="center">差旅费
				<input type="button" id="add" value="增加">&nbsp;<input type="button" id="remove" value="删除">&nbsp;
			</td>
			<td align="center">出租车
				<input type="button" id="notadd" value="增加">&nbsp;<input type="button" id="notremove" value="删除">&nbsp;
			</td>
		</tr>
	</table>  
</body> 
</html> 
 

