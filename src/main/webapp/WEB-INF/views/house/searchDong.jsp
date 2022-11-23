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
			
			<%-- 선택 검색 창 --%>
			<div class="container-fluid px-4">
				<h2 class="text-center mt-5 mb-5" style="font-family: Open Sans">동별 매매 내역 검색</h2>
				<div class="container mt-4">
					<form id="form-deal">
						<div class="row col-md-12 justify-content-center mb-4">
							<div class="form-group col-md-3">
								<select class="form-select bg-secondary text-light btn-lg "
									name="sido" id="sido" style="width: 100%">
									<option value="">시도선택</option>
								</select>
							</div>
							<div class="form-group col-md-3">
								<select class="form-select bg-secondary text-light btn-lg"
									name="gugun" id="gugun" style="width: 100%">
									<option value="">구군선택</option>
								</select>
							</div>
							<div class="form-group col-md-3">
								<select class="form-select bg-secondary text-light btn-lg"
									name="dong" id="dong" style="width: 100%">
									<option value="">동선택</option>
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
			<%-- 선택 검색 창 --%>	


			<%-- 카카오 지도 --%>
			<div class="container row" style="float: none; margin: 0 auto;">
				<div id="map" style="width: 1100px; height: 600px;"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23658ec81df716d953dc7efcbbbfde05"></script>
				<script>
	          var mapContainer = document.getElementById('map'), 
	          mapOption = { 
	            center: new kakao.maps.LatLng(37.4999, 127.0374),
	            level: 3
	          };
	          var map = new kakao.maps.Map(mapContainer, mapOption); 
	          </script>
			</div>
			<%-- 카카오 지도 --%>
			
			<%-- 위치 받기 --%>
			<script>
			window.onload = function () {
	          sendRequest("sido", "*00000000");
	        };
	        
	        document.querySelector("#sido").addEventListener("change", function () {
	          if (this[this.selectedIndex].value) {
	            let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
	            sendRequest("gugun", regcode);
	          } else {
	            initOption("gugun");
	            initOption("dong");
	          }
	        });

	        document.querySelector("#gugun").addEventListener("change", function () {
	          if (this[this.selectedIndex].value) {
	            let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
	            sendRequest("dong", regcode);
	          } else {
	            initOption("dong");
	          }
	        });
	
	        function sendRequest(selid, regcode) {
	         	const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
	          	let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
	        	fetch(`${'${url}'}?${'${params}'}`)
	            .then((response) => response.json())
	            .then((data) => addOption(selid, data));
	        }
	
	        function addOption(selid, data) {
				let opt = ``;
				initOption(selid);
				switch (selid) {
	            case "sido":	            
					opt += `<option value="">시도선택</option>`;
					data.regcodes.forEach(function (regcode) {
						opt += `
							<option value=${"${regcode.code}"} name="sido">${'${regcode.name}'}</option>
							`;
							        });
	              break;
	            case "gugun":
	              opt += `<option value="">구군선택</option>`;
	              for (let i = 0; i < data.regcodes.length; i++) {
	                if (i != data.regcodes.length - 1) {
	                  if (
	                    data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
	                    data.regcodes[i].name.split(" ").length !=
	                    data.regcodes[i + 1].name.split(" ").length
	                  ) {
	                    data.regcodes.splice(i, 1);
	                    i--;
	                  }
	                }
	              }
	              let name = "";
	              data.regcodes.forEach(function (regcode) {
	                if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
	                else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
	                opt += `
	                  <option value=${"${regcode.code}"}>${'${name}'}</option>
	                  `;
	              });
	              break;
	            case "dong":
	              opt += `<option value="">동선택</option>`;
	              let idx = 2;
	              data.regcodes.forEach(function (regcode) {
	                if (regcode.name.split(" ").length != 3) idx = 3;
	                opt += `
	                  <option value=${"${regcode.code}"}>${'${regcode.name.split(" ")[idx]}'}</option>
	                  `;
	              });
	          }
	          document.querySelector(`#${'${selid}'}`).innerHTML = opt;
	        }
	
	        function initOption(selid) {
	          let options = document.querySelector(`#${'${selid}'}`);
	          options.length = 0;
	        }

	        document.querySelector("#list-btn").addEventListener("click", function () {
				let form = document.querySelector("#form-deal");
                form.setAttribute("action", "${root}/house/searchDong");
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
                form.submit();
	        });
        </script> 
        </main>
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>
</body>

</html>