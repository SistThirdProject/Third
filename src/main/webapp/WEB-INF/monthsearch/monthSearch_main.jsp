<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet prefetch'
	href='http://cdn.jsdelivr.net/instantsearch.js/1/instantsearch.min.css'>
<link rel='stylesheet prefetch'
	href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="../css/cloudstyle.css">
<!-- word cloud css & js -->
<link rel="stylesheet" href="../css/feel_style.css">
<script src="https://d3js.org/d3.v4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/d3-legend/2.13.0/d3-legend.js"></script>
</head>
<body>

	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	$(function(){
		searchFeel();
	});
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable(${graphdata});
        var options = {
          title: '1주간 검색량 변화',
          curveType: 'function',
          legend: { position: 'bottom' }
        };
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      };
      
      /* function searchFeel(input)
      {
      	var keyword='최은주';
      	
      	alert(keyword);
      	
      	 $.ajax({
      		type : 'post',
      		data : {keyword:keyword},
      		url : '../main/feel.do',
      		success : function(data){
      			alert(data);
      			 $('#feeling').html(data); 
      		}
      	}); 
      	
      }; */
      


</script>
	<p></p>
	<p></p>
	<p></p>
	<div class="container" id="reactroot">
		<div class="panel panel-info col-lg-3">
			<div class="panel-heading">날짜</div>
			<c:forEach var="vo" items="${datelist}">
				<div class="panel-body" style="padding: 0px;">
					<a href="month_search.do?time=${vo.time}">${vo.str}</a>
				</div>
			</c:forEach>
		</div>
		<div class="panel panel-info col-lg-3">
			<div class="panel-heading">시간</div>
			<c:forEach var="vo" items="${hourlist}">
				<div class="panel-body" style="padding: 0px;">
					<a href="month_search.do?time=${vo.time}">${vo.str}</a>
				</div>
			</c:forEach>
		</div>
		<div class="panel panel-info col-lg-3">
			<div class="panel-heading">분</div>
			<c:forEach var="vo" items="${minutelist}">
				<div class="panel-body" style="padding: 0px;">
					<a href="month_search.do?time=${vo.time}">${vo.str}</a>
				</div>
			</c:forEach>
		</div>
		<div class="panel panel-info col-lg-3">
			<div class="panel-heading">키워드</div>
			<c:forEach var="vo" items="${keywordlist}">
				<div class="panel-body"
					style="padding: 0px; min-height: 10; max-height: 10;"
					value="${vo.time}">
					<a class="emotion" value="${vo.keyword}"
						href="month_search.do?time=${vo.time}&rank=${vo.rank}">
						${vo.keyword} </a>
				</div>
			</c:forEach>
		</div>

	</div>

	

	<div class="container">
		<h2>${title}</h2>
		<div class="row">
			<div class="col-lg-6">
				<p>&nbsp</p>
				<p>&nbsp</p>
				<p>&nbsp</p>
				<div id="chart_div" style="width: 600px; height: 300px"></div>
			</div>
			<div class="col-lg-6">
				<div id="tagsList">
					<c:forEach var="str" items="${cloud}">
						<span><a>${str}</a></span>
					</c:forEach>
					<script src="../js/monthcloud.js"></script>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top:30px">
		<h3>감정 분석 결과</h3>
		<div id="chart"></div>

		<script type="text/javascript">
			var data = {
				"name" : 'planets',
				"children" :
		<%=request.getAttribute("jarry")%>
			};
	
			var colorMap = {
				'긍정' : '#73a4f4',
				'중립' : '#bef473',
				'부정' : '#f48e73'
			};
	
			var ordinal = d3.scaleOrdinal().domain([ '긍정', '중립', '부정' ]).range(
					[ '#73a4f4', '#bef473', '#f48e73' ]);
	
			var legendOrdinal = d3.legendColor().shape('rect').shapePadding(10)
					.labelOffset(20).scale(ordinal);
	
			var diameter = 600, format = d3.format(",d");
	
			var bubble = d3.pack().size([ diameter, diameter ]).padding(1.5);
	
			var svg = d3.select("#chart").append("svg").attr("width", diameter)
					.attr("height", diameter).attr('viewBox', '0 0 800 800').attr(
							'preserveAspectRatio', 'xMidYMid meet').attr("class",
							"bubble");
	
			svg.append('g').attr('class', 'legendOrdinal').attr('transform',
					'translate(0,0)');
	
			svg.select('.legendOrdinal').call(legendOrdinal);
	
			svg.selectAll('.label').style('fill', 'grey');
	
			var root = d3.hierarchy(classes(data)).sum(function(d) {
				return d.value;
			}).sort(function(a, b) {
				return b.value - a.value;
			});
	
			bubble(root);
			var node = svg.selectAll(".node").data(root.children).enter().append(
					"g").attr("class", "node").attr("transform", function(d) {
				return "translate(" + d.x + "," + d.y + ")";
			});
			node.append("title").text(function(d) {
				return d.data.className + ": " + format(d.value);
			});
	
			node.append("circle").attr("r", function(d) {
				return d.r;
			}).style("fill", function(d) {
				return colorMap[d.data.packageName.maj_atmos_comp];
			});
	
			node.append("text").attr("dy", ".3em").style("text-anchor", "middle")
					.style('font-family', 'sans-serif').style('font-size', '3rem')
					.text(function(d) {
						return d.data.className.substring(0, d.r / 3);
					});
			console.log(classes(data));
	
			function classes(root) {
				var classes = [];
				function recurse(title, node) {
					// console.log(node.children);
					if (node.children)
						node.children.forEach(function(child) {
							recurse(node.name, child);
						});
					else
						classes.push({
							packageName : node,
							className : node.title,
							value : node.mer
						});
				}
				recurse(null, root);
				return {
					children : classes
				};
			}
	
			d3.select(self.frameElement).style("height", diameter + "px");
		</script>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="row">
					<table class="table table-hover">
						<tr class="success">
							<th class="text-center">관련 뉴스 리스트</th>
						</tr>
						<c:forEach var="vo" items="${newslist}" varStatus="state">
							<c:if test="${state.index %2 eq 0 }">
								<tr>
									<td><a href="${vo.link }">${vo.title }</a></td>
								</tr>
							</c:if>
						</c:forEach>

					</table>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="row">
					<table class="table table-hover">
						<tr class="success">
							<th class="text-center">&nbsp</th>
						</tr>
						<c:forEach var="vo" items="${newslist}" varStatus="state">
							<c:if test="${state.index %2 ne 0 }">
								<tr>
									<td><a href="${vo.link }">${vo.title }</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>