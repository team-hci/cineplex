<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加电影 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/pages/header.jsp" flush="true" />

<div id="add_movie">
	<h2 class="bottom-line">添加影片</h2>
	<br />
	<form action="SubmitMovie" method="post" class="form-horizontal" enctype="multipart/form-data" onsubmit="return checkMovie();"> <!-- enctype="multipart/form-data" -->
		<div class="form-group">
			<label for="movie_name" class="col-sm-2 control-label">电影名称</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="movie_name" id="movie_name" />
			</div>
		</div>
		<div class="form-group">
			<label for="movie_poster" class="col-sm-2 control-label">电影图片</label>
			<div class="col-sm-9">
				<input type="file" name="movie_poster" id="movie_poster" />
			</div>
		</div>
		<div class="form-group">
			<label for="movie_description" class="col-sm-2 control-label">电影描述</label>
			<div class="col-sm-9">
				<textarea class="form-control" name="movie_description" id="movie_description" rows="5"></textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="movie_begin" class="col-sm-2 control-label">上映日期</label>
			<div class="col-sm-9">
				<input type="date" class="form-control" name="movie_begin" id="movie_begin" />
			</div>
		</div>
		<div class="form-group">
			<label for="movie_over" class="col-sm-2 control-label">下线日期</label>
			<div class="col-sm-9">
				<input type="date" class="form-control" name="movie_over" id="movie_over" />
			</div>
		</div>
		<div class="form-group">
			<label for="movie_length" class="col-sm-2 control-label">电影时长</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="movie_length" id="movie_length" />
			</div>
		</div>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label">地区</label>
			<div class="col-sm-9">
			<label class="radio-inline">
			  <input type="radio" name="region_radio" id="region_radio1" value="1">内地
			</label>
			<label class="radio-inline">
			  <input type="radio" name="region_radio" id="region_radio2" value="2">港台
			</label>
			<label class="radio-inline">
			  <input type="radio" name="region_radio" id="region_radio3" value="3">日韩
			</label>
			<label class="radio-inline">
			  <input type="radio" name="region_radio" id="region_radio4" value="4">欧美
			</label>
			</div>
		</div>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-9">
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox1" value="1">动作
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox2" value="2">喜剧
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox3" value="3">犯罪
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox4" value="4">爱情
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox5" value="5">科幻
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox6" value="6">恐怖
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox7" value="7">剧情
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox8" value="8">冒险
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox9" value="9">奇幻
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox10" value="10">悬疑
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox11" value="11">武侠
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox12" value="12">战争
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox13" value="13">惊悚
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox14" value="14">动画
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox15" value="15">传记
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox16" value="16">经典
			</label>
			<label class="checkbox-inline">
			  <input type="checkbox" name="type_checkbox" id="type_checkbox17" value="17">伦理
			</label>
			</div>
		</div>
		<div class="form-group">
			<label for="director" class="col-sm-2 control-label">导演</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="director" id="director" placeholder="多个以‘空格’分隔" />
			</div>
		</div>
		<div class="form-group">
			<label for="actor" class="col-sm-2 control-label">主演</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="actor" id="actor" placeholder="多个以‘空格’分隔" />
			</div>
		</div>
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
	$(".addMovieLab").addClass("active");
});

function checkMovie(){
	var movieName = $("#movie_name").val();
	var moviePoster = $("#movie_poster").val();
	var movieDescription = $("#movie_description").val();
	var movieBegin = $("#movie_begin").val();
	var movieOver = $("#movie_over").val();
	var movieLength = $("#movie_length").val();
	var region = $("#region_radio").val();
	var type = $("#type_checkbox").val();
	var director = $("#movie_director").val();
	var actor = $("#movie_actor").val();
	if(movieName=='' || moviePoster=='' || movieDescription=='' || movieBegin=='' || movieOver=='' || movieLength=='' || region=='' || type=='' || director=='' || actor==''){
		$("#hint").html("请填写完整信息！");
		return false
	}
	else{
		return true;
	}
}
</script>
</body>
</html>