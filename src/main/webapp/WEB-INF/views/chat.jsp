<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/chatt.css''>
</head>
<body>
	<div id='chatt'>
		<p>실시간 채팅</p>
		<input type='text' id='mid' value='홍길동'>
		<input type='button' value='로그인' id='btnLogin'>
		<br/>
		<div id='talk'></div>
		<div id='sendZone'>
			<textarea id='msg' value='hi...' ></textarea>
			<input type='button' value='전송' id='btnSend'>
		</div>
	</div>
	<script src='./js/chatt.js'></script>
</body>
</html>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>