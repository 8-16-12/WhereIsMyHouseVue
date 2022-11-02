<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/include/header.jsp" %>

<!--search overlay end -->
<section class="section-padding">
  <div class="container">
    <h2 style="font-family: Open Sans">${user.name}님 회원정보</h2>
    
    <form id="updateform" action="${root}/update.do">
    
    <div class="border 1px solid mt-3">
      <div class="col-md-12 mb-3 mt-2">
        <label for="id">아이디 :</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="" value="${user.id}" required>
      </div>
      
      <div class="col-md-12 mb-3">
        <label for="pass">비밀번호 :</label>
        <input type="password" class="form-control" id="pass" name="pass" placeholder="" value="${user.pass}" required>
      </div>

      <div class="col-md-12 mb-3">
        <label for="name">이름 : </label>
        <input type="text" class="form-control" id="name" name="name" placeholder="" value="${user.name}" required>
      </div>
      
      <div class="col-md-12 mb-3">
        <label for="birth">생년월일 : </label>
        <input type="text" class="form-control" id="birth" name="birth" placeholder="" value="${user.birth}" required>
      </div>

      <div class="col-md-12 mb-3">
        <label for="email">이메일 : </label>
        <input type="email" class="form-control" id="email" name="email" placeholder="" value="${user.email}" required>
      </div>

      <div class="col-md-12 mb-3">
        <label for="addr">주소 : </label>
        <input type="text" class="form-control" id="addr" name="addr" placeholder="" value="${user.addr}" required>
      </div>

      <div class="col-md-12 mb-3">
        <label for="addr2">상세주소 : <span class="text-muted">&nbsp;(필수 아님)</span></label>
        <input type="text" class="form-control" id="addr2" name="addr2" placeholder="" value="${user.addr2}">
      </div>
    </div>
    <div class="text-center mt-5">
      <a class="btn btn-primary" href="${root}/delete.do?id=${user.id}" role="button"> 탈퇴 </a>
      <a class="btn btn-primary" href="${root}/index.jsp" role="button"> 확인  </a>
      <button type="submit" class="btn btn-primary" id="regist">수정</button>
    </div>
    
    </form>
  </div>
</section>
<%@ include file="/include/footer.jsp" %>
