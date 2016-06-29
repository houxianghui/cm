<%@page import="com.blue.version.versionhis.VersionHisVO"%>
<%@page import="com.blue.enums.YesOrNo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<title>��ѡ�汾ά��</title>

<script language="javascript"> 
$(function(){
	
	$("input[name*=Date]").attr("readonly","true").click(function(){
		
		new Calendar().show(this);
	});
	$("#sub").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		//�ύ�� 
		location.href="SubSysVersion.do?act=list&versionId="+document.forms[0].versionId.value;
	}).attr("class","Button");
	$("#version").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		document.forms[0].act.value='tabs';	 
		document.forms[0].submit(); 
	});
	$("#release").click(function(){
		if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
			alert('����ѡ���¼'); 
			return; 
		} 
		//�ύ�� 
		document.forms[0].act.value='view';	 
		document.forms[0].submit(); 
	});
	$("#his").click(function(){
		window.location.href="VersionHis.do?act=display";
	})

});
function doUpload(){
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="ProjectFiles.do?act=list&versionId="+document.forms[0].versionId.value;
}
function doAdd(){
	location.href="VersionHis.do?act=new";
}
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
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
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
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
function detail() { 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].versionId.value == null ||document.forms[0].versionId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 	window.location.href="VersionHis.do?act=view&versionId="+document.forms[0].versionId.value;
} 
 
function doQuery() {  
	document.forms[0].act.value = "<%=request.getParameter("act") %>";
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].versionId.value=id_var; 
} 
 
function turnPage( pagenm ) {   
   	document.forms[0].act.value = "<%=request.getParameter("act") %>";  
   	document.forms[0].pageNO.value = pagenm;     
   	document.forms[0].submit(); 
} 

</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="VersionHis.do">
<input type="hidden" name="act" value="<%=request.getParameter("act") %>"/>
<html:hidden property="versionId"/>
<%=ViewUtil.getTitle("��ѡ�汾ά��")%>
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			��ѡ�汾�ţ�
			<html:text property="versionId_f" styleClass="Textfield" size="10"></html:text>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;��ѡ�汾��</td>
			<td>&nbsp;Ԥ�Ʒ�������</td>
			<td>�Ƿ��ѷ���</td>
			<td>ʵ�ʷ�������</td>
			<td>������Ա</td>
			<td>��Ŀ��</td>
			<td>��ϵͳ��</td>
			<td>�汾����</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	int i = 0;
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			VersionHisVO vo = (VersionHisVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getVersionId()%></td>
			<td><%=vo.getPlanReleaseDate() %></td>
			<td><%=YesOrNo.valueOf(vo.getIsReleased()).getDesc()%></td>
			<td><%=vo.getReleaseDate()%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getInputUser())%></td>
			<td><%=vo.getProjectCount()%></td>
			<td><%=vo.getSubSysCount()%></td>
			<td><%=vo.getMemo()%></td>
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getVersionId()%>')">
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
			<input type="button" value="�汾��ʷ����" class="Button" id="his"/>
			<input type="button" value="������Ϣ" class="Button" id="release"/>
			<auth:button value="����" onClick="doAdd()" id="release" name="add"></auth:button>
			<auth:button value="�޸�" onClick="doEdit()" id="release" name="edit"></auth:button>
			<auth:button value="ɾ��" onClick="doDelete()" id="release" name="delete"></auth:button>
			<auth:button value="����ά��" onClick="doUpload()" id="release" name="upload"></auth:button>
			<input type="button" value="��ϵͳά��" id="sub"/>
			<input type="button" value="�汾������Ϣ¼��" id="version" class="Button"/>
			<%} %>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>