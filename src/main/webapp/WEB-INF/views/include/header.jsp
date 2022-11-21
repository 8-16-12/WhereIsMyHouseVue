<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<%@ include file="/WEB-INF/views/include/front.jsp" %>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	<c:if test="${not empty msg_login}">
		alert("${msg_login}");
	</c:if>
	<c:if test="${not empty msg_update}">
		alert("${msg_update}");
	</c:if>
	
	
	window.Kakao.init("b99103b95fda97377eadc2f9af649331");
	
	function kakaoLogout() {
		alert("ddddddd");
	    if (Kakao.Auth.getAccessToken()) {
	    	alert("카카오로그아웃");
	      Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      Kakao.Auth.setAccessToken(undefined)
	    }
	  }  
</script>

</head>
<body>
<!-- header logo-->
<div class="header-logo py-5 d-none d-lg-block">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-6 text-center">
        <!-- <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="" class="img-fluid w-100"></a> -->
				<h1>
          <a href="${root}/index" class="nav-link">Where Is My House?</a>
        </h1>
      </div>
    </div>
  </div>
</div>

<header class="header-top bg-grey justify-content-center">
  <nav class="navbar navbar-expand-lg navigation">
      <div class="container">
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="ti-menu"></span>
          </button>
              <div class="collapse navbar-collapse" id="navbarContent">
                  <ul id="menu" class="menu navbar-nav ">
                      <li class="nav-item dropdown  pl-0">
                          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          		우리를 위한 집 구하기
                          </a>
                          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${root}/house/searchForm">전체 조회</a>
                            <a class="dropdown-item" href="${root}/house/searchDongForm">동별 조회</a>
                            <a class="dropdown-item" href="${root}/house/searchAptForm">아파트별 조회</a>
                          </div>
                      </li>
                      <li class="nav-item dropdown">
                          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          		지혜롭게 내 집 마련하기
                          </a>
                          <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
						  <a class="dropdown-item" href="https://ohou.se/store?utm_source=brand_google&utm_medium=cpc&utm_campaign=commerce&utm_content=e&utm_term=%EC%98%A4%EB%8A%98%EC%9D%98%EC%A7%91&source=14&affect_type=UtmUrl&gclid=Cj0KCQjw39uYBhCLARIsAD_SzMQfyGc3HeEd40IOdpO5XnQxaKqE5TekcmCe7T4ei2WuGIPBPAvwIjwaArycEALw_wcB"> 인테리어 공사 (오늘의 집)</a>
                          </div>
                      </li>

                      <li class="nav-item"><a href="https://land.naver.com/news/" class="nav-link">부동산 뉴스 (네이버)</a></li>
                      <li class="nav-item"><a href="${root}/user/notice" class="nav-link">공지사항</a></li>
                      <li class="nav-item"><a href="${root}/qna" class="nav-link">qna</a></li>
				  </ul>
                  <c:choose>
					<c:when test="${empty userinfo}">
					<ul class="row justify-content-center">
						<a class="btn btn-primary mx-1" href="${root}/user/login" role="button"> 로그인 </a>
						<a class="btn btn-primary" href="${root}/user/insert" role="button"> 회원가입 </a>
					</ul>
					</c:when>
					<c:otherwise>
					<ul id="menu" class="menu navbar-nav ">
                  		<!-- <li class="nav-item"><a href="${root}/nav/notice.jsp" class="nav-link">내 관심사</a></li> -->
                  		<li class="nav-item dropdown  pl-0">
                          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                         	 내 관심사
                          </a>
                          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${root}/interest/dropdownInterest?id=${userinfo.id}">관심지역 둘러보기</a>
                          </div>
                      </li>
                  	</ul>
                  	<ul class="row justify-content-center">
						<a class="btn btn-primary mx-1" href="${root}/user/search?id=${userinfo.id}" role="button"> 내 정보 확인 </a>
						<a class="btn btn-primary" href="${root}/user/logout" onclick="kakaoLogout();" role="button"> 로그아웃 </a>
					</ul>
					
					</c:otherwise>
				  </c:choose>
			</div>
      </div>
  </nav>
</header>
<!-- search overlay start -->
<div class="search-wrap">
    <div class="overlay">
        <form action="#" class="search-form">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-9">
                        <input type="text" class="form-control" placeholder="Search..."/>
                    </div>
                    <div class="col-md-2 col-3 text-right">
                        <div class="search_toggle toggle-wrap d-inline-block">
                            <i class="ti-close"></i>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- search overlay end -->
  
<%@ include file="/WEB-INF/views/include/back.jsp" %>
</body>
</html>