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
<title>回收库卡片查询</title>
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
<html:form method="post" action="Storeuse.do">
<input type=hidden name=act value="cardlist">
<input type=hidden name=requery>
<%=ViewUtil.getTitle("回收库卡片查询")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr> 
       	   <td  width="98%" align="left" class="dtPanel_Main2">&nbsp;
       	             退回日期:		
			从<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			到<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			</td>
		 </tr>
		 <tr> 
			<td  width="98%" align="left" colspan="4"  class="dtPanel_Main2">&nbsp;	 
			发行卡号:			
			从<html:text property="samId_min" styleClass="Textfield" size="12" />
			到<html:text property="samId_max" styleClass="Textfield" size="12" />
			印刷卡号:
			<html:text property="samCsn_f" styleClass="Textfield" size="20" onblur="noChinese(this)"/>
			产品类型:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="storeuseForm" property="prodIdcollection"/>
			</html:select>
			</td>
		 </tr>
		 <tr> 
		 	<td  width="98%" align="left" colspan="4"  class="dtPanel_Main2">&nbsp;	 
			通信速率:
			<html:select property="phiTypeId" styleClass="Select">
				<html:optionsCollection name="storeuseForm" property="phiTypeIdcollection"/>
			</html:select>
			应用类型:
			<html:select property="appTypeId" styleClass="Select">
				<html:optionsCollection name="storeuseForm" property="appTypeIdcollection"/>
			</html:select>						
			<input type="button" value="搜索" class="Button" onclick="doQuery()">
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
			<td>退回时间 </td>
			<td>检测结果 </td>
			<td>流水跟踪 </td>	
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
			<td><%=vo.getOAappNo()==null?"":vo.getOAappNo()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, vo.getAppTypeId())%> </td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%> </td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(vo.getManufacId())) %>	</td>
			<td><%=vo.getBatchId()==null?"":vo.getBatchId()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(vo.getWkState()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(vo.getIOState()))%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.UNITID, String.valueOf(vo.getUnitId()))%></td>
			<td><%=vo.getIssueTime()==null?"":vo.getIssueTime()%></td>		
			<td><%=vo.getIOStateChgDate()==null?"":vo.getIOStateChgDate()%></td>		
			<td><%=vo.getDetectSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN,String.valueOf(vo.getDetectSign()))%></td>		
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>">流水</a></td>			
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
			<input type="button" value="导出" class="Button" onClick="doCardDown()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


