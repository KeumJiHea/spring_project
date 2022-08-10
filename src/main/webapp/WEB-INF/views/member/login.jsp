<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<div class="wrap">
		<h3 class="subtitle">로그인</h3>
		<form action="successLogin" method="post" class="box">
			<div class="field">
			  <p class="control">
			    <input class="input" name="id" placeholder="아이디">
			  </p>
			</div>
			<div class="field">
			  <p class="control">
			    <input class="input" type="password" name="pwd" placeholder="비밀번호">
			  </p>
			</div>
			<div class="field is-grouped is-grouped-centered">
			  <p class="control">
			    <button class="button is-success" type="submit">
			      	로그인
			    </button>
			  </p>
			    <button class="button is-info" type="button" onclick="location.href='${contextPath }/member/register'">
			      	회원가입
			    </button>
			</div>
			<label class="checkbox">
			  <input type="checkbox" name="autoLogin">
			  	자동 로그인
			</label>
		</form>
	</div>
	<%@ include file="../default/footer.jsp" %>
</body>
</html>