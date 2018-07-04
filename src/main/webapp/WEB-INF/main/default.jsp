<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<div class="centerContainer">
	  <div id="leftContainer" class="fl">
	    <h3 class="sectionTitle">latest <span>news</span></h3>
	    
	  </div>
	  
	  <div id="rightContainer" class="fr">
	    <h3 class="sectionTitle">연관 검색어</h3>
			<div id="tagsList" style="margin: 0px auto; background-color: white; ">
				<c:forEach var="vo" items="${list }">
					<span><a href="http://www.hao123.com/haoserver/kuaidi.htm" style="color:grey;">${vo.keyword }</a></span>
				</c:forEach>
			</div>
		</div>
	</div>

	
	
    <script  src="../js/index.js"></script>
	


</body>
</html>