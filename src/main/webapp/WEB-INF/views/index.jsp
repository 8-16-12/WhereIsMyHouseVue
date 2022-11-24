<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<section class="section-padding">
	
	<h2 class="text-center mt-1 mb-5" style="font-family: Open Sans">
		행복한 우리 집! </t> 같이 찾아봐요! 
	</h2>
	
	<div class="row col-md-10 justify-content-center mb-2">
		<div class="form-group col-md-1">
			<img src="${root}/img/intro2.gif" height="100%" border="10">
		</div>
	</div>

	<div class="container">
		<div style="height: 150px">
			<div class="row">

				<!--프로그램 언어 시작-->
				<div class="mb-2 mt-3">
					<h4 style="font-family: Open Sans">[부동산 관련 추천 어플]</h4>
				</div>
				<div class="row mb-2">

					<div class="col-sm-3">
						<img src="images/app/app1.png" alt="직방" class="img-thumbnail"
							width="300" height="240">
					</div>
					<div class="col-sm-3">
						<img src="images/app/app2.png" alt="다방" class="img-thumbnail"
							width="300" height="240">
					</div>
					<div class="col-sm-3">
						<img src="images/app/app3.png" alt="네이버 부동산" class="img-thumbnail"
							width="300" height="240">
					</div>
					<div class="col-sm-3">
						<img src="images/app/app4.png" alt="한국부동산원" class="img-thumbnail"
							width="300" height="240">
					</div>

					<!--프로그램 언어 종료-->

				</div>
			</div>
</section>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

