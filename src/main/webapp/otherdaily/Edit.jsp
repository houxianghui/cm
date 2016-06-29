<%@page import="com.blue.otherdaily.OtherDailyForm"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="otherDailyForm" class="com.blue.otherdaily.OtherDailyForm"/>
<html> 
 <head>
<title>������ʱ</title> 
 
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/adjust.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='text']").attr("class","Textfield");
	$("input[name*='Date']").attr("readonly","true").click(function(){
		new Calendar().show(this);
	});
	$("#save").click(function(){
		if($(":input[name='workDate']").val() == ''){
			alert("��ѡ�������");
			return;
		}
		if($(":input[name='type']").val() == "shouQian"){
			if($.trim($(":input[name='info']").val())==''){
				alert("��ʱ����Ϊ��ǰʱ�ͻ�����Ϊ������");
				return;
			}
		}
		var cost = $("input[name='cost']").val();
		if($.trim(cost) == ''){
			alert("����д��ʱ");
			return;
		}
		if(!$.isNumeric(cost)){
			alert("��ʱ����������");
			return;
		}
		
		if($("input[name='act']").val() == 'init'){
			document.forms[0].act.value="add";
			document.forms[0].submit();
		}else{
			document.forms[0].act.value="update";
			document.forms[0].submit();
		}
	}).attr("class","Button");
	
	checkHide();
	$(":input[name='type']").change(function(){
		checkHide();
		
	});
});
function checkHide(){

	if($(":input[name='type']").val()=="chanPin"){
		$.get("ModuleDef.do?act=getMyModule&selected="+$("#selected").val(),function(data){
			$("#option").append(data);
		});
		$("#module").show();
	}else{
		$("#option").empty();
		$("#module").hide();
	}
}
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="OtherDaily.do"> 
<input type=hidden name=act value="<%=request.getAttribute("act") %>"> 
<html:hidden property="id"/>
<%=ViewUtil.getTitle("��д������ʱ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�����<nl>*</nl>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workDate" /> 
         </td>
       </tr> 
		
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;��ʱ����<nl>*</nl>��</td>
			<td align="left" class="dtPanel_Main2" nowrap="nowrap">&nbsp;
				<html:select property="type">
					<html:optionsCollection name="otherDailyForm" property="typeCollection"/>
				</html:select>
				
			</td>
			
		</tr>

       <tr bgcolor="#FFFFFF" id="module"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ģ��<nl>*</nl>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <select name="info" id="option"></select>
         <input id="selected" value="<%=(String)request.getAttribute("info")%>" type="hidden">
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��ʱ<nl>*</nl>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="cost" /> 
         </td>
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;����˵����</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="memo" /> 
         </td>
       </tr> 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" id="save" value="����"/>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

