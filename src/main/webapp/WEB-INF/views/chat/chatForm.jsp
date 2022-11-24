<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/chatt.css''>
<style>
	/* img { display: block; margin: 0px auto; } */
</style>
</head>
<body>
	<div class="container-fluid px-4">
		<h2 class="text-center mt-5 mb-5" style="font-family: Open Sans">
			채팅방 입장하기</h2>
		<div class="container">
			<form id="chatt" action="${root}/chat">
				<div class="row col-md-12 justify-content-center mb-2">
					<div class="form-group col-md-2">
						<img src="${root}/img/chat.gif" height="100%" border="10">
					</div>
					
					<div class="form-group col-md-4">
						<input type="text" name="nickName" class="form-control"
							placeholder="닉네임을 설정하세요!">
					</div>
					
					<div class="form-group col-md-2">
						<button type="submit" id="btnLogin" class="btn btn-primary"
							style="width: 100%">Enter</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>