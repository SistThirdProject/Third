<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script type="text/javascript">


	$(function() {
		$("div").mouseover(function() {
			$(this).css("color", "red");
		});
	});

</script>
<style type="text/css">
ol {
	list-style-type: decimal-leading-zero;
}
;
</style>
</head>
<body>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<div class="container">
		<c:forEach var="set" items="${setList}">
			<div class="panel panel-default col-lg-2">
				<div class="panel-heading">${set.time}시</div>
				<c:forEach var="keywordvo" items="${set.keywords}">
					<c:choose>
						<c:when test="${keywordvo.keyword.length() ge 10 }">
							<div class="panel-body keyword"
								style="padding-top: 0px; padding-bottom: 0px;">${fn:substring(keywordvo.keyword,0,9)}...</div>
						</c:when>
						<c:otherwise>
							<div class="panel-body keyword"
								style="padding-top: 0px; padding-bottom: 0px;">${keywordvo.keyword}</div>
						</c:otherwise>

					</c:choose>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<div class="container">
		<iframe src="../graph.html" frameborder="0" width=950 height=500></iframe>
	</div>
	<div id="sliderContainer">
		<div class="centerContainer">
			<div id="sliderBtnPrev">
				<img src="../img/btnPrev.png" alt="">
			</div>
			<div id="mainSlider"
				style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 840px;">
				<ul
					style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 5040px; left: -840px;">
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>실시간 검색어</h1>
						<p class="sliderParagraph">각 포털의 실시간 검색어 리스트를 한눈에 보여줍니다.</p>
						<p class="sliderParagraph">Naver, Daum, ZUM</p>

						<div class="blankSeparator"></div>
					</li>
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>월간 검색어</h1>
						<p class="sliderParagraph">지난 날짜의 실시간 검색어 리스트를 시간 별로 찾아볼수 있습니다
							(현재 Naver만 지원)</p>
						<div class="blankSeparator"></div> <a href="month_search.do"
						class="darkButton fl">Use it now! <img src="month_search.do"
							alt="">
					</a>

					</li>
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>뉴스 통계</h1>
						<p class="sliderParagraph">지난 10년 간의 뉴스 데이터를 분석하여 시각화 합니다.</p>
						<div class="blankSeparator"></div> <a href="news_stats.do"
						class="darkButton fl">Use it now! </a>

						</p>
					</li>
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>채팅</h1>
						<p class="sliderParagraph">Web Socket 프로토콜을 사용한 실시간 채팅을 지원합니다.
							각자의 브라우저에서 대화를 해보세요</p>
						<div class="blankSeparator"></div> <a href="wschat.do"
						class="darkButton fl">Use it now! <img
							src="../img/darkButtonImg.png" alt="wschat.do">
					</a>
						<p class="additionalOptions fl">
							or <a href="#">Find Out More</a>
						</p>
					</li>
				</ul>
			</div>
			<div id="sliderBtnNext">
				<img src="../img/btnNext.png" alt="">
			</div>
		</div>
	</div>

	<div class="centerContainer">
		<div id="leftContainer" class="fl">
			<h3 class="sectionTitle">
				latest <span>news</span>
			</h3>

		</div>

		<div id="rightContainer" class="fr">
			<h3 class="sectionTitle">연관 검색어</h3>
			<div id="tagsList" style="margin: 0px auto; background-color: white;">
				<c:forEach var="vo" items="${list }">
					<span><a href="http://www.hao123.com/haoserver/kuaidi.htm"
						style="color: grey;">${vo.keyword }</a></span>
				</c:forEach>
			</div>
		</div>
	</div>



	<script src="../js/index.js"></script>
</body>
</html>