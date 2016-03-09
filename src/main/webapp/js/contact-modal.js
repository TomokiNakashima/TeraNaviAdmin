var outerData;
var contact="contact",reply="Reply";
function read(id){

        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/showcon',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            conId:id,
        };

        ajaxSettings.success = function(data){
            outerData=data;
            $("#contactId_"+id).css("font-weight",100);
            formWrite(true);
        }
        ajax = $.ajax(ajaxSettings);
}

function formWrite(flag){
    var p;
    if(flag){
        p=contact;
        $("#mail-modal").modal("show");
    }else{
        p=contact+reply;
        $("#mail-modal").modal("hide");
        $("#contact-modal").modal("show");
    }

    $("#"+p+"Name").val(outerData["userName"]);
    if(flag){
        $("#"+p+"Title").val(outerData["title"]);
    }else {
        $("#"+p+"Title").val("Re:"+outerData["title"]);
    }
    $("#"+p+"Address").val(outerData["address"]);
    $("#"+p+"Category").val(outerData["category"]);
    $("#"+p+"Body").val(outerData["contactBody"]);
}
