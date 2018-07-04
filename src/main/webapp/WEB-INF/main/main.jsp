<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Corpora</title>
<meta http-equiv="content-type" content="text/html;charset=EUC-KR" />

<link rel="stylesheet" href="../css/reset.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="../css/master.css" type="text/css"
	media="screen" />
<script src="../js/cufon.js" type="text/javascript"></script>
<!-- <script src="../js/Aller_400.font.js" type="text/javascript"></script> -->
<script src="../js/css_browser_select.js" type="text/javascript"></script>
<script type="text/javascript">
	Cufon.replace('h1, h2, h3');
	Cufon.replace('ul#nav li a', {
		hover : true
	});
	Cufon.replace('blockquote p');
	Cufon.replace('#mainSlider h3, .sliderParagraph');
	Cufon.replace('a.darkButton');
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"
	type="text/javascript"></script>
<script src="../js/jCarouselLite.js" type="text/javascript"></script>
<script src="../js/functions.js" type="text/javascript"></script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" width="100%" style="background-color: white;">
		<!-- header 위치 -->
		<tr>
			<td><tiles:insertAttribute name="header" /></td>
		</tr>
		<!-- content 위치 -->
		<tr>
			<td ><tiles:insertAttribute name="body" /></td>
		</tr>
		<!-- footer 들어갈 위치 -->
		<tr>
			<td><tiles:insertAttribute name="footer" /></td>
		</tr>


	</table>




	<script type="text/javascript">
		Cufon.now();
	</script>


</body>
</html>