<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:useBean id="movieInfo" type="edu.nju.cineplex.utildata.MovieInfo" scope="request" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><jsp:getProperty name="movieInfo" property="moviename" /> - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="/pages/header.jsp" flush="true" />

<div id="movie_detail" class="top-line div-gap row">
	<div class="col-md-3 col-sm-4 col-xs-12 col-md-offset-1">
		<img alt="" title="" src="<jsp:getProperty name="movieInfo" property="movieposter" />">
		<div class="row">
		<%
			int valuation = (int)request.getAttribute("valuation");
			if(valuation>0){
		%>
		<span class="largegrade"><jsp:getProperty name="movieInfo" property="grade" /></span><span class="mylabel">（<jsp:getProperty name="movieInfo" property="gcount" />评分）</span>
			<span>已评<%=valuation %>分</span>
		<%
			}else if(session.getAttribute("userid")!=null){
		%>
			<form action="Evaluate" method="post" class="form-inline" style="margin-left: 20px;">
			<span class="largegrade"><jsp:getProperty name="movieInfo" property="grade" /></span><span class="mylabel">（<jsp:getProperty name="movieInfo" property="gcount" />评分）</span>
				<select class="form-control" id="valuation" name="valuation" style="width:100px">
				  <option value="10">10分</option>
				  <option value="9">9分</option>
				  <option value="8">8分</option>
				  <option value="7">7分</option>
				  <option value="6">6分</option>
				  <option value="5">5分</option>
				  <option value="4">4分</option>
				  <option value="3">3分</option>
				  <option value="2">2分</option>
				  <option value="1">1分</option>
				</select>
				<input type="hidden" name="movieid" value="<jsp:getProperty name="movieInfo" property="movieid" />" />
				<button type="submit" class="btn btn-link">评</button>
			</form>	
		<%
			}
		%>		
		</div>
	</div>
	<div class="col-md-8 col-sm-5 col-xs-12 movie_words">
		<div class="row">
			<h3><jsp:getProperty name="movieInfo" property="moviename" /></h3>
		</div>
		<br />
		<div class="row">
			<p>
				<span class="mylabel">导演：</span>
			<%
				String[] directors = movieInfo.getDirectors();
				out.print(directors[0]);
				for(int i=1;i<directors.length;i++){
					out.print("/"+directors[i]);
				}
			%>
			</p>
			<p>
				<span class="mylabel">主演：</span>
			<%
				String[] actors = movieInfo.getActors();
				out.print(actors[0]);
				for(int i=1;i<actors.length;i++){
					out.print("/"+actors[i]);
				}
			%>
			</p>
<%-- 			<p>
				<span class="mylabel">片长：&nbsp;</span><jsp:getProperty name="movieInfo" property="movielength" />
				
			</p> --%>
			<p>
				<span class="mylabel">类型：</span>
			<%
				String[] types = movieInfo.getTypes();
				out.print(types[0]);
				for(int i=1;i<types.length;i++){
					out.print("/"+types[i]);
				}
			%>
			</p>
			<p>
				<span class="mylabel">地区：&nbsp;</span><jsp:getProperty name="movieInfo" property="region" />
			</p>
			<p>
				<span class="mylabel">上映时间：&nbsp;</span><jsp:getProperty name="movieInfo" property="moviebegin" />
			</p>
			<p>
				<span class="mylabel">简介：&nbsp;</span><jsp:getProperty name="movieInfo" property="moviedescription" />
			</p>
		</div>
	</div>
</div>
<%-- <div class="row div-gap">
	<span class="mylabel">挑选场次</span>
<%
	String date = null;
	int dateIndex = 0;
	for(int i=0;i<plans.size();i++){
		//pageContext.setAttribute("planItem", plans.get(i));
		planItem = plans.get(i);
		if(!planItem.getPlandate().equals(date)){
			date = planItem.getPlandate();
			dateIndex ++;
%>
	<a href="javascript:void(0);" onclick="changeDate(<%=dateIndex %>)"><%=date %></a>
<%
		}
	}
%>
</div>
<div class="row">
<%
	date = null;
	dateIndex = 0;
	String time = null;
	for(int i=0;i<plans.size();i++){
		planItem = plans.get(i);
		time = planItem.getPlantime();
		if(!planItem.getPlandate().equals(date)){
			date = planItem.getPlandate();
			dateIndex ++;
			if(i!=0){
%>
	</div>
	<div class="times hidden" id="date<%=dateIndex %>">
<%
			}else{
%>
	<div class="times" id="date<%=dateIndex %>">
<%
			}
		}
%>
		<a href="javascript:void(0);" onclick="pickTime()"><%=time %></a>
<%
	}
%>
	</div>
</div> --%>

<%-- <div class="row div-gap">
<p class="text-center">屏幕</p>
<table class="table table-bordered seats">
	<%
		for(int i=1;i<=12;i++){
	%>
	<tr>
		<td class="pai"><%=i %>排</td>
	<%
			for(int j=1;j<=14;j++){
	%>
		<td class="seat" id="<%=i %>-<%=j %>"><a href="javascript:void(0);" onclick="pickSeat(<%=i %>,<%=j %>)"><%=j %></a></td>
	<%
			}
	%>
	</tr>
	<%
		}
	%>
</table>
</div> --%>
<%
	if(session.getAttribute("userid")!=null){
		if(((String)session.getAttribute("userlevel")).equals("未激活")){
%>
	<p class="text-center">请激活帐号</p>
<%
		}else{
%>
<div class="row">
	<iframe src="BuyTicket?m=<jsp:getProperty name="movieInfo" property="movieid" />" width="100%" frameborder="0" id="seat_frame" onLoad="iFrameHeight()">
	</iframe>
</div>
<%
		}
	}else{
%>
	<p class="text-center">登录后才能购票！</p>
<%
	}
%>
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
var date = 1;

$(document).ready(function(){
});

function evaluate(){
	alert("fuck");

}

function iFrameHeight() {
	var ifm= document.getElementById("seat_frame");
	var subWeb = document.frames ? document.frames["seat_frame"].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
	ifm.height = subWeb.body.scrollHeight;
	}
	} 

</script>
</body>
</html>