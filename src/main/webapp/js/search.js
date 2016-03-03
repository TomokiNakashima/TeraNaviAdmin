function search(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/usearch',
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        keyword:$("#inputUserName").val()
    };

    ajaxSettings.success = function(data){
        var area = $("#printArea");
        area.empty();
        for(var i=0;i<data.length;i++){
            area.append("<div class='col-md-3'><div class='thumbnail'><span><button class='glyphicon glyphicon-plus pull-right' id='"+data[i]["id"]+"' style='color:#F37518'></button></span><img src='"+data[i]["iconPath"]+"' alt='Icon' class='img-rounded' style='height:50px;width50px;'><div class='caption'><span>名前："+data[i]["userName"]+"</span><p style='font-size:90%''>アドレス:"+data[i]["mailAddress"]+"</div></p></div></div></div>");
        }
        eventAdd();
    }

    ajax = $.ajax(ajaxSettings);
}

function eventAdd(){
    $("#printArea button").on("click",function(){
        authorrity(this);
    })
}


function authorrity(obj){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/acAuth',
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        target:obj.id
    };

    ajaxSettings.success = function(data){
        alert("権限を付けしました");
    }

    ajax = $.ajax(ajaxSettings);
}
