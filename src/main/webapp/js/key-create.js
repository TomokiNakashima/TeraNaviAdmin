$(document).keydown(function(e) {
    if(e.keyCode==13) {
         $("#search_button").click();
    }
});

function keyResult(){

    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/keyCreate',
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
        for(var i=0;i<data.length;i++){
            p.append("<tr><td>"+i+"</td><td>"+data[i].signUpKey+"</td><td>"+data[i].keyValidDate+"</td></tr>");
        }
        
        
    };
    ajax = $.ajax(ajaxSettings);
}

ajaxSettings2 = {
        type:'post',
        url:'/TeraNaviAdmin/front/ShowKey',
        dataType:'json',
        data:null

    };
    
	ajaxSettings2.data = {
        ajax:"true"
    };

    ajaxSettings2.success = function(data){
        var p=$("#create_table");
        p.empty();

        p.append("<caption><h3>発行された登録キー一覧</h3></caption><tr><th>番号</th><th>キー</th><th>status</th></tr>");
        for(var i=0;i<data.length;i++){
            p.append("<tr><td>"+i+"</td><td>"+data[i].signUpKey+"</td><td>"+data[i].keyValidDate+"</td></tr>");
        }
        
        
    };
    ajax2 = $.ajax(ajaxSettings2);
