
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
<title>发行加工单</title>
<script language="javascript"> 

function turnPage( pagenm ) {   
    	document.forms[0].act.value = "pagelist";  
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
	$.get("Mwsissuetb.do?act=E&operationType=<%=mwsissuetbForm.getOperationType()%>&applyAttr=<%=mwsissuetbForm.getApplyAttr()%>&prodId=<%=mwsissuetbForm.getProdId()%>&manufacId=<%=mwsissuetbForm.getManufacId()%>&phiTypeId=<%=mwsissuetbForm.getPhiTypeId()%>&binFileVer=<%=mwsissuetbForm.getBinFileVer()%>",function(result){
		var json = $.parseJSON(result);
		if(json.error!=null){
			$("#detectSign"+json.flowNo).text("次品");
			$("#detect"+json.flowNo).hide();	
			alert(json.error);
		}else{
	 		$("#detectSign"+json.flowNo).text("良品");
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
<input type=hidden name=act value="pagelist">
<html:hidden property="operationType"/>
<html:hidden property="formNo"/>
<html:hidden property="manufacId"/>
<html:hidden property="prodId"/>
<html:hidden property="applyAttr"/>
<html:hidden property="phiTypeId"/>
<%=ViewUtil.getTitle("发行加工单")%>
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		单据编号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormNo()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		任务明细号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getTaskCtrlNo()%>
		</td>
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		制单时间:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getFormTime()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		加工单序号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWsSnr()%>
		</td>
	</tr>		

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		产品类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, String.valueOf(mwsissuetbForm.getProdId()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		密钥类型:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(mwsissuetbForm.getKeyType()))%> 
		</td>		
	</tr>	   

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		<%if(mwsissuetbForm.getApplyAttr().equals("301")||mwsissuetbForm.getApplyAttr().equals("302")||mwsissuetbForm.getApplyAttr().equals("201")||mwsissuetbForm.getApplyAttr().equals("202")){%>
		模块版本:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, mwsissuetbForm.getBinFileVer())%> 
		<%}else{%>
		产品通信速率:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, String.valueOf(mwsissuetbForm.getPhiTypeId()))%> 
		<%}%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		产品应用属性:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(mwsissuetbForm.getApplyAttr()))%> 
		</td>		
	</tr>	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		充值密钥使用权限不需要认证:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getAuthSign()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		是否装载维护密钥二:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, String.valueOf(mwsissuetbForm.getW2Sign()))%> 
		</td>		
	</tr>

	<%if(mwsissuetbForm.getOperationType()!=21  && mwsissuetbForm.getOperationType()!=24  && mwsissuetbForm.getOperationType()!=43  && mwsissuetbForm.getOperationType()!=53){%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			厂商代码:
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(mwsissuetbForm.getManufacId())) %>				
			</td>
			<td width="16%" align="left" class="dtPanel_Left">
			业务类型:
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<%=mwsissuetbForm.getBatchId()%>&nbsp;&nbsp;
			<%if(mwsissuetbForm.getProdId().equals("4")){%>
			<%=ViewUtil.must()%>请选择配件厂商:
			<html:select property="partManufacId" styleClass="Select">
				<html:optionsCollection name="mwsissuetbForm" property="partManufacIdCollection"/>
			</html:select>
			<%}%>
			</td>		
		</tr>
	<%}else{%>
		<tr>
			<td width="16%" align="left" class="dtPanel_Left">
			厂商代码:
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(mwsissuetbForm.getManufacId())) %>							
			</td>
			<td width="16%" align="left" class="dtPanel_Left">
			批次号:
			</td>
			<td class="dtPanel_Main2">&nbsp;
			<a href="StoApp.do?act=r&formNo=<%=mwsissuetbForm.getBatchId()%>"><%=mwsissuetbForm.getBatchId()%></a>&nbsp;&nbsp;印刷卡号范围:<%=mwsissuetbForm.getPressCardScale()%>
			&nbsp;&nbsp;
			<%=ViewUtil.must()%>主控密钥:
			<html:select property="authkey" styleClass="Select" >
				<html:optionsCollection name="mwsissuetbForm" property="authkeycollection"/>
			</html:select>
			<html:hidden property="authkey"/>
			</td>		
		</tr>
	<%}%>	
		
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		加工数量:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getWorkSheetAmt()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		起始SAM号--结束SAM号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamIdBegin()%>--<%=mwsissuetbForm.getSamIdEnd()%>
		</td>		
	</tr>

	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		SAM卡号:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getSamId()%>
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		已发行数量:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=mwsissuetbForm.getIssueDoneAmt()%>
		</td>		
	</tr>	
	
	<tr>
		<td width="16%" align="left" class="dtPanel_Left">
		单据状态:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=SingleDicMap.getDicItemVal(SingleDic.FORMTYPE, String.valueOf(mwsissuetbForm.getFormState()))%> 
		</td>
		<td width="16%" align="left" class="dtPanel_Left">
		发行操作员:
		</td>
		<td class="dtPanel_Main2">&nbsp;
		<%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, mwsissuetbForm.getIssueOperID()) %>
		</td>		
	</tr>


</table>
 <%=ViewUtil.getTitle("发行流水信息")%> 		
	<table width="98%" border="0" cellspacing="1" align="center"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Line">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01">
					<td width="10%">流水号</td>
					<td width="10%">SAM卡号</td>
					<td width="10%">SAM印刷卡号</td>				
					<td width="10%">检测结果</td>	
					<td width="10%">发行员</td>	
					<td width="10%">发行时间</td>			
					<td width="10%">错误码</td>				
				</tr>
				<%List list = pageResultLsInfo.getList();

if (list != null) {
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	Lsinfo vo = (Lsinfo)iter.next();%>
				<tr align="center" class="dtPanel_Main2" onclick="_clickTr( this )">
				<%if(vo.getErrorCode()!=null && vo.getErrorCode()!=0000){%>
					<td bgcolor="#FF2400"><%=vo.getFlowNo()%></td>	
					<td bgcolor="#FF2400"><%=vo.getSamId()%></td>
					<td bgcolor="#FF2400"><%=vo.getSamCSN()%></td>
					<td bgcolor="#FF2400"><div id="detectSign<%=vo.getFlowNo()%>"></div><div id="detect<%=vo.getFlowNo()%>"><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></div></td>
					<td bgcolor="#FF2400"><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td bgcolor="#FF2400"><%=vo.getCurrDate()%></td>		
					<td bgcolor="#FF2400"><%=SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(vo.getErrorCode()))%></td>	
				<%}else if(vo.getSamId().startsWith("81")){ %>
					<td bgcolor="#CFB53B"><%=vo.getFlowNo()%></td>	
					<td bgcolor="#CFB53B"><%=vo.getSamId()%></td>
					<td bgcolor="#CFB53B"><%=vo.getSamCSN()%></td>
					<td bgcolor="#CFB53B"><div id="detectSign<%=vo.getFlowNo()%>"></div><div id="detect<%=vo.getFlowNo()%>"><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></div></td>
					<td bgcolor="#CFB53B"><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td bgcolor="#CFB53B"><%=vo.getCurrDate()%></td>		
					<td bgcolor="#CFB53B"></td>	
				<%}else{ %>
					<td><%=vo.getFlowNo()%></td>	
					<td><%=vo.getSamId()%></td>
					<td><%=vo.getSamCSN()%></td>
					<td><div id="detectSign<%=vo.getFlowNo()%>"></div><div id="detect<%=vo.getFlowNo()%>"><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></div></td>
					<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, String.valueOf(vo.getOperId())) %></td>
					<td><%=vo.getCurrDate()%></td>		
					<td></td>	
				<%}%>
				</tr>
				<%}
}%>
			</table>
			</td>
		</tr>

	</table>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
  <link rel="stylesheet" href="jqueryui/style.css">
  <style>
  .ui-progressbar {
    position: relative;
    width:98%; 
   }
  .progress-label {
    position: absolute;
    left: 50%;
    top: 4px;
    font-weight: bold;
    text-shadow: 1px 1px 0 #fff;
    width:98%; 
  }
  </style>
  <script>
  $(function() {
    var progressbar = $( "#progressbar" ),
      progressLabel = $( ".progress-label" );
 
    progressbar.progressbar({
      value: false,
      change: function() {
        progressLabel.text( progressbar.progressbar( "value" ) + "%" );
      },
      complete: function() {
        progressLabel.text( "完成！" );
      }
    });
 
    function progress() {
      var val=<%=mwsissuetbForm.getIssueDoneAmt()*100/mwsissuetbForm.getWorkSheetAmt()%>;
      progressbar.progressbar( "value", val );
    }
 
    setTimeout( progress, 0 );
  });
  </script>
</head>
<body>
<div id="progressbar"><div class="progress-label">加载...</div></div>
</body>
	
	<%
//产生翻页脚注 
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
			<%if(mwsissuetbForm.getApplyAttr().equals("302") || mwsissuetbForm.getApplyAttr().equals("202")||mwsissuetbForm.getApplyAttr().equals("301") || mwsissuetbForm.getApplyAttr().equals("201")){%>
			<input type="button" id="down1" value="下载" class="Button" onClick="doDown1()" />-->
			<%} %>
			<%if(mwsissuetbForm.getFormState()!=3){%>
			<input type="button" id="issue" value="发行" class="Button" onClick="doIssue()"/>-->
			<%}else{ %>
			<input type="button" value="发行完成" class="Button" onClick="doIssueDone()"/>-->
			<%} %>
			<%if(mwsissuetbForm.getApplyAttr().equals("302") || mwsissuetbForm.getApplyAttr().equals("202")){%>
			<input type="button"  id="down2" value="下载" class="Button" onClick="doDown2()" />-->
			<%} %>
			<input type="button"  id="exam" value="检测" class="Button" onClick="doExam()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


