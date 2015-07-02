<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.ActivityQuestion" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.nju.cineplex.utildata.PlanItem" %>
<html>
<head>
<jsp:useBean id="activityInfo" type="edu.nju.cineplex.utildata.ActivityInfo" scope="request" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=activityInfo.getTitle() %> - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="question" class="edu.nju.cineplex.model.ActivityQuestion" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<h3 class="text-center">
	<jsp:getProperty name="activityInfo" property="title" />
</h3>
<p class="text-center">
	<span class="mylabel"><jsp:getProperty name="activityInfo" property="activitytime" /></span>
</p>
<p class="text-center">
	<span class="mylabel"><jsp:getProperty name="activityInfo" property="credit" />分，<%=activityInfo.getAcount() %>人参加</span>
</p>

<span class="mylabel">相关放映</span>
<% 
	PlanItem[] plans = activityInfo.getPlans();
	if(plans.length > 0){
%>
<table class="table table-striped table-hover">
	<tr>
		<th>电影名称</th>
		<th>放映时间</th>
		<th>大厅</th>
		<th>价格</th>
	</tr>
	<%
		for(int i=0;i<plans.length;i++){
	%>
	<tr>
		<td><a href="/CineplexWeb/MovieDetail?m=<%=plans[i].getMovieid() %>"><%=plans[i].getMoviename() %></a></td>
		<td><%=plans[i].getPlandate()+" "+plans[i].getPlantime() %></td>
		<td><%=plans[i].getHall() %></td>
		<td><%=plans[i].getPlanprice() %></td>
	</tr>
	<%
		}
	%>
</table>
<% } else { %>
	<p class="text-center">无相关放映</p>
<% } %>
<br />
<form action="" method="post" class="text-center">

<%
	int tb = (int)request.getAttribute("tb");
	if(session.getAttribute("userid")==null){
%>
	<p class="text-center">登陆后参加活动</p>
<%
	}else if(tb==1){
%>
<%
	ActivityQuestion[] questions = activityInfo.getQuestions();
	int index = 0;
	for(int i=0;i<questions.length;i++){
		question = questions[i];
		int questionId = question.getQuestionid();
		index ++;
%>
<div class="question-item">
	<p class="text-center">
		<%=question.getContent() %>
	</p>
	<div class="radio">
	  <label>
	    <input type="radio" class="radioBox" name="options<%=index %>" id="option<%=index %>-1" value="<%=questionId %>-1">
	    <%=question.getOption1() %>
	  </label>
	</div>
	<div class="radio">
	  <label>
	    <input type="radio" class="radioBox" name="options<%=index %>" id="option<%=index %>-2" value="<%=questionId %>-2">
	    <%=question.getOption2() %>
	  </label>
	</div>
	<div class="radio">
	  <label>
	    <input type="radio" class="radioBox" name="options<%=index %>" id="option<%=index %>-3" value="<%=questionId %>-3">
	    <%=question.getOption3() %>
	  </label>
	</div>
</div>
<%
	}
%>
	<br />
	<button type="button" class="btn btn-primary" onclick="submitChoice()">提交</button>
<%
	}else if(tb==0){
%>
	<p class="text-center">未观看相关电影</p>
<%
	}else if(tb==2){
%>
	<p class="text-center">已参加此活动</p>
<%		
	}
%>
</form>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	var tb = <%=(int)request.getAttribute("tb") %>;
	if(tb ==2){
		<%
		List<Integer> taken = (List<Integer>)request.getAttribute("taken");
		int ind = 0;
		for(int t:taken){
			ind++;
		%>
		$("#option<%=ind %>-<%=t %>").attr("checked","checked");
		<%
		}
		%>
	}
	if(tb!=1){
		$(".radioBox").attr("disabled",true);
	}
});

function submitChoice(){
	var length = <%=activityInfo.getQuestions().length %>;
	var answers = "";
	for(var i=0;i<length;i++){
		var choice = $('input[name="options'+(i+1)+'"]:checked').val();
		if(choice==''){
			return false;
		}
		answers += choice;
		if(i!=length-1)
			answers += ",";
	}
	$.ajax({
		type : 'post',
 		url: "ActivityDetail",
		dataType:'text',
		data : {
	      "options":answers,
	      "activityid":<%=activityInfo.getActivityid() %>
		},
		success: function(data){
		  if(data=='y')
			  alert("感谢您的参与！");
	      location.reload();
		}
	});
}



</script>
</body>
</html>