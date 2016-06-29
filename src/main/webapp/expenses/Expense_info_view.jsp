<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.expenses.detail.ExpensesDetail" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="expensesInfoForm" scope="request" class="com.blue.expenses.ExpensesInfoForm"/>
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
 
<html:form method="post" action="ExpensesInfo.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<!--startprint1-->  
<%=ViewUtil.getTitle("上海通联金融服务有限公司")%> 
<%=ViewUtil.getTitle("报销单")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		
        <tr bgcolor="#FFFFFF"> 
          <td width="22%" align="left" class="dtPanel_Left">&nbsp;部门：
            <%=SingleDicMap.getDicItemVal("9990", expensesInfoForm.getDept()) %>                
         </td> 
         <td width="22%" align="left" class="dtPanel_Left">&nbsp;项目编号：
 		  <%=expensesInfoForm.getProjectNo()%> 
 		 </td>
         <td width="22%" align="right" class="dtPanel_Left">&nbsp;项目名称：
          <%=expensesInfoForm.getProjectName()%> 
         </td> 
         <td width="22%" align="center" class="dtPanel_Left">&nbsp;附件：<%=(java.lang.Integer)request.getAttribute("invoiceno")%>张</td>
       </tr> 
       </table>
       <br />
       
       <table align="left" width="78%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr bgcolor="#FFFFFF"> 
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;发生时间：</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;报销内容：</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;原始单据张数：</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;金额：</td>
       </tr>
      <% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							ExpensesDetail vo = (ExpensesDetail) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getTime()%></td>
						<td><%=vo.getContent()%></td> 
						<td><%=vo.getInvoiceno()%></td>
						<td><%=vo.getAmt()%></td>
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
 
 
  	</table>
  	<table  width="20%" height="300" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr > 
       		<td align=“left” valign="top" class="dtPanel_Main">&nbsp;公司领导：</td>
       </tr>
 		<tr > 
       		<td align=“left” valign="top" class="dtPanel_Main">&nbsp;部门分管领导：</td>
       </tr>

 		
  	</table>  

	<table align="left" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
		<tr> 
				<td align="left" width="49%" colspan="2" class="dtPanel_Main"> 合计（人民币大写）：
				<%=(java.lang.String)request.getAttribute("chineseAmt")%>
 
		 		</td> 
		 		<td height="25" colspan="2" align="left" class="dtPanel_Main">￥ 
 				<%=(java.lang.Double)request.getAttribute("totalAmt")%>
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
		 		报销人：
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
 

