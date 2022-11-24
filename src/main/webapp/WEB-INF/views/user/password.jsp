<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
		<div class="container">
          <form method="post" id="llogin" name="login_frm" action="${root}/user/password">
            <div class="mb-3 mt-3">
              <label for="text" class="form-label">아이디:</label>
              <input type="text" class="form-control" id="id" name="id" placeholder="아이디" name="아이디">
            </div>
            <div class="mb-3">
              <label for="pass" class="form-label">이메일:</label>
              <input type="text" class="form-control" id="email" name="email" placeholder="이메일" name="email">
            </div>
            
            <button type="submit" class="btn btn-primary" id="password">비밀번호 찾기</button>
          </form>
        </div>
		
		
		


<%@ include file="/WEB-INF/views/include/footer.jsp" %>