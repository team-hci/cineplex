<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.utildata.PlanItem" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制定活动 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="plans" type="java.util.List<PlanItem>" scope="request" />
<jsp:useBean id="item" class="edu.nju.cineplex.utildata.PlanItem" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div id="make_activity">
	<h2 class="bottom-line">制定活动</h2>
	<br />
	<form action="SubmitActivity" method="post" class="form-horizontal" onsubmit="return checkActivity();">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">活动标题</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="title" id="title" placeholder="" />
			</div>
		</div>
		<div class="form-group">
			<label for="credit" class="col-sm-2 control-label">积分</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="credit" id="credit" placeholder="" />
			</div>
		</div>
		<div class="form-group">
			<label for="option3" class="col-sm-2 control-label">匹配影片</label>
			<div class="col-sm-9">
				<table class="table table-striped table-hover">
					<tr>
						<th>电影名称</th>
						<th>放映时间</th>
						<th>大厅</th>
						<th>价格</th>
						<th>匹配</th>
					</tr>
					<%
						for(int i=0;i<plans.size();i++){
							pageContext.setAttribute("item", plans.get(i));
					%>
					<tr>
						<td><jsp:getProperty name="item" property="moviename" /></td>
						<td><jsp:getProperty name="item" property="plandate" />&nbsp;<jsp:getProperty name="item" property="plantime" /></td>
						<td><jsp:getProperty name="item" property="hall" /></td>
						<td><jsp:getProperty name="item" property="planprice" /></td>
						<td><input type="checkbox" name="match_checkbox" value="<jsp:getProperty name="item" property="planid" />"></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
		
		
		<div id="questions">
		<div class="question-item">
			<div class="form-group">
				<label for="question" class="col-sm-2 control-label">问题一</label>
				<div class="col-sm-9">
					<textarea class="form-control" name="question1" id="question1" rows="5"></textarea>
				</div>
			</div>
			<div id="options">
				<div class="form-group">
					<label for="option1-1" class="col-sm-2 control-label">选项一</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="option1-1" id="option1-1" placeholder="" />
					</div>
				</div>
				<div class="form-group">
					<label for="option1-2" class="col-sm-2 control-label">选项二</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="option1-2" id="option1-2" placeholder="" />
					</div>
				</div>
				<div class="form-group">
					<label for="option1-3" class="col-sm-2 control-label">选项三</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="option1-3" id="option1-3" placeholder="" />
					</div>
				</div>
				</div>
			</div>
		</div><!-- end of questions -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="btn btn-link" onclick="appendQuestion()">添加问题</button>
				<button type="button" class="btn btn-link" onclick="removeQuestion()">移除问题</button>
			</div>
		</div>
		<input type="hidden" name="question_count" id="question_count" value="1" />
		<div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">提交</button>
		      <button type="reset" class="btn btn-default">重置</button>
		      <span class="hint" id="hint"></span>
		    </div>
		</div>
	</form>
</div>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
var questionCount = 1;
var numbers = ["一","二","三","四","五","六","七","八","九","十"];

$(document).ready(function(){

});

function appendQuestion(){
	if(questionCount<10){
		questionCount ++;
		$("#questions").append("<div class=\"question-item\"><div class=\"form-group\"><label for=\"question\" class=\"col-sm-2 control-label\">问题"+numbers[questionCount-1]+"</label><div class=\"col-sm-9\"><textarea class=\"form-control\" name=\"question"+questionCount+"\" id=\"question"+questionCount+"\" rows=\"5\"></textarea></div></div><div id=\"options\"><div class=\"form-group\"><label for=\"option"+questionCount+"-1\" class=\"col-sm-2 control-label\">选项一</label><div class=\"col-sm-9\"><input type=\"text\" class=\"form-control\" name=\"option"+questionCount+"-1\" id=\"option"+questionCount+"-1\" /></div></div><div class=\"form-group\"><label for=\"option"+questionCount+"-2\" class=\"col-sm-2 control-label\">选项二</label><div class=\"col-sm-9\"><input type=\"text\" class=\"form-control\" name=\"option"+questionCount+"-2\" id=\"option"+questionCount+"-2\" /></div></div><div class=\"form-group\"><label for=\"option"+questionCount+"-3\" class=\"col-sm-2 control-label\">选项三</label><div class=\"col-sm-9\"><input type=\"text\" class=\"form-control\" name=\"option"+questionCount+"-3\" id=\"option"+questionCount+"-3\" /></div></div></div></div>");
		$("#question_count").val(questionCount);
	}
}

function removeQuestion(){
	if(questionCount>1){
		$("#questions").children("div:last-child").remove();
		questionCount --;
		$("#question_count").val(questionCount);
	}
}

function checkActivity(){
	return true;
}
</script>
</body>
</html>