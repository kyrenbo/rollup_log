var websocket = null;
if(!'WebSocket' in window){
	alert("您的浏览器不支持websocket");
	
}

window.onbeforeunload = function(){
	clos();
}
function setMessageInHtml(message){
	$('.log').append(message);
	// 换行
	$('.log').append('<br>')
}

function clos(){
	websocket.close(3000,"强制关闭");
}
/** 加载页面列表 */
const host = "http://localhost:8091";
var fileList = null;
$(document).ready(function(){
	$.ajax({
		url: host + "/log/list",
		type:"get",
		data:null,
		dataType: 'json',
		success:function(data){
			// console.log(data);
			listFile(data.data);
			fileList = data.data;
		},
		error:function(error){
			
		},
		timeout:5000,
		async:false,
		global:true//生命周期（钩子函数）只有当global为true的时候才能出触发
	})
});

function listFile(list) {
	if(list) {
		list.forEach(function(item,index) {
			console.log(item);
			// 将
			var link = '<a href="javascript:;" onClick="printLog('+ item.id +')"> '+ item.title +'</a>'
			var fileItem = '<div class="fileItem">' + link +'</div>'
			$(".list").html(fileItem);
		})
	}
}

function init(item) {
	websocket = new WebSocket("ws://localhost:8091/showLog/" + item.id);
	websocket.onerror = function(){
		setMessageInHtml("send error！");
	}
	websocket.onopen = function(){
		setMessageInHtml("connection success！")
	}
	websocket.onmessage  = function(event){
		setMessageInHtml(event.data + "\n");
	}
	websocket.onclose = function(){
		setMessageInHtml("closed websocket!")
	}
}

function printLog(id) {
	// console.log('item', item);
	for(var i=0; i<fileList.length; i++) {
		var item = fileList[0];
		if(item.id === id) {
			// 建立websocket连接
			init(item);
			return ;
		}
	}
}