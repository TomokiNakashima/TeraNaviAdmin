$(document).keydown(function(e) {
    if(e.keyCode==13) {
            $("#search_button").click();
    }
});

function keyResult(url){
    var target,p,name;
    if(url=="keyCreate"){
        target=$("#create_count").val();
        p=$("#create_table");
        name="生成"
    }else if(url="writeKeyCreate") {
        target=$("#write_count").val();
        p=$("#write_table");
        name="発行"
    }else {
        return false;
    }

    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/'+url,
        dataType:'json',
        data:null

    };
    ajaxSettings.data = {
        ajax:"true",
        count:target
    };

    ajaxSettings.success = function(data){
        p.empty();

        p.append("<caption><h3>"+name+"された登録キー一覧</h3></caption><tr><th>番号</th><th>キー</th><th>status</th></tr>");
        for(var i=0;i<data.length;i++){
            p.append("<tr><td>"+i+"</td><td>"+data[i]["signUpKey"]+"</td><td>"+data[i]["keyStatus"]+"</td></tr>")
        }
        alert(name+"完了しました");
    }

    ajax = $.ajax(ajaxSettings);
}
