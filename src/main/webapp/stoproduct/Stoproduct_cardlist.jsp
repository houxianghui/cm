<%@page import="com.yly.exstore.Stoproduct"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="stoproductForm" scope="request"  class="com.yly.exstore.StoproductForm" />

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��Ʒ�⿨Ƭ��ѯ</title>
<script language="javascript"> 

function doQuery() {  
	document.forms[0].act.value = "cardlist";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "cardlist";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doCardDown(){ 
	document.forms[0].act.value = "cardDown";
	document.forms[0].submit(); 
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Stoproduct.do">
<input type=hidden name=act value="cardlist">
<input type=hidden name=requery>
<%=ViewUtil.getTitle("����Ʒ��ѯ")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
         <td>
			���п���:			
			��<html:text property="samId_min" styleClass="Textfield" size="12" />
			��<html:text property="samId_max" styleClass="Textfield" size="12" />
			����:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="manufacIdCollection"/>
			</html:select>
			��Ʒ:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="prodIdCollection"/>
			</html:select>
			��Ʒ״̬:
			<html:select property="wkState" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="wkStatecollection"/>
			</html:select>	
			���״̬:
			<html:select property="IOState" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="IOStatecollection"/>
			</html:select>			
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
		</td>
       </tr> 
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>SAM���� </td>
			<td>SAMӡˢ���� </td>
			<td>OA�����</td>
			<td>��Ʒ����  </td>
			<td>��ƷӦ������</td>
			<td>��Կ����  </td>
			<td>���̴���</td>
			<td>������κ�  </td>
			<td>��Ʒ״̬ </td>
			<td>���ؿ�״̬</td>
			<td>���뵥λ </td>
			<td>����ʱ�� </td>
			<td>����� </td>
			<td>��ˮ���� </td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoproduct vo = (Stoproduct) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><a href="Stoproduct.do?act=r&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>"><%=vo.getSamId()%></a></td>	
			<td><%=vo.getSamCSN()%></td>		
			<td><%=vo.getOAappNo()%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId()))%> </td>
			<td><%=vo.getKeyType()==null||vo.getKeyType()==0?"":SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%> </td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(vo.getManufacId())) %>	</td>
			<td><%=vo.getBatchId()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(vo.getWkState()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(vo.getIOState()))%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>
			<td><%=vo.getIssueTime()==null?"":vo.getIssueTime()%></td>		
			<td><%=vo.getDetectSign()==null?"δ���":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN,String.valueOf(vo.getDetectSign()))%></td>		
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>">��ˮ</a></td>	
			
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
			<input type="button" value="����" class="Button" onClick="doCardDown()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


