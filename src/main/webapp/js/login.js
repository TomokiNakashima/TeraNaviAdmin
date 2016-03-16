$(function(){
    $("#login").on("click",function(){
        loginCheck();
    });

});

$(document).keydown(function(e) {
    if(e.keyCode==13) {
        loginCheck();
    }
});

function loginCheck(){
    var id=$("#adminLoginId").val();
    var pass=$("#password").val();
    if(id==""||pass==""){
        if(id==""){
            $("#adminLoginId").attr("placeholder","アカウントは必須");
            $("#adminLoginId").css("border-color","#c00");
        }
        if(pass==""){
            $("#password").attr("placeholder","パスワードは必須");
            $("#password").css("border-color","#c00");
        }
		return ;
   }
    login(id,pass);
}

function login(id,pass){
	$("#login").css("display","none");
	$("#loging").css("display","blcok");

    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/login',
        dataType:'json',
        data:null
    };
    ajaxSettings.data = {
        ajax:"true",
        adminLoginId:id,
        password:pass
    };
    ajaxSettings.success = function(data){
        location.href="/TeraNaviAdmin/dashboard";
    };


    ajaxSettings.error = function (XMLHttpRequest, textStatus, errorThrown) {
        var report;
        if(XMLHttpRequest.status==500){
            if(XMLHttpRequest.responseText.indexOf("パスワード")>-1){
                report="IDまたはパスワードが違います";
            }else {
                report="管理者ではありません";
            }
        }
        $("#report").text(report);
		$("#login").css("display","block");
		$("#loging").css("display","none");
    },

    ajax = $.ajax(ajaxSettings);
}
