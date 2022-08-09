<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if(${loginResult==1}){
		alert('${loginUser}님 환영합니다.')
	}else if(${loginResult==0}){
		alert('존재하지 않는 아이디입니다.')
	}else if(${loginResult==-1}){
		alert('잘못된 비밀번호입니다.')
	}
	
	if(${regResult==1}){
		alert('회원가입이 성공했습니다. 로그인 해주세요.')
	}
</script>
</head>
<body>
	<%@ include file="default/header.jsp" %>
	<%@ include file="default/main.jsp" %>
	<%@ include file="default/footer.jsp" %>
</body>
</html>