<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.blue.taxi.TaxiInfo" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
<jsp:useBean id="taxiInfoForm" scope="request" class="com.blue.taxi.TaxiInfoForm"/>
 
<html> 
<head> 
<title>���⳵����ά��</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="TaxiInfo.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].taxiId.value == null ||document.forms[0].taxiId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].taxiId.value == null ||document.forms[0].taxiId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(taxiId_var) { 
	document.forms[0].taxiId.value=taxiId_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="TaxiInfo.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=taxiId> 
 
<!--startprint1--> 
<%=ViewUtil.getTitle("���⳵����ϸ��")%> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td colspan="2">����</td>
						<td colspan="4"><%=SingleDicMap.getDicItemVal("9990", (String)request.getAttribute("dept"))%></td>

 
					</tr> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >���</td>
						<td >����</td>
						<td >ʱ��</td>
						<td >����ԭ��</td> 
						<td >ʼ����-Ŀ�ĵ�</td> 
						<td >���</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							TaxiInfo vo = (TaxiInfo) iter.next();
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=vo.getTaxiId()%></td>
						<td><%=DateUtil.formatDate(vo.getTaxiDate())%></td>
						<td><%=vo.getTaxiTime()%></td>
						<td><%=vo.getTaxiReason()%></td> 
						<td><%=vo.getTaxiPath()%></td> 
						<td><%=vo.getTaxiAmt()%></td> 
					</tr> 
 
				<% 
							} 
						} 
					%>
					<tr align="center" class="dtPanel_Top01"> 
						<td colspan="2">�ܼƣ�Ԫ��</td>
						<td colspan="4"><%=(java.lang.Double)request.getAttribute("totalAmt")%></td> 
					</tr>
				<%  
					} 
				%> 
				</table> 
<!--endprint1--> 
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
			<br> 
 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<input type="button" class="Button" name="prview" id="taxiInfo_c" value="��ӡ" onClick="preview(1);"/> 
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

