$(function () {

    }
);
function changeIframe(i) {
    var src="";
    if(i==1){
        src = 'webApp/system_setting/reference_manage/reference_manage.html';
    }else if(i==2){
        src='webApp/system_setting/special_manage/special_manage.html';
    }else if(i==3){
        src='webApp/system_setting/word_manage/word_manage.html';
    }else if(i==4){
        src='webApp/system_setting/knowledge_manage/knowledge_manage.html';
    }else if(i==5){
        src='webApp/system_setting/user_manage/user_manage.html';
    }
    $("#frame1").attr("src",src);
}