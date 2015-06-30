<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Activity" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="activities" type="java.util.List<Activity>" scope="request" />
<jsp:useBean id="activityItem" class="edu.nju.cineplex.model.Activity" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="row div-gap">
	<ul class="nav nav-pills">
	  <li role="presentation" id="sort1"><a href="/CineplexWeb/ActivityList?sort=1">最新</a></li>
	  <li role="presentation" id="sort2"><a href="/CineplexWeb/ActivityList?sort=2">最热</a></li>
	</ul>
</div>

<div class="row div-gap top-line">
	<%
		for(int i=0;i<activities.size();i++){
			pageContext.setAttribute("activityItem", activities.get(i));
	%>
	<div class="col-md-6 col-sm-6 col-xs-12 activity-summary">
		<div class="row">
		<h4>
		<a class="movie_type" href="/CineplexWeb/ActivityDetail?a=<jsp:getProperty name="activityItem" property="activityid" />">
			<span class="glyphicon glyphicon-certificate"></span><jsp:getProperty name="activityItem" property="title" />
		</a>
		</h4>
		</div>
		<div class="row">
			<div class="col-md-8 col-sm-8 col-xs-8">
				<span class="mylabel"><jsp:getProperty name="activityItem" property="acount" />人参与</span>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<jsp:getProperty name="activityItem" property="activitytime" />
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>

<%
	if((int)request.getAttribute("totalPage")>1){
%>
<div class="row div-gap">
	<div class="col-md-offset-5 col-md-7">
	<nav>
		<ul class="pagination">
			<li id="previous">
		      <a href="/CineplexWeb/MovieList?sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")-1 %>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		<%
			for(int i=0;i<(int)request.getAttribute("totalPage");i++){
		%>
		    <li id="page<%=i %>"><a href="/CineplexWeb/MovieList?sort=<%=request.getAttribute("sort") %>&page=<%=i %>"><%=i %></a></li>
		<%
			}
		%>
		    <li id="next">
		      <a href="/CineplexWeb/MovieList?sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")+1 %>" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		</ul>
	</nav>
	</div>
</div>
<%
	}
%>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".activityLab").addClass("active");
	$("#sort"+<%=request.getAttribute("sort") %>).addClass("active");
	$("#page"+<%=request.getAttribute("page") %>).addClass("active");
	if(<%=request.getAttribute("page") %>==1){
		$("#previous").addClass("disabled");
	}
	else if(<%=request.getAttribute("page") %>==<%=request.getAttribute("totalPage") %>){
		$("#next").addClass("disabled");
	}
});
</script>
</body>
</html>