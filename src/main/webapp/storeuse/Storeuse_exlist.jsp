<%@page import="com.yly.reuse.Storeuse"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="storeuseForm" scope="request"  class="com.yly.reuse.StoreuseForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>退回好卡重新领用管理</title>
<script language="javascript"> 

function doQuery() {  
	document.forms[0].act.value = "exlist";
	document.forms[0].submit(); 
}
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "exlist";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doRepExport(){ 
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
	document.forms[0].act.value = "reEx";
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


</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" enctype="multipart/form-data" action="Storeuse.do">
<input type=hidden name=act value="upload">
<input type=hidden name=requery>
<html:hidden property="operationType"/>
<html:hidden property="appNo"/>
<html:hidden property="taskAmt"/>
<html:hidden property="taskAmtLeft"/>
<html:hidden property="unitId"/>
<%=ViewUtil.getTitle("退回好卡重新领用管理")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr> 
         <td>
			发行卡号:			
			从<html:text property="samId_min" styleClass="Textfield" size="12" />
			到<html:text property="samId_max" styleClass="Textfield" size="12" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
       	</tr>
       	<tr> 
       	出库数量:<%=storeuseForm.getTaskAmt()%> 剩余出库数量:<%=storeuseForm.getTaskAmtLeft()%>
       	</tr>
	</table>
     <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="center" class="dtPanel_Left">&nbsp;文件： 
			<html:file property="file"></html:file>
       		<html:submit value="批量上传出库卡号" styleClass="Button"/>
         </td> 
       </tr>     
      
  </table>  
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>SAM卡号 </td>
			<td>SAM印刷卡号 </td>
			<td>OA申请号</td>
			<td>产品类型  </td>
			<td>产品应用类型</td>
			<td>密钥类型  </td>
			<td>厂商代码</td>
			<td>入库批次号  </td>
			<td>产品状态 </td>
			<td>出回库状态</td>
			<td>申请单位 </td>
			<td>发行时间 </td>
			<td>检测结果 </td>
			<td>流水跟踪 </td>
			<td>选择 </td>			
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Storeuse vo = (Storeuse) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><a href="Storeuse.do?act=r&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>"><%=vo.getSamId()%></a></td>	
			<td><%=vo.getSamCSN()%></td>		
			<td><%=vo.getOAappNo()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getAppTypeId())%> </td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%> </td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(vo.getManufacId())) %>	</td>
			<td><%=vo.getBatchId()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(vo.getWkState()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(vo.getIOState()))%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(vo.getUnitId()))%></td>
			<td><%=vo.getIssueTime()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.DETECSIGN,String.valueOf(vo.getDetectSign()))%></td>		
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>">流水</a></td>	
			<td>
			<label>
			<input type="checkbox" name="cx" value="<%=vo.getSamId()+","+vo.getSamCSN()%>"> 
			</label></td> 			
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
			<input type="button" name="check_all" class="Button" value="全选" onClick="checkAll(document.forms[0].cx);"/>  
			<input type="button" name="check2" class="Button" value="取消全选" onClick="cancelCheckAll(document.forms[0].cx);"/>  
			<input type="button" value="退回卡再次领出复用" class="Button" onClick="doRepExport()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


