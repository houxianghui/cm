<%@page import="com.yly.ls.Lsinfo"%>
<%@ page import="com.yly.issue.Mwsissuetb"%>
<%@ page  contentType="text/html; charset=GBK" %>
<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="mwsissuetbForm" scope="request"  class="com.yly.issue.MWsIssuetbForm" />
<jsp:useBean id="pageResultLsInfo" scope="request" class="com.eis.base.PageObject" />


<html>

<%int maxPage = 1;
if (pageResultLsInfo != null)
	maxPage = pageResultLsInfo.getTotalPage();
%>
<head>
<title>���мӹ���</title>
<script language="javascript"> 

function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
}
function doIssue(){ 
    document.getElementById('issue').style.color="red"; 
	document.forms[0].act.value = "issue";
	document.forms[0].submit();
	
} 
function doIssueDone(){ 
	document.forms[0].act.value = "closePort";
	document.forms[0].submit();
	
} 
function doExam(){ 
    document.getElementById('exam').style.color="red"; 
	$.get("Mwsissuetb.do?act=E&operationType=<%=mwsissuetbForm.getOperationType()%>&applyAttr=<%=mwsissuetbForm.getApplyAttr()%>&prodId=<%=mwsissuetbForm.getProdId()%>&manufacId=<%=mwsissuetbForm.getManufacId()%>",function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			alert(json.error);
		}else{
	 		$("#detectSign"+json.flowNo).text("��Ʒ");
			$("#detect"+json.flowNo).hide();	
			alert(json.msg);
		}
		return;
	});
	
} 
function doDown1() {  
    document.getElementById('down1').style.color="red"; 
	$.get("Mwsissuetb.do?act=down",function(result){
 		return;
	});
} 
function doDown2() {  
    document.getElementById('down2').style.color="red"; 
	$.get("Mwsissuetb.do?act=down",function(result){
 		return;
	});
} 


</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Mwsissuetb.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery> 
<input type=hidden name=operationType> 
<html:hidden property="formNo"/>
<html:hidden property="manufacId"/>
<html:hidden property="prodId"/>
<html:hidden property="applyAttr"/>

<%=ViewUtil.getTitle("���мӹ���")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���ݱ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormNo()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		������ϸ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getTaskCtrlNo()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�Ƶ�ʱ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormTime()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		�ӹ������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWsSnr()%>
		</td>
	</tr>		

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		��Ʒ����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(mwsissuetbForm.getProdId()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		��Կ����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(mwsissuetbForm.getKeyType()))%> 
		</td>		
	</tr>	   

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%if(mwsissuetbForm.getProdId().equals("4")){%>
		ģ��汾:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, mwsissuetbForm.getBinFileVer())%> 
		<%}else{%>
		��Ʒͨ������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, String.valueOf(mwsissuetbForm.getPhiTypeId()))%> 
		<%}%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		��ƷӦ������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(mwsissuetbForm.getApplyAttr()))%> 
		</td>		
	</tr>	


	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		isam��֤��ʽ:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getAuthSign()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		�Ƿ�װ��ά����Կ��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getW2Sign()))%> 
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		���̴���:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(mwsissuetbForm.getManufacId())) %>							
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		���κ�:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<a href="StoApp.do?act=r&formNo=<%=mwsissuetbForm.getBatchId()%>"><%=mwsissuetbForm.getBatchId()%>&nbsp;&nbsp;ӡˢ���ŷ�Χ:<%=mwsissuetbForm.getPressCardScale()%>
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		�ӹ�����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWorkSheetAmt()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		��ʼSAM��--����SAM��:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamIdBegin()%>--<%=mwsissuetbForm.getSamIdEnd()%>
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM����:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamId()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		�ѷ�������:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getIssueDoneAmt()%>
		</td>		
	</tr>	
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		����״̬:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, String.valueOf(mwsissuetbForm.getFormState()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		���в���Ա:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, mwsissuetbForm.getIssueOperID()) %>
		</td>		
	</tr>


</table>
 <%=ViewUtil.getTitle("������ˮ��Ϣ")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">��ˮ��</td>
					<td width="10%">SAM����</td>
					<td width="10%">SAMӡˢ����</td>
					<td width="10%">�����</td>	
					<td width="10%">������</td>						
					<td width="10%">ԭSAM��</td>	
					<td width="10%">ԭSAMӡˢ��</td>
					<td width="10%">����Ա</td>	
					<td width="10%">����ʱ��</td>					
				</tr>
				<%List list = pageResultLsInfo.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Lsinfo vo = (Lsinfo)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
					<td><%=vo.getFlowNo()%></td>	
					<td><%=vo.getSamId()%></td>
					<td><%=vo.getSamCSN()%></td>
					<td><div id="detectSign<%=vo.getFlowNo()%>"></div><div id="detect<%=vo.getFlowNo()%>"><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></div></td>
					<td><%=vo.getErrorCode()==null?"":SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(vo.getErrorCode()))%></td>
					<td><%=vo.getSamIdOld()%></td>
					<td><%=vo.getSamCSNOld()%></td>			
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td><%=vo.getCurrDate()%></td>		
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>
	<%
//������ҳ��ע 
if (pageResultLsInfo != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td nowrap class="dtPanel_Pager"><%=pageResultLsInfo.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<%if(mwsissuetbForm.getProdId().equals("4")){%>
			<input type="button" id="down1" value="����" class="Button" onClick="doDown1()" />-->
			<%} %>
			<%if(mwsissuetbForm.getFormState()!=3){%>
			<input type="button" id="issue" value="����" class="Button" onClick="doIssue()"/>-->
			<%}else{ %>
			<input type="button" value="�������" class="Button" onClick="doIssueDone()"/>-->
			<%} %>
			<%if(mwsissuetbForm.getProdId().equals("4") && mwsissuetbForm.getApplyAttr().equals("302")){%>
			<input type="button"  id="down2" value="����" class="Button" onClick="doDown2()" />-->
			<%} %>
			<input type="button"  id="exam" value="���" class="Button" onClick="doExam()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


