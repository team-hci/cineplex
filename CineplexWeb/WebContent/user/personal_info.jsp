<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.model.Payment" %>
<%@ page import="edu.nju.cineplex.utildata.Consumption" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="userInfo" type="edu.nju.cineplex.model.UserInfo" scope="request" />
<jsp:useBean id="consumptions" type="java.util.List<Consumption>" scope="request" />
<jsp:useBean id="conItem" class="edu.nju.cineplex.utildata.Consumption" scope="page" />
<jsp:useBean id="payments" type="java.util.List<Payment>" scope="request" />
<jsp:useBean id="payItem" class="edu.nju.cineplex.model.Payment" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<ul id="infoTab" class="nav nav-tabs div-gap">
   <li class="active">
      <a href="#info" data-toggle="tab">
         	个人资料
      </a>
   </li>
   <li>
   		<a href="#consumption" data-toggle="tab">
   			消费记录
   		</a>
   </li>
   <li>
      <a href="#pay" data-toggle="tab">
      	交费记录
      </a>
   </li>
</ul>

<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active div-gap" id="info">
		<div class="row">
			<div class="col-md-offset-3 col-md-9" id="info-show">
				<div style="width:40%;float:left">
					<p>
					<span class="mylabel">会员号：</span>
					<jsp:getProperty name="userInfo" property="userid" />
					</p>
					<p>
						<span class="mylabel">会员名称：</span>
						<jsp:getProperty name="userInfo" property="username" />
					</p>
					<p>
						<span class="mylabel">年龄：</span>
						<jsp:getProperty name="userInfo" property="age" />
					</p>
					<p>
						<span class="mylabel">性别：</span>
						<jsp:getProperty name="userInfo" property="gender" />
					</p>
					<p>
						<span class="mylabel">地址：</span>
						<jsp:getProperty name="userInfo" property="location" />
					</p>
				</div>
			
			<div style="width:40%;float:left">
				<p>
					<span class="mylabel">银行卡号：</span>
					<jsp:getProperty name="userInfo" property="cardid" />
				</p>
				<p>
					<span class="mylabel">用户状态：</span>
					<jsp:getProperty name="userInfo" property="level" />
				</p>
				<p>
					<span class="mylabel">积分：</span>
					<jsp:getProperty name="userInfo" property="credit" />
					<button class="btn btn-link" type="button" onclick="changeMoney()">兑换金额</button>
				</p>
				<p>
					<span class="mylabel">余额：</span>
					<jsp:getProperty name="userInfo" property="balance" />
				</p>
			</div>
			
			<div style="width:100%;float:left;margin:2% 0 0 15%">
				<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#pay-modal">充值</button>
				<button class="btn btn-default" type="button" onclick="modify()">修改资料</button>
				<button class="btn btn-default" type="button" onclick="cancelMember()">取消会员</button>
			</div>
			
			</div>
			
			<div class="col-md-offset-2 col-md-6 hidden" id="info-modify">
				<form class="form-horizontal" method="post" action="ModifyUserInfo">
				  <div class="form-group">
				    <label for="username" class="col-sm-2 control-label">会员名称</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="username" value="<jsp:getProperty name="userInfo" property="username" />">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="age" class="col-sm-2 control-label">年龄</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="age" value="<jsp:getProperty name="userInfo" property="age" />">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="gender" class="col-sm-2 control-label">性别</label>
				    <div class="col-sm-10">
				      <select class="form-control" name="gender">
						  <option value="男">男</option>
						  <option value="女">女</option>
						</select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="location" class="col-sm-2 control-label">地址</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="location" value="<jsp:getProperty name="userInfo" property="location" />">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-primary">提交</button>
				      <button type="button" class="btn btn-default" onclick="cancelModify()">取消</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="tab-pane fade in div-gap" id="consumption">
		<div class="row">
		<% if(consumptions.size() > 0){ %>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>影片</th>
						<th>放映时间</th>
						<th>价格</th>
						<th>购买时间</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<consumptions.size();i++){
						pageContext.setAttribute("conItem", consumptions.get(i));
				%>
					<tr>
						<td><%=i+1 %></td>					
						<td><jsp:getProperty name="conItem" property="moviename" /></td>				
						<td><jsp:getProperty name="conItem" property="plandate" />&nbsp;<jsp:getProperty name="conItem" property="plantime" /></td>	
						<td><jsp:getProperty name="conItem" property="singlePrice" /></td>					
						<td><jsp:getProperty name="conItem" property="payDatetime" /></td>					
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
		<%} else { %>
			<p class="text-center">暂无消费纪录</p>
		<% } %>
		</div>
	</div>
	
	<div class="tab-pane fade in div-gap" id="pay">
		<div class="row">
		<% if(payments.size() > 0){ %>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>时间</th>
						<th>充值金额</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<payments.size();i++){
						pageContext.setAttribute("payItem", payments.get(i));
				%>
					<tr>
						<td><%=i+1 %></td>
						<td><jsp:getProperty name="payItem" property="datetime" /></td>					
						<td><jsp:getProperty name="payItem" property="sum" /></td>					
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
		<% } else { %>
			<p class="text-center">暂无充值纪录</p>
		<% } %>
		</div>
	</div>
</div>

<div class="modal fade" id="pay-modal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
      	<form action="" class="form-horizontal">
      		<div class="form-group">
      			<label for="sum" class="col-sm-2 control-label">充值金额</label>
      			<div class="col-sm-10">
      				<input type="text" id="sum" name="sum" class="form-control" />
      			</div>
      		</div>
      		<div class="form-group">
      			<div class="col-sm-offset-2 col-sm-10">
      				<span class="mylabel">单次充值200及以上为普通会员，1000及以上为超级会员</span>
      			</div>
      		</div>
      		<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="button" class="btn btn-primary" onclick="pay()">确定</button>
      			</div>
      		</div>
      	</form>
      </div>
    </div>
  </div>
</div>
      
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});

function pay(){
	var sum = $("#sum").val();
	if(sum=='')
		return;
	$.ajax({
		type : 'post',
		url: "Pay",
		dataType:'text',
		data : {
	      "sum":sum
		},
		success: function(data){
	      if(data=="y"){
	    	  alert("充值成功！");
	      	  location.reload();
	      }else if(data=="n"){
	    	  alert("充值失败！");
	      }
		}
	});
}

function cancelMember(){
	if(confirm('确定取消会员资格吗？')){
		$.ajax({
			type : 'post',
			url: "CancelMember",
			dataType:'text',
			data : {
			},
			success: function(data){
		      if(data=="y"){
		    	  alert("取消成功！");
		      	  location.reload();
		      }else if(data=="n"){
		    	  alert("取消失败！");
		      }
			}
		});
        return true;
     }else{
        //alert("取消");
        return false;
    }
}

function modify(){
	$("#info-show").addClass("hidden");
	$("#info-modify").removeClass("hidden");
}

function cancelModify(){
	$("#info-modify").addClass("hidden");
	$("#info-show").removeClass("hidden");
}

function changeMoney(){
	var credit = <jsp:getProperty name="userInfo" property="credit" />;
	if(credit<100){
		alert("积分太低！");
		return;
	}
	var money = <jsp:getProperty name="userInfo" property="credit" />/100.0;
	if(confirm('确定兑换金额'+money+'吗？')){
		$.ajax({
			type : 'post',
			url: "ChangeMoney",
			dataType:'text',
			data : {
			},
			success: function(data){
		      if(data=="y"){
		    	  alert("兑换成功！");
		      	  location.reload();
		      }else if(data=="n"){
		    	  alert("兑换失败！");
		      }
			}
		});
        return true;
     }else{
        //alert("取消");
        return false;
    }
}

</script>
</body>
</html>