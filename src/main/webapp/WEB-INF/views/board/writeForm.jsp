<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../default/header.jsp" %>
<html>
<script type="text/javascript">
	function readURL(input){
		var file = input.files[0]
		if(file != ''){
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
		}
	}
</script>
<body>

<div class="wrap">
	<form action="write" method="post" class="box" enctype="multipart/form-data">
			<div class="field">
				<label class="label">
					작성자
				</label>
				<p class="control">
					<input class="input" name="id">
				</p>
			</div>
			<div class="field">
				<label class="label">
					제목
				</label>
				<p class="control">
					<input class="input" name="title">
				</p>
			</div>
			<div class="field">
				<label class="label">
					내용
				</label>
				<p class="control">
			    	<textarea class="textarea" name="content"></textarea>
				</p>
			</div>
			<div class="field">
				<label class="label">
					파일 첨부
				</label>
				<p class="control">
			  	  <input class="input" type="file" name="f" onchange="readURL(this);">
			  	  <img src="#" id="preview" width="100" height="100" alt="No Image">
				</p>
			</div>
			<div class="field is-grouped is-grouped-centered">
				<p class="control">
			    	<button class="button is-success" type="submit">
			     	 	글쓰기
					</button>
				</p>
			    <button class="button is-info" type="button" onclick="location.href='${contextPath }/board/board'">
			      	목록 보기
				</button>
			</div>
		</form>
</div>


</body>
</html>
<%@ include file="../default/footer.jsp" %>