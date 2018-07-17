<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="../css/feel_style.css">
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-legend/2.13.0/d3-legend.js"></script>
</head>

<body>



<div id="chart"></div>

<script type="text/javascript">
var data = {
	    "name": 'planets',
	    "children": <%=request.getAttribute("jarry")%>

	};

	var colorMap = {
	    '긍정': 'blue',
	    '중립': 'green',
	    '부정': 'red'
	};

	var ordinal = d3.scaleOrdinal().domain(['긍정', '중립', '부정']).range(['blue', 'green', 'red']);

	var legendOrdinal = d3.legendColor().shape('rect').shapePadding(10).labelOffset(20).scale(ordinal);

	var diameter = 400,
	    format = d3.format(",d");

	var bubble = d3.pack().size([diameter, diameter]).padding(1.5);

	var svg = d3.select("#chart").append("svg").attr("width", diameter).attr("height", diameter).attr('viewBox', '0 0 800 800').attr('preserveAspectRatio', 'xMidYMid meet').attr("class", "bubble");

	svg.append('g').attr('class', 'legendOrdinal').attr('transform', 'translate(0,0)');

	svg.select('.legendOrdinal').call(legendOrdinal);

	svg.selectAll('.label').style('fill', 'grey');

	var root = d3.hierarchy(classes(data)).sum(function (d) {
	    return d.value;
	}).sort(function (a, b) {
	    return b.value - a.value;
	});

	bubble(root);
	var node = svg.selectAll(".node").data(root.children).enter().append("g").attr("class", "node").attr("transform", function (d) {
	    return "translate(" + d.x + "," + d.y + ")";
	});
	node.append("title").text(function (d) {
	    return d.data.className + ": " + format(d.value);
	});

	node.append("circle").attr("r", function (d) {
	    return d.r;
	}).style("fill", function (d) {
	    return colorMap[d.data.packageName.maj_atmos_comp];
	});

	node.append("text").attr("dy", ".3em").style("text-anchor", "middle").style('font-family', 'sans-serif').style('font-size', '.8rem').text(function (d) {
	    return d.data.className.substring(0, d.r / 3);
	});
	console.log(classes(data));

	function classes(root) {
	    var classes = [];
	    function recurse(title, node) {
	        // console.log(node.children);
	        if (node.children) node.children.forEach(function (child) {
	            recurse(node.name, child);
	        });else classes.push({ packageName: node, className: node.title, value: node.mer });
	    }
	    recurse(null, root);
	    return { children: classes };
	}

	d3.select(self.frameElement).style("height", diameter + "px");
</script>

</body>

</html>
