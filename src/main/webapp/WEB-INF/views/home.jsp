<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<style type="text/css">
.wrapper{
	margin: 0 auto;
	width: 1000px;
	z-index: 1;
}

.bgImg{
	position: fixed;
	width: 1000px;
	height: 100%;
	background-image: url('resources/images/ocean.png');
	background-size: cover;
	opacity: 0.6;
	z-index: -1;
}

.topFrame {
	height: 100px;
	text-align: center;
}

.title{
	font-family: "맑은고딕";
	font-size: 14pt;
	color: #000000;
	font-weight: bold;
}

section{
	display: flex;
	height: 600px;
}
.leftFrame{
	width: 150px;
}

.mainFrame{
	width: 850px;
}

.sites > div{
	margin: 30px;
}

.footerFrame{
	height: 50px;
	text-align: center;
}
</style>
</head>
<body>
<div class="wrapper">
	<div class="bgImg"></div>
	<header class="topFrame">
		<h1 class="title">Title</h1>
	</header>
	
	<section>
		<aside class="leftFrame">
			<nav>
				<ul style="list-style-type: none; position: fixed; overflow: auto;">
					<li><a href="/index" title="홈으로 가기">
						<img src="https://cdn-icons-png.flaticon.com/512/709/709873.png" alt="홈 버튼" width="20" height="20">
						홈으로 가기
					</a></li>
					<li><a href="javascript:history.back();" title="뒤로가기">
						<img src="https://png.pngtree.com/png-vector/20190411/ourlarge/pngtree-vector-back-icon-png-image_927284.jpg" alt="뒤로가기 버튼" width="20" height="20">
						뒤로 가기
					</a></li>
					<li><a href="" title="메뉴3">메뉴3</a></li>
					<li><a href="" title="메뉴4">메뉴4</a></li>
					<li><a href="" title="메뉴5">메뉴5</a></li>
				</ul>
			</nav>
		</aside>
		<main class="mainFrame" style="margin-left:150px; overflow: auto;">
		<div class="sites">
			<div class="naver">
				<a href="https://www.naver.com" title="네이버로 가기">
					<img alt="네이버 로고" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Naver_Logotype.svg/2560px-Naver_Logotype.svg.png" width="100px" height="20px">
					네이버로 이동
				</a>
			</div>
			<div class="google">
				<a href="https://www.google.com" title="구글로 가기">
					<img alt="구글 로고" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2560px-Google_2015_logo.svg.png" width="100px" height="30px">
					구글로 이동
				</a>
			</div>
			<div class="daum">
				<a href="https://www.daum.net" title="다음으로 가기">
					<img alt="다음 로고" src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Daum_communication_logo.svg/2560px-Daum_communication_logo.svg.png" width="100px" height="30px">
					다음으로 이동
				</a>
			</div>
		</div>
			<a href="javascript:window.scrollTo(0,0);" title="맨 위로" style="display:scroll; position:fixed; bottom:10px; right: 10px">
				<img src="https://cdn-icons-png.flaticon.com/512/159/159665.png" alt="위로 가기 버튼" width="20" height="20">
			</a>
		</main>
	</section>
	
	<footer class="footerFrame">
		Footer frame
	</footer>
</div>
</body>
</html>
