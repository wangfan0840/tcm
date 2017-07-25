/**
 * Created by wangfan on 2017/7/23.
 */

// function dynamicContent(rownum, colnum, content) {
//
//     alert(content.length);
//
//     var pageContent = rownum * colnum;
//     alert(pageContent);
//
//
// }
// function gotopage(value) {
//     try {
//         value == "-1" ? showPage(pgindex - 1) : showPage(pgindex + 1);
//     } catch (e) {
//
//     }
// }
// function showPage(pageINdex) {
//
//     obj.scrollTop = (pageINdex - 1) * parseInt(obj.offsetHeight);                                                                  //根据高度，输出指定的页
//     pgindex = pageINdex;
// }
// var obj = document.getElementById("frameContent");  //获取内容层
// var pages = document.getElementById("pages");         //获取翻页层
// var pgindex = 1;

var current = 1;
var bookId = 1;
var pagenum;

function gotopage(value) {

    if (value == "-1") {
        if (current == 1) {
            return false;
        } else {
            showPage(current - 1);
        }
    } else {
        if (current == pagenum) {
            return false;
        } else {
            showPage(current + 1);
        }
    }

}


function showPage(pageNO, sectionId) {
    var data = {
        bookId: bookId,
        number: pageNO
    };
    if (sectionId) {
        data = {
            bookId: bookId,
            number: pageNO,
            sectionId: sectionId

        }
    }

    ajaxGet("/api/reader/getContentByNumber", data).success(function (res) {
        $('#frameContent').html(res);
        current = pageNO;

    }).error(function (res) {
        alert("error")

    });

}

$(function () {
        ajaxGet("/api/reader/getBookInfo", {
            bookId: 1,
        }).success(function (res) {

            $('#desc').html(res.bookInfo.book_name + " " + res.bookInfo.author);
            pagenum = res.pagenum;
            $('#inputNum').val("1/" + pagenum)

        }).error(function (res) {
            alert("error")

        });

        ajaxGet("/api/reader/getTree", {
            bookId: 1,
        }).success(function (res) {

            $('#tree').treeview({
                data: res,         // 数据源
                showCheckbox: true,   //是否显示复选框
                highlightSelected: true,    //是否高亮选中
                //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                // nodeIcon: 'glyphicon glyphicon-folder-close',
                expandIcon: "glyphicon glyphicon-folder-close",
                collapseIcon: "glyphicon glyphicon-folder-open",
                emptyIcon: '',    //没有子节点的节点图标
                nodeIcon: '',
                multiSelect: false,    //多选
                onNodeSelected: function (event, data) {
                    console.log(data);
                    if(data.leaf){
                        showPage(data.number,data.sectionId);

                    }else {
                        return false
                    }
                    // alert(data.nodeId);
                    // console.log(data);
                }
            });

        }).error(function (res) {
            alert("error")

        });

        showPage(1);
        /*ajaxGet("/api/reader/getContent", {
         bookId: 1,
         }).success(function (res) {
         // alert("success");
         var rownum = 20;
         var colnum = 66;
         var reg=new RegExp("\n","g"); //创建正则RegExp对象


         var newstr=res.replace(reg,"<br>");


         $('#frameContent').html(newstr)

         var allpages = Math.ceil(parseInt(obj.scrollHeight)/parseInt(obj. offsetHeight));


         pages.innerHTML = "<b>共" + allpages + "页</b>";     //输出页面数量
         //         for (var i = 1; i <= allpages; i++) {
         //             pages.innerHTML += "<a href=\"javascript:showPage('" + i + "');\">第" + i + "页</a> ";
         // //循环输出第几页
         //         }
         pages.innerHTML += "      <a href=\"javascript:gotopage('-1');\">上一页</a>  <a href=\"javascript:gotopage('0');\">下一页</a>"


         // dynamicContent(20, 66, res);
         // $('#content').html(res)

         }).error(function (res) {
         alert("error")

         });*/


    }
)
;