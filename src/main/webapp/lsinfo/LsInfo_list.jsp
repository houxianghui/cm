<%@page import="com.yly.ls.Lsinfo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��ˮ��¼</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doLsDown(){ 
	document.forms[0].act.value = "lsDown";
	document.forms[0].submit(); 
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Lsinfo.do">
<input type=hidden name=act value="list">
<html:hidden property="appNo"/>
<%=ViewUtil.getTitle("��ˮ��¼")%>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>��ˮ��</td>
			<td>���뵥��</td>
			<td>��ص���(���κ�)</td>
			<td>ҵ������</td>
			<td>SAM����</td>
			<td>SAMӡˢ����</td>
			<td>�����</td>						
			<td>������</td>
			<td>ԭSAM��</td>
			<td>ԭSAMӡˢ��</td>
			<td>¼��Ա</td>
			<td>¼��ʱ��</td>
			<td>��Ʒ���</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Lsinfo vo = (Lsinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getFlowNo() %></td>	
			<td><%=vo.getAppNo()%></td>			
			<td><%=vo.getFormNo()==null?"":vo.getFormNo()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType())) %></td>			
			<td><%=vo.getSamId()==null?"":vo.getSamId()%></td>
			<td><%=vo.getSamCSN()==null?"":vo.getSamCSN()%></td>					
			<td><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></td>
			<td><%=vo.getErrorCode()==null?"":SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(vo.getErrorCode()))%></td>
			<td><%=vo.getSamIdOld()==null?"":vo.getSamIdOld()%></td>	
			<td><%=vo.getSamCSNOld()==null?"":vo.getSamCSNOld()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId()) %></td>			
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


</html:form>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<input type="button" value="����" class="Button" onClick="history.back()"/>
					<input type="button" value="����" class="Button" onClick="doLsDown()"/>
					
				</td>
	    </tr> 
  </table> 
<p>&nbsp;</p>
</body>
</html>


