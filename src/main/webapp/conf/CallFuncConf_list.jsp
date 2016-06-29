<%@page import="com.yly.conf.Callfuncconf"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>���ú�������</title>
<script language="javascript"> 
function doAdd(){ 
	//���� 
	window.location="Callfuncconf.do?act=c"; 
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
<html:form method="post" action="Callfuncconf.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("���ú�������")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>ҵ�����ͣ�
			<html:select property="operationType" styleClass="Select">
				<html:optionsCollection name="callfuncconfForm" property="operationTypecollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
  		  </td> 
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>���ú������</td>
			<td>ҵ������</td>
			<td>����</td>
			<td>��Ʒ����</td>
			<td>Ӧ������</td>
			<td>����1</td>						
			<td>����2</td>
			<td>����3</td>
			<td>����4</td>
			<td>����Ա</td>
			<td>¼������</td>
			<td>��ע</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Callfuncconf vo = (Callfuncconf) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getCallerId() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, String.valueOf(vo.getOperationType()))%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.MAUN_ID, vo.getManufacId()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId()) %></td>
			<td><%=vo.getApplyTypeId()!=null?ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getApplyTypeId()):"/"%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc1()) %></td>						
			<td><%=vo.getFunc2()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc2()) %></td>
			<td><%=vo.getFunc3()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc3()) %></td>		
			<td><%=vo.getFunc4()==null?"/":SingleDicMap.getDicItemVal(SingleDic.FUNCID, vo.getFunc4()) %></td>
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
			<input type="button" value="���ӵ��ú�������" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


