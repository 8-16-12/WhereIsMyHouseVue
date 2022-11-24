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
        <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
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