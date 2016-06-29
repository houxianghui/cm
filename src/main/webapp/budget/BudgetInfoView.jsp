<%@ include file="/includes/common.jsp"%>
<%@ page language="java" pageEncoding="gb18030"%>
<%@ page contentType="text/html; charset=GB18030"%>
<jsp:useBean id="budgetInfoForm" scope="request" class="com.blue.budget.BudgetInfoForm" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />  
<title>预算维护</title>
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT> 
<script type="text/javascript" src="js/pfee/fee.js">
</script>
<script type="text/javascript" src="js/Map.js"></script>
<script language="javascript">
	function doQuery(){
		var field = new Array("budgetKind","feeKind");
		var info = new Array("预算类别","费用类别");

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
		var depart = $(":input[name='depart']").val();
		var projectNo = $("input[name='projectNo']").val();
		var budgetKind = $(":input[name='budgetKind']").val();
		if (isEmpty(depart)){
			alert('请输入部门');
			return;
		}
		if(budgetKind==0){
			if (isEmpty(projectNo)){
				alert('请输入项目号');
				return;
			}
		}
		reload();
	}
	function disableNextfield(){
		var value = document.forms[0].budgetKind.value;
		if(value==0){
			document.forms[0].projectNo.disabled=false;
			document.forms[0].projectName.disabled=false;
		}else{
			document.forms[0].depart.disabled=false;
			document.forms[0].projectNo.disabled=true;
			document.forms[0].projectName.disabled=true;
		}
	}
	function doSave() {
		var field = new Array("depart");
		var info = new Array("阶段");

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

	
</script>
</head>
<body>
	<SCRIPT type="text/javascript" src="js/move.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<table width="98%" align=center>
		<tr>
			<td colspan="3"><html:form action="BudgetInfo.do">
					<table align="center" width="100%" class="dtPanel_Line3" border="0"
						cellspacing="1" cellpadding="0">
						
								<tr bgcolor="#FFFFFF"> 
          <td align="right" class="dtPanel_Left">&nbsp;预算类别：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="budgetKind" onchange="disableNextfield();"> 
         	<html:optionsCollection name="budgetInfoForm" property="budget_kind_options" />               
            </html:select>               
         </td>  
          <td align="right" class="dtPanel_Left">&nbsp;预算申请部门：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="depart"> 
         	<html:optionsCollection name="budgetInfoForm" property="depart_f_options" />               
            </html:select>               
         </td> 
          <td align="right" class="dtPanel_Left">&nbsp;申请项目：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectNo" styleClass="Textfield" size="20" maxlength="20"  disabled="true" onblur="getProName(this);"/> 
         <html:text property="projectName" styleClass="Textfield" size="20" maxlength="20"  disabled="true" readonly="true"/> 
         </td> 
          <td align="right" class="dtPanel_Left">&nbsp;费用类别：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="feeKind"> 
         	<html:optionsCollection name="budgetInfoForm" property="fee_kind_options" />               
            </html:select>               
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
		<tr>
			<td align="center">
				<input type="button" id="pr" value="打印" onclick="this.style.display='none';window.print();">
			</td>
		</tr>
	</table>
	
</body>
</html>


