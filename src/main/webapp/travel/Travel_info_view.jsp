<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.travel.detail.TravelDetail" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="travelInfoForm" scope="request" class="com.blue.travel.TravelInfoForm"/>
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
 
<html:form method="post" action="TravelInfo.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<!--startprint1-->  
<%=ViewUtil.getTitle("�Ϻ�ͨ�����ڷ������޹�˾")%> 
<%=ViewUtil.getTitle("���÷ѱ�����")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 		<tr bgcolor="#FFFFFF"> 
          <td align="center" colspan="5" class="dtPanel_Left">&nbsp;�������ڣ�
          <%=DateUtil.formatDate(travelInfoForm.getRegDate())%>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="33%" align="left" class="dtPanel_Left">&nbsp;���ţ�
          <%=SingleDicMap.getDicItemVal("9990", travelInfoForm.getDept()) %>                 
         </td> 
         <td width="33%" align="left" class="dtPanel_Left">&nbsp;��Ŀ��ţ�
 		  <%=travelInfoForm.getProjectNo()%> 
 		 </td>
         <td  align="left" class="dtPanel_Left">&nbsp;��Ŀ���ƣ�
          <%=travelInfoForm.getProjectName()%>
          </td> 
       </tr>
       <tr bgcolor="#FFFFFF"> 
          <td align="center" colspan="1" class="dtPanel_Left">&nbsp;�����ˣ�
          <%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,travelInfoForm.getCurrUser())%>
         </td>
         <td align="center" colspan="4" class="dtPanel_Left">&nbsp;�������ɣ�
          <%=travelInfoForm.getTravelReason()%>
         </td>  
       </tr> 
       </table>
       <br />
       
       <table align="left" width="56%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       <tr bgcolor="#FFFFFF"> 
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;����ʱ�䣺</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;�����ص㣺</td>
    		<td width="8%" align="center" class="dtPanel_Left">&nbsp;����ʱ�䣺</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;����ص㣺</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;��ͨ���ߣ�</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;����������</td>
       		<td width="8%" align="center" class="dtPanel_Left">&nbsp;��</td>
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
       		<td align="left" colspan="3" class="dtPanel_Main">&nbsp;�������</td>
       </tr>
 		<tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;����</td>
       		<td align="left" class="dtPanel_Main">&nbsp;��׼</td>
       		<td align="left" class="dtPanel_Main">&nbsp;���</td>
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
       		<td align="left" colspan="3" class="dtPanel_Main">&nbsp;�������ã�</td>
       </tr>
 		<tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;��Ŀ</td>
       		<td align="left" class="dtPanel_Main">&nbsp;��������</td>
       		<td align="left" class="dtPanel_Main">&nbsp;���</td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;ס�޷�</td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getLodgingInvoiceno()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getLodgingAmt()%></td>
       </tr>
       <tr > 
       		<td align="left" class="dtPanel_Main">&nbsp;���ڳ���</td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getTaxiInvoiceno()%></td>
       		<td align="left" class="dtPanel_Main">&nbsp;<%=travelInfoForm.getTaxiAmt()%></td>
       </tr>

 		
  	</table>  

	<table align="left" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
		<tr> 
				<td align="left" width="56%" colspan="2" class="dtPanel_Main"> С�ƣ�
				<%=travelInfoForm.getTravelAmt()%></td> 
		 		<td height="25" align="left" class="dtPanel_Main">�� 
 				<%=travelInfoForm.getAllowanceTotal()+travelInfoForm.getAllowanceTotal2()%>
		 		</td>
		 		<td height="25" align="left" class="dtPanel_Main">С�ƣ��� 
 				<%=travelInfoForm.getTaxiAmt()+travelInfoForm.getLodgingAmt()%>
		 		</td>
	    </tr>
	    <tr> 
				<td align="left" width="49%" colspan="2" class="dtPanel_Main"> �����ܶ����Ҵ�д����
				<%=(java.lang.String)request.getAttribute("chineseAmt")%>
 
		 		</td> 
		 		<td height="25" colspan="2" align="left" class="dtPanel_Main">�� 
 				<%=travelInfoForm.getTotalAmt()%>
		 		</td>
	    </tr>
	    <tr> 
				<td align="left" colspan="2"   class="dtPanel_Main"> 
				�ܲ������
		 		</td> 
		 		<td height="25" colspan="2"  align="left" class="dtPanel_Main">
		 		�ֹ��쵼�����
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
 

