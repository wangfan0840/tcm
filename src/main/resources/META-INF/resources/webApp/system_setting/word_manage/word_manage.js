var IP="http://192.168.1.10:8085/tcm/";

$(function () {
    getWordType();



});
var wtMapping={};
function getWordType() {
    $.get(IP+"knowledge/getAllTheme").done(function (data) {
        if(data.status==0){
            var types=data.data;
            console.log(types)
            var html=""
            for(var i=0;i<types.length;i++){
                html+="<option value="+types[i].themeShort+">"+types[i].themeName+"</option>";
                wtMapping[types[i].themeShort]=types[i].themeName;
            }
            $("#wordType").html(html);
            metTable_initialization();
        }else{
            toastr.warning(data.msg);
        }
    });
}

function metTable_initialization(){
    $('#metTable1').bootstrapTable({
        method: 'POST',
        url: IP+"word/selectWord",
        dataType: "JSON",
        /*contentType: "text/html; charset=utf-8",*/
        iconSize: "outline",
        //crossDomain: true,
        // clickToSelect: true,//点击选中行
        pagination: true,	//在表格底部显示分页工具栏
        pageSize: 15,	//页面大小
        /* pageNumber: 1,	//页数*/
        pageList: [10,15,20,50],
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
            field: 'word',
            title: '主题词',
            align: 'center',
            valign: 'middle',
            //  sortable: true // 开启排序功能
        },{
            field: 'alias',
            title: '别名',
            align: 'center',
            valign: 'middle',
            //  sortable: true // 开启排序功能
        },{
            field: 'theme_type',
            title: '类型',
            align: 'center',
            valign: 'middle',
            formatter:function (value,row,index) {
                console.log(value);
                console.log(wtMapping[value])
                return wtMapping[value];
            }
            //  sortable: true // 开启排序功能
        },{
            field: 'operate',
            title: '操作',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return [
                    '<a  class="edit" title="edit" onclick="update('+row.id+')" style="cursor:pointer" >',
                    '修改',
                    '</a>  '/*,
                     '&nbsp;&nbsp;&nbsp;&nbsp;',
                     '<a class="remove"  onclick="remove('+row.id+')" title="Remove" style="cursor:pointer">',
                     '删除',
                     '</a>'*/
                ].join('');
            },
            events: window.operateEvents,
        }]
    });
}
//点击修改
function update(id) {
    $("#title_zh").html("修改主题词");
    $("#add_button").hide();
    $("#up_button").show();
    var row = $("#metTable1").bootstrapTable('getData');
    for(var i=0;i<row.length;i++){
        if(row[i].id==id){
            var tem=row[i];
            $("#id").val(tem.id);
            $("#word").val(tem.word);
            $("#alias").val(tem.alias);
            $("#wordType").val(tem.theme_type);
            $("#creat_modal").modal({backdrop: 'static', keyboard: false});
        }
    }
}
//确认修改
function xiugai() {
    var id= $("#id").val();
    var word = $("#word").val();
    var alias = $("#alias").val();
    var wordType=$("#wordType").val();
    if(word==""){
        return toastr.warning("请填写主题词");
    }else if(wordType==""){
        return toastr.warning("请选择词语类型");
    }
    var data={"id":id,"word":word,"alias":alias,"wordType":wordType};
    ajaxPost("word/updateWord",data).done(function (data) {
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
    var rows = $("#metTable1").bootstrapTable('getSelections');
    if(rows.length<1){
        return toastr.warning("请选择要删除的主题词");
    }
    swal({
            title: "您确定要删除这些主题词吗？",
            text: "请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function() {
            var ids=[];
            for(var i=0;i<rows.length;i++){
                ids.push(rows[i].id)
            }
            var data={"wordIds":ids};
            ajaxPost("word/deleteWord",data).done(function (data) {
                if(data.status==0){
                    swal("删除成功！", "您已经删除了这些主题词", "success");
                    chaxun();
                }else{
                    swal("删除失败", data.msg, "error");
                }
            });
        });
}
function createUser() {
    $("#title_zh").html('创建主题词');
   // $("#loginName").attr("readOnly",false);
    $("#add_button").show();
    $("#up_button").hide();
    $("#word").val("");
    $("#alias").val("");
    $("#wordType").val("");
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
    $("#word").val("");
    $("#alias").val("");
    $('#wordType').val("");
}
function chuangjian(){
    var word=$("#word").val();
    var alias = $("#alias").val();
    var wordType = $('#wordType').val();
    if(word==""){
        return toastr.warning("请填写主题词");
    }else if(wordType==""){
        return toastr.warning("请选择词语类型");
    }
    var datas = {
        word:word,
        alias:alias,
        wordType:wordType,
    };
    ajaxPost('word/recordWord', datas).done(function (data, status) {
        if(data){
            if(data.status==0){
                $("#creat_modal").modal('hide');
                toastr.info("添加成功");
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