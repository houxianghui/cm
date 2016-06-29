<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="/WEB-INF/tld/eis-auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/eis-htm.tld" prefix="htm"%>


<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.action.Action"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>
<%@ page import="org.apache.struts.validator.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%@ page import="com.eis.cache.*"%>
<%@ page import="com.eis.portal.UserContext"%>
<%@ page import="com.eis.util.*"%>


<%@ page errorPage="/common/error.jsp"%>

<%
	UserContext user = (UserContext) session.getAttribute("user");
	try {

		if (user == null) {
%>

<script language="JavaScript">
window.alert('\u4f1a\u8bdd\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55');
parent.parent.parent.location="<%=request.getContextPath()%>/index.jsp";
</script>

<%
	return;
		}

	} catch (Throwable t) {
		t.printStackTrace();
	}
%>


<%
	String css = "Style.css";
	String css_temp = (String) session.getAttribute("css");
	if (null != css_temp) {
		css = css_temp;
	}
%>



<link href="CSS/<%=css%>" rel="stylesheet" type="text/css">
<link href="CSS/tonglink.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="CSS/redmond/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="CSS/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="CSS/validate.css" />


	
<link id="favicon" href="BMAC_favicon.ico" rel="icon" type="image/x-icon" />
<SCRIPT src="js/validate.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/event.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/func.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/progressBar.js" type="text/javascript"></SCRIPT>

<script src="js/jquery-1.8.0.min.js" type="text/javascript" ></script>
<script src="js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.src.js" type="text/javascript"></script>
<script src="js/easy_validator.pack.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript"></script>

<script type="text/javascript" src="js/jquery.cookie.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("table tr:nth-child(even)").css("background", "white");
	});
</script>