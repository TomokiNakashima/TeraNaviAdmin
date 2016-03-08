$(function(){
    $("button").on("click",function(){
        login();
    });
});

function login(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/login',
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        loginId:$("#loginId").val(),
        password:$("#password").val()
    };

    ajaxSettings.success = function(data){
    Location.href="/TeraNaviAdmin/dashboard";
    }

    ajaxSettings.error = function (XMLHttpRequest, textStatus, errorThrown) {
        var report;
        if(XMLHttpRequest.status==500){
            if(XMLHttpRequest.responseText.indexOf("パスワード")>-1){
                report="IDまたはパスワードが違います";
            }else {
                report="管理者ではありません"
            }
        }
        $("#report").text(report);
    },

    ajax = $.ajax(ajaxSettings);
}
