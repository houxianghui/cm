<%@page import="com.yly.para.Applytypeinfo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>Ӧ����������</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
	window.location="Applytypeinfo.do?act=c"; 
} 

function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Applytypeinfo.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("Ӧ����������")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			Ӧ������:			
			<html:text property="applyTypeId" styleClass="Textfield" size="6" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>Ӧ�����ͱ��</td>
			<td>Ӧ����������</td>
			<td>Pki����</td>
			<td>ά2����</td>
			<td>ά2��֤</td>
			<td>֧�ֻ�ͨ</td>						
			<td>��ֵ��Կʹ��Ȩ�޲���Ҫ��֤</td>
			<td>������Կ�ⲿ��֤��ԿΪȫ��</td>
			<td>SAM��Ƭ��Ʒ�����</td>
			<td>����Ա</td>
			<td>¼������</td>
			<td>��ע</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Applytypeinfo vo = (Applytypeinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getApplyTypeId() %></td>	
			<td><%=vo.getApplyIdDesc()%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsPki()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsV2()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsV2Sign()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsHLCard()) %></td>						
			<td><%=vo.getIsIsamSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsIsamSign()) %></td>
			<td><%=vo.getIsIsamTestAllO()==null?"":SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsIsamTestAllO()) %></td>
			<td><%=vo.getProdClassId()==null?"":vo.getProdClassId()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=vo.getRemarks()%></td>				
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
			<input type="button" value="����Ӧ������" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


