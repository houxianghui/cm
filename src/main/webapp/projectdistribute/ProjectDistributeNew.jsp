
<%@page import="com.blue.mile.MileStone"%>
<%@page import="com.projectmaintain.ProjectDistributeVO"%>
<%@ include file="/includes/common.jsp"%>
<%@ page language="java" pageEncoding="gb18030"%>
<%@ page contentType="text/html; charset=GB18030"%>
<jsp:useBean id="projectDistributeForm" scope="request"	class="com.projectmaintain.ProjectDistributeForm" />
<jsp:useBean id="projectMaintainForm" scope="request"	class="com.projectmaintain.ProjectMaintainForm" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />  
<title>项目维护</title>
<script type="text/javascript" src="js/project/projectDistribute.js">
</script>
<script type="text/javascript" src="js/Map.js"></script>
<script language="javascript">
	var map = new Map();
	<%
	List<MileStone> l = (List)request.getAttribute("miles");
	if(l != null){
		for(MileStone m :l){
			out.print("map.put('"+m.getMileStoneId()+"','"+m.getStoneName()+"');");
		}
	}%>
	function doSave() {
		var field = new Array("status", "startDate", "endDate");
		var info = new Array("阶段", "开始时间", "结束时间");

		//检察输入信息是否为空 
		var tmp;
		for ( var i = 0; i < field.length; i++) {
			tmp = "document.forms[0]." + field[i] + ".value";
			if (isEmpty(eval(tmp))) {
				alert('请输入' + info[i]);
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
	function mileStoneFormatter(cellvalue,options,rowObject){
		if(map.get(cellvalue)){
			return map.get(cellvalue);
		}else{
			return cellvalue;
		}
	}
	
</script>
</head>
<body>
	<SCRIPT type="text/javascript" src="js/move.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>

	<table width="98%" align=center>
		<tr>
			<td colspan="3"><html:form action="ProjectDistribute.do">
				<html:hidden property="projectId"/>
					<table align="center" width="100%" class="dtPanel_Line3" border="0"
						cellspacing="1" cellpadding="0">
						<tr>
							<td width="35%" align="right" class="dtPanel_Top01">&nbsp;
								项目阶段：</td>
							<td align="left" class="dtPanel_Top01">&nbsp; <html:select
									property="step" styleClass="Select">
									<html:optionsCollection name="projectMaintainForm"
										property="stepCollection" />
								</html:select>
							</td>
						</tr>
					</table>
				</html:form></td>
		</tr>
		<tr>
			<td width="20%" align="center">
				<table id="notSelectedStaff"></table>
				<div id="pager"></div>
			</td>
			<td align="center">
				<input type="button" id="add" value="增加>>"><br>&nbsp;<br>
				<input type="button" id="remove" value="<<释放"><br>&nbsp;<br>
				<input type="button" id="return" value="<返回>">
			</td>
			<td width="68%" align="center">
				<table id="selectedStaff" style="{align:right}"></table>
				<div id="selectedPager"></div>
			</td>
		</tr>
	</table>
	
</body>
</html>


