<%@page import="com.yly.info.Biunitinfotb"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
tr{
	background-color:#F7F7F7;
	COLOR:#333333;
	FONT-SIZE:9pt;
	FOTN-FAMILY:����,verdana,Arial, Helvetica;
	height:23px;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>ָ�ɷ��е�λ</title>
<script type="text/javascript">
function addIssue(){
	var tbName = "issue";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<input name='issue_"+rowID+"' id='issue_"+rowID+"' type='text' class='TextField' size='9' maxLength='9'  value=''/>";
	innerHtml2 = "<input name='issue_"+rowID+"_Amt' id='issue_"+rowID+"_Amt' type='text' class='TextField' size='10' maxLength='10' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='0'/>";
	innerHtml3 = "<input name='issue_"+rowID+"_Ctp' id='issue_"+rowID+"_Ctp' type='text' class='TextField' size='2' maxLength='2' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='1'/>";
	innerHtml4 = "<input name='issue_"+rowID+"_Ptp' id='issue_"+rowID+"_Ptp' type='text' class='TextField' size='2' maxLength='2' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='1'/>";
	innerHtml5 = "<input name='issue_"+rowID+"_Pri' id='issue_"+rowID+"_Pri' type='text' class='TextField' size='10' maxLength='10' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='0'/>";
	addRow(tbName,new Array(innerHtml1,innerHtml2,innerHtml3,innerHtml4,innerHtml5));
}
function doSave(){
	document.forms[0].submit();
}
function doUnitCode(){
	openWin("Biunitinfo.do?act=popList&leadStore="+document.forms[0].unitId.value+"","unitPop");
}
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<html:form method="post" action="Issuetaskctrl.do">
	<input type=hidden name=act value="batchAdd">
		<html:hidden property="taskAmt" />
		<html:hidden property="taskNo" />
		<html:hidden property="appNo" />
		<html:hidden property="prodId" />
		<html:hidden property="keyType" />		
		<html:hidden property="phiTypeId" />
		<html:hidden property="appTypeId" />
		<html:hidden property="unitId" />
		<html:hidden property="operationType" />
		<html:hidden property="samIdBegin" />
		<html:hidden property="OAappNo" />
  		<input name='txtTRLastIndex' type='hidden' id='issue.index' value="1" />
	<table id="units" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr  class="dtPanel_Top01">
		<td align="left">
		һ�����뵥λ���:<%=request.getParameter("unitId")%><input type="button" name="company" value="���������ѯ" class="Button"onclick="doUnitCode()">
		</td>
		<td align="left">
		ҵ������Ϊ:<%=SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, request.getParameter("operationType"))%>
		</td>
		</tr>
		<tr align="center" class="dtPanel_Top01">
		<td align="left">
		ͨ������Ϊ:<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, request.getParameter("phiTypeId"))%>
		</td>
		<td align="left">
		Ӧ������Ϊ:<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, request.getParameter("appTypeId")) %></td>
		
		</td>
		</tr>
		<tr align="center" class="dtPanel_Top01">
		<td align="left">
		��������: <%=request.getParameter("taskAmt")%>
		</td>
		<%
		String aa=String.valueOf(request.getParameter("samIdBegin"));
		if(!CheckUtil.isEmptry(aa)){%>
		<td align="left">
		���п���:<%=aa%>
		</td>
		<%} %>
		</tr>

		</table>
		<br>
		<table id="issue" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">���</td><td width="20%">��λ���ƴ���<input type="button" class="Button" onclick="addIssue()" value="��ӷ��е�λ�������Ϣ"></button></td><td width="5%">��������</td><td width="10%">���÷�ʽ(1�ɹ���2��ʧ���졢3��������)</td><td width="10%">֧����ʽ(1����2Ѻ����á�3���)</td><td width="10%">����(��λԪ)</td><td width="5%">����</td>
			</tr>
		</table>
</html:form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" class="Button" onclick="doSave()" value="����"></button>
			<input type="button" class="Button" onclick="window.close()" value="����"></button>
			</td>
		</tr>
	</table>
</body>
</html>