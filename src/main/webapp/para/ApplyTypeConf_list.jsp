<%@page import="com.yly.para.Applytypeinfo"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>应用类型配置</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Applytypeinfo.do?act=c"; 
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
<html:form method="post" action="Applytypeinfo.do">
<input type=hidden name=act value="list">

<%=ViewUtil.getTitle("应用类型配置")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			应用类型:			
			<html:text property="applyTypeId" styleClass="Textfield" size="6" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>应用类型编号</td>
			<td>应用类型名称</td>
			<td>Pki存在</td>
			<td>维2存在</td>
			<td>维2认证</td>
			<td>支持互通</td>						
			<td>充值密钥使用权限不需要认证</td>
			<td>测试密钥外部认证密钥为全零</td>
			<td>SAM卡片产品类别编号</td>
			<td>操作员</td>
			<td>录入日期</td>
			<td>备注</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Applytypeinfo vo = (Applytypeinfo) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getApplyTypeId() %></td>	
			<td><%=vo.getApplyIdDesc()%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsPki()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsV2()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsV2Sign()) %></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsHLCard()) %></td>						
			<td><%=vo.getIsIsamSign()==null?"":SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsIsamSign()) %></td>
			<td><%=vo.getIsIsamTestAllO()==null?"":SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO, vo.getIsIsamTestAllO()) %></td>
			<td><%=vo.getProdClassId()==null?"":vo.getProdClassId()%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=vo.getRemarks()%></td>				
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
			<input type="button" value="增加应用类型" class="Button" onClick="doAdd()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


