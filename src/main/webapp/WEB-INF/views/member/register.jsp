<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if(${result==0 }){
		alert('가입 실패');
	}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${contextPath }/resources/js/daum_post.js"></script>
</head>
<body>
<%@ include file="../default/header.jsp" %>
		
	<h2 class="title">회원 가입</h2>
	<form action="regChk" method="post">
	
		<div class="field">
		  <label class="label">아이디</label>
		  <div class="control">
		    <input class="input" id="id" name="id" type="text" placeholder="아이디를 입력하세요.">
		  </div>
		</div>
		
		<div class="field">
		  <label class="label">비밀번호</label>
		  <div class="control">
		    <input class="input" id="pwd" name="pwd" type="password" placeholder="비밀번호를 입력하세요.">
		  </div>
		</div>
		
		<div class="field">
		  <label class="label">우편번호</label>
		  <div class="control">
		    <input class="input" id="addr1" name="addr" type="text" placeholder="우편번호" readonly>
		  	<input class="button" type="button" onclick="daumPost()" value="우편번호찾기">
		  </div>
		</div>
		
		<div class="field">
		  <label class="label">주소</label>
		  <div class="control">
		    <input class="input" id="addr2" name="addr" type="text" placeholder="주소" readonly>
		    <input class="input" id="addr3" name="addr" type="text" placeholder="상세주소">
		  </div>
		</div>
		
		<div class="field is-grouped is-grouped-centered">
		  <p class="control">
		    <button class="button is-primary" type="submit">
		      	가입
		    </button>
		  </p>
		  <p class="control">
		    <button class="button is-light" type="reset">
		      	취소
		    </button>
		  </p>
		</div>
		
	</form>

<%@ include file="../default/footer.jsp" %>
</body>
</html>