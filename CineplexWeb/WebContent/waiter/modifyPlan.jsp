<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.utildata.PlanItem" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制定放映计划 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="plans" type="java.util.List<PlanItem>" scope="request" />
<jsp:useBean id="planItem" class="edu.nju.cineplex.utildata.PlanItem" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="div-gap bottom-line">
	<h3>未通过计划</h3>
</div>
<%
	if(plans.size()>0){
%>
<div class="div-gap">
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
				<td>
					<a href="<%=request.getContextPath()+"/MakePlan.waiter"%>?p=<jsp:getProperty name="planItem" property="planid" />">修改</a>
				</td>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
</div>
<%
	}else{
%>
<div class="div-gap">
	<p class="text-center mylabel">暂无</p>
</div>
<%
	}
%>
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});

function checkPlan(){
	var mid = $("#movie").val();
	var hall = $("#hall").val();
	var date = $("#date").val();
	var time = $("#time").val();
	var price = $("#price").val();
	
	if(mid=="" || hall=="" || date=="" || time=="" || price==""){
		alert("fuck");
		$("#hint").html("请填写完整信息！");
		return false;
	}
	
	return true;
}
</script>
</body>
</html>