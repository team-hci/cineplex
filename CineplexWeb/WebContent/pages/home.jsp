<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Movie" %>
<%@ page import="edu.nju.cineplex.model.Activity" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%--  <%@include file="/pages/header.jsp" %>  --%>

<jsp:useBean id="newMovies" type="java.util.List<Movie>" scope="request" />
<jsp:useBean id="newItem" class="edu.nju.cineplex.model.Movie" scope="page" />
<jsp:useBean id="newActivities" type="java.util.List<Activity>" scope="request" />
<jsp:useBean id="activityItem" class="edu.nju.cineplex.model.Activity" scope="page" />
<jsp:useBean id="comingMovies" type="java.util.List<Movie>" scope="request" />
<jsp:useBean id="comingItem" class="edu.nju.cineplex.model.Movie" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />
    

  	
	<div id="demo1" class="slideBox div-gap" style="margin-left:auto;margin-right:auto">
	  <ul class="items">
	    <li><a href="<%=request.getContextPath() %>/MovieDetail?m=40" title="黑暗骑士"><img src="img/1.jpg"></a></li>
	    <li><a href="<%=request.getContextPath() %>/MovieDetail?m=41" title="霍比特人"><img src="img/2.jpg"></a></li>
	    <li><a href="<%=request.getContextPath() %>/MovieDetail?m=42" title="沉睡魔咒"><img src="img/3.jpg"></a></li>
	    <li><a href="<%=request.getContextPath() %>/MovieDetail?m=43" title="星际穿越"><img src="img/4.jpg"></a></li>
	    <li><a href="<%=request.getContextPath() %>/MovieDetail?m=41" title="霍比特人"><img src="img/5.jpg"></a></li>
	  </ul>
	</div>
  
  	<div id="overview">
  		<div class="row">
  			<div id="newest_activities_overview" class="col-md-6">
  				<h4 class="bottom-line"><span class="glyphicon glyphicon-pencil"  aria-hidden="true"></span>&nbsp;最新活动</h4>
		  	<%
		  		int length = newActivities.size();
		  		int showLen = length<10?length:10;
		  		for(int i=0;i<showLen;i++){
		  			pageContext.setAttribute("activityItem", newActivities.get(i));
		  	%>
		  		<div class="col-md-12 col-sm-12 col-xs-12 item">
		  			<a href="/CineplexWeb/ActivityDetail?a=<jsp:getProperty name="activityItem" property="activityid" />">
		  				<jsp:getProperty name="activityItem" property="title" />
		  			</a>
		  			<span class="mylabel right">
		  				<jsp:getProperty name="activityItem" property="activitytime" />
		  			</span>
		  		</div>
			<%
		  		}
			%>
  			</div>
  			<div id="coming_movies_overview" class="col-md-6">
  				<h4 class="bottom-line"><span class="glyphicon glyphicon-facetime-video"  aria-hidden="true"></span>&nbsp;即将上映</h4>
		  	<%
		  		int length2 = comingMovies.size();
		  		int showLen2 = length2<10?length2:10;
		  		for(int i=0;i<showLen2;i++){
		  			pageContext.setAttribute("comingItem", comingMovies.get(i));
		  	%>
		  		<div class="col-md-12 col-sm-12 col-xs-12 item">
		  			<a href="/CineplexWeb/MovieDetail?m=<jsp:getProperty name="comingItem" property="movieid" />">
		  				<jsp:getProperty name="comingItem" property="moviename" />
		  			</a>
		  			<span class="mylabel right">
		  				<jsp:getProperty name="comingItem" property="moviebegin" />上映
		  			</span>
		  		</div>
			<%
		  		}
			%>
  			</div>
  		</div>
  	</div>
  	
  	<div id="newest_movies_overview">
  		<div class="row">
  			<div class="col-md-12">
  				<h4 class="bottom-line"><span class="glyphicon glyphicon glyphicon-film"  aria-hidden="true"></span>&nbsp;最新电影</h4>
  			</div>
  		</div>
  	<%
  		for(int i=0;i<newMovies.size();i++){
  			pageContext.setAttribute("newItem", newMovies.get(i));
  	%>
  		<div class="col-md-2 col-sm-3 col-xs-4 movie_img">
  			<a href="/CineplexWeb/MovieDetail?m=<jsp:getProperty name="newItem" property="movieid" />" class="thumbnail">
  				<img title="<jsp:getProperty name="newItem" property="moviename" />" alt="<jsp:getProperty name="newItem" property="moviename" />" src="<jsp:getProperty name="newItem" property="movieposter" />">
  				<span class="text-overflow"><nobr><jsp:getProperty name="newItem" property="moviename" /></nobr></span>
				<span class="right smallgrade"><jsp:getProperty name="newItem" property="moviegrade" /></span>
  			</a>
  		</div>
	<%
  		}
	%>
  	</div>
    
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script src="/CineplexWeb/js/jquery.slideBox.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#demo1').slideBox();
});
</script>
</body>
</html>