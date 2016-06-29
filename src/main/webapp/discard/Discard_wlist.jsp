<%@page import="com.yly.exstore.Stoproduct"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>待报废记录列表</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "ql";
	document.forms[0].submit(); 
}

function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 


function checkAll(field) //全选
{
	var checkflag = "false";
	if (checkflag == "false") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "false")
			{ document.forms[0].cx.checked = true;}
		
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = true;
			}
			checkflag = "true";
			return ;
		}	
	}
} 
function cancelCheckAll(field)//全部取消
{
	var checkflag = "true";
	if (checkflag == "true") 
	{
		var tmptmp=field.length;
		if(tmptmp == undefined)
			tmptmp=1;
		if(tmptmp == 1)
		{
			if(checkflag == "true")
			{ document.forms[0].cx.checked = false;}
			
		}else{
			for (i = 0; i < field.length; i++) 
			{
				field[i].checked = false;
			}
			checkflag = "false";
			return ;
		}
	}
}

function doDisCard(){ 

	var flag_cc = 0;
	var cx_field = document.forms[0].cx;
	var cx_len = document.forms[0].cx.length;
	
	if(cx_len == undefined){
		cx_len = 1;
		for(var cc = 0 ; cc < cx_len ; cc++){
			if(document.forms[0].cx.checked){
				flag_cc = 1;
				break;
			}	
		}
	}
	else{
		for(var cc = 0 ; cc < cx_len ; cc++){	
			if(document.forms[0].cx[cc].checked){
				flag_cc = 1;
				break;
			}
		
		}
	}
	if(flag_cc == 0){
		alert('请先选择记录');
		return;
	}
	document.forms[0].act.value = "disCard";
	document.forms[0].submit();
	
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Stoproduct.do">
<input type=hidden name=act value="ql">
<input type=hidden name=requery > 
<input type=hidden name=wkState value="15"> 
<input type=hidden name=operationType value="71"> 

<%=ViewUtil.getTitle("待报废记录列表")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
 			sam卡号段:	
			从<html:text property="samId_min" styleClass="Textfield" size="12" />
			到<html:text property="samId_max" styleClass="Textfield" size="12" />
			</td>
		</tr>
		<tr>
			<td>
			产品类型:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="prodIdcollection"/>
			</html:select>
			通信速率:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="phiTypeIdcollection"/>
			</html:select>
			应用类型:
			<html:select property="appTypeId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="appTypeIdcollection"/>
			</html:select>						
			<input type="button" value="搜索" class="Button" onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>多选</td>	
			<td>SAM卡号</td>
			<td>OA申请号</td>
			<td>申请单位</td>
			<td>产品类型</td>
			<td>产品通信速率</td>
			<td>产品应用类型</td>
			<td>密钥类型</td>
			<td>认证方式</td>
			<td>发行时间</td>
			<td>领用类型</td>
			<td>支付方式</td>
			<td>检测结果</td>
			<td>产品状态</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoproduct vo = (Stoproduct) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td>
			<label>
			<input type="checkbox" name="cx" value="<%=vo.getSamId()+","+vo.getSamCSN()%>"> 
			</label></td> 
			<td><a href="Stoproduct.do?act=r&SamId=<%=vo.getSamId()%>&SamCSN=<%=vo.getSamCSN()%>"><%=vo.getSamId() %></a></td>	
			<td><%=vo.getOAappNo()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID, vo.getProdId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.COMM_RATE, vo.getPhiTypeId())%></td>					
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getAppTypeId()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getAuthSign().toString())%></td>
			<td><%=vo.getIssueTime() %></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.CONSUTYPE, String.valueOf(vo.getConsumeType()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PAYMETYPE, String.valueOf(vo.getPaymentType()))%></td>				
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN, String.valueOf(vo.getDetectSign()))%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE, String.valueOf(vo.getWkState()))%></td>						
		</tr>

		<%}
}
}%>

</table>
	<%
//产生翻页脚注 
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
			<input type="button" value="报废" class="Button" onClick="doDisCard()"/>
			
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


