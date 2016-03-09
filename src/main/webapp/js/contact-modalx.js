var data={'id':'id',
'Name':'name',
'Category':'category',
'Title':'title',
'Date':'date',
'Body':'body',
'Address':'address'};
var targetName=['id','Name','Category','Title','Date','Body','Address'];
var contact="contact",replay="Replay";
function read(id){
    var tr=$("#contactId_"+id);
    var l=tr.children().length;
    for(i=0;i<l-1;i++){
        data[targetName[i]]=tr.children().eq(i).text();
    }
    formWrite(true);
}

function formWrite(flag){
    var p;
    if(flag){
        p=contact;
        $("#mail-modal").modal("show");
    }else{
        p=contact+replay;
        $("#mail-modal").modal("hide");
        $("#contact-modal").modal("show");
    }

    for(var i=0;i<7;i++){
        var str;
        if((i==5||i==3)&&(!flag)){
            str="Re:"+data[targetName[i]];
        }else {
            str=data[targetName[i]];
        }
        $("#"+p+targetName[i]).val(str);
    }
}
