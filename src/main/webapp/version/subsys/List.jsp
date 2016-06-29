<%@page import="com.blue.version.subsysversion.SubSysVersion"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>��ϵͳ�汾ά��</title>
<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	});
	$(":input[name='add']").click(function(){
		$("#dialog").dialog("open");
		//location.href="SubSysVersion.do?act=new&versionId="+$(":input[name='versionId']").val();
	});
	$("#sub").click(function(){
		if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		//�ύ�� 
		document.forms[0].act.value='sub';	 
		document.forms[0].submit(); 
	});
	$("#dialog").dialog({
		autoOpen:false,
		height:300,
		width:500,
		modal:false,
		buttons:{
			"�ر�":function(){
				$(this).dialog("close");
			},
			"����":function(){
				if(!$(":input[name='sysName']").val() || $.trim($(":input[name='sysName']").val())==""){
					alert('��ѡ����ϵͳ');
					return;
				}
				
				if(!$("input[name='nextVersion']").val() || $.trim($("input[name='nextVersion']").val())==""){
					alert('����д�°汾��');
					return;
				}
			
				document.forms[1].submit();
			}
		}
	});
	$(":input[name='sysName']").change(function(){
		$(":input[name='preVersion']").empty();
		$.get('SubSysVersion.do?act=getPreVersion&sysName='+$(":input[name='sysName']").val(),function(data){
			$(":input[name='preVersion']").append(data);
		});
	});
	$.get('SubSysVersion.do?act=getPreVersion&sysName='+$(":input[name='sysName']").val(),function(data){
		$(":input[name='preVersion']").append(data);
	});
});

function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 

function doQuery() {  
	document.forms[0].act.value = "<%=request.getParameter("act") %>";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].id.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "<%=request.getParameter("act") %>";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 
function doReturn(){
	window.location.href="VersionHis.do?act=list"
}
</script>
</head>
<body>
<html:form method="post" action="SubSysVersion.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="id"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("��ϵͳ�汾ά��")%>
	
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;��ϵͳ</td>
			<td>&nbsp;ǰһ�汾��</td>
			<td>�°汾��</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			SubSysVersion vo = (SubSysVersion) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getSysName()%></td>
			<td><%=vo.getPreVersion() %></td>
			<td><%=vo.getNextVersion()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%
//������ҳ��ע 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<%String flag = (String)request.getAttribute("NOACT");if(CheckUtil.isEmptry(flag)){ %>
			<auth:button value="����" onClick="" id="release" name="add"></auth:button>
			<auth:button value="�޸�" onClick="doEdit()" id="release" name="edit"></auth:button>
			<auth:button value="ɾ��" onClick="doDelete()" id="release" name="delete"></auth:button>
			<input type="button" value="����" class="Button" onclick="doReturn()"/>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>
<div id="dialog">
<script type="text/javascript">
$(function(){
	$("div :input").css("width","188pt");
	$("div :button").css("width","");
	
	$("div :text").attr("class","Textfield");
	$("div select").attr("class","Select");
});
</script>
	<html:form method="post" action="SubSysVersion.do"> 
	<html:hidden property="act" value="add"/>
	<html:hidden property="versionId"/>
	<%=ViewUtil.getTitle("��ϵͳ�汾ά��")%> 
	 
	    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	        
			<tr bgcolor="#FFFFFF"> 
	          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ϵͳ��</td> 
	         <td align="left" class="dtPanel_Main2">&nbsp; 
	         <html:select property="sysName">
	         	<html:optionsCollection name="subSysVersionForm" property="subSysCollection"/>
	         </html:select>
	         </td>
	       </tr> 
	       <tr bgcolor="#FFFFFF"> 
	          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ǰһ�汾�ţ�</td> 
	         <td align="left" class="dtPanel_Main2">&nbsp; 
	         <html:select property="preVersion"/> 
	         </td>
	       </tr>
	        <tr bgcolor="#FFFFFF"> 
	          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �°汾�ţ�</td> 
	         <td align="left" class="dtPanel_Main2">&nbsp; 
	         <html:text property="nextVersion"/> 
	         </td>
	       </tr>
	  </table> 
	</html:form> 
</div>
<p>&nbsp;</p>
</body>
</html>