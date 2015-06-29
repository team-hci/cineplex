<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮助 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/pages/header.jsp" flush="true" />
    
<div>
	<h2 class="bottom-line">帮助</h2>
	
	<p>
		此系统实现用户在线订购电影票的服务。
		<br /><br />
		1. 新用户需凭借银行卡进行注册，每张银行卡只能注册一次。<br />
		2. 用户账号未激活时，一次需充值¥200以上进行激活，充值¥200-¥1000激活普通会员，充值¥1000以上激活高级会员。<br />
		3. 会员可进入正在上映的电影界面进行订票，普通会员9折优惠，高级会员8折优惠。<br />
		4. 会员可根据已订购的电影场次参加相应活动。<br />
		5. 会员订购电影片或参加活动可获得积分，使用积分可在购票时优惠。<br />
		6. 会员一年内未购票则暂停其会员服务，再次购票可恢复；两年内未购票则停止会员服务，需要充值激活。<br />
	</p>
</div>
    
<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

</body>
</html>