<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员信息统计 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="div-gap bottom-line">
	<h3>会员信息统计</h3>
</div>

<div class="div-gap">
<!--     <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a href="#user-statistics-pane" aria-controls="user-statistics-pane" role="tab" data-toggle="tab">会员信息统计</a></li>
	    <li role="presentation"><a href="#cineplex-statistics-pane" aria-controls="cineplex-statistics-pane" role="tab" data-toggle="tab">影院使用情况</a></li>
	  </ul>
    <div class="tab-content">
	  <div role="tabpanel" class="tab-pane fade in active" id="user-statistics-pane"> -->
		<div id="age-chart">
		
		</div>
		
		<div id="gender-chart">
		
		</div>
		
		<div id="location-chart">
		
		</div>
		
		<div id="consumption-chart">
		
		</div>
		
		<div id="activity-chart">
		
		</div>
		
		<div id="state-chart">
		
		</div>
	  <!-- </div> -->
	  
<%-- 	  <div role="tabpanel" class="tab-pane fade" id="cineplex-statistics-pane">
	  	<div id="daily-chart" style="margin-left:auto;margin-right:auto">
	  	
	  	</div>
	  	
	  	<div id="monthly-chart" class="div-gap" style="margin-left:auto;margin-right:auto">
	  	
	  	</div>
	  	
	  	<div id="hall-rates" class="div-gap" style="margin-left:auto;margin-right:auto">
	  	
	  	</div>
	  	
	  	<div id="movie-rates" class="div-gap">
	  		<table class="table table-striped table-hover">
	  			<thead>
					<tr>
						<th>电影名称</th>
						<th>上映日期</th>
						<th>下线日期</th>
						<th>上座率</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<rates.size();i++){
						pageContext.setAttribute("mr", rates.get(i));
				%>
					<tr>
						<td><jsp:getProperty name="mr" property="moviename" /></td>
						<td><jsp:getProperty name="mr" property="moviebegin" /></td>
						<td><jsp:getProperty name="mr" property="movieover" /></td>
						<td><jsp:getProperty name="mr" property="seatRate" /></td>
					</tr>
				<%
					}
				%>
				</tbody>
	  		</table>
	  	</div>
	  </div>
	</div> --%>
</div>

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/js/highcharts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".memberLab").addClass("active");
	$('#age-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员年龄比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("age") %>
            ]
        }]
    });
	
	$('#gender-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员性别比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("gender") %>
            ]
        }]
    });
	
	$('#location-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员居住地比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("location") %>
            ]
        }]
    });
	
	$('#consumption-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员消费比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("consumption") %>
            ]
        }]
    });
	
	$('#activity-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员参加活动比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("activity") %>
            ]
        }]
    });
	
	$('#state-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员状态比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Percent',
            data: [
                <%=request.getAttribute("level") %>
            ]
        }]
    });
	

});

</script>
</body>
</html>