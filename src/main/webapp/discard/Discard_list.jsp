<%@page import="com.yly.discard.Disproduct"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="disproductForm" scope="request"  class="com.yly.discard.DisproductForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>���ϼ�¼�б�</title>
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


</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Disproduct.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery > 
<html:hidden property="OAappNo"/>
<%=ViewUtil.getTitle("���ϼ�¼�б�")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
 			sam���Ŷ�:	
			��<html:text property="samId_min" styleClass="Textfield" size="12" />
			��<html:text property="samId_max" styleClass="Textfield" size="12" />
			</td>
		</tr>
		<tr>
			<td>
			��Ʒ����:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="prodIdcollection"/>
			</html:select>
			ͨ������:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="phiTypeIdcollection"/>
			</html:select>
			Ӧ������:
			<html:select property="appTypeId" styleClass="Select">
				<html:optionsCollection name="disproductForm" property="appTypeIdcollection"/>
			</html:select>						
			<input type="button" value="����" class="Button" onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>SAM����</td>
			<td>OA�����</td>
			<td>���뵥λ</td>
			<td>��Ʒ����</td>
			<td>��Ʒͨ������</td>
			<td>��ƷӦ������</td>
			<td>��Կ����</td>
			<td>��֤��ʽ</td>
			<td>����ʱ��</td>
			<td>����ʱ��</td>
			<td>��������</td>
			<td>�����</td>
			<td>��Ʒ״̬</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Disproduct vo = (Disproduct) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			 
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>&operationType=71"><%=vo.getSamId() %></a></td>	
			<td><%=vo.getOAappNo()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getAppTypeId()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getAuthSign().toString())%></td>
			<td><%=vo.getIssueTime() %></td>	
			<td><%=vo.getWkStateChgDate()%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE, String.valueOf(vo.getWkState()))%></td>						
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

	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


