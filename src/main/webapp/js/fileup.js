var ajaxSettings;
var ajax;
$(function(){

	ajaxSettings = {
		type:'post',
		url:'upload',
		processData:false,
		contentType:false,
		cache:false,
		dataType:'json',
		success:function(data){
			console.log("success");
			var text = $("#tbody").val();
			$("#tbody").val(text+"<br>"+data.result);
		}
	}

});

function onDrop(event){
	var files = event.dataTransfer.files;

	console.log("オンドロップ");

	for(var i = 0;i < files.length;i++){
		console.log("for");
		var f = files[i];
		var formData = new FormData();
		formData.append("file",f);
		ajaxSettings.data = formData;
		ajax = $.ajax(ajaxSettings);
	}

	event.preventDefault();
}

function onDragOver(event){
	event.preventDefault();
}


function upLoad(){
	var file=$("#file-3").val();
	if(window.confirm('送信しますか？')){
		ajaxSettings = {
			type:'post',
			url:'/TeraNaviAdmin/UserCsv',
			dataType:'json',
			data:null

		};
		ajaxSettings.data = {
			ajax:"true",
			filename:file
		};

		ajaxSettings.success = function(data){
			alert("OK")
		}
		ajax = $.ajax(ajaxSettings);
	}
	else{
		window.alert('キャンセルされました');
	}
}
