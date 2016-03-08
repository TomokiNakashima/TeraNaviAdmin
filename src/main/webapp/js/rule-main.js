var ajaxSettings;
var ajax;

var nowId;

function loadRuleVersion(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNavi/front/TermsDisplay',
        dataType:'json',
        data:null
    };

    loadRule();
    $("#modal").modal("show");
}

function loadRule(){

    ajaxSettings.data = {
        ajax:"true",
        target:"rule"
    };

    ajaxSettings.success = function(data){

        var textBody = $("#textBody");
        var list = $("#list ul");

        textBody.empty();
        list.empty();

        textBody.innerHTML=data.main.body;
        for(var i = 0;i < data.list.length;i++){
            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadRuleId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");

        }

        $("#list ul ol:first").css("border-style","groove");
    }

    ajax = $.ajax(ajaxSettings);

}

function loadRuleId(id){


    nowId = id;
    ajaxSettings.data = {
        ajax:"true",
        target:"rule",
        id:id,
        where:" WHERE rule_id = ?"
    };

    ajaxSettings.success = function(data){

        var textBody = document.getElementById("textBody");
        var list = $("#list ul");

        //textBody.empty();
        list.empty();


        textBody.innerHTML=data.main.body;
        for(var i = 0;i < data.list.length;i++){

            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadRuleId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");
            if(data.list[i].id==nowId){
                $("#list ul ol:last").css("border-style","groove");

            }
        }
    }

    ajax = $.ajax(ajaxSettings);
}

function sendRule(){
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
