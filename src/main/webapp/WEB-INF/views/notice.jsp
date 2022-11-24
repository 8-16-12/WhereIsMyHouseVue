<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<section class="section-padding">

  <div class="container">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>날짜</th>
          <th>조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th>1</th>
          <th>바른말 고운말 합시다</th>
          <th>강유림</th>
          <th>2022-09-07</th>
          <th>1</th>
        </tr>
        <tr>
          <th>0</th>
          <th>저한테 컴플레인 하지 마세요</th>
          <th>김도균</th>
          <th>2022-09-07</th>
          <th>58</th>
        </tr>
      </tbody>
    </table>
    <hr>
    <div class="d-md-flex justify-content-md-end">
      <button type="button" class="btn btn-primary border-0" style="background-color: #ce8460;" disabled>글쓰기</button>
    </div>
    
    <div class="text-center">
      <ul class="pagination justify-content-center">
        <li class="page-item"><a class="page-link" href="#" >Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
      </ul>  
    </div>
  </div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %> --%>
<!doctype html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="icon" href="/favicon.ico">
<title>vueclitest</title>
<script defer="defer" src="${root }/js/chunk-vendors.11f423a5.js"></script>
<script defer="defer" src="${root }/js/app.b9e2ed7b.js"></script>
<link href="${root }/css/chunk-vendors.44332441.css" rel="stylesheet">
<link href="${root }/css/app.15a3fd1f.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<noscript>
		<strong>We're sorry but vueclitest doesn't work properly
			without JavaScript enabled. Please enable it to continue.</strong>
	</noscript>
	<div id="app"></div>
</body>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>
