$(document).keydown(function(e) {
    if(e.keyCode==13) {
            $("#search_button").click();
    }
});

function keyResult(){

    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/writeKeyCreate',
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        count:$("#create_count").val()
    };

    ajaxSettings.success = function(data){
        var p=$("#create_table");
        p.empty();

        p.append("<caption><h3>発行された登録キー一覧</h3></caption><tr><th>番号</th><th>キー</th><th>status</th></tr>");
        for(var i=0;i<data["keys"].length;i++){
            p.append("<tr><td>"+i+"</td><td>"+data["keys"][i]+"</td><td>未使用</td></tr>")
        }
        fileDownload(data);
        alert("完了しました");
    }
    ajax = $.ajax(ajaxSettings);
}


function fileDownload(data){
    $("#file").val(data["fileName"]);
}
