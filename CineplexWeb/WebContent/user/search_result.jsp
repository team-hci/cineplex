<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Movie" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="movies" type="java.util.List<Movie>" scope="request" />
<jsp:useBean id="movieItem" class="edu.nju.cineplex.model.Movie" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

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
 			<a href="/CineplexWeb/MovieDetail?m=<jsp:getProperty name="movieItem" property="movieid" />" class="thumbnail">
				<img alt="<jsp:getProperty name="movieItem" property="moviename" />" title="<jsp:getProperty name="movieItem" property="moviename" />" src="<jsp:getProperty name="movieItem" property="movieposter" />">
				<span class="text-overflow"><nobr><jsp:getProperty name="movieItem" property="moviename" /></nobr></span>
				<span class="right smallgrade"><jsp:getProperty name="movieItem" property="moviegrade" /></span>
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

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</body>
</html>