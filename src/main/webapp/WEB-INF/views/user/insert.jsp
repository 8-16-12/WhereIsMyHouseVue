<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mt-3 mb-3" style="font-family: Open Sans">회원가입</h4>
        <form id="insert-form" method="get" action="${root}/insert">

          <div class="mb-3">
             <label for="id">아이디</label>
             <input type="text" class="form-control" id="id" name="id" placeholder="아이디" value="" required>
           </div>
          
          <div class="mb-3">
             <label for="pass">비밀번호</label>
             <input type="password" class="form-control" id="pass" name="pass" placeholder="숫자와 영어를 혼합한 비밀번호가 안전합니다" value="" required>
          </div>

          <div class="mb-3">
             <label for="name">이름</label>
             <input type="text" class="form-control" id="name" name="name" placeholder="이름" value="" required>
          </div>
          
          <div class="mb-3">
            <label for="birth">생년월일</label>
            <input type="text" class="form-control" id="birth" name="birth" placeholder="1997.03.09" required>
          </div>

          <div class="mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required>
          </div>

          <div class="mb-3">
            <label for="addr">주소</label>
            <input type="text" class="form-control" id="addr" name="addr" placeholder="서울특별시 강남구" required>
          </div>

          <div class="mb-3">
            <label for="addr2">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label>
            <input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소를 입력해주세요.">
          </div>
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          <div class="mb-4"></div>
          <!-- <a class="btn btn-primary" role="button"> 회원가입 </a>  -->
          <button type="submit" class="btn btn-primary" id="insert">등록</button>
        </form>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
    </footer>
 </div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>