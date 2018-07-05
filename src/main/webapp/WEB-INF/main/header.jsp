<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>


	<div id="topContainer">
		<div class="centerContainer">
			<a href="live_search.do" class="logo fl"><img
				src="../img/logo.png" alt="" /></a>

		</div>
	</div>
	<center>
	 	<div id="menuContainer" style="text-align: center;">
			<div class="centerContainer">
				<ul id="nav">
					<li><a href="live_search.do">실시간 검색어<span></span></a></li>
					<li><a href="month_search.do">월간 검색어<span></span></a></li>
					<li><a href="news_stats.do">뉴스 통계<span></span></a></li>
					<li><a href="wschat.do">채팅<span></span></a></li>
					<li><a href="list.do">게시판<span></span></a></li>
				</ul>
				
				<div class="oneThirdCol" style="margin:15px;">
	
					<form action="#" method="post" id="subscribeForm">
						<fieldset>
							<div>
								<input type="text" id="newsletterEmailAddress" class="fl" /> <a
									href="#" id="newsletterSignup" class="redButton"><img
									src="../img/redButtonImg.png" alt="" /></a>
							</div>
						</fieldset>
					</form>
				
			   </div>
			
			</div>
		</div> 

		<!-- <!--  ------------------------------------------------------------------------------------------------------------------------->
		<!-- <div id="menuContainer">
			<div class="centerContainer">
				<ul id="nav">
					<li><a href="live_search.do"><cufon
								class="cufon cufon-canvas" alt="Home"
								style="width: 39px; height: 14px;">
							<canvas width="57" height="17"
								style="width: 57px; height: 17px; top: -2px; left: -3px;"></canvas>
							<cufontext>Live KeyWord</cufontext></cufon><span><cufon
									class="cufon cufon-canvas" alt="go "
									style="width: 17px; height: 12px;">
								<canvas width="36" height="15"
									style="width: 36px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>go </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="to "
									style="width: 15px; height: 12px;">
								<canvas width="34" height="15"
									style="width: 34px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>to </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="start"
									style="width: 26px; height: 12px;">
								<canvas width="43" height="15"
									style="width: 43px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>start</cufontext></cufon></span></a></li>
					<li><a href="month_search.do"><cufon
								class="cufon cufon-canvas" alt="Services"
								style="width: 60px; height: 14px;">
							<canvas width="78" height="17"
								style="width: 78px; height: 17px; top: -2px; left: -3px;"></canvas>
							<cufontext>월간 검색어</cufontext></cufon><span><cufon
									class="cufon cufon-canvas" alt="what "
									style="width: 31px; height: 12px;">
								<canvas width="50" height="15"
									style="width: 50px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>what </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="we "
									style="width: 20px; height: 12px;">
								<canvas width="39" height="15"
									style="width: 39px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>we </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="do"
									style="width: 15px; height: 12px;">
								<canvas width="30" height="15"
									style="width: 30px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>do</cufontext></cufon></span></a></li>
					<li><a href="news_stats.do"><cufon
								class="cufon cufon-canvas" alt="Gallery"
								style="width: 57px; height: 14px;">
							<canvas width="74" height="17"
								style="width: 74px; height: 17px; top: -2px; left: -3px;"></canvas>
							<cufontext>뉴스 통계</cufontext></cufon><span><cufon
									class="cufon cufon-canvas" alt="our "
									style="width: 22px; height: 12px;">
								<canvas width="41" height="15"
									style="width: 41px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>our </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="best "
									style="width: 28px; height: 12px;">
								<canvas width="46" height="15"
									style="width: 46px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>best </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="products"
									style="width: 50px; height: 12px;">
								<canvas width="66" height="15"
									style="width: 66px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>products</cufontext></cufon></span></a></li>
					<li><a href="wschat.do"><cufon class="cufon cufon-canvas"
								alt="Our " style="width: 32px; height: 14px;">
							<canvas width="54" height="17"
								style="width: 54px; height: 17px; top: -2px; left: -3px;"></canvas>
							<cufontext>채팅</cufontext></cufon>
							<cufon class="cufon cufon-canvas" alt="Clients"
								style="width: 52px; height: 14px;">
							<canvas width="70" height="17"
								style="width: 70px; height: 17px; top: -2px; left: -3px;"></canvas>
							<cufontext>Clients</cufontext></cufon><span><cufon
									class="cufon cufon-canvas" alt="what "
									style="width: 31px; height: 12px;">
								<canvas width="50" height="15"
									style="width: 50px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>what </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="we've "
									style="width: 36px; height: 12px;">
								<canvas width="55" height="15"
									style="width: 55px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>we've </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="done "
									style="width: 32px; height: 12px;">
								<canvas width="51" height="15"
									style="width: 51px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>done </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="for "
									style="width: 20px; height: 12px;">
								<canvas width="39" height="15"
									style="width: 39px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>for </cufontext></cufon>
								<cufon class="cufon cufon-canvas" alt="others"
									style="width: 36px; height: 12px;">
								<canvas width="52" height="15"
									style="width: 52px; height: 15px; top: -2px; left: -2px;"></canvas>
								<cufontext>others</cufontext></cufon></span></a></li>


				</ul>
			</div>
		</div> -->
 


	</center>


</body>
</html>