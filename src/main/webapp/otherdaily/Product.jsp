<%@page import="com.blue.otherdaily.OtherDailyForm"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="otherDailyForm" class="com.blue.otherdaily.OtherDailyForm"/>
<html> 
 <head>
<title>��Ʒ��ʱ</title> 
 
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
		var cost = $("input[name='cost']").val();
		if($.trim(cost) == ''){
			alert("����д��ʱ");
			return;
		}
		if(!$.isNumeric(cost)){
			alert("��ʱ����������");
			return;
		}
		document.forms[0].submit();
	}).attr("class","Button");
	
	$.get("ModuleDef.do?act=getMyModule&selected="+$("#selected").val(),function(data){
		$("#option").append(data);
	});
});

</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="OtherDaily.do"> 
<input type=hidden name=act value="add"> 
<html:hidden property="type" value="chanPin"/>
<%=ViewUtil.getTitle("��д��Ʒ��ʱ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�����<nl>*</nl>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workDate" /> 
         </td>
       </tr> 

       <tr bgcolor="#FFFFFF" id="module"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;ģ��<nl>*</nl>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <select name="info" id="option"></select>
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
 

