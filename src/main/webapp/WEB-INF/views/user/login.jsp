<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		<script>
			window.Kakao.init("b99103b95fda97377eadc2f9af649331");
			
			function kakaoLogin(){
				window.Kakao.Auth.login({
					scope:'profile_nickname,account_email,birthday',
					success: function(authObj){
						console.log(authObj);
						window.Kakao.API.request({
							url: '/v2/user/me',
							success: res => {
								const email = res.kakao_account.email;
								const name = res.properties.nickname;
								const birth = res.kakao_account.birthday;
								
								console.log(email);
								console.log(name);
								console.log(birth);
								
								$('#kaoemail').val(email);
								$('#kaoname').val(name);
								$('#kaobirth').val(birth);
								console.log(document.querySelector("#kaoemail").value);
								alert("로그인 성공");
								
								document.querySelector("#llogin").submit();
							}
						});
						
					}
				});
			}
			
			function kakaoLogout() {
			    if (Kakao.Auth.getAccessToken()) {
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
		
		<div class="container">
          <form method="post" id="llogin" name="login_frm" action="${root}/user/login">
            <div class="mb-3 mt-3">
              <label for="text" class="form-label">아이디:</label>
              <input type="text" class="form-control" id="id" name="id" placeholder="아이디" name="아이디">
            </div>
            <div class="mb-3">
              <label for="pass" class="form-label">비밀번호:</label>
              <input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" name="pswd">
            </div>
            <input type="hidden" name="kaoemail" id="kaoemail" />
				<input type="hidden" name="kaoname" id="kaoname" />
				<input type="hidden" name="kaobirth" id="kaobirth" />
            <div class="form-check mb-3">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember"> Remember me
              </label>
            </div>
            <button type="submit" class="btn btn-primary" id="login">로그인</button>
            <a class="btn btn-primary" href="${root}/user/password" role="button"> 비밀번호 찾기 </a>
            
            <div class="form-group row">
				<div class="or-seperator"><b>or</b></div>
			</div>
			<div class="form-group row" id="kakaologin">
				<div class="kakaobtn">
					<input type="hidden" name="kakaoemail" id="kakaoemail" />
					<input type="hidden" name="kakaoname" id="kakaoname" />
					<input type="hidden" name="kakaobirth" id="kakaobirth" />
					<a href="javascript:kakaoLogin();">
						<img src="${root}/img/login.png">
					</a>
	 				<!-- <a href="javascript:kakaoLogout();">
			          	<span>카카오 로그아웃</span>
			      	</a>  -->
				</div>
			</div>
          </form>
        </div>
		
		
		


<%@ include file="/WEB-INF/views/include/footer.jsp" %>