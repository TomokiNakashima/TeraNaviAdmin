var outerData;

window.onload= function(){
    showCaution();
}

function showCaution(){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/showcaution',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            flag:"true"
        };

        ajaxSettings.success = function(data){
            outerData=data;
            var htmlData,buttonData,excuteFlag;
            var area=$("#showUserArea_report");

            area.empty();
            for(var i=0;i<data.length;i++){
                if(data[i]["cautionUserId"]["userStatus"]==3){
                    buttonData="<p class='Forced_withdrawal'>アカウントを削除しました</p>";
                }else{
                    buttonData="<button type='button' id='delete_account"+data[i]["id"]+"' class='btn btn-primaty Forced_withdrawal'"+
                                "onclick='deleteUser("+data[i]["cautionUser"]+","+data[i]["id"]+")'>強制退会</button>";
                }
				if(data[i]["cautionFlag"]==0){
                    excuteFlag="<option value='0'>新着</option>";
                }
				htmlData="<div class='col-md-10 list'>"+
                "<div class='list-group'>"+
                "<div class='list-group-item list_cautionUser'>"+
                "<div class='col-cm-2 pull-left'>"+
                "<img src='"+data[i]["cautionUserId"]["iconPath"]+"'>"+
                "</div>"+
                "<div class='showUserArea_caption'>"+
                "<div class='pull-left'>"+
                "<div class='caution_user_name'>"+data[i]["cautionUserId"]["userName"]+"</div><br>"+
                "<div class='caution_page_url'>"+
                "URL:<a href='"+data[i]["reportPageUrl"]+"' target='_blank'>通報されたページ</a>"+
                "</div>"+
                "</div>"+
                "<div class='center-block operate_area'>"+
                "<button type='button' class='btn btn-warning caution_button' onclick='caution("+data[i]["id"]+")'>通報内容</button>"+
                "<select class='form-control lock_select' id='lock_select"+data[i]["cautionUser"]+"' onchange='lockAccount("+data[i]["cautionUser"]+")'>"+
                excuteFlag+
				"<option value='0'>ロック</option>"+
                "<option value='24'>24時間</option>"+
                "<option value='48'>48時間</option>"+
                "<option value='9999999'>無期限</option>"+
                "</select>"+
                "</div>"+
                "<div class='pull-right'>"+buttonData+
                "</div>"+
                "</div>"+
                "</div>"+
                "</div>"+
                "<label>通報者:</label>"+
                "<div class='col-md-10 list-2 pull-right'>"+
                "<div class='list-group list2'>"+
                "<div class='list-group-item list_user'>"+
                "<div class='col-cm-2 pull-left'>"+
                "<img src='"+data[i]["userId"]["iconPath"]+"'>"+
                "</div>"+
                "<div class='showUserArea_caption'>"+
                "<div class='pull-left'>"+
                "<div class='user_name'>"+data[i]["userId"]["userName"]+"</div><br>"+
                "<div class='caution_date'>"+
                "通報時間:"+data[i]["date"]+
                "</div></div></div></div></div></div></div>"
                area.append(htmlData);
                console.log("完了");
            }
        }
        ajax = $.ajax(ajaxSettings);
}


function caution(id){
    var name=outerData[id-1]["userId"]["userName"];
    var title=outerData[id-1]["title"];
    var cautionBody=outerData[id-1]["cautionBody"];
    var cautionUserName=outerData[id-1]["cautionUserId"]["userName"];

    $("#cautionTitle").val(title);
    $("#cautionUser").val(cautionUserName);
    $("#user").val(name);
    $("#cautionBody").val(cautionBody);

    $("#cautionBtn").attr("onclick","sendCaution("+outerData[id-1]["cautionUser"]+")");
    $("#caution-modal").modal("show");
    console.log("OK")
}

function sendCaution(id){
    if(window.confirm('警告しますか？')){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/caution',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            toUserId:outerData[id-1]["cautionUser"]
        };

        ajaxSettings.success = function(data){
            alert("OK")
        }
        ajax = $.ajax(ajaxSettings);
    }
    else{
        window.alert('キャンセルされました');
    }
}
