<%@ include file = "/includes/common.jsp" %>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.huateng.creditcard.apply.attachcard.AttachCardVO" %>
<%@ page import="com.huateng.creditcard.apply.maincard.CardReqForm" %>
<%@ page import="com.eis.util.CheckUtil"%>
 

<jsp:useBean id="pageResultAttach" scope="request"
	class="com.eis.base.PageObject" />
<jsp:useBean id="cardReqForm" scope="request"
	class="com.huateng.creditcard.apply.maincard.CardReqForm" />
<html>
<head>
<title>修改申请资料</title>
<SCRIPT src="js/apply/getExpandManagerInfo.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/apply/attachcardApply.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/apply/referenceinfo.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT>
<script language="javascript"> 
function doManagerPop(){
	openWin("Ep_expand_manager_info.do?act=pop&manager_name=managerName&seq_no=managerId","managerPop");
}
function doPopCredit(){

	openWin("GetCredit.do?act=c&app_no=<%=cardReqForm.getApp_no()%>","getCredit");
}
function doSave(){ 

	//修改 
	if(document.forms[0].managerId.value.length>0 && trim(document.forms[0].managerId.value).length!=10){
		alert("营销人编号应该为10位数字");
		document.forms[0].managerId.focus();
		return;
	}

	//检验QQ卡附卡
	var lmmark = '<%=cardReqForm.getCard_single_code()%>';
	if(lmmark =='H2'&&(document.forms[0].per_layout_flag[1].checked)){
		alert("QQ卡附卡不允许发小卡，请修改为大卡");
		return;
	}
	
	 //根据附卡申请人姓名是否为空,来确定是否要同批申请一张附卡
	//若不为空,还需要校验附卡资料
	var per_card_cname = document.forms[0].per_card_cname.value; 
	if(!isEmpty(per_card_cname)){
		//校验附卡资料
		if(!checkForm()){
			return;
		}			
		
		if(!checkFormAttach()){
 			return;
 		}
 		
 		var rela_lm = getSelectedButton(document.forms[0].relation_bet);//与联系人关系
 		var rela_percard = getSelectedButton(document.forms[0].per_rela);//与附卡人关系
 		//联系人与附卡人不同时,申请人不得同时与两人关系为配偶
 		if(per_card_cname != document.forms[0].lm_name.value){
 			if(rela_lm == 0 && rela_percard == 0){
 				alert("申请人与联系人或附卡申请人关系错误");
 				document.forms[0].per_rela[0].focus();
 				return;
 			}		
 		}
 		//申请人未婚，不得与附属卡申请人关系为配偶
 		if(getSelectedButton(document.forms[0].marr_stat) == 0 && rela_percard == 0){
 			alert("申请人与联系人或附卡申请人关系错误");
 			document.forms[0].per_rela[0].focus();
 			return;
 		}
 		//同性关系不得为配偶
 		if(getSelectedButton(document.forms[0].cust_sex) == getSelectedButton(document.forms[0].per_sex) && rela_percard == 0){
 			alert("申请人与联系人或附卡申请人关系错误");
 			document.forms[0].per_rela[0].focus();
 			return;
 		}
 		document.forms[0].act.value='u';	 	
		document.forms[0].submit(); 
 		return;
 			 	
	}
	
 	if(checkForm()){
 		document.forms[0].act.value='u';	 
 		document.forms[0].submit(); 
 	}
 
}
<%String success = (String)request.getAttribute("success");
if (null != success && success.equals("y")) {%> 
		alert("数据修改成功！"); 
		<%}
request.setAttribute("success", "n");
%> 
function doSend(){ //提交复核
	if(document.forms[0].managerId.value.length>0 && trim(document.forms[0].managerId.value).length!=10){
		alert("营销人编号应该为10位数字");
		document.forms[0].managerId.focus();
		return;
	}

	//检验QQ卡附卡
	var lmmark = '<%=cardReqForm.getCard_single_code()%>';
	if(lmmark =='H2'&&(document.forms[0].per_layout_flag[1].checked)){
		alert("QQ卡附卡不允许发小卡，请修改为大卡");
		return;
	}
	
	//根据附卡申请人姓名是否为空,来确定是否要同批申请一张附卡
	//若不为空,还需要校验附卡资料
	var per_card_cname = document.forms[0].per_card_cname.value; 
	if(!isEmpty(per_card_cname)){
		//校验附卡资料
		if(!checkForm()){
			return;
		}			
		
		if(!checkFormAttach()){
 			return;
 		}
 			
 		var flag = document.forms[0].feed_back_flag.value;
		if(flag == 1){		
			if(isEmpty(document.forms[0].feedback_sug.value)){
				alert("请填写反馈意见");
				document.forms[0].feedback_sug.focus();
				return;
			}	
			if(isEmpty(document.forms[0].feedback_exp.value)){
				alert("请填写反馈说明");
				document.forms[0].feedback_exp.focus();
				return;
			}
			if(document.forms[0].feedback_exp.value.length > 100){
				alert("反馈说明限制100个字");
				document.forms[0].feedback_exp.focus();
				return;
			}
		}
			
		document.forms[0].act.value='input_send';	 
		document.forms[0].submit(); 
 		return; 	
	}	
	
	var flag = document.forms[0].feed_back_flag.value;
	if(flag == 1){
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("请填写反馈意见");
			document.forms[0].feedback_sug.focus();
			return;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("请填写反馈说明");
			document.forms[0].feedback_exp.focus();
			return;
		}		
	}

	//主卡申请的其他逻辑检查
 	if(checkForm()){
 		document.forms[0].act.value='input_send';	 
		document.forms[0].submit(); 
 	}

} 

//附卡管理所用的校验
function doAdd(){ 
	//增加 

	window.location="AttachCard.do?act=c&prod=<%=cardReqForm.getProd_kind()%>&prod=<%=cardReqForm.getProd_kind()%>";
} 
function feedbackError(){
	openWin("ErrorInfoFlag.do?act=list&app_no=<%=cardReqForm.getApp_no()%>","getErrorList");
} 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[1].app_no.value == null ||document.forms[1].app_no.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[1].act.value='u';
	var prod = "<%=cardReqForm.getProd_kind()%>";
	
	document.forms[1].prod.value=prod;	 
	document.forms[1].submit(); 
} 
 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[1].app_no.value == null ||document.forms[1].app_no.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[1].act.value='d'; 
	document.forms[1].submit(); 
} 
 
function doQuery() {  
	//检查输入查询条件 
	document.forms[1].act.value = "list"; 
	document.forms[1].requery.value='y'; 
	document.forms[1].submit(); 
} 
 
function setPKey(app_no_var,net_node_no_var,settlement_no_var,seq_no_var) { 
	document.forms[1].app_no.value=app_no_var; 
	document.forms[1].net_node_no.value=net_node_no_var; 
	document.forms[1].settlement_no.value=settlement_no_var; 
	document.forms[1].seq_no.value=seq_no_var; 
} 

function copyPost(){ //若户籍地址与现住址相同，则户籍地址邮编自动等于现住址邮编
	//若户籍地址为“同现住址”
	if(getSelectedButton(document.forms[0].pre_reg_addr) == 0){
		if(!isEmpty(document.forms[0].pre_post.value)){
			document.forms[0].reg_post.value = document.forms[0].pre_post.value;
		}
	}
} 
function doPopOrg() {
	openWin("OrgPop.do?act=applylist&id_field=exam_node_no&name_field=exam_node_name","org_pop");
} 
function doCityCode() {
	openWin("CityCode.do?act=list&id_field=pre_city_code&no_field=pre_zone_no","city_code");
}
function doCityCodeComp() {
	openWin("CityCode.do?act=list&id_field=comp_city_code&no_field=comp_zone_no","city_code");
}
function onlyNum(obj){
	if(obj.value!=obj.value.replace(/[^\u0030-\u0039]/g,''))
	obj.value=obj.value.replace(/[^\u0030-\u0039]/g,'');
}
function onlyNum1(obj1,obj2){
	if(obj1.value!=obj1.value.replace(/[^\u0030-\u0039]/g,''))
	obj1.value=obj1.value.replace(/[^\u0030-\u0039]/g,'');
	
	obj2.value = obj1.value;
}
function identical(obj1,obj2){
	obj2.value = obj1.value;
}
function doReferencePop(){
    openWin("Ep_reference_manager_info.do?act=pop&cardtype=0","referencePop");
}
function doReferencePop1(){
    openWin("Ep_reference_manager_info.do?act=pop&cardtype=1","referencePop");
}
function doPopBudget(){
	openWin("BudgetInfo.do?act=new&app_no=<%=cardReqForm.getApp_no()%>&cust_name=<%=cardReqForm.getCust_name()%>&cert_info=<%=cardReqForm.getCert_info()%>&org=<%=cardReqForm.getApp_cur()%>&exam_node_no=<%=cardReqForm.getExam_node_no()%>","bugetInfoPop");
}
function doPopServ(){
	openWin("CsServ.do?act=new&cs_app_no=<%=cardReqForm.getApp_no()%>&product_id=<%=cardReqForm.getProd_kind()%>&cust_name=<%=cardReqForm.getCust_name()%>&cert_info=<%=cardReqForm.getCert_info()%>","csServPop");
}
</script>
</head>
<BODY class="body_bg_grey1">
<% String prod_name = cardReqForm.getProd_kind();
   boolean prod_flag = false;
   if (com.eis.util.CheckUtil.isOfficeStyle(prod_name)) {
   	  prod_flag = true;
   }
%>
<html:form method="post" action="CardReq.do">
	<input type=hidden name=act value=u>
	<input type=hidden name=step value="commit">
	<input type=hidden name=prod_kind value="<%=cardReqForm.getProd_kind()%>">
	<input type=hidden name=prod value="<%=cardReqForm.getProd_kind()%>">
	<input type=hidden name=for_cur_type
		value="<%=cardReqForm.getFor_cur_type()%>">

	<html:hidden property="app_no" />
	<html:hidden property="net_node_no" />
	<html:hidden property="settlement_no" />
	<html:hidden property="card_no" />
	<html:hidden property="account" />
	<html:hidden property="task_type" />
	<!--F10203 20100730 modiy by lixq delete 
	<html:hidden property="employee" /> -->
	<!--是否是本行员工标志-->
	<html:hidden property="card_single_code" />
	<!--预设“联名卡标识码”-->
	<html:hidden property="prep1" />
	<!--v+产品编码-->
	<!--反馈意见标志位,决定是否显示反馈意见栏-->
	<html:hidden property="feed_back_flag" />

	<html:hidden property="currency" />
	<!--约定还款本外币置为本币-->

	<!--标志第一张附卡信息中，“与主卡关系栏”是配偶，而且列表中对应的第一条附卡关系也为配偶-->
	<!--注意：在下部的附卡列表中，有对应项-->
	<!--html:hidden property="couple_flag"/-->

	<%=ViewUtil.getTitle("您的申请卡种")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">申请卡品牌</td>
			<td colspan="2" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getDicItemVal("0010", cardReqForm.getCard_mark())%>
			<input type=hidden name="card_mark"
				value="<%=cardReqForm.getCard_mark()%>">

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 联名卡标识： <%=SingleDicMap.getDicItemVal("0014", cardReqForm.getCard_single_code())%>
		<%
			if(com.eis.util.CheckUtil.checkMemberAuto(cardReqForm.getProd_kind()) == 1){
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			会员编号：<html:text property="memberNumber" styleClass="Textfield" size="19" maxlength="19"  onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"></html:text>
		<%}%>
			</td>
		</tr>
		<%
			if(!cardReqForm.getLayout_options().isEmpty()){
		%>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		卡面标识
		</td> 
		<td colspan="2" class="dtPanel_Main2">&nbsp;
		<html:select styleClass="Select" property="layoutNo">	
	  	<html:optionsCollection name="cardReqForm" property="layout_options" />
    	</html:select>	
		</td>
		<%}%>
			<tr align="center" class="dtPanel_Top01">
		<td width="16%" align="left" class="dtPanel_Left">申请单位:</td>
		<td width="568" class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getListCheckBox((List)request.getAttribute("unitlist"), "unitId") %>
		</td>
	</tr>
		<%
			if(com.eis.util.CheckUtil.isLayout(cardReqForm.getProd_kind())){
			String radio = com.eis.util.CheckUtil.radioNumber(cardReqForm.getProd_kind());
		%>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		版面标志
		</td>
			<%
				if(cardReqForm.getProd_kind().equalsIgnoreCase("LYRGH2IC")){
			%>
			<td colspan="2" class="dtPanel_Main2">&nbsp;
			<input type=hidden name="layout_flag" value="1"><%=SingleDicMap.getDicItemVal(radio,"1")%>
			</td>
			<%}else{%>
			<td colspan="2" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("layout_flag",radio,cardReqForm.getLayout_flag())%>
			</td>
			<%}%>
		</tr>	
		<%}%>
		
		

		<tr>
			<td width="16%" align="left" class="dtPanel_Left">申请卡种</td>
			<td colspan="2" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getDicItemVal("0013", cardReqForm.getCard_kind())%>&nbsp;&nbsp;
			<input type=hidden name="card_kind" value="<%=cardReqForm.getCard_kind()%>"> 

			</td>
		</tr>						
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">申请币种</td>
			<td width="209" class="dtPanel_Main2">&nbsp;<%=OrgUtil.getNameByAppCode(cardReqForm.getApp_cur())%>
			<input type=hidden name="app_cur"
				value="<%=cardReqForm.getApp_cur()%>">
				&nbsp;卡种代码&nbsp;<%=cardReqForm.getDefine()%></td>
			<td width="349" class="dtPanel_Main2">&nbsp;
			&nbsp;<input type="button" name="zz" value="申请增值服务" class="Button" onclick="doPopServ();">
		</td>         
		</tr>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		是否有我行贷记卡
		</td>
		<td width="209" class="dtPanel_Main2">
		<%=SingleDicMap.getRadio("abccard","0000",cardReqForm.getAbccard())%>
		</td>           
		<td width="349" class="dtPanel_Main2">&nbsp;
		&nbsp;<input type="button" name="xe" value="申请专项商户分期额度" class="Button" onclick="doPopBudget();">
		</td>         
	</tr>		
	</table>


	<%=ViewUtil.getTitle("申请人基本资料")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">中文姓名</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
				property="cust_name" size="20" styleClass="Textfield" maxlength="40"
				onblur="onlyName(this)" onkeyup="onlyName(this)" /> &nbsp;国籍 <html:text
				property="nationality" size="3" maxlength="3" styleClass="Textfield"
				readonly="true" /> <SELECT onchange=mainCardNationality(this)
				name=select>
				<% if(prod_flag){ %>
					<SCRIPT language="javascript">document.writeln(country_code1);</SCRIPT>
				<% } else { %>
					<SCRIPT language="javascript">document.writeln(country_code);</SCRIPT>
				<%}%>
			</SELECT></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">性别</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp; <%=SingleDicMap.getRadio("cust_sex", "0024", cardReqForm.getCust_sex())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">拼音或英文姓名</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<htm:title
				fieldID="LAST_NAME" title="姓" /> <html:text property="last_name"
				styleClass="Textfield" size="10" maxlength="10"
				onblur="onlyEngToUpperCase(this)" onkeyup="onlyEngToUpperCase(this)" />
			<htm:title fieldID="FIRST_NAME" title="名" /> <html:text
				property="first_name" size="16" styleClass="Textfield"
				maxlength="16" onblur="onlyEngToUpperCase(this)"
				onkeyup="onlyEngToUpperCase(this)" /> &nbsp; 出生日期 <html:text
				property="birthday" styleClass="Textfield" size="8" maxlength="8"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> (yyyymmdd)</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">婚姻状况</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("marr_stat", "0025", cardReqForm.getMarr_stat())%></td>
		</tr>

		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="CERT_INFO" title="证件类型" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%if(prod_name.equals("LYRODOCC") || prod_name.equals("LYRGJOCC"))
		      {%>
		      <%=SingleDicMap.getRadio("cert_type", "0252", cardReqForm.getCert_type())%>
		     <%} else {%>
		     <%=SingleDicMap.getRadio("cert_type", "0026", cardReqForm.getCert_type())%>
		     <%}%>
			
			请列明 <html:text property="cert_name" styleClass="Textfield"
				maxlength="20" size="15" /></td>
		</tr>

		<tr>
			<td width="16%" align="left" class="dtPanel_Left">证件号码</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
				property="cert_no" styleClass="Textfield" maxlength="24" size="24" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">教育程度</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("edu_degr", "0027", cardReqForm.getEdu_degr())%></td>
		</tr>

		<% String produ_idd=cardReqForm.getProd_kind();
		if(produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC"))
		{%> 
			<input type="hidden" name="per_income" value="20000">
			
	    <%} else {%>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">收入状况</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;个人年收入 <html:text
				property="per_income" styleClass="Textfield" size="9" maxlength="9"
				onblur="checkDouble(this)" onkeyup="checkDouble(this)" />
			元人民币&nbsp;&nbsp;
			<%if(!prod_flag) {%>
			现住址已居住年限<html:text property="pre_years" size="2"
			styleClass="Textfield" maxlength="2" onblur="onlyNum(this);"
			onkeyup="onlyNum(this);" /> 年 &nbsp;&nbsp; &nbsp;&nbsp;	<%}%>
			</td>
		</tr>
		<%}%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">住宅状况</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("hou_stat", "0029", cardReqForm.getHou_stat())%><br>
			&nbsp;月还款 &nbsp;<html:text property="hou_mon_loan" maxlength="7"
				size="9" styleClass="Textfield" onblur="checkDouble(this)"
				onkeyup="checkDouble(this)" /> 元人民币</td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="COMP_NAME" title="单位全称" /></td>
			<td width="570" class="dtPanel_Main2">&nbsp;<html:text
				property="comp_name" styleClass="Textfield" maxlength="80" size="60" /></td>
		</tr>
		
		
		<%
		if(com.eis.util.CheckUtil.isOrgn(cardReqForm.getProd_kind())){
		%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="ORGANCODE" title="单位组织机构代码" /></td>
			<td width="570" class="dtPanel_Main2">&nbsp;<html:text
				property="organcode" styleClass="Textfield" maxlength="10" size="10" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<%}%>
		<%if(produ_idd.equals("LYROASCC") || produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC")){ %>
			<input type="hidden" name="profession" value="6">
		<%} else {%>
		<tr>
			<td  width="16%" align="left" class="dtPanel_Left">职业</td>
			<td class="dtPanel_Main2">&nbsp;
			<%if(produ_idd.equals("LYRGAJCC")){ %>
				<%=SingleDicMap.getRadio("profession","5010", cardReqForm.getProfession())%>
			<%} else{%>
				<%=SingleDicMap.getRadio("profession","5009", cardReqForm.getProfession())%>
			<% }%>
			</td>                                 
	    </tr>	
		<% }%>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">行业类别</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("trade_kind", "0033", cardReqForm.getTrade_kind())%>
			</td>
		</tr>
		
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">职称</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_grade", "0034", cardReqForm.getTech_grade())%>
		 <%	if(produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC"))
		 {%>
			<input type="hidden" name="work_years" value="1"> 
			</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">职务</td>
			<% if(prod_flag){%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0251", cardReqForm.getTech_posi(),true)%></td>
			<%} else {%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0251", cardReqForm.getTech_posi())%></td>
			<%}%>
		</tr>
		<%} else {%>
			&nbsp;&nbsp;现单位工作年限 <html:text property="work_years"
				styleClass="Textfield" size="2" maxlength="2"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> 年
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">职务</td>
			<% if(prod_flag){%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0035", cardReqForm.getTech_posi(),true)%></td>
			<%} else {%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0035", cardReqForm.getTech_posi())%></td>
			<%}%>
		</tr>
		<%}%>
		
		
		<html:hidden property="employee" /> <!--将“是否是本行员工”置为“否”  -->

		
	</table>


	<%=ViewUtil.getTitle("您的联系信息")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="PRE_ADDR" title="现住址" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;
			<% if(prod_flag){ %>
				<html:text	property="pre_addr" size="60" styleClass="Textfield" maxlength="80" onkeyup="identical(this,comp_addr)" />
			<% } else { %>
				<html:text	property="pre_addr" size="60" styleClass="Textfield" maxlength="80" />
			<%}%>
			<br>
			&nbsp;现住址城市代码 <html:text property="pre_city_code" size="3"
				styleClass="Textfield" maxlength="3"
				onblur="onlyEngToUpperCase(this);getCityZone(this,'pre_zone_no');" onkeyup="onlyEngToUpperCase(this)" />
			&nbsp;<input type="button" name="company" value="城市代码" class="Button"
				onclick="doCityCode()"></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">现住址邮编</td>
			<% if(prod_flag){ %>
				<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
					property="pre_post" styleClass="Textfield" size="6" maxlength="6"
					onblur="onlyNum(this);" onkeyup="onlyNum1(this,comp_post);" /> &nbsp;&nbsp;
				</td>	
			<% } else { %>
				<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
					property="pre_post" styleClass="Textfield" size="6" maxlength="6"
					onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> </td>	
			<%}%>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="COMP_ADDR" title="单位地址" /></td>
			<td class="dtPanel_Main2">&nbsp;
			
			<% if(prod_flag){ %>
				<html:text property="comp_addr"	styleClass="Textfield" size="60" maxlength="80" onkeyup="identical(this,pre_addr)" />	
			<% } else { %>
				<html:text property="comp_addr"	styleClass="Textfield" size="60" maxlength="80" />	
			<%}%>
			
			 <br>
			&nbsp;单位地址城市代码 <html:text property="comp_city_code" size="3"
				styleClass="Textfield" maxlength="3" 
				onblur="onlyEngToUpperCase(this);getCityZone(this,'comp_zone_no')" onkeyup="onlyEngToUpperCase(this)" />
			<input type="button" name="company" value="城市代码" class="Button"
				onclick="doCityCodeComp()"></td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">单位邮编</td>
			
			<% if(prod_flag){ %>
				<td class="dtPanel_Main2">&nbsp;<html:text property="comp_post"
					onblur="onlyNum(this);" onkeyup="onlyNum1(this,pre_post);"
					styleClass="Textfield" size="6" maxlength="6" />&nbsp;</td>	
			<% } else { %>
				<td class="dtPanel_Main2">&nbsp;<html:text property="comp_post"
					onblur="onlyNum(this);" onkeyup="onlyNum(this);"
					styleClass="Textfield" size="6" maxlength="6" />&nbsp;</td>	
			<%}%>
			
			
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="PRE_PHONE" title="联系电话" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;区号 0<html:text styleId="pre_zone_no"
				property="pre_zone_no" styleClass="Textfield" size="3" maxlength="3"
				readonly="false" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />&nbsp;
			电话 <html:text property="pre_phone" size="17" styleClass="Textfield"
				maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)" />&nbsp;
			&nbsp; <htm:title fieldID="PRE_MOBILE" title="移动电话" /> <html:text
				property="pre_mobile" size="11" styleClass="Textfield"
				maxlength="11" onblur="onlyNum(this);" onkeyup="onlyNum(this);" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="COMP_PHONE" title="单位电话" /></td>
			<td class="dtPanel_Main2">&nbsp;区号 0<html:text
				property="comp_zone_no" styleClass="Textfield" size="3" styleId="comp_zone_no"
				maxlength="3" onblur="onlyNum(this);" onkeyup="onlyNum(this);" />&nbsp;
			电话号码 <html:text property="comp_phone" styleClass="Textfield"
				size="17" maxlength="17" onblur="noChinese(this)"
				onkeyup="noChinese(this)" />&nbsp;</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="LM_NAME" title="联系人姓名" /></td>
			<td width="569" class="dtPanel_Main2">&nbsp;<html:text
				property="lm_name" styleClass="Textfield" size="20" maxlength="40"
				onblur="onlyName(this)" onkeyup="onlyName(this)" /> &nbsp;性别 <%=SingleDicMap.getRadio("lm_sex", "0024", cardReqForm.getLm_sex())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="RELATION_BET" title="与申请人关系" /></td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("relation_bet", "0036", cardReqForm.getRelation_bet())%></td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="LM_PRE_PHONE" title="联系电话" /></td>
			<td class="dtPanel_Main2">&nbsp;区号 0<html:text
				property="lm_pre_zoneno" styleClass="Textfield" size="3"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" maxlength="3" />
			&nbsp; 电话号码 <html:text property="lm_pre_phone" styleClass="Textfield"
				size="17" maxlength="17" onblur="noChinese(this)"
				onkeyup="noChinese(this)" />&nbsp; &nbsp; 移动电话号码 <html:text
				property="lm_mobile" styleClass="Textfield" size="11" maxlength="11"
				onblur="onlyNum(this)" onkeyup="onlyNum(this)" /></td>
		</tr>
		<tr>
		<td width="98%" align="left" class="dtPanel_Left" colspan="2" >
		<font color ="red">非本地市户籍需填写以下内容：</font>
		</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			户籍地联系人姓名
			</td>
			<td width="569" class="dtPanel_Main2">&nbsp;<html:text property="hukou_relname" styleClass="Textfield" size="20" maxlength="40" onblur="onlyName(this)" onkeyup="onlyName(this)"/>
			 &nbsp;户籍联系人性别 <%=SingleDicMap.getRadio("hukou_relsex","0024",cardReqForm.getHukou_relsex())%></td>                    
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			户籍联系人与申请人关系
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("hukou_relation","0036",cardReqForm.getHukou_relation())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			户籍联系人电话
			</td>
			<td class="dtPanel_Main2">
			&nbsp;区号 0<html:text property="hukou_relzoneno" onblur="onlyNum(this)" onkeyup="onlyNum(this)" styleClass="Textfield" size="3" maxlength="3"/> &nbsp; 
			电话号码 <html:text property="hukou_relphone" styleClass="Textfield" size="17" maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)"/>&nbsp; &nbsp; 
			移动电话号码 <html:text property="hukou_relmobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/></td>
		</tr>	
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">本人户籍地址</td>
			<td class="dtPanel_Main2">
			&nbsp;
			<html:text property="hukou_loc" styleClass="Textfield"  size="60" maxlength="80"/>
			</td>
		</tr>
		

	</table>


	
    <%=ViewUtil.getTitle("申请人签阅")%>
   
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		
		<%if(CheckUtil.allowDemotion(cardReqForm.getProd_kind())){%>
		<tr> 
			<td  align="left"  class="dtPanel_Left" colspan="4" >
			若申请卡种未获批准，是否同意申办其他等级贷记卡 
			<%=SingleDicMap.getRadio("normal_card","0000", cardReqForm.getNormal_card())%>
			<td>
		</tr>
		<%}else{%>
			<html:hidden property="normal_card" />
		<%}%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">领卡方式</td>
			<td class="dtPanel_Main2" colspan="3">
			<%String str=SingleDicMap.getDicItemVal("0014", cardReqForm.getCard_single_code());
			if(str.equals("金穗缴费通贷记卡"))
			 {%>
		     &nbsp;<INPUT type="radio" name="get_card_mode" class="Radiobutton" checked="checked" value="0">网点自行领取
             <INPUT type="hidden"  name="get_card_mode" value="0">	   
		    <%}
		      else {%>&nbsp;<%=SingleDicMap.getRadio("get_card_mode", "0030", cardReqForm.getGet_card_mode())%>
			<%}%>
			</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">邮寄地址</td>
			<td class="dtPanel_Main2" colspan="3">&nbsp;<%=SingleDicMap.getRadio("post_addr", "0031", cardReqForm.getPost_addr())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">账单方式</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("pre4", "0238", cardReqForm.getPre4())%></td>
			<td width="349" class="dtPanel_Main2">
			电子信箱
			</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="email" styleClass="Textfield"  size="30" maxlength="40"/></td>
		</tr>
		
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">清算行 <html:hidden
				property="settle_no" /></td>
			<td class="dtPanel_Main2">&nbsp;<input name="settle_name"
				class="Textfield" type="text"
				value="<%=OrgMap.getOrgName(cardReqForm.getSettle_no())%>"
				maxlength="40" size="20" readonly>&nbsp; 
			</td>
			<td width="16%" align="left" class="dtPanel_Left">还款卡号</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="repay_card"
				styleClass="Textfield" maxlength="19" size="19"
				onblur="onlyNum(this)" onkeyup="onlyNum(this)" /></td>
		</tr>
					 

		<tr>
			<td width="16%" align="left" class="dtPanel_Left">还款方式</td>
			<td class="dtPanel_Main2" colspan="3">
			&nbsp;
			<%
				if(!"EO".equals(cardReqForm.getCard_single_code())){
			%>
			 <html:select styleClass="Select" property="repay_mark">
			 	<html:optionsCollection name="cardReqForm" property="repay_mark_options" />
			</html:select>	
			<%
				}else{
			%>
       <input type=hidden name="repay_mark" value="2"> 全额还款 
      <%
				}
			%>
		    
				

			 &nbsp; &nbsp;&nbsp;</td>
		</tr>
	
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">起始日期</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="start_date"
				maxlength="8" styleClass="Textfield" size="8" onblur="onlyNum(this)"
				onkeyup="onlyNum(this)" /> <!--input type=button value=" " class="Button_time" onclick="setday(this,document.all.start_date)"-->(yyyymmdd)

			</td>
			<td width="16%" align="left" class="dtPanel_Left">终止日期&nbsp;</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="expire_date"
				styleClass="Textfield" maxlength="8" size="8" onblur="onlyNum(this)"
				onkeyup="onlyNum(this)" /> <!--input type=button value=" " class="Button_time" onclick="setday(this,document.all.expire_date)"-->(yyyymmdd)
			</td>
		</tr>
		
		<%
				if(com.eis.util.CheckUtil.showFixTrans(cardReqForm.getCard_single_code())){
				
		%>	
	  <tr> 
		<td width="16%" align="left" class="dtPanel_Left">固定转存金额</td> 
		<td  class="dtPanel_Main2" colspan="3">
		&nbsp;
		<%=SingleDicMap.getRadio("pre2","0236",cardReqForm.getPre2())%>
		</td>                         					
                     
    </tr> 
<%}%>	
	</table>

	<%=ViewUtil.getTitle("银行填写栏")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">


		<tr>
			<td width="16%" align="left" class="dtPanel_Left">进件来源</td>
			<td height="27" scope="row" class="dtPanel_Main2" colspan="2">&nbsp;<html:select
				property="app_source">
				<html:optionsCollection name="cardReqForm"
					property="app_source_options" />
			</html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
         <input type="button" name="zx" value="调查意见" class="Button" onclick="doPopCredit();"></td>
		</tr>


		<tr bgcolor="#FFFFFF">
			<TD align="left" class='dtPanel_left'>营销人</TD>
			<TD class='dtPanel_left' colspan="2">
		       	&nbsp;&nbsp;营销人姓名：<html:text property="managerName" styleId="managerName" styleClass="Textfield" size="10" maxlength="10" />
		       	&nbsp;&nbsp;营销人编号：<html:text property="managerId" styleId="managerId" styleClass="Textfield-READONLY"  size="10" maxlength="10" readonly="true"/>
		       	<div id="popup" style="position:absolute">
		       		<table id="name_table" bgcolor=#fffafa " border="0" cellspacing="0"	cellpadding="0" />
						<TBODY id="name_table_body"></TBODY>
					</table>
       			</div>			
 			<input type='button' class="Button" value="选择营销人"
				onClick="doManagerPop()" />
      	 	&nbsp; &nbsp;营销人移动电话号码 <html:text property="manager_mobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
      	 	</td>
		</tr>
		<tr>
       		<td width="16%" align="left" class="dtPanel_Left">备注意见</td>
      		<td colspan="3" scope="row" class="dtPanel_Main2" width="84%">&nbsp;
					<html:textarea property="oper_sug" cols="50" rows="3" styleClass="Textfield" />
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
       	<TD align = "left" class='dtPanel_left'>受理人</TD>
       	<TD class='dtPanel_left' colspan="2">
       	&nbsp;&nbsp;受理人姓名：<html:text property="processer_name" styleId="processer_name" styleClass="Textfield" size="10" maxlength="10"/>
		&nbsp;&nbsp;受理机构代码;<input
				name="exam_node_name" type="text" class="Textfield" size="20"
				maxlength="40"
				value="<%=(cardReqForm.getExam_node_no().equals("000000000") ? "总行" : OrgMap.getOrgName(cardReqForm.getExam_node_no()))%>"
				readonly onclick="doPopOrg();"> <html:hidden property="exam_node_no" />
			<input type="button" name="xz2" value="选择机构" class="Button"
				onclick="doPopOrg();"></td>
		</tr>
	    <tr>
		<td width="16%" align="left" class="dtPanel_Left">受理人电话</td>
		<td height="27" scope="row" class="dtPanel_Main2" colspan="2">
		区号 0<html:text property="processer_zoneno" onblur="onlyNum(this)" onkeyup="onlyNum(this)" styleClass="Textfield" size="3" maxlength="3"/> &nbsp; 
		电话号码 <html:text property="processer_phone" styleClass="Textfield" size="17" maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)"/>&nbsp; &nbsp; 
		移动电话号码 <html:text property="processer_mobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td> 
    </tr> 
    <tr>
		<td width="16%" align="left" class="dtPanel_Left">客户来源</td>
		<td class="dtPanel_Main2">
		&nbsp;
		<html:select property="channel_id">
		<html:optionsCollection name="cardReqForm" property="channel_id_options"/>
		</html:select>
		</td>
		<td width="349" class="dtPanel_Main2">活动代码
		<html:select property="act_id">
		<html:optionsCollection name="cardReqForm" property="act_id_options"/>
		</html:select>
		</td>
	</tr>
		
	</table>   
<%=ViewUtil.getTitle("证明文件类")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0" >
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		身份证明文件
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("pidfile","5083",cardReqForm.getPidfile())%>  
			<html:text property="otherpidfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		资信证明文件
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("creditfile","5084",cardReqForm.getCreditfile())%>  
			<html:text property="othercreditfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		收入证明文件
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("incomefile","5085",cardReqForm.getIncomefile())%>  
			<html:text property="otherincomefile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		工作证明文件
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("jobidentifyfile","5086",cardReqForm.getJobidentifyfile())%>  
			<html:text property="otherjobidentifyfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		担保方式
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("assu_type","5007",cardReqForm.getAssu_type())%>  
		(存在担保方式时，关于担保方式的其他信息，可在下方“其他证明文件”域里补充)
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		其它证明文件
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<html:text property="otheridentifyfile" styleClass="Textfield" size="100" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		担保金额
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<html:text property="assure_lmt" styleClass="Textfield" size="8" maxlength="8"/>元
		</td>		
	</tr>
</table>
<%=ViewUtil.getTitle("主卡推荐人栏")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    <tr bgcolor="#FFFFFF">
       	<td width="16%" align="left" class="dtPanel_Left">主卡推荐人</td>
       	<td class="dtPanel_Main2">&nbsp;
       	推荐类型：
        <%=SingleDicMap.getRadio_WithFun("ref_type","5103",cardReqForm.getRef_type(),"clean_ref_no_id(this)")%>
        推荐号码：
       	<html:text property="ref_info" styleClass="Textfield"  size="20" maxlength="20" onkeyup="checkRef_info(this)" onblur=" checkRef_info_length(this)"/>
       	 <INPUT type="hidden"  name="ref_no_id" value='<%=cardReqForm.getRef_no_id()%>'>
       	 
       	<input type='button'class="Button"  value="推荐人查询" onClick="doReferencePop()"/>
       	
       	</td>
    </tr>
</table>
<% if(CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
	<!--修改附卡页面-->			
	<%=ViewUtil.getTitle("附卡申请资料")%>					
<%}%>

	<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"
<%if(!CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
 	style="display:none"
<%}%>	
	>

		<html:hidden property="seq_no" />
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">中文姓名</td>
			<td width="568" class="dtPanel_Main2">&nbsp;<html:text
				property="per_card_cname" styleClass="Textfield" size="20"
				maxlength="40" onblur="onlyName(this)" onkeyup="onlyName(this)" />
			&nbsp; 国籍 <html:text property="per_nation" styleClass="Textfield"
				size="3" maxlength="3" readonly="true" /> <SELECT
				onchange=attachCardNationality(this) name=select>
				<SCRIPT language="javascript">document.writeln(country_code);</SCRIPT>
			</SELECT></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">性别</td>
			<td width="500" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_sex", "0024", cardReqForm.getPer_sex())%>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">拼音或英文姓名</td>
			<td class="dtPanel_Main2">&nbsp;姓 <html:text property="per_last_name"
				styleClass="Textfield" size="10" maxlength="10"
				onblur="onlyEngToUpperCase(this)" onkeyup="onlyEngToUpperCase(this)" />
			名 <html:text property="per_first_name" styleClass="Textfield"
				size="16" maxlength="16" onblur="onlyEngToUpperCase(this)"
				onkeyup="onlyEngToUpperCase(this)" />&nbsp; 出生日期 <html:text
				property="per_birth" styleClass="Textfield" size="8" maxlength="8"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" />(yyyymmdd)</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">证件类型</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_cert_type", "0026", cardReqForm.getPer_cert_type())%>
			请列明 <html:text property="per_cert_name" styleClass="Textfield"
				size="15" maxlength="20" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">证件号码</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_cert_no"
				styleClass="Textfield" size="24" maxlength="24" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<%
			if(com.eis.util.CheckUtil.checkMemberPer(cardReqForm.getProd_kind()) == 1){
		%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">附卡会员编号</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_membernumber" styleClass="Textfield" size="19" maxlength="19"  onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"></html:text>
			</td>
		</tr>
		<%} %>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">联系地址</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_addr"
				styleClass="Textfield" size="60" maxlength="80" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">地址邮编</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_post_code"
				styleClass="Textfield" size="6" maxlength="6"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> &nbsp;&nbsp;
			&nbsp;&nbsp; 电子信箱 <html:text property="per_email"
				styleClass="Textfield" size="30" maxlength="40" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">联系电话</td>
			<td class="dtPanel_Main2">&nbsp;区号 0<html:text property="per_zone_no"
				styleClass="Textfield" size="3" maxlength="3"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> &nbsp; &nbsp;
			电话号码 <html:text property="per_phone" styleClass="Textfield" size="17"
				maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)" />
			&nbsp; 移动电话号码 <html:text property="per_mobile" styleClass="Textfield"
				size="11" maxlength="11" onblur="onlyNum(this);"
				onkeyup="onlyNum(this);" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">与主卡人关系</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_rela", "0036", cardReqForm.getPer_rela())%>
			</td>
		</tr>
		
		<%
			if(com.eis.util.CheckUtil.needPerlayout(cardReqForm.getProd_kind())){
			String radio = com.eis.util.CheckUtil.radioNumber(cardReqForm.getProd_kind());
		%>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">附卡版面标志</td>
		<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_layout_flag",radio,cardReqForm.getPer_layout_flag())%>
		</td>
		</tr>	
		<%}%>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">领卡方式</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_getcard_mode", "0047", cardReqForm.getPer_getcard_mode())%>
			</td>
		</tr>
	</table>
<%if(CheckUtil.allowAttach(cardReqForm.getProd_kind())) {%> 
<%=ViewUtil.getTitle("附卡推荐人栏")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    <tr bgcolor="#FFFFFF">
       	<td width="16%" align="left" class="dtPanel_Left">附卡推荐人</td>
       	<td class="dtPanel_Main2">&nbsp;
       	推荐类型：
        <%=SingleDicMap.getRadio_WithFun("per_ref_type","5103",cardReqForm.getPer_ref_type(),"clean_per_no_id(this)")%>
        推荐号码：
       	<html:text property="per_ref_info" styleClass="Textfield"  size="20" maxlength="20" onkeyup="checkPer_ref_info(this)" onblur=" checkPRef_info_length(this)"/>
       	 <INPUT type="hidden"  name="per_ref_no_id" value='<%=cardReqForm.getPer_ref_no_id()%>'/>
       	<input type='button'class="Button"  value="推荐人查询" onClick="doReferencePop1()"/>
       	
       	</td>
    </tr>
</table> 
 <%}%>
	<logic:equal name="cardReqForm" property="feed_back_flag" value="1">
		<%=ViewUtil.getTitle("反馈意见栏")%>
		<table align="center" width="98%" class="dtPanel_Line3" border="0"
			cellspacing="1" cellpadding="0">
			<tr>
				<td width="16%" align="left" class="dtPanel_Left">备注</td>
				<td class="dtPanel_Main2">&nbsp;<%=cardReqForm.getMemo()%></td>
			</tr>

			<tr>
				<td width="16%" align="left" class="dtPanel_Left">反馈意见</td>
				<td class="dtPanel_Main2">&nbsp;<html:select property="feedback_sug">
					<html:optionsCollection name="cardReqForm"
						property="feedback_sug_options" />
				</html:select></td>
			</tr>

			<tr>
				<td width="16%" align="left" class="dtPanel_Left">反馈说明</td>
				<td class="dtPanel_Main2">&nbsp;<html:textarea
					property="feedback_exp" cols="50" rows="3" styleClass="Textfield" />
				</td>
			</tr>
		</table>
	</logic:equal>

	<!--修改附卡结束-->
	<table class=heightspace_bottom1 border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<table align="center" width="98%" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom">
			<auth:button name="update" id="modify_req" value="保存" onClick="doSave()" />
			&nbsp;&nbsp;&nbsp; 
			<auth:button name="d" value="错误反馈" id="inputer_submit" onClick="feedbackError();"/>&nbsp;&nbsp;
			<auth:button name="Submit1" id="inputer_submit" value="提交复核" onClick="doSend()" /> 
			&nbsp;&nbsp;&nbsp; 
			<input name="return" type="button" class="Button" value="返回" onClick="window.location='CardReqNew.do?act=list';"></td>
			
		</tr>
	</table>

</html:form>


<%if(!CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
<!--以下为附卡管理-->
<html:form method="post" action="AttachCard.do" style="display:none">
	<input type=hidden name=act value="list">
	<input type=hidden name=requery>
	<input type=hidden name=app_no>
	<input type=hidden name=net_node_no>
	<input type=hidden name=settlement_no>
	<input type=hidden name=seq_no>
	<input type=hidden name=task_type>
	<input type=hidden name=prod>

	<table width="98%" align="center" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line"></td>
		</tr>
	</table>
	<%=ViewUtil.getTitle("附卡列表")%>
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">



					<td width="20%">附卡客户姓名</td>
					<td width="20%">附卡客户证件类型</td>

					<td width="35%">附卡客户证件号码</td>

					<td width="15%">附卡客户与主卡人关系</td>

					<td>选择</td>

				</tr>
				<%List list = pageResultAttach.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
        AttachCardVO vo = (AttachCardVO)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">

					<%if (vo.getRed().equals("0")) {%>
					<td><a class=fontlink1
						href="AttachCard.do?act=r&app_no=<%=vo.getApp_no()%>&net_node_no=<%=vo.getNet_node_no()%>&settlement_no=<%=vo.getSettlement_no()%>&seq_no=<%=vo.getSeq_no()%>">
					<%=vo.getPer_card_cname()%></a></td>
					<%} else {%>
					<td><a class=redlink
						href="AttachCard.do?act=r&app_no=<%=vo.getApp_no()%>&net_node_no=<%=vo.getNet_node_no()%>&settlement_no=<%=vo.getSettlement_no()%>&seq_no=<%=vo.getSeq_no()%>">
					<%=vo.getPer_card_cname()%></a></td>
					<%}%>

					<td><%=SingleDicMap.getDicItemVal("0026", vo.getPer_cert_type())%></td>
					<td><%=vo.getPer_cert_no()%></td>


					<td><%=SingleDicMap.getDicItemVal("0036", vo.getPer_rela())%></td>



					<td><label><input type="radio" name="param"
						onClick="setPKey('<%=vo.getApp_no()%>','<%=vo.getNet_node_no()%>','<%=vo.getSettlement_no()%>','<%=vo.getSeq_no()%>')">
					</label></td>
				</tr>

				<%}
}%>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" align="center"><auth:button name="add"
						id="AttachCard_c" value="增加附卡" onClick="doAdd()" /> &nbsp; <auth:button
						name="edit" id="AttachCard_u" value="修改附卡" onClick="doEdit()" />
					&nbsp; <auth:button name="delete" id="AttachCard_d" value="删除附卡"
						onClick="doDelete()" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<%} else {%>
<!--以下为附卡管理-->
<html:form method="post" action="AttachCard.do" >
	<input type=hidden name=act value="list">
	<input type=hidden name=requery>
	<input type=hidden name=app_no>
	<input type=hidden name=net_node_no>
	<input type=hidden name=settlement_no>
	<input type=hidden name=seq_no>
	<input type=hidden name=task_type>
      <input type=hidden name=prod>
	<table width="98%" align="center" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line"></td>
		</tr>
	</table>
	<%=ViewUtil.getTitle("附卡列表")%>
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">



					<td width="20%">附卡客户姓名</td>
					<td width="20%">附卡客户证件类型</td>

					<td width="35%">附卡客户证件号码</td>

					<td width="15%">附卡客户与主卡人关系</td>

					<td>选择</td>

				</tr>
				<%List list = pageResultAttach.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
        AttachCardVO vo = (AttachCardVO)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">

					<%if (vo.getRed().equals("0")) {%>
					<td><a class=fontlink1 href="AttachCard.do?act=r&app_no=<%=vo.getApp_no()%>&net_node_no=<%=vo.getNet_node_no()%>&settlement_no=<%=vo.getSettlement_no()%>&seq_no=<%=vo.getSeq_no()%>&prod=<%=cardReqForm.getProd_kind()%>">
					<%=vo.getPer_card_cname()%></a></td>
					<%} else {%>
					<td><a class=redlink href="AttachCard.do?act=r&app_no=<%=vo.getApp_no()%>&net_node_no=<%=vo.getNet_node_no()%>&settlement_no=<%=vo.getSettlement_no()%>&seq_no=<%=vo.getSeq_no()%>&prod=<%=cardReqForm.getProd_kind()%>">
					<%=vo.getPer_card_cname()%></a></td>
					<%}%>

					<td><%=SingleDicMap.getDicItemVal("0026", vo.getPer_cert_type())%></td>
					<td><%=vo.getPer_cert_no()%></td>

					<td><%=SingleDicMap.getDicItemVal("0036", vo.getPer_rela())%></td>

					<td><label><input type="radio" name="param"
						onClick="setPKey('<%=vo.getApp_no()%>','<%=vo.getNet_node_no()%>','<%=vo.getSettlement_no()%>','<%=vo.getSeq_no()%>')">
					</label></td>
				</tr>

				<%}
}%>
			</table>
			</td>
		</tr>

		<tr>
			<td>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" align="center"><auth:button name="add"
						id="AttachCard_c" value="增加附卡" onClick="doAdd()" /> &nbsp; <auth:button
						name="edit" id="AttachCard_u" value="修改附卡" onClick="doEdit()" />
					&nbsp; <auth:button name="delete" id="AttachCard_d" value="删除附卡"
						onClick="doDelete()" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<%}%>
</BODY>
</HTML>
