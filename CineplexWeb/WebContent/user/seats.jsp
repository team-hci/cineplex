<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Plan" %>
<%@ page import="edu.nju.cineplex.model.Seat" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
  html {padding-left:30px}
</style>
</head>
<body>
<jsp:useBean id="plans" type="java.util.List<Plan>" scope="request" />
<jsp:useBean id="planItem" class="edu.nju.cineplex.model.Plan" scope="page" />
<jsp:useBean id="seats" type="java.util.List<Seat>" scope="request" />
<jsp:useBean id="seatItem" class="edu.nju.cineplex.model.Seat" scope="page" />

<%
	if(((String)session.getAttribute("userlevel")).equals("未激活")){
%>
	<p class="text-center">请激活帐号</p>
<%
	}else if(plans.size()>0){
%>
<div class="row">
	<p class="mylabel text-center bottom-line">挑选场次</p>
	<span class="mylabel">日期：</span>
	<ul class="nav nav-pills">
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
		<li role="presentation" id="date<%=dateIndex %>"><a href="javascript:void(0);" onclick="changeDate(<%=dateIndex %>)"><%=date %></a></li>
<%
		}
	}
%>
	</ul>
</div>
<div class="row">
	<span class="mylabel">时间：</span>
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
		</ul>
	</div>
<%
			}
%>
	<div class="times hidden" id="datediv<%=dateIndex %>">
		<ul class="nav nav-pills">
<%
		}
%>
		<li role="presentation" id="time<%=planItem.getPlanid() %>"><a href="javascript:void(0);" onclick="pickTime(<%=planItem.getPlanid() %>)"><%=time %></a></li>
<%
	}
%>
		</ul>
	</div>
</div>

<form action="BuyTicket" method="get" id="planForm">
	<input type="hidden" name="p" id="planid">
	<input type="hidden" name="m" id="movieid">
	<input type="hidden" name="d" id="dateid">
</form>

<div class="row">
	<p id="price">
		<span class="mylabel">票价：</span>
	</p>
</div>

<div class="row div-gap">
<p class="mylabel bottom-line text-center">挑选位置</p>
<br />
<table class="table table-bordered table-hover seats">
	<tr>
		<td class="pai">1排</td>
	<%
		int row = 1;
		int seatRow = 0;
		int seatCol = 0;
		int seatId = 0;
		boolean active = false;
		for(int i=0;i<seats.size();i++){
			seatItem = seats.get(i);
			seatRow = seatItem.getSeatrow();
			seatCol = seatItem.getSeatcolumn();
			seatId = seatItem.getSeatid();
			active = seatItem.isSeatactive();
			while(seatRow>row){
				row ++;
	%>
	</tr>
	<tr>
		<td class="pai"><%=row %>排</td>
	<%
			}
			if(active){
	%>
		<td class="seat" id="<%=seatId %>"><a href="javascript:void(0);" onclick="pickSeat(<%=seatId %>)"><%=seatCol %>座</a></td>
	<%
			}else{
	%>
		<td class="seat danger" id="<%=seatId %>"></td>
	<%		
			}
		}
	%>
	</tr>
	
</table>
</div>

	<form action="" method="post" class="form-inline" enctype="multipart/form-data" onsubmit="return checkMovie();"> <!-- enctype="multipart/form-data" -->
	  <%
	  	if(((String)session.getAttribute("userlevel")).equals("服务员") || ((String)session.getAttribute("userlevel")).equals("经理")){
	  %>
	  <div class="form-group col-sm-1">
	  		<label for="" class="control-label">支付方式</label>
	  </div>
	  <div class="form-group col-sm-3">
		    <label class="radio-inline">
			  <input type="radio" name="payType" id="card" value="卡" checked>卡
			</label>
			<input type="text" name="card" id="card" />
			&nbsp;&nbsp;
			<label class="radio-inline">
			  <input type="radio" name="payType" id="cash" value="现金">现金
			</label>
	  </div>
	  <%
		}
	  %>
	  	<div class="form-group col-sm-2">
	  		<span class="mylabel">总价：</span>
		  <span class="price">￥<span id="total-price">0</span></span>
		</div>
		<div class="form-group col-sm-4">
		      <button type="button" class="btn btn-primary" onclick="buy()">购买</button>
		      <span class="hint" id="hint"></span>
		    </div>
	</form>
<%
	}
%>

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
var date = <%=request.getAttribute("dateid") %>;
var plan = <%=request.getAttribute("planid") %>;
var pickedSeats = new Array();
var finalPrice;

$(document).ready(function(){
	$("#movieid").val(<%=request.getAttribute("movieid") %>)
	$("#dateid").val(date);
	$("#date"+date).addClass("active");
	$("#time"+plan).addClass("active");
	$("#datediv"+date).removeClass("hidden");
	//price
	<%
		int price = plans.get(((int)request.getAttribute("dateid"))-1).getPlanprice();
		String level = (String)session.getAttribute("userlevel");
		double finalPrice = 0;
		if(level.equals("普通会员")){
			finalPrice = price*0.9;
	%>
	$("#price").append("<span class='cancel-price'>￥"+<%=price %>+"</span>");
	$("#price").append("<span class='price'>￥"+<%=finalPrice %>+"</span>");
	<%
		}else if(level.equals("高级会员")){
			finalPrice = price*0.8;
	%>
	$("#price").append("<span class='cancel-price'>￥"+<%=price %>+"</span>");
	$("#price").append("<span class='price'>￥"+<%=finalPrice %>+"</span>");
	<%
		}else{
			finalPrice = price;
	%>
	$("#price").append("<span class='price'>￥"+<%=finalPrice %>+"</span>");
	<%
		}
	%>
	finalPrice = <%=finalPrice %>;
});

function changeDate(d){
	$("#datediv"+date).addClass("hidden");
	$("#datediv"+d).removeClass("hidden");
	$("#date"+date).removeClass("active");
	$("#date"+d).addClass("active");
	date = d;
	$("#dateid").val(date);
}

function pickTime(planid){
	$("#planid").val(planid);
	$("#planForm").submit();
}

function pickSeat(s){
	//$("#"+x+"-"+y).addClass("success");
	var seat = $("#"+s);
	if(!seat.hasClass("danger")){
		if(seat.hasClass("success")){//delete
			var index = 0;
			for(;index<pickedSeats.length;index++){
				if(pickedSeats[index]==s)
					break;
			}
			pickedSeats.splice(index,1);
		}else{//add
			pickedSeats.push(s);
		}
		seat.toggleClass("success");
	}
	$("#total-price").html(pickedSeats.length*finalPrice);
}

function buy(){
	if(pickedSeats.length==0)
		return;
	var payType = "";
	var types = document.getElementsByName("payType");
	var card = $("#card").val();
	for(var i=0;i<types.length;i++){
		if(types[i].checked){
			payType=types[i].value;
		}
	}
	if(confirm('共￥'+(finalPrice*pickedSeats.length)+',确认购票？')){
	$.ajax({
		type : 'post',
		url: "BuyTicket",
		dataType:'text',
		data : {
	      "pickedSeats":pickedSeats.toString(),
	      "planid":plan,
	      "finalPrice":finalPrice,
	      "payType":payType,
	      "card":card
		},
		success: function(data){
	      if(data=="y"){
	    	  alert("购票成功！");
	      }else if(data=="n"){
	    	  alert("购票失败！");
	      }
	      location.reload();
		}
	});
	}else{
		return false;
	}
}

</script>
</body>
</html>