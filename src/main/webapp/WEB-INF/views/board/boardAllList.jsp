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
	<h2>게시판</h2>
	<table class="table is-striped">
		<tr>
			<th>번호</th><th>아이디</th><th>제목</th>
			<th>날짜</th><th>조회수</th><th>이미지 이름</th>
		</tr>
		
		<c:if test="${list != 'null' }">
			<tr>
				<c:forEach var="dto" items="${list }">
					<td>${dto.writeNo }</td><td>${dto.id }</td><td>${dto.title }</td>
					<td>${dto.savedate }</td><td>${dto.hit }</td><td>${dto.imageFileName }</td>
				</c:forEach>
			</tr>
		</c:if>
		<c:if test="${list == 'null' }">
			<tr>
				<td colspan="6">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
	
		<tr>
			<td colspan="6"><a href="${contextPath }/board/writeForm">글 작성</a></td>
		</tr>
	</table>
</div>

<%@ include file="../default/footer.jsp" %>
</body>
</html>