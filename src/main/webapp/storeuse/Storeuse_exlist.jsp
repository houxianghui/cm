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
<title>�˻غÿ��������ù���</title>
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
		alert('����ѡ���¼');
		return;
	}
	document.forms[0].act.value = "reEx";
	document.forms[0].submit();
} 
function checkAll(field) //ȫѡ
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
function cancelCheckAll(field)//ȫ��ȡ��
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
<%=ViewUtil.getTitle("�˻غÿ��������ù���")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr> 
         <td>
			���п���:			
			��<html:text property="samId_min" styleClass="Textfield" size="12" />
			��<html:text property="samId_max" styleClass="Textfield" size="12" />
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
       	</tr>
       	<tr> 
       	��������:<%=storeuseForm.getTaskAmt()%> ʣ���������:<%=storeuseForm.getTaskAmtLeft()%>
       	</tr>
	</table>
     <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="center" class="dtPanel_Left">&nbsp;�ļ��� 
			<html:file property="file"></html:file>
       		<html:submit value="�����ϴ����⿨��" styleClass="Button"/>
         </td> 
       </tr>     
      
  </table>  
	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>SAM���� </td>
			<td>SAMӡˢ���� </td>
			<td>OA�����</td>
			<td>��Ʒ����  </td>
			<td>��ƷӦ������</td>
			<td>��Կ����  </td>
			<td>���̴���</td>
			<td>������κ�  </td>
			<td>��Ʒ״̬ </td>
			<td>���ؿ�״̬</td>
			<td>���뵥λ </td>
			<td>����ʱ�� </td>
			<td>����� </td>
			<td>��ˮ���� </td>
			<td>ѡ�� </td>			
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
			<td><a href="Lsinfo.do?act=list&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>">��ˮ</a></td>	
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
//������ҳ��ע 
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
			<input type="button" name="check_all" class="Button" value="ȫѡ" onClick="checkAll(document.forms[0].cx);"/>  
			<input type="button" name="check2" class="Button" value="ȡ��ȫѡ" onClick="cancelCheckAll(document.forms[0].cx);"/>  
			<input type="button" value="�˻ؿ��ٴ��������" class="Button" onClick="doRepExport()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


