<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.nju.cineplex.utildata.MovieInfo" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影院使用统计 - 最长的电影</title>
<link href="/CineplexWeb/lib/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/CineplexWeb/css/mystyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="rates" type="java.util.List<MovieInfo>" scope="request" />
<jsp:useBean id="mr" class="edu.nju.cineplex.utildata.MovieInfo" scope="page" />

<jsp:include page="/pages/header.jsp" flush="true" />

<div class="div-gap bottom-line">
	<h3>影院使用统计</h3>
</div>

<div class="div-gap">
		
	<div class="col-md-5">
		<form action="MonthlyStatistics.manager" method="get" class="form-inline">
			<select class="form-control" id="year" name="year">
			<%
				for(int i=(int)request.getAttribute("currentYear");i>=2014;i--){
			%>
				<option value="<%=i %>"><%=i %>年</option>
			<%
				}
			%>
			</select>
			<select class="form-control" id="month" name="month">
				  <option value="1">1月</option>
				  <option value="2">2月</option>
				  <option value="3">3月</option>
				  <option value="4">4月</option>
				  <option value="5">5月</option>
				  <option value="6">6月</option>
				  <option value="7">7月</option>
				  <option value="8">8月</option>
				  <option value="9">9月</option>
				  <option value="10">10月</option>
				  <option value="11">11月</option>
				  <option value="12">12月</option>
			</select>
			<button type="submit">统计</button>
		</form>
	</div>
		
	  	<div id="daily-chart" style="margin-left:auto;margin-right:auto" class="div-gap">
	  	
	  	</div>
	  	
<!-- 	  	<div id="monthly-chart" class="div-gap" style="margin-left:auto;margin-right:auto">
	  	
	  	</div> -->
	  	
	  	<div id="hall-rates" class="div-gap" style="margin-left:auto;margin-right:auto" class="div-gap">
	  	
	  	</div>
	  	
	  	<div id="movie-rates" class="div-gap" class="div-gap">
	  		<h4 class="text-center">各影片上座率</h4>
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

<jsp:include page="/pages/footer.html" flush="true" />

<script type="text/javascript" src="/CineplexWeb/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/lib/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/CineplexWeb/js/highcharts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#year").val('<%=(int)request.getAttribute("showYear") %>');
	$("#month").val('<%=(int)request.getAttribute("showMonth") %>');
	
	///////////////////////////////////////
	
    $('#daily-chart').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '<%=request.getAttribute("showYear") %>年<%=request.getAttribute("showMonth") %>月观影人数'
        },
        xAxis: {
            categories: [
              <%
              	int days = (int)request.getAttribute("showDates");
              	for(int i=0;i<days;i++){
              		out.print("'"+(i+1)+"'");
              		if(i!=days-1)
              			out.print(",");
              	}
              %>           
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: -70,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br>'+
                    this.series.name +': '+ this.y +'<br>'+
                    'Total: '+ this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                }
            }
        },
        series: [{
            name: '卡',
            data: [<%=((String)request.getAttribute("daily")).split("#")[0] %>]
        }, {
            name: '现金',
            data: [<%=((String)request.getAttribute("daily")).split("#")[1] %>]
        }]
    });

<%--     $('#monthly-chart').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '最近1年影院人数'
        },
        xAxis: {
            categories: ['12个月前', '11个月前', '10个月前', '9个月前', '8个月前','7个月前','6个月前','5个月前','4个月前','3个月前','2个月前','上个月']
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: -70,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br>'+
                    this.series.name +': '+ this.y +'<br>'+
                    'Total: '+ this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                }
            }
        },
        series: [{
            name: '卡',
            data: [<%=((String)request.getAttribute("monthly")).split("#")[0] %>]
        }, {
            name: '现金',
            data: [<%=((String)request.getAttribute("monthly")).split("#")[1] %>]
        }]
    }); --%>
    
        $('#hall-rates').highcharts({
            title: {
                text: '<%=request.getAttribute("showYear") %>年<%=request.getAttribute("showMonth") %>月各大厅上座率',
                x: -20 //center
            },
            xAxis: {
                categories: [
                <%
	              	int days2 = (int)request.getAttribute("showDates");
	              	for(int i=0;i<days2;i++){
	              		out.print("'"+(i+1)+"'");
	              		if(i!=days2-1)
	              			out.print(",");
	              	}
	            %>
              	]
            },
            yAxis: {
                title: {
                    text: '上座率'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [<%=(String)request.getAttribute("halls") %>]
        });
        
});
</script>
</body>
</html>