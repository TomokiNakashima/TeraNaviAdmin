$(function(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/contsupplist',
        dataType:'json',
        data:null,
        success:function(data){
            var lastTime=$("#lastTime").text();
            var count=0;

            console.log(lastTime)
            for(var i=0;i<data.length;i++){
                if(lastTime<data[i]["date"]){
                    count++;
                }
                console.log(i+":"+data[i]["userName"])
            }
            $("#new_contact").text(count+" 通");
        }
    }
    ajaxSettings.data = {
        ajax:"true",
    };

    ajax = $.ajax(ajaxSettings);
});
