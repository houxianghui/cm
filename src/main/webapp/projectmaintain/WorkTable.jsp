<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<head>
<style type="text/css">
a:LINK{
	color:white;
	text-decoration: none;
}

</style>

<title>员工工作表</title>
<%
	String date = (String)request.getAttribute("date");
	String worktable = (String)request.getAttribute("worktable");
%>
<script type="text/javascript">
function showTable(){
	var value = document.forms[0].date.value;
	document.forms[0].submit();
}
function next(event){
	if(event.keyCode == 37){
		//window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=pre";
		preWeek();
	}else if(event.keyCode==39){
		//window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=next";
		nextWeek();
	}else if(event.keyCode == 36){
		showTable();
		//window.location.href="ProjectDistribute.do?act=display";
	}
}
function prevWeek(){
	document.forms[0].flag.value="pre";
	document.forms[0].date.value="<%=date%>";
	document.forms[0].submit();
	//window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=pre";
}
function nextWeek(){
	document.forms[0].flag.value="next";
	document.forms[0].date.value="<%=date%>";
	document.forms[0].submit();
	//window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=next";
}
$(function() {
	$(":input[type='button']").attr("class","Button").attr("width","50");
	
	$("#all").click(function(){
		$("input[name='ids']").attr("checked",true);
	});
	$("#allnot").click(function(){
		$("input[name='ids']").attr("checked",false);
	});
});
</script>
</head>
<body onkeydown="next(event)">
<script type="text/javascript" src="js/calendar.js"></script>
<html:form action="ProjectDistribute.do" method="post">
<input type="hidden" value="display" name="act">
<input type="hidden" name="flag"/>
	
	 <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
	 <tr class="dtPanel_Main" align="center">
		<td>
		<input type="button" id="all" value="全选" style="width:50px">
		</td>
		<td rowspan="2" width="90%">
		<%out.print(request.getAttribute("checkbox")); %>
		</td>
	</tr>
	<tr align="center"><td>
		<input type="button" id="allnot" value="全不选" style="width:50px">
		</td>
	</tr>
     <tr align="center" class="dtPanel_Top01"  >
		<td class="dtPanel_Top01" colSpan="2">
			<input type="button" class="Button" value="<<上周" onclick="prevWeek()">
			员工工作表
			<input type="text" name="date" id="date" class="Textfield" readonly="true" size="8" onclick="new Calendar().show(this);"  value="<%=date%>" >
			<input type="button" class="Button" value="搜索" onclick="showTable()">
			<input type="button" class="Button" value="下周>>" onclick="nextWeek()">
		</td>
       
      </tr>

     </table>
	<%
		out.println(worktable);
	%>
</html:form>


</body>
</html>


