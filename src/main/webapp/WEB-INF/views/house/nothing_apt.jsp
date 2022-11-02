<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
<body>
	<div class="container p-4">

		<img src="${root}/img/noApt02.gif" height="100%" border="10">
				
		<h4 class="text-center mt-3 mb-3" style="font-family: Open Sans"> 해당 아파트 데이터는 없습니다! </h4>
		<h5 class="text-center mt-5 mb-5" style="font-family: Open Sans"> 아파트 이름을 다시 확인해주세요! </h2>
	</div>
	<%-- 출력 내용 --%>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>