<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <style>
label {
display: inline-block; width: 5em;
}
fieldset div {
margin-bottom: 2em;
}
fieldset .help {
display: inline-block;
}
.ui-tooltip {
width: 210px;
}
fieldset
{
    padding: 10px;
    margin: 10px;
    width: 960px;
    color: #333;
    border: #06c thin 1px;
    cellspacing:0;
    bordercolor:#99BCFC;
}
legend
{
    color: #06c;
    font-weight: 800;
    background: #fff;
    border: #b6b6b6 solid 1px;
    padding: 3px 6px;
}
div{
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>通知信息</title>
<script type="text/javascript">

$(function(){
	$("table").attr("width","98%").attr("class","dtPanel_Line1").attr("border","0").attr("cellspaceing","1").attr("align","center").attr("cellpadding","0");
	$("tr").attr("align","center").attr("class","dtPanel_Top01").attr("height","28");
	$("div").css("width","98%").css("padding","15px");
});
</script>
</head>
<body>
	<div id="notice">
		<fieldset>
		<legend>通知</legend>
		<div><%out.println(request.getAttribute("notice"));%></div>
		</fieldset>
	</div>
	<div>
		<fieldset>
		<legend>待办任务</legend>
		<div>当前共有&nbsp;&nbsp;<a href="Mwsissuetb.do?act=list"><%out.println(request.getAttribute("task")); %></a>笔我接收的任务</div>
		</fieldset>
	</div>
</body>
</html>