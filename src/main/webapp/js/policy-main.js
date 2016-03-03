// var ajaxSettings;
// var ajax;
//
// var nowId;

function loadPolicyVersion(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNavi/front/TermsDisplay',
        dataType:'json',
        data:null
    };

    loadPolicy();
    $("#modal").modal("show");
}

function loadPolicy(){

    ajaxSettings.data = {
        ajax:"true",
        target:"policy"
    };

    ajaxSettings.success = function(data){

        var textBody = $("#textBody");
        var list = $("#list ul");

        textBody.empty();
        list.empty();

        textBody.innerHTML=data.main.body;
        for(var i = 0;i < data.list.length;i++){
            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadPolicyId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");

        }

        $("#list ul ol:first").css("border-style","groove");
    }

    ajax = $.ajax(ajaxSettings);

}

function loadPolicyId(id){


    nowId = id;
    ajaxSettings.data = {
        ajax:"true",
        target:"policy",
        id:id,
        where:" WHERE policy_id = ?"
    };

    ajaxSettings.success = function(data){

        var textBody = document.getElementById("textBody");
        var list = $("#list ul");

        //textBody.empty();
        list.empty();


        textBody.innerHTML=data.main.body;
        for(var i = 0;i < data.list.length;i++){

            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadPolicyId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");
            if(data.list[i].id==nowId){
                $("#list ul ol:last").css("border-style","groove");

            }
        }
    }

    ajax = $.ajax(ajaxSettings);
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

function sendPolicy(){
	if(window.confirm('送信してもよろしいですか？')){
		ajaxSettings = {
			type:'post',
			url:'/TeraNaviAdmin/front/policyedit',
			dataType:'json',
			data:null

		};
		ajaxSettings.data = {
			ajax:"true",
			policy:CKEDITOR.instances.editorpolicy.getData()
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
