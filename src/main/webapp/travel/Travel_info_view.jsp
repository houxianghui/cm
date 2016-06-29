<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.travel.detail.TravelDetail" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="travelInfoForm" scope="request" class="com.blue.travel.TravelInfoForm"/>
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
<html> 
 <head>
<title>差旅费信息</title> 
<SCRIPT src="js/apply/cityCode.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 


</script> 
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="TravelInfo.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<!--startprint1-->  
<%=ViewUtil.getTitle("上海通联金融服务有限公司")%> 
<%=ViewUtil.getTitle("差旅费报销单")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td align="center" colspan="5" class="dtPanel_Left">&nbsp;报销日期：
          <%=DateUtil.formatDate(travelInfoForm.getRegDate())%>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="33%" align="left" class="dtPanel_Left">&nbsp;部门：
          <%=SingleDicMap.getDicItemVal("9990", travelInfoForm.getDept()) %>                 
         </td> 
         <td width="33%" align="left" class="dtPanel_Left">&nbsp;项目编号：
 		  <%=travelInfoForm.getProjectNo()%> 
 		 </td>
         <td  align="left" class="dtPanel_Left">&nbsp;项目名称：
          <%=travelInfoForm.getProjectName()%>
          </td> 
       </tr>
       <tr bgcolor="#FFFFFF"> 
          <td align="center" colspan="1" class="dtPanel_Left">&nbsp;出差人：
          <%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,travelInfoForm.getCurrUser())%>
         </td>
         <td align="center" colspan="4" class="dtPanel_Left">&nbsp;出差事由：
          <%=travelInfoForm.getTravelReason()%>
         </td>  
       </tr> 
       </table>
       <br />
       
       <table align="left" width="56%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr bgcolor="#FFFFFF"> 
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;出发时间：</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;出发地点：</td>
    		<td width="8%" align="center" class="dtPanel_Left">&nbsp;到达时间：</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;到达地点：</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;交通工具：</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;单据张数：</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;金额：</td>
       </tr>
      <% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							TravelDetail vo = (TravelDetail) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getFromDate()%></td>
						<td><%=SingleDicMap.getDicItemVal("7004", vo.getTravelFrom())%></td> 
						<td><%=vo.getToDate()%></td>
						<td><%=SingleDicMap.getDicItemVal("7004", vo.getTravelTo())%></td>
						<td><%=SingleDicMap.getDicItemVal("7003", vo.getTransportMode())%></td>
						<td><%=vo.getInvoiceno()%></td>
						<td><%=vo.getFee()%></td>
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
 
 
  	</table>
  	<table  align="left" width="22%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr > 
       		<td align="left" colspan="3" class="dtPanel_Main">&nbsp;出差补贴：</td>
       </tr>
 		<tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;天数</td>
       		<td align="left" class="dtPanel_Main">&nbsp;标准</td>
       		<td align="left" class="dtPanel_Main">&nbsp;金额</td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowancePerday()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowanceDays()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowanceTotal()%></td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowancePerday2()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowanceDays2()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getAllowanceTotal2()%></td>
       </tr>

 		
  	</table>
  	<table  width="20%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr > 
       		<td align="left" colspan="3" class="dtPanel_Main">&nbsp;其他费用：</td>
       </tr>
 		<tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;项目</td>
       		<td align="left" class="dtPanel_Main">&nbsp;单据张数</td>
       		<td align="left" class="dtPanel_Main">&nbsp;金额</td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;住宿费</td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getLodgingInvoiceno()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getLodgingAmt()%></td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;市内车费</td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getTaxiInvoiceno()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getTaxiAmt()%></td>
       </tr>

 		
  	</table>  

	<table align="left" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
		<tr> 
				<td align="left" width="56%" colspan="2" class="dtPanel_Main"> 小计：
				<%=travelInfoForm.getTravelAmt()%></td> 
		 		<td height="25" align="left" class="dtPanel_Main">￥ 
 				<%=travelInfoForm.getAllowanceTotal()+travelInfoForm.getAllowanceTotal2()%>
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">小计：￥ 
 				<%=travelInfoForm.getTaxiAmt()+travelInfoForm.getLodgingAmt()%>
		 		</td>
	    </tr>
	    <tr> 
				<td align="left" width="49%" colspan="2" class="dtPanel_Main"> 报销总额（人民币大写）：
				<%=(java.lang.String)request.getAttribute("chineseAmt")%>
 
		 		</td> 
		 		<td height="25" colspan="2" align="left" class="dtPanel_Main">￥ 
 				<%=travelInfoForm.getTotalAmt()%>
		 		</td>
	    </tr>
	    <tr> 
				<td align="left" colspan="2"   class="dtPanel_Main"> 
				总裁意见：
		 		</td> 
		 		<td height="25" colspan="2"  align="left" class="dtPanel_Main">
		 		分管领导意见：
		 		</td>
		 		
	    </tr>
	    <tr> 
				<td align="left"  width="24%" class="dtPanel_Main"> 
				财务复核：
		 		</td> 
		 		<td height="25" align="left" class="dtPanel_Main">
		 		部门负责人：
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">
		 		项目经理：
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">
		 		出差人：
		 		</td>
	    </tr>
	</table>
	<br/>
	<!--endprint1--> 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr> </table> 
 	<br/>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
    	
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="打印" onClick="preview(1);"/> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

