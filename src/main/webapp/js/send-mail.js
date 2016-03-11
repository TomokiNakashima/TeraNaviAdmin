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
        toAddress:$("#contactReplyAddress").val(),
        pass:"#TeraInfomation",
        title:$("#contactReplyTitle").val(),
        mess:$("#contactReplyBody").val()
    };

    ajaxSettings.success = function(data){
        alert("メールを送信しました");
    }

    ajax = $.ajax(ajaxSettings);
}
