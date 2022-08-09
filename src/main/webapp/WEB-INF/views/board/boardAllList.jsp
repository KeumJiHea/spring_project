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
<section>
	<h2 class="title">게시판</h2>
	<table class="table is-striped">
		<tr>
			<th>번호</th><th>아이디</th><th>제목</th>
			<th>날짜</th><th>조회수</th><th>이미지 이름</th>
		</tr>
		
		<c:if test="${list != 'null' }">
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${dto.writeNo }</td><td>${dto.id }</td><td>${dto.title }</td>
					<td>${dto.savedate }</td><td id="hit">${dto.hit }</td><td>${dto.imageFileName }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${list == 'null' }">
			<tr>
				<td colspan="6">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<button class="button is-primary" id="writeButton" onclick="location.href='${contextPath }/board/writeForm'">
		새 글 작성
	</button>
</section>

<%@ include file="../default/footer.jsp" %>
</body>
</html>