<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/chatt.css''>
</head>
<style>
@charset "UTF-8";

* {
	box-sizing: border-box;
}

#chatt {
	width: 800px;
	margin: 20px auto;
	
}

#chatt #talk {
	width: 800px;
	height: 400px;
	/* overflow: scroll; */
/* 	border: 1px solid #aaa; */
	border-radius: 50px;
	display: inline-block;
}

#chatt #talk::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

#chatt #msg {
	width: 740px;
	height: 45px;
	display: inline-block;
}

#chatt #sendZone>* {
	vertical-align: top;
}

#chatt #btnSend {
	width: 100px;
	height: 45px;
}

#chatt #talk div {
	width: 70%;
	display: inline-block;
	padding: 6px;
	border-radius: 10px;
	/* background-image: url("http://poiemaweb.com/img/bg/paper.gif"); */
    /* background-repeat: repeat; */
}

#chatt .me {
	background-color: #ffc;
	margin: 1px 0px 2px 30%;
}

#chatt .other {
	background-color: #eee;
	margin: 2px;
}
</style>


<body>
	<div class="container-fluid px-4">
		<h2 class="text-center mt-5 mb-5" style="font-family: Open Sans">
			집 구하기 푸념방
		</h2>
		
		<div class="container">
			
			<div id='chatt' >
				<div class="row col-md-12 justify-content-center mb-2">
					<div id='talk' ></div>
				</div>
				
				<div class="form-group col-md-6">
				</div>
				
				<div id='sendZone' class="col-md-14">
					<textarea id='msg' value='hi...' placeholder="여기에 작성하세요" class="col-md-10"></textarea>
					<input type='button' value='전송' id='btnSend' class="btn btn-primary">
				</div>
			</div>
		</div>
	</div>
</body>

<script>
function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)
var mid = `${nickName}`
var btnSend = getId('btnSend');
var talk = getId('talk');
var msg = getId('msg');

var ws ;
ws = new WebSocket("ws://" + location.host + "/chat");
ws.onmessage = function(msg){
	var data = JSON.parse(msg.data);
	var css;
	
	if(data.mid == mid){
		css = 'class=me';
	}else{
		css = 'class=other';
	}
	
	var item = `<div \${css} >
        <span><b>\${data.mid}</b></span> [ \${data.date} ]<br/>
      <span>\${data.msg}</span>
        </div>`;
				
	talk.innerHTML += item;
	talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
}

msg.onkeyup = function(ev){
	if(ev.keyCode == 13){
		send();
	}
}

btnSend.onclick = function(){
	send();
}

function send(){
	if(msg.value.trim() != ''){
		data.mid = mid
		data.msg = msg.value;
		data.date = new Date().toLocaleString();
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value ='';
}
</script>
</html>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>