<%@page import="com.blue.enums.DailyType"%>
<%@page import="com.blue.otherdaily.OtherDaily"%>
<%@page import="com.blue.enums.Steps"%>
<%@page import="com.blue.dailyrecord.DailyRecord"%>
<%@ include file = "/includes/common.jsp" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
<<jsp:useBean id="dailyRecordForm" class="com.blue.dailyrecord.DailyRecordForm"></jsp:useBean>
<html> 
<head>
<title>������ʱ��д��ѯ</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
	document.forms[0].act.value = "qa"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qa";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 <script type="text/javascript" src="js/calendar.js"></script>
 
<html:form method="post" action="OtherDaily.do"> 
<input type=hidden name=act value="qa"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
    	<tr class="dtPanel_Top01">
    		<td align="center">������ʱ��д��ѯ</td>
    	</tr>
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; ������
            <html:select property="inputUser">
            	<html:optionsCollection name="dailyRecordForm" property="staff"/>
            </html:select>
           &nbsp;��ʼ���ڣ�<html:text property="startDate" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            &nbsp;��ֹ���ڣ�<html:text property="endDate" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td nowrap>����</td>
						<td nowrap>&nbsp;��ʱ���</td>
						<td nowrap>�����</td>
						<td nowrap>��ʱ��Сʱ��</td>
						<td nowrap>¼������</td>
						<td>����˵��</td>
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							OtherDaily vo = (OtherDaily) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td nowrap><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,vo.getInputUser())%></td>
						<td nowrap><%=DailyType.valueOf(vo.getType()).getDesc() %></td>
						<td><%=vo.getWorkDate() %></td>
						<td><%=vo.getCost() %></td>
						<td><%=vo.getInputDate() %></td>
						<td width="30%" align="left"><%=vo.getMemo() %></td>
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//������ҳ��ע 
				if(pageResult != null) { 
			%> 
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

