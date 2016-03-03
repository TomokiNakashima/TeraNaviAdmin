function sendMail(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/mailSend',
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        address:"teranavi.info@gmail.com",
        toAddress:$("#contactReplayAddress").val(),
        pass:"#TeraInfomation",
        title:$("#contactReplayTitle").val(),
        mess:$("#contactReplayBody").val()
    };

    ajaxSettings.success = function(data){
        alert("メールを送信しました");
    }

    ajax = $.ajax(ajaxSettings);
}
