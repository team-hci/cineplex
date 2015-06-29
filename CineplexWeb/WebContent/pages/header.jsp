<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/CineplexWeb">LongestMovie</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()+"/MovieList" %>">电影</a></li>
				<li><a href="<%=request.getContextPath()+"/ActivityList" %>">活动</a></li>
				<li><a href="<%=request.getContextPath()+"/Help" %>">帮助</a></li>
				<%
					if(session.getAttribute("userid")==null){//游客
				%>
				<li><a href="javascript:void(0);" data-toggle="modal" data-target="#lr-modal">登录/注册</a></li>
				<%
					}else if(((String)session.getAttribute("userlevel")).equals("经理")){//经理
				%>
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		          <%=session.getAttribute("username") %>
		          <span class="caret"></span>
		          </a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="<%=request.getContextPath()+"/CheckPlan.manager" %>">审查放映计划</a></li>
		            <li><a href="<%=request.getContextPath()+"/MemberStatistics.manager"%>">会员信息统计</a></li>
		            <li><a href="<%=request.getContextPath()+"/MonthlyStatistics.manager"%>">影院使用统计</a></li>
		            <li class="divider"></li>
		            <li><a href="Logout">退出</a></li>
		          </ul>
		        </li>
				<%
					}else if(((String)session.getAttribute("userlevel")).equals("服务员")){//服务员
				%>
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		          <%=session.getAttribute("username") %>
		          <span class="caret"></span>
		          </a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="<%=request.getContextPath()+"/MakePlan.waiter"%>">制定放映计划</a></li>
		            <li><a href="<%=request.getContextPath()+"/AddMovie.waiter"%>">添加影片</a></li>
		            <li><a href="<%=request.getContextPath()+"/MakeActivity.waiter"%>">制定活动</a></li>
		            <li><a href="<%=request.getContextPath()+"/CheckUser.waiter"%>">查询会员</a></li>
		            <li><a href="<%=request.getContextPath()+"/ModifyPlan.waiter"%>">修改放映计划</a></li>
		            <li class="divider"></li>
		            <li><a href="Logout">退出</a></li>
		          </ul>
		        </li>
				<%
					}else{//会员
				%>
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		          <%=session.getAttribute("username") %>
		          <span class="caret"></span>
		          </a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="<%=request.getContextPath()+"/PersonalInfo" %>">个人信息</a></li>
		            <!-- <li><a href="#">订单</a></li>
		            <li><a href="#">活动</a></li> -->
		            <li class="divider"></li>
		            <li><a href="Logout">退出</a></li>
		          </ul>
		        </li>
				<%
					}
				%>
			</ul>
		</div>
    </div>
</nav>
<div class="modal fade" id="lr-modal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-body">
        <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#login-pane" aria-controls="login-pane" role="tab" data-toggle="tab">登录</a></li>
		    <li role="presentation"><a href="#register-pane" aria-controls="register-pane" role="tab" data-toggle="tab">注册</a></li>
		  </ul>
        <div class="tab-content">
		  <div role="tabpanel" class="tab-pane fade in active" id="login-pane">
		 	<form role="form" action="Login" method="post" onsubmit="return login();">
			  <div class="form-group">
			    <input type="text" class="form-control" name="id" id="id" placeholder="会员卡号">
			  </div>
			  <div class="form-group">
			    <input type="password" class="form-control" name="password" id="password" placeholder="密码">
			  </div>
			  <button type="submit" class="btn btn-primary">登录</button>
			  <label id="login_hint"></label>
			</form>
		  </div>
		  <div role="tabpanel" class="tab-pane fade" id="register-pane">
		  	<form role="form" action="Register" method="post" onsubmit="return register();">
		  	  <div class="form-group">
			    <input type="text" class="form-control" name="name" id="rname" placeholder="姓名">
			  </div>
			  <div class="form-group">
			    <input type="text" class="form-control" name="credit_card" id="rcredit_card" placeholder="银行卡号">
			  </div>
			  <div class="form-group">
			    <input type="password" class="form-control" name="password" id="rpassword" placeholder="密码">
			  </div>
			  <div class="form-group">
			    <input type="password" class="form-control" name="password_repeat" id="rpassword_repeat" placeholder="重复密码">
			  </div>
			  <button type="submit" class="btn btn-primary">注册</button>
			  <button type="reset" class="btn btn-default">重置</button>
			  <label id="register_hint"></label>
			</form>
		  </div>
		</div>
      </div>

    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
var xmlHttp;
var loading = "<img src='/CineplexWeb/pic/loading.gif'>";

function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}

function processor1(){
	if (xmlHttp.readyState==4 && xmlHttp.status==200){
		var response = xmlHttp.responseText;
		response = response.trim();
		if(response=="yes"){
			location.reload();
		}else if(response=="no"){
			document.getElementById("login_hint").innerHTML="用户名或密码错误！";
			document.getElementById("password").value = "";
			document.getElementById("password").focus();
		}
	}
}

function processor2(){
	if (xmlHttp.readyState==4 && xmlHttp.status==200){
		var response = xmlHttp.responseText;
		response = response.trim();
		if(response!="no"){
			alert("注册成功！请记住你的会员号是："+response);
			location.reload();
		}else if(response=="no"){
			document.getElementById("register_hint").innerHTML="你的卡号已被注册！";
		}
	}
}

function login(){
	document.getElementById("login_hint").innerHTML = loading;
	var id = document.getElementById("id").value;
	var password = document.getElementById("password").value;
	if(id=='' || password==''){
		document.getElementById("login_hint").innerHTML="请填写完整！";
	}
	else{
		createXMLHttpRequest();
		xmlHttp.onreadystatechange=processor1;
		xmlHttp.open("POST","Login",true);
		xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttp.send("id="+id+"&password="+password);
	}
	return false;
}

function register(){
	document.getElementById("register_hint").innerHTML=loading;
	var name = document.getElementById("rname").value;
	var creditCard = document.getElementById("rcredit_card").value;
	var password = document.getElementById("rpassword").value;
	var passwordRepeat = document.getElementById("rpassword_repeat").value;
	if(name=='' || creditCard=='' || password=='' || passwordRepeat==''){
		document.getElementById("register_hint").innerHTML="请填写完整！";
	}else if(password!=passwordRepeat){
		document.getElementById("register_hint").innerHTML="两次密码不一致！";
	}else{
		createXMLHttpRequest();
		xmlHttp.onreadystatechange=processor2;
		xmlHttp.open("POST","Register",true);
		xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		name = encodeURI(encodeURI(name));
		xmlHttp.send("name="+name+"&creditCard="+creditCard+"&password="+password);
	}
	return false;
}

function checkKey(){
	var k = document.getElementById("searchKey").value;
	if(k=='')
		return false;
	return true;
}
</script>

<div class="container">
  
  	<div id="top-bar" class="row">
  		<div id="top-left" class="col-md-3">
  			<a href="/CineplexWeb"><img alt="" src="/CineplexWeb/pic/logo.jpg"></a>
  		</div>
	  	<div id="top-middle" class="col-md-9">
	  		<form action="SearchMovie" method="get" onsubmit="return checkKey();">
		  		<div class="input-group">
		           <input type="text" class="form-control" id="searchKey" name="k" placeholder="搜索电影">
		           <span class="input-group-btn">
		              <button class="btn btn-default" type="submit" id="search-button">
		              	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		              </button>
		           </span>
		        </div><!-- /input-group -->
	        </form>
	  	</div>
<!--   		<div id="top-right" class="col-md-4">
  		热门搜索：
  		</div> -->
  	</div>