<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<title>아파트 매매 내역 검색 : Where is My House?</title>
</head>

<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<div id="layoutSidenav_content">
			<main>
			
			<div class="container-fluid px-4">
				<h2 class="text-center mt-5 mb-5" style="font-family: Open Sans">아파트 매매 내역 검색</h2>
				<div class="container">
					<form id="form-deal-2" action="${root}/house/searchApt">
						<div class="row col-md-12 justify-content-center mb-2">
							<div class="form-group col-md-10">
								<input type="text" name="aptName" class="form-control" placeholder="아파트 이름을 입력하세요." >
							</div>
							<div class="form-group col-md-2">
								<button type="submit" id="list-btn-2" class="btn btn-primary" style="width: 100%">아파트별 조회</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<%-- 카카오 지도 --%>
			<div class="container row" style="float: none; margin:0 auto;">
	          <div id="map" style="width:1100px;height:600px;"></div>
	
	          <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23658ec81df716d953dc7efcbbbfde05"></script>
	          <script>
	          var mapContainer = document.getElementById('map'), 
	          mapOption = { 
	            center: new kakao.maps.LatLng(37.4999, 127.0374), 
	            level: 3 
	          };
	          var map = new kakao.maps.Map(mapContainer, mapOption); 
	          </script>
	        </div>
	
	
	
			<%-- 위치 받기 --%>
			<script>
	        function initOption(selid) {
	          let options = document.querySelector(`#${'${selid}'}`);
	          options.length = 0;
	        }

	        document.querySelector("#list-btn-2").addEventListener("click", function () {
	        	let form = document.querySelector("#form-deal-2");
                form.setAttribute("action", "${root}/house/searchApt");
                var input   = document.createElement('input');
                var input2   = document.createElement('input');

                input.type   = 'hidden';
                input.name  = 'pageNum';
                input.value  = 1;
                
                input2.type   = 'hidden';
                input2.name  = 'amount';
                input2.value  = 10;
                
                form.appendChild(input);
                form.appendChild(input2);
                
                console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                
                form.submit();
	        });
	        
        </script> </main>
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>
</body>

</html>