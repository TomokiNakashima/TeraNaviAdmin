
	loadUserList();


function loadUserList(){
	ajaxSettings = {
		type:'post',
		url:'/TeraNaviAdmin/front/showlock',
		dataType:'json',
		data:null

	};
	ajaxSettings.data = {
		ajax:"true",
		userStatus:3
	};

	ajaxSettings.success = function(data){
		var area=$("#showUserArea");
		area.empty();

		for(var i=0;i<data.length;i++){
			area.append("<div class='list-group rock'>"+
			" <div class='list-group-item'>"+
			" <div class='col-cm-2 pull-left'>"+
			" <img src='"+data[i]["iconPath"]+"'>"+
			" </div>"+
			" <div class='showUserArea_caption'>"+
			" <div class='pull-left'>"+
			" <div class='showUserArea_name'>"+data[i]["userName"]+"</div><br>"+
			" <div class='showUserArea_url'>"+
			" <a href='http://192.168.33.10/TeraNavi/front/mypage?paramUserId="+data[i]["id"]+"' target='_blank'>個人ページ</a>"+
			" </div>"+
			" </div>"+
			" <div class='center-block showUserArea_lastTime' id='lastTime"+data[i]["id"]+"'>終了時間："+data[i]["lockEndDate"]+"</div>"+
			" <div class='pull-right'>"+
			" <button type='button' class='btn btn-primaty showUserArea_button_release' onclick='lockEnd("+data[i]["id"]+")'>解除</button>"+
			" </div></div></div></div>");
		}
	}
	ajax = $.ajax(ajaxSettings);
}


function lockEnd(id){
	if(window.confirm('ロックを解除してもよろしいですか？')){
		ajaxSettings = {
			type:'post',
			url:'/TeraNaviAdmin/front/acDelete',
			dataType:'json',
			data:null

		};
		ajaxSettings.data = {
			ajax:"true",
			status:0,
			target:id
		};

		ajaxSettings.success = function(data){
			$("#lastTime"+id).text("ロックを解除しました");
		}
		ajax = $.ajax(ajaxSettings);
	}
}
