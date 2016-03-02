/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

function sendPolicy(){
	if(window.confirm('送信してもよろしいですか？')){
		ajaxSettings = {
			type:'post',
			url:'/TeraNaviAdmin/front/ruleedit',
			dataType:'json',
			data:null

		};
		ajaxSettings.data = {
			ajax:"true",
			rule:CKEDITOR.instances.editorrule.getData()
		};

		ajaxSettings.success = function(data){
			alert("保存完了しました");
		}

		ajax = $.ajax(ajaxSettings);
	}
	else{
		window.alert('キャンセルされました');
	}
}
