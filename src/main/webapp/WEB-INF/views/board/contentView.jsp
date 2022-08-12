<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function slideClick(){
	$("#first").slideDown("slow");
	$("#modal_wrap").show();
}
function slide_hide(){
	$("#title").val("");
	$("#content").val("");
	
	$("#first").hide();
	$("#modal_wrap").hide();
}
function rep(){
/* 	
	아이디로 가져오는 거
	let form={ 
			write_no : $("#write_no").val(),
			title: $("#title").val(),
			content: $("#content").val()
			}
	console.log(form)
*/
	/*네임으로 가져오는 거*/
	let form={}
	let arr = $("#frm").serializeArray();
	console.log(arr);
	for(i=0 ; i<arr.length ; i++){
		form[arr[i].name] = arr[i].value
	}
	console.log(form);
	$.ajax({
		url: "addReply", type: "post",
		data: JSON.stringify( form ),
		contentType: "application/json;charset=utf8",
		success: function(){
			alert("답글이 달렸습니다.")
			slide_hide();
			replyData();
		}
	})
}

function replyData(){
	$.ajax({
		//url: "replayData?writeNo="+${dto.writeNo}
		//위는 컨트롤러 매개변수 int writeNo 받으면 됨
		
		url: "replyData/"+${dto.writeNo}, type: "get",
		dataType: "json",
		success: function(rep){
			console.log(rep)
			let html = ""
			for(i=0 ; i<rep.length ; i++){
				let data = new Date(rep[i].write_date);
				let wd = data.getFullYear()+"년 ";
				wd += (data.getMonth()+1) + "월 "; //월은 0부터 시작하기 때문
				wd += data.getDate() + "일 ";
				wd += data.getHours() + "시 ";
				wd += data.getMinutes() + "분 ";
				wd += data.getSeconds() + "초 ";
				
				html += "<div><b>아이디: </b>"+rep[i].id+"님/"
				html += "<b>작성일: </b>"+wd+"<br>"
				html += "<b>제목: </b>"+rep[i].content+"<hr></div>"
			}
			$("#reply").html(html)
		}
	})
	
}
</script>
</head>
<body onload="replyData()">
<div id="modal_wrap">
<div id="first">

<div style="width: 250px; margin: auto; padding-top: 20px;">
	<form id="frm">
		<input type="hidden" id="write_no" name="write_no" value="${dto.writeNo }">
		<b>답글 작성 페이지</b><hr>
		<b>작성자: </b>${loginUser }<hr>
		<b>제목</b><br>
		<input type="text" id="title" name="title" size="30"><hr>
		<b>내용</b><br>
		<textarea rows="5" cols="30" name="content" id="content"></textarea>
		<hr>
		<div class="field is-grouped is-grouped-centered">
			<button class="button is-primary" type="button" onclick="rep()">답글</button>
			<button class="button" type="button" onclick="slide_hide()">취소</button>
		</div>
	</form>
</div>
	
</div>
</div>
<c:import url="../default/header.jsp" />
<table class="table">
   <tr>
      <th width="100">글 번호</th> <td width="200">${dto.writeNo}</td> 
      <th width="100">작성자</th>  <td width="200">${dto.id}</td>
   </tr>
   <tr>
      <th>제목</th> <td>${dto.title}</td> 
      <th>등록일자</th> <td>${dto.savedate}</td>
   </tr>
   <tr>
      <th>내용</th><td>${dto.content}</td> 
      <td colspan="2">
         <c:if test="${ dto.imageFileName == 'nan' }"> <b>이미지가 없습니다</b> </c:if>
         <c:if test="${ dto.imageFileName != 'nan' }">
            <img width="200px" height="100px" 
               src="${contextPath}/board/download?imageFileName=${dto.imageFileName}">
         </c:if>
      </td>
   </tr>
   <tr>
      <td colspan="4" align="center">
      <div class="field is-grouped is-grouped-centered">
         <c:if test="${ loginUser == dto.id }">
            <input class="button is-primary" type="button" onclick="location.href='modify_form?writeNo=${dto.writeNo}'" value="수정하기"> 
            <input class="button is-danger" type="button" onclick="location.href='delete?writeNo=${dto.writeNo}&imageFileName=${dto.imageFileName}'" value="삭제하기">
         </c:if>
      </div>
         <br>
      <div class="field is-grouped is-grouped-centered">
         <input class="button is-primary" type="button" onclick="slideClick()" value="답글달기"> 
         <input class="button" type="button" onclick="location.href='board'" value="리스트로 돌아가기">
      </div>
      </td>
   </tr>
   <tr>
		<td colspan="4">
			<div id="reply"></div>
		</td>
   </tr>
</table>
<c:import url="../default/footer.jsp" />
</body>
</html>
