/**
 * Created by lvcheng on 2017/2/24.
 */

var userMsg, llqHeight;
var ls = window.sessionStorage;

var userId;
var IP = "localhost:8085/";

// var dps_um = getSessionMsg();

$.when(true).then(function() {
	// if(!userMsg){
	//   window.location.href="index.html";
	//   return;
	// }
	// userId = userMsg.userId;

	if (!true) {
		window.location.href = "index.html";
	} else {

		// 当我修改完新的功能栏的时候，可以对此处修改
		llqHeight = document.documentElement.clientHeight;
		var cssStyle = '.qjbjDiv,.qdCreate,.qdListBox{height:' + (llqHeight - 138) + 'px;}#sidebar .panel-body{overflow:auto;border-top:0;border-bottom:0;padding:0;height: ' + (llqHeight - 441) + 'px;} ';
		$('head').append($('<style></style>').html(cssStyle));
		// $("#Route_conter").css("height",llqHeight-150+"px");
		$(".qdCreate").css("height", llqHeight - 138 + "px");

		$(function() {
			//文档加载完成后需要执行的代码
			var auri = window.location.hash;
			console.log($("[href='" + auri + "']").parent().addClass("active").siblings().removeClass("active"));
			$("[href='" + auri + "']").parent().addClass("active").siblings().removeClass("active").end().parentsUntil('#sidebarlt>ul>li').last().parent(".candrop").addClass("active").siblings().removeClass("active").find(".candrop>p.active").removeClass();
			//功能栏一级菜单的点击事件
			$("#sidebarlt>ul").on("click", "li", function() {
				$(this).siblings().removeClass("active").end().addClass("active").siblings().find(".dropmenu>div p.active").removeClass('active');
			});
			//功能栏二级菜单的点击事件
			$('#sidebarlt>ul').on("click", "p", function() {
				$(this).siblings().removeClass("active").end().addClass("active").parentsUntil("#sidebarlt>ul>li").last().parent().siblings().find("p.active").removeClass("active");
			})

			/*为功能栏增加滚动条
			 * 功能栏下所有展开框都需要滚动条
			 * 要求：高度自适应*/
			$("#sidebarlt>ul>li>.dropmenu>div").slimScroll({
				height: '100%'
			})
		});

		vipspa.start({
			view: '#ui-main-view', //页面路由的div(id=ui-main-view)
			router: {
                'home_page': {
                    templateUrl: 'webApp/home_page/home_page.html',
                    controller: 'webApp/home_page/home_page.js'
                },
				'a': {
					templateUrl: 'webApp/user/a.html',
					controller: 'webApp/user/a.js'
				},
                'b': {
                    templateUrl: 'webApp/b/b.html',
                    controller: 'webApp/b/b.js'
                },
				'system_setting':{
                    templateUrl: 'webApp/system_setting/system_setting.html',
                    controller: 'webApp/system_setting/system_setting.js'
                },
				'defaults': 'a' //默认显示页面
			},
			errorTemplateId: '#error' //错误显示页面
		});
	}
});


/*退出登录*/
function dengluOut() {
	var url = '/user/loginOut';
	ajaxPost(url, {}).success(function() {
		window.location.href = "index.html";
	})
}

/*获取session信息*/
function getSessionMsg() {
	var url = '/user/get_sessionInfo';
	return ajaxPost(url, {}).success(function(res) {
		userMsg = res.data;
	})
}

/*初始化zTree数据*/
// function initZTree() {
//   var url = '/area/find_areas_new';
//   ajaxPost(url, {
//     userId: userId
//   }).success(function (res) {
//     zTreeData = res.data;
//   });
// }

// 左侧菜单栏隐藏展开功能
$("#toggle-sidebar").on("click", function(e) {
	if ($(this).hasClass("toggle-flag")) {
		$("#sidebarlt").show();
		$("#content-title").css({
			'left': '210px'
		});
		$("#content").css({
			'left': '210px'
		});
		$(this).removeClass("toggle-flag");
	} else {
		$("#sidebarlt").hide();
		$("#content-title").css({
			'left': '0px'
		});
		$("#content").css({
			'left': '0px'
		});
		$(this).addClass("toggle-flag");
	}
});

/*当url改变的时候，左侧菜单栏中内容改变*/
$(window).bind('hashchange', function(e) {
	console.log('url改变了====' + e.target.location.hash);
	if (e.target.location.hash == '#/yabj') {
		$('.qyCon').removeClass('disNone');
	} else {
		$('.qyCon').addClass('disNone');
	}

	if (e.target.location.hash == '#/csbj') {
		$('.csCon').removeClass('disNone');
	} else {
		$('.csCon').addClass('disNone');
	}

	if (e.target.location.hash == '#/rwgl_reductAnalys') {
		$('.jpfxCon').removeClass('disNone');
	} else {
		$('.jpfxCon').addClass('disNone');
	}
});


/*左侧菜单点击后处理路由信息*/
$('#sidebar a').click(function(e) {
	e.preventDefault();
	var href = e.target.hash.replace('#', '#/');
	var a = $('#clickA');
	if (a.attr('href') == href) return;
	if (href == '#/xgpg' || href == '#/kqzlyb') return;
	a.attr('href', href);
	a[0].click();
});

/*封装一个遮罩层载入效果
 * 使用插件为blockUI
 * parameter：selector，method
 * selector：string，为css选择器，按照jquery中$的选择器
 * state：string，'start'or'end','start'表示启动载入效果，'end'表示关闭载入效果
 * */

configSystem = {
	// rootUrl = "http://makenv.ddns.net:8287",//prod
	rootUrl: "http://210.12.2.66:8302", //dev
	ajaxPost: function(url, parameter) {
		var jsonParams = JSON.stringify(parameter);
		return $.ajax(this.rootUrl + url, {
			contentType: "application/json;charset=UTF-8",
			type: "POST",
			async: true,
			dataType: 'JSON',
			data: jsonParams
		})
	},
	ajaxGet: function(url) {
		var getUrl = this.rootUrl + url;
		return $.get(getUrl);
	}
};
var config = {};
config.legend = ['circle', 'rect', 'triangle', 'roundRect', 'diamond'];



/*看名字*/
function ajaxPost(url, parameter) {
	 var parameterPar={
	 	data:parameter
	 };
	var p = JSON.stringify(parameterPar);
	return $.ajax(Ip + url, {
		contentType: "application/json",
		type: "POST",
		async: true,
		dataType: 'JSON',
		data: p
	})
}