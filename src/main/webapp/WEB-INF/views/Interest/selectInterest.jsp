<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/include/header.jsp"%>
<title>관심지역 둘러보기</title>
</head>

<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<div id="layoutSidenav_content">
			<main>
			
			<%-- 선택 검색 창 --%>
			<div class="container-fluid px-4 mb-5">
				<h2 class="text-center mt-5 mb-5" style="font-family: Open Sans">관심지역 둘러보기</h2>
				<div class="container mt-4">
						
					<form id="form-deal">
						<div class="row col-md-12 justify-content-center mb-4">
							<div class="form-group col-md-3">
								<select class="form-select bg-secondary text-light btn-lg "
									name="aptName" id="aptName" style="width: 100%">
									<option value="">관심 지역 선택</option>
									<c:forEach items="${InterestList}" var="list">
							      		<option value=${list} class="dropDown"}>${list}</option>
							      	</c:forEach>
								</select>
							</div>
							<div class="form-group col-md-3">
								<button type="button" id="list-btn" class="btn btn-primary"
									style="width: 100%">매매 정보 확인</button>
							</div>
						</div>
					</form>
					
				</div>
			</div>
			
			<script>
		        document.querySelector("#list-btn").addEventListener("click", function () {
					let form = document.querySelector("#form-deal");
	                form.setAttribute("action", "${root}/selectInterest.do");
	                form.submit();
		        });
			</script>

        </main>
			<%@ include file="/include/footer.jsp"%>
		</div>
	</div>
</body>

</html>