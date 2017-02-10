<%@page import="com.yly.info.Biunitinfotb"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>申请单位列表</title>
<script language="javascript"> 
function doAdd(){ 
	//增加 
	window.location="Biunitinfo.do?act=c"; 
} 
function setPKey(unitId_var) { 
	document.forms[0].unitId.value=unitId_var; 
} 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].unitId.value == null ||document.forms[0].unitId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u'; 
	document.forms[0].submit(); 
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
function doNoUse() {  
	document.forms[0].act.value = "disUse";
	document.forms[0].submit(); 
}
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Biunitinfo.do">
<input type=hidden name=act value="list">
<input type=hidden name=requery>
<input type=hidden name=unitId>
<%=ViewUtil.getTitle("申请单位列表信息列表")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr > 
          <td>单位名称：
			<html:text property="chnshort_f" styleClass="Textfield" size="16" maxlength="16"  />
  			行业代码：
			<html:text property="hyid_f" styleClass="Textfield" size="4" maxlength="4"  />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
  		  </td> 		  
       </tr>       
	</table>
 
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>单位代码</td>
			<td>行业编码</td>
			<td>行业名称</td>
			<td>单位类型</td>
			<td>单位中文全称</td>
			<td>单位中文简称</td>	
			<td>联系人</td>	
			<td>电话</td>	
			<td>地址</td>	
			<td>上级机构</td>
			<td>单位等级</td>
			<td>录入员</td>
			<td>录入日期</td>
			<td>备注</td>
			<td>状态</td>
			<td>选择</td>
			
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Biunitinfotb vo = (Biunitinfotb) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">			
			<td><%=vo.getUnitid() %></td>	
			<td><%=vo.getHyid()%></td>	
			<td><%=vo.getHyName()%></td>		
			<td><%=vo.getUnittype()%></td>		
			<td><%=vo.getUnitdes()%></td>		
			<td><%=vo.getChnshort()%></td>	
			<td><%=vo.getUnitperson()%></td>	
			<td><%=vo.getUnittel()%></td>	
			<td><%=vo.getUnitaddr()%></td>	
			<td><%=vo.getLeadStore()%></td>	
			<td><%=SingleDicMap.getDicItemVal(SingleDic.UNIT_LEVEL,vo.getUnitlevel())%></td>				
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getOperId()) %></td>
			<td><%=vo.getCurrDate()%></td>		
			<td><%=vo.getRsvd()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.STATE, vo.getUnittestflag())%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getUnitid()%>')">		
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
			<input type="button" value="增加" class="Button" onClick="doAdd()"/>
			<input type="button" value="修改" class="Button" onClick="doEdit()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


