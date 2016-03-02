var ajaxSettings;
var ajax;

var nowId;

function loadVersion(){
    ajaxSettings = {
            type:'post',
            url:'/TeraNavi/front/TermsDisplay',
            dataType:'json',
            data:null
        };

        loadPolicy();
        $("#modal").modal("show");
}

function loadPolicy(){

    ajaxSettings.data = {
        ajax:"true",
        target:"policy"
    };

    ajaxSettings.success = function(data){

        var body = $("#textBody");
        var list = $("#list ul");

        body.empty();
        list.empty();

console.log(data.main.date)
        // body.append("<p>"+data.main.date+"</p>");
        $("#date").innerText=data.main.date;
        console.log($("#date"))
        // body.append("<p>"+data.body.body+"</p>");
        body.innerHTML=data.main.body;

        // list.append("<h1>過去のバージョン</h1>");
        for(var i = 0;i < data.list.length;i++){
            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadPolicyId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");

        }

        $("#list ul ol:first").css("border-style","groove");
    }

    ajax = $.ajax(ajaxSettings);

}

function loadPolicyId(id){


    nowId = id;
    ajaxSettings.data = {
        ajax:"true",
        target:"policy",
        id:id,
        where:" WHERE policy_id = ?"
    };

    ajaxSettings.success = function(data){

        var body = $("#textBody");
        var list = $("#list ul");

        body.empty();
        list.empty();


        $("#date").innerText=data.main.date;
        // body.append("<p>"+data.body.body+"</p>");
        body.innerHTML=data.main.body;

        // list.append("<h1>リスト</h1>");
        for(var i = 0;i < data.list.length;i++){

            var date = data.list[i].date.slice(0,10);
            list.append("<ol><a onclick='loadPolicyId(\""+data.list[i].id+"\")'>"+date+"</a></ol>");
            if(data.list[i].id==nowId){
                $("#list ul ol:last").css("border-style","groove");

            }
        }
    }

    ajax = $.ajax(ajaxSettings);
}
