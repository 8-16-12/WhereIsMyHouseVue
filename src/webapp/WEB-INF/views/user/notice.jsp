<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/include/header.jsp" %>
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

<%@ include file="/include/footer.jsp" %>