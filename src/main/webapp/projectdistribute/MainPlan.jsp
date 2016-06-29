<%@page import="com.blue.scale.ScaleCache"%>
<%@page import="com.blue.scale.Step"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.product.ModuleDef"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<head>
<title>���ƻ�����</title>
<script type="text/javascript" src="js/project/mainPlan.js"></script>
<script type="text/javascript" src="js/Map.js"></script>
<script language="javascript">  
	var map = new Map();
	var stepMap = new Map();
	var actionMap = new Map();
	
	<%
	Steps[] steps = Steps.values();
	for(Steps s:steps){
		out.print("stepMap.put('"+s.toString()+"','"+s.getDesc()+"');");
	}
	Map<Integer,com.blue.scale.Action> actionMap = ScaleCache.getActionMap();
	for(Integer i:actionMap.keySet()){
		out.print("actionMap.put('"+i+"','"+actionMap.get(i).getActionDef().getName()+"');");
	}
	
	List<ModuleDef> l = (List)request.getAttribute("modules");
	if(l != null){
		for(ModuleDef m :l){
			out.print("map.put('"+m.getModuleId()+"','"+m.getName()+"');");
		}
	}%>
	function moduleFormatter(cellvalue,options,rowObject){
		if(map.get(cellvalue)){
			return map.get(cellvalue);
		}else{
			return cellvalue;
		}
	}
	
</script>

<style type="text/css">
table {
    border-collapse:collapse;
    border-spacing:0;
    border:1px solid #0065B3;
}
table td {
    border-color:#0065B3;
    padding:10px 0;
}
</style>
</head>
<body>
<div id="return">����</div>  
<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="mainPlan">
<html:hidden property="projectId"/>
<input type="hidden" name="actionId"/>
<input type="hidden" name="step"/>
<table width="98%" class="dtPanel_Line1" border="1" cellspacing="1"
		align="center" cellpadding="0"  >
		<tr align="center" class="dtPanel_Top01" >
			<td>�׶�</td>
			<td>�</td>
			<td>ִ��˵��</td>
			<td nowrap>������Ʒ�ύ����</td>	
			<td nowrap>������Ա</td>
			<td>����</td>	
			<td >�����ɹ�</td>	
			<td>����ʽ</td>	
		</tr>
<%out.print(request.getAttribute("scale")) ;%>
</table>
</html:form>
<div id="dialog-form" title="�������">
  <form>
	<table width="98%" align=center>
		<tr>
			<td width="20%" align="center">
				<table id="notSelectedStaff"></table>
				<div id="pager"></div>
			</td>
			<td align="center">
				<input type="button" id="add" value="����>>"><br>&nbsp;<br>
				<input type="button" id="remove" value="<<�ͷ�"><br>&nbsp;<br>
				<input type="button" id="return" value="<����>">
			</td>
			<td width="68%" align="center">
				<table id="selectedStaff" style="{align:right}"></table>
				<div id="selectedPager"></div>
			</td>
		</tr>
	</table>
  </form>
</div>

<p>&nbsp;</p>
</body>
</html>


