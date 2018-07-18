<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.*,java.util.*,org.json.simple.*"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    
  </head>
  <body>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
  <% 
  		List<JSONArray> list=(List<JSONArray>)request.getAttribute("list");
  		JSONObject data=(JSONObject)request.getAttribute("data");
  	%>
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
         ["월", "검색비율", { role: "style" }],
         <%for(JSONArray ar:list)
         {
        	 %>
        	 <%= ar %>,
        	 <%
         }%>
        
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: '<%=request.getAttribute("keyword")%>'+" 월간 검색량 추이",
        width: 600,
        height: 400,
        bar: {groupWidth: "80%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
  </script>
  
  
    
    
		<div class="row">
				<div id="columnchart_values" style="height: 300px;" class=""></div>
		</div>
		
		<div class="row" style="margin-top:50px">
				<iframe src="../star.html" frameborder="0" width="600" height=500></iframe>
		</div>
		
	
  </body>
</html>