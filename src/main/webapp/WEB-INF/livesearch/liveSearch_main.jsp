<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<div id="serviceListContainer">
		<div class="centerContainer">
			<div class="oneThirdCol" id="col1">
				<img src="../img/naver.png" alt="" class="fl"
					style="width: 50px; height: 50px">
				<div class="centerHeadings">
					<h3>Naver</h3>
				</div>
				<p></p>
				<ul>
					<li><a href="#">Scalable web hosting</a></li>
					<li><a href="#">Custom built web interface</a></li>
					<li><a href="#">Another great service</a></li>
				</ul>
			</div>
			<div class="oneThirdCol" id="col2">
				<img src="../img/daum.png" alt="" class="fl"
					style="width: 50px; height: 50px">
				<h3>Daum</h3>
				<p></p>
				<ul>
					<li><a href="#">Scalable web hosting</a></li>
					<li><a href="#">Custom built web interface</a></li>
					<li><a href="#">Another great service</a></li>
				</ul>
			</div>
			<div class="oneThirdCol lastCol" id="col3">
				<img src="../img/zum.png" alt="" class="fl"
					style="width: 50px; height: 50px">
				<h3>ZUM</h3>
				<p></p>
				<ul>
					<li><a href="#">Scalable web hosting</a></li>
					<li><a href="#">Custom built web interface</a></li>
					<li><a href="#">Another great service</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="sliderContainer">
		<div class="centerContainer">
			<div id="sliderBtnPrev">
				<img src="../img/btnPrev.png" alt="">
			</div>
			<div id="mainSlider"	style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 840px;">
				<ul style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 5040px; left: -840px;">
					<li	style="overflow: hidden; float: left; width: 720px; height: 260px;">
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
						<p class="sliderParagraph">지난 날짜의 실시간 검색어 리스트를 시간 별로 찾아볼수 있습니다 (현재 Naver만 지원)</p>
						<div class="blankSeparator"></div> <a href="month_search.do" class="darkButton fl">Use
							it now! <img src="month_search.do" alt="">
					</a>
						
					</li>
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>뉴스 통계</h1>
						<p class="sliderParagraph">지난 10년 간의 뉴스 데이터를 분석하여 시각화 합니다. </p>
						<div class="blankSeparator"></div> <a href="news_stats.do" class="darkButton fl">Use
							it now! 
					</a>
		
						</p>
					</li>
					<li
						style="overflow: hidden; float: left; width: 720px; height: 260px;">
						<img src="../img/sliderImg1.png" alt="" class="slideImg fl">
						<h1>채팅</h1>
						<p class="sliderParagraph">Web Socket 프로토콜을 사용한 실시간 채팅을 지원합니다. 각자의 브라우저에서 대화를 해보세요</p>
						<div class="blankSeparator"></div> <a href="wschat.do" class="darkButton fl">Use
							it now! <img src="../img/darkButtonImg.png" alt="wschat.do">
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
</body>
</html>