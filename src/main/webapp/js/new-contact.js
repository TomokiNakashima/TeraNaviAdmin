window.onload= function(){
    newContact();
    newReport();
}

function newContact(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/contsupplist',
        dataType:'json',
        data:null,
        success:function(data){
            var count=0;
            for(var i=0;i<data.length;i++){
                if(data[i]["readFlag"]=='0'){
                    count++;
                }
            }
            $("#new_contact").text(count+" 通");
        }
    }
    ajaxSettings.data = {
        ajax:"true",
    }

    ajax = $.ajax(ajaxSettings);
}


function newReport(){
    ajaxSettings = {
        type:'post',
        url:'/TeraNaviAdmin/front/showcaution',
        dataType:'json',
        data:null,
        success:function(data){
            var lastTime=$("#lastTime").text();
            var count=0;
            for(var i=0;i<data.length;i++){
                if(lastTime<data[i]["date"]){
                    count++;
                }
            }
            $("#new_report").text(count+" 通");
            console.log(count);
        }
    }
    ajaxSettings.data = {
        ajax:"true",
    }

    ajax = $.ajax(ajaxSettings);
}
