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
<title>�޸���������</title>
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

	//�޸� 
	if(document.forms[0].managerId.value.length>0 && trim(document.forms[0].managerId.value).length!=10){
		alert("Ӫ���˱��Ӧ��Ϊ10λ����");
		document.forms[0].managerId.focus();
		return;
	}

	//����QQ������
	var lmmark = '<%=cardReqForm.getCard_single_code()%>';
	if(lmmark =='H2'&&(document.forms[0].per_layout_flag[1].checked)){
		alert("QQ������������С�������޸�Ϊ��");
		return;
	}
	
	 //���ݸ��������������Ƿ�Ϊ��,��ȷ���Ƿ�Ҫͬ������һ�Ÿ���
	//����Ϊ��,����ҪУ�鸽������
	var per_card_cname = document.forms[0].per_card_cname.value; 
	if(!isEmpty(per_card_cname)){
		//У�鸽������
		if(!checkForm()){
			return;
		}			
		
		if(!checkFormAttach()){
 			return;
 		}
 		
 		var rela_lm = getSelectedButton(document.forms[0].relation_bet);//����ϵ�˹�ϵ
 		var rela_percard = getSelectedButton(document.forms[0].per_rela);//�븽���˹�ϵ
 		//��ϵ���븽���˲�ͬʱ,�����˲���ͬʱ�����˹�ϵΪ��ż
 		if(per_card_cname != document.forms[0].lm_name.value){
 			if(rela_lm == 0 && rela_percard == 0){
 				alert("����������ϵ�˻򸽿������˹�ϵ����");
 				document.forms[0].per_rela[0].focus();
 				return;
 			}		
 		}
 		//������δ�飬�����븽���������˹�ϵΪ��ż
 		if(getSelectedButton(document.forms[0].marr_stat) == 0 && rela_percard == 0){
 			alert("����������ϵ�˻򸽿������˹�ϵ����");
 			document.forms[0].per_rela[0].focus();
 			return;
 		}
 		//ͬ�Թ�ϵ����Ϊ��ż
 		if(getSelectedButton(document.forms[0].cust_sex) == getSelectedButton(document.forms[0].per_sex) && rela_percard == 0){
 			alert("����������ϵ�˻򸽿������˹�ϵ����");
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
		alert("�����޸ĳɹ���"); 
		<%}
request.setAttribute("success", "n");
%> 
function doSend(){ //�ύ����
	if(document.forms[0].managerId.value.length>0 && trim(document.forms[0].managerId.value).length!=10){
		alert("Ӫ���˱��Ӧ��Ϊ10λ����");
		document.forms[0].managerId.focus();
		return;
	}

	//����QQ������
	var lmmark = '<%=cardReqForm.getCard_single_code()%>';
	if(lmmark =='H2'&&(document.forms[0].per_layout_flag[1].checked)){
		alert("QQ������������С�������޸�Ϊ��");
		return;
	}
	
	//���ݸ��������������Ƿ�Ϊ��,��ȷ���Ƿ�Ҫͬ������һ�Ÿ���
	//����Ϊ��,����ҪУ�鸽������
	var per_card_cname = document.forms[0].per_card_cname.value; 
	if(!isEmpty(per_card_cname)){
		//У�鸽������
		if(!checkForm()){
			return;
		}			
		
		if(!checkFormAttach()){
 			return;
 		}
 			
 		var flag = document.forms[0].feed_back_flag.value;
		if(flag == 1){		
			if(isEmpty(document.forms[0].feedback_sug.value)){
				alert("����д�������");
				document.forms[0].feedback_sug.focus();
				return;
			}	
			if(isEmpty(document.forms[0].feedback_exp.value)){
				alert("����д����˵��");
				document.forms[0].feedback_exp.focus();
				return;
			}
			if(document.forms[0].feedback_exp.value.length > 100){
				alert("����˵������100����");
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
			alert("����д�������");
			document.forms[0].feedback_sug.focus();
			return;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("����д����˵��");
			document.forms[0].feedback_exp.focus();
			return;
		}		
	}

	//��������������߼����
 	if(checkForm()){
 		document.forms[0].act.value='input_send';	 
		document.forms[0].submit(); 
 	}

} 

//�����������õ�У��
function doAdd(){ 
	//���� 

	window.location="AttachCard.do?act=c&prod=<%=cardReqForm.getProd_kind()%>&prod=<%=cardReqForm.getProd_kind()%>";
} 
function feedbackError(){
	openWin("ErrorInfoFlag.do?act=list&app_no=<%=cardReqForm.getApp_no()%>","getErrorList");
} 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[1].app_no.value == null ||document.forms[1].app_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[1].act.value='u';
	var prod = "<%=cardReqForm.getProd_kind()%>";
	
	document.forms[1].prod.value=prod;	 
	document.forms[1].submit(); 
} 
 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[1].app_no.value == null ||document.forms[1].app_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[1].act.value='d'; 
	document.forms[1].submit(); 
} 
 
function doQuery() {  
	//��������ѯ���� 
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

function copyPost(){ //��������ַ����סַ��ͬ���򻧼���ַ�ʱ��Զ�������סַ�ʱ�
	//��������ַΪ��ͬ��סַ��
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
	<!--�Ƿ��Ǳ���Ա����־-->
	<html:hidden property="card_single_code" />
	<!--Ԥ�衰��������ʶ�롱-->
	<html:hidden property="prep1" />
	<!--v+��Ʒ����-->
	<!--���������־λ,�����Ƿ���ʾ���������-->
	<html:hidden property="feed_back_flag" />

	<html:hidden property="currency" />
	<!--Լ����������Ϊ����-->

	<!--��־��һ�Ÿ�����Ϣ�У�����������ϵ��������ż�������б��ж�Ӧ�ĵ�һ��������ϵҲΪ��ż-->
	<!--ע�⣺���²��ĸ����б��У��ж�Ӧ��-->
	<!--html:hidden property="couple_flag"/-->

	<%=ViewUtil.getTitle("�������뿨��")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">���뿨Ʒ��</td>
			<td colspan="2" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getDicItemVal("0010", cardReqForm.getCard_mark())%>
			<input type=hidden name="card_mark"
				value="<%=cardReqForm.getCard_mark()%>">

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��������ʶ�� <%=SingleDicMap.getDicItemVal("0014", cardReqForm.getCard_single_code())%>
		<%
			if(com.eis.util.CheckUtil.checkMemberAuto(cardReqForm.getProd_kind()) == 1){
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			��Ա��ţ�<html:text property="memberNumber" styleClass="Textfield" size="19" maxlength="19"  onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"></html:text>
		<%}%>
			</td>
		</tr>
		<%
			if(!cardReqForm.getLayout_options().isEmpty()){
		%>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�����ʶ
		</td> 
		<td colspan="2" class="dtPanel_Main2">&nbsp;
		<html:select styleClass="Select" property="layoutNo">	
	  	<html:optionsCollection name="cardReqForm" property="layout_options" />
    	</html:select>	
		</td>
		<%}%>
			<tr align="center" class="dtPanel_Top01">
		<td width="16%" align="left" class="dtPanel_Left">���뵥λ:</td>
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
		�����־
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
			<td width="16%" align="left" class="dtPanel_Left">���뿨��</td>
			<td colspan="2" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getDicItemVal("0013", cardReqForm.getCard_kind())%>&nbsp;&nbsp;
			<input type=hidden name="card_kind" value="<%=cardReqForm.getCard_kind()%>"> 

			</td>
		</tr>						
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�������</td>
			<td width="209" class="dtPanel_Main2">&nbsp;<%=OrgUtil.getNameByAppCode(cardReqForm.getApp_cur())%>
			<input type=hidden name="app_cur"
				value="<%=cardReqForm.getApp_cur()%>">
				&nbsp;���ִ���&nbsp;<%=cardReqForm.getDefine()%></td>
			<td width="349" class="dtPanel_Main2">&nbsp;
			&nbsp;<input type="button" name="zz" value="������ֵ����" class="Button" onclick="doPopServ();">
		</td>         
		</tr>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�Ƿ������д��ǿ�
		</td>
		<td width="209" class="dtPanel_Main2">
		<%=SingleDicMap.getRadio("abccard","0000",cardReqForm.getAbccard())%>
		</td>           
		<td width="349" class="dtPanel_Main2">&nbsp;
		&nbsp;<input type="button" name="xe" value="����ר���̻����ڶ��" class="Button" onclick="doPopBudget();">
		</td>         
	</tr>		
	</table>


	<%=ViewUtil.getTitle("�����˻�������")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��������</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
				property="cust_name" size="20" styleClass="Textfield" maxlength="40"
				onblur="onlyName(this)" onkeyup="onlyName(this)" /> &nbsp;���� <html:text
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
			<td width="16%" align="left" class="dtPanel_Left">�Ա�</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp; <%=SingleDicMap.getRadio("cust_sex", "0024", cardReqForm.getCust_sex())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">ƴ����Ӣ������</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<htm:title
				fieldID="LAST_NAME" title="��" /> <html:text property="last_name"
				styleClass="Textfield" size="10" maxlength="10"
				onblur="onlyEngToUpperCase(this)" onkeyup="onlyEngToUpperCase(this)" />
			<htm:title fieldID="FIRST_NAME" title="��" /> <html:text
				property="first_name" size="16" styleClass="Textfield"
				maxlength="16" onblur="onlyEngToUpperCase(this)"
				onkeyup="onlyEngToUpperCase(this)" /> &nbsp; �������� <html:text
				property="birthday" styleClass="Textfield" size="8" maxlength="8"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> (yyyymmdd)</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">����״��</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("marr_stat", "0025", cardReqForm.getMarr_stat())%></td>
		</tr>

		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="CERT_INFO" title="֤������" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%if(prod_name.equals("LYRODOCC") || prod_name.equals("LYRGJOCC"))
		      {%>
		      <%=SingleDicMap.getRadio("cert_type", "0252", cardReqForm.getCert_type())%>
		     <%} else {%>
		     <%=SingleDicMap.getRadio("cert_type", "0026", cardReqForm.getCert_type())%>
		     <%}%>
			
			������ <html:text property="cert_name" styleClass="Textfield"
				maxlength="20" size="15" /></td>
		</tr>

		<tr>
			<td width="16%" align="left" class="dtPanel_Left">֤������</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text
				property="cert_no" styleClass="Textfield" maxlength="24" size="24" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�����̶�</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("edu_degr", "0027", cardReqForm.getEdu_degr())%></td>
		</tr>

		<% String produ_idd=cardReqForm.getProd_kind();
		if(produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC"))
		{%> 
			<input type="hidden" name="per_income" value="20000">
			
	    <%} else {%>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">����״��</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;���������� <html:text
				property="per_income" styleClass="Textfield" size="9" maxlength="9"
				onblur="checkDouble(this)" onkeyup="checkDouble(this)" />
			Ԫ�����&nbsp;&nbsp;
			<%if(!prod_flag) {%>
			��סַ�Ѿ�ס����<html:text property="pre_years" size="2"
			styleClass="Textfield" maxlength="2" onblur="onlyNum(this);"
			onkeyup="onlyNum(this);" /> �� &nbsp;&nbsp; &nbsp;&nbsp;	<%}%>
			</td>
		</tr>
		<%}%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">סլ״��</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("hou_stat", "0029", cardReqForm.getHou_stat())%><br>
			&nbsp;�»��� &nbsp;<html:text property="hou_mon_loan" maxlength="7"
				size="9" styleClass="Textfield" onblur="checkDouble(this)"
				onkeyup="checkDouble(this)" /> Ԫ�����</td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="COMP_NAME" title="��λȫ��" /></td>
			<td width="570" class="dtPanel_Main2">&nbsp;<html:text
				property="comp_name" styleClass="Textfield" maxlength="80" size="60" /></td>
		</tr>
		
		
		<%
		if(com.eis.util.CheckUtil.isOrgn(cardReqForm.getProd_kind())){
		%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="ORGANCODE" title="��λ��֯��������" /></td>
			<td width="570" class="dtPanel_Main2">&nbsp;<html:text
				property="organcode" styleClass="Textfield" maxlength="10" size="10" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<%}%>
		<%if(produ_idd.equals("LYROASCC") || produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC")){ %>
			<input type="hidden" name="profession" value="6">
		<%} else {%>
		<tr>
			<td  width="16%" align="left" class="dtPanel_Left">ְҵ</td>
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
			<td width="16%" align="left" class="dtPanel_Left">��ҵ���</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("trade_kind", "0033", cardReqForm.getTrade_kind())%>
			</td>
		</tr>
		
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">ְ��</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_grade", "0034", cardReqForm.getTech_grade())%>
		 <%	if(produ_idd.equals("LYRODOCC") || produ_idd.equals("LYRGJOCC"))
		 {%>
			<input type="hidden" name="work_years" value="1"> 
			</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">ְ��</td>
			<% if(prod_flag){%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0251", cardReqForm.getTech_posi(),true)%></td>
			<%} else {%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0251", cardReqForm.getTech_posi())%></td>
			<%}%>
		</tr>
		<%} else {%>
			&nbsp;&nbsp;�ֵ�λ�������� <html:text property="work_years"
				styleClass="Textfield" size="2" maxlength="2"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> ��
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">ְ��</td>
			<% if(prod_flag){%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0035", cardReqForm.getTech_posi(),true)%></td>
			<%} else {%>
				<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("tech_posi", "0035", cardReqForm.getTech_posi())%></td>
			<%}%>
		</tr>
		<%}%>
		
		
		<html:hidden property="employee" /> <!--�����Ƿ��Ǳ���Ա������Ϊ����  -->

		
	</table>


	<%=ViewUtil.getTitle("������ϵ��Ϣ")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="PRE_ADDR" title="��סַ" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;
			<% if(prod_flag){ %>
				<html:text	property="pre_addr" size="60" styleClass="Textfield" maxlength="80" onkeyup="identical(this,comp_addr)" />
			<% } else { %>
				<html:text	property="pre_addr" size="60" styleClass="Textfield" maxlength="80" />
			<%}%>
			<br>
			&nbsp;��סַ���д��� <html:text property="pre_city_code" size="3"
				styleClass="Textfield" maxlength="3"
				onblur="onlyEngToUpperCase(this);getCityZone(this,'pre_zone_no');" onkeyup="onlyEngToUpperCase(this)" />
			&nbsp;<input type="button" name="company" value="���д���" class="Button"
				onclick="doCityCode()"></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��סַ�ʱ�</td>
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
				fieldID="COMP_ADDR" title="��λ��ַ" /></td>
			<td class="dtPanel_Main2">&nbsp;
			
			<% if(prod_flag){ %>
				<html:text property="comp_addr"	styleClass="Textfield" size="60" maxlength="80" onkeyup="identical(this,pre_addr)" />	
			<% } else { %>
				<html:text property="comp_addr"	styleClass="Textfield" size="60" maxlength="80" />	
			<%}%>
			
			 <br>
			&nbsp;��λ��ַ���д��� <html:text property="comp_city_code" size="3"
				styleClass="Textfield" maxlength="3" 
				onblur="onlyEngToUpperCase(this);getCityZone(this,'comp_zone_no')" onkeyup="onlyEngToUpperCase(this)" />
			<input type="button" name="company" value="���д���" class="Button"
				onclick="doCityCodeComp()"></td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��λ�ʱ�</td>
			
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
				fieldID="PRE_PHONE" title="��ϵ�绰" /></td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;���� 0<html:text styleId="pre_zone_no"
				property="pre_zone_no" styleClass="Textfield" size="3" maxlength="3"
				readonly="false" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />&nbsp;
			�绰 <html:text property="pre_phone" size="17" styleClass="Textfield"
				maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)" />&nbsp;
			&nbsp; <htm:title fieldID="PRE_MOBILE" title="�ƶ��绰" /> <html:text
				property="pre_mobile" size="11" styleClass="Textfield"
				maxlength="11" onblur="onlyNum(this);" onkeyup="onlyNum(this);" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="COMP_PHONE" title="��λ�绰" /></td>
			<td class="dtPanel_Main2">&nbsp;���� 0<html:text
				property="comp_zone_no" styleClass="Textfield" size="3" styleId="comp_zone_no"
				maxlength="3" onblur="onlyNum(this);" onkeyup="onlyNum(this);" />&nbsp;
			�绰���� <html:text property="comp_phone" styleClass="Textfield"
				size="17" maxlength="17" onblur="noChinese(this)"
				onkeyup="noChinese(this)" />&nbsp;</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="LM_NAME" title="��ϵ������" /></td>
			<td width="569" class="dtPanel_Main2">&nbsp;<html:text
				property="lm_name" styleClass="Textfield" size="20" maxlength="40"
				onblur="onlyName(this)" onkeyup="onlyName(this)" /> &nbsp;�Ա� <%=SingleDicMap.getRadio("lm_sex", "0024", cardReqForm.getLm_sex())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="RELATION_BET" title="�������˹�ϵ" /></td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("relation_bet", "0036", cardReqForm.getRelation_bet())%></td>
		</tr>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left"><htm:title
				fieldID="LM_PRE_PHONE" title="��ϵ�绰" /></td>
			<td class="dtPanel_Main2">&nbsp;���� 0<html:text
				property="lm_pre_zoneno" styleClass="Textfield" size="3"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" maxlength="3" />
			&nbsp; �绰���� <html:text property="lm_pre_phone" styleClass="Textfield"
				size="17" maxlength="17" onblur="noChinese(this)"
				onkeyup="noChinese(this)" />&nbsp; &nbsp; �ƶ��绰���� <html:text
				property="lm_mobile" styleClass="Textfield" size="11" maxlength="11"
				onblur="onlyNum(this)" onkeyup="onlyNum(this)" /></td>
		</tr>
		<tr>
		<td width="98%" align="left" class="dtPanel_Left" colspan="2" >
		<font color ="red">�Ǳ����л�������д�������ݣ�</font>
		</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			��������ϵ������
			</td>
			<td width="569" class="dtPanel_Main2">&nbsp;<html:text property="hukou_relname" styleClass="Textfield" size="20" maxlength="40" onblur="onlyName(this)" onkeyup="onlyName(this)"/>
			 &nbsp;������ϵ���Ա� <%=SingleDicMap.getRadio("hukou_relsex","0024",cardReqForm.getHukou_relsex())%></td>                    
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			������ϵ���������˹�ϵ
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("hukou_relation","0036",cardReqForm.getHukou_relation())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			������ϵ�˵绰
			</td>
			<td class="dtPanel_Main2">
			&nbsp;���� 0<html:text property="hukou_relzoneno" onblur="onlyNum(this)" onkeyup="onlyNum(this)" styleClass="Textfield" size="3" maxlength="3"/> &nbsp; 
			�绰���� <html:text property="hukou_relphone" styleClass="Textfield" size="17" maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)"/>&nbsp; &nbsp; 
			�ƶ��绰���� <html:text property="hukou_relmobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/></td>
		</tr>	
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">���˻�����ַ</td>
			<td class="dtPanel_Main2">
			&nbsp;
			<html:text property="hukou_loc" styleClass="Textfield"  size="60" maxlength="80"/>
			</td>
		</tr>
		

	</table>


	
    <%=ViewUtil.getTitle("������ǩ��")%>
   
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">
		
		<%if(CheckUtil.allowDemotion(cardReqForm.getProd_kind())){%>
		<tr> 
			<td  align="left"  class="dtPanel_Left" colspan="4" >
			�����뿨��δ����׼���Ƿ�ͬ����������ȼ����ǿ� 
			<%=SingleDicMap.getRadio("normal_card","0000", cardReqForm.getNormal_card())%>
			<td>
		</tr>
		<%}else{%>
			<html:hidden property="normal_card" />
		<%}%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�쿨��ʽ</td>
			<td class="dtPanel_Main2" colspan="3">
			<%String str=SingleDicMap.getDicItemVal("0014", cardReqForm.getCard_single_code());
			if(str.equals("����ɷ�ͨ���ǿ�"))
			 {%>
		     &nbsp;<INPUT type="radio" name="get_card_mode" class="Radiobutton" checked="checked" value="0">����������ȡ
             <INPUT type="hidden"  name="get_card_mode" value="0">	   
		    <%}
		      else {%>&nbsp;<%=SingleDicMap.getRadio("get_card_mode", "0030", cardReqForm.getGet_card_mode())%>
			<%}%>
			</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�ʼĵ�ַ</td>
			<td class="dtPanel_Main2" colspan="3">&nbsp;<%=SingleDicMap.getRadio("post_addr", "0031", cardReqForm.getPost_addr())%></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�˵���ʽ</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("pre4", "0238", cardReqForm.getPre4())%></td>
			<td width="349" class="dtPanel_Main2">
			��������
			</td>
			<td colspan="3" class="dtPanel_Main2">&nbsp;<html:text property="email" styleClass="Textfield"  size="30" maxlength="40"/></td>
		</tr>
		
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">������ <html:hidden
				property="settle_no" /></td>
			<td class="dtPanel_Main2">&nbsp;<input name="settle_name"
				class="Textfield" type="text"
				value="<%=OrgMap.getOrgName(cardReqForm.getSettle_no())%>"
				maxlength="40" size="20" readonly>&nbsp; 
			</td>
			<td width="16%" align="left" class="dtPanel_Left">�����</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="repay_card"
				styleClass="Textfield" maxlength="19" size="19"
				onblur="onlyNum(this)" onkeyup="onlyNum(this)" /></td>
		</tr>
					 

		<tr>
			<td width="16%" align="left" class="dtPanel_Left">���ʽ</td>
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
       <input type=hidden name="repay_mark" value="2"> ȫ��� 
      <%
				}
			%>
		    
				

			 &nbsp; &nbsp;&nbsp;</td>
		</tr>
	
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��ʼ����</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="start_date"
				maxlength="8" styleClass="Textfield" size="8" onblur="onlyNum(this)"
				onkeyup="onlyNum(this)" /> <!--input type=button value=" " class="Button_time" onclick="setday(this,document.all.start_date)"-->(yyyymmdd)

			</td>
			<td width="16%" align="left" class="dtPanel_Left">��ֹ����&nbsp;</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="expire_date"
				styleClass="Textfield" maxlength="8" size="8" onblur="onlyNum(this)"
				onkeyup="onlyNum(this)" /> <!--input type=button value=" " class="Button_time" onclick="setday(this,document.all.expire_date)"-->(yyyymmdd)
			</td>
		</tr>
		
		<%
				if(com.eis.util.CheckUtil.showFixTrans(cardReqForm.getCard_single_code())){
				
		%>	
	  <tr> 
		<td width="16%" align="left" class="dtPanel_Left">�̶�ת����</td> 
		<td  class="dtPanel_Main2" colspan="3">
		&nbsp;
		<%=SingleDicMap.getRadio("pre2","0236",cardReqForm.getPre2())%>
		</td>                         					
                     
    </tr> 
<%}%>	
	</table>

	<%=ViewUtil.getTitle("������д��")%>
	<table align="center" width="98%" class="dtPanel_Line3" border="0"
		cellspacing="1" cellpadding="0">


		<tr>
			<td width="16%" align="left" class="dtPanel_Left">������Դ</td>
			<td height="27" scope="row" class="dtPanel_Main2" colspan="2">&nbsp;<html:select
				property="app_source">
				<html:optionsCollection name="cardReqForm"
					property="app_source_options" />
			</html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
         <input type="button" name="zx" value="�������" class="Button" onclick="doPopCredit();"></td>
		</tr>


		<tr bgcolor="#FFFFFF">
			<TD align="left" class='dtPanel_left'>Ӫ����</TD>
			<TD class='dtPanel_left' colspan="2">
		       	&nbsp;&nbsp;Ӫ����������<html:text property="managerName" styleId="managerName" styleClass="Textfield" size="10" maxlength="10" />
		       	&nbsp;&nbsp;Ӫ���˱�ţ�<html:text property="managerId" styleId="managerId" styleClass="Textfield-READONLY"  size="10" maxlength="10" readonly="true"/>
		       	<div id="popup" style="position:absolute">
		       		<table id="name_table" bgcolor=#fffafa " border="0" cellspacing="0"	cellpadding="0" />
						<TBODY id="name_table_body"></TBODY>
					</table>
       			</div>			
 			<input type='button' class="Button" value="ѡ��Ӫ����"
				onClick="doManagerPop()" />
      	 	&nbsp; &nbsp;Ӫ�����ƶ��绰���� <html:text property="manager_mobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
      	 	</td>
		</tr>
		<tr>
       		<td width="16%" align="left" class="dtPanel_Left">��ע���</td>
      		<td colspan="3" scope="row" class="dtPanel_Main2" width="84%">&nbsp;
					<html:textarea property="oper_sug" cols="50" rows="3" styleClass="Textfield" />
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
       	<TD align = "left" class='dtPanel_left'>������</TD>
       	<TD class='dtPanel_left' colspan="2">
       	&nbsp;&nbsp;������������<html:text property="processer_name" styleId="processer_name" styleClass="Textfield" size="10" maxlength="10"/>
		&nbsp;&nbsp;�����������;<input
				name="exam_node_name" type="text" class="Textfield" size="20"
				maxlength="40"
				value="<%=(cardReqForm.getExam_node_no().equals("000000000") ? "����" : OrgMap.getOrgName(cardReqForm.getExam_node_no()))%>"
				readonly onclick="doPopOrg();"> <html:hidden property="exam_node_no" />
			<input type="button" name="xz2" value="ѡ�����" class="Button"
				onclick="doPopOrg();"></td>
		</tr>
	    <tr>
		<td width="16%" align="left" class="dtPanel_Left">�����˵绰</td>
		<td height="27" scope="row" class="dtPanel_Main2" colspan="2">
		���� 0<html:text property="processer_zoneno" onblur="onlyNum(this)" onkeyup="onlyNum(this)" styleClass="Textfield" size="3" maxlength="3"/> &nbsp; 
		�绰���� <html:text property="processer_phone" styleClass="Textfield" size="17" maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)"/>&nbsp; &nbsp; 
		�ƶ��绰���� <html:text property="processer_mobile" styleClass="Textfield"  size="11" maxlength="11" onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>
		</td> 
    </tr> 
    <tr>
		<td width="16%" align="left" class="dtPanel_Left">�ͻ���Դ</td>
		<td class="dtPanel_Main2">
		&nbsp;
		<html:select property="channel_id">
		<html:optionsCollection name="cardReqForm" property="channel_id_options"/>
		</html:select>
		</td>
		<td width="349" class="dtPanel_Main2">�����
		<html:select property="act_id">
		<html:optionsCollection name="cardReqForm" property="act_id_options"/>
		</html:select>
		</td>
	</tr>
		
	</table>   
<%=ViewUtil.getTitle("֤���ļ���")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0" >
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���֤���ļ�
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("pidfile","5083",cardReqForm.getPidfile())%>  
			<html:text property="otherpidfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����֤���ļ�
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("creditfile","5084",cardReqForm.getCreditfile())%>  
			<html:text property="othercreditfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����֤���ļ�
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("incomefile","5085",cardReqForm.getIncomefile())%>  
			<html:text property="otherincomefile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����֤���ļ�
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getCheckBox("jobidentifyfile","5086",cardReqForm.getJobidentifyfile())%>  
			<html:text property="otherjobidentifyfile" styleClass="Textfield" size="25" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		������ʽ
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<%=SingleDicMap.getRadio("assu_type","5007",cardReqForm.getAssu_type())%>  
		(���ڵ�����ʽʱ�����ڵ�����ʽ��������Ϣ�������·�������֤���ļ������ﲹ��)
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����֤���ļ�
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<html:text property="otheridentifyfile" styleClass="Textfield" size="100" maxlength="100"/>
		</td>		
	</tr>
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�������
		</td>
		<td colspan="3" class="dtPanel_Main2">&nbsp;
			<html:text property="assure_lmt" styleClass="Textfield" size="8" maxlength="8"/>Ԫ
		</td>		
	</tr>
</table>
<%=ViewUtil.getTitle("�����Ƽ�����")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    <tr bgcolor="#FFFFFF">
       	<td width="16%" align="left" class="dtPanel_Left">�����Ƽ���</td>
       	<td class="dtPanel_Main2">&nbsp;
       	�Ƽ����ͣ�
        <%=SingleDicMap.getRadio_WithFun("ref_type","5103",cardReqForm.getRef_type(),"clean_ref_no_id(this)")%>
        �Ƽ����룺
       	<html:text property="ref_info" styleClass="Textfield"  size="20" maxlength="20" onkeyup="checkRef_info(this)" onblur=" checkRef_info_length(this)"/>
       	 <INPUT type="hidden"  name="ref_no_id" value='<%=cardReqForm.getRef_no_id()%>'>
       	 
       	<input type='button'class="Button"  value="�Ƽ��˲�ѯ" onClick="doReferencePop()"/>
       	
       	</td>
    </tr>
</table>
<% if(CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
	<!--�޸ĸ���ҳ��-->			
	<%=ViewUtil.getTitle("������������")%>					
<%}%>

	<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"
<%if(!CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
 	style="display:none"
<%}%>	
	>

		<html:hidden property="seq_no" />
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��������</td>
			<td width="568" class="dtPanel_Main2">&nbsp;<html:text
				property="per_card_cname" styleClass="Textfield" size="20"
				maxlength="40" onblur="onlyName(this)" onkeyup="onlyName(this)" />
			&nbsp; ���� <html:text property="per_nation" styleClass="Textfield"
				size="3" maxlength="3" readonly="true" /> <SELECT
				onchange=attachCardNationality(this) name=select>
				<SCRIPT language="javascript">document.writeln(country_code);</SCRIPT>
			</SELECT></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�Ա�</td>
			<td width="500" class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_sex", "0024", cardReqForm.getPer_sex())%>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">ƴ����Ӣ������</td>
			<td class="dtPanel_Main2">&nbsp;�� <html:text property="per_last_name"
				styleClass="Textfield" size="10" maxlength="10"
				onblur="onlyEngToUpperCase(this)" onkeyup="onlyEngToUpperCase(this)" />
			�� <html:text property="per_first_name" styleClass="Textfield"
				size="16" maxlength="16" onblur="onlyEngToUpperCase(this)"
				onkeyup="onlyEngToUpperCase(this)" />&nbsp; �������� <html:text
				property="per_birth" styleClass="Textfield" size="8" maxlength="8"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" />(yyyymmdd)</td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">֤������</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_cert_type", "0026", cardReqForm.getPer_cert_type())%>
			������ <html:text property="per_cert_name" styleClass="Textfield"
				size="15" maxlength="20" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">֤������</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_cert_no"
				styleClass="Textfield" size="24" maxlength="24" onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"/></td>
		</tr>
		<%
			if(com.eis.util.CheckUtil.checkMemberPer(cardReqForm.getProd_kind()) == 1){
		%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">������Ա���</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_membernumber" styleClass="Textfield" size="19" maxlength="19"  onblur="javascript:this.value=this.value.toUpperCase()" onkeyup="javascript:this.value=this.value.toUpperCase()"></html:text>
			</td>
		</tr>
		<%} %>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��ϵ��ַ</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_addr"
				styleClass="Textfield" size="60" maxlength="80" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��ַ�ʱ�</td>
			<td class="dtPanel_Main2">&nbsp;<html:text property="per_post_code"
				styleClass="Textfield" size="6" maxlength="6"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> &nbsp;&nbsp;
			&nbsp;&nbsp; �������� <html:text property="per_email"
				styleClass="Textfield" size="30" maxlength="40" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">��ϵ�绰</td>
			<td class="dtPanel_Main2">&nbsp;���� 0<html:text property="per_zone_no"
				styleClass="Textfield" size="3" maxlength="3"
				onblur="onlyNum(this);" onkeyup="onlyNum(this);" /> &nbsp; &nbsp;
			�绰���� <html:text property="per_phone" styleClass="Textfield" size="17"
				maxlength="17" onblur="noChinese(this)" onkeyup="noChinese(this)" />
			&nbsp; �ƶ��绰���� <html:text property="per_mobile" styleClass="Textfield"
				size="11" maxlength="11" onblur="onlyNum(this);"
				onkeyup="onlyNum(this);" /></td>
		</tr>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�������˹�ϵ</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_rela", "0036", cardReqForm.getPer_rela())%>
			</td>
		</tr>
		
		<%
			if(com.eis.util.CheckUtil.needPerlayout(cardReqForm.getProd_kind())){
			String radio = com.eis.util.CheckUtil.radioNumber(cardReqForm.getProd_kind());
		%>
		<tr>
		<td width="16%" align="left" class="dtPanel_Left">���������־</td>
		<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_layout_flag",radio,cardReqForm.getPer_layout_flag())%>
		</td>
		</tr>	
		<%}%>
		
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">�쿨��ʽ</td>
			<td class="dtPanel_Main2">&nbsp;<%=SingleDicMap.getRadio("per_getcard_mode", "0047", cardReqForm.getPer_getcard_mode())%>
			</td>
		</tr>
	</table>
<%if(CheckUtil.allowAttach(cardReqForm.getProd_kind())) {%> 
<%=ViewUtil.getTitle("�����Ƽ�����")%>
<table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    <tr bgcolor="#FFFFFF">
       	<td width="16%" align="left" class="dtPanel_Left">�����Ƽ���</td>
       	<td class="dtPanel_Main2">&nbsp;
       	�Ƽ����ͣ�
        <%=SingleDicMap.getRadio_WithFun("per_ref_type","5103",cardReqForm.getPer_ref_type(),"clean_per_no_id(this)")%>
        �Ƽ����룺
       	<html:text property="per_ref_info" styleClass="Textfield"  size="20" maxlength="20" onkeyup="checkPer_ref_info(this)" onblur=" checkPRef_info_length(this)"/>
       	 <INPUT type="hidden"  name="per_ref_no_id" value='<%=cardReqForm.getPer_ref_no_id()%>'/>
       	<input type='button'class="Button"  value="�Ƽ��˲�ѯ" onClick="doReferencePop1()"/>
       	
       	</td>
    </tr>
</table> 
 <%}%>
	<logic:equal name="cardReqForm" property="feed_back_flag" value="1">
		<%=ViewUtil.getTitle("���������")%>
		<table align="center" width="98%" class="dtPanel_Line3" border="0"
			cellspacing="1" cellpadding="0">
			<tr>
				<td width="16%" align="left" class="dtPanel_Left">��ע</td>
				<td class="dtPanel_Main2">&nbsp;<%=cardReqForm.getMemo()%></td>
			</tr>

			<tr>
				<td width="16%" align="left" class="dtPanel_Left">�������</td>
				<td class="dtPanel_Main2">&nbsp;<html:select property="feedback_sug">
					<html:optionsCollection name="cardReqForm"
						property="feedback_sug_options" />
				</html:select></td>
			</tr>

			<tr>
				<td width="16%" align="left" class="dtPanel_Left">����˵��</td>
				<td class="dtPanel_Main2">&nbsp;<html:textarea
					property="feedback_exp" cols="50" rows="3" styleClass="Textfield" />
				</td>
			</tr>
		</table>
	</logic:equal>

	<!--�޸ĸ�������-->
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
			<auth:button name="update" id="modify_req" value="����" onClick="doSave()" />
			&nbsp;&nbsp;&nbsp; 
			<auth:button name="d" value="������" id="inputer_submit" onClick="feedbackError();"/>&nbsp;&nbsp;
			<auth:button name="Submit1" id="inputer_submit" value="�ύ����" onClick="doSend()" /> 
			&nbsp;&nbsp;&nbsp; 
			<input name="return" type="button" class="Button" value="����" onClick="window.location='CardReqNew.do?act=list';"></td>
			
		</tr>
	</table>

</html:form>


<%if(!CheckUtil.allowAttach(cardReqForm.getProd_kind())){%>
<!--����Ϊ��������-->
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
	<%=ViewUtil.getTitle("�����б�")%>
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">



					<td width="20%">�����ͻ�����</td>
					<td width="20%">�����ͻ�֤������</td>

					<td width="35%">�����ͻ�֤������</td>

					<td width="15%">�����ͻ��������˹�ϵ</td>

					<td>ѡ��</td>

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
						id="AttachCard_c" value="���Ӹ���" onClick="doAdd()" /> &nbsp; <auth:button
						name="edit" id="AttachCard_u" value="�޸ĸ���" onClick="doEdit()" />
					&nbsp; <auth:button name="delete" id="AttachCard_d" value="ɾ������"
						onClick="doDelete()" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<%} else {%>
<!--����Ϊ��������-->
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
	<%=ViewUtil.getTitle("�����б�")%>
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">



					<td width="20%">�����ͻ�����</td>
					<td width="20%">�����ͻ�֤������</td>

					<td width="35%">�����ͻ�֤������</td>

					<td width="15%">�����ͻ��������˹�ϵ</td>

					<td>ѡ��</td>

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
						id="AttachCard_c" value="���Ӹ���" onClick="doAdd()" /> &nbsp; <auth:button
						name="edit" id="AttachCard_u" value="�޸ĸ���" onClick="doEdit()" />
					&nbsp; <auth:button name="delete" id="AttachCard_d" value="ɾ������"
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
