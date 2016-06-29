
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>


<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>


<%@page import="java.util.Random"%>

<%@ page contentType="text/html; charset=GBK"%>

<%
	session.setAttribute("css", "Style.css");
	Random random = new Random();
	int n = random.nextInt(1000);
	String num = Integer.toString(n);
	session.setAttribute("checknum", num);
%>

<html><head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type">
  <meta content="noindex, nofollow" name="robots">
  <meta content="noindex, nofollow" name="googlebot">
  <style type="text/css">
    body {
    background: url('keys.jpg') no-repeat fixed center center;
    background-size: cover;
    font-family: "Helvetica Neue","Luxi Sans","DejaVu Sans",Tahoma,"Hiragino Sans GB","Microsoft Yahei",sans-serif;
}

.center {
  position: absolute;
  width: 400px;
  height: 360px;
  z-index: 15;
  top: 50%;
  left: 50%;
  margin: -230px 0 0 -200px;
}

.logo {
    width: 239px;
    height: 55px;
    background: url('logo.png') no-repeat;
    margin: 30px auto;
}

.login-block {
    width: 360px;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    border-top: 5px solid #ff656c;
    margin: 0 auto;
}

.login-block h1 {
    text-align: center;
    color: #000;
    font-size: 18px;
    text-transform: uppercase;
    margin-top: 0;
    margin-bottom: 20px;
}

.login-block input {
    width: 100%;
    height: 42px;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 14px;
    padding: 0 20px 0 50px;
    outline: none;
}

.login-block input#username {
    background: #fff url('user.png') 20px top no-repeat;
    background-size: 16px 80px;
}

.login-block input#username:focus {
    background: #fff url('user.png') 20px bottom no-repeat;
    background-size: 16px 80px;
}

.login-block input#password {
    background: #fff url('pass.png') 20px top no-repeat;
    background-size: 16px 80px;
}

.login-block input#password:focus {
    background: #fff url('pass.png') 20px bottom no-repeat;
    background-size: 16px 80px;
}

.login-block input:active, .login-block input:focus {
    border: 1px solid #ff656c;
}

.login-block button {
    width: 100%;
    height: 40px;
    background: #ff656c;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #e15960;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 14px;
    outline: none;
    cursor: pointer;
}

.login-block button:hover {
    background: #ff7b81;
}

  </style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>登录</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>

<SCRIPT src="<%=request.getContextPath()%>/js/validate.js"
	type="text/javascript"></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/event.js"
	type="text/javascript"></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/func.js"
	type="text/javascript"></SCRIPT>

<script language="javascript">
	function checkLogin() {
		if (document.forms[0].login_id.value == ""
				|| document.forms[0].password.value == "") {
			alert(" 请输入用户名和密码！ ");
			return false;
		}
		return true;

	}
	function Login() {
		if (document.forms[0].login_id.value == ""
				|| document.forms[0].password.value == "") {
			alert(" 请输入用户名和密码！ ");
			return;
		}
		document.forms[0].submit();
	}

	//<!--
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}

	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
//-->
</script>
<link id="favicon" href="BMAC_favicon.ico" rel="icon" type="image/x-icon" />


<html:form method="post" action="Login.do" onsubmit="return checkLogin()">
<input name="random" type="hidden" value="<%=num%>">
<input name="PIXEL" type="hidden">

<script>
	document.forms[0].PIXEL.value = window.screen.width;

	function Keydown(e) {
		if (navigator.appName == "Netscape") {
			if (e.which == 13) {//检测键盘，输入回车，提交表单
				if (document.forms[0].login_id.value == ""
						|| document.forms[0].password.value == "") {
					alert(" 请输入用户名和密码！ ");
					return;
				}
				document.forms[0].submit();
			}
		} else {
			if (event.keyCode == 13) {//检测键盘，输入回车，提交表单
				if (document.forms[0].login_id.value == ""
						|| document.forms[0].password.value == "") {
					alert(" 请输入用户名和密码！ ");
					return;
				}
				document.forms[0].submit();
			}
		}
	}
	document.onkeydown = Keydown;
</script>

        
        
<table width="100%" border="0" cellspacing="1" cellpadding="0" style="background: #ffffff">


<div class="center">
    <div class="logo"></div>
    <div class="login-block">
        <h1>密钥卡及模块发行管理系统</h1>
         <input type="text" name="login_id"  placeholder="用户名"  value="">
         <input type="password" name="password"   placeholder="密码" value="">
         <button>登录</button>
      
     </div>
</div>
</table>
</html:form>

