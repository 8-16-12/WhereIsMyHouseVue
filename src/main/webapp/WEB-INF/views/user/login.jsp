<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

		<div class="container">
          <form method="post" action="${root}/user/login">
            <div class="mb-3 mt-3">
              <label for="text" class="form-label">아이디:</label>
              <input type="text" class="form-control" id="id" name="id" placeholder="아이디" name="아이디">
            </div>
            <div class="mb-3">
              <label for="pass" class="form-label">비밀번호:</label>
              <input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" name="pswd">
            </div>
            <div class="form-check mb-3">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember"> Remember me
              </label>
            </div>
            <button type="submit" class="btn btn-primary" id="login">로그인</button>
          </form>
        </div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>