<%@page import="com.blue.project.ProjectList"%>
<%@ include file="/includes/common.jsp"%>
<%@ page language="java" pageEncoding="gb18030"%>
<%@ page contentType="text/html; charset=GB18030"%>
<jsp:useBean id="budgetInfoForm" scope="request" class="com.blue.budget.BudgetInfoForm" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />  
<title>Ԥ��ά��</title>
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT> 
<script type="text/javascript" src="js/Map.js"></script>
<script language="javascript">
	var map7001 = new Map();
	<%
	java.util.HashMap tmap  = com.eis.cache.SingleDicMap.getDicMap("7001");
	for (Iterator<String> keys = tmap.keySet().iterator(); keys.hasNext();) {
		   String key = (String) keys.next();
		   String value = (String) ((HashMap)tmap.get(key)).get("ITEM_VAL");
		   out.print("map7001.put('"+key+"','"+value+"');");
		} 
	%>
	var map = new Map();
	<%
	List<ProjectList> l = (List)request.getAttribute("projectList");
	if(l != null){
		for(ProjectList m :l){
			out.print("map.put('"+m.getProjectId()+"','"+m.getProjectName()+"');");
		}
	}%>
	function doQuery(){
		var field = new Array("year","budgetKind","feeKind");
		var info = new Array("���","Ԥ�����","�������");

		//���������Ϣ�Ƿ�Ϊ�� 
		var tmp;
		for ( var i = 0; i < field.length; i++) {
			tmp = "document.forms[0]." + field[i] + ".value";
			if (isEmpty(eval(tmp))) {
				alert('������' + info[i]);
				eval("document.forms[0]." + field[i] + ".focus()");
				return;
			}
		}
		var depart = $(":input[name='depart']").val();
		if (isEmpty(depart)){
			alert('�����벿��');
			return;
		}
		reload();
	}
	function doSave() {
		var field = new Array("depart");
		var info = new Array("����");

		//���������Ϣ�Ƿ�Ϊ�� 
		var tmp;
		for ( var i = 0; i < field.length; i++) {
			tmp = "document.forms[0]." + field[i] + ".value";
			if (isEmpty(eval(tmp))) {
				alert('������' + info[i]);
				eval("document.forms[0]." + field[i] + ".focus()");
				return;
			}
		}
		var rolesRight = document.forms[0].rolesRight;
		for ( var i = 0; i < rolesRight.length; i++) {
			rolesRight.options[i].selected = true;
		}
		document.forms[0].submit();

	}
	function projectFormatter(cellvalue,options,rowObject){
		if(map.get(cellvalue)){
			return map.get(cellvalue);
		}else{
			return cellvalue;
		}
	}
	function map7001Formatter(cellvalue,options,rowObject){
		if(map7001.get(cellvalue)){
			return map7001.get(cellvalue);
		}else{
			return cellvalue;
		}
	}
	var result="";
	for ( var int = 0; int < map7001.size(); int++) {
		result+=map7001.element(int).key+":";
		result+=map7001.element(int).value+";";
	}
	result=result.substring(0, result.length-1);
	
</script>
</head>
<body>
<script type="text/javascript" src="js/pfee/fee.js"></script>
	<SCRIPT type="text/javascript" src="js/move.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<table width="98%" align=center>
<!--startprint1-->  
		<tr>
			<td colspan="3"><html:form action="BudgetInfo.do">
					<table align="center" width="100%" class="dtPanel_Line3" border="0"
						cellspacing="1" cellpadding="0">
						
								<tr bgcolor="#FFFFFF">
		<td align="right" class="dtPanel_Left">&nbsp;��ݣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="year" styleClass="Textfield" size="4" maxlength="4"/> 
         </td>  
          <td align="right" class="dtPanel_Left">&nbsp;Ԥ�����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="budgetKind"> 
         	<html:optionsCollection name="budgetInfoForm" property="budget_kind_options" />               
            </html:select>               
         </td>  
          <td align="right" class="dtPanel_Left">&nbsp;Ԥ�����벿�ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="depart"> 
         	<html:optionsCollection name="budgetInfoForm" property="depart_f_options" />               
            </html:select>               
         </td> 
          <td align="right" class="dtPanel_Left">&nbsp;�������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="feeKind"> 
         	<html:optionsCollection name="budgetInfoForm" property="fee_kind_options" />               
            </html:select>               
         </td>
         <td align="left" class="dtPanel_Main2">&nbsp;
         <input type="button" id="query" value="��ѯ" onclick="doQuery();"> 
         </td>
       </tr>
					</table>
				</html:form></td>
		</tr>
		<tr>
			<td width="88%" align="center">
				<table id="selectedStaff" style="{align:right}"></table>
				<div id="selectedPager"></div>
			</td>
		</tr>
<!--endprint1--> 
		<tr>
			<td align="center">
				<input type="button" id="add" value="����">&nbsp;<input type="button" id="remove" value="ɾ��">&nbsp;<input type="button" id="pr" value="��ӡ" onClick="preview(1);">&nbsp;<input type="button" id="return" value="����">
			</td>
		</tr>
	</table>
	
</body>
</html>


