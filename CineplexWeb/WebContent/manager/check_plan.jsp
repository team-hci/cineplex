<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.utildata.PlanItem" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审查放映计划 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="plans" type="java.util.List<PlanItem>" scope="request" />
<jsp:useBean id="planItem" class="edu.nju.cineplex.utildata.PlanItem" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="div-gap bottom-line">
	<h3>审查放映计划</h3>
</div>
<div class="div-gap">
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
				<td>
					<button class="btn btn-link" type="button" onclick="deal('pass',<jsp:getProperty name="planItem" property="planid" />)">批准</button>
					<button class="btn btn-link" type="button" onclick="deal('fail',<jsp:getProperty name="planItem" property="planid" />)">拒绝</button>	
				</td>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
	<% } else { %>
		<p class="text-center">暂无待审核计划</p>
	<% } %>
</div>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$(".checkPlanLab").addClass("active");
});

function deal(operation,planid){
	$.ajax({
		type : 'post',
		url: "CheckPlan.manager",
		dataType:'text',
		data : {
	      "operation":operation,
	      "planid":planid
		},
		success: function(data){
	      if(data=="y"){
	    	  location.reload();
	      }else if(data=="n"){
	    	  alert("操作失败！");
	      }
		}
	});	
}

</script>
</body>
</html>