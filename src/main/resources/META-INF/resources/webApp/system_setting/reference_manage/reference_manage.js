$(function () {


    /*  ajaxPost("user/getAllUser",{userName:"haah"}).done(function (data) {

     });*/

    metTable_initialization();

});
var IP="http://192.168.1.10:8085/tcm/";
function metTable_initialization(){
    $('#metTable1').bootstrapTable({
        method: 'POST',
        url: IP+"user/getAllUser",
        dataType: "JSON",
        /*contentType: "text/html; charset=utf-8",*/
        iconSize: "outline",
        //crossDomain: true,
        // clickToSelect: true,//点击选中行
        pagination: true,	//在表格底部显示分页工具栏
        pageSize: 3,	//页面大小
        /* pageNumber: 1,	//页数*/
        pageList: [2,3,5],
        striped: true,	 //使表格带有条纹
        sidePagination: "server",//表格分页的位置 client||server
        queryParams: queryParams, //参数
        queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求
        silent: true,  //刷新事件必须设置
        contentType: "application/x-www-form-urlencoded",//请求远程数据的内容类型。
        onClickRow: function (row, $element) {
            $('.success').removeClass('success');
            $($element).addClass('success');
        },
        icons: {
            refresh: "glyphicon-repeat",
            toggle: "glyphicon-list-alt",
            columns: "glyphicon-list"
        },
        columns: [{ // 列设置
            field: 'state',
            // 使用单选框

        },{
            field: 'id',
        },{
            field: 'login_name',
            title: '登录名',
            align: 'center',
            valign: 'middle',
            //  sortable: true // 开启排序功能
        },{
            field: 'user_name',
            title: '用户名',
            align: 'center',
            valign: 'middle',
        }, {
            field: 'user_type',
            title: '用户类型',
            align: 'center',
            valign: 'middle',
            formatter:function (value, row, index) {
                if(value==1){
                    return "管理员";
                }else if(value==2){
                    return "会员";
                }else if(value==3){
                    return "普通用户";
                }
            }
        },{
            field: 'tel',
            title: '电话',
            align: 'center',
            valign: 'middle',
        },{
            field: 'email',
            title: '邮箱',
            align: 'center',
            valign: 'middle',
        }, {
            field: 'create_time',
            title: '创建时间',
            align: 'center',
            valign: 'middle',
        },{
            field: 'permi_time',
            title: '权限时间',
            align: 'center',
            valign: 'middle',
        }, {
            field: 'operate',
            title: '操作',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return [
                    '<a  class="edit" title="edit" onclick="update('+row.id+')" style="cursor:pointer" >',
                    '修改',
                    '</a>  ',
                    '&nbsp;&nbsp;&nbsp;&nbsp;',
                    '<a class="remove"  onclick="remove('+row.id+')" title="Remove" style="cursor:pointer">',
                    '删除',
                    '</a>',
                ].join('');
            },
            events: window.operateEvents,
        }]
    });
}
//点击修改
function update(id) {
    $("#add_button").hide();
    $("#up_button").show();
    var row = $("#metTable1").bootstrapTable('getData');
    for(var i=0;i<row.length;i++){
        if(row[i].id==id){
            var tem=row[i];
            $("#id").val(tem.id);
            $("#loginName").val(tem.login_name);
            $("#userName").val(tem.user_name);
            $("#per").val(tem.user_type);
            $("#tel").val(tem.tel);
            $("#mail").val(tem.email);
            $("#loginName").attr("readOnly",true);
            $("#creat_modal").modal({backdrop: 'static', keyboard: false});
        }
    }
}
//确认修改
function xiugai() {
    var id= $("#id").val();
    var loginName = $("#loginName").val();
    var userName = $("#userName").val();
    var per=$().val("#per");
    var tem=$().val("#tel");
    var email=$().val("mail");
    if(userName==""){
        return toastr.warning("请填写用户姓名");
    }else if(per==""){
        return toastr.warning("请选择用户权限");
    }
    var data={"loginName":loginName,"userName":userName,"userId":id,"per":per,"tel":tel,"email":email};
    ajaxPost("user/updateUser",data).done(function (data) {
        if(data.status==0){
            $("#creat_modal").modal('hide');
            toastr.info("修改成功");
            chaxun();
        }else{
            toastr.warning(data.msg);
        }
    });
}
function remove(id){

    swal({
            title: "您确定要删除该账号吗？",
            text: "请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function() {
            var data={"userId":id};
            ajaxPost("user/deleteUser",data).done(function (data) {
                if(data.status==0){
                    $("#creat_modal").modal('hide');
                    toastr.info("删除");
                    chaxun();
                }else{
                    toastr.warning(data.msg);
                }
            });
        });



}
function createUser() {
    $("#title_zh").html('账号创建');
    $("#loginName").attr("readOnly",false);
    $("#add_button").show();
    $("#up_button").hide();
    $("#loginName").val("");
    $("#userName").val("");
    $("#per").val("");
    $("#tel").val("");
    $("#mail").val("");
    $("#creat_modal").modal({backdrop: 'static', keyboard: false});
}
function add() {

}
function shanchu() {

}
function daoru() {

}
function daochu() {

}
function save() {

}
function queryParams(params) {
    var datas = {};
    datas.pageSize = params.limit;
    datas.pageNumber = params.offset;
    datas.keyWord = $("#keyWord").val();
    /* datas.company = $("#compan").val();
     datas.userName =$("#qname").val();*/
    //var data = JSON.stringify(datas);
    return datas;
}
function ajaxPost(url, parameter) {
    var parameterPar={
        "token":"",
        "data":JSON.stringify(parameter)
    };
    // var p = JSON.stringify(parameterPar);
    return $.ajax(IP + url, {
        type: "POST",
        //async: true,
        dataType: 'Json',
        data:parameterPar
    })
}

function quxiaomtk(){
    $("#creat_modal").modal("hide");
    $("#userName").val("");
    $("#tel").val("");
    $('#mail').val("");
    $("#loginName").val("");
    $("#per").val("");
    $("#loginName-error").hide();
    $("#tel-error").hide();
    $("#mail-error").hide();
    $("#userName-error").hide();
    $("#per-error").hide();
}
function chuangjian(){
    var loginName=$("#loginName").val();
    var userName = $("#userName").val();
    var per = $('#per').val();
    var tel = $("#tel").val();
    var mail = $("#mail").val();
    if(loginName==""){
        return toastr.warning("请填写登录名");
    }else if(userName==""){
        return toastr.warning("请填写用户姓名");
    }else if(per=""){
        return toastr.warning("请选择用户权限");
    }
    var datas = {
        loginName:loginName,
        userName:userName,
        level:per,
        tel:tel,
        mail:mail
    };
    ajaxPost('user/createUser', datas).done(function (data, status) {
        if(data){
            if(data.status==0){
                $("#creat_modal").modal('hide');
                toastr.info("创建成功");
                chaxun();
            }else{
                toastr.warning(data.msg);
            }
        }else{
            toastr.warning("创建失败");
        }
    });
}
function chaxun(){
    $('#metTable1').bootstrapTable('destroy');//销毁表格数据
    metTable_initialization();
}