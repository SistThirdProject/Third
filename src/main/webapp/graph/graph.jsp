<%@page import="com.sist.graph.Main"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.graph.*,java.util.*,org.json.simple.*"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
		<% Main main=new Main();
			List<JSONArray> list=main.data();
		%>
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        	<%int i=0; 
        	for(JSONArray ar:list)
        	{
        		%>
        		<%= ar+","%>
        		<%
        	}%>
        	/* <c:forEach var="vo" items="{list}"> */
			/* ,['2004',  1000],
			['2005',  100],
			['2006',  1040],
			['2007',  2000], */
          /* </c:forEach> */
        ]);

        var options = {
          title: '검색어 트랜드변화',
          curveType: 'function',
          legend: { position: 'bottom' }
        };
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
 <div id="chart_div" style="width: 900px; height: 500px"></div>
</body>
</html>