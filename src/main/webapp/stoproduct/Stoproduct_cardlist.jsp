<%@page import="com.yly.exstore.Stoproduct"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="stoproductForm" scope="request"  class="com.yly.exstore.StoproductForm" />

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��Ʒ�⿨Ƭ��ѯ</title>
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
function doWDiscard(){
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
	document.forms[0].operationType.value="72";
	document.forms[0].act.value = "wDisCard";
	document.forms[0].submit();
}
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="Stoproduct.do">
<input type=hidden name=act value="cardlist">
<input type=hidden name=requery>
<input type=hidden name=operationType>	
<%=ViewUtil.getTitle("����Ʒ��ѯ")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
         <tr> 
         <td  width="98%" align="left" colspan="4"  class="dtPanel_Main2">&nbsp;
         	��������:		
			��<html:text property="beginDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			��<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
		 </td>
		 </tr>
		 <tr> 
		 <td  width="98%" align="left" colspan="4"  class="dtPanel_Main2">&nbsp;
         	OA���뵥��:			
			<html:text property="OAappNo" styleClass="Textfield"  size="16" maxlength="30"  onblur="noChinese(this)"/>
			���п���:			
			��<html:text property="samId_min" styleClass="Textfield" size="12" />
			��<html:text property="samId_max" styleClass="Textfield" size="12" />
			ӡˢ����:
			<html:text property="samCsn_f" styleClass="Textfield" size="20" onblur="noChinese(this)"/>
		</td>
		</tr>
		<tr> 
		<td  width="98%" align="left" colspan="4"  class="dtPanel_Main2">&nbsp;
			����:
			<html:select property="manufacId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="manufacIdCollection"/>
			</html:select>
			��Ʒ:
			<html:select property="prodId" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="prodIdCollection"/>
			</html:select>
			��Ʒ״̬:
			<html:select property="wkState" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="wkStatecollection"/>
			</html:select>	
			���״̬:
			<html:select property="IOState" styleClass="Select">
				<html:optionsCollection name="stoproductForm" property="IOStatecollection"/>
			</html:select>			
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
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
			<td>��ѡ </td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Stoproduct vo = (Stoproduct) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">	
			<td><a href="Stoproduct.do?act=r&samId=<%=vo.getSamId()%>&samCSN=<%=vo.getSamCSN()%>"><%=vo.getSamId()%></a></td>	
			<td><%=vo.getSamCSN()%></td>		
			<td><%=vo.getOAappNo()==null||vo.getOAappNo().equals("null")?"":vo.getOAappNo()%></td>			
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROD_ID,vo.getProdId())%></td>	
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.APPTYPEID, String.valueOf(vo.getAppTypeId()))%> </td>
			<td><%=vo.getKeyType()==null?"":SingleDicMap.getDicItemVal(SingleDic.KEYTYPE, String.valueOf(vo.getKeyType()))%> </td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.MAUN_ID, String.valueOf(vo.getManufacId())) %>	</td>
			<td><%=vo.getBatchId()==null||vo.getBatchId().equals("null")?"":vo.getBatchId()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WKSTATE,String.valueOf(vo.getWkState()))%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.IO_STATE,String.valueOf(vo.getIOState()))%></td>
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.ALL_UNITID, String.valueOf(vo.getUnitId()))%></td>
			<td><%=vo.getIssueTime()==null?"":vo.getIssueTime()%></td>		
			<td><%=vo.getDetectSign()==null?"δ���":SingleDicMap.getDicItemVal(SingleDic.DETECSIGN,String.valueOf(vo.getDetectSign()))%></td>		
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
			<input type="button" value="����" class="Button" onClick="doCardDown()"/>
			<input type="button" value="����������" class="Button" onClick="doWDiscard()"/>
			</td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


