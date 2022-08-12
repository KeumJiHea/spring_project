<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CARE LAB</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${contextPath }/resources/css/header.css?after">
</head>
<body>
<div id="wrap">
<header>
	<h1 class="header" onclick="location.href='${contextPath }/index'" style="cursor: pointer;">CARE LAB</h1>

	<nav class="navbar is-white" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a class="navbar-item" href="${contextPath }/index">
		      <img src="${contextPath }/resources/images/hamster.jpg" width="112" height="50">
		    </a>
		</div>
		
		<div id="careLabNavbar" class="navbar-menu">
			<div class="navbar-start">
				<a class="navbar-item" href="${contextPath }/index">
					HOME
				</a>
				<a class="navbar-item" href="${contextPath }/board/board">
					BOARD
				</a>
			</div>
	
			<div class="navbar-end">
				<a class="navbar-item" href="${contextPath }/member/memberInfo">
					회원정보
				</a>
				<c:choose>
					<c:when test="${loginUser != null}">
						<div class="buttons">
							<a class="button is-primary" href="${contextPath }/member/logout">
								로그아웃
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="buttons">
							<a class="button is-primary" href="${contextPath }/member/login">
								로그인
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
</header>
</body>
