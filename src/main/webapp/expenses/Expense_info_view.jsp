<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.expenses.detail.ExpensesDetail" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="expensesInfoForm" scope="request" class="com.blue.expenses.ExpensesInfoForm"/>
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
<html> 
 <head>
<title>���÷���Ϣ</title> 
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
<%=ViewUtil.getTitle("�Ϻ�ͨ�����ڷ������޹�˾")%> 
<%=ViewUtil.getTitle("������")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		
        <tr bgcolor="#FFFFFF"> 
          <td width="22%" align="left" class="dtPanel_Left">&nbsp;���ţ�
            <%=SingleDicMap.getDicItemVal("9990", expensesInfoForm.getDept()) %>                
         </td> 
         <td width="22%" align="left" class="dtPanel_Left">&nbsp;��Ŀ��ţ�
 		  <%=expensesInfoForm.getProjectNo()%> 
 		 </td>
         <td width="22%" align="right" class="dtPanel_Left">&nbsp;��Ŀ���ƣ�
          <%=expensesInfoForm.getProjectName()%> 
         </td> 
         <td width="22%" align="center" class="dtPanel_Left">&nbsp;������<%=(java.lang.Integer)request.getAttribute("invoiceno")%>��</td>
       </tr> 
       </table>
       <br />
       
       <table align="left" width="78%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr bgcolor="#FFFFFF"> 
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;����ʱ�䣺</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;�������ݣ�</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;ԭʼ����������</td>
       		<td width="15%" align="center" class="dtPanel_Left">&nbsp;��</td>
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
       		<td align=��left�� valign="top" class="dtPanel_Main">&nbsp;��˾�쵼��</td>
       </tr>
 		<tr > 
       		<td align=��left�� valign="top" class="dtPanel_Main">&nbsp;���ŷֹ��쵼��</td>
       </tr>

 		
  	</table>  

	<table align="left" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
		<tr> 
				<td align="left" width="49%" colspan="2" class="dtPanel_Main"> �ϼƣ�����Ҵ�д����
				<%=(java.lang.String)request.getAttribute("chineseAmt")%>
 
		 		</td> 
		 		<td height="25" colspan="2" align="left" class="dtPanel_Main">�� 
 				<%=(java.lang.Double)request.getAttribute("totalAmt")%>
		 		</td>
	    </tr>
	    <tr> 
				<td align="left"  width="24%" class="dtPanel_Main"> 
				���񸴺ˣ�
		 		</td> 
		 		<td height="25" align="left" class="dtPanel_Main">
		 		���Ÿ����ˣ�
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">
		 		��Ŀ����
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">
		 		�����ˣ�
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
						<input	name="add" type="button" class="Button" value="��ӡ" onClick="preview(1);"/> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

