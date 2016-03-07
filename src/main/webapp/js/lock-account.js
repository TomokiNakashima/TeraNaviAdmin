function lockAccount(id){
    var statusNo;
    var selectId = $("#lock_select"+id);
    var date = selectId.val();
    if(date==0){
        return false;
    }
    if(date==24||date==48){
        statusNo=1;
    }else{
        statusNo=2;
    }
    if(window.confirm('このアカウントをロックしますか？')){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/acLock',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            target:id,
            lockEnd:date,
            status:statusNo
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


function deleteUser(id){

    if(window.confirm('このユーザー削除しますか？')){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/acDelete',
            dataType:'json',
            data:null

        };
        ajaxSettings.data = {
            ajax:"true",
            target:id,
            status:3
        };

        ajaxSettings.success = function(data){
            document.getElementById("delete_account").outerHTML="<p>アカウントを削除しました</p>";
        }
        ajax = $.ajax(ajaxSettings);
    }
    else{
        window.alert('キャンセルされました');
    }
}
