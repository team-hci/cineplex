<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Movie" %>
<%@ page import="edu.nju.cineplex.utildata.PlanItem" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制定放映计划 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/pages/header.jsp" flush="true" />

<jsp:useBean id="movies" type="java.util.List<Movie>" scope="request" />
<jsp:useBean id="item" class="edu.nju.cineplex.model.Movie" scope="page" />
<jsp:useBean id="plans" type="java.util.List<PlanItem>" scope="request" />
<jsp:useBean id="planItem" class="edu.nju.cineplex.utildata.PlanItem" scope="page" />

<div id="make_plan">
	<h2 class="bottom-line">制定放映计划</h2>
	
	<div class="div-gap">
	<ul class="nav nav-tabs">
	<li class="active">
	<p class="mylabel text-center">现有计划</p>
	</li>
	</ul>
		
		<% if(plans.size() > 0){ %>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>电影名称</th>
					<th>大厅</th>
					<th>时间</th>
					<th>票价</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(int i=0;i<plans.size();i++){
					pageContext.setAttribute("planItem", plans.get(i));
			%>
				<tr>
					<td><jsp:getProperty name="planItem" property="moviename" /></td>
					<td><jsp:getProperty name="planItem" property="hall" /></td>
					<td>
						<jsp:getProperty name="planItem" property="plandate" />
						&nbsp;<jsp:getProperty name="planItem" property="plantime" />
					</td>
					<td><jsp:getProperty name="planItem" property="planprice" /></td>
				</tr>
			<%
				}
			%>
			</tbody>
		</table>
		<% } else { %>
			<p class="text-center">暂无</p>
		<% } %>
		
	</div>
	
	<br />
	<form action="SubmitPlan" method="post" class="form-horizontal" onsubmit="return checkPlan();"> <!-- enctype="multipart/form-data" -->
		<div class="form-group">
			<label for="movie_name" class="col-sm-2 control-label">电影</label>
			<div class="col-sm-9">
				<select class="form-control" id="movie" name="movie">
					<%
						for(int i=0;i<movies.size();i++){
							pageContext.setAttribute("item", movies.get(i));
					%>
						<option value="<jsp:getProperty name="item" property="movieid" />"><jsp:getProperty name="item" property="moviename" />&nbsp;(<jsp:getProperty name="item" property="moviebegin" />~<jsp:getProperty name="item" property="movieover" />)</option>
					<%
						}
					%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="movie_description" class="col-sm-2 control-label">大厅</label>
			<div class="col-sm-9">
				<select class="form-control" id="hall" name="hall">
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="director" class="col-sm-2 control-label">时间</label>
			<div class="col-sm-5">
				<input type="date" class="form-control" name="date" id="date" placeholder="yyyy-MM-dd" />
			</div>
			<div class="col-sm-4">
				<input type="time" class="form-control" name="time" id="time" placeholder="HH:mm" />
			</div>
		</div>
		<div class="form-group">
			<label for="movie_price" class="col-sm-2 control-label">价格</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="price" id="price" placeholder="请填入整数" />
			</div>
		</div>
		<input type="hidden" value="0" id="planid" name="planid" />
		<div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">提交</button>
		      <button type="reset" class="btn btn-default">重置</button>
		      <label class="hint" id="hint"></label>
		    </div>
		</div>
	</form>
</div>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	<%
		PlanItem plan = (PlanItem)request.getAttribute("plan");
		if(plan!=null){
	%>
	$("#movie").val("<%=plan.getMovieid() %>");
	$("#hall").val("<%=plan.getHall() %>");
	$("#date").val("<%=plan.getPlandate() %>");
	$("#time").val("<%=plan.getPlantime() %>");
	$("#price").val("<%=plan.getPlanprice() %>");
	$("#planid").val("<%=plan.getPlanid() %>");
	<%
		}
	%>
});

function checkPlan(){
	var mid = $("#movie").val();
	var hall = $("#hall").val();
	var date = $("#date").val();
	var time = $("#time").val();
	var price = $("#price").val();
	
	if(mid=="" || hall=="" || date=="" || time=="" || price==""){
		$("#hint").html("请填写完整信息！");
		return false;
	}else{

		var re = /^[1-9]+[0-9]*]*$/;
		if(!re.test(price)){
			$("#hint").html("价格为正整数！");
			return false;
			}
		}
	
	return true;
}
</script>
</body>
</html>