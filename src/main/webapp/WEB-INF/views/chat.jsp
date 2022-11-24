<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/chatt.css''>
</head>
<style>
@charset "UTF-8";
*{
	box-sizing: border-box;
}

#chatt{
	width: 800px;
	margin: 20px auto;
}

#chatt #talk{
	width: 800px;
	height: 400px;
	overflow: scroll;
	border : 1px solid #aaa;
}
#chatt #msg{
	width: 740px;
	height:100px;
	display: inline-block;
}

#chatt #sendZone > *{
	vertical-align: top;
	
}
#chatt #btnSend{
	width: 54px;
	height: 100px;
}

#chatt #talk div{
	width: 70%;
	display: inline-block;
	padding: 6px;
	border-radius:10px;
	
}

#chatt .me{
	background-color : #ffc;
	margin : 1px 0px 2px 30%;	
}

#chatt .other{
	background-color : #eee;
	margin : 2px;
}
</style>



<body>
	<div id='chatt'>
		<h1>집 구하기  푸념방</h1>
		<br/>
		<div id='talk'></div>
		<div id='sendZone'>
			<textarea id='msg' value='hi...' >이건 언디인가2</textarea>
			<input type='button' value='전송' id='btnSend'>
		</div>
	</div>
</body>



<script type="text/javascript">
function getId(id){
	return document.getElementById(id);
}

var data = {};//전송 데이터(JSON)
var mid = `${nickName}`
//var btnLogin = getId('btnLogin');
var btnSend = getId('btnSend');
var talk = getId('talk');
var msg = getId('msg');

var ws ;
ws = new WebSocket("ws://" + location.host + "/chat");
ws.onmessage = function(msg){
	console.log("이게 보내는거구나")
	var data = JSON.parse(msg.data);
	var css;
	
	if(data.mid == mid){
		css = 'class=me';
	}else{
		css = 'class=other';
	}
	
	console.log(data); // sysout.............................data
	console.log(css); // sysout.............................css
	console.log(data.mid); // sysout.............................data
	console.log(data.msg); // sysout.............................data
	console.log(data.date); // sysout.............................data
	
	var item = `<div ${css} >
        <span><b>${data.mid}</b></span> [ `${'${data.date}'}` ]<br/>
      <span>${data.msg}</span>
        </div>`;
		
	console.log(item); // sysout.............................data	
				
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
	console.log("이게 먼저인가")
	if(msg.value.trim() != ''){
		data.mid = mid
		console.log(data.mid);
		
		data.msg = msg.value;
		console.log(data.msg);
		
		data.date = new Date().toLocaleString();
		console.log(data.date);
		
		var temp = JSON.stringify(data);
		ws.send(temp);
	}
	msg.value ='';
}
</script>
</html>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>