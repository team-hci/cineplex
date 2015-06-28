<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Movie" %>
<%@ page import="edu.nju.cineplex.model.Type" %>
<%@ page import="edu.nju.cineplex.model.Region" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大片 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="movies" type="java.util.List<Movie>" scope="request" />
<jsp:useBean id="movieItem" class="edu.nju.cineplex.model.Movie" scope="page" />
<jsp:useBean id="types" type="java.util.List<Type>" scope="request" />
<jsp:useBean id="typeItem" class="edu.nju.cineplex.model.Type" scope="page" />
<jsp:useBean id="regions" type="java.util.List<Region>" scope="request" />
<jsp:useBean id="regionItem" class="edu.nju.cineplex.model.Region" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="row div-gap">
	<div class="col-md-1 text-center" style="padding-top:10px">
		类型：
	</div>
	<div class="col-md-11">
		<ul class="nav nav-pills">
		<li role="presentation" id="type20"><a class="movie_type" href="/CineplexWeb/MovieList?type=20&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>">
			全部
		</a></li>
	<%
		for(int i=0;i<types.size();i++){
			pageContext.setAttribute("typeItem", types.get(i));
	%>
		<li role="presentation" id="type<jsp:getProperty name="typeItem" property="typeid" />"><a class="movie_type" href="/CineplexWeb/MovieList?type=<jsp:getProperty name="typeItem" property="typeid" />&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>">
			<jsp:getProperty name="typeItem" property="typename" />
		</a></li>
	<%
		}
	%>
		</ul>
	</div>
</div>
<div class="row">
	<div class="col-md-1 text-center" style="padding-top:10px">
		地区：
	</div>
	<div class="col-md-11">
		<ul class="nav nav-pills">
		<li role="presentation" id="region10"><a class="region" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=10&sort=<%=request.getAttribute("sort") %>">
			全部
		</a></li>
	<%
		for(int i=0;i<regions.size();i++){
			pageContext.setAttribute("regionItem", regions.get(i));
	%>
		<li role="presentation" id="region<jsp:getProperty name="regionItem" property="regionid" />"><a class="region" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<jsp:getProperty name="regionItem" property="regionid" />&sort=<%=request.getAttribute("sort") %>">
			<jsp:getProperty name="regionItem" property="regionname" />
		</a></li>
	<%
		}
	%>
		</ul>
	</div>
</div>

<div class="row div-gap">
	<div class="col-md-11">
		<ul class="nav nav-pills">
		<li role="presentation" id="sort1"><a class="sort_rule" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=1">
			最新
		</a></li>
		<li role="presentation" id="sort2"><a class="sort_rule" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=2">
			最热
		</a></li>
		<li role="presentation" id="sort3"><a class="sort_rule" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=3">
			好评
		</a></li>
		</ul>
	</div>
<%-- 	<div class="col-md-2">
		<a class="page" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")-1 %>">
			上一页
		</a>
		<a class="page" href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")+1 %>">
			下一页
		</a>
	</div> --%>
</div>

<div id="movie_list" class="div-gap top-line">
	<%
		if(movies.size()==0){
	%>
		<p class="text-center">暂无影片</p>
	<%
		}else{
		for(int i=0;i<movies.size();i++){
			pageContext.setAttribute("movieItem", movies.get(i));
			int row = 0;
			int columnCount = 6;
			//if(i+1-row*columnCount>columnCount){
			//	row ++;
	%>
  	<!-- <div class="row"> -->
  	<%
		//	}
  	%>
  		<div class="col-md-2 col-sm-3 col-xs-4 movie_img">
 			<a href="/CineplexWeb/MovieDetail?m=<jsp:getProperty name="movieItem" property="movieid" />" class="thumbnail remove-line">
				<img alt="<jsp:getProperty name="movieItem" property="moviename" />" title="<jsp:getProperty name="movieItem" property="moviename" />" src="<jsp:getProperty name="movieItem" property="movieposter" />">
				<span class="text-overflow"><nobr><jsp:getProperty name="movieItem" property="moviename" /></nobr></span>
				<span class="right smallgrade"><jsp:getProperty name="movieItem" property="moviegrade" /></span>
				<%-- <br />
				<span class="description"><nobr><jsp:getProperty name="movieItem" property="moviedescription" /></nobr></span> --%>
			</a>
		</div>
	<%
		//	if(i+1-row*columnCount==columnCount){
	%>
	<!-- </div> -->
	<%
		//	}
		}
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
		      <a href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")-1 %>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		<%
			for(int i=0;i<(int)request.getAttribute("totalPage");i++){
		%>
		    <li id="page<%=i %>"><a href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>&page=<%=i %>"><%=i %></a></li>
		<%
			}
		%>
		    <li id="next">
		      <a href="/CineplexWeb/MovieList?type=<%=request.getAttribute("type") %>&region=<%=request.getAttribute("region") %>&sort=<%=request.getAttribute("sort") %>&page=<%=(int)request.getAttribute("page")+1 %>" aria-label="Next">
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
	$("#type"+<%=request.getAttribute("type") %>).addClass("active");
	$("#region"+<%=request.getAttribute("region") %>).addClass("active");
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