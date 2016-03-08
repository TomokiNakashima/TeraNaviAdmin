$(document).keydown(function(e) {
    if(e.keyCode==13) {
        $("#search_button").click();
    }
});

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
        if(data[0]==null){
            area.append("<img src='http://static.hdslb.com/mstation/images/video/notfound.png' style='margin-left:19%;'>");
            return;
        }else{
            for(var i=0;i<data.length;i++){
                var nameId="admin";
                var htmlData;

                htmlData="<div class='col-md-3'>"+
                "<div class='thumbnail'>"+
                "<span>";

                if(data[i]["adminFlag"]!=1){
                    nameId="nomal";
                    htmlData+="<button class='glyphicon glyphicon-plus pull-right' id='"+data[i]["id"]+"'></button>";
                }

                htmlData+="</span><img src='"+data[i]["iconPath"]+"' alt='Icon' class='img-rounded'>"+
                "<div class='caption "+nameId+"'>"+
                "<span>名前："+data[i]["userName"]+"</span>"+
                "<p>アドレス:"+data[i]["mailAddress"]+"</div></div></div></div>";
                area.append(htmlData);
            }
            eventAdd();
        }
    }

    ajax = $.ajax(ajaxSettings);
}

function eventAdd(){
    $("#printArea button").on("click",function(){
        authorrity(this);
    });
}


function authorrity(user){
    if(window.confirm('このユーザーに管理者権限を与えますか？')){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/acAuth',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            target:user.id
        };

        ajaxSettings.success = function(data){
            alert("権限を付けしました");
            $("#"+user.id).parent().next().next().addClass("admin");
            $("#"+user.id).remove();
        }

        ajax = $.ajax(ajaxSettings);
    }
    else{
        window.alert('キャンセルされました');
    }
}
