<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${contextPath }/resources/js/daum_post.js"></script>
<script type="text/javascript">
function pwdchg(){
	console.log(document.getElementById("new_pwd").value)
	if(document.getElementById("new_pwd").value != '********'){
		document.getElementById("pwdChange").innerHTML = '비밀번호 변경됨';
	}
	if(document.getElementById("new_pwd").value == ''){
		document.getElementById("new_pwd").value = '********'
		document.getElementById("pwdChange").innerHTML = '';
	}
}
</script>
</head>
<body>
<%@ include file="../default/header.jsp" %>
<c:set var="addr1" value="${addr[0] }"/>
<c:set var="addr2" value="${addr[1] }"/>
<c:set var="addr3" value="${addr[2] }"/>

<div class="wrap">
	<div class="titles">
		<h2 class="title">My Page</h2>
		<h2 class="subtitle">${dto.id }정보</h2>
	</div>	
	<form action="modify" method="post" onsubmit="pwdChange()">
	
		<div class="field">
		  <label class="label">아이디</label>
		  <div class="control">
		    <input class="input" id="id" name="id" type="text" value="${dto.id }" readonly>
		  </div>
		</div>
		
		<div class="field">
		  <label class="label">비밀번호</label>
		  <div class="control">
		    <input class="input" id="new_pwd" name="new_pwd" type="password" value="********" readonly onchange="pwdchg()">
		    <input class="input" id="pwd" name="pwd" type="hidden" value="${dto.pwd }">
		  </div>
		  <p id="pwdChange" class="help is-danger"></p>
		</div>
		
		<div class="field">
		  <label class="label">우편번호</label>
		  <div class="control">
		    <input class="input" id="addr1" name="addr" type="text" value="${addr1 }" readonly>
		  	<input class="button" type="button" onclick="daumPost()" value="우편번호찾기">
		  </div>
		</div>
		
		<div class="field">
		  <label class="label">주소</label>
		  <div class="control">
		    <input class="input" id="addr2" name="addr" type="text" value="${addr2 }" readonly>
		    <input class="input" id="addr3" name="addr" type="text" value="${addr3 }" readonly>
		  </div>
		</div>

		<!-- 이 부분을 c:if 안에 넣으면 js말고 jstl로도 처리 가능 -->
		<div class="field is-grouped is-grouped-centered" id="btns" style="visibility: hidden">
		  <p class="control">
		    <button class="button is-primary" type="submit">
		      	수정
		    </button>
		  </p>
		  <p class="control">
		    <button class="button is-danger" type="button" onclick="location.href='delete?id=${dto.id }'">
		      	삭제
		    </button>
		  </p>
		  <p class="control">
		    <button class="button" type="button" onclick="history.back()">
		      	뒤로가기
		    </button>
		  </p>
		</div>
		
	</form>
</div>
<script type="text/javascript">
if('${dto.id}'=='${loginUser}'){
	var btns = document.getElementById("btns");
	console.log(btns)
	btns.style.visibility = 'visible'
	
	var pwd = document.getElementById("new_pwd");
	pwd.readOnly = false;
	
	var addr = document.getElementById("addr3");
	addr.readOnly = false;
}
</script>
<script type="text/javascript">
function pwdChange(){
	var new_pwd = document.getElementById("new_pwd").value;
	document.getElementById("pwd").value = new_pwd;
}
</script>
<%@ include file="../default/footer.jsp" %>
</body>
</html>