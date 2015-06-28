<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Payment" %>
<%@ page import="edu.nju.cineplex.utildata.Consumption" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看会员 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="row div-gap">
	<form action="CheckUser.waiter" class="form-inline" method="get">
	  <div class="form-group">
	    <input type="text" class="form-control" id="userid" name="u" placeholder="会员卡号">
	  </div>
	  <button type="submit" class="btn btn-primary">查看</button>
	</form>
</div>
<%
	if((boolean)request.getAttribute("b")){
%>
<jsp:useBean id="userInfo" type="edu.nju.cineplex.model.UserInfo" scope="request" />
<jsp:useBean id="consumptions" type="java.util.List<Consumption>" scope="request" />
<jsp:useBean id="conItem" class="edu.nju.cineplex.utildata.Consumption" scope="page" />
<jsp:useBean id="payments" type="java.util.List<Payment>" scope="request" />
<jsp:useBean id="payItem" class="edu.nju.cineplex.model.Payment" scope="page" />

<div class="div-gap">
	<ul id="infoTab" class="nav nav-tabs div-gap">
	   <li class="active">
	      <a href="#info" data-toggle="tab">
	         	个人资料
	      </a>
	   </li>
	   <li>
	   		<a href="#consumption" data-toggle="tab">
	   			消费记录
	   		</a>
	   </li>
	   <li>
	      <a href="#pay" data-toggle="tab">
	      		交费记录
	      </a>
	   </li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active div-gap" id="info">
			<div class="row">
				<div class="col-md-offset-3 col-md-9" id="info-show">
				<p>
					<span class="mylabel">会员号：</span>
					<jsp:getProperty name="userInfo" property="userid" />
				</p>
				<p>
					<span class="mylabel">会员名称：</span>
					<jsp:getProperty name="userInfo" property="username" />
				</p>
				<p>
					<span class="mylabel">银行卡号：</span>
					<jsp:getProperty name="userInfo" property="cardid" />
				</p>
				<p>
					<span class="mylabel">用户状态：</span>
					<jsp:getProperty name="userInfo" property="level" />
				</p>
				<p>
					<span class="mylabel">积分：</span>
					<jsp:getProperty name="userInfo" property="credit" />
				</p>
				<p>
					<span class="mylabel">余额：</span>
					<jsp:getProperty name="userInfo" property="balance" />
				</p>
				<p>
					<span class="mylabel">年龄：</span>
					<jsp:getProperty name="userInfo" property="age" />
				</p>
				<p>
					<span class="mylabel">性别：</span>
					<jsp:getProperty name="userInfo" property="gender" />
				</p>
				<p>
					<span class="mylabel">地址：</span>
					<jsp:getProperty name="userInfo" property="location" />
				</p>
				</div>
			</div>
		</div>
		
		<div class="tab-pane fade in div-gap" id="consumption">
			<div class="row">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>影片</th>
							<th>放映时间</th>
							<th>价格</th>
							<th>购买时间</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(int i=0;i<consumptions.size();i++){
							pageContext.setAttribute("conItem", consumptions.get(i));
					%>
						<tr>
							<td><%=i+1 %></td>					
							<td><jsp:getProperty name="conItem" property="moviename" /></td>				
							<td><jsp:getProperty name="conItem" property="plandate" />&nbsp;<jsp:getProperty name="conItem" property="plantime" /></td>	
							<td><jsp:getProperty name="conItem" property="singlePrice" /></td>					
							<td><jsp:getProperty name="conItem" property="payDatetime" /></td>					
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="tab-pane fade in div-gap" id="pay">
			<div class="row">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>时间</th>
							<th>充值金额</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(int i=0;i<payments.size();i++){
							pageContext.setAttribute("payItem", payments.get(i));
					%>
						<tr>
							<td><%=i+1 %></td>
							<td><jsp:getProperty name="payItem" property="datetime" /></td>					
							<td><jsp:getProperty name="payItem" property="sum" /></td>					
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%
	}else{
%>
	<p class="text-center">无记录</p>
<%
	}
%>
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});

</script>
</body>
</html>